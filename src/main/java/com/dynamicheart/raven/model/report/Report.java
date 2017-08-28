package com.dynamicheart.raven.model.report;


import com.dynamicheart.raven.constants.Constants;
import com.dynamicheart.raven.model.common.audit.AuditListener;
import com.dynamicheart.raven.model.common.audit.AuditSection;
import com.dynamicheart.raven.model.common.audit.Auditable;
import com.dynamicheart.raven.model.user.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dynamicheart on 6/8/2017.
 */
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "REPORT", schema = Constants.RAVEN_SCHEMA)
public class Report implements Serializable, Auditable {

    private static final long serialVersionUID = 7374785245019735458L;

    @Id
    @Column(name = "REPORT_ID", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_GEN", table = "RN_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "REPORT_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "REPORTER_ID", nullable = false)
    private User reporter;

    @Column(name = "NOTIFICATION_ID", nullable = false)
    private String notificationId;

    @Column(name = "REPORT_STATUS", length = 1, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ReportStatus status = ReportStatus.PROCESSING;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    @Override
    public AuditSection getAuditSection() {
        return auditSection;
    }

    @Override
    public void setAuditSection(AuditSection auditSection) {
        this.auditSection = auditSection;
    }
}
