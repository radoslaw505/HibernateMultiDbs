package org.binge.radoslaw;


import org.binge.radoslaw.model.Employees;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class App {

    public static void main( String[] args ) {

        EntityManager em = Persistence
                .createEntityManagerFactory("BAZA_1")
                .createEntityManager();

        em.getTransaction().begin();

        Employees employee = new Employees();
        employee.setFirstName("John");
        employee.setLastName("Rambo");
        employee.setLogin("ramjoh");
        System.out.println(employee.getFirstName());

        em.persist(employee);
        em.getTransaction().commit();

    }

}
