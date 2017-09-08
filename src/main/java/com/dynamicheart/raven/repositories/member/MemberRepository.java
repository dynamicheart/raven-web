package com.dynamicheart.raven.repositories.member;

import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.member.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member, String> {
}
