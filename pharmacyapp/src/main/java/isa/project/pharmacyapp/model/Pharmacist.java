package isa.project.pharmacyapp.model;
/***********************************************************************
 * Module:  Pharmacist.java
 * Author:  User
 * Purpose: Defines the Class Pharmacist
 ***********************************************************************/

import java.sql.Timestamp;
import java.util.*;
import javax.persistence.*;


/** @pdOid f3ced588-eb04-41f5-93d0-b29a2c7718b4 */
@Entity
@Table(name = "pharmacist")
public class Pharmacist extends User {


   /** @pdOid ef54d13e-a883-42ec-b396-f28eebc9ba66 */
   @ElementCollection(fetch = FetchType.LAZY, targetClass = Double.class)
   private java.util.List<Double> ratings;

   /** @pdRoleInfo migr=no name=Appointment assc=association16 coll=java.util.List impl=java.util.ArrayList mult=1..* */
   @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   @JoinColumn
   public java.util.List<Appointment> appointment;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "pharmacy_id")
   public Pharmacy pharmacy;

   @Column(nullable = false, name = "start_hour")
   private Date start_hour;

   @Column(nullable = false, name = "hours")
   private Integer hours;

   /** @pdOid ae9862db-768d-4ac0-a45e-5fbda16edd5f */
   protected void finalize() {
      // TODO: implement
   }
   
   /** @pdOid b7374aa2-10a7-4e46-ae72-358159effaaf */
   public Pharmacist() {
      // TODO: implement
   }



   public List<Double> getRatings() {
      return ratings;
   }

   public void setRatings(List<Double> ratings) {
      this.ratings = ratings;
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

   public Pharmacy getPharmacy() {
      return pharmacy;
   }

   public void setPharmacy(Pharmacy pharmacy) {
      this.pharmacy = pharmacy;
   }

   public Date getStart_hour() {
      return start_hour;
   }

   public void setStart_hour(Date start_hour) {
      this.start_hour = start_hour;
   }

   public Integer getHours() {
      return hours;
   }

   public void setHours(Integer hours) {
      this.hours = hours;
   }
}