package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  PriceList.java
 * Author:  User
 * Purpose: Defines the Class PriceList
 ***********************************************************************/

import isa.project.pharmacyapp.model.many2many.PriceListDrug;

import java.util.*;
import javax.persistence.*;

/** @pdOid 1b622050-5c42-4dfe-8ed0-3391a928322a */
@Entity
@Table(name = "price_list")
public class PriceList {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;


   @Column(nullable = false, name = "start_date")
   private Date startDate;

   @Column(nullable = false, name = "end_date")
   private Date endDate;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "pricelist_id",referencedColumnName = "id")
   private List<PriceListDrug> drugs;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "pharmacy_id")
   private Pharmacy pharmacy;

   @Column(nullable = false, name = "active")
   private Boolean active;

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

   public List<PriceListDrug> getDrugs() {
      return drugs;
   }

   public void setDrugs(List<PriceListDrug> drugs) {
      this.drugs = drugs;
   }

   public Boolean getActive() {
      return active;
   }

   public void setActive(Boolean active) {
      this.active = active;
   }
}