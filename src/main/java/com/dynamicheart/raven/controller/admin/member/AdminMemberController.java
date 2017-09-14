package com.dynamicheart.raven.controller.admin.member;

import com.dynamicheart.raven.authorization.annotation.AdminAuthorization;
import com.dynamicheart.raven.controller.admin.member.populator.MemberBriefInfoPopulator;
import com.dynamicheart.raven.controller.app.member.field.MemberInfoFields;
import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.services.house.HouseService;
import com.dynamicheart.raven.services.member.MemberService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/admin/api/v1/houses/{houseId}/members")
public class AdminMemberController {
    @Inject
    private HouseService houseService;

    @Inject
    private MemberService memberService;

    @Inject
    private MemberBriefInfoPopulator memberBriefInfoPopulator;

    @RequestMapping(value = "/allMemberInfo", method = RequestMethod.GET)
    @AdminAuthorization
    @ApiResponses({
            @ApiResponse(code = 200, response = MemberInfoFields.class, message = "Get member info")
    })
    public ResponseEntity<?> get(@PathVariable String houseId) throws Exception {
        if(!houseService.exists(houseId))
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        House house = houseService.getById(houseId);

        List<Member> memberList=memberService.findByHouse(house);

        List<MemberInfoFields> memberInfoFieldsList = memberBriefInfoPopulator.populateList(memberList);

        return new ResponseEntity<>(memberInfoFieldsList, HttpStatus.OK);
    }
}
