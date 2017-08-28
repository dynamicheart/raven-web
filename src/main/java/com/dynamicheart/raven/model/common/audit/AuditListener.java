package com.dynamicheart.raven.model.common.audit;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by dynamicheart on 6/8/2017.
 */
public class AuditListener {

    @PrePersist
    @PreUpdate
    public void onSaveOrUpdate(Object o) {
        if (o instanceof Auditable) {
            Auditable audit = (Auditable) o;
            AuditSection auditSection = audit.getAuditSection();

            auditSection.setDateModified(new Date());
            if (auditSection.getDateCreated() == null) {
                auditSection.setDateCreated(new Date());
            }
            audit.setAuditSection(auditSection);
        }
    }
}
