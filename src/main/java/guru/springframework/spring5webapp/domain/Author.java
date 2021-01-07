package guru.springframework.spring5webapp.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 1) After setting up the Author and the Book class, this are the 2 objects that we will be persisting into the database with JPA
 * 		These are known as POJO [Plain Old Java Object]
 * 2) Adding Id's to the classes to convert them to JPA entities [JPA entities can be persisted into the database by hibernate]
 * 		JPA is an object relational mapping tool (Usually there will be leakage)
 * 		Adding Id so they know how to store and retrieve them
 * 		1. Add @Entity to the top of your models [This tells intelliJ that these are JPA entities]
 * 		2. Add id to each class and define them as the @Id & @GeneratedValue (How hibernate will it will generated)
 * 3)
 */

@Entity
public class Author {
	/**
	 * Setting up the ID and the GeneratedValue for each class
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;
	private String lastName;

	/**
	 * Set the many to many relationships
	 * mappedBy authors [Look inside the book class]
	 */
	@ManyToMany(mappedBy = "authors")
	private Set<Book> books = new HashSet<>();

//	0 Args constructor
	public Author() {

	}
// Standard constructor
	public Author (String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author{" +
			"id=" + id +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Author author = (Author) o;

		return id != null ? id.equals(author.id) : author.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
