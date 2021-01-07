package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This converts this class to a spring mvc controller
 */
@Controller
public class BookController {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
/** Because I have a constructor and this is a managed component, spring will create an instance of this bookRepository when its initialized. Spring
 *  will inject an instance of bookRepo into our controller
 * **/
	public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}
	/** Request mapping refers to the url that you want to set just like routes in nodejs **/
	/** We pass in the model which will give us the model object. This model is what will be returned to the view after you do something to it or smth
	 *  So when the /books url is entered, spring will run this getBooks method
	 * **/
	@RequestMapping("/books")
	public String getBooks(Model model) {
    /**
     * addAttribute refers to naming this model call books and giving it a list of books .findAll()
     * is from the extention of the CrudRepository in our interface *
     */
    System.out.println(bookRepository.findAll());
		model.addAttribute("books", bookRepository.findAll());
/** Adding the /list at the end tells sping to look for the list template inside the directory books **/
		return "books/list";
	}

//	@RequestMapping("/authors")
//	public String getAuthors(Model model) {
//    System.out.println(authorRepository.findAll());
//		model.addAttribute("authors", authorRepository.findAll());
//
//		return "authors/list";
//	}
}
