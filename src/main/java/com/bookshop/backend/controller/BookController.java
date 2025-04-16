package com.bookshop.backend.controller;

import com.bookshop.backend.entity.Book;
import com.bookshop.backend.responsemodels.ShelfCurrentLoansResponse;
import com.bookshop.backend.service.BookService;
import com.bookshop.backend.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@PutMapping("/secure/checkout")
	public Book checkoutBook(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId) throws Exception {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		return bookService.checkoutBook(userEmail, bookId);
	}

	@GetMapping("/secure/currentloans")
	public List<ShelfCurrentLoansResponse> currentLoans(@RequestHeader(value = "Authorization") String token) throws Exception {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		return bookService.currentLoans(userEmail);
	}


	@GetMapping("/secure/currentloans/count")
	public int currentLoansCount(@RequestHeader(value = "Authorization") String token) {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		return bookService.currentLoansCount(userEmail);
	}


	@GetMapping("/secure/ischeckedout/byuser")
	public Boolean checkoutBookByUser(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId) {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		return bookService.checkoutBookByUser(userEmail, bookId);
	}

	@PutMapping("/secure/return")
	public void returnBook(@RequestHeader(value = "Authorization") String token,
	                       @RequestParam Long bookId) throws Exception {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		bookService.returnBook(userEmail, bookId);
	}


}
