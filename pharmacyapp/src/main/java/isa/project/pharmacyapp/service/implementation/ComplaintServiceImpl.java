package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.ComplaintDTO;
import isa.project.pharmacyapp.model.Complaints;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.repository.ComplaintRepository;
import isa.project.pharmacyapp.service.ComplaintService;
import isa.project.pharmacyapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private EmailService emailService;


    @Override
    public void answerComplaint(ComplaintDTO complaintDTO) throws Exception {
        Complaints complaints = complaintRepository.findById(complaintDTO.getId()).orElseThrow(
                () -> new Exception("Complaint does not exists with give  id"));
        complaints.setAnswer(complaintDTO.getAnswer());

        sendEmailWithAnswer(complaints.getSubmitter(),complaints.getAnswer(),complaints.getDescription());

        try{
            complaintRepository.save(complaints);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("Saving complaint");
        }



    }

    @Override
    public List<ComplaintDTO> findUnanswered() {
        List<Complaints> unansweredComplaints = complaintRepository.findByAnswerIsNull();
        ArrayList<ComplaintDTO> complaintDTOS = new ArrayList<>();
        for(Complaints complaints : unansweredComplaints){
            complaintDTOS.add(ComplaintDTO.complaint2dto(complaints));
        }
        return complaintDTOS;
    }

    private void sendEmailWithAnswer(User submitter, String answer, String description) {
        StringBuilder builder = new StringBuilder();
        builder.append("Dear ");
        builder.append(submitter.getFirstname());
        builder.append(" ");
        builder.append(submitter.getLastname());
        builder.append("\n");
        builder.append("We alert you with an answer to your complaint.\n");
        builder.append(description);
        builder.append("\n with \n");
        builder.append(answer);

        emailService.sendSimpleMessage(submitter.getEmail(),"Answering complaint you made", builder.toString());

    }
}
