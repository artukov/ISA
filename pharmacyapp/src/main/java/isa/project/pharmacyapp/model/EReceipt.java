package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  EReceipt.java
 * Author:  User
 * Purpose: Defines the Class EReceipt
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;
/** @pdOid 6d965712-ddc8-4ec3-9e5a-604bb4d19211 */
@Entity
@Table(name = "erecepit")
public class EReceipt {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   /** @pdOid cbb94b01-2793-42f0-ad1b-979645b9f450 */
   @Column(nullable = false, unique = true)
   private Long code;
   /** @pdOid a95fcce8-8d85-49ca-b8df-43463b648ca3 */
   @Column(nullable = false, name = "issue_date")
   private Date issueDate;
   
   /** @pdRoleInfo migr=no name=Drug assc=association31 coll=java.util.List impl=java.util.ArrayList mult=1..* */
   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(
           name = "recepit_drug",
           joinColumns = @JoinColumn(name = "recepit_id"),
           inverseJoinColumns = @JoinColumn(name = "drug_id")
   )
   public java.util.List<Drug> drug;
   /** @pdRoleInfo migr=no name=Patient assc=association30 mult=1..1 side=A */

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "patient_id")
   public Patient patient;
   
   
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
   /** @pdGenerated default parent getter */
   public Patient getPatient() {
      return patient;
   }
   
   /** @pdGenerated default parent setter
     * @param newPatient */
   public void setPatient(Patient newPatient) {
      if (this.patient == null || !this.patient.equals(newPatient))
      {
         if (this.patient != null)
         {
            Patient oldPatient = this.patient;
            this.patient = null;
            oldPatient.removeEReceipt(this);
         }
         if (newPatient != null)
         {
            this.patient = newPatient;
            this.patient.addEReceipt(this);
         }
      }
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getCode() {
      return code;
   }

   public void setCode(Long code) {
      this.code = code;
   }

   public Date getIssueDate() {
      return issueDate;
   }

   public void setIssueDate(Date issueDate) {
      this.issueDate = issueDate;
   }
}