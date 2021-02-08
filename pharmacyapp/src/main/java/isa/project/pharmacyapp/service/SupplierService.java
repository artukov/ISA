package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.dto.SupplierDTO;
import isa.project.pharmacyapp.dto.SupplyOrderDTO;
import isa.project.pharmacyapp.model.OrderStatus;
import isa.project.pharmacyapp.model.Supplier;
import isa.project.pharmacyapp.model.SupplyOrder;
import isa.project.pharmacyapp.model.many2many.SupplyOrderDrug;

import java.util.List;

public interface SupplierService extends UserService {

    void updateWareHouse(List<SupplyOrderDrug> drugs, Long supplierID) throws Exception;

    List<SupplierDTO> findAll();

    List<SupplyOrderDTO> findAllIncomingOrders(Long supplierID);

    List<SupplyOrderDTO> findAllSupplierOrders(Long supplierID);

    List<SupplyOrderDTO> filterSupplyOrders(List<SupplyOrder> orders, Long supplierID, OrderStatus statuss);

    void createNewSupplier(SupplierDTO supplierDTO) throws Exception;
    void saveSupplier(Supplier supplier, SupplierDTO supplierDTO) throws Exception;
}
