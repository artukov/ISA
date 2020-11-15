/***********************************************************************
 * Module:  SupplyOrder.java
 * Author:  User
 * Purpose: Defines the Class SupplyOrder
 ***********************************************************************/

import java.util.*;

/** @pdOid cd2c2f65-f35f-46a4-b587-c7a9716a8ed9 */
public class SupplyOrder {
   /** @pdOid 60cd11b0-d93a-4789-9eac-fc819e958700 */
   private Boolean status;
   /** @pdOid a470a81c-268d-456b-934f-6cdb8867caac */
   private Date deliveryDate;
   
   /** @pdRoleInfo migr=no name=Drug assc=association10 coll=java.util.Set impl=java.util.HashSet mult=1..* */
   public java.util.Set<Drug> drug;
   
   
   /** @pdGenerated default getter */
   public java.util.Set<Drug> getDrug() {
      if (drug == null)
         drug = new java.util.HashSet<Drug>();
      return drug;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDrug() {
      if (drug == null)
         drug = new java.util.HashSet<Drug>();
      return drug.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDrug */
   public void setDrug(java.util.Set<Drug> newDrug) {
      removeAllDrug();
      for (java.util.Iterator iter = newDrug.iterator(); iter.hasNext();)
         addDrug((Drug)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDrug */
   public void addDrug(Drug newDrug) {
      if (newDrug == null)
         return;
      if (this.drug == null)
         this.drug = new java.util.HashSet<Drug>();
      if (!this.drug.contains(newDrug))
         this.drug.add(newDrug);
   }
   
   /** @pdGenerated default remove
     * @param oldDrug */
   public void removeDrug(Drug oldDrug) {
      if (oldDrug == null)
         return;
      if (this.drug != null)
         if (this.drug.contains(oldDrug))
            this.drug.remove(oldDrug);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDrug() {
      if (drug != null)
         drug.clear();
   }

}