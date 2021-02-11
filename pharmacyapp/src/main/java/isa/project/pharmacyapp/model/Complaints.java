package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  Complaints.java
 * Author:  User
 * Purpose: Defines the Class Complaints
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;
/** @pdOid 82549b7c-4dee-45b9-8752-62f8664d4b02 */
@Entity
@Table(name = "complaints")
public class Complaints {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "submitter_id")
   private User submitter;

   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "referred_id")
   private User referred;

   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "pharmacy_id")
   private Pharmacy pharmacy;

   @Column(nullable = false)
   private String description;

   @Column(nullable = true)
   private String answer;

   public Complaints() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public User getSubmitter() {
      return submitter;
   }

   public void setSubmitter(User submitter) {
      this.submitter = submitter;
   }

   public User getReferred() {
      return referred;
   }

   public void setReferred(User referred) {
      this.referred = referred;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getAnswer() {
      return answer;
   }

   public void setAnswer(String answer) {
      this.answer = answer;
   }

   public Pharmacy getPharmacy() {
      return pharmacy;
   }

   public void setPharmacy(Pharmacy pharmacy) {
      this.pharmacy = pharmacy;
   }
}