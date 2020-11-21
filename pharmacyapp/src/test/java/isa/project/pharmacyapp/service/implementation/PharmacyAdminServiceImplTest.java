package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.PharmacyAdminDTO;
import isa.project.pharmacyapp.model.Address;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.model.PharmacyAdmin;
import isa.project.pharmacyapp.repository.AddressRepository;
import isa.project.pharmacyapp.repository.PharmacyAdminRepository;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.service.PharmacyAdminService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class PharmacyAdminServiceImplTest {

    @Mock
    private PharmacyAdminRepository adminRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private PharmacyRepository pharmacyRepository;

    @Mock
    private PharmacyAdminService adminService;

    @InjectMocks
    private PharmacyAdminServiceImpl adminImplService;

    @Before
    public void beforeAllTest(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldNotReturnNullForDTO() {

        final Long id = 200L;
        PharmacyAdmin admin = new PharmacyAdmin();
        admin.setId(id);
        admin.setEmail("test_email");
        admin.setPassword("test_password");
        admin.setFirstname("test_firstname");
        admin.setLastname("test_lastname");
        admin.setPhoneNumber("test_phoneNumber");

        Address address = new Address();
        address.setId(id);

        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(id);

        admin.setAddress(address);
        admin.setPharmacy(pharmacy);

        //defines what will return call of the adminRepository.findById(id) function
        given(adminRepository.findById(id)).willReturn(Optional.of(admin));
        given(addressRepository.findById(id)).willReturn(Optional.of(address));
        given(pharmacyRepository.findById(id)).willReturn(Optional.of(pharmacy));

        PharmacyAdminDTO expected  = adminImplService.findById(id);

        assertNotEquals(null,expected);

    }


}