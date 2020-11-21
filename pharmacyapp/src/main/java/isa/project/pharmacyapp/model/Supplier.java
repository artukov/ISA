package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  Supplier.java
 * Author:  User
 * Purpose: Defines the Class Supplier
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;


/** @pdOid 7ab3196e-5076-41fa-8c47-a1805f714c22 */
@Entity
@Table(name = "supplier")
public class Supplier extends User {


   /** @pdRoleInfo migr=no name=Address assc=association23 mult=1..1 */
   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "address")
   public Address address;

   public Supplier() {
   }

   @Override
   public Address getAddress() {
      return address;
   }

   @Override
   public void setAddress(Address address) {
      this.address = address;
   }
}