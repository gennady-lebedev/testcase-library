package testcase.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import testcase.library.entity.User;

@RepositoryRestResource //TODO not safe, replace when security implemented
public interface UserRepository extends JpaRepository<User, String> {
    User findByName(String name);
}
