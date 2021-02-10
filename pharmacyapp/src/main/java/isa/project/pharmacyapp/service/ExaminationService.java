package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.ConsultationDTO;
import isa.project.pharmacyapp.dto.ExaminationDTO;
import isa.project.pharmacyapp.exception.ExaminationOverlappingException;
import isa.project.pharmacyapp.model.Examination;

import java.util.Date;
import java.util.List;

public interface ExaminationService {

    public abstract Examination findById(Long id);
    public abstract void createNewExamination(ExaminationDTO examinationDTO) throws Exception, ExaminationOverlappingException;
    public abstract void modifyExamination(Long id, ExaminationDTO examinationDTO) throws Exception;
    public abstract void deleteExamination(Long id);

    public abstract void saveExamination(Examination examination, ExaminationDTO examinationDTO) throws Exception;

    List<ExaminationDTO> findFreeExaminations(Long dermaID, Long pharmacyID);

    List<ExaminationDTO> findFreeExaminations(Long pharmacyID);

    void modifyExamination(ExaminationDTO examinationDTO) throws Exception;
    void createNewExaminationPharmacy(ExaminationDTO dto, Long pharmacyID) throws Exception;

    Date getExamDate(Long id);
    ExaminationDTO findExamination(Long patientId, Date dateTime);

    List<ExaminationDTO> getPatientsDermaAppointments(Long patientId);
    List<ExaminationDTO> getPatientsDermaAppointmentsNotFinished(Long patientId);
}
