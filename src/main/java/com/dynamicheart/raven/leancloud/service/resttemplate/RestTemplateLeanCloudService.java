package com.dynamicheart.raven.leancloud.service.resttemplate;

import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.constant.Message;
import com.dynamicheart.raven.leancloud.manager.InstallationManager;
import com.dynamicheart.raven.leancloud.model.installation.InstallationModel;
import com.dynamicheart.raven.leancloud.model.push.PushModel;
import com.dynamicheart.raven.leancloud.model.push.component.PushModelData;
import com.dynamicheart.raven.leancloud.service.LeanCloudService;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.utils.exception.ServiceException;
import com.mongodb.DBObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestTemplateLeanCloudService implements LeanCloudService {

    @Inject
    private RestTemplate restTemplate;

    @Inject
    private InstallationManager installationManager;

    private static HttpHeaders httpHeaders;

    @Override
    public void send(Raven raven, User addresser) throws ServiceException {

        List<String> installationIds = new ArrayList<>();
        raven.getAddresseeIds().forEach(addresseeId -> {
            InstallationModel installationModel = installationManager.get(addresseeId);
            if(installationModel != null){
                installationIds.add(installationModel.getInstallationId());
            }
        });
        DBObject where = PushModel.genWhereQuery(installationIds);

        PushModelData data = new PushModelData();
        data.setAlert(raven.getId());
        data.setTitle(String.format(Message.MESSAGE_TEMPLATE_NEW_RAVEN, addresser.getId()));
        data.setAction(Constants.LEAN_CLOUD_PUSH_ACTION);

        PushModel pushModel = new PushModel();
        pushModel.setPushModelData(data);
        pushModel.setWhere(where);

        HttpEntity<PushModel> entity = new HttpEntity<>(pushModel, getHttpHeaders());
        try {
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(
                    String.format(Constants.LEAN_CLOUD_API_BASE_URL, "/push"),
                    HttpMethod.POST,
                    entity,
                    byte[].class);
        } catch (RestClientException e) {
            throw new ServiceException();
        }
    }

    private static HttpHeaders getHttpHeaders() {
        if (httpHeaders == null) {
            httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.add("X-LC-Id", Constants.LEAN_CLOUD_RAVEN_APP_ID);
            httpHeaders.add("X-LC-Key",
                    String.format("%s,master", Constants.LEAN_CLOUD_RAVEN_MASTER_KEY));
        }
        return httpHeaders;
    }
}
