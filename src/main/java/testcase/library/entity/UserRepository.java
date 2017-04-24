package testcase.library.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource //TODO not safe, replace when security implemented
public interface UserRepository extends CrudRepository<User, String> {
}