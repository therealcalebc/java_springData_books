/**
 * 
 */
package cd.java.springdata.mvc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import cd.java.springdata.mvc.models.Book;
import cd.java.springdata.mvc.services.BookService;

/**
 * @author ccomstock
 *
 */
@Controller
public class BooksController {
	
	private final BookService bookService;
	
	public BooksController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/books")
	public String index(Model model) {
		List<Book> books = bookService.allBooks();
		model.addAttribute("books", books);
		return "books/index.jsp";
	}
	
	@GetMapping("/books/new")
	public String newBook(@ModelAttribute Book book) {
		return "books/new.jsp";
	}
	
	@PostMapping("/books")
	public String create(@Valid @ModelAttribute Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "books/new.jsp";
		} else {
			bookService.createBook(book);
			return "redirect:/books";
		}
	}
	
	@GetMapping("/books/{id}")
	public String show(@PathVariable Long id, Model model) {
		Book book = bookService.findBook(id);
		if(book == null) {
			return "redirect:/books";
		} else {
			model.addAttribute("book", book);
			return "books/show.jsp";
		}
	}
	
	@GetMapping("/books/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		Book book = bookService.findBook(id);
		model.addAttribute("book", book);
		return "/books/edit.jsp";
	}
	
	@PutMapping("/books/{id}")
	public String update(@Valid @ModelAttribute Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "/books/edit.jsp";
		} else {
			bookService.updateBook(book);
			return "redirect:/books";
		}
	}
	
	@DeleteMapping("/books/{id}")
	public String remove(@PathVariable Long id) {
		bookService.deleteBook(id);
		return "redirect:/books";
	}
	
}
