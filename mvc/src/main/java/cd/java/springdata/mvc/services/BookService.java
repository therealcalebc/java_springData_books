/**
 * 
 */
package cd.java.springdata.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cd.java.springdata.mvc.models.Book;
import cd.java.springdata.mvc.repositories.BookRepository;

/**
 * @author ccomstock
 *
 */
@Service
public class BookService {

	// adding the book repository as a dependency
	private final BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	// returns all the books
	public List<Book> allBooks() {
		return bookRepository.findAll();
	}
	
	// creates a book
	public Book createBook(Book b) {
		return bookRepository.save(b);
	}
	
	// retrieves a book
	public Book findBook(Long id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
			return null;
		}
	}
	
	// updates a book
	public Book updateBook(Long id, String title, String description, String language, Integer numberOfPages) {
		Book b = null;
		Optional<Book> optionalBook = bookRepository.findById(id);
		if(optionalBook.isPresent()) {
			b = optionalBook.get();
		} else {
			b = new Book();
		}
		b.setTitle(title);
		b.setDescription(description);
		b.setLanguage(language);
		b.setNumberOfPages(numberOfPages);
		return bookRepository.save(b);
	}
	
	// deletes a book
	public void deleteBook(Long id) {
		if(bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
		}
	}
	
}
