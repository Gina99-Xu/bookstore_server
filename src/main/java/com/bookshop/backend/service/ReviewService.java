package com.bookshop.backend.service;

import com.bookshop.backend.dao.BookRepository;
import com.bookshop.backend.dao.ReviewRepository;
import com.bookshop.backend.entity.Review;
import com.bookshop.backend.requestmodels.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;

@Service
@Transactional
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private BookRepository bookRepository;


	public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception {
		Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, reviewRequest.getBookId());

		if (validateReview != null) {
			throw new Exception("Review has already created!");
		}

		Review review = new Review();
		review.setBookId(reviewRequest.getBookId());
		review.setRating(reviewRequest.getRating());
		review.setUserEmail(userEmail);
		review.setDate(Date.valueOf(LocalDate.now()));

		if (reviewRequest.getReviewDescription().isPresent()) {
			review.setReviewDescription(reviewRequest.getReviewDescription().map(
					Object::toString
			).orElse(null));
		}

		reviewRepository.save(review);
	}

	public boolean userReviewed(String userEmail, Long bookId) {
		Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, bookId);
		return validateReview != null;
	}

}
