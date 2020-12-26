package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.AppointmentDTO;
import isa.project.pharmacyapp.dto.ConsultationDTO;

import isa.project.pharmacyapp.model.Consultation;
import isa.project.pharmacyapp.repository.ConsultationRepository;
import isa.project.pharmacyapp.repository.DrugRepository;
import isa.project.pharmacyapp.repository.PatientRepository;
import isa.project.pharmacyapp.repository.PharmacistRepository;
import isa.project.pharmacyapp.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private PharmacistRepository pharmacistRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DrugRepository drugRepository;

    @Override
    public void createNewConsultation(ConsultationDTO dto) throws Exception {
        Consultation consultation = new Consultation();

        this.saveConsultation(consultation, dto);
    }

    @Override
    public void saveConsultation(Consultation consultation, ConsultationDTO consultationDTO) throws Exception {
        ConsultationDTO.dto2Consultation(consultation, consultationDTO);

        try {
            consultation.setPharmacist(pharmacistRepository.findById(consultationDTO.getPharmacistID()).orElse(null));
            consultation.setPatient(patientRepository.findById(consultationDTO.getPatient_id()).orElse(null));

            for(Long drugID : consultationDTO.getDrugs()){
                consultation.getDrug().add(drugRepository.findById(drugID).orElse(null));
            }
        }
        catch (Exception e){
            throw new Exception();
        }

        try {
            consultationRepository.save(consultation);
        }
        catch (Exception e){
            throw new Exception();
        }


    }
}
