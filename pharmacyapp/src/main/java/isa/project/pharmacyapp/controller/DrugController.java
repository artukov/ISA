package isa.project.pharmacyapp.controller;

import isa.project.pharmacyapp.dto.DrugDTO;
import isa.project.pharmacyapp.dto.DrugSpecDTO;
import isa.project.pharmacyapp.dto.DrugsFromReceiptPriceinPharmacyDTO;
import isa.project.pharmacyapp.dto.PharmaDrugDTO;
import isa.project.pharmacyapp.model.TimeSpam;
import isa.project.pharmacyapp.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/drug")
public class DrugController {

    private static final String AUTHORITY = "hasAuthority('USER')";

    @Autowired
    private DrugService drugService;

    @GetMapping(value="/findAll",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllSystemDrugs(){

        List<DrugDTO> drugDTOS = drugService.findAll();

        return new ResponseEntity<>(drugDTOS,HttpStatus.OK);
    }

    @PostMapping(value = "/add/{pharmacyID}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> addToPharmacyDrug(@RequestBody DrugDTO drugDTO, @PathVariable("pharmacyID") Long pharmacyID){

        try {
            drugService.addToPharmacyDrug(drugDTO, pharmacyID);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping(value = "/modify/{drugID}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> modifyDrug(@RequestBody DrugDTO drugDTO, @PathVariable("drugID") Long drugID){

        try {
            drugService.modifyDrug(drugID,drugDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "delete/{drugID}/{pharmacyID}")
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> deleteDrugFromPharmacy(@PathVariable("drugID")Long drugID,
                                                    @PathVariable("pharmacyID") Long pharmacyID){

        try {
            drugService.deleteDrug(drugID, pharmacyID);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @GetMapping(value = "/avgConsumption/{timespam}/{pharmacyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> getAverageDrugConsumption(@PathVariable("timespam")TimeSpam timeSpam,
                                                      @PathVariable("pharmacyID") Long pharmacyID){

        List<Double> consumption = drugService.getConsumptionStatistics(timeSpam, pharmacyID);

        return new ResponseEntity<>(consumption, HttpStatus.OK);
    }

    @GetMapping(value = "/allPharmacyDrugs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getPharmacyAllDrugs(@PathVariable("id") Long pharmacyID){

        List<DrugDTO> drugDTOS = this.drugService.findAllPharmacyDrugs(pharmacyID);

        return new ResponseEntity<>(drugDTOS,HttpStatus.OK);

    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getAllDrugs() {
        List<DrugDTO> dtos = this.drugService.findAll();

        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @GetMapping(value = "/search/{drugName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> searchDrugs(@PathVariable("drugName") String drugName){
        List<PharmaDrugDTO> dtos = this.drugService.searchDrugs(drugName);

        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @GetMapping(value = "/specification/{drugId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDrugSpec(@PathVariable("drugId") Long drugId){
        List<DrugSpecDTO> dtos = this.drugService.getDrugSpec(drugId);

        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @GetMapping(value = "/findNotInPharmacy/{pharmacyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> findAllDrugsNotInPharmacy(@PathVariable("pharmacyID") Long pharmacyID){

        List<DrugDTO> drugDTOS = null;
        try {
            drugDTOS = drugService.findAllNotPharmacyDrugs(pharmacyID);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(drugDTOS,HttpStatus.OK);
    }

    @GetMapping(value = "/substitutesId/{drugId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> findSubstituteDrugs(@PathVariable("drugId") Long drugId){
        List<Long> substitutes = drugService.findSubstituteDrug(drugId);

        return new ResponseEntity<>(substitutes,HttpStatus.OK);
    }

    @GetMapping(value = "/substitutes/{drugId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> searchDrugs(@PathVariable("drugId") Long drugId){
        List<Long> substitutes = drugService.findSubstituteDrug(drugId);
        List<DrugDTO> dtos = new ArrayList<>();
        for(Long id: substitutes){
            dtos.add(drugService.findById(id));
        }
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @GetMapping(value = "/ereceipt/{ereceiptId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDrugsFromEReceipt(@PathVariable("ereceiptId") Long ereceiptId){
        //List<Long> drugs = drugService.getDrugsFromEReceipt(ereceiptId);
        List<DrugsFromReceiptPriceinPharmacyDTO> pharmacies = new ArrayList<>();
        try {
            pharmacies = drugService.getPharmaciesWithDrugs(ereceiptId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pharmacies,HttpStatus.OK);
    }
}
