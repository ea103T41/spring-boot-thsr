package com.euphy.learn.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenericDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Finds an entity by its ID.
     *
     * @param clazz the class of the entity
     * @param id the ID of the entity
     * @param <RESULT> the type of the entity
     * @return the found entity or null if not found
     */
    public <RESULT> RESULT findById(Class<RESULT> clazz, long id) {
        LOGGER.info("Finding entity of class {} with id {}", clazz.getName(), id);
        try {
            return entityManager.find(clazz, id);
        } catch (Exception e) {
            LOGGER.error("Error finding entity of class {} with id {}", clazz.getName(), id, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds all entities of the specified class.
     *
     * @param clazz the class of the entities
     * @param <RESULT> the type of the entities
     * @return a list of all found entities
     */
    public <RESULT> List<RESULT> findAll(Class<RESULT> clazz) {
        LOGGER.info("Finding all entities of class {}", clazz.getName());
        try {
            return entityManager
                    .createQuery("from " + clazz.getName(), clazz)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.error("Error finding all entities of class {}", clazz.getName(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves an entity.
     *
     * @param entity the entity to save
     * @param <RESULT> the type of the entity
     */
    public <RESULT> void save(RESULT entity) {
        LOGGER.info("Saving entity of class {}", entity.getClass().getName());
        try {
            entityManager.persist(entity);
        } catch (Exception e) {
            LOGGER.error("Error saving entity of class {}", entity.getClass().getName(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates an entity.
     *
     * @param entity the entity to update
     * @param <RESULT> the type of the entity
     * @return the updated entity
     */
    public <RESULT> RESULT update(RESULT entity) {
        LOGGER.info("Updating entity of class {}", entity.getClass().getName());
        try {
            return entityManager.merge(entity);
        } catch (Exception e) {
            LOGGER.error("Error updating entity of class {}", entity.getClass().getName(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes an entity by its ID.
     *
     * @param clazz the class of the entity
     * @param entityId the ID of the entity
     * @param <RESULT> the type of the entity
     */
    public <RESULT> void deleteById(Class<RESULT> clazz, long entityId) {
        LOGGER.info("Deleting entity of class {} with id {}", clazz.getName(), entityId);
        try {
            entityManager.createQuery("delete from " + clazz.getName() + " where id = :entityId")
                    .setParameter("entityId", entityId)
                    .executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error deleting entity of class {} with id {}", clazz.getName(), entityId, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes all entities of the specified class.
     *
     * @param clazz the class of the entities
     * @param <RESULT> the type of the entities
     */
    public <RESULT> void deleteAll(Class<RESULT> clazz) {
        LOGGER.info("Deleting all entities of class {}", clazz.getName());
        try {
            entityManager.createQuery("delete from " + clazz.getName()).executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error deleting all entities of class {}", clazz.getName(), e);
            throw new RuntimeException(e);
        }
    }

}
