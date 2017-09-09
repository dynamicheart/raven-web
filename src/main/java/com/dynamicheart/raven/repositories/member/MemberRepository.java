package com.dynamicheart.raven.repositories.member;

import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface MemberRepository extends MongoRepository<Member, String> {

    @RestResource(exported = false)
    List<Member> findByHouse(House house);

    @RestResource(exported = false)
    List<Member> findByUser(User user);

    @RestResource(exported = false)
    Member findTopByHouseAndUser(House house, User user);

    @RestResource(exported = false)
    List<Member> deleteByHouse(House house);

    @RestResource(exported = false)
    List<Member> deleteByUser(User user);
}
