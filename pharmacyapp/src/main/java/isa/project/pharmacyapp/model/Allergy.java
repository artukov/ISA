package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  Allergy.java
 * Author:  User
 * Purpose: Defines the Class Allergy
 ***********************************************************************/

import java.util.*;

/** @pdOid 50e6998e-0fc6-4991-a016-d20d8a382048 */
public class Allergy {
   /** @pdRoleInfo migr=no name=Drug assc=association25 coll=java.util.Set impl=java.util.HashSet mult=1..* */
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