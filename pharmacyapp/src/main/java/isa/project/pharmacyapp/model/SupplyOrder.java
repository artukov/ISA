package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  SupplyOrder.java
 * Author:  User
 * Purpose: Defines the Class SupplyOrder
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;

/** @pdOid cd2c2f65-f35f-46a4-b587-c7a9716a8ed9 */
@Entity
@Table(name = "supply_order")
public class SupplyOrder {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   /** @pdOid 60cd11b0-d93a-4789-9eac-fc819e958700 */
   @Column(nullable = true)
   private Boolean status;
   /** @pdOid a470a81c-268d-456b-934f-6cdb8867caac */
   @Column(nullable = false, name = "delivery_date")
   private Date deliveryDate;
   
   /** @pdRoleInfo migr=no name=Drug assc=association10 coll=java.util.Set impl=java.util.HashSet mult=1..* */
   @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinTable(
           name = "order_drug",
           joinColumns = @JoinColumn(name = "order_id"),
           inverseJoinColumns = @JoinColumn(name = "drug_id")
   )
   public java.util.Set<Drug> drug;

   public SupplyOrder() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Boolean getStatus() {
      return status;
   }

   public void setStatus(Boolean status) {
      this.status = status;
   }

   public Date getDeliveryDate() {
      return deliveryDate;
   }

   public void setDeliveryDate(Date deliveryDate) {
      this.deliveryDate = deliveryDate;
   }

   /** @pdGenerated default getter */
   public java.util.Set<Drug> getDrug() {
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
   public void setDrug(java.util.Set<Drug> newDrug) {
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