package com.dynamicheart.raven.services.house;

import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.repositories.house.HouseRepository;
import com.dynamicheart.raven.repositories.member.MemberRepository;
import com.dynamicheart.raven.services.generic.RavenEntityServiceImpl;
import com.dynamicheart.raven.utils.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class HouseServiceImpl extends RavenEntityServiceImpl<String, House>
        implements HouseService {

    private HouseRepository houseRepository;

    private MemberRepository memberRepository;

    @Inject
    public HouseServiceImpl(HouseRepository houseRepository, MemberRepository memberRepository) {
        super(houseRepository);
        this.houseRepository = houseRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public House create(House house, User founder) throws ServiceException{
        house.setFounderId(founder.getId());
        House createdHouse = houseRepository.save(house);

        Member member = new Member();
        member.setHouse(createdHouse);
        member.setUser(founder);
        member.setRole(Constants.MEMBER_ROLE_LORD);
        memberRepository.save(member);

        return createdHouse;
    }

    @Override
    public List<House> getAll() {
        return houseRepository.findAll();
    }

    @Override
    public List<House> getBySimilarName(String name) {
        return houseRepository.findHousesByNameLike(name);
    }

    @Override
    public House getByName(String name) {
        return houseRepository.findHouseByName(name);
    }

    @Override
    public void delete(House house) throws ServiceException {
        houseRepository.delete(house);
        memberRepository.deleteByHouse(house);
    }
}
