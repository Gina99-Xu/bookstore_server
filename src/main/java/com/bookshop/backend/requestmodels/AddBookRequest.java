package com.bookshop.backend.requestmodels;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class AddBookRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String author;
	private String description;
	private int copies;
	private int copiesAvailable;
	private String category;
	private String img;
}
