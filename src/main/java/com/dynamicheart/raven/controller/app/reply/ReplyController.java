package com.dynamicheart.raven.controller.app.reply;

import com.dynamicheart.raven.authorization.annotation.Authorization;
import com.dynamicheart.raven.authorization.annotation.CurrentUser;
import com.dynamicheart.raven.constant.Message;
import com.dynamicheart.raven.controller.app.reply.field.ReplyInfoFields;
import com.dynamicheart.raven.controller.app.reply.populator.ReplyInfoFieldsPopulator;
import com.dynamicheart.raven.controller.common.model.GenericResponseBody;
import com.dynamicheart.raven.model.raven.Raven;
import com.dynamicheart.raven.model.reply.Reply;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.services.raven.RavenService;
import com.dynamicheart.raven.services.reply.ReplyService;
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
public class ReplyController {

    @Inject
    private RavenService ravenService;

    @Inject
    private ReplyService replyService;

    @Inject
    private ReplyInfoFieldsPopulator replyInfoFieldsPopulator;

    @RequestMapping(value = "/api/v1/ravens/{ravenId}/replies", method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = ReplyInfoFields.class, responseContainer = "List", message = "Get all replies of a raven")
    })
    public ResponseEntity<?> getAll(@PathVariable String ravenId, @CurrentUser @ApiIgnore User currentUser) throws Exception{
        Raven raven = ravenService.getById(ravenId);
        if(raven == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        if(!raven.getAddresserId().equals(currentUser.getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        List<Reply> replies = replyService.findByRavenId(ravenId);

        List<ReplyInfoFields> replyInfoFieldsList = new ArrayList<>();
        for(Reply reply: replies){
            replyInfoFieldsList.add(replyInfoFieldsPopulator.populate(reply));
        }

        return new ResponseEntity<>(replyInfoFieldsList, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/replies/{replyId}",method = RequestMethod.GET)
    @Authorization
    @ApiResponses({
            @ApiResponse(code = 200, response = ReplyInfoFields.class,  message = "Get one raven")
    })
    public ResponseEntity<?> get(@PathVariable String replyId, @CurrentUser @ApiIgnore User currentUser) throws Exception{
        Reply reply = replyService.getById(replyId);
        if(reply == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        Raven raven = ravenService.getById(reply.getRavenId());
        if(raven == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponseBody(Message.MESSAGE_NOT_FOUND));
        }

        if(!raven.getAddresserId().equals(currentUser.getId()) || !reply.getUserId().equals(currentUser.getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseBody(Message.MESSAGE_FORBIDDEN));
        }

        ReplyInfoFields replyInfoFields = replyInfoFieldsPopulator.populate(reply);

        return new ResponseEntity<>(replyInfoFields, HttpStatus.OK);
    }
}
