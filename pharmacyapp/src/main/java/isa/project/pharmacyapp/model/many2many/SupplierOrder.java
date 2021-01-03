package isa.project.pharmacyapp.model.many2many;

import isa.project.pharmacyapp.model.embedded_ids.OrderSupplierID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "supplier_order")
public class SupplierOrder {

    @EmbeddedId
    private OrderSupplierID id;

    @Column(nullable = true, name = "delivery_date")
    private Date deliveryDate;

    @Column(nullable = true, name = "price_offer")
    private Double priceOffer;

    public SupplierOrder() {
    }

    public OrderSupplierID getId() {
        return id;
    }

    public void setId(OrderSupplierID id) {
        this.id = id;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getPriceOffer() {
        return priceOffer;
    }

    public void setPriceOffer(Double priceOffer) {
        this.priceOffer = priceOffer;
    }
}
