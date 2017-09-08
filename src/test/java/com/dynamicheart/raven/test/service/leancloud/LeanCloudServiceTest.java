package com.dynamicheart.raven.test.service.leancloud;

import com.dynamicheart.raven.application.ApplicationConfiguration;
import com.dynamicheart.raven.model.leancloud.LeanCloudResponse;
import com.dynamicheart.raven.model.leancloud.push.LeanCloudPush;
import com.dynamicheart.raven.model.leancloud.push.LeanCloudPushData;
import com.dynamicheart.raven.services.leancloud.LeanCloudService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)
public class LeanCloudServiceTest {
    @Inject
    private LeanCloudService leanCloudService;

    @Test
    public void pushTest(){
        LeanCloudPushData data = new LeanCloudPushData();
        data.setAlert("hello");
        LeanCloudPush push = new LeanCloudPush();
        push.setData(data);
        LeanCloudResponse response = leanCloudService.push(push);
        System.out.print(response);
    }
}
