package com.dynamicheart.raven.services.mold;

import com.dynamicheart.raven.model.mold.Mold;
import com.dynamicheart.raven.repositories.mold.MoldRepository;
import com.dynamicheart.raven.services.generic.RavenEntityServiceImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class MoldServiceImpl extends RavenEntityServiceImpl<String, Mold>
        implements MoldService {

    private MoldRepository moldRepository;

    @Inject
    public MoldServiceImpl(MoldRepository moldRepository) {
        super(moldRepository);
        this.moldRepository = moldRepository;
    }

    @Override
    public List<Mold> getAllByTag(String tag) {
        return moldRepository.findAllByTagsContains(tag);
    }
}
