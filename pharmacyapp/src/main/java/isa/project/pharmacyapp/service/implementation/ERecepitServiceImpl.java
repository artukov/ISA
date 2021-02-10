package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.EReceiptDTO;
import isa.project.pharmacyapp.model.EReceipt;
import isa.project.pharmacyapp.repository.ERecepitRepository;
import isa.project.pharmacyapp.service.ERecepitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ERecepitServiceImpl implements ERecepitService {

    @Autowired
    private ERecepitRepository eRecepitRepository;

    @Override
    public List<EReceiptDTO> findByPatient(Long patientId){
        List<EReceipt> eReceipts = eRecepitRepository.findByPatientId(patientId);
        List<EReceiptDTO> dtos = new ArrayList<>();
        for(EReceipt e: eReceipts) {
            EReceiptDTO dto = new EReceiptDTO();
            dto.setId(e.getId());
            dto.setCode(e.getCode());
            dto.setIssue_date(e.getIssueDate());
            dto.setPatientId(e.getPatient().getId());
            dtos.add(dto);
        }
        return dtos;
    }
}
