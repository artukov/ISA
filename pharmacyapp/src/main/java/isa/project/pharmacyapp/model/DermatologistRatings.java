package isa.project.pharmacyapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Class that holds informations about dermatologist's ratings in a particular pharmacies
 * Primary keys are both Dermathologists's key, as a foreing key
 * And Pharmacy's primary key, as a foreing key
 * Algohritam that was used is Embedded primary key
 * It is enabled due to @EmbeddedId and  @Embeddable annotations
 * @Embeddable class had to implement Serializable interface
 * */

@Entity
@Table(name = "derma_ratings")
public class DermatologistRatings {

    @EmbeddedId
    private DermaPharmacyId id;

    @ElementCollection(fetch = FetchType.EAGER,targetClass = Double.class)
    private List<Double> ratings;

    public DermatologistRatings() {
    }

    public DermaPharmacyId getId() {
        return id;
    }

    public void setId(DermaPharmacyId id) {
        this.id = id;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(List<Double> ratings) {
        this.ratings = ratings;
    }
}
@Embeddable
class DermaPharmacyId implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "derma_id")
    private Dermatologist dermatologist;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    /**
     * Object::equals() and Object::hashCode() have been overriden
     * because of the warning that showed up in the console during build.
     * They have no great meaning for the production of the code
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DermaPharmacyId)) return false;
        DermaPharmacyId that = (DermaPharmacyId) o;
        return Objects.equals(dermatologist, that.dermatologist) &&
                Objects.equals(pharmacy, that.pharmacy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dermatologist, pharmacy);
    }
}
