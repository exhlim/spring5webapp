package guru.springframework.spring5webapp.repositories;

import guru.springframework.spring5webapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

/**
 * Creating a package called repositories and extend the class to CrudRepository (Id, Type)
 * Exteneding the CrudRepository allows you to access all the methods the findById, existsById, save, saveAll, delete, deleteAll
 */
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
