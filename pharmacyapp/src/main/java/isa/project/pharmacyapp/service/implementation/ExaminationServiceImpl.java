package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.ConsultationDTO;
import isa.project.pharmacyapp.dto.ExaminationDTO;
import isa.project.pharmacyapp.dto.WorkingHoursDTO;
import isa.project.pharmacyapp.exception.DermatologistNotWorkingException;
import isa.project.pharmacyapp.exception.ExaminationOverlappingException;
import isa.project.pharmacyapp.exception.InsertingConsultationException;
import isa.project.pharmacyapp.model.Calendar;
import isa.project.pharmacyapp.model.Consultation;
import isa.project.pharmacyapp.model.Examination;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.model.embedded_ids.CalendarAppointmentsID;
import isa.project.pharmacyapp.model.many2many.CalendarAppointments;
import isa.project.pharmacyapp.repository.*;
import isa.project.pharmacyapp.service.CalendarService;
import isa.project.pharmacyapp.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class ExaminationServiceImpl implements ExaminationService {

    @Autowired
    private ExaminationRepository repository;

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private CalendarService calendarService;


    private String CLASS_NAME = this.getClass().getName();

    @Value("examination not found by given id")
    private String NOT_FOUND;

    @Value("unsuccessful save")
    private String UNSUCCESSFUL;

    @Override
    public Examination findById(Long id) {
        Examination examination = this.repository.findById(id).orElse(null);

        return examination;
    }

    @Override
    public void createNewExamination(ExaminationDTO examinationDTO) throws Exception, ExaminationOverlappingException {
        Examination examination = new Examination();

        Date endDateTime = new Date();
        endDateTime.setTime( examinationDTO.getBeggingDateTime().getTime());
//        System.out.println("Before change \t " + endDateTime);
        endDateTime.setMinutes(endDateTime.getMinutes() + examinationDTO.getDuration());
//        System.out.println("After change \t" + endDateTime );

        if(repository.overlappingExaminations(examinationDTO.getBeggingDateTime(),endDateTime,
                examinationDTO.getDermatologist_id()) != 0.0){
            //System.out.println("There are some overlapping");
            throw new ExaminationOverlappingException("Begging of the examination and its duration are " +
                    "overlapping with some examinations that dermatologist already has booked", examinationDTO.getBeggingDateTime(), endDateTime);
        }

        Date endHours = new Date();
        endHours.setTime(examinationDTO.getBeggingDateTime().getTime());
        endHours.setHours(endHours.getHours() + examinationDTO.getDuration());

        Double resultPatientConsultation = patientRepository.overlappingConsultationHours(examinationDTO.getBeggingDateTime(), endHours, examinationDTO.getPatient_id());
        if(resultPatientConsultation != 0){
            throw new ExaminationOverlappingException("Patient consultation hours are overlapping",examinationDTO.getBeggingDateTime(),endHours);
        }
        Double resultPatientExamination = patientRepository.overlappingExaminationHours(examinationDTO.getBeggingDateTime(), endHours, examinationDTO.getPatient_id());
        if(resultPatientExamination != 0){
            throw new ExaminationOverlappingException("Patient examination hours are overlapping",examinationDTO.getBeggingDateTime(),endHours);
        }
        this.saveExamination(examination, examinationDTO);

    }



    @Override
    public void modifyExamination(Long id, ExaminationDTO examinationDTO) throws Exception {
        Examination examination = this.repository.findById(id).orElse(null);

        this.saveExamination(examination,examinationDTO);
    }

    @Override
    public void deleteExamination(Long id) {

    }

    @Override
    public void saveExamination(Examination examination, ExaminationDTO examinationDTO) throws Exception {
        ExaminationDTO.dto2Examination(examination, examinationDTO);
        /**
         * TODO
         * Check if examination is possible
         * */
        try {
            examination.setDermatologist(dermatologistRepository.findById(examinationDTO.getDermatologist_id()).orElse(null));
            examination.setPatient(patientRepository.findById(examinationDTO.getPatient_id()).orElse(null));

            for(Long drugID : examinationDTO.getDrugs()){
                examination.getDrug().add(drugRepository.findById(drugID).orElse(null));
            }

        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception(CLASS_NAME+"::saveExamination " + e.getMessage());
        }

        try {
            examination = repository.save(examination);
            /**
             * If the appointment exists already in the calendar then there is no need
             * To insert another raw into calendar_appointments table,
             * this is when the dermatologist/pharmacist modifies the appointment with status finished or something similar
             * On the other hand, if appointment does not exists, program is obliged to insert new row*/
            if(!calendarService.checkIfAppointmentExists(examinationDTO.getPharmacyID(), examination.getId())){
                calendarService.saveAppointment(examinationDTO.getPharmacyID(), examination);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception(CLASS_NAME + "::saveExamination " + UNSUCCESSFUL);
        }
    }

    @Override
    public List<ExaminationDTO> findFreeExaminations(Long dermaID, Long pharmacyID) {

        List<Examination> examinations = repository.findByDermaPharmacy(dermaID, pharmacyID);

        ArrayList<ExaminationDTO> examinationDTOS = new ArrayList<>();
        for(Examination e : examinations){
            examinationDTOS.add(ExaminationDTO.examination2DTO(e));
        }


        return examinationDTOS;
    }

    @Override
    public List<ExaminationDTO> findFreeExaminations(Long pharmacyID) {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyID).orElse(null);

        List<Examination> examinations = repository.findByPharmacy(pharmacyID);
        ArrayList<ExaminationDTO> examinationDTOS = new ArrayList<>();
        for(Examination e : examinations){
            examinationDTOS.add(ExaminationDTO.examination2DTO(e));
        }


        return examinationDTOS;


    }

    @Override
    public void createNewExaminationPharmacy(ExaminationDTO dto, Long pharmacyID) throws Exception,DermatologistNotWorkingException {
        List<Object[]> dermaWorkingHours = this.dermatologistRepository.getWorkingHours(dto.getDermatologist_id(),pharmacyID);
        Object[] arrayO = dermaWorkingHours.get(0);
        WorkingHoursDTO workingHours = new WorkingHoursDTO((Date) arrayO[0],(Integer) arrayO[1]);

        if(dto.getBeggingDateTime().before(workingHours.getStartHour()))
            throw new DermatologistNotWorkingException("Dermatologist is not yet in pharmacy at that hours" , dto.getBeggingDateTime());
        Date endHours = workingHours.getStartHour();
        endHours.setHours(endHours.getHours() + workingHours.getHours());
        if(dto.getBeggingDateTime().after(endHours))
            throw new DermatologistNotWorkingException("Dermatologist is finished at that hours", true);
        Date endExamTime = new Date();
        endExamTime.setTime(dto.getBeggingDateTime().getTime());
        endExamTime.setMinutes(endExamTime.getMinutes() + dto.getDuration());

        if(endExamTime.after(endHours))
            throw new DermatologistNotWorkingException("Dermatologist is finished at that hours", true);

        this.createNewExamination(dto);
    }

    @Override
    public void modifyExamination(ExaminationDTO examinationDTO) throws Exception {
        Examination examination = repository.findById(examinationDTO.getId()).orElse(null);
        //consultationRepository.deleteById(consultationDTO.getId());
        if(examination == null){
            throw new NoSuchElementException("ExaminationSerivceImpl::modifyExamination(ExaminationDTO examiantionDTO)" +
                    "examiantion could not be find by the given id");
        }

        try {
            this.saveExamination(examination, examinationDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("ExaminationServiceImpl::modifyExamination(ExaminationDTO examiantionDTO)" +
                    "saving of the modified object did not excecute");
        }
    }
}
