/***********************************************************************
 * Module:  Promotions.java
 * Author:  User
 * Purpose: Defines the Class Promotions
 ***********************************************************************/

import java.util.*;

/** @pdOid baf6987a-bc04-49f5-ba4f-5653aa32b47d */
public class Promotions {
   /** @pdOid 1435405c-8bf9-49d5-b1fe-23c66c99bb26 */
   private Date startDate;
   /** @pdOid 15feddbb-8638-4867-b5eb-5335b4d8b326 */
   private Date endDate;
   
   /** @pdRoleInfo migr=no name=Pharmacy assc=association21 mult=1..1 side=A */
   public Pharmacy pharmacy;
   
   
   /** @pdGenerated default parent getter */
   public Pharmacy getPharmacy() {
      return pharmacy;
   }
   
   /** @pdGenerated default parent setter
     * @param newPharmacy */
   public void setPharmacy(Pharmacy newPharmacy) {
      if (this.pharmacy == null || !this.pharmacy.equals(newPharmacy))
      {
         if (this.pharmacy != null)
         {
            Pharmacy oldPharmacy = this.pharmacy;
            this.pharmacy = null;
            oldPharmacy.removePromotions(this);
         }
         if (newPharmacy != null)
         {
            this.pharmacy = newPharmacy;
            this.pharmacy.addPromotions(this);
         }
      }
   }

}