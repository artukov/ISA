package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.dto.DrugDTO;
import isa.project.pharmacyapp.model.Drug;
import isa.project.pharmacyapp.model.DrugSpecification;
import isa.project.pharmacyapp.model.Pharmacy;
import isa.project.pharmacyapp.repository.DrugRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class DrugServiceImplTest {

    @Mock
    public DrugRepository drugRepository;

    @InjectMocks
    public DrugServiceImpl drugService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnDTOListOfDrugs() {

        final Long pharmacyId = 200L;
        DrugSpecification drugSpecification = new DrugSpecification();
        drugSpecification.setId(pharmacyId);

        ArrayList<Drug> drugs = new ArrayList<>();
        for(Long i = 0L; i < 10L; i++){
            Drug temp = new Drug();
            temp.setId(i);
            temp.setDrugSpecification(drugSpecification);
            drugs.add(temp);
        }

        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(pharmacyId);
        pharmacy.setDrug(drugs);

        given(drugRepository.findAllByPharmacyId(pharmacyId)).willReturn(drugs);

        ArrayList<DrugDTO> drugDTOS = (ArrayList<DrugDTO>) drugService.findAllPharmacyDrugs(pharmacyId);

        assertNotEquals(null, drugDTOS);
        assertEquals(drugs.size(),drugDTOS.size());

        for(int i = 0; i < drugDTOS.size(); i++){
            assertEquals(drugs.get(i).getId(), drugDTOS.get(i).getId());
        }

    }

}