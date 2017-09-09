package com.dynamicheart.raven.controller.app.member.populator;

import com.dynamicheart.raven.controller.app.member.field.MemberInfoFields;
import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class MemberInfoFieldsPopulator extends AbstractDataPopulator<Member,MemberInfoFields>{

    @Override
    protected MemberInfoFields createTarget() {
        return new MemberInfoFields();
    }

    @Override
    public MemberInfoFields populate(@NotNull Member member,@NotNull MemberInfoFields memberInfoFields) throws ConversionException {
        memberInfoFields.setId(member.getUser().getId());
        memberInfoFields.setUsername(member.getUser().getUsername());
        memberInfoFields.setRole(member.getRole());
        memberInfoFields.setAvatar(member.getUser().getAvatar());
        return memberInfoFields;
    }
}
