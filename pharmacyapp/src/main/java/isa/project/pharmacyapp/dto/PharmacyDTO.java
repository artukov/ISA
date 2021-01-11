package isa.project.pharmacyapp.dto;

import isa.project.pharmacyapp.model.*;

import java.util.List;

public class PharmacyDTO {

    private Long id;
    private String name;
    private String description;
    private List<Double> ratings;

//    public List<Long> pharmacistIDs;
//    public List<Long> priceListIDs;
//    public List<Long> pharmacyAdminIDs;
//    public List<Long> absenceRequestIDs;
//    public List<Long> promotionsIDs;
//
//    public List<Long> dermatologistIDs;
//    public List<Long> drugIDs;

    public Long calendarID;
//    public Long addressID;

    public Address address;

    public PharmacyDTO() {
    }

    public PharmacyDTO(Long id, String name, String description, List<Double> ratings,  Long calendarID, Address address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ratings = ratings;
//        this.pharmacistIDs = pharmacistIDs;
//        this.priceListIDs = priceListIDs;
//        this.pharmacyAdminIDs = pharmacyAdminIDs;
//        this.absenceRequestIDs = absenceRequestIDs;
//        this.promotionsIDs = promotionsIDs;
//        this.dermatologistIDs = dermatologistIDs;
//        this.drugIDs = drugIDs;
        this.calendarID = calendarID;
//        this.addressID = addressID;
        this.address = address;
    }


    public static PharmacyDTO pharmacy2DTO(Pharmacy pharmacy){
        PharmacyDTO retDTO = new PharmacyDTO(
                pharmacy.getId(),
                pharmacy.getName(),
                pharmacy.getDescription(),
                pharmacy.getRatings(),
                pharmacy.getCalendar().getId(),
                pharmacy.getAddress()
        );

        return retDTO;


    }

    public static void dto2Pharmacy(Pharmacy pharmacy, PharmacyDTO pharmacyDTO) {
        pharmacy.setName(pharmacyDTO.getName());
        pharmacy.setDescription(pharmacyDTO.getDescription());
        pharmacy.setRatings(pharmacyDTO.getRatings());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(List<Double> ratings) {
        this.ratings = ratings;
    }
    public Long getCalendarID() {
        return calendarID;
    }

    public void setCalendarID(Long calendarID) {
        this.calendarID = calendarID;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



}
