package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  Examination.java
 * Author:  User
 * Purpose: Defines the Class Examination
 ***********************************************************************/

import java.util.*;

import javax.persistence.*;

/** @pdOid 964cb615-f3e1-4147-9add-187e036219ce */
@Entity
@Table(name = "examination")
public class Examination extends Appointment {

//   @Id
//   @GeneratedValue(strategy = GenerationType.AUTO)
//   private Long id;

   /** @pdOid 6e4a99ff-5418-4c20-b5c0-2089f3848932 */
   @Column(nullable = true)
   private Double price;
   /** @pdOid 3e0ec50d-3567-4c56-a345-5676b9968aa4 */
   @Column(nullable = true)
   private String diagnose;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "derma_id", referencedColumnName = "id")
   private Dermatologist dermatologist;

   public Examination() {
   }

   @Override
   public void patientAttendance(Boolean attendance) {

   }

   @Override
   public Long getId() {
      return id;
   }

   @Override
   public void setId(Long id) {
      this.id = id;
   }

   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   public String getDiagnose() {
      return diagnose;
   }

   public void setDiagnose(String diagnose) {
      this.diagnose = diagnose;
   }

   public Dermatologist getDermatologist() {
      return dermatologist;
   }

   public void setDermatologist(Dermatologist dermatologist) {
      this.dermatologist = dermatologist;
   }
}