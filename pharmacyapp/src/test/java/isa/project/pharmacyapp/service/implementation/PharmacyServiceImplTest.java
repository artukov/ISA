package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.PharmacyDTO;
import isa.project.pharmacyapp.model.Address;
import isa.project.pharmacyapp.model.Calendar;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.repository.PharmacyRepository;
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
import static org.mockito.Mockito.verify;


@SpringBootTest
class PharmacyServiceImplTest {

    @Mock
    private PharmacyRepository pharmacyRepository;

    @InjectMocks
    private PharmacyServiceImpl pharmacyService;

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
}