package com.bookshop.backend.controller;


import com.bookshop.backend.entity.Message;
import com.bookshop.backend.requestmodels.AdminQuestionRequest;
import com.bookshop.backend.service.MessageService;
import com.bookshop.backend.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader(value="Authorization") String token,
                            @RequestBody Message messageRequest) throws Exception{

        String userEmail = ExtractJWT.payloadJWTExtraction(token,  "\"sub\"");
        if(userEmail == null) {
            throw new Exception("user email is missing!");
        }
        messageService.postMessage(messageRequest, userEmail);
    }


    @PutMapping("/secure/admin/message")
    public void putMessage(@RequestHeader(value="Authorization") String token,
                           @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception{
        String userEmail = ExtractJWT.payloadJWTExtraction(token,  "\"sub\"");
        String admin = ExtractJWT.payloadJWTExtraction(token,  "\"userType\"");

        if( admin == null || !admin.equals("admin")) {
            throw new Exception("user is not admin role");
        }
        messageService.putMessage(adminQuestionRequest, userEmail);
    }

}
