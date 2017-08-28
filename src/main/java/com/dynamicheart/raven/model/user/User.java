package com.dynamicheart.raven.model.user;

import com.dynamicheart.raven.constants.Constants;
import com.dynamicheart.raven.model.apply.Apply;
import com.dynamicheart.raven.model.group.member.GroupMember;
import com.dynamicheart.raven.model.common.audit.AuditListener;
import com.dynamicheart.raven.model.common.audit.AuditSection;
import com.dynamicheart.raven.model.common.audit.Auditable;
import com.dynamicheart.raven.model.report.Report;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dynamicheart on 6/8/2017.
 */
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "USER", schema = Constants.RAVEN_SCHEMA)
public class User implements Serializable, Auditable {

    private static final long serialVersionUID = 2861850070967782259L;

    @Id
    @Column(name = "USER_ID", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_GEN", table = "RN_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "USER_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @NotEmpty
    @Column(name = "USER_NAME", length = 100, unique = true)
    private String username;

    @NotEmpty
    @Column(name = "USER_PASSWORD", length = 60)
    private String password;

    @NotEmpty
    @Email
    @Column(name = "USER_EMAIL", length = 96, nullable = false)
    private String email;

    @NotEmpty
    @Column(name = "USER_PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "IS_ADMIN")
    private Boolean idAdmin = false;

    @Column(name = "USER_STATUS", length = 1, nullable = true)
    @Enumerated(value = EnumType.STRING)
    private UserStatus status = UserStatus.NORMAL;

    @Column(name = "USER_AVATAR")
    private String avatar;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
    private Set<GroupMember> groups = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "applier")
    private Set<Apply> applies = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "reporter")
    private Set<Report> reports = new HashSet<>();

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @Override
    public AuditSection getAuditSection() {
        return auditSection;
    }

    @Override
    public void setAuditSection(AuditSection audit) {
        auditSection = audit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Boolean idAdmin) {
        this.idAdmin = idAdmin;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<GroupMember> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupMember> groups) {
        this.groups = groups;
    }

    public Set<Apply> getApplies() {
        return applies;
    }

    public void setApplies(Set<Apply> applies) {
        this.applies = applies;
    }
}
