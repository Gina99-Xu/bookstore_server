package com.bookshop.backend.entity;


import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class History {

	public History() {
	}

	public History(String userEmail, String checkoutDate, String returnedDate, String title,
	               String author, String description, String image) {
		this.userEmail = userEmail;
		this.checkoutDate = checkoutDate;
		this.returnedDate = returnedDate;
		this.title = title;
		this.author = author;
		this.description = description;
		this.image = image;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userEmail;
	@Column(name = "checkout_date", columnDefinition = "TEXT", length = 1000)
	private String checkoutDate;
	@Column(name = "returned_date", columnDefinition = "TEXT", length = 1000)
	private String returnedDate;
	private String title;
	private String author;
	@Column(name = "description", columnDefinition = "TEXT", length = 1000)
	private String description;
	@Column(name = "image", columnDefinition = "TEXT", length = 1000)
	private String image;

}
