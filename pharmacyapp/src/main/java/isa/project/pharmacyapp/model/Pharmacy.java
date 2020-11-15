/***********************************************************************
 * Module:  Pharmacy.java
 * Author:  User
 * Purpose: Defines the Class Pharmacy
 ***********************************************************************/

import java.util.*;

/** @pdOid 0be80692-2b24-42fd-8ea3-a7ecf4a30110 */
public class Pharmacy {
   /** @pdOid d2895207-1d32-4cbf-bd48-9aad5d77fda2 */
   private String name;
   /** @pdOid 2cb9f03b-ec2b-4a12-9bfb-2e6a49cef2ec */
   private String description;
   /** @pdOid 29a4cc5d-959d-4ad7-a190-ba72ba5b1279 */
   private java.util.List<Double> ratings;
   
   /** @pdRoleInfo migr=no name=Pharmacist assc=association1 coll=java.util.List impl=java.util.ArrayList mult=1..* */
   public java.util.List<Pharmacist> pharmacist;
   /** @pdRoleInfo migr=no name=Dermatologist assc=association2 coll=java.util.List impl=java.util.ArrayList mult=0..* */
   public java.util.List<Dermatologist> dermatologist;
   /** @pdRoleInfo migr=no name=Drug assc=association4 coll=java.util.List impl=java.util.ArrayList mult=1..* */
   public java.util.List<Drug> drug;
   /** @pdRoleInfo migr=no name=PriceList assc=association5 coll=java.util.List impl=java.util.ArrayList mult=1..* */
   public java.util.List<PriceList> priceList;
   /** @pdRoleInfo migr=no name=PharmacyAdmin assc=association7 coll=java.util.List impl=java.util.ArrayList mult=1..* */
   public java.util.List<PharmacyAdmin> pharmacyAdmin;
   /** @pdRoleInfo migr=no name=Calendar assc=association8 mult=1..1 */
   public Calendar calendar;
   /** @pdRoleInfo migr=no name=Address assc=association11 mult=1..1 */
   public Address address;
   /** @pdRoleInfo migr=no name=AbsenseRequest assc=association19 coll=java.util.List impl=java.util.ArrayList mult=0..* */
   public java.util.List<AbsenseRequest> absenseRequest;
   /** @pdRoleInfo migr=no name=Promotions assc=association21 coll=java.util.List impl=java.util.ArrayList mult=0..* */
   public java.util.List<Promotions> promotions;
   
   
   /** @pdGenerated default getter */
   public java.util.List<Pharmacist> getPharmacist() {
      if (pharmacist == null)
         pharmacist = new java.util.ArrayList<Pharmacist>();
      return pharmacist;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPharmacist() {
      if (pharmacist == null)
         pharmacist = new java.util.ArrayList<Pharmacist>();
      return pharmacist.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPharmacist */
   public void setPharmacist(java.util.List<Pharmacist> newPharmacist) {
      removeAllPharmacist();
      for (java.util.Iterator iter = newPharmacist.iterator(); iter.hasNext();)
         addPharmacist((Pharmacist)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPharmacist */
   public void addPharmacist(Pharmacist newPharmacist) {
      if (newPharmacist == null)
         return;
      if (this.pharmacist == null)
         this.pharmacist = new java.util.ArrayList<Pharmacist>();
      if (!this.pharmacist.contains(newPharmacist))
         this.pharmacist.add(newPharmacist);
   }
   
   /** @pdGenerated default remove
     * @param oldPharmacist */
   public void removePharmacist(Pharmacist oldPharmacist) {
      if (oldPharmacist == null)
         return;
      if (this.pharmacist != null)
         if (this.pharmacist.contains(oldPharmacist))
            this.pharmacist.remove(oldPharmacist);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPharmacist() {
      if (pharmacist != null)
         pharmacist.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<Dermatologist> getDermatologist() {
      if (dermatologist == null)
         dermatologist = new java.util.ArrayList<Dermatologist>();
      return dermatologist;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDermatologist() {
      if (dermatologist == null)
         dermatologist = new java.util.ArrayList<Dermatologist>();
      return dermatologist.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDermatologist */
   public void setDermatologist(java.util.List<Dermatologist> newDermatologist) {
      removeAllDermatologist();
      for (java.util.Iterator iter = newDermatologist.iterator(); iter.hasNext();)
         addDermatologist((Dermatologist)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDermatologist */
   public void addDermatologist(Dermatologist newDermatologist) {
      if (newDermatologist == null)
         return;
      if (this.dermatologist == null)
         this.dermatologist = new java.util.ArrayList<Dermatologist>();
      if (!this.dermatologist.contains(newDermatologist))
         this.dermatologist.add(newDermatologist);
   }
   
   /** @pdGenerated default remove
     * @param oldDermatologist */
   public void removeDermatologist(Dermatologist oldDermatologist) {
      if (oldDermatologist == null)
         return;
      if (this.dermatologist != null)
         if (this.dermatologist.contains(oldDermatologist))
            this.dermatologist.remove(oldDermatologist);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDermatologist() {
      if (dermatologist != null)
         dermatologist.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<Drug> getDrug() {
      if (drug == null)
         drug = new java.util.ArrayList<Drug>();
      return drug;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDrug() {
      if (drug == null)
         drug = new java.util.ArrayList<Drug>();
      return drug.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDrug */
   public void setDrug(java.util.List<Drug> newDrug) {
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
         this.drug = new java.util.ArrayList<Drug>();
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
   /** @pdGenerated default getter */
   public java.util.List<PriceList> getPriceList() {
      if (priceList == null)
         priceList = new java.util.ArrayList<PriceList>();
      return priceList;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPriceList() {
      if (priceList == null)
         priceList = new java.util.ArrayList<PriceList>();
      return priceList.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPriceList */
   public void setPriceList(java.util.List<PriceList> newPriceList) {
      removeAllPriceList();
      for (java.util.Iterator iter = newPriceList.iterator(); iter.hasNext();)
         addPriceList((PriceList)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPriceList */
   public void addPriceList(PriceList newPriceList) {
      if (newPriceList == null)
         return;
      if (this.priceList == null)
         this.priceList = new java.util.ArrayList<PriceList>();
      if (!this.priceList.contains(newPriceList))
         this.priceList.add(newPriceList);
   }
   
   /** @pdGenerated default remove
     * @param oldPriceList */
   public void removePriceList(PriceList oldPriceList) {
      if (oldPriceList == null)
         return;
      if (this.priceList != null)
         if (this.priceList.contains(oldPriceList))
            this.priceList.remove(oldPriceList);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPriceList() {
      if (priceList != null)
         priceList.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<PharmacyAdmin> getPharmacyAdmin() {
      if (pharmacyAdmin == null)
         pharmacyAdmin = new java.util.ArrayList<PharmacyAdmin>();
      return pharmacyAdmin;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPharmacyAdmin() {
      if (pharmacyAdmin == null)
         pharmacyAdmin = new java.util.ArrayList<PharmacyAdmin>();
      return pharmacyAdmin.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPharmacyAdmin */
   public void setPharmacyAdmin(java.util.List<PharmacyAdmin> newPharmacyAdmin) {
      removeAllPharmacyAdmin();
      for (java.util.Iterator iter = newPharmacyAdmin.iterator(); iter.hasNext();)
         addPharmacyAdmin((PharmacyAdmin)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPharmacyAdmin */
   public void addPharmacyAdmin(PharmacyAdmin newPharmacyAdmin) {
      if (newPharmacyAdmin == null)
         return;
      if (this.pharmacyAdmin == null)
         this.pharmacyAdmin = new java.util.ArrayList<PharmacyAdmin>();
      if (!this.pharmacyAdmin.contains(newPharmacyAdmin))
      {
         this.pharmacyAdmin.add(newPharmacyAdmin);
         newPharmacyAdmin.setPharmacy(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldPharmacyAdmin */
   public void removePharmacyAdmin(PharmacyAdmin oldPharmacyAdmin) {
      if (oldPharmacyAdmin == null)
         return;
      if (this.pharmacyAdmin != null)
         if (this.pharmacyAdmin.contains(oldPharmacyAdmin))
         {
            this.pharmacyAdmin.remove(oldPharmacyAdmin);
            oldPharmacyAdmin.setPharmacy((Pharmacy)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPharmacyAdmin() {
      if (pharmacyAdmin != null)
      {
         PharmacyAdmin oldPharmacyAdmin;
         for (java.util.Iterator iter = getIteratorPharmacyAdmin(); iter.hasNext();)
         {
            oldPharmacyAdmin = (PharmacyAdmin)iter.next();
            iter.remove();
            oldPharmacyAdmin.setPharmacy((Pharmacy)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.List<AbsenseRequest> getAbsenseRequest() {
      if (absenseRequest == null)
         absenseRequest = new java.util.ArrayList<AbsenseRequest>();
      return absenseRequest;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAbsenseRequest() {
      if (absenseRequest == null)
         absenseRequest = new java.util.ArrayList<AbsenseRequest>();
      return absenseRequest.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAbsenseRequest */
   public void setAbsenseRequest(java.util.List<AbsenseRequest> newAbsenseRequest) {
      removeAllAbsenseRequest();
      for (java.util.Iterator iter = newAbsenseRequest.iterator(); iter.hasNext();)
         addAbsenseRequest((AbsenseRequest)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newAbsenseRequest */
   public void addAbsenseRequest(AbsenseRequest newAbsenseRequest) {
      if (newAbsenseRequest == null)
         return;
      if (this.absenseRequest == null)
         this.absenseRequest = new java.util.ArrayList<AbsenseRequest>();
      if (!this.absenseRequest.contains(newAbsenseRequest))
         this.absenseRequest.add(newAbsenseRequest);
   }
   
   /** @pdGenerated default remove
     * @param oldAbsenseRequest */
   public void removeAbsenseRequest(AbsenseRequest oldAbsenseRequest) {
      if (oldAbsenseRequest == null)
         return;
      if (this.absenseRequest != null)
         if (this.absenseRequest.contains(oldAbsenseRequest))
            this.absenseRequest.remove(oldAbsenseRequest);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAbsenseRequest() {
      if (absenseRequest != null)
         absenseRequest.clear();
   }
   /** @pdGenerated default getter */
   public java.util.List<Promotions> getPromotions() {
      if (promotions == null)
         promotions = new java.util.ArrayList<Promotions>();
      return promotions;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorPromotions() {
      if (promotions == null)
         promotions = new java.util.ArrayList<Promotions>();
      return promotions.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newPromotions */
   public void setPromotions(java.util.List<Promotions> newPromotions) {
      removeAllPromotions();
      for (java.util.Iterator iter = newPromotions.iterator(); iter.hasNext();)
         addPromotions((Promotions)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newPromotions */
   public void addPromotions(Promotions newPromotions) {
      if (newPromotions == null)
         return;
      if (this.promotions == null)
         this.promotions = new java.util.ArrayList<Promotions>();
      if (!this.promotions.contains(newPromotions))
      {
         this.promotions.add(newPromotions);
         newPromotions.setPharmacy(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldPromotions */
   public void removePromotions(Promotions oldPromotions) {
      if (oldPromotions == null)
         return;
      if (this.promotions != null)
         if (this.promotions.contains(oldPromotions))
         {
            this.promotions.remove(oldPromotions);
            oldPromotions.setPharmacy((Pharmacy)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllPromotions() {
      if (promotions != null)
      {
         Promotions oldPromotions;
         for (java.util.Iterator iter = getIteratorPromotions(); iter.hasNext();)
         {
            oldPromotions = (Promotions)iter.next();
            iter.remove();
            oldPromotions.setPharmacy((Pharmacy)null);
         }
      }
   }

}