package isa.project.pharmacyapp.model;
/***********************************************************************
 * Module:  Pharmacy.java
 * Author:  User
 * Purpose: Defines the Class Pharmacy
 ***********************************************************************/

import isa.project.pharmacyapp.model.many2many.PharmacyDermatologist;
import isa.project.pharmacyapp.model.many2many.PharmacyDrug;

import java.util.*;
import javax.persistence.*;

/** @pdOid 0be80692-2b24-42fd-8ea3-a7ecf4a30110 */
@Entity
@Table(name = "pharmacy")
public class
Pharmacy {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   /** @pdOid d2895207-1d32-4cbf-bd48-9aad5d77fda2 */
   @Column(nullable = false)
   private String name;
   /** @pdOid 2cb9f03b-ec2b-4a12-9bfb-2e6a49cef2ec */
   @Column(nullable = false)
   private String description;
   /** @pdOid 29a4cc5d-959d-4ad7-a190-ba72ba5b1279 */
   @ElementCollection(fetch = FetchType.LAZY, targetClass = Double.class)
   private java.util.List<Double> ratings;

   /**********************************************************
    * OneToMany
    *
    ************************************************************/

//   /** @pdRoleInfo migr=no name=Pharmacist assc=association1 coll=java.util.List impl=java.util.ArrayList mult=1..* */
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//   @JoinColumn
//   public java.util.List<Pharmacist> pharmacist;
//
//   /** @pdRoleInfo migr=no name=PriceList assc=association5 coll=java.util.List impl=java.util.ArrayList mult=1..* */
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//   @JoinColumn
//   public java.util.List<PriceList> priceList;
//
//   /** @pdRoleInfo migr=no name=PharmacyAdmin assc=association7 coll=java.util.List impl=java.util.ArrayList mult=1..* */
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//   @JoinColumn
//   public java.util.List<PharmacyAdmin> pharmacyAdmin;
//
//   /** @pdRoleInfo migr=no name=AbsenceRequest assc=association19 coll=java.util.List impl=java.util.ArrayList mult=0..* */
//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//   @JoinColumn
//   public java.util.List<AbsenceRequest> absenceRequest;
//   /** @pdRoleInfo migr=no name=Promotions assc=association21 coll=java.util.List impl=java.util.ArrayList mult=0..* */
//   @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//   @JoinColumn
//   public java.util.List<Promotions> promotions;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "pharmacy_id")
   private List<PharmacyDermatologist> dermatologists;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "pharmacy_id")
   private List<PharmacyDrug> drugs;

   /**********************************************************
    * ManyToMany
    *
    ************************************************************/

   @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   @JoinTable(
           name="pharmacy_subscriber",
           joinColumns = @JoinColumn(name = "pharmacy_id", insertable = true,referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name="subscriber_id", insertable = true,referencedColumnName = "id")
   )
   private List<User> subscribers;

   /**********************************************************
    * OneToOne
    *
    ************************************************************/

   /** @pdRoleInfo migr=no name=Calendar assc=association8 mult=1..1 */
   @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
   @JoinColumn(name = "calendar_id")
   public Calendar calendar;
   /** @pdRoleInfo migr=no name=Address assc=association11 mult=1..1 */
   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "address_id")
   public Address address;



   public Pharmacy() {
   }


   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public List<Double> getRatings() {
      return ratings;
   }

   public void setRatings(List<Double> ratings) {
      this.ratings = ratings;
   }

   public Calendar getCalendar() {
      return calendar;
   }

   public void setCalendar(Calendar calendar) {
      this.calendar = calendar;
   }

   public Address getAddress() {
      return address;
   }

   public void setAddress(Address address) {
      this.address = address;
   }

   public List<PharmacyDermatologist> getDermatologists() {
      return dermatologists;
   }

   public void setDermatologists(List<PharmacyDermatologist> dermatologists) {
      this.dermatologists = dermatologists;
   }

   public List<PharmacyDrug> getDrugs() {
      return drugs;
   }

   public void setDrugs(List<PharmacyDrug> drugs) {
      this.drugs = drugs;
   }

   public List<User> getSubscribers() {
      return subscribers;
   }

   public void setSubscribers(List<User> subscribers) {
      this.subscribers = subscribers;
   }
}