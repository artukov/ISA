package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.OrderSupplierDTO;
import isa.project.pharmacyapp.dto.SupplyOrderDTO;
import isa.project.pharmacyapp.model.*;
import isa.project.pharmacyapp.model.embedded_ids.OrderSupplierID;
import isa.project.pharmacyapp.model.embedded_ids.SupplyOrderDrugID;
import isa.project.pharmacyapp.model.many2many.SupplierOrder;
import isa.project.pharmacyapp.model.many2many.SupplyOrderDrug;
import isa.project.pharmacyapp.repository.*;
import isa.project.pharmacyapp.security.TimeProvider;
import isa.project.pharmacyapp.service.SupplyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplyOrderServiceImpl implements SupplyOrderService {

    @Autowired
    private SupplyOrderRepository orderRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private PharmacyAdminRepository adminRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private WareHouseRepository wareHouseRepository;

    @Autowired
    private TimeProvider timeProvider;


    @Override
    public SupplyOrderDTO findById(Long id) {
        return null;
    }

    @Override
    public List<SupplyOrderDTO> findPharmacyOrders(Long pharmacyID, OrderStatus status) {
        //System.out.println("\t\t\tOrdinal : " + status.ordinal());

        List<SupplyOrder> orders = orderRepository.findByStatusPharmacy(status.ordinal(), pharmacyID);//Enum.ordinal() returns the position/ordinal of this enumeration constant
        ArrayList<SupplyOrderDTO> retOrders = new ArrayList<>();
        for(SupplyOrder order : orders){
            retOrders.add(SupplyOrderDTO.order2DTO(order,status));
        }

        return retOrders;

    }

    @Override
    public void createNewSupplyOrder(SupplyOrderDTO orderDTO) throws Exception {
        SupplyOrder order = new SupplyOrder();
        this.saveSupplyOrder(order,orderDTO);

    }

    @Override
    public void modifySupplyOrder(Long id, SupplyOrderDTO orderDTO) {

    }

    @Override
    public void saveSupplyOrder(SupplyOrder order, SupplyOrderDTO orderDTO) throws Exception {

        if(orderDTO.getDrugs().size() != orderDTO.getAmount().size()){
            throw  new Exception("Sizes of the arrays do not match");
        }

        PharmacyAdmin admin = adminRepository.findById(orderDTO.getAdminID()).orElse(null);
        if(admin == null){
            throw  new Exception("PharmacyAdmin does not exists");
        }


        SupplyOrderDTO.dto2Order(order, orderDTO);
        order.setPharmacyAdmin(admin);

        try {
            order = orderRepository.save(order);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("saving supply order");
        }



        ArrayList<SupplierOrder> suppliers = new ArrayList<>();



        for(OrderSupplierDTO supplierDTO : orderDTO.getSupplierDTOS()){
            Supplier supplier = supplierRepository.findById(supplierDTO.getSupplierID()).orElse(null);
            if(supplier == null){
                throw new Exception("Supplier does not exists with the ID: " + supplierDTO.getSupplierID());
            }
            SupplierOrder supplierOrder = new SupplierOrder();

            OrderSupplierID id = new OrderSupplierID();
            id.setOrder(order);
            id.setSupplier(supplier);

            supplierOrder.setId(id);
            supplierOrder.setPriceOffer(supplierDTO.getPriceOffer());
            supplierOrder.setDeliveryDate(supplierDTO.getDeliveryDate());
            supplierOrder.setStatus(OrderStatus.PENDING);

            suppliers.add(supplierOrder);

        }

        order.setSuppliers(suppliers);

        /**
         * List of drugs in the supply
         * */
        ArrayList<SupplyOrderDrug> orderDrugs = new ArrayList<>();



        /**
         * Sizes of {@code SupplyOrderDTO::drugs} and {@code SupplyOrderDTO::amount} are equal
         * */
        for(int i = 0; i < orderDTO.getDrugs().size(); i++){
            Drug drug = drugRepository.findById(orderDTO.getDrugs().get(i)).orElse(null);
            if(drug == null){
                throw new Exception("Drug does not exists with the ID: " + orderDTO.getDrugs().get(i));
            }
            Integer amount = orderDTO.getAmount().get(i);


            SupplyOrderDrug orderDrug = new SupplyOrderDrug();

            SupplyOrderDrugID id = new SupplyOrderDrugID();
            id.setSupplyOrder(order);
            id.setDrug(drug);

            /**
             * Setting new key and the amount of drugs
             * */
            orderDrug.setId(id);
            orderDrug.setAmount(amount);


            orderDrugs.add(orderDrug);


        }

        order.setDrugs(orderDrugs);

        try {
            orderRepository.save(order);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("saving supply order");
        }


    }

    @Transactional
    @Override
    public void modifyOrderWithOffer(OrderSupplierDTO dto) throws Exception {
        SupplyOrder order = orderRepository.findById(dto.getOrderID()).orElse(null);
        if(order == null){
            throw new Exception("Order does not exists");
        }

        if((timeProvider.now().after(order.getDeadlineDate()))){
            throw new Exception("To late to place an offer");
        }

        Supplier supplier = supplierRepository.findById(dto.getSupplierID()).orElse(null);
        if(supplier == null){
            throw  new Exception("Supplier does not exists");
        }

        /**
         * Check if the supplier has enough of drugs in warehouse
         * */
        for(SupplyOrderDrug orderDrug : order.getDrugs()){
            Integer stashedAmount = wareHouseRepository.getAmountOfDrugs(orderDrug.getId().getDrug().getId(), supplier.getId());

            if(stashedAmount < orderDrug.getAmount()){
                throw new Exception("Supplier does not have enough amount for drugs in warehouse");
            }
        }

        try{
           orderRepository.updateOffer(order.getId(), supplier.getId(), dto.getPriceOffer(), dto.getDeliveryDate());
        }
        catch (Exception e){
           e.printStackTrace();
           throw new Exception("Updating order");
        }
    }

    @Override
    public SupplyOrder acceptOfferForOrder(OrderSupplierDTO dto) throws Exception {
        SupplyOrder order = orderRepository.findById(dto.getOrderID()).orElse(null);
        if(order == null){
            throw new Exception("Order does not exists");
        }
        Supplier supplier = supplierRepository.findById(dto.getSupplierID()).orElse(null);
        if(supplier == null){
            throw new Exception("Supplier does not exists");
        }

        for(SupplierOrder supplierOrder : order.getSuppliers()){
            if(supplierOrder.getId().getSupplier().getId() == dto.getSupplierID()){
                supplierOrder.setStatus(OrderStatus.ACCEPTED);
            }
            else{
                supplierOrder.setStatus(OrderStatus.DENIED);
            }

        }

        try{
            orderRepository.save(order);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("Saving accepted order");
        }

        return  order;

    }

    @Override
    public List<SupplyOrderDTO> findWithoutOffer(Long pharmacyID) {
        PharmacyAdmin admin = adminRepository.findByPharmacy_id(pharmacyID);
        //System.out.println("\t\tPharmacy admin : " + admin.getEmail());

        List<SupplyOrder> orders = orderRepository.findWithoutOffers(admin.getId());

        ArrayList<SupplyOrderDTO> orderDTOS = new ArrayList<>();

        for(SupplyOrder order : orders){
            orderDTOS.add(SupplyOrderDTO.order2DTO(order, OrderStatus.PENDING));
        }

        return orderDTOS;
    }

    @Transactional
    @Override
    public void deleteSupplyOrder(Long orderID) {
        SupplyOrder order = orderRepository.getOne(orderID);

//        orderRepository.deleteOrder(orderID,orderID, orderID);
        return;
    }


//    @Override
//    public void modifySupplyOrderOffer(Long id, SupplyOrderDTO orderDTO) throws Exception {
//
//        if(timeProvider.now().after(orderDTO.getDeadlineDate())){
//            throw  new Exception("Change of order is not available, because of the deadline");
//        }
//
//        SupplyOrder order = orderRepository.findById(id).orElse(null);
//        if(order == null){
//            throw new Exception("Order does not exist");
//        }
//
//
////        if(order.getSupplier().getId() != orderDTO.getSupplierID()){
////            throw  new Exception("Supplier id does not equal supplyorder supplier id");
////        }
//
//        Supplier supplier = supplierRepository.findById(null).orElse(null);
//
//        for(int i = 0; i < orderDTO.getDrugs().size(); i++){
//            Integer stashedAmount = wareHouseRepository.getAmountOfDrugs(orderDTO.getDrugs().get(i), supplier.getId());
//
//            if(stashedAmount < orderDTO.getAmount().get(i)){
//                throw new Exception("Supplier does not have enough amount for drugs in warehouse");
//            }
//        }
//
//
////        order.setPriceOffer(orderDTO.getPriceOffer());
////        order.setDeliveryDate(orderDTO.getDeliveryDate());
//
//        try{
//            orderRepository.save(order);
//        }
//        catch (Exception e){
//            throw new Exception("Saving order with price offer");
//        }
//
//    }
}
