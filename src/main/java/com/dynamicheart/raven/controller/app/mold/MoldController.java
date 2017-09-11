package com.dynamicheart.raven.controller.app.mold;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.controller.app.mold.field.MoldInfoFields;
import com.dynamicheart.raven.controller.app.mold.populator.MoldInfoFieldsPopulator;
import com.dynamicheart.raven.controller.app.user.field.UserInfoFields;
import com.dynamicheart.raven.model.mold.Mold;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.mold.MoldService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MoldController {
    @Inject
    private MoldService moldService;

    @Inject
    private MoldInfoFieldsPopulator moldInfoFieldsPopulator;

    @RequestMapping(value = "/api/v1/molds/{tag}", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = MoldInfoFields.class, responseContainer = "List",message = "Get mold by tag")
    })
    public ResponseEntity<?> getByTag(@PathVariable String tag) throws Exception{
        List<Mold> moldList=moldService.getAllByTag(tag);

        List<MoldInfoFields> moldInfoFieldsList=new ArrayList<>();
        for(Mold mold:moldList)
            moldInfoFieldsList.add(moldInfoFieldsPopulator.populate(mold));

        return new ResponseEntity<>(moldInfoFieldsList, HttpStatus.OK);
    }

}
