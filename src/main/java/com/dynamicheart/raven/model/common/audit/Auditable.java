package com.dynamicheart.raven.model.common.audit;

/**
 * Created by dynamicheart on 6/8/2017.
 */
public interface Auditable {
    AuditSection getAuditSection();

    void setAuditSection(AuditSection audit);
}
