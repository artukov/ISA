package isa.project.pharmacyapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Class that holds information about dermatologist's ratings in a particular pharmacies
 * Primary keys are both Dermatologist's' key, as a foreign key
 * And Pharmacy's primary key, as a foreign key
 * Algorithm that was used is Embedded primary key
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

