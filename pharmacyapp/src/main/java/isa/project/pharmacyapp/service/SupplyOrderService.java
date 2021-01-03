package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.OrderSupplierDTO;
import isa.project.pharmacyapp.dto.SupplyOrderDTO;
import isa.project.pharmacyapp.model.OrderStatus;
import isa.project.pharmacyapp.model.SupplyOrder;

import java.util.List;

public interface SupplyOrderService {

    SupplyOrderDTO findById(Long id);
    List<SupplyOrderDTO> findPharmacyOrders(Long pharmacyID, OrderStatus status);

    void createNewSupplyOrder(SupplyOrderDTO orderDTO) throws Exception;
    void modifySupplyOrder(Long id, SupplyOrderDTO orderDTO);

    void saveSupplyOrder(SupplyOrder order, SupplyOrderDTO orderDTO) throws Exception;

    void modifyOrderWithOffer(OrderSupplierDTO dto) throws Exception;


    SupplyOrder acceptOfferForOrder(OrderSupplierDTO dto) throws Exception;
}
