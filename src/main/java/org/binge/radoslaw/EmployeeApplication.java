package org.binge.radoslaw;

import org.binge.radoslaw.DAO.Dao;
import org.binge.radoslaw.model.Employees;

import java.util.List;
import java.util.Optional;

public class EmployeeApplication {

    private static Dao<Employees> employeesDao;

    // standard constructors

    public static void main(String[] args) {
//        Employees employee1 = getEmployee(30);
//        System.out.println(employee1);
//        updateEmployee(employee1, new String[]{"Bruce", "Lee"});
        saveEmployee(new Employees("Monica", "CeCcoco"));
//        deleteEmployee(getEmployee(31));
//        getAllEmployees().forEach(employee -> System.out.println(employee.getFirstName()));
    }

    public static Employees getEmployee(long id) {
        Optional<Employees> employee = employeesDao.get(id);

        return employee.orElseGet(
                () -> new Employees("non-existing firstName", "non-existing lastName"));
    }

    public static List<Employees> getAllEmployees() {
        return employeesDao.getAll();
    }

    public static void updateEmployee(Employees employee, String[] params) {
        employeesDao.update(employee, params);
    }

    public static void saveEmployee(Employees employee) {
        employeesDao.save(employee);
    }

    public static void deleteEmployee(Employees employee) {
        employeesDao.delete(employee);
    }

}
