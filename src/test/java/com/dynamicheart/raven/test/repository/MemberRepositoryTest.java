package com.dynamicheart.raven.test.repository;

import com.dynamicheart.raven.application.ApplicationConfiguration;
import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.repositories.house.HouseRepository;
import com.dynamicheart.raven.repositories.member.MemberRepository;
import com.dynamicheart.raven.repositories.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)
public class MemberRepositoryTest {

    @Inject
    MemberRepository memberRepository;

    @Inject
    HouseRepository houseRepository;

    @Inject
    UserRepository userRepository;

    @Test
    public void Test() {
        Member member=new Member();
        member.setHouse(houseRepository.findHousesByNameLike("m").get(0));
        member.setRole(Constants.MEMBER_ROLE_LORD);
        member.setUser(userRepository.findUserByUsername("julius_chen"));
        memberRepository.save(member);
    }
}
