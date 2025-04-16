package com.bookshop.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Checkout {

	public Checkout() {
	}

	public Checkout(String userEmail, String checkoutDate, String returnDate, Long bookId) {
		this.userEmail = userEmail;
		this.checkoutDate = checkoutDate;
		this.returnDate = returnDate;
		this.bookId = bookId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userEmail;

	private String checkoutDate;

	private String returnDate;

	private Long bookId;

}
