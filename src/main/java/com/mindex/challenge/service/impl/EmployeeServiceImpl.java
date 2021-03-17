package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    /**
     *
     * Obtaining total number of Reports given to a parent Employee
     * by counting all the child nodes using recursion.
     * @param EmployeeId: This is the id for which it will return the sum of nodes present.
     * @return sum: This will give the total number of reports given to the head Employee.
     *
     * */

    @Override
    public int totalNumberOfReports(String EmployeeId) {
        int sum = 0;
        Employee employee = this.read(EmployeeId);
        List<Employee> reports = employee.getDirectReports();
        if (reports != null) {
            for (int i=0;i<reports.size();i++) {
                sum += (totalNumberOfReports(reports.get(i).getEmployeeId())+1);
            }
        }
        return sum;
    }
}
