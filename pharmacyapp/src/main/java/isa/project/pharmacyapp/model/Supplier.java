package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  Supplier.java
 * Author:  User
 * Purpose: Defines the Class Supplier
 ***********************************************************************/

import java.util.*;

/** @pdOid 7ab3196e-5076-41fa-8c47-a1805f714c22 */
public class Supplier extends User {
   /** @pdRoleInfo migr=no name=Address assc=association23 mult=1..1 */
   public Address address;

}