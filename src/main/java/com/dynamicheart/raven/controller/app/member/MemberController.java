package com.dynamicheart.raven.controller.app.member;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.constant.Message;
import com.dynamicheart.raven.controller.app.member.field.MemberInfoFields;
import com.dynamicheart.raven.controller.app.member.populator.MemberInfoFieldsPopulator;
import com.dynamicheart.raven.controller.common.model.GenericResponseBody;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.house.HouseService;
import com.dynamicheart.raven.services.member.MemberService;
import com.dynamicheart.raven.services.user.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/houses/{houseId}/members")
public class MemberController {

    @Inject
    private HouseService houseService;

    @Inject
    private MemberService memberService;

    @Inject
    private UserService userService;

    @Inject
    private MemberInfoFieldsPopulator memberInfoFieldsPopulator;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = MemberInfoFields.class, message = "Get member info")
    })
    public ResponseEntity<?> get(@PathVariable String houseId,
                          @PathVariable String userId,
                          @CurrentUser @ApiIgnore User currentUser) throws Exception {
        House house = houseService.getById(houseId);
        if (house == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        //bug fix:应该能查看任意成员
        User user=userService.getById(userId);
        if(user==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));

        Member member = memberService.findTopByHouseAndUser(house, user);

        //bug fix
        if (!house.getPublicity() && member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        MemberInfoFields memberInfoFields = memberInfoFieldsPopulator.populate(member);

        return new ResponseEntity<>(memberInfoFields, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = MemberInfoFields.class, responseContainer = "List", message = "Get member info")
    })
    public ResponseEntity<?> getAll(@PathVariable String houseId,
                                 @CurrentUser @ApiIgnore User currentUser) throws Exception {
        House house = houseService.getById(houseId);
        if (house == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        //当前用户不在组内，且为私密组时不给反馈
        Member curMember = memberService.findTopByHouseAndUser(house, currentUser);

        if (!house.getPublicity() && curMember == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }


        List<Member> memberList=memberService.findByHouse(house);

        List<MemberInfoFields> memberInfoFieldsList=new ArrayList<>();
        for(Member member:memberList)
            memberInfoFieldsList.add(memberInfoFieldsPopulator.populate(member));

        return new ResponseEntity<>(memberInfoFieldsList, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = MemberInfoFields.class, message = "Add a member")
    })
    public ResponseEntity<?> post(@PathVariable String houseId,
                           @RequestBody String userId,
                           @CurrentUser @ApiIgnore User currentUser) throws Exception{

        House house = houseService.getById(houseId);
        if (house == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        Member currentUserMember = memberService.findTopByHouseAndUser(house, currentUser);
        if (currentUserMember == null || !currentUserMember.getRole().equals(Constants.MEMBER_ROLE_LORD)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        User user=userService.getById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }
        //bug fix: 不应该看当前用户有没有在通知组内，而应该看目标用户有没有
        if (memberService.findTopByHouseAndUser(house, user) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponseBody(Message.MESSAGE_MEMBER_DUPLICATE));
        }

        Member member = new Member();
        member.setHouse(house);
        member.setUser(user);

        member = memberService.create(member);
        //bug fix:正确计算house member count
        house.setMemberNumbers(house.getMemberNumbers()+1);
        houseService.save(house);

        MemberInfoFields memberInfoFields = memberInfoFieldsPopulator.populate(member);

        return new ResponseEntity<>(memberInfoFields, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = MemberInfoFields.class, message = "Update the role of member")
    })
    public ResponseEntity<?> put(@PathVariable String houseId,
                          @PathVariable String userId,
                          @RequestBody Integer role,
                          @CurrentUser @ApiIgnore User currentUser) throws Exception{
        House house = houseService.getById(houseId);
        User user = userService.getById(userId);
        if(user == null || house == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Message.MESSAGE_NOT_FOUND);
        }

        Member currentUserMember = memberService.findTopByHouseAndUser(house, currentUser);
        if (currentUserMember == null || !currentUserMember.getRole().equals(Constants.MEMBER_ROLE_LORD)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        //不能更改自己的权限
        if(userId.equals(currentUser.getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        Member member = memberService.findTopByHouseAndUser(house, user);

        //增加对成员是否存在的检查
        if(member==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Message.MESSAGE_NOT_FOUND);


        if(role.equals(Constants.MEMBER_ROLE_LORD)){
            member.setRole(Constants.MEMBER_ROLE_LORD);
            currentUserMember.setRole(Constants.MEMBER_ROLE_ORDINARY);
            memberService.save(currentUserMember);
        }else {
            member.setRole(role);
        }

        member = memberService.save(member);
        MemberInfoFields memberInfoFields = memberInfoFieldsPopulator.populate(member);

        return new ResponseEntity<>(memberInfoFields, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = MemberInfoFields.class, message = "Delete a member")
    })
    public ResponseEntity<?> delete(@PathVariable String houseId,
                             @PathVariable String userId,
                             @CurrentUser @ApiIgnore User currentUser) throws Exception {
        House house = houseService.getById(houseId);
        if (house == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        Member currentUserMember = memberService.findTopByHouseAndUser(house, currentUser);
        Boolean isHouseLord = currentUserMember != null && currentUserMember.getRole().equals(Constants.MEMBER_ROLE_LORD);
        Boolean isCurrentUser = userId.equals(currentUser.getId());
        if (!isHouseLord && !isCurrentUser) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        //bug fix:应该可以删除任意成员
        User user=userService.getById(userId);
        if(user==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));

        Member member = memberService.findTopByHouseAndUser(house, user);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }
        //feature:不允许删除圈主
        if(member.getRole().equals(Constants.MEMBER_ROLE_LORD))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));

        memberService.delete(member);

        //那是因为你没有save house啊……
        house.setMemberNumbers(house.getMemberNumbers() - 1);
        houseService.save(house);

        MemberInfoFields memberInfoFields = memberInfoFieldsPopulator.populate(member);

        return new ResponseEntity<>(memberInfoFields, HttpStatus.OK);
    }
}
