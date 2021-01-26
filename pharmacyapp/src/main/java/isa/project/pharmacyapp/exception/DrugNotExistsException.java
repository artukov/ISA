package isa.project.pharmacyapp.exception;

public class DrugNotExistsException extends Exception{
    public static String message = "Drug with given id does not exists";

    public DrugNotExistsException(String message) {
        super(message);
    }
}
