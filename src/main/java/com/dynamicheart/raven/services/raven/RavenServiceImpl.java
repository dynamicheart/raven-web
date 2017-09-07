package com.dynamicheart.raven.services.raven;

import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.repositories.raven.RavenRepository;
import com.dynamicheart.raven.services.generic.RavenEntityServiceImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class RavenServiceImpl extends RavenEntityServiceImpl<String, Raven>
        implements RavenService {

    private RavenRepository ravenRepository;

    @Inject
    public RavenServiceImpl(RavenRepository ravenRepository) {
        super(ravenRepository);
        this.ravenRepository = ravenRepository;
    }
}
