package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Implementing the CommandLineRunner is so that when spring any of these implementation, Spring is going to run them
 * Impement its run method
 * Define it as a Compoenent so that spring detects it as a management component
 */
@Component
public class BootStrapData implements CommandLineRunner {
	/**
	 * Adding this 2 interface so that when spring implements this component, it will execute the dependency injection
	 * and create an instance of the AuthorRepo and the BookRepo
	 */
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Started in Bootstrap");

		Publisher publisher = new Publisher("Eugene", "Singapore", "Singapore","Jurong","5ss123123");
		publisherRepository.save(publisher);

		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Design", "123123");
		/**
		 * Create and instance of each of the class
		 * getBooks method here returns the HashSet
		 */
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);

		/**
		 * Use the authorRepo and the bookRepo and the JPA predefined methods to save the instances which you just create
		 * into the H2 Database
		 */
		authorRepository.save(eric);
		bookRepository.save(ddd);

		Author rod = new Author("Rod", "Johnson");
		Book noEJB = new Book("J2EE Development without EJB", "123123123");

		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);

		authorRepository.save(rod);
		bookRepository.save(noEJB);

		ddd.setPublisher(publisher);
		publisher.getBooks().add(ddd);
		noEJB.setPublisher(publisher);
		publisher.getBooks().add(noEJB);
		publisherRepository.save(publisher);


    System.out.println("Number of Books: " + bookRepository.count());
    System.out.println("Number of publishers: " + publisherRepository.count());
    System.out.println("Publisher Numeber of Books: " + publisher.getBooks().size());
	}
}
