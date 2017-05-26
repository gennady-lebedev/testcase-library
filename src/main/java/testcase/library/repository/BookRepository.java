package testcase.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import testcase.library.entity.Book;
import testcase.library.error.NotFound;

@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Long> {
    default Book getById(Long id) {
        Book found = this.findOne(id);
        if(found == null) {
            throw new NotFound("book", id);
        }
        return found;
    }
}
