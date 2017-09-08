package com.dynamicheart.raven.services.member;

import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.repositories.member.MemberRepository;
import com.dynamicheart.raven.services.generic.RavenEntityServiceImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class MemberServiceImpl extends RavenEntityServiceImpl<String, Member>
        implements MemberService {

    private MemberRepository memberRepository;

    @Inject
    public MemberServiceImpl(MemberRepository memberRepository) {
        super(memberRepository);
        this.memberRepository = memberRepository;
    }
}
