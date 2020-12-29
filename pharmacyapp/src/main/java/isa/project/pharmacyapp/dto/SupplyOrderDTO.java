package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import isa.project.pharmacyapp.model.OrderStatus;
import isa.project.pharmacyapp.model.SupplyOrder;

import java.util.Date;
import java.util.List;

public class SupplyOrderDTO {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OrderStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy ss:mm:HH Z")
    private Date deadlineDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy ss:mm:HH Z")
    private Date deliveryDate;
    private List<Long> drugs;
    private Double priceOffer;

    private List<Integer> amount;
    private Long pharmacyID;

    public SupplyOrderDTO() {
    }

    public SupplyOrderDTO(Long id, OrderStatus status, Date deadlineDate, Date deliveryDate,
                          List<Long> drugs, Double priceOffer, List<Integer> amount, Long pharmacyID) {
        this.id = id;
        this.status = status;
        this.deadlineDate = deadlineDate;
        this.deliveryDate = deliveryDate;
        this.drugs = drugs;
        this.priceOffer = priceOffer;
        this.amount = amount;
        this.pharmacyID = pharmacyID;
    }

    public static void dto2Order(SupplyOrder order, SupplyOrderDTO orderDTO) {
        order.setStatus(orderDTO.getStatus());
        order.setDeadlineDate(orderDTO.getDeadlineDate());
        order.setDeliveryDate(orderDTO.getDeliveryDate());
        order.setPriceOffer(orderDTO.getPriceOffer());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<Long> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Long> drugs) {
        this.drugs = drugs;
    }

    public Double getPriceOffer() {
        return priceOffer;
    }

    public void setPriceOffer(Double priceOffer) {
        this.priceOffer = priceOffer;
    }

    public List<Integer> getAmount() {
        return amount;
    }

    public void setAmount(List<Integer> amount) {
        this.amount = amount;
    }

    public Long getPharmacyID() {
        return pharmacyID;
    }

    public void setPharmacyID(Long pharmacyID) {
        this.pharmacyID = pharmacyID;
    }
}
