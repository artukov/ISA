package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  Drug.java
 * Author:  User
 * Purpose: Defines the Class Drug
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;
/** @pdOid 61c8608a-4d7a-4c58-a985-fea7e9bef4ed */

@Entity
@Table(name = "drug")
public class Drug {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   /** @pdOid 4212795f-99dd-4d4f-b992-3402ea32a8e3 */
   @Column(nullable = false, unique = true)
   private String name;
   /** @pdOid 9921a95d-3be5-4c3b-bb71-fb44f11eda52 */
   @Column(nullable = false)
   private Long code;
   /** @pdOid dc96261d-f51a-410f-8313-8ffb9c1f1235 */
   @Column(nullable = false)
   private String type;
   /** @pdOid 3f713ddc-5b12-4212-ac64-0500a73dd538 */
   @Column(nullable = false)
   private String shape;
   /** @pdOid f9e070fe-5620-44b2-8262-fe4ef41ef25b */
   @Column(nullable = false)
   private String manufacturer;
   /** @pdOid 66c31f60-6ca1-48e4-af34-697d90836105 */
   @Column(nullable = true)
   private Boolean receipt = null;
   
   /** @pdRoleInfo migr=no name=DrugSpecification assc=association9 mult=1..1 */
   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "spec_id")
   private DrugSpecification drugSpecification;
   /** @pdRoleInfo migr=no name=Drug assc=association29 coll=java.util.List impl=java.util.ArrayList mult=0..* */
   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(
           name = "substitute_drugs",
           joinColumns = @JoinColumn(name = "original_id"),
           inverseJoinColumns = @JoinColumn(name = "substitute_id")
   )
   private java.util.List<Drug> substituteDrugs;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "drug_id")
   private List<PharmacyDrug> pharmacies;

   public Drug() {
   }

   public List<PharmacyDrug> getPharmacies() {
      return pharmacies;
   }

   public void setPharmacies(List<PharmacyDrug> pharmacies) {
      this.pharmacies = pharmacies;
   }



   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Long getCode() {
      return code;
   }

   public void setCode(Long code) {
      this.code = code;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getShape() {
      return shape;
   }

   public void setShape(String shape) {
      this.shape = shape;
   }

   public String getManufacturer() {
      return manufacturer;
   }

   public void setManufacturer(String manufacturer) {
      this.manufacturer = manufacturer;
   }

   public Boolean getReceipt() {
      return receipt;
   }

   public void setReceipt(Boolean receipt) {
      this.receipt = receipt;
   }

   public DrugSpecification getDrugSpecification() {
      return drugSpecification;
   }

   public void setDrugSpecification(DrugSpecification drugSpecification) {
      this.drugSpecification = drugSpecification;
   }

   /** @pdGenerated default getter */
   public java.util.List<Drug> getSubstituteDrugs() {
      if (substituteDrugs == null)
         substituteDrugs = new java.util.ArrayList<Drug>();
      return substituteDrugs;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorSubstituteDrugs() {
      if (substituteDrugs == null)
         substituteDrugs = new java.util.ArrayList<Drug>();
      return substituteDrugs.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newSubstituteDrugs */
   public void setSubstituteDrugs(java.util.List<Drug> newSubstituteDrugs) {
      removeAllSubstituteDrugs();
      for (java.util.Iterator iter = newSubstituteDrugs.iterator(); iter.hasNext();)
         addSubstituteDrugs((Drug)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDrug */
   public void addSubstituteDrugs(Drug newDrug) {
      if (newDrug == null)
         return;
      if (this.substituteDrugs == null)
         this.substituteDrugs = new java.util.ArrayList<Drug>();
      if (!this.substituteDrugs.contains(newDrug))
         this.substituteDrugs.add(newDrug);
   }
   
   /** @pdGenerated default remove
     * @param oldDrug */
   public void removeSubstituteDrugs(Drug oldDrug) {
      if (oldDrug == null)
         return;
      if (this.substituteDrugs != null)
         if (this.substituteDrugs.contains(oldDrug))
            this.substituteDrugs.remove(oldDrug);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllSubstituteDrugs() {
      if (substituteDrugs != null)
         substituteDrugs.clear();
   }

}