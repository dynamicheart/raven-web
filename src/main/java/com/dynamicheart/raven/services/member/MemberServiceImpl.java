package com.dynamicheart.raven.services.member;

import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.repositories.member.MemberRepository;
import com.dynamicheart.raven.services.generic.RavenEntityServiceImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class MemberServiceImpl extends RavenEntityServiceImpl<String, Member>
        implements MemberService {

    private MemberRepository memberRepository;

    @Inject
    public MemberServiceImpl(MemberRepository memberRepository) {
        super(memberRepository);
        this.memberRepository = memberRepository;
    }

    @Override
    public Member findTopByHouseAndUser(House house, User user) {
        return memberRepository.findTopByHouseAndUser(house, user);
    }

    @Override
    public List<Member> findByHouse(House house) {
        return memberRepository.findByHouse(house);
    }

    @Override
    public List<Member> findByUser(User user) {
        return memberRepository.findByUserId(user.getId());
    }
}
