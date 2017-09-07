package com.dynamicheart.raven.repositories.member;

import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemberRepository extends MongoRepository<Member, String> {

    List<Member> findByHouseId(String houseId);

    List<Member> findByHouseIdAndUser(String houseId, User user);
}
