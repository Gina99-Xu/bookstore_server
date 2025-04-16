package com.bookshop.backend.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String author;

	@Column(name = "description", columnDefinition = "TEXT", length = 1000)
	private String description;

	private int copies;

	private int copiesAvailable;

	private String category;

	@Column(name = "image", columnDefinition = "TEXT", length = 1000)
	private String img;

}
