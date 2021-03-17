package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService{

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     *
     * This is the read method to obtain the total number of reports
     * received by an employee. In this first employee and total
     * number of reports will be extracted for the given employeeId.
     * Then both the values obtained will set both the properties.
     * @param employeeId:
     * @return reportingStructure: This will return back the reporting structure model with values set in them.
     *
     * */

    @Override
    public ReportingStructure read(String employeeId) {
        LOG.debug("EmployeeId with the given ReportingStructure is : [{}]", employeeId);

        Employee employee = employeeService.read(employeeId);
        int totalNumberOfReports = employeeService.totalNumberOfReports(employeeId);

        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(totalNumberOfReports);
        return reportingStructure;
    }
}
