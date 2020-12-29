package isa.project.pharmacyapp.model.embedded_ids;

import isa.project.pharmacyapp.model.Supplier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SupplyOrderID implements Serializable {

    @ManyToOne
    @JoinColumn
    private Supplier supplier;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderID;

    public SupplyOrderID() {
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupplyOrderID)) return false;
        SupplyOrderID that = (SupplyOrderID) o;
        return supplier.equals(that.supplier) &&
                orderID.equals(that.orderID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplier, orderID);
    }
}
