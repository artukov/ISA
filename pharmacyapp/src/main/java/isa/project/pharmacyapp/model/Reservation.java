package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  Reservation.java
 * Author:  User
 * Purpose: Defines the Class Reservation
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;

/** @pdOid cf2cc870-0189-465c-a222-edfcbfff6c94 */
@Entity
@Table(name = "reservation")
public class Reservation {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   /** @pdOid b41dfd4c-5da7-4015-a2b0-01c0423858ed */
   @Column(nullable = false, name = "acceptance_date")
   private Date acceptanceDate;

   @Column(name = "accepted")
   private Boolean accepted;

   /** @pdRoleInfo migr=no name=Drug assc=association14 coll=java.util.List impl=java.util.ArrayList mult=1..* */
   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(
           name = "reservation_drug",
           joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "drug_id", referencedColumnName = "id")
   )
   private java.util.List<Drug> drug;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "patient_id", referencedColumnName = "id")
   private Patient patient;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
   private Pharmacy pharmacy;


   public Reservation() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Date getAcceptanceDate() {
      return acceptanceDate;
   }

   public void setAcceptanceDate(Date acceptanceDate) {
      this.acceptanceDate = acceptanceDate;
   }

   public Boolean getAccepted() {
      return accepted;
   }

   public void setAccepted(Boolean accepted) {
      this.accepted = accepted;
   }

   public Patient getPatient() {
      return patient;
   }

   public void setPatient(Patient patient) {
      this.patient = patient;
   }

   public Pharmacy getPharmacy() {
      return pharmacy;
   }

   public void setPharmacy(Pharmacy pharmacy) {
      this.pharmacy = pharmacy;
   }




   /** @pdGenerated default getter */
   public java.util.List<Drug> getDrug() {
      if (drug == null)
         drug = new java.util.ArrayList<Drug>();
      return drug;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDrug() {
      if (drug == null)
         drug = new java.util.ArrayList<Drug>();
      return drug.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDrug */
   public void setDrug(java.util.List<Drug> newDrug) {
      removeAllDrug();
      for (java.util.Iterator iter = newDrug.iterator(); iter.hasNext();)
         addDrug((Drug)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDrug */
   public void addDrug(Drug newDrug) {
      if (newDrug == null)
         return;
      if (this.drug == null)
         this.drug = new java.util.ArrayList<Drug>();
      if (!this.drug.contains(newDrug))
         this.drug.add(newDrug);
   }
   
   /** @pdGenerated default remove
     * @param oldDrug */
   public void removeDrug(Drug oldDrug) {
      if (oldDrug == null)
         return;
      if (this.drug != null)
         if (this.drug.contains(oldDrug))
            this.drug.remove(oldDrug);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDrug() {
      if (drug != null)
         drug.clear();
   }

}