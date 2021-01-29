package isa.project.pharmacyapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import isa.project.pharmacyapp.model.OrderStatus;
import isa.project.pharmacyapp.model.SupplyOrder;
import isa.project.pharmacyapp.model.many2many.SupplierOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SupplyOrderDTO {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    private Date deadlineDate;
    private Long adminID;

    /**
     * TODO:
     * Fetch name and id of the drug
     * */
    private List<Long> drugs;
    private List<String> drugsNames;
    private List<Integer> amount;


    private List<OrderSupplierDTO> supplierDTOS;





    public SupplyOrderDTO() {
    }


//    public SupplyOrderDTO(Long id, Date deadlineDate, Long adminID,
//                          List<Long> drugs, List<Integer> amount, List<OrderSupplierDTO> supplierDTOS) {
//        this.id = id;
//        this.deadlineDate = deadlineDate;
//        this.adminID = adminID;
//        this.drugs = drugs;
//        this.amount = amount;
//        this.supplierDTOS = supplierDTOS;
//    }

    public SupplyOrderDTO(Long id, Date deadlineDate, Long adminID, List<Long> drugs,
                          List<String> drugsNames, List<Integer> amount, List<OrderSupplierDTO> supplierDTOS) {
        this.id = id;
        this.deadlineDate = deadlineDate;
        this.adminID = adminID;
        this.drugs = drugs;
        this.drugsNames = drugsNames;
        this.amount = amount;
        this.supplierDTOS = supplierDTOS;
    }

    public static void dto2Order(SupplyOrder order, SupplyOrderDTO orderDTO) {
        order.setDeadlineDate(orderDTO.getDeadlineDate());
    }

    public static SupplyOrderDTO order2DTO(SupplyOrder order, OrderStatus status) {
        SupplyOrderDTO dto = new SupplyOrderDTO(
                order.getId(),
                order.getDeadlineDate(),
                order.getPharmacyAdmin().getId(),
                new ArrayList<>(),//drugs,
                new ArrayList<>(),
                new ArrayList<>(), // amount,
                new ArrayList<>() // Order Supplier
        );

        for(int i = 0; i < order.getDrugs().size(); i++){
            dto.getDrugs().add(order.getDrugs().get(i).getId().getDrug().getId());
            dto.getDrugsNames().add(order.getDrugs().get(i).getId().getDrug().getName());
            dto.getAmount().add(order.getDrugs().get(i).getAmount());
        }
        for(SupplierOrder supplierOrder : order.getSuppliers()){
            if(supplierOrder.getStatus() != status)
                continue;
            dto.getSupplierDTOS().add(OrderSupplierDTO.supplierOrder2DTO(supplierOrder));
        }
        return dto;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public List<Long> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Long> drugs) {
        this.drugs = drugs;
    }

    public List<Integer> getAmount() {
        return amount;
    }

    public void setAmount(List<Integer> amount) {
        this.amount = amount;
    }

    public Long getAdminID() {
        return adminID;
    }

    public void setAdminID(Long adminID) {
        this.adminID = adminID;
    }

    public List<OrderSupplierDTO> getSupplierDTOS() {
        return supplierDTOS;
    }

    public void setSupplierDTOS(List<OrderSupplierDTO> supplierDTOS) {
        this.supplierDTOS = supplierDTOS;
    }

    public List<String> getDrugsNames() {
        return drugsNames;
    }

    public void setDrugsNames(List<String> drugsNames) {
        this.drugsNames = drugsNames;
    }
}