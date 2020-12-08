package isa.project.pharmacyapp.exception;

public class ResourceConflictException extends RuntimeException {

    private Long resourceId;

    public ResourceConflictException(String message, Long resourceId) {
        super(message);
        this.resourceId = resourceId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}
