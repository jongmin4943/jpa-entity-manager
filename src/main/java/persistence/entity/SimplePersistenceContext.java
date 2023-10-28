package persistence.entity;

import persistence.exception.PersistenceException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SimplePersistenceContext implements PersistenceContext {

    private final Map<EntityKey, Object> entities;
    private final EntitySnapshots entitySnapshots;
    private final EntityEntries entityEntries;

    public SimplePersistenceContext() {
        this.entities = new HashMap<>();
        this.entitySnapshots = new EntitySnapshots();
        this.entityEntries = new EntityEntries();
    }

    @Override
    public Optional<Object> getEntity(final EntityKey key) {
        return Optional.ofNullable(entities.get(key));
    }

    @Override
    public void addEntity(final EntityKey key, final Object entity) {
        entities.put(key, entity);
    }

    @Override
    public void removeEntity(final EntityKey key) {
        entities.remove(key);
    }

    @Override
    public boolean hasEntity(final EntityKey key) {
        return entities.containsKey(key);
    }

    @Override
    public Object getDatabaseSnapshot(final EntityKey key, final Object entity) {
        return entitySnapshots.getDatabaseSnapshot(key, entity);
    }

    @Override
    public void addEntityEntry(final EntityKey key, final Status status) {
        entityEntries.addEntityEntry(key, status);
    }

    @Override
    public Optional<EntityEntry> getEntityEntry(final EntityKey key) {
        return entityEntries.getEntityEntry(key);
    }

    @Override
    public void updateEntityEntryStatus(final EntityKey entityKey, final Status status) {
        final EntityEntry entityEntry = getEntityEntry(entityKey)
                .orElseThrow(() -> new PersistenceException("PersistenceContext 내에 관리되고 있지 않은 Entity 입니다."));
        entityEntry.updateStatus(status);
    }
}
