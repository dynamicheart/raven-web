package com.dynamicheart.raven.controller.admin.house;

import com.dynamicheart.raven.constant.Constants;
import com.dynamicheart.raven.controller.admin.house.field.HouseBriefInfo;
import com.dynamicheart.raven.controller.admin.house.field.HouseDetailForm;
import com.dynamicheart.raven.controller.admin.house.populator.HouseBriefInfoPopulator;
import com.dynamicheart.raven.controller.admin.house.populator.HouseDetailFormPopulator;
import com.dynamicheart.raven.controller.admin.user.field.UserBriefInfo;
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
@RequestMapping("/admin/api/v1/houses")
public class AdminHouseController {
    @Inject
    private HouseService houseService;

    @Inject
    private MemberService memberService;

    @Inject
    private HouseBriefInfoPopulator houseBriefInfoPopulator;

    @Inject
    private HouseDetailFormPopulator houseDetailFormPopulator;

    @RequestMapping(value = "allHouseInfo", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 200, response = List.class, message = "query all house infos")
    })
    ResponseEntity<?> getAll() throws Exception{
        List<House> houseList=houseService.getAll();
        List<HouseBriefInfo> houseBriefInfoList=houseBriefInfoPopulator.populateList(houseList);

        return new ResponseEntity<>(houseBriefInfoList, HttpStatus.OK);
    }

    @RequestMapping(value = "searchInfoByName/{houseName}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 200, response = UserBriefInfo.class, message = "query house info by name")
    })
    ResponseEntity<?> getByName(@PathVariable String houseName) throws Exception{
        List<House> houseList=houseService.getBySimilarName(houseName);
        List<HouseBriefInfo> houseBriefInfoList=houseBriefInfoPopulator.populateList(houseList);

        return new ResponseEntity<>(houseBriefInfoList, HttpStatus.OK);
    }

    @RequestMapping(value = "searchDetailById/{id}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 200, response = HouseDetailForm.class, message = "query house detail by id")
    })
    ResponseEntity<?> getDById(@PathVariable String id) throws Exception{
        String master=null;
        if(!houseService.exists(id))
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        House house=houseService.getById(id);
        List<Member> memberList=memberService.findByHouse(house);

        for(Member member:memberList)
            if(member.getRole()== Constants.MEMBER_ROLE_LORD)
                master=member.getUser().getUsername();

        HouseDetailForm houseDetailForm=new HouseDetailForm();
        houseDetailFormPopulator.populate(house,houseDetailForm,master);
        return new ResponseEntity<>(houseDetailForm, HttpStatus.OK);
    }



    @RequestMapping(value = "deleteHouse/{id}", method = RequestMethod.DELETE)
    @ApiResponses({
            @ApiResponse(code = 200, response = House.class, message = "delete house")
    })
    ResponseEntity<?> deleteHouse(@PathVariable String id) throws Exception{
        if(!houseService.exists(id))
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        House house=houseService.getById(id);
        houseService.delete(house);

        return new ResponseEntity<>(house, HttpStatus.OK);
    }


    @RequestMapping(value = "censorSigil/{id}", method = RequestMethod.PUT)
    @ApiResponses({
            @ApiResponse(code = 200, response = HouseDetailForm.class, message = "delete house avatar")
    })
    ResponseEntity<?> censorSigil(@PathVariable String id) throws Exception{
        if(!houseService.exists(id))
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        House house=houseService.getById(id);
        house.setSigil(null);
        houseService.save(house);

        HouseDetailForm houseDetailForm=new HouseDetailForm();
        houseDetailFormPopulator.populate(house,houseDetailForm,null);
        return new ResponseEntity<>(houseDetailForm, HttpStatus.OK);
    }

    @RequestMapping(value = "setToPublic/{id}", method = RequestMethod.PUT)
    @ApiResponses({
            @ApiResponse(code = 200, response = HouseDetailForm.class, message = "set house to public")
    })
    ResponseEntity<?> setToPublic(@PathVariable String id) throws Exception{
        if(!houseService.exists(id))
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        House house=houseService.getById(id);
        house.setPublicity(true);
        houseService.save(house);

        HouseDetailForm houseDetailForm=new HouseDetailForm();
        houseDetailFormPopulator.populate(house,houseDetailForm,null);
        return new ResponseEntity<>(houseDetailForm, HttpStatus.OK);
    }

    @RequestMapping(value = "setToPrivate/{id}", method = RequestMethod.PUT)
    @ApiResponses({
            @ApiResponse(code = 200, response = HouseDetailForm.class, message = "set house to private")
    })
    ResponseEntity<?> setToPrivate(@PathVariable String id) throws Exception{
        if(!houseService.exists(id))
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        House house=houseService.getById(id);
        house.setPublicity(false);
        houseService.save(house);

        HouseDetailForm houseDetailForm=new HouseDetailForm();
        houseDetailFormPopulator.populate(house,houseDetailForm,null);
        return new ResponseEntity<>(houseDetailForm, HttpStatus.OK);
    }

    @RequestMapping(value = "disableHouse/{id}", method = RequestMethod.PUT)
    @ApiResponses({
            @ApiResponse(code = 200, response = HouseDetailForm.class, message = "disable house")
    })
    ResponseEntity<?> disableHouse(@PathVariable String id) throws Exception{
        if(!houseService.exists(id))
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        House house=houseService.getById(id);
        house.setStatus(Constants.HOUSE_STATUS_DISABLE);
        houseService.save(house);

        HouseDetailForm houseDetailForm=new HouseDetailForm();
        houseDetailFormPopulator.populate(house,houseDetailForm,null);
        return new ResponseEntity<>(houseDetailForm, HttpStatus.OK);
    }

    @RequestMapping(value = "activateHouse/{id}", method = RequestMethod.PUT)
    @ApiResponses({
            @ApiResponse(code = 200, response = HouseDetailForm.class, message = "activate house")
    })
    ResponseEntity<?> activateHouse(@PathVariable String id) throws Exception{
        if(!houseService.exists(id))
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        House house=houseService.getById(id);
        house.setStatus(Constants.HOUSE_STATUS_NORMAL);
        houseService.save(house);

        HouseDetailForm houseDetailForm=new HouseDetailForm();
        houseDetailFormPopulator.populate(house,houseDetailForm,null);
        return new ResponseEntity<>(houseDetailForm, HttpStatus.OK);
    }
}
