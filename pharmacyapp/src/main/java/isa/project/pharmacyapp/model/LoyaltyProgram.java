package isa.project.pharmacyapp.model;

import javax.persistence.*;

@Entity
@Table(name = "loyalty_program")
public class LoyaltyProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @Id
//    @OneToOne
//    private User user;


    public LoyaltyProgram() {
    }
}
