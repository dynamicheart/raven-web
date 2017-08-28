package com.dynamicheart.raven.model.group.member;

import com.dynamicheart.raven.constants.Constants;
import com.dynamicheart.raven.model.group.Group;
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
@Table(name = "GROUP_MEMBER", schema = Constants.RAVEN_SCHEMA)
public class GroupMember implements Serializable, Auditable {

    private static final long serialVersionUID = 1827874648075560645L;

    @Id
    @JoinColumn(name = "USER_ID", nullable = false)
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Id
    @JoinColumn(name = "GROUP_ID", nullable = false)
    @ManyToOne(targetEntity = Group.class)
    private Group group;

    @Column(name = "MEMBER_ROLE", length = 1, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.PEOPLE;

    @Embedded
    private AuditSection auditSection;

    @Override
    public AuditSection getAuditSection() {
        return auditSection;
    }

    @Override
    public void setAuditSection(AuditSection audit) {
        auditSection = audit;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
