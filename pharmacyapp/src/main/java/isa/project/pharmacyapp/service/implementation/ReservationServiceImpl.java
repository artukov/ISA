package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.ReservationDTO;
import isa.project.pharmacyapp.model.Drug;
import isa.project.pharmacyapp.model.Patient;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.model.Reservation;
import isa.project.pharmacyapp.repository.DrugRepository;
import isa.project.pharmacyapp.repository.PatientRepository;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.repository.ReservationRepository;
import isa.project.pharmacyapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void createNewReservation(ReservationDTO reservationDTO) throws Exception {
        Reservation reservation = new Reservation();

        try {
            this.saveReservation(reservation, reservationDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public void modifyReservation(Long id, ReservationDTO reservationDTO) {

    }


    @Override
    public void saveReservation(Reservation reservation, ReservationDTO reservationDTO) throws Exception {
        ReservationDTO.dto2reservation(reservation, reservationDTO);

        Pharmacy pharmacy = pharmacyRepository.findById(reservationDTO.getPharmacyID()).orElse(null);
        if(pharmacy == null){
            throw  new Exception("Pharmacy does not exists");
        }
        Patient patient = patientRepository.findById(reservationDTO.getPatientID()).orElse(null);
        if(patient == null){
            throw  new Exception("Patient does not exists");
        }

        ArrayList<Drug> drugs = new ArrayList<>();
        for(Long drugID : reservationDTO.getDrugs()){
            if(drugRepository.getAmount(drugID, pharmacy.getId()) < 1){
                throw  new Exception("Pharmacy has not have enough of drug in the stock");
            }
            Drug drug = drugRepository.findById(drugID).orElse(null);
            drugs.add(drug);

        }

        reservation.setPatient(patient);
        reservation.setPharmacy(pharmacy);
        reservation.setDrug(drugs);

        try{
            reservationRepository.save(reservation);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("Saving reservation");
        }


    }

    @Transactional
    @Override
    public void drugAccepted(Long id) throws Exception {
        Reservation reservation = reservationRepository.getOne(id);
        reservation.setAccepted(true);

        try{
            reservationRepository.save(reservation);
        }
        catch (Exception e){
            throw new Exception("Saving reservation");
        }

        for(Drug drug : reservation.getDrug()){
            drugRepository.addDrugToPharmacy(drug.getId(),reservation.getPharmacy().getId(),-1);
        }

    }
}
