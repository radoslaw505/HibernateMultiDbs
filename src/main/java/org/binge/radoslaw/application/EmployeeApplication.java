package org.binge.radoslaw.application;

import org.binge.radoslaw.config.JpaEntityManagerFactory;
import org.binge.radoslaw.dao.Dao;
import org.binge.radoslaw.dao.EmployeeDao;
import org.binge.radoslaw.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeApplication {

    private static EmployeeDao employeeDao;


    public static void main(String[] args) {

        List<String> unitList = new ArrayList<String>();
        unitList.add("BASE_1");
        unitList.add("BASE_2");

        for (String unitName : unitList) {

            EmployeeDao employeeDao
                    = new EmployeeDao(new JpaEntityManagerFactory().getEntityManager(unitName));

            Employee employee1;

            if (unitName.equals("BASE_1")) {
                employee1 = getEmployee(employeeDao, 30);
            } else {
                employee1 = getEmployee(employeeDao, 32);
            }

            System.out.println(employee1);
            updateEmployee(employeeDao, employee1, new String[]{"John", "Rambo"});
            saveEmployee(employeeDao, new Employee("Monica", "Decoco", "decmon"));
            deleteEmployee(employeeDao, getEmployee(employeeDao, 31));
            getAllEmployees(employeeDao).forEach(employee ->
                    System.out.printf("%4d %15s %15s %6s %10s%n"
                            , employee.getId()
                            , employee.getFirstName()
                            , employee.getLastName()
                            , employee.getLogin()
                            , employee.getCurrentOn()
                    )
            );
        }
    }


    public static Employee getEmployee(EmployeeDao employeeDao, long id) {
        Optional<Employee> employee = employeeDao.get(id);
        return employee.orElseGet(()-> {return new Employee("non-existing firstName"
                , "non-existing lastName"
                ,"non-existing login");
        });
    }

    public static List<Employee> getAllEmployees(EmployeeDao employeeDao) {
        return employeeDao.getAll();
    }

    public static void updateEmployee(EmployeeDao employeeDao, Employee employee, String[] params){
        employeeDao.update(employee, params);
    }

    public static void saveEmployee(EmployeeDao employeeDao, Employee employee) {
        employeeDao.save(employee);
    }

    public static void deleteEmployee(EmployeeDao employeeDao, Employee employee) {
        employeeDao.delete(employee);
    }

}
