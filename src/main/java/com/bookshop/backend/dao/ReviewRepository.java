package com.bookshop.backend.dao;

import com.bookshop.backend.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	Page<Review> findByBookId(@RequestParam("book_Id") Long bookId, Pageable pageable);

	Review findByUserEmailAndBookId(String userEmail, Long bookId);

	@Transactional
	@Modifying
	@Query("delete from Review where bookId in :bookId")
	void deleteAllByBookId(@Param("bookId") Long bookId);

}
