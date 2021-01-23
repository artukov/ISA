package isa.project.pharmacyapp.exception;

import java.util.Date;

public class DermatologistNotWorkingException extends RuntimeException{

    Date toEarly;
    Boolean notWorking;

    public DermatologistNotWorkingException(String message, Date toEarly) {
        super(message);
        this.toEarly = toEarly;
    }

    public DermatologistNotWorkingException(String message, Boolean notWorking) {
        super(message);
        this.notWorking = notWorking;
    }
}
