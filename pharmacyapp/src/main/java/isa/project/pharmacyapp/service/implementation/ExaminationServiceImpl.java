package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.ExaminationDTO;
import isa.project.pharmacyapp.model.Examination;
import isa.project.pharmacyapp.repository.DermatologistRepository;
import isa.project.pharmacyapp.repository.DrugRepository;
import isa.project.pharmacyapp.repository.ExaminationRepository;
import isa.project.pharmacyapp.repository.PatientRepository;
import isa.project.pharmacyapp.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


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
    public void createNewExamination(ExaminationDTO examinationDTO) throws Exception {
        Examination examination = new Examination();
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
            repository.save(examination);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception(CLASS_NAME + "::saveExamination " + UNSUCCESSFUL);
        }

    }
}
