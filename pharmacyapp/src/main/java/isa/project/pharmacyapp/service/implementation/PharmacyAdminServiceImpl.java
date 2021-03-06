package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.DrugRequestDTO;
import isa.project.pharmacyapp.dto.PharmacyAdminDTO;
import isa.project.pharmacyapp.dto.UserDTO;
import isa.project.pharmacyapp.model.*;
import isa.project.pharmacyapp.repository.AddressRepository;
import isa.project.pharmacyapp.repository.DrugNotInPharmacyStashRequestRepository;
import isa.project.pharmacyapp.repository.PharmacyAdminRepository;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.service.AuthorityService;
import isa.project.pharmacyapp.service.PharmacyAdminService;
import isa.project.pharmacyapp.service.bean_factory_statistics.BeanFactoryDynamicService;
import isa.project.pharmacyapp.strategy_pattern.StatisticsStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PharmacyAdminServiceImpl implements PharmacyAdminService {

    @Autowired
    private PharmacyAdminRepository adminRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private BeanFactoryDynamicService beanFactoryDynamicService;

    @Autowired
    private DrugNotInPharmacyStashRequestRepository drugNotInPharmacyStashRequestRepository;

    @Override
    public PharmacyAdminDTO findById(Long id) throws NoSuchElementException  {
        PharmacyAdmin admin = adminRepository.findById(id).orElse(null);
        if(admin == null){
            throw new NoSuchElementException("PharmacyAdminServiceImpl::findById(Long id) PharmacyAdmin with passed id does not exist");
        }

        PharmacyAdminDTO adminDTO =   PharmacyAdminDTO.pharmacyAdmin2DTO(admin);
        return adminDTO;

    }

    @Override
    public List<PharmacyAdminDTO> findAdminsOfPharmacy(Long pharmacyId) throws NoSuchElementException {
        ArrayList<PharmacyAdmin> adminList = (ArrayList<PharmacyAdmin>) adminRepository.findAllByPharmacyId(pharmacyId);
        if(adminList == null || adminList.size() == 0)
            throw new
                NoSuchElementException("PharamcyAdminSerivceImpl::findAdminsOfPharmacy(Long pharmacyId) there is no admins for given pharmacy");

        ArrayList<PharmacyAdminDTO> adminDTOS = new ArrayList<>();
        for(PharmacyAdmin admin : adminList){
            adminDTOS.add(PharmacyAdminDTO.pharmacyAdmin2DTO(admin));
        }

        return adminDTOS;
    }

    @Override
    public void createNewPharmacyAdmin(PharmacyAdminDTO adminDTO) throws Exception {

        PharmacyAdmin admin = new PharmacyAdmin();

        List<Authority> authorities = authorityService.findByName("USER");
        admin.setAuthorities(authorities);

        admin.setRole(UserRoles.PHARMACY_ADMIN);
        admin.setPassword(adminDTO.getPassword());
        admin.setLastPasswordResetDate(null);

        try {
            this.savePharmacyAdmin(admin,adminDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("PharmacyAdminServiceImpl::createNewPharmacyAdmin(PharmacyAdminDTO adminDTO)" +
                    "saving newly created PharmacyAdmin object");
        }


    }

    @Override
    public void modifyPharmacyAdmin(Long adminId, PharmacyAdminDTO adminDTO) throws Exception {
        PharmacyAdmin admin = adminRepository.findById(adminId).orElse(null);
        System.out.println("admin id:\t"+admin.getId());

        if(admin == null){
            throw new NoSuchElementException("PharmacyAdminSerivceImpl::modifyPharmacyAdmin(Long adminId, PharmacyAdminDTO adminDTO)" +
                    "pharmacy admin could not be find by the given id");
        }

        try {
            this.savePharmacyAdmin(admin, adminDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("PharamcyAdminServiceImpl::modifyPharmacyAdmin(Long adminId, PharmacyAdminDTO adminDTO)" +
                    "saving of the modified object did not excecute");
        }


    }

    @Override
    public void deletePharmacyAdmin(Long adminId) throws Exception {

        PharmacyAdmin admin = adminRepository.findById(adminId).orElse(null);
        if(admin == null){
            throw new NoSuchElementException("PharmacyAdminServiceImpl::deletePharmacyAdmin" +
                    "admin does not exists");
        }

        try{
           adminRepository.deleteById(adminId);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("PharamcyAdminServiceImpl::deletePharmacyAdmin" +
                    "deletion was not successful");
        }

    }

    @Override
    public PharmacyAdmin savePharmacyAdmin(PharmacyAdmin admin, PharmacyAdminDTO adminDTO) throws Exception {
        UserDTO.dto2User(admin,adminDTO);

        /**
         * TODO
         * Saving address of the user
         * */
        if(admin.getAddress() == null){
            admin.setAddress(addressRepository.save(adminDTO.getAddress()));
        }
        else if(adminDTO.getAddress().getId() == null){

        }



        try{
            admin.setPharmacy(pharmacyRepository.findById(adminDTO.getPharmacy_id()).orElse(null));
//            admin.setAddress(addressRepository.findById(adminDTO.getAddress_id()).orElse(null));
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("PharmacyAdminServiceImpl::savePharmacyAdmin(PharmacyAdmin admin, PharmacyAdminDTO adminDTO)" +
                    "pharmacy or address does not exists");
        }

        try {
            adminRepository.save(admin);
        }
        catch(Exception e){
            e.printStackTrace();
            throw new Exception("PharmacyAdminServiceImpl::savePharmacyAdmin(PharmacyAdmin admin, PharmacyAdminDTO adminDTO)" +
                    "saving admin exception ");
        }

        return admin;
    }

    @Override
    public Double getAvgRating(Long pharmacyId) throws Exception {
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).orElse(null);

        if(pharmacy == null){
            throw new Exception(getClass().getName()+"::getAvgRating pharmacy does not exists");
        }

        return pharmacyRepository.getAvgRating(pharmacyId);

    }

    @Override
    public List<Double> getExaminationStatistics(Long id, TimeSpam timeSpam) {


        Pharmacy pharmacy = pharmacyRepository.findById(id).orElse(null);

        StatisticsStrategy strategy = beanFactoryDynamicService.getStrategyStatistics(timeSpam);
        System.out.println("Strategy class: " + strategy.getClass().getName());
        ArrayList<Double> stats = (ArrayList<Double>) strategy.calculateStatistics(pharmacy);

        return stats;

    }

    @Override
    public List<DrugRequestDTO> getDrugRequestsNotInPharmacyStash(Long pharmacyID) {
        List<DrugNotInPharmacyStashRequest> drugRequests = drugNotInPharmacyStashRequestRepository.findByPharmacy_id(pharmacyID);
        ArrayList<DrugRequestDTO> drugRequestDTOS = new ArrayList<>();
        for(DrugNotInPharmacyStashRequest drugRequest : drugRequests){
            drugRequestDTOS.add(DrugRequestDTO.request2dto(drugRequest));
        }

        return drugRequestDTOS;
    }

    @Override
    public void checkIfAdminCreatedOrder(User admin, Long orderID) throws Exception {
        if(adminRepository.createdOrder(admin.getId(), orderID) == 0.0){
            throw  new Exception("Admin that created the order can only accept order");
        }
        return;
    }

    @Override
    public PharmacyAdmin findByEmail(String email) {
        return this.adminRepository.findByEmail(email);
    }

    @Override
    public User saveNewUser(UserDTO userDTO) throws Exception {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return this.savePharmacyAdmin(new PharmacyAdmin(),  PharmacyAdminDTO.createPharmacyDTO(userDTO));
    }


}
