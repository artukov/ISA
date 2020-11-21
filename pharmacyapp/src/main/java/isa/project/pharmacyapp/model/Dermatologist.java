package isa.project.pharmacyapp.model;
/***********************************************************************
 * Module:  Dermatologist.java
 * Author:  User
 * Purpose: Defines the Class Dermatologist
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;

/** @pdOid be8b1403-84b5-421b-a720-0a290a7fa17a */
@Entity
@Table(name = "dermatologist")
public class Dermatologist extends User {



   /** @pdOid 66a45f1c-c8b7-4690-ad23-6a73f3abec25 */

   //private java.util.Map<Long,java.util.List<Double>> ratings;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "derma_ratings_id")
   private List<DermatologistRatings> dermatologistRatings;
   
   /** @pdRoleInfo migr=no name=Appointment assc=association17 coll=java.util.List impl=java.util.ArrayList mult=1..* */
   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "appointment_id")
   protected java.util.List<Appointment> appointment;
   
   /** @pdOid 78e678c6-0907-4cd1-83c9-3df27513740e */
   protected void finalize() {
      // TODO: implement
   }


   public List<DermatologistRatings> getDermatologistRatings() {
      return dermatologistRatings;
   }

   public void setDermatologistRatings(List<DermatologistRatings> dermatologistRatings) {
      this.dermatologistRatings = dermatologistRatings;
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
   
   /** @pdOid 602a41a1-8fe0-42e0-9654-838a2dc2d785 */
   public Dermatologist() {
      // TODO: implement
   }

}