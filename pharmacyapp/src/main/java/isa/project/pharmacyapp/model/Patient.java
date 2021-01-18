package isa.project.pharmacyapp.model;
/***********************************************************************
 * Module:  Patient.java
 * Author:  User
 * Purpose: Defines the Class Patient
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;

/** @pdOid 8fd28308-0982-428d-b3b0-4b7e59801216 */
@Entity
@Table(name = "patient")
public class Patient extends User {


   /** @pdOid 79dd33ff-4755-445d-b350-7ad428e6ccd4 */
   @Column(nullable = true)
   private Integer points = 0;
   /** @pdOid 90b59b78-6b39-457e-9480-03cba740dcc8 */
   @Column(nullable = true)
   private String category;
   /** @pdOid 166fec65-4553-4ceb-ba38-454d3592a0c9 */
   @Column(nullable = true)
   private Integer penalties = 0;
   
   /** @pdRoleInfo migr=no name=Address assc=association12 mult=1..1 */
   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "address_id")
   public Address address;
   /** @pdRoleInfo migr=no name=Reservation assc=association13 coll=java.util.List impl=java.util.ArrayList mult=0..* */
   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "patient_id")
   public java.util.List<Reservation> reservation;
   /** @pdRoleInfo migr=no name=Appointment assc=association15 coll=java.util.List impl=java.util.ArrayList mult=1..* */
   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "appointment_id")
   public java.util.List<Appointment> appointment;
   /** @pdRoleInfo migr=no name=Allergy assc=association20 coll=java.util.List impl=java.util.ArrayList mult=0..* */
   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "allergy_id")
   public java.util.List<Allergy> allergy;
   /** @pdRoleInfo migr=no name=Promotions assc=association26 coll=java.util.List impl=java.util.ArrayList mult=0..* */
   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "promotion_id")
   public java.util.List<Promotions> promotions;
   /** @pdRoleInfo migr=no name=Complaints assc=association28 coll=java.util.List impl=java.util.ArrayList mult=0..* */
   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "complaint_id")
   public java.util.List<Complaints> complaints;
   /** @pdRoleInfo migr=no name=EReceipt assc=association30 coll=java.util.List impl=java.util.ArrayList mult=1..* */
   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "receipt_id")
   public java.util.List<EReceipt> eReceipt;

   public Patient() {
   }

   public Integer getPoints() {
      return points;
   }

   public void setPoints(Integer points) {
      this.points = points;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public Integer getPenalties() {
      return penalties;
   }

   public void setPenalties(Integer penalties) {
      this.penalties = penalties;
   }

   @Override
   public Address getAddress() {
      return address;
   }

   @Override
   public void setAddress(Address address) {
      this.address = address;
   }

   /** @pdGenerated default getter */
   public java.util.List<Reservation> getReservation() {
      if (reservation == null)
         reservation = new java.util.ArrayList<Reservation>();
      return reservation;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorReservation() {
      if (reservation == null)
         reservation = new java.util.ArrayList<Reservation>();
      return reservation.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newReservation */
   public void setReservation(java.util.List<Reservation> newReservation) {
      removeAllReservation();
      for (java.util.Iterator iter = newReservation.iterator(); iter.hasNext();)
         addReservation((Reservation)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newReservation */
   public void addReservation(Reservation newReservation) {
      if (newReservation == null)
         return;
      if (this.reservation == null)
         this.reservation = new java.util.ArrayList<Reservation>();
      if (!this.reservation.contains(newReservation))
         this.reservation.add(newReservation);
   }
   
   /** @pdGenerated default remove
     * @param oldReservation */
   public void removeReservation(Reservation oldReservation) {
      if (oldReservation == null)
         return;
      if (this.reservation != null)
         if (this.reservation.contains(oldReservation))
            this.reservation.remove(oldReservation);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllReservation() {
      if (reservation != null)
         reservation.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<Appointment> getAppointment() {
      if (appointment == null)
         appointment = new java.util.ArrayList<Appointment>();
      return appointment;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAppointment() {
      if (appointment == null)
         appointment = new java.util.ArrayList<Appointment>();
      return appointment.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAppointment */
   public void setAppointment(java.util.List<Appointment> newAppointment) {
      removeAllAppointment();
      for (java.util.Iterator iter = newAppointment.iterator(); iter.hasNext();)
         addAppointment((Appointment)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newAppointment */
   public void addAppointment(Appointment newAppointment) {
      if (newAppointment == null)
         return;
      if (this.appointment == null)
         this.appointment = new java.util.ArrayList<Appointment>();
      if (!this.appointment.contains(newAppointment))
         this.appointment.add(newAppointment);
   }
   
   /** @pdGenerated default remove
     * @param oldAppointment */
   public void removeAppointment(Appointment oldAppointment) {
      if (oldAppointment == null)
         return;
      if (this.appointment != null)
         if (this.appointment.contains(oldAppointment))
            this.appointment.remove(oldAppointment);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAppointment() {
      if (appointment != null)
         appointment.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<Allergy> getAllergy() {
      if (allergy == null)
         allergy = new java.util.ArrayList<Allergy>();
      return allergy;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAllergy() {
      if (allergy == null)
         allergy = new java.util.ArrayList<Allergy>();
      return allergy.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAllergy */
   public void setAllergy(java.util.List<Allergy> newAllergy) {
      removeAllAllergy();
      for (java.util.Iterator iter = newAllergy.iterator(); iter.hasNext();)
         addAllergy((Allergy)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newAllergy */
   public void addAllergy(Allergy newAllergy) {
      if (newAllergy == null)
         return;
      if (this.allergy == null)
         this.allergy = new java.util.ArrayList<Allergy>();
      if (!this.allergy.contains(newAllergy))
         this.allergy.add(newAllergy);
   }
   
   /** @pdGenerated default remove
     * @param oldAllergy */
   public void removeAllergy(Allergy oldAllergy) {
      if (oldAllergy == null)
         return;
      if (this.allergy != null)
         if (this.allergy.contains(oldAllergy))
            this.allergy.remove(oldAllergy);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAllergy() {
      if (allergy != null)
         allergy.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<Promotions> getPromotions() {
      if (promotions == null)
         promotions = new java.util.ArrayList<Promotions>();
      return promotions;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPromotions() {
      if (promotions == null)
         promotions = new java.util.ArrayList<Promotions>();
      return promotions.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPromotions */
   public void setPromotions(java.util.List<Promotions> newPromotions) {
      removeAllPromotions();
      for (java.util.Iterator iter = newPromotions.iterator(); iter.hasNext();)
         addPromotions((Promotions)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPromotions */
   public void addPromotions(Promotions newPromotions) {
      if (newPromotions == null)
         return;
      if (this.promotions == null)
         this.promotions = new java.util.ArrayList<Promotions>();
      if (!this.promotions.contains(newPromotions))
         this.promotions.add(newPromotions);
   }
   
   /** @pdGenerated default remove
     * @param oldPromotions */
   public void removePromotions(Promotions oldPromotions) {
      if (oldPromotions == null)
         return;
      if (this.promotions != null)
         if (this.promotions.contains(oldPromotions))
            this.promotions.remove(oldPromotions);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPromotions() {
      if (promotions != null)
         promotions.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<Complaints> getComplaints() {
      if (complaints == null)
         complaints = new java.util.ArrayList<Complaints>();
      return complaints;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorComplaints() {
      if (complaints == null)
         complaints = new java.util.ArrayList<Complaints>();
      return complaints.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newComplaints */
   public void setComplaints(java.util.List<Complaints> newComplaints) {
      removeAllComplaints();
      for (java.util.Iterator iter = newComplaints.iterator(); iter.hasNext();)
         addComplaints((Complaints)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newComplaints */
   public void addComplaints(Complaints newComplaints) {
      if (newComplaints == null)
         return;
      if (this.complaints == null)
         this.complaints = new java.util.ArrayList<Complaints>();
      if (!this.complaints.contains(newComplaints))
         this.complaints.add(newComplaints);
   }
   
   /** @pdGenerated default remove
     * @param oldComplaints */
   public void removeComplaints(Complaints oldComplaints) {
      if (oldComplaints == null)
         return;
      if (this.complaints != null)
         if (this.complaints.contains(oldComplaints))
            this.complaints.remove(oldComplaints);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllComplaints() {
      if (complaints != null)
         complaints.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<EReceipt> getEReceipt() {
      if (eReceipt == null)
         eReceipt = new java.util.ArrayList<EReceipt>();
      return eReceipt;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorEReceipt() {
      if (eReceipt == null)
         eReceipt = new java.util.ArrayList<EReceipt>();
      return eReceipt.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newEReceipt */
   public void setEReceipt(java.util.List<EReceipt> newEReceipt) {
      removeAllEReceipt();
      for (java.util.Iterator iter = newEReceipt.iterator(); iter.hasNext();)
         addEReceipt((EReceipt)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newEReceipt */
   public void addEReceipt(EReceipt newEReceipt) {
      if (newEReceipt == null)
         return;
      if (this.eReceipt == null)
         this.eReceipt = new java.util.ArrayList<EReceipt>();
      if (!this.eReceipt.contains(newEReceipt))
      {
         this.eReceipt.add(newEReceipt);
         newEReceipt.setPatient(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldEReceipt */
   public void removeEReceipt(EReceipt oldEReceipt) {
      if (oldEReceipt == null)
         return;
      if (this.eReceipt != null)
         if (this.eReceipt.contains(oldEReceipt))
         {
            this.eReceipt.remove(oldEReceipt);
            oldEReceipt.setPatient((Patient)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllEReceipt() {
      if (eReceipt != null)
      {
         EReceipt oldEReceipt;
         for (java.util.Iterator iter = getIteratorEReceipt(); iter.hasNext();)
         {
            oldEReceipt = (EReceipt)iter.next();
            iter.remove();
            oldEReceipt.setPatient((Patient)null);
         }
      }
   }

}