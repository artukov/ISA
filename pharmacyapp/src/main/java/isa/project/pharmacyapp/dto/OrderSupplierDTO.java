package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import isa.project.pharmacyapp.model.OrderStatus;

import java.util.Date;

public class OrderSupplierDTO {

    private Long orderID;
    private Long supplierID;
    private Double priceOffer;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    private Date deliveryDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OrderStatus status;

    public OrderSupplierDTO() {
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
}
