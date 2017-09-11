package com.dynamicheart.raven.services.mold;

import com.dynamicheart.raven.model.mold.Mold;
import com.dynamicheart.raven.services.generic.RavenEntityService;

import java.util.List;

public interface MoldService extends RavenEntityService<String, Mold> {
    List<Mold> getAllByTag(String tag);
}
