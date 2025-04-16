package com.bookshop.backend.controller;

import com.bookshop.backend.requestmodels.PaymentInfoRequest;
import com.bookshop.backend.service.PaymentService;
import com.bookshop.backend.utils.ExtractJWT;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/payment/secure")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfoRequest paymentInfoRequest) throws Exception{
        PaymentIntent paymentIntent = paymentService.createPaymentIntent(paymentInfoRequest);
        String paymentJson = paymentIntent.toJson();
        return new ResponseEntity<>(paymentJson, HttpStatus.CREATED);
    }

    @PutMapping("/payment-complete")
    public ResponseEntity<String> stripePaymentCompletion(@RequestHeader(value="Authorization") String token) throws Exception{
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        if(userEmail == null) {
            throw new Exception("user email is missing!");
        }
        return paymentService.stripePayment(userEmail);
    }


}
