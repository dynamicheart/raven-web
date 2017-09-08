package com.dynamicheart.raven.test.model;

import com.dynamicheart.raven.leancloud.model.push.PushModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PushModelTest {

    @Test
    public void genWhereQueryTest(){
        List<String> installationIds = new ArrayList<>();
        installationIds.add("!");
        installationIds.add("2");
        installationIds.add("3");
        System.out.print(PushModel.genWhereQuery(installationIds));
    }
}
