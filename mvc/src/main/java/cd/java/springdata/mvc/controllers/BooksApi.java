/**
 * 
 */
package cd.java.springdata.mvc.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cd.java.springdata.mvc.models.Book;
import cd.java.springdata.mvc.services.BookService;

/**
 * @author ccomstock
 *
 */
@RestController
public class BooksApi {
	
	private final BookService bookService;
	
	public BooksApi(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/api/books")
	public List<Book> readAll() {
		return bookService.allBooks();
	}
	
	@PostMapping("/api/books")
	public Book create(@RequestParam String title, @RequestParam(value="description") String desc, @RequestParam(value="language") String lang, @RequestParam(value="numberOfPages") Integer pages) {
		Book book = new Book(title, desc, lang, pages);
		return bookService.createBook(book);
	}
	
	@GetMapping("/api/books/{id}")
	public Book readOne(@PathVariable Long id) {
		Book book = bookService.findBook(id);
		return book;
	}
	
	@PutMapping("/api/books/{id}")
	public Book update(@PathVariable Long id, @RequestParam String title, @RequestParam(value="description") String desc, @RequestParam(value="language") String lang, @RequestParam(value="numberOfPages") Integer pages) {
		//Book book = bookService.updateBook(id, title, desc, lang, pages);
		Book book = bookService.findBook(id);
		if(book != null) {
			book.setTitle(title);
			book.setDescription(desc);
			book.setLanguage(lang);
			book.setNumberOfPages(pages);
		}
		return bookService.updateBook(book);
	}
	
	@DeleteMapping("/api/books/{id}")
	public void destroy(@PathVariable Long id) {
		bookService.deleteBook(id);
	}
	
}
