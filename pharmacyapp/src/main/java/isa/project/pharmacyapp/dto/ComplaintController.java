package isa.project.pharmacyapp.dto;

import isa.project.pharmacyapp.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/complaint")
@CrossOrigin
public class ComplaintController {

    private static final String AUTHORITY = "hasAuthority('USER')";
    private static final String ADMIN_AUTHORITY = "hasAuthority('ADMIN')";
    private static final String ALL_AUTHORITY = "hasAnyAuthority('ADMIN','USER')";

    @Autowired
    private ComplaintService complaintService;

    @GetMapping(value = "/findUnanswered", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(ADMIN_AUTHORITY)
    public ResponseEntity<?> getAllUnansweredComplaints(){
        List<ComplaintDTO> complaintDTOS = complaintService.findUnanswered();

        return new ResponseEntity<>(complaintDTOS,HttpStatus.OK);
    }


    @PutMapping(value="/answer",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(ADMIN_AUTHORITY)
    public ResponseEntity<?> answerToComplaint(@RequestBody ComplaintDTO complaintDTO){

        try {
            complaintService.answerComplaint(complaintDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
