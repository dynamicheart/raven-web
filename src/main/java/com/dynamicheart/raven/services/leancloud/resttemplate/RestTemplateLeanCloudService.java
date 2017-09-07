package com.dynamicheart.raven.services.leancloud.resttemplate;

import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.model.leancloud.installation.LeanCloudInstallation;
import com.dynamicheart.raven.model.leancloud.push.LeanCloudPush;
import com.dynamicheart.raven.model.leancloud.LeanCloudResponse;
import com.dynamicheart.raven.services.leancloud.LeanCloudService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

@Service
public class RestTemplateLeanCloudService implements LeanCloudService {

    @Inject
    private RestTemplate restTemplate;

    private HttpHeaders httpHeaders;

    @Override
    public LeanCloudResponse push(LeanCloudPush push) {
        HttpEntity<LeanCloudPush> entity = new HttpEntity<>(push, getHttpHeaders());
        return restTemplate.postForObject(String.format(Constants.LEAN_CLOUD_API_TEMPLATE, "/push"), entity, LeanCloudResponse.class);
    }

    @Override
    public LeanCloudResponse saveInstallation(LeanCloudInstallation installation) {
        HttpEntity<LeanCloudInstallation> entity = new HttpEntity<>(installation, getHttpHeaders());
        return restTemplate.postForObject(String.format(Constants.LEAN_CLOUD_API_TEMPLATE, "/installations"), entity, LeanCloudResponse.class);
    }

    private HttpHeaders getHttpHeaders() {
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
