package testcase.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import testcase.library.entity.Publisher;

@RepositoryRestResource
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
