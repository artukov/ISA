package isa.project.pharmacyapp.model;
/***********************************************************************
 * Module:  Allergy.java
 * Author:  User
 * Purpose: Defines the Class Allergy
 ***********************************************************************/

import java.util.*;

import javax.persistence.*;

/** @pdOid 50e6998e-0fc6-4991-a016-d20d8a382048 */
@Entity
@Table(name = "allergy")
public class Allergy {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   /** @pdRoleInfo migr=no name=Drug assc=association25 coll=java.util.Set impl=java.util.HashSet mult=1..* */
   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(
           name = "allergy_drug",
           joinColumns = @JoinColumn(name = "allergy_id"),
           inverseJoinColumns = @JoinColumn(name = "drug_id")
   )
   public java.util.List<Drug> drug;

   @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   @JoinColumn(name = "patient_id")
   private Patient patient;

   public Allergy() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Patient getPatient() {
      return patient;
   }

   public void setPatient(Patient patient) {
      this.patient = patient;
   }

   public List<Drug> getDrug() {
      return drug;
   }

   public void setDrug(List<Drug> drug) {
      this.drug = drug;
   }
}