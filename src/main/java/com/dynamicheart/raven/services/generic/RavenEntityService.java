package com.dynamicheart.raven.services.generic;

import com.dynamicheart.raven.model.generic.RavenEntity;
import com.dynamicheart.raven.utils.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

public interface RavenEntityService<K extends Serializable & Comparable<K>, E extends RavenEntity<K, ?>> {

    void save(E entity) throws ServiceException;

    void update(E entity) throws ServiceException;

    void create(E entity) throws ServiceException;

    void delete(E entity) throws ServiceException;

    E getById(K id);

    List<E> list();

    Long count();
}
