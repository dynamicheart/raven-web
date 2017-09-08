package com.dynamicheart.raven.services.user;

import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.repositories.member.MemberRepository;
import com.dynamicheart.raven.repositories.user.UserRepository;
import com.dynamicheart.raven.services.generic.RavenEntityServiceImpl;
import com.dynamicheart.raven.utils.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service("userService")
public class UserServiceImpl extends RavenEntityServiceImpl<String, User>
        implements UserService {

    private UserRepository userRepository;

    private MemberRepository memberRepository;

    @Inject
    public UserServiceImpl(UserRepository userRepository, MemberRepository memberRepository) {
        super(userRepository);
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void delete(User user) throws ServiceException {
        userRepository.delete(user);
        memberRepository.deleteByUser(user);
    }

    @Override
    public Boolean exists(List<String> userIds) {
        for(String userId: userIds){
            if (!userRepository.exists(userId)){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getBySimilarName(String username) {
        return userRepository.findUsersByUsernameLike(username);
    }
}
