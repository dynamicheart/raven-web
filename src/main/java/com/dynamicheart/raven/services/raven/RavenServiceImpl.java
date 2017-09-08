package com.dynamicheart.raven.services.raven;

import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.repositories.raven.RavenRepository;
import com.dynamicheart.raven.services.generic.RavenEntityServiceImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service
public class RavenServiceImpl extends RavenEntityServiceImpl<String, Raven>
        implements RavenService {

    private RavenRepository ravenRepository;

    @Inject
    public RavenServiceImpl(RavenRepository ravenRepository) {
        super(ravenRepository);
        this.ravenRepository = ravenRepository;
    }

    @Override
    public List<Raven> findByAddresserId(String addresserId) {
        return ravenRepository.findByAddresserId(addresserId);
    }

    @Override
    public List<Raven> findByAddresseeId(String addresseeId) {
        return ravenRepository.findByAddresseeId(addresseeId);
    }

    @Override
    public List<Raven> findByAddresseeIdAndCreatedDateAfter(String addresseeId, Date date) {
        return ravenRepository.findByAddresseeIdAndCreatedDateAfter(addresseeId, date);
    }
}
