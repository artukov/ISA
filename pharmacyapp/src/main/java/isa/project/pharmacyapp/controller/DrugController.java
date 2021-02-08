package isa.project.pharmacyapp.controller;

import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import isa.project.pharmacyapp.dto.DrugDTO;
import isa.project.pharmacyapp.dto.DrugSpecDTO;
import isa.project.pharmacyapp.dto.DrugsFromReceiptPriceinPharmacyDTO;
import isa.project.pharmacyapp.dto.PharmaDrugDTO;
import isa.project.pharmacyapp.model.DrugShapes;
import isa.project.pharmacyapp.model.TimeSpam;
import isa.project.pharmacyapp.qrcode.QRReader;
import isa.project.pharmacyapp.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/drug")
public class DrugController {

    private static final String AUTHORITY = "hasAuthority('USER')";
    private static final String ADMIN_AUTHORITY = "hasAuthority('ADMIN')";
    private static final String ALL_AUTHORITY = "hasAnyAuthority('ADMIN','USER')";


    @Autowired
    private DrugService drugService;

    @GetMapping(value="/findAll",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(ALL_AUTHORITY)
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

    @GetMapping(value = "/shapes", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(ALL_AUTHORITY)
    public ResponseEntity<?> getAllDrugShapes(){
        return new ResponseEntity<>(DrugShapes.values(),HttpStatus.OK);
    }

    @PostMapping(value="/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(ADMIN_AUTHORITY)
    public ResponseEntity<?> postNewDrug(@RequestBody DrugDTO drugDTO){
        try {
            drugService.createNewDrug(drugDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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

    @PostMapping(value = "/ereceipt", consumes = "text/plain")
    @PreAuthorize(AUTHORITY)
    public ResponseEntity<?> getDrugsFromEReceipt(@RequestBody String ereceiptId) throws WriterException, IOException,
            NotFoundException {
        //skalnjam /r/n sa kraja 

       Integer len = ereceiptId.length();
       String str = ereceiptId.substring(0,len-2);
       Path pathTest = Paths.get(str);
        String filePath = pathTest.toString();

        // Encoding charset
        String charset = "UTF-8";

        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
                = new HashMap<EncodeHintType,
                ErrorCorrectionLevel>();

        hashMap.put(EncodeHintType.ERROR_CORRECTION,
                ErrorCorrectionLevel.L);
        String receiptString = QRReader.readQR(filePath,charset,hashMap);
        Long id = Long.parseLong(receiptString);
        List<DrugsFromReceiptPriceinPharmacyDTO> pharmacies = new ArrayList<>();
        try {
            pharmacies = drugService.getPharmaciesWithDrugs(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pharmacies,HttpStatus.OK);
    }
}
