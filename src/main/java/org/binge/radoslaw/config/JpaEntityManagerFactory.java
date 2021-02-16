package org.binge.radoslaw.config;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JpaEntityManagerFactory {

    public EntityManager getEntityManager(String unitName) {
        return Persistence.createEntityManagerFactory(unitName).createEntityManager();
    }

}
