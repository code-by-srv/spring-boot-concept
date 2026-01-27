package com.codingsrv.CascadingAndTransactional;

import com.codingsrv.CascadingAndTransactional.entity.Insurance;
import com.codingsrv.CascadingAndTransactional.entity.Patient;
import com.codingsrv.CascadingAndTransactional.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Test
    public void testInsurance(){
        Insurance insurance = Insurance.builder()
                .policyNumber("Hdfc_123")
                .provider("HDFC")
                .validUntil(LocalDate.of(2027,12,12))
                .build();

        var updatedInsurance = insuranceService.assignInsuranceToPatient(insurance,1L);

    }


}
