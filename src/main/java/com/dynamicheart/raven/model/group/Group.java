package com.dynamicheart.raven.model.group;

import com.dynamicheart.raven.constants.Constants;
import com.dynamicheart.raven.model.apply.Apply;
import com.dynamicheart.raven.model.group.member.GroupMember;
import com.dynamicheart.raven.model.common.audit.AuditListener;
import com.dynamicheart.raven.model.common.audit.AuditSection;
import com.dynamicheart.raven.model.common.audit.Auditable;
import org.hibernate.annotations.Type;
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
@Table(name = "GROUP", schema = Constants.RAVEN_SCHEMA)
public class Group implements Serializable, Auditable {

    private static final long serialVersionUID = 6757222346751034796L;

    @Id
    @Column(name = "GROUP_ID", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_GEN", table = "RN_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "GROUP_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @NotEmpty
    @Column(name = "GROUP_NAME", length = 100, unique = true)
    private String name;

    @Column(name = "GROUP_STATUS", length = 1, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private GroupStatus status = GroupStatus.NORMAL;

    @Column(name = "GROUP_CAPACITY")
    private Integer capacity = 100;

    @Column(name = "DESCRIPTION")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
    private Set<GroupMember> Groups = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "group")
    private Set<Apply> applies = new HashSet<>();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroupStatus getStatus() {
        return status;
    }

    public void setStatus(GroupStatus status) {
        this.status = status;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<GroupMember> getGroups() {
        return Groups;
    }

    public void setGroups(Set<GroupMember> groups) {
        Groups = groups;
    }

    public Set<Apply> getApplies() {
        return applies;
    }

    public void setApplies(Set<Apply> applies) {
        this.applies = applies;
    }
}
