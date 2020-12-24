package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  Consultation.java
 * Author:  User
 * Purpose: Defines the Class Consultation
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;
/** @pdOid 06c8bac4-f2dd-4048-aa5c-c4cf43b068a8 */
@Entity
@Table(name = "consultation")
public class Consultation extends Appointment {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pharmacist_id")
    private Pharmacist pharmacist;

    public Consultation() {
    }

    @Override
    public void patientAttendance(Boolean attendance) {

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }
}