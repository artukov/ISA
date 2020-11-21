
package isa.project.pharmacyapp.model;
/***********************************************************************

 * Module:  User.java
 * Author:  User
 * Purpose: Defines the Class User
 ***********************************************************************/

import javax.persistence.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.*;

/** @pdOid ec55a7e0-adf7-49f8-be3c-0032c8378e0d */
@Entity
@Table(name = "user_table")
@Inheritance(strategy = InheritanceType.JOINED)
//@MappedSuperclass
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   protected Long id;


   /** @pdOid 4ad3776b-e8c2-48d7-9f42-85a84b147179 */
   @Column(nullable = false, unique = true, name = "email")
   protected String email;
   /** @pdOid 9932bea1-50f2-4c08-add5-5de579952cd5 */
   @Column(nullable = false, unique = false)
   protected String password;
   /** @pdOid 87fa4181-18d9-4ddc-b93d-46f84ab7d450 */
   @Column(nullable = false)
   protected String firstname;
   /** @pdOid 9e6173ac-8ad4-4930-abdd-926ffc45fbdf */
   @Column(nullable = false)
   protected String lastname;
   /** @pdOid 70fdc350-344d-4159-984b-a4f5e957ad56 */
   @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "address_id")
   protected Address address;
   /** @pdOid 41cf5bc2-d383-4bec-bec0-13aa7eafd22d */
   @Column(nullable = true, name = "phone_number")
   protected String phoneNumber;
   
   /** @pdOid 60abe2b8-4b64-47d7-977c-01e1657e9319 */
   protected void finalize() {
      // TODO: implement
   }
   
   /** @pdOid 51ba5d4d-89b2-414c-aa2a-690f8d7de3e2 */
   public User() {
      // TODO: implement
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getFirstname() {
      return firstname;
   }

   public void setFirstname(String firstname) {
      this.firstname = firstname;
   }

   public String getLastname() {
      return lastname;
   }

   public void setLastname(String lastname) {
      this.lastname = lastname;
   }

   public Address getAddress() {
      return address;
   }

   public void setAddress(Address address) {
      this.address = address;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }
}