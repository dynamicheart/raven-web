package com.dynamicheart.raven.services.generic;

import com.dynamicheart.raven.model.generic.RavenEntity;
import com.dynamicheart.raven.utils.exception.ServiceException;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class RavenEntityServiceImpl<K extends Serializable & Comparable<K>, E extends RavenEntity<K, ?>>
        implements RavenEntityService<K, E> {

    private Class<E> objectClass;

    private MongoRepository<E, K> repository;

    @SuppressWarnings("unchecked")
    public RavenEntityServiceImpl(MongoRepository<E, K> repository) {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.objectClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
        this.repository = repository;
    }

    protected final Class<E> getObjectClass() {
        return objectClass;
    }

    @Override
    public E getById(K id) {
        return repository.findOne(id);
    }

    @Override
    public E save(E entity) throws ServiceException {
        return repository.save(entity);
    }

    @Override
    public E create(E entity) throws ServiceException {
        return save(entity);
    }

    @Override
    public E update(E entity) throws ServiceException {
        return save(entity);
    }

    @Override
    public void delete(E entity) throws ServiceException {
        repository.delete(entity);
    }

    @Override
    public List<E> list() {
        return repository.findAll();
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public Boolean exists(K id) {
        return repository.exists(id);
    }
}
