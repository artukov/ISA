package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.ComplaintDTO;

import java.util.List;

public interface ComplaintService {
    void answerComplaint(ComplaintDTO complaintDTO) throws Exception;

    List<ComplaintDTO> findUnanswered();
}
