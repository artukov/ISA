package isa.project.pharmacyapp.model.many2many;

import isa.project.pharmacyapp.model.embedded_ids.PriceListDrugID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pl_drug")
public class PriceListDrug {

    @EmbeddedId
    private PriceListDrugID id;

    @Column(name = "price", nullable = false)
    private Double price;


    public PriceListDrug() {
    }

    public PriceListDrugID getId() {
        return id;
    }

    public void setId(PriceListDrugID id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
