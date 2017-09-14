package com.dynamicheart.raven.controller.app.serve.field;

import com.dynamicheart.raven.controller.common.field.HouseRefFields;
import com.dynamicheart.raven.controller.common.field.UserRefFields;
import com.dynamicheart.raven.model.serve.Serve;

public class ServeInfoFields extends Serve {
    private UserRefFields man;
    private HouseRefFields house;

    public UserRefFields getMan() {
        return man;
    }

    public void setMan(UserRefFields man) {
        this.man = man;
    }

    public HouseRefFields getHouse() {
        return house;
    }

    public void setHouse(HouseRefFields house) {
        this.house = house;
    }
}
