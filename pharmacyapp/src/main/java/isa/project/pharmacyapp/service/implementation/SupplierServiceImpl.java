package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.OrderSupplierDTO;
import isa.project.pharmacyapp.dto.SupplierDTO;
import isa.project.pharmacyapp.dto.SupplyOrderDTO;
import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.model.*;
import isa.project.pharmacyapp.model.many2many.SupplierOrder;
import isa.project.pharmacyapp.model.many2many.SupplyOrderDrug;
import isa.project.pharmacyapp.repository.SupplierRepository;
import isa.project.pharmacyapp.repository.SupplyOrderRepository;
import isa.project.pharmacyapp.repository.WareHouseRepository;
import isa.project.pharmacyapp.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {


    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private WareHouseRepository wareHouseRepository;

    @Autowired
    private SupplyOrderRepository orderRepository;

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
    public List<SupplierDTO> findAll() {
        List<Supplier> suppliers = supplierRepository.findAll();

        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();
        for(Supplier supplier : suppliers){
            supplierDTOS.add( SupplierDTO.supplier2DTO(supplier));
        }
        /**
         * TODO:
         * Creat static method that creates SupplierDTO object with Supplier state
         * */
        return supplierDTOS;

    }


    @Override
    public List<SupplyOrderDTO> findAllIncomingOrders(Long supplierID) {
        List<SupplyOrder> supplyOrders = orderRepository.findSupplierIncoming(supplierID);
        ArrayList<SupplyOrderDTO> supplyOrderDTOS = new ArrayList<>();
        for(SupplyOrder order : supplyOrders){
            supplyOrderDTOS.add(SupplyOrderDTO.order2DTO(order, OrderStatus.PENDING));
        }
        ArrayList<OrderSupplierDTO> orderSupplierDTOS = new ArrayList<>();
        for(SupplyOrderDTO supplyOrderDTO : supplyOrderDTOS){
            for(OrderSupplierDTO orderSupplierDTO : supplyOrderDTO.getSupplierDTOS()){
                if(orderSupplierDTO.getSupplierID().equals(supplierID)){
                    supplyOrderDTO.setSupplierDTOS(new ArrayList<>());
                    supplyOrderDTO.getSupplierDTOS().add(orderSupplierDTO);
                    break;
                }
                continue;
            }
        }


        return supplyOrderDTOS;
    }




    /**
     * UserService methods
     * */

    @Override
    public Supplier findByEmail(String email) {
        return supplierRepository.findByEmail(email);
    }

    @Override
    public User saveNewUser(UserDTO userDTO) throws Exception {
        return null;
    }
}
