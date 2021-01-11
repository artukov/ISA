package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  PharmacyAdmin.java
 * Author:  User
 * Purpose: Defines the Class PharmacyAdmin
 ***********************************************************************/

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;
import javax.persistence.*;

/** @pdOid 1e797a6e-1615-4b6b-b153-191972d89c03 */
@Entity
@Table(name = "pharmacy_admin")
public class PharmacyAdmin extends User {



   /** @pdRoleInfo migr=no name=Pharmacy assc=association7 mult=1..1 side=A */
   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "pharmacy_id")
   public Pharmacy pharmacy;


   public PharmacyAdmin() {
   }


   /** @pdGenerated default parent getter */
   public Pharmacy getPharmacy() {
      return pharmacy;
   }

   public void setPharmacy(Pharmacy pharmacy) {
      this.pharmacy = pharmacy;
   }

   //   /** @pdGenerated default parent setter
//     * @param newPharmacy */
//   public void setPharmacy(Pharmacy newPharmacy) {
//      if (this.pharmacy == null || !this.pharmacy.equals(newPharmacy))
//      {
//         if (this.pharmacy != null)
//         {
//            Pharmacy oldPharmacy = this.pharmacy;
//            this.pharmacy = null;
//            oldPharmacy.removePharmacyAdmin(this);
//         }
//         if (newPharmacy != null)
//         {
//            this.pharmacy = newPharmacy;
//            this.pharmacy.addPharmacyAdmin(this);
//         }
//      }
//   }

}