package com.mindex.challenge.data;

// This one saves the data but temporarily it will have to post everytime the program reruns.

public class Compensation {

    private Employee employee;
    private int salary;
    private String effectiveDate;


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

}
