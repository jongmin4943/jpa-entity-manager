package persistence.entity;

import java.util.Optional;

public interface PersistenceContext {
    Optional<Object> getEntity(EntityKey key);

    void addEntity(EntityKey key, Object entity);

    void removeEntity(EntityKey key);

    boolean hasEntity(EntityKey key);

    Object getDatabaseSnapshot(EntityKey key, Object entity);

    Object getCachedDatabaseSnapshot(EntityKey key);
}
