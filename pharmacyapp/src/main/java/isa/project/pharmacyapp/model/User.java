package isa.project.pharmacyapp.model;
/***********************************************************************
 * Module:  User.java
 * Author:  User
 * Purpose: Defines the Class User
 ***********************************************************************/

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.*;

/** @pdOid ec55a7e0-adf7-49f8-be3c-0032c8378e0d */

public class User {



   /** @pdOid 4ad3776b-e8c2-48d7-9f42-85a84b147179 */
   protected String email;
   /** @pdOid 9932bea1-50f2-4c08-add5-5de579952cd5 */
   protected String password;
   /** @pdOid 87fa4181-18d9-4ddc-b93d-46f84ab7d450 */
   protected String firstname;
   /** @pdOid 9e6173ac-8ad4-4930-abdd-926ffc45fbdf */
   protected String lastname;
   /** @pdOid 70fdc350-344d-4159-984b-a4f5e957ad56 */
   protected String address;
   /** @pdOid 5fd28c78-c43c-4a62-abcc-b0a63dcb55c8 */
   protected String city;
   /** @pdOid 3f66012c-ee9e-44fe-8f52-042b4216a800 */
   protected String country;
   /** @pdOid 41cf5bc2-d383-4bec-bec0-13aa7eafd22d */
   protected String phoneNumber;
   
   /** @pdOid 60abe2b8-4b64-47d7-977c-01e1657e9319 */
   protected void finalize() {
      // TODO: implement
   }
   
   /** @pdOid 51ba5d4d-89b2-414c-aa2a-690f8d7de3e2 */
   public User() {
      // TODO: implement
   }

}