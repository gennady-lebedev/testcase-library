package testcase.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import testcase.library.entity.User;
import testcase.library.error.NotFound;

@RepositoryRestResource //TODO not safe, replace when security implemented
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    default User getById(Long id) {
        User found = this.findOne(id);
        if(found == null) {
            throw new NotFound("user", id);
        }
        return found;
    }
}
