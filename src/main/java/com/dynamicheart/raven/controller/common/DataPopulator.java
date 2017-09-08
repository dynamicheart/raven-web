package com.dynamicheart.raven.controller.common;

import com.dynamicheart.raven.utils.exception.ConversionException;

public interface DataPopulator<Source, Target> {

    public Target populate(Source source,Target target) throws ConversionException;
    public Target populate(Source source) throws ConversionException;
}
