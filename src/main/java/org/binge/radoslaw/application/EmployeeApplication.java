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
    private static String unitName = "BASE_1";


    public static void main(String[] args) {

        List<String> unitList = new ArrayList<String>();
        unitList.add("BASE_1");
        unitList.add("BASE_2");

        for (String unitName : unitList) {

            Employee employee1 = new Employee();

            if (unitName.equals("BASE_1")) {
                employee1 = getEmployee(30);
            } else {
                employee1 = getEmployee(32);
            }

            System.out.println(employee1);
            updateEmployee(employee1, new String[]{"John", "Rambo"});
            saveEmployee(new Employee("Monica", "Decoco", "decmon"));
            deleteEmployee(getEmployee(31));
            getAllEmployees().forEach(employee ->
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

    private static class JpaEmployeeDataHolder {
        private static final EmployeeDao employeeDao
                = new EmployeeDao(new JpaEntityManagerFactory().getEntityManager(unitName));
    }

    public static Dao getJpaEmployeeDao() {
        return JpaEmployeeDataHolder.employeeDao;
    }

    public static Employee getEmployee(long id) {
        Optional<Employee> employee = getJpaEmployeeDao().get(id);
        return employee.orElseGet(()-> {return new Employee("non-existing firstName"
                , "non-existing lastName"
                ,"non-existing login");
        });
    }

    public static List<Employee> getAllEmployees() {
        return getJpaEmployeeDao().getAll();
    }

    public static void updateEmployee(Employee employee, String[] params){
        getJpaEmployeeDao().update(employee, params);
    }

    public static void saveEmployee(Employee employee) {
        getJpaEmployeeDao().save(employee);
    }

    public static void deleteEmployee(Employee employee) {
        getJpaEmployeeDao().delete(employee);
    }

}
