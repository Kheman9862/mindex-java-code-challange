package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureImplTest {

    private String reportingStructureURL;
    private String employeeURL;
    private Employee employeeID;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ReportingStructureService resportStructureService;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    @Before
    public void setup() {
        employeeURL = "http://localhost:" + port + "/employee";
        reportingStructureURL = "http://localhost:" + port + "/reporting/{id}";
        employeeID = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }

    @Test
    public void testRead() {
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employeeID);
        reportingStructure.setNumberOfReports(4);

        // Read checks
        ReportingStructure readReporting = restTemplate.getForEntity(reportingStructureURL, ReportingStructure.class, employeeID.getEmployeeId()).getBody();
        assertEquals(employeeID.getEmployeeId(), readReporting.getEmployee().getEmployeeId());
        assertEquals(reportingStructure.getNumberOfReports(),readReporting.getNumberOfReports());
    }

}
