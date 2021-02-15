package org.binge.radoslaw.DAO;

import org.binge.radoslaw.model.Employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class EmployeesDao implements Dao<Employees> {

    private final EntityManager entityManager;

    public EmployeesDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // standard constructors

    @Override
    public Optional<Employees> get(long id) {
        return Optional.ofNullable(entityManager.find(Employees.class, id));
    }

    @Override
    public List<Employees> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Employees2 e");
        return query.getResultList();
    }

    @Override
    public void save(Employees employee) {
        executeInsideTransaction(entityManager -> entityManager.persist(employee));
    }

    @Override
    public void update(Employees employee, String[] params) {
        employee.setFirstName(Objects.requireNonNull(params[0], "Name cannot be null"));
        employee.setLastName(Objects.requireNonNull(params[1], "Email cannot be null"));
        executeInsideTransaction(entityManager -> entityManager.merge(employee));
    }

    @Override
    public void delete(Employees employee) {
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

