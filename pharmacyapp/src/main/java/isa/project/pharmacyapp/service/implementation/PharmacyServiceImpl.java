package isa.project.pharmacyapp.service.implementation;


import isa.project.pharmacyapp.dto.DateLimitsDTO;
import isa.project.pharmacyapp.dto.DermatologistDTO;
import isa.project.pharmacyapp.dto.PharmacyDTO;
import isa.project.pharmacyapp.dto.WorkingHoursDTO;
import isa.project.pharmacyapp.exception.InsertingDermatologistException;
import isa.project.pharmacyapp.model.*;
import isa.project.pharmacyapp.model.embedded_ids.DermaPharmacyId;
import isa.project.pharmacyapp.model.many2many.DermatologistRatings;
import isa.project.pharmacyapp.model.many2many.PharmacyDermatologist;
import isa.project.pharmacyapp.repository.AddressRepository;
import isa.project.pharmacyapp.repository.CalendarRepository;
import isa.project.pharmacyapp.repository.DermatologistRepository;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private DermatologistRepository dermatologistRepository;

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

    @Override
    public DermatologistDTO addNewDermatologist(Long dermaID, Long pharmacyID, WorkingHoursDTO workingHoursDTO) throws Exception {

        Dermatologist dermatologist = dermatologistRepository.findById(dermaID).orElse(null);
        if(dermatologist == null){
            throw new Exception("Dermatologist does not exists");
        }

        Pharmacy pharmacy = pharmacyRepository.getOne(pharmacyID);

//        if(dermatologistRepository.overlappingWorkingHours(dermaID, pharmacyID,
//                workingHoursDTO.getStartHour(), workingHoursDTO.getHours()) != 0.0){
//            throw new Exception("Hours are overlapping with another pharmacy");
//        }
//
        Date endHours = new Date();
        endHours.setTime(workingHoursDTO.getStartHour().getTime());
        endHours.setHours(endHours.getHours() + workingHoursDTO.getHours());
        Double result = pharmacyRepository.overlappingWorkingHours(dermaID, pharmacyID,
                workingHoursDTO.getStartHour(), endHours);
        if(result != 0.0){
            throw new InsertingDermatologistException("Hours are overlapping with another pharmacy");
        }


        PharmacyDermatologist pharmacyDermatologist = new PharmacyDermatologist();

        DermaPharmacyId id = new DermaPharmacyId();
        id.setDermatologist(dermatologist);
        id.setPharmacy(pharmacy);

        pharmacyDermatologist.setId(id);
        pharmacyDermatologist.setHours(workingHoursDTO.getHours());
        pharmacyDermatologist.setStartHour(workingHoursDTO.getStartHour());

        pharmacy.getDermatologists().add(pharmacyDermatologist);

        /**
         * Setting rating of the dermatologist for the new pharmacy
         * */
        DermatologistRatings dermatologistRatings = new DermatologistRatings();

        dermatologistRatings.setId(id);
        dermatologistRatings.setRatings(new ArrayList<Double>());

        dermatologist.getDermatologistRatings().add(dermatologistRatings);

        try{
            pharmacyRepository.save(pharmacy);
            dermatologistRepository.save(dermatologist);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("saving pharmacy");
        }

        DermatologistDTO retDerma = DermatologistDTO.dermatologist2Dto(dermatologist);
        retDerma.setRatings(0.0);
        retDerma.setHours(workingHoursDTO.getHours());
        retDerma.setStart_hour(workingHoursDTO.getStartHour());

        return retDerma;






    }

    private void savePharmacy(Pharmacy pharmacy, PharmacyDTO pharmacyDTO) throws Exception, NoSuchElementException {

        PharmacyDTO.dto2Pharmacy(pharmacy, pharmacyDTO);

        /**
         * TODO
         * Deleting address if it has been changed, and checking if the deletion is allowed
         * */
        Address address = new Address();
        if(pharmacyDTO.getAddress() != null){
            address = addressRepository.save(pharmacyDTO.getAddress());
        }
        else{
            addressRepository.save(address);
        }

        pharmacy.setAddress(address);

        pharmacy.setCalendar(calendarRepository.findById(pharmacyDTO.getCalendarID()).orElse(new Calendar()));

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

    public List<PharmacyDTO> getPharmaciesWithFreeConsultation(Date dateTime){
        List<Pharmacy> pharmacies = pharmacyRepository.getPharmaciesWithFreeConsultation(dateTime);
        List<PharmacyDTO> dtos = new ArrayList<>();
        for(Pharmacy p: pharmacies){
            //PharmacyDTO dto = new PharmacyDTO();
            dtos.add(PharmacyDTO.pharmacy2DTO(p));
        }

        return dtos;
    }
}
