package testcase.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import testcase.library.entity.Item;
import testcase.library.entity.ItemStatus;
import testcase.library.error.NotFound;

import java.time.LocalDate;

@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findAllByStatusAndDueDateLessThan(ItemStatus status, LocalDate dueDate, Pageable pageable);

    Page<Item> findAllByStatus(ItemStatus status, Pageable pageable);

    default Item getById(Long id) {
        Item found = this.findOne(id);
        if(found == null) {
            throw new NotFound("item", id);
        }
        return found;
    }
}
