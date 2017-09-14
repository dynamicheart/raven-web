package com.dynamicheart.raven.controller.common.populator;

import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.controller.common.field.MemberRefFields;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Component
public class MemberRefFieldsListPopulator extends AbstractDataPopulator<List<Member>, List<MemberRefFields>> {

    @Override
    protected List<MemberRefFields> createTarget() {
        return new LinkedList<>();
    }

    @Override
    public List<MemberRefFields> populate(@NotNull List<Member> members, @NotNull List<MemberRefFields> memberRefFieldsList) throws ConversionException {
        members.forEach(member -> {
                    if (member.getUser() != null){
                        MemberRefFields memberRefFields = new MemberRefFields();
                        memberRefFields.setUserId(member.getUser().getId());
                        memberRefFields.setRole(member.getRole());
                        memberRefFields.setUsername(member.getUser().getUsername());
                        memberRefFields.setAvatar(member.getUser().getAvatar());
                        memberRefFieldsList.add(memberRefFields);
                    }
                });
        //bug fix
        return memberRefFieldsList;
    }
}
