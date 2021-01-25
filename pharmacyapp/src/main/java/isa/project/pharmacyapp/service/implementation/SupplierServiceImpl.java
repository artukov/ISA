package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.model.Supplier;
import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.model.WareHouse;
import isa.project.pharmacyapp.model.many2many.SupplyOrderDrug;
import isa.project.pharmacyapp.repository.SupplierRepository;
import isa.project.pharmacyapp.repository.WareHouseRepository;
import isa.project.pharmacyapp.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {


    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private WareHouseRepository wareHouseRepository;

    @Transactional
    @Override
    public void updateWareHouse(List<SupplyOrderDrug> drugs, Long supplierID) throws Exception {

        WareHouse wareHouse = wareHouseRepository.findBySupplier_id(supplierID);
        for(SupplyOrderDrug orderDrug : drugs){
           try{
               //Integer amount = 0 - orderDrug.getAmount();
               wareHouseRepository.updateAmount((~(orderDrug.getAmount() -1 )),orderDrug.getId().getDrug().getId(),wareHouse.getId());
           }
           catch (Exception e){
               throw new Exception("Update warehouse");
           }
        }

    }

    @Override
    public Supplier findByEmail(String email) {
        return supplierRepository.findByEmail(email);
    }

    @Override
    public User saveNewUser(UserDTO userDTO) throws Exception {
        return null;
    }
}
