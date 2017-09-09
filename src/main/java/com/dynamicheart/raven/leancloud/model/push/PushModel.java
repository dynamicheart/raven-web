package com.dynamicheart.raven.leancloud.model.push;

import com.dynamicheart.raven.leancloud.model.push.component.PushModelData;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class PushModel implements Serializable{

    private static final long serialVersionUID = 1699941338653415619L;

    private static final String INSTALLATION_KEY = "installationId";

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("where")
    private DBObject where;

    @JsonProperty("pushModelData")
    private PushModelData pushModelData;

    public DBObject getWhere() {
        return where;
    }

    public void setWhere(DBObject where) {
        this.where = where;
    }

    public PushModelData getPushModelData() {
        return pushModelData;
    }

    public void setPushModelData(PushModelData pushModelData) {
        this.pushModelData = pushModelData;
    }

    public static DBObject genWhereQuery(@NotNull List<String> installationIds){
        return  QueryBuilder
                .start(INSTALLATION_KEY)
                .in(installationIds)
                .get();
    }
}
