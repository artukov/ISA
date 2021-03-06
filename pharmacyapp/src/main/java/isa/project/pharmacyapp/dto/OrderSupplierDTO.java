package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import isa.project.pharmacyapp.model.OrderStatus;
import isa.project.pharmacyapp.model.many2many.SupplierOrder;

import java.util.Date;

public class OrderSupplierDTO {

    private Long orderID;
    private Long supplierID;
    private String supplierEmail;
    private Double priceOffer;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    private Date deliveryDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OrderStatus status;

    public OrderSupplierDTO() {
    }

//    public OrderSupplierDTO(Long orderID, Long supplierID, Double priceOffer, Date deliveryDate, OrderStatus status) {
//        this.orderID = orderID;
//        this.supplierID = supplierID;
//        this.priceOffer = priceOffer;
//        this.deliveryDate = deliveryDate;
//        this.status = status;
//    }

    public OrderSupplierDTO(Long orderID, Long supplierID, String supplierEmail,
                            Double priceOffer, Date deliveryDate, OrderStatus status) {
        this.orderID = orderID;
        this.supplierID = supplierID;
        this.supplierEmail = supplierEmail;
        this.priceOffer = priceOffer;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public static OrderSupplierDTO supplierOrder2DTO(SupplierOrder supplierOrder){
        OrderSupplierDTO retDTO = new OrderSupplierDTO(
                supplierOrder.getId().getOrder().getId(),
                supplierOrder.getId().getSupplier().getId(),
                supplierOrder.getId().getSupplier().getEmail(),
                supplierOrder.getPriceOffer(),
                supplierOrder.getDeliveryDate(),
                supplierOrder.getStatus()
        );

        return  retDTO;

    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Long supplierID) {
        this.supplierID = supplierID;
    }

    public Double getPriceOffer() {
        return priceOffer;
    }

    public void setPriceOffer(Double priceOffer) {
        this.priceOffer = priceOffer;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }
}
