package com.bookshop.backend.controller;


import com.bookshop.backend.requestmodels.ReviewRequest;
import com.bookshop.backend.service.ReviewService;
import com.bookshop.backend.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @GetMapping("secure/user/book")
    public Boolean reviewBookByUser(@RequestHeader(value="Authorization") String token,
                                    @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token,  "\"sub\"");
        if(userEmail == null) {
            throw new Exception("user email is missing!");
        }
        return reviewService.userReviewed(userEmail, bookId);
    }


    @PostMapping("secure/submitreview")
    public void postReview(@RequestHeader(value="Authorization") String token,
                           @RequestBody ReviewRequest reviewRequest) throws Exception{
        String userEmail = ExtractJWT.payloadJWTExtraction(token,  "\"sub\"");
        if(userEmail == null) {
            throw new Exception("user email is missing!");
        }
        reviewService.postReview(userEmail, reviewRequest);
    }
}
