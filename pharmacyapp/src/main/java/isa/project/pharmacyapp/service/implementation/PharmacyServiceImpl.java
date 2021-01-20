package isa.project.pharmacyapp.service.implementation;


import isa.project.pharmacyapp.dto.DateLimitsDTO;
import isa.project.pharmacyapp.dto.PharmacyDTO;
import isa.project.pharmacyapp.model.Address;
import isa.project.pharmacyapp.model.Appointment;
import isa.project.pharmacyapp.model.Calendar;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.repository.AddressRepository;
import isa.project.pharmacyapp.repository.CalendarRepository;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PharmacyServiceImpl implements PharmacyService {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CalendarRepository calendarRepository;

    @Override
    public PharmacyDTO findById(Long id) throws NoSuchElementException {
        Pharmacy pharmacy = pharmacyRepository.findById(id).orElse(null);

        if(pharmacy == null){
            throw new NoSuchElementException("PharmacyServiceImpl::findById pharmacy does not exists for given id");
        }

        PharmacyDTO pharmacyDTO = PharmacyDTO.pharmacy2DTO(pharmacy);

        return pharmacyDTO;
    }

    @Override
    public List<PharmacyDTO> findAll() {

        ArrayList<Pharmacy> pharmacies = (ArrayList<Pharmacy>) pharmacyRepository.findAll();
        ArrayList<PharmacyDTO> retList = new ArrayList<>();


        for(Pharmacy pharmacy : pharmacies){
            retList.add(PharmacyDTO.pharmacy2DTO(pharmacy));
        }

        return retList;
    }

    @Override
    public void createNewPharmacy(PharmacyDTO pharmacyDTO) throws Exception {

        Pharmacy pharmacy = new Pharmacy();

        this.savePharmacy(pharmacy, pharmacyDTO);


    }

    @Override
    public PharmacyDTO modifyPharmacy(Long id, PharmacyDTO pharmacyDTO) throws Exception, NoSuchElementException {

        Pharmacy pharmacy = pharmacyRepository.findById(id).orElse(null);

        if(pharmacy == null){
            throw new NoSuchElementException("PharmacyServiceImpl::modifyPharmacy There is no pharmacy with this id");
        }

        savePharmacy(pharmacy,pharmacyDTO);

        return pharmacyDTO;

    }

    @Override
    public void deletePharmacy(Long id) throws Exception {
        Pharmacy pharmacy = pharmacyRepository.findById(id).orElse(null);

        if(pharmacy == null){
            throw new NoSuchElementException("PharmacyServiceImpl::modifyPharmacy There is no pharmacy with this id");
        }

        try{
            pharmacyRepository.deleteById(id);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("PharmacyServiceImpl::deletePharmacy deleting did not execute successfully");
        }

    }

    @Override
    public Double calculateFinances(DateLimitsDTO limitsDTO, Long id) {
        Pharmacy pharmacy = pharmacyRepository.findById(id).orElse(null);
        return calendarRepository.getAppointmentsBasedOnDate(pharmacy.getCalendar().getId(),
                limitsDTO.getLowerLimit(), limitsDTO.getUpperLimit());

    }

    @Transactional
    @Override
    public Address getAddress(Long id) {
        Address  pharmacyAddress = pharmacyRepository.getAddress(id);
        return pharmacyAddress;
    }

    private void savePharmacy(Pharmacy pharmacy, PharmacyDTO pharmacyDTO) throws Exception, NoSuchElementException {

        PharmacyDTO.dto2Pharmacy(pharmacy, pharmacyDTO);

        /**
         * TODO
         * Deleting address if it has been changed, and checking if the deletion is allowed
         * */

        Address savedAddress = addressRepository.save(pharmacyDTO.getAddress());
        pharmacy.setAddress(savedAddress);
//        if(savedAddress.getId() != pharmacyDTO.getAddress().getId()){
//            addressRepository.delete(pharmacyDTO.getAddress());
//
//        }
//        else
//            pharmacy.setAddress(pharmacyDTO.getAddress());



        pharmacy.setCalendar(calendarRepository.findById(pharmacyDTO.getCalendarID()).orElse(null));

        if(pharmacy.getAddress() == null){
            throw new NoSuchElementException("PharmacyServiceImpl::savePharmacy there is no address by this id");
        }

        if(pharmacy.getCalendar() == null)
            throw  new  NoSuchElementException("PharmacyServiceImpl::savePharmacy there is no calendar by this id");

        try{
            pharmacyRepository.save(pharmacy);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("PharmacyServiceImpl::savePharmacy Saving pharmacy did not execute");
        }

    }
}
