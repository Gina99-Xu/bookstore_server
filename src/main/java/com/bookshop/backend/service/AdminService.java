package com.bookshop.backend.service;

import com.bookshop.backend.dao.BookRepository;
import com.bookshop.backend.dao.CheckoutRepository;
import com.bookshop.backend.dao.ReviewRepository;
import com.bookshop.backend.entity.Book;
import com.bookshop.backend.requestmodels.AddBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AdminService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private CheckoutRepository checkoutRepository;


	public void increaseBookQuantity(Long bookId) throws Exception {
		Optional<Book> book = bookRepository.findById(bookId);
		if (book.isEmpty()) {
			throw new Exception("Book not found");
		}
		book.get().setCopiesAvailable(book.get().getCopiesAvailable() + 1);
		book.get().setCopies(book.get().getCopies() + 1);
		bookRepository.save(book.get());
	}

	public void decreaseBookQuantity(Long bookId) throws Exception {
		Optional<Book> book = bookRepository.findById(bookId);
		if (book.isEmpty()) {
			throw new Exception("Book not found");
		}
		book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
		book.get().setCopies(book.get().getCopies() - 1);
		bookRepository.save(book.get());
	}


	public void postBook(AddBookRequest addBookRequest) {
		Book book = new Book();
		book.setTitle(addBookRequest.getTitle());
		book.setAuthor(addBookRequest.getAuthor());
		book.setDescription(addBookRequest.getDescription());
		book.setCopiesAvailable(addBookRequest.getCopiesAvailable());
		book.setCopies(addBookRequest.getCopies());
		book.setCategory(addBookRequest.getCategory());
		book.setImg(addBookRequest.getImg());
		bookRepository.save(book);
	}


	public void deleteBook(Long bookId) throws Exception {
		Optional<Book> book = bookRepository.findById(bookId);
		if (book.isEmpty()) {
			throw new Exception("Book does not exit!");
		}
		bookRepository.delete(book.get());
		checkoutRepository.deleteAllByBookId(bookId);
		reviewRepository.deleteAllByBookId(bookId);
	}


}
