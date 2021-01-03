package isa.project.pharmacyapp.model.embedded_ids;

import isa.project.pharmacyapp.model.Supplier;
import isa.project.pharmacyapp.model.SupplyOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderSupplierID implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private SupplyOrder order;

    public OrderSupplierID() {
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public SupplyOrder getOrder() {
        return order;
    }

    public void setOrder(SupplyOrder order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderSupplierID)) return false;
        OrderSupplierID that = (OrderSupplierID) o;
        return Objects.equals(supplier, that.supplier) &&
                Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplier, order);
    }
}
