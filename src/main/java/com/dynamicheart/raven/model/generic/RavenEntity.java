package com.dynamicheart.raven.model.generic;

import java.io.Serializable;

public abstract class RavenEntity<K extends Serializable & Comparable<K>, E extends RavenEntity<K, ?>>
        implements Serializable, Comparable<E> {

    public abstract K getId();

    public abstract void setId(K id);

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object object) {
        if(object == null){
            return false;
        }

        if(object == this){
            return false;
        }

        if (!(object instanceof RavenEntity)){
            return false;
        }

        RavenEntity<K, E> entity = (RavenEntity<K, E>) object;
        K id = getId();

        return id != null && id.equals(entity.getId());
    }

    @Override
    public int compareTo(E o) {
        if (this == o){
            return 0;
        }
        return this.getId().compareTo(o.getId());
    }
}
