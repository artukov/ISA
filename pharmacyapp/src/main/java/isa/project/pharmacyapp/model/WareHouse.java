package isa.project.pharmacyapp.model;

import isa.project.pharmacyapp.model.many2many.WareHouseDrug;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "warehouse")
public class WareHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(nullable = false)
    private Integer size;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "warehouse_id")
    private List<WareHouseDrug> drugs;


    public WareHouse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<WareHouseDrug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<WareHouseDrug> drugs) {
        this.drugs = drugs;
    }
}
