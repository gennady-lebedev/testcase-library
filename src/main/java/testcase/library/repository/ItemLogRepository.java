package testcase.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import testcase.library.entity.Item;
import testcase.library.entity.ItemLog;
import testcase.library.entity.ItemStatus;
import testcase.library.error.NotFound;

@RepositoryRestResource
public interface ItemLogRepository extends JpaRepository<ItemLog, Long> {
    int countAllByItemAndStatusNot(Item item, ItemStatus status);

    default ItemLog getById(Long id) {
        ItemLog found = this.findOne(id);
        if(found == null) {
            throw new NotFound("log", id);
        }
        return found;
    }
}
