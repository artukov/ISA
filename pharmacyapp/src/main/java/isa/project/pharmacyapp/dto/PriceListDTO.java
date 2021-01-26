package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import isa.project.pharmacyapp.model.PriceList;
import isa.project.pharmacyapp.model.many2many.PriceListDrug;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PriceListDTO {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    private Date endDate;

    private Long pharmacyID;

    private List<PlDrugDTO> drugs;

    public PriceListDTO() {
    }

    public PriceListDTO(Long id, Date startDate, Date endDate, Long pharmacyID, List<PlDrugDTO> drugs) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pharmacyID = pharmacyID;
        this.drugs = drugs;
    }

    public static void dto2PriceList(PriceList priceList, PriceListDTO priceListDTO) {
        priceList.setStartDate(priceListDTO.getStartDate());
        priceList.setEndDate(priceListDTO.getEndDate());
    }

    public static PriceListDTO priceList2DTO(PriceList priceList) {
        PriceListDTO dto = new PriceListDTO(
                priceList.getId(),
                priceList.getStartDate(),
                priceList.getEndDate(),
                priceList.getPharmacy().getId(),
                new ArrayList<>()
        );
        for(PriceListDrug drug : priceList.getDrugs()){
            dto.getDrugs().add(PlDrugDTO.plDrug2DTO(drug));
        };

        return dto;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getPharmacyID() {
        return pharmacyID;
    }

    public void setPharmacyID(Long pharmacyID) {
        this.pharmacyID = pharmacyID;
    }

    public List<PlDrugDTO> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<PlDrugDTO> drugs) {
        this.drugs = drugs;
    }
}
