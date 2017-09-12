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

    //TODO:增加新的tag代表成为公开圈子的申请
    @Override
    public List<Serve> getAllHandlingToPublic() {
        return serveRepository.findAllByStatusAndType(Constants.SERVE_STATUS_HANDLING,Constants.SERVE_TYPE_ORDINARY);
    }

    @Override
    public List<Serve> getAllByManId(String manId) {
        return serveRepository.findAllByManId(manId);
    }

    @Override
    public List<Serve> getAllHandingOrdinaryByHouseId(String houseId) {
        return serveRepository.findAllByStatusAndTypeAndHouseId(Constants.SERVE_STATUS_HANDLING,Constants.SERVE_TYPE_ORDINARY,houseId);
    }
}
