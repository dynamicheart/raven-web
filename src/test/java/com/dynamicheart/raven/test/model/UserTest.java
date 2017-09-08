package com.dynamicheart.raven.test.model;

import com.dynamicheart.raven.model.generic.RavenEntity;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.model.user.User;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;

public class UserTest{

    @Test
    public void testIsInstanceOf(){
        Raven raven1 = new Raven();
        raven1.setId("1");
        String raven2 = "";
        Raven raven3 = null;
        User raven4 = new User();
        raven4.setId("1");
        System.out.print(raven1.equals(raven2));
        System.out.print(raven1.equals(raven3));
        System.out.print(raven1.equals(raven4));
    }
}
