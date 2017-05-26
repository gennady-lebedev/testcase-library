package testcase.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import testcase.library.entity.Item;
import testcase.library.error.NotFound;

@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item, Long> {
    default Item getById(Long id) {
        Item found = this.findOne(id);
        if(found == null) {
            throw new NotFound("item", id);
        }
        return found;
    }
}
