package com.example.demo.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.BookDao.Book;
import com.example.demo.BookDao.BookRepository;


@RestController
public class BookController {
	@Autowired
	BookRepository bookRepository;
	@GetMapping("/books/{bookId}")
	public ResponseEntity<Book>  getBooks(@PathVariable String bookId) {
		Optional<Book> book = bookRepository.findById(bookId);
		if(book.isPresent()) {
			return ResponseEntity.ok(book.get());
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Book Is Not Found");
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>>  getBooks() {
		List<Book> book = (List<Book>) bookRepository.findAll();
		return ResponseEntity.ok(book);
	}
	
	@PostMapping(value="/books/create")
	public String  getBooks(@RequestBody Book book) {
		Book b = bookRepository.save(book);
		return "http://localhost:8080/books"+b.getId();
	
	}
	@PutMapping(value="/books/update")
	public ResponseEntity<Book>  updateBooks(@RequestBody Book book)  {
		Book b = bookRepository.save(book);
		return ResponseEntity.ok(b);
	
	}
	
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Boolean>  deleteBooks(@PathVariable String bookId) {
		try {
			 bookRepository.deleteById(bookId);
			 return  ResponseEntity.ok(true);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	
	}
	

}
