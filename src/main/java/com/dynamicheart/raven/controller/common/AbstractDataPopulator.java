package com.dynamicheart.raven.controller.common;

import com.dynamicheart.raven.utils.exception.ConversionException;

public abstract class AbstractDataPopulator<Source, Target> implements DataPopulator<Source, Target>{

    @Override
    public Target populate(Source source) throws ConversionException {
        return populate(source, createTarget());
    }

    protected abstract Target createTarget();
}
