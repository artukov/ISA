
package isa.project.pharmacyapp.model;
/***********************************************************************

 * Module:  User.java
 * Author:  User
 * Purpose: Defines the Class User
 ***********************************************************************/

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Timestamp;
import java.util.*;

/** @pdOid ec55a7e0-adf7-49f8-be3c-0032c8378e0d */
@Entity
@Table(name = "user_table")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@MappedSuperclass
public  class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   protected Long id;

   @Column(nullable = false, unique = true, name = "email")
   protected String email;

   @JsonIgnore
   @Column(nullable = false, unique = false)
   protected String password;

   @Column(nullable = false)
   protected String firstname;

   @Column(nullable = false)
   protected String lastname;

   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "address_id")
   protected Address address;

   @Column(nullable = true, name = "phone_number")
   protected String phoneNumber;

   @Column(name = "role", nullable = true)
   protected UserRoles role;


   /************************
    * Security attributes
       *  lastPasswordResetDate
       *  enabled
       *  authorities
    ************************/

   @Column(name = "last_password_reset_date", nullable = true)
   protected Timestamp lastPasswordResetDate;

   @Column(name = "enabled")
   protected Boolean enabled = true;


   @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinTable(
           name = "user_authority",
           joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "authority_id",referencedColumnName = "id")
   )
   private List<Authority> authorities;
   

   protected void finalize() {
      // TODO: implement
   }
   

   public User() {
      // TODO: implement
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      Timestamp now = new Timestamp(DateTime.now().getMillis());
      this.setLastPasswordResetDate(now);
      this.password = password;
   }

   public String getFirstname() {
      return firstname;
   }

   public void setFirstname(String firstname) {
      this.firstname = firstname;
   }

   public String getLastname() {
      return lastname;
   }

   public void setLastname(String lastname) {
      this.lastname = lastname;
   }

   public Address getAddress() {
      return address;
   }

   public void setAddress(Address address) {
      this.address = address;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public UserRoles getRole() {
      return role;
   }

   public void setRole(UserRoles role) {
      this.role = role;
   }

   /******************************************
    * Security methods
       * lastPasswordResetDate getter and setters
       * enable getters and setters
       * authorities getters and setters
       * isAccountNonExpired
       * isAccountNonLocked
       * isCredentialsNonExpired
    ******************************************/

   public Timestamp getLastPasswordResetDate() {
      return lastPasswordResetDate;
   }

   public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
      this.lastPasswordResetDate = lastPasswordResetDate;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return this.authorities;
   }

   public void setAuthorities(List<Authority> authorities) {
      this.authorities = authorities;
   }

   public Boolean getEnabled() {
      return enabled;
   }

   @Override
   public boolean isEnabled() {
      return this.enabled;
   }

   public void setEnabled(Boolean enabled) {
      this.enabled = enabled;
   }

   @JsonIgnore
   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @JsonIgnore
   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @JsonIgnore
   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public String getUsername() {
      return this.email;
   }
}