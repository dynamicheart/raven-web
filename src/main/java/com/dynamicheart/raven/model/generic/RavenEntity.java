package com.dynamicheart.raven.model.generic;

import java.io.Serializable;

public abstract class RavenEntity<K extends Serializable & Comparable<K>, E extends RavenEntity<K, ?>>
        implements Serializable {

    public abstract K getId();

    public abstract void setId(K id);

    public boolean isNew() {
        return getId() == null;
    }
}
