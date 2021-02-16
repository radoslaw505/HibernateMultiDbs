package org.binge.radoslaw.dao;

import org.binge.radoslaw.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class EmployeeDao implements Dao<Employee> {


    private final EntityManager entityManager;

    public EmployeeDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // standard constructors

    @Override
    public Optional<Employee> get(long id) {
        return Optional.ofNullable(entityManager.find(Employee.class, id));
    }

    @Override
    public List<Employee> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Employee e");
        return query.getResultList();
    }

    @Override
    public void save(Employee employee) {
        executeInsideTransaction(entityManager -> entityManager.persist(employee));
    }

    @Override
    public void update(Employee employee, String[] params) {
        employee.setFirstName(Objects.requireNonNull(params[0], "Name cannot be null"));
        employee.setLastName(Objects.requireNonNull(params[1], "Email cannot be null"));
        executeInsideTransaction(entityManager -> entityManager.merge(employee));
    }

    @Override
    public void delete(Employee employee) {
        executeInsideTransaction(entityManager -> entityManager.remove(employee));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}

