package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.PharmacyDTO;
import isa.project.pharmacyapp.model.Address;
import isa.project.pharmacyapp.model.Calendar;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.repository.AddressRepository;
import isa.project.pharmacyapp.repository.CalendarRepository;
import isa.project.pharmacyapp.repository.PharmacyRepository;
import isa.project.pharmacyapp.service.PharmacyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest
class PharmacyServiceImplTest {

    @Mock
    private PharmacyRepository pharmacyRepository;

    @InjectMocks
    private PharmacyServiceImpl pharmacyService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private CalendarRepository calendarRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldNotReturnNullDtoForId() {

        final Long id = 300L;
        final Double[] ratings = {1.5, 3.75, 4.0};
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(id);
        pharmacy.setName("test_name");
        pharmacy.setDescription("test_desc");
        pharmacy.setRatings(Arrays.asList(ratings));

        Address address = new Address();
        address.setId(id);
        Calendar calendar = new Calendar();
        calendar.setId(id);

        pharmacy.setAddress(address);
        pharmacy.setCalendar(calendar);

        given(pharmacyRepository.findById(id)).willReturn(Optional.of(pharmacy));

        PharmacyDTO pharmacyDTO = pharmacyService.findById(id);

        assertNotEquals(null, pharmacyDTO);
        verify(pharmacyRepository, Mockito.times(1)).findById(id);

    }

    @Test
    void shouldCreateNewPharmacyWithPassedDTO(){

        final Long id = 200L;
        final Double[] ratings = {1.5, 3.75, 4.0};
        PharmacyDTO dto = new PharmacyDTO(id,"test_name","test_description",Arrays.asList(ratings),id,id);

        Address address = new Address();
        address.setId(id);

        Calendar calendar = new Calendar();
        calendar.setId(id);

        given(addressRepository.findById(id)).willReturn(Optional.of(address));
        given(calendarRepository.findById(id)).willReturn(Optional.of(calendar));

        try {
            pharmacyService.createNewPharmacy(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Pharmacy pharmacy = pharmacyRepository.findById(id).orElse(null);


        assertNull(pharmacy);

        verify(addressRepository, times(1)).findById(id);
        //verify(pharmacyRepository, times(1)).save(pharmacy);


    }

    @Test
    void shouldModifyPharmacyWithPassedDTO(){
        final Long id = 200L;
        final Double[] ratings = {1.5, 3.75, 4.0};
        PharmacyDTO dto = new PharmacyDTO(id,"test_name","test_description",Arrays.asList(ratings),id,id);
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(id);

        Address address = new Address();
        address.setId(id);

        Calendar calendar = new Calendar();
        calendar.setId(id);

        given(addressRepository.findById(id)).willReturn(Optional.of(address));
        given(calendarRepository.findById(id)).willReturn(Optional.of(calendar));
        given(pharmacyRepository.findById(id)).willReturn(Optional.of(pharmacy));

        try {
            pharmacyService.modifyPharmacy(id, dto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(pharmacy.getName(),dto.getName());
        assertEquals(pharmacy.getRatings().get(0), 1.5);
        assertEquals(pharmacy.getAddress().getId(), address.getId());
        assertEquals(pharmacy.getCalendar().getId(), calendar.getId());
        verify(pharmacyRepository, times(1)).save(pharmacy);

    }

    @Test
    void shouldDeletePharmacyByPassedID(){
        final Long id = 200L;
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(id);

        Address address = new Address();
        address.setId(id);

        Calendar calendar = new Calendar();
        calendar.setId(id);

        pharmacy.setCalendar(calendar);
        pharmacy.setAddress(address);

        given(pharmacyRepository.findById(id)).willReturn(Optional.of(pharmacy));

        try {
            pharmacyService.deletePharmacy(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(pharmacyRepository, times(1)).deleteById(id);

    }
}