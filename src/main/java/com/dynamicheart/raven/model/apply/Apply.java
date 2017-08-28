package com.dynamicheart.raven.model.apply;

import com.dynamicheart.raven.constants.Constants;
import com.dynamicheart.raven.model.common.audit.AuditListener;
import com.dynamicheart.raven.model.common.audit.Auditable;
import com.dynamicheart.raven.model.group.Group;
import com.dynamicheart.raven.model.common.audit.AuditSection;
import com.dynamicheart.raven.model.user.User;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dynamicheart on 6/8/2017.
 */
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "APPLY", schema = Constants.RAVEN_SCHEMA)
public class Apply implements Serializable, Auditable {

    private static final long serialVersionUID = -3874982418149647602L;

    @Id
    @Column(name = "APPLY_ID", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_GEN", table = "RN_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "APPLY_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @ManyToOne(targetEntity = Group.class)
    @JoinColumn(name = "GROUP_ID", nullable = false)
    private Group group;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "APPLIER_ID", nullable = false)
    private User applier;

    @Column(name = "TEXT_BODY")
    @Type(type = "org.hibernate.type.TextType")
    private String textBody;

    @Column(name = "APPLY_TYPE", length = 1, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ApplyType type = ApplyType.INFORMER;

    @Column(name = "APPLY_STATUS", length = 1, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ApplyStatus status;

    @Embedded
    private AuditSection auditSection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getApplier() {
        return applier;
    }

    public void setApplier(User applier) {
        this.applier = applier;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    public ApplyType getType() {
        return type;
    }

    public void setType(ApplyType type) {
        this.type = type;
    }

    public ApplyStatus getStatus() {
        return status;
    }

    public void setStatus(ApplyStatus status) {
        this.status = status;
    }

    public AuditSection getAuditSection() {
        return auditSection;
    }

    public void setAuditSection(AuditSection auditSection) {
        this.auditSection = auditSection;
    }
}
