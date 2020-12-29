package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.SupplyOrderDTO;
import isa.project.pharmacyapp.model.Drug;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.model.SupplyOrder;
import isa.project.pharmacyapp.model.embedded_ids.SupplyOrderDrugID;
import isa.project.pharmacyapp.model.many2many.SupplyOrderDrug;
import isa.project.pharmacyapp.repository.DrugRepository;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.repository.SupplyOrderRepository;
import isa.project.pharmacyapp.service.SupplyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplyOrderServiceImpl implements SupplyOrderService {

    @Autowired
    private SupplyOrderRepository orderRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;


    @Override
    public SupplyOrderDTO findById(Long id) {
        return null;
    }

    @Override
    public List<SupplyOrderDTO> findPharmacyOrders(Long pharmacyID) {
        return null;
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

        Pharmacy pharmacy = pharmacyRepository.findById(orderDTO.getPharmacyID()).orElse(null);
        if(pharmacy == null){
            throw new Exception("Pharmacy does not exists");
        }

        SupplyOrderDTO.dto2Order(order, orderDTO);

        order.setPharmacy(pharmacy);

        try {
            order = orderRepository.save(order);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("saving supply order");
        }

        /**
         * List of drugs in the supply
         * */
        ArrayList<SupplyOrderDrug> orderDrugs = new ArrayList<>();


        /**
         * Sizes of {@code SupplyOrderDTO::drugs} and {@code SupplyOrderDTO::amount} are equal
         * */
        for(int i = 0; i < orderDTO.getDrugs().size(); i++){
            Drug drug = drugRepository.findById(orderDTO.getDrugs().get(i)).orElse(null);
            Integer amount = orderDTO.getAmount().get(i);

            SupplyOrderDrug orderDrug = new SupplyOrderDrug();

            /**
             * Creating a new key
             * */
            SupplyOrderDrugID id = new SupplyOrderDrugID();
            id.setDrug(drug);
            id.setSupplyOrder(order);

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
}
