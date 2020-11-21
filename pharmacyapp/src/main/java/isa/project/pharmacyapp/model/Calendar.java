package isa.project.pharmacyapp.model; /***********************************************************************
 * Module:  Calendar.java
 * Author:  User
 * Purpose: Defines the Class Calendar
 ***********************************************************************/

import java.util.*;
import javax.persistence.*;
/** @pdOid e96db096-4921-4472-b1fd-bb4ddce28df4 */
@Entity
@Table(name = "calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Calendar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}