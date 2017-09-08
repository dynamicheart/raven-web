package com.dynamicheart.raven.leancloud.service.resttemplate;

import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.leancloud.model.installation.LeanCloudInstallation;
import com.dynamicheart.raven.leancloud.model.push.LeanCloudPush;
import com.dynamicheart.raven.leancloud.model.response.LeanCloudResponse;
import com.dynamicheart.raven.leancloud.service.LeanCloudService;
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

    private static HttpHeaders httpHeaders;

    @Override
    public LeanCloudResponse push(LeanCloudPush push) {
        HttpEntity<LeanCloudPush> entity = new HttpEntity<>(push, getHttpHeaders());
        return restTemplate.postForObject(String.format(Constants.LEAN_CLOUD_API_BASE_URL, "/push"), entity, LeanCloudResponse.class);
    }

    @Override
    public LeanCloudResponse saveInstallationInRemote(LeanCloudInstallation installation) {
        HttpEntity<LeanCloudInstallation> entity = new HttpEntity<>(installation, getHttpHeaders());
        return restTemplate.postForObject(String.format(Constants.LEAN_CLOUD_API_BASE_URL, "/installations"), entity, LeanCloudResponse.class);
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
