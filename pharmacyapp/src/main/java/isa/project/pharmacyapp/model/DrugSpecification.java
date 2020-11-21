package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  DrugSpecification.java
 * Author:  User
 * Purpose: Defines the Class DrugSpecification
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;
/** @pdOid 66b7026f-d90a-4786-abd3-728392799c2f */
@Entity
@Table(name = "drug_spec")
public class DrugSpecification {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   /** @pdOid 3222e9fd-2e2d-4e2a-a2df-3085077f3fac */
   @Column(nullable = true)
   private String sideEffects;
   /** @pdOid 9ccab86b-f991-4180-ba18-1dad90227a18 */
   @Column(nullable = false)
   private String composition;
   /** @pdOid 73822e41-6a3b-4ce4-8c82-61d10cd7611c */
   @Column(nullable = false, name = "recom_consum")
   private String recommendedConsumption;

   public DrugSpecification() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getSideEffects() {
      return sideEffects;
   }

   public void setSideEffects(String sideEffects) {
      this.sideEffects = sideEffects;
   }

   public String getComposition() {
      return composition;
   }

   public void setComposition(String composition) {
      this.composition = composition;
   }

   public String getRecommendedConsumption() {
      return recommendedConsumption;
   }

   public void setRecommendedConsumption(String recommendedConsumption) {
      this.recommendedConsumption = recommendedConsumption;
   }
}