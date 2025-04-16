package com.bookshop.backend.dao;

import com.bookshop.backend.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

	Checkout findByUserEmailAndBookId(String userEmail, Long bookId);

	List<Checkout> findBooksByUserEmail(String userEmail);

	@Transactional
	@Modifying
	@Query("delete from Checkout where bookId in :bookId")
	void deleteAllByBookId(@Param("bookId") Long bookId);
}
