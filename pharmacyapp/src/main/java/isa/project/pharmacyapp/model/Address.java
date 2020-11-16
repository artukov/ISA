package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  Address.java
 * Author:  User
 * Purpose: Defines the Class Address
 ***********************************************************************/




import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.*;

@Entity
@Table(name = "address")
/** @pdOid 176e15b0-8fe8-4183-845d-aba7ce69df77 */
public class Address {


   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   /** @pdOid 6c0179d6-670f-4af9-9c22-63fada5128bf */
   @Column
   private String street;
   /** @pdOid 31d5e5c0-8cb6-4b19-afc0-1aa1d06ee65f */
   @Column
   private int streetNumber;
   /** @pdOid 33342b88-66ed-4d99-befb-e6a32d6823b6 */
   @Column
   private String city;
   /** @pdOid 02024c05-3a16-434b-a003-9d2f98429f8a */
   @Column
   private String country;


   public Address() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getStreet() {
      return street;
   }

   public void setStreet(String street) {
      this.street = street;
   }

   public int getStreetNumber() {
      return streetNumber;
   }

   public void setStreetNumber(int streetNumber) {
      this.streetNumber = streetNumber;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }
}