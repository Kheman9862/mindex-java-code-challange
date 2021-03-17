package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationImplTest {

    private String employeeURL;
    private String createCompensationURL;
    private String readCompensationURL;
    private Employee employeeID;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationService compensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Before
    public void setup() {
        employeeURL = "http://localhost:" + port + "/employee";
        createCompensationURL = "http://localhost:" + port + "/compensation/create";
        readCompensationURL = "http://localhost:" + port + "/compensation/read/{id}";
        employeeID = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }


    @Test
    public void testCreateRead() {
        Compensation newTestCompensation = new Compensation();
        newTestCompensation.setEmployee(employeeID);
        newTestCompensation.setSalary(700000);
        newTestCompensation.setEffectiveDate("2021-03-16");

        // Create checks
        Compensation createdCompensation = restTemplate.postForEntity(createCompensationURL, newTestCompensation, Compensation.class).getBody();

        assertNotNull(createdCompensation.getEmployee());
        assertCompensationEquivalence(newTestCompensation, createdCompensation);


        // Read checks
        Compensation readCompensation = restTemplate.getForEntity(readCompensationURL, Compensation.class, createdCompensation.getEmployee().getEmployeeId()).getBody();
        assertEquals(employeeID.getEmployeeId(), createdCompensation.getEmployee().getEmployeeId());
        assertEquals(readCompensation.getSalary(),newTestCompensation.getSalary());
    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
        assertEquals(expected.getSalary(), actual.getSalary());
    }
}
