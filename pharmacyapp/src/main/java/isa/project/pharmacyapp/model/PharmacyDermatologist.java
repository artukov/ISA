package isa.project.pharmacyapp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pharmacy_derma")
public class PharmacyDermatologist {

    @EmbeddedId
    private DermaPharmacyId id;

    @Column(nullable = false, name = "start_hour")
    private Date startHour;

    @Column(nullable = false)
    private Integer hours;

    public PharmacyDermatologist() {
    }

    public DermaPharmacyId getId() {
        return id;
    }

    public void setId(DermaPharmacyId id) {
        this.id = id;
    }

    public Date getStartHour() {
        return startHour;
    }

    public void setStartHour(Date startHour) {
        this.startHour = startHour;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}
