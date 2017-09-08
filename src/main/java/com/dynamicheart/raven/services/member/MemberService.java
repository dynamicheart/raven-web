package com.dynamicheart.raven.services.member;

import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.generic.RavenEntityService;

import java.util.List;

public interface MemberService extends RavenEntityService<String, Member> {

    Member findTopByHouseAndUser(House house, User user);

    List<Member> findByHouse(House house);

    List<Member> findByUser(User user);
}
