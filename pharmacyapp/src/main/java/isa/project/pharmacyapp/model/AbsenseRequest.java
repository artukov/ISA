package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  AbsenseRequest.java
 * Author:  User
 * Purpose: Defines the Class AbsenseRequest
 ***********************************************************************/

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import java.util.*;

/** @pdOid 19dc7e07-fef3-42c9-948a-926fbb86535f */
@Entity
@Table(name = "absense_request")
public class AbsenseRequest {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;


   /** @pdOid 2fdc36ba-4b80-4943-a2d3-9b999631d96b */
   @Column(nullable = false, name = "start_date")
   private Date startDate;
   /** @pdOid e23af7d0-b39f-4102-8191-03705bfda89a */
   @Column(nullable = false, name = "end_date")
   private Date endDate;
   /** @pdOid 87222d38-f72c-47e0-b360-4b21d1def4be */
   @Column(nullable = true, name = "status")
   private Boolean status;
   /** @pdOid 41ea2e9e-28b0-4c39-a4eb-eae464cc0e4c */
   @Column(nullable = true)
   private String description = null;
   
   /** @pdRoleInfo migr=no name=User assc=association18 mult=1..1 */
   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "user_id")
   public User user;

   @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   @JoinColumn(name = "pharmacy_id")
   public Pharmacy pharmacy;

   public AbsenseRequest() {
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

   public Boolean getStatus() {
      return status;
   }

   public void setStatus(Boolean status) {
      this.status = status;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }
}