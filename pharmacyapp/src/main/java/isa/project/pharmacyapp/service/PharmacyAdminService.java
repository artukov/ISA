package isa.project.pharmacyapp.service;


import isa.project.pharmacyapp.dto.PharmacyAdminDTO;

import java.util.List;

public interface PharmacyAdminService {

    /**
     * Return DTO of PharmacyAdmin found in the database, based on the input parameter id
     * @param id
     * */
    public PharmacyAdminDTO findById(Long id);
    /**
     * Returns List of PharmacyAdminDTOs based on the pharmacy's id
     * @param pharmacyId
     * */
    public List<PharmacyAdminDTO> findAdminsOfPharmacy(Long pharmacyId);
    /**
     *  Creates new PharmacyAdmin and saves in the database
     * @param adminDTO
     * */
    public void createNewPharmacyAdmin(PharmacyAdminDTO adminDTO) throws Exception;
    /**
     * Modifies with new data PharmacyAdmin in the database
     * @param adminId
     * @param adminDTO */
    public void modifyPharmacyAdmin(Long adminId, PharmacyAdminDTO adminDTO) throws Exception;

    /**
     * Deletes PharmacyAdmin from the database
     * @param adminId */
    public void deletePharmacyAdmin(Long adminId) throws Exception;


}
