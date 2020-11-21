package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  PriceList.java
 * Author:  User
 * Purpose: Defines the Class PriceList
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;

/** @pdOid 1b622050-5c42-4dfe-8ed0-3391a928322a */
@Entity
@Table(name = "price_list")
public class PriceList {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   /** @pdOid 5cd95d32-5567-47f2-a107-8bcd619dd44e */
   @Column(nullable = false, name = "start_date")
   private Date startDate;
   /** @pdOid b1b8b5b5-ced0-4e53-b3c5-4a11a5939702 */
   @Column(nullable = false, name = "end_date")
   private Date endDate;
   
   /** @pdRoleInfo migr=no name=Drug assc=association27 coll=java.util.Collection impl=java.util.HashSet mult=* */
   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(
           name = "pl_drug",
           joinColumns = @JoinColumn(name = "pl_id"),
           inverseJoinColumns = @JoinColumn(name = "drug_id")
   )
   public java.util.Collection<Drug> drug;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "pharmacy_id")
   private Pharmacy pharmacy;

   public PriceList() {
   }

   public Pharmacy getPharmacy() {
      return pharmacy;
   }

   public void setPharmacy(Pharmacy pharmacy) {
      this.pharmacy = pharmacy;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Date getStartDate() {
      return startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public Date getEndDate() {
      return endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   /** @pdGenerated default getter */
   public java.util.Collection<Drug> getDrug() {
      if (drug == null)
         drug = new java.util.HashSet<Drug>();
      return drug;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDrug() {
      if (drug == null)
         drug = new java.util.HashSet<Drug>();
      return drug.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDrug */
   public void setDrug(java.util.Collection<Drug> newDrug) {
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
         this.drug = new java.util.HashSet<Drug>();
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