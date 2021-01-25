package isa.project.pharmacyapp.service;

import isa.project.pharmacyapp.model.many2many.SupplyOrderDrug;

import java.util.List;

public interface SupplierService extends UserService {

    void updateWareHouse(List<SupplyOrderDrug> drugs, Long supplierID) throws Exception;
}
