package com.dynamicheart.raven.services.whistleblowing;

import com.dynamicheart.raven.model.whistleblowing.WhistleBlowing;
import com.dynamicheart.raven.services.generic.RavenEntityService;

import java.util.List;

public interface WhistleBlowingService extends RavenEntityService<String, WhistleBlowing>{
    public List<WhistleBlowing> getAll();
}
