package isa.project.pharmacyapp.exception;

public class DrugNotExistsInPharmacyException  extends Exception {

    public static String message = "Drug does not exists in this pharmacy";

    public DrugNotExistsInPharmacyException(String message) {
        super(message);
    }
}
