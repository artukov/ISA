package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.ReservationDTO;
import isa.project.pharmacyapp.exception.ReservationException;
import isa.project.pharmacyapp.model.Drug;
import isa.project.pharmacyapp.model.Patient;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.model.Reservation;
import isa.project.pharmacyapp.model.many2many.PharmacyDrug;
import isa.project.pharmacyapp.repository.DrugRepository;
import isa.project.pharmacyapp.repository.PatientRepository;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.repository.ReservationRepository;
import isa.project.pharmacyapp.service.EmailService;
import isa.project.pharmacyapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private EmailService emailService;

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
            for(PharmacyDrug pharmacyDrug : drug.getPharmacies()){
                if(pharmacyDrug.getId().getDrug().getId().equals(drug.getId())
                    && pharmacyDrug.getId().getPharmacy().getId().equals(reservation.getPharmacy().getId())){
                    pharmacyDrug.setAmount(pharmacyDrug.getAmount() - 1);
                    drugRepository.save(drug);
                    break;
                }

            }
//            drugRepository.addDrugToPharmacy(drug.getId(),reservation.getPharmacy().getId(),-1);
        }
    }

    @Override
    public void dispenseDrug(Long id) throws Exception{
        Reservation reservation = reservationRepository.findById(id).orElse(null);

        if (reservation == null){
            throw new ReservationException("Reservation does not exist");
        }
        Date newDate = new Date(reservation.getAcceptanceDate().getTime() + 24*3600*1000);
        Date currentDate = new Date(System.currentTimeMillis());
        if(currentDate.after(newDate)){
            throw new ReservationException("Reservation does not exist");
        }

        drugAccepted(id);
        Patient p = reservation.getPatient();
        emailService.sendSimpleMessage(p.getEmail(),"Drug accepted","YOu have accepted drugs from your reservation");
    }

    @Override
    public ReservationDTO getByIdAndPharmacy(Long reservationId, Long pharmacyId){
        Reservation reservation = reservationRepository.getByIdAndPharmacy(reservationId, pharmacyId);
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setId(reservation.getId());
        reservationDTO.setAcceptanceDate(reservation.getAcceptanceDate());
        reservationDTO.setAccepted(reservation.getAccepted());
        reservationDTO.setPatientID(reservation.getPatient().getId());
        reservationDTO.setPharmacyID(reservation.getPharmacy().getId());
        List<Long> drugs = new ArrayList<>();

        for(Long drugId: reservationRepository.getDrugsFromReservation(reservationId)){
            drugs.add(drugId);
        }

        reservationDTO.setDrugs(drugs);

        return reservationDTO;
    }
}
