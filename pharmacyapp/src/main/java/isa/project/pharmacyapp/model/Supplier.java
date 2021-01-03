package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  Supplier.java
 * Author:  User
 * Purpose: Defines the Class Supplier
 ***********************************************************************/

import isa.project.pharmacyapp.model.many2many.SupplierOrder;

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

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "supplier_id", referencedColumnName = "id")
   private List<SupplierOrder> orders;

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

   public List<SupplierOrder> getOrders() {
      return orders;
   }

   public void setOrders(List<SupplierOrder> orders) {
      this.orders = orders;
   }
}