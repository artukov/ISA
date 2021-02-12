package isa.project.pharmacyapp.model;
/***********************************************************************
 * Module:  Appointment.java
 * Author:  User
 * Purpose: Defines the Class Appointment
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;

/** @pdOid e71c7cf4-069a-493f-aa05-de27de76f80d */
@Entity
@Table(name = "appointment")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Appointment {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   protected Long id;

   /** @pdOid 9ad910a3-867d-4f0c-83ed-e3fd1439b842 */
   @Column(nullable = true)
   protected String report;
   /** @pdOid 1bb72927-18bd-44f0-b1c7-85a70871e6e0 */
   @Column(nullable = false, name = "beg_date")
   protected Date beggingDateTime;

   /** @pdOid ab6b7d96-4a16-4991-a7f3-465b7e183036 */
   @Column(nullable = false)
   protected Integer duration;

   @Column
   protected Boolean finished;

   @Column(name = "price", nullable = true)
   protected Double price;
   
   /** @pdRoleInfo migr=no name=Drug assc=association24 coll=java.util.List impl=java.util.ArrayList mult=0..* */
   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(
           name = "appointment_drug",
           joinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "drug_id", referencedColumnName = "id")
   )
   protected java.util.List<Drug> drug;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "patient_id")
   protected Patient patient;

   
   
   /** @param attendance
    * @pdOid 80190f73-ae6b-4bb8-8356-6284e6a152ce */
   public abstract void patientAttendance(Boolean attendance);

   public Appointment() {
   }

   public Patient getPatient() {
      return patient;
   }

   public void setPatient(Patient patient) {
      this.patient = patient;
   }

   public Boolean getFinished() {
      return finished;
   }

   public void setFinished(Boolean finished) {
      this.finished = finished;
   }

   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
      this.price = price;
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

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getReport() {
      return report;
   }

   public void setReport(String report) {
      this.report = report;
   }


   public Integer getDuration() {
      return duration;
   }

   public void setDuration(Integer duration) {
      this.duration = duration;
   }

   public Date getBeggingDateTime() {
      return beggingDateTime;
   }

   public void setBeggingDateTime(Date beggingDateTime) {
      this.beggingDateTime = beggingDateTime;
   }
}