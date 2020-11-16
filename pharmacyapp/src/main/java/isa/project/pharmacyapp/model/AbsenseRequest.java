package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  AbsenseRequest.java
 * Author:  User
 * Purpose: Defines the Class AbsenseRequest
 ***********************************************************************/

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import java.util.*;

/** @pdOid 19dc7e07-fef3-42c9-948a-926fbb86535f */

public class AbsenseRequest {


   /** @pdOid 2fdc36ba-4b80-4943-a2d3-9b999631d96b */
   private Date startDate;
   /** @pdOid e23af7d0-b39f-4102-8191-03705bfda89a */
   private Date endDate;
   /** @pdOid 87222d38-f72c-47e0-b360-4b21d1def4be */
   private Boolean status;
   /** @pdOid 41ea2e9e-28b0-4c39-a4eb-eae464cc0e4c */
   private String description = null;
   
   /** @pdRoleInfo migr=no name=User assc=association18 mult=1..1 */
   @OneToOne(cascade = CascadeType.ALL)
   public User user;

}