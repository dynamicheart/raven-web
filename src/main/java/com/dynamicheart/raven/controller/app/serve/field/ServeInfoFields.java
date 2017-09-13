package com.dynamicheart.raven.controller.app.serve.field;

import com.dynamicheart.raven.controller.common.field.HouseRefFields;
import com.dynamicheart.raven.controller.common.field.UserRefFields;
import com.dynamicheart.raven.model.serve.Serve;

public class ServeInfoFields extends Serve {
    private UserRefFields userRefFields;
    private HouseRefFields houseRefFields;

    public UserRefFields getUserRefFields() {
        return userRefFields;
    }

    public void setUserRefFields(UserRefFields userRefFields) {
        this.userRefFields = userRefFields;
    }

    public HouseRefFields getHouseRefFields() {
        return houseRefFields;
    }

    public void setHouseRefFields(HouseRefFields houseRefFields) {
        this.houseRefFields = houseRefFields;
    }
}
