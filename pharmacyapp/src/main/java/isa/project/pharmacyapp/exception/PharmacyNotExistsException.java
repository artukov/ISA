package isa.project.pharmacyapp.exception;

public class PharmacyNotExistsException  extends Exception{

    public static String message = "Pharmacy with the given id does not exists";

    public PharmacyNotExistsException(String message) {
        super(message);
    }
}
