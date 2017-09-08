package com.dynamicheart.raven.controller.common.populator;

import com.dynamicheart.raven.controller.common.AbstractDataPopulator;
import com.dynamicheart.raven.controller.common.field.MemberRefFields;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.repositories.user.UserRepository;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Component
public class MemberRefFieldsListPopulator extends AbstractDataPopulator<List<Member>, List<MemberRefFields>> {

    @Inject
    private UserRepository userRepository;

    @Override
    protected List<MemberRefFields> createTarget() {
        return new LinkedList<>();
    }

    @Override
    public List<MemberRefFields> populate(@NotNull List<Member> members, @NotNull List<MemberRefFields> memberRefFieldsList) throws ConversionException {
        members.forEach(member -> {
                    if (member.getUser() != null){
                        MemberRefFields memberRefFields = new MemberRefFields();
                        memberRefFields.setId(member.getUser().getId());
                        memberRefFields.setRole(member.getRole());
                        memberRefFields.setUsername(member.getUser().getUsername());
                        memberRefFields.setAvatar(member.getUser().getAvatar());
                        memberRefFieldsList.add(memberRefFields);
                    }
                });
        return null;
    }
}
