package com.dynamicheart.raven.services.whistleblowing;

import com.dynamicheart.raven.model.whistleblowing.WhistleBlowing;
import com.dynamicheart.raven.repositories.whistleblowing.WhistleBlowingRepository;
import com.dynamicheart.raven.services.generic.RavenEntityServiceImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class WhistleBlowingServiceImpl extends RavenEntityServiceImpl<String, WhistleBlowing>
        implements WhistleBlowingService {

    private WhistleBlowingRepository whistleBlowingRepository;

    @Inject
    public WhistleBlowingServiceImpl(WhistleBlowingRepository whistleBlowingRepository) {
        super(whistleBlowingRepository);
        this.whistleBlowingRepository = whistleBlowingRepository;
    }

    @Override
    public List<WhistleBlowing> getAll() {
        return whistleBlowingRepository.findAll();
    }
}
