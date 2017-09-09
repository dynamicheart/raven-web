package com.dynamicheart.raven.controller.admin.member.populator;

import com.dynamicheart.raven.controller.app.member.field.MemberInfoFields;
import com.dynamicheart.raven.controller.app.member.populator.MemberInfoFieldsPopulator;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.utils.exception.ConversionException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberBriefInfoPopulator extends MemberInfoFieldsPopulator{
    public List<MemberInfoFields> populateList(List<Member> memberList) throws ConversionException {
        List<MemberInfoFields> memberInfoFieldsList=new ArrayList<>();
        for(Member member:memberList)
            memberInfoFieldsList.add(populate(member,new MemberInfoFields()));
        return memberInfoFieldsList;
    }
}
