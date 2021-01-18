package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  Promotions.java
 * Author:  User
 * Purpose: Defines the Class Promotions
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;

/** @pdOid baf6987a-bc04-49f5-ba4f-5653aa32b47d */
@Entity
@Table(name = "promotions")
public class Promotions {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   /** @pdOid 1435405c-8bf9-49d5-b1fe-23c66c99bb26 */
   @Column(nullable = false, name = "start_date")
   private Date startDate;
   /** @pdOid 15feddbb-8638-4867-b5eb-5335b4d8b326 */
   @Column(nullable = false, name = "end_date")
   private Date endDate;
   
   /** @pdRoleInfo migr=no name=Pharmacy assc=association21 mult=1..1 side=A */
   @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
   @JoinColumn(name = "pharmacy_id")
   private Pharmacy pharmacy;

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

   public Promotions() {
   }

   /** @pdGenerated default parent getter */
   public Pharmacy getPharmacy() {
      return pharmacy;
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
//            oldPharmacy.removePromotions(this);
//         }
//         if (newPharmacy != null)
//         {
//            this.pharmacy = newPharmacy;
//            this.pharmacy.addPromotions(this);
//         }
//      }
//   }

}