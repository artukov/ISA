package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.SupplyOrderDTO;
import isa.project.pharmacyapp.model.SupplyOrder;

import java.util.List;

public interface SupplyOrderService {

    SupplyOrderDTO findById(Long id);
    List<SupplyOrderDTO> findPharmacyOrders(Long pharmacyID);

    void createNewSupplyOrder(SupplyOrderDTO orderDTO) throws Exception;
    void modifySupplyOrder(Long id, SupplyOrderDTO orderDTO);

    void saveSupplyOrder(SupplyOrder order, SupplyOrderDTO orderDTO) throws Exception;

}
