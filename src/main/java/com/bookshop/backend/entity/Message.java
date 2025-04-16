package com.bookshop.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
public class Message {

	public Message(String title, String question) {
		this.title = title;
		this.question = question;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userEmail;
	private String title;
	private String question;
	private String adminEmail;
	private String response;
	private boolean closed;
}
