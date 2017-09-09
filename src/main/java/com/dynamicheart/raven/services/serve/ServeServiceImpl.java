package com.dynamicheart.raven.services.serve;

import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.model.serve.Serve;
import com.dynamicheart.raven.repositories.serve.ServeRepository;
import com.dynamicheart.raven.services.generic.RavenEntityServiceImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ServeServiceImpl extends RavenEntityServiceImpl<String, Serve>
        implements ServeService {

    private ServeRepository serveRepository;

    @Inject
    public ServeServiceImpl(ServeRepository serveRepository) {
        super(serveRepository);
        this.serveRepository = serveRepository;
    }

    @Override
    public List<Serve> getAll() {
        return serveRepository.findAll();
    }

    @Override
    public List<Serve> getAllHandling() {
        return serveRepository.findAllByStatus(Constants.SERVE_STATUS_HANDLING);
    }
}
