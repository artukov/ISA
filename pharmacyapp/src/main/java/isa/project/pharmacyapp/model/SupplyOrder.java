package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  SupplyOrder.java
 * Author:  User
 * Purpose: Defines the Class SupplyOrder
 ***********************************************************************/

import isa.project.pharmacyapp.model.many2many.SupplierOrder;
import isa.project.pharmacyapp.model.many2many.SupplyOrderDrug;

import java.util.*;
import javax.persistence.*;

/** @pdOid cd2c2f65-f35f-46a4-b587-c7a9716a8ed9 */
@Entity
@Table(name = "supply_order")
public class SupplyOrder {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   /**
    * @pdOid 60cd11b0-d93a-4789-9eac-fc819e958700
    */
   @Column(nullable = true)
   private OrderStatus status;
   /**
    * @pdOid a470a81c-268d-456b-934f-6cdb8867caac
    */
   @Column(nullable = false, name = "deadline_date")
   private Date deadlineDate;

//   @Column(nullable = false, name = "delivery_date")
//   private Date deliveryDate;
//
//   @Column(nullable = true, name = "offer")
//   private Double priceOffer;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "supply_id")
   private List<SupplyOrderDrug> drugs;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "admin_id", referencedColumnName = "id")
   private PharmacyAdmin pharmacyAdmin;

//   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//   @JoinColumn(name = "supplier_id")
//   private Supplier supplier;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "order_id", referencedColumnName = "id")
   private List<SupplierOrder> suppliers;


   public SupplyOrder() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public OrderStatus getStatus() {
      return status;
   }

   public void setStatus(OrderStatus status) {
      this.status = status;
   }

   public Date getDeadlineDate() {
      return deadlineDate;
   }

   public void setDeadlineDate(Date deadlineDate) {
      this.deadlineDate = deadlineDate;
   }

   public List<SupplyOrderDrug> getDrugs() {
      return drugs;
   }

   public void setDrugs(List<SupplyOrderDrug> drugs) {
      this.drugs = drugs;
   }

   public PharmacyAdmin getPharmacyAdmin() {
      return pharmacyAdmin;
   }

   public void setPharmacyAdmin(PharmacyAdmin pharmacyAdmin) {
      this.pharmacyAdmin = pharmacyAdmin;
   }

   public List<SupplierOrder> getSuppliers() {
      return suppliers;
   }

   public void setSuppliers(List<SupplierOrder> suppliers) {
      this.suppliers = suppliers;
   }
}