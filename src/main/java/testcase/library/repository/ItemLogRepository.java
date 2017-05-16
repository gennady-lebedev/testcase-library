package testcase.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import testcase.library.entity.ItemLog;

@RepositoryRestResource
public interface ItemLogRepository extends JpaRepository<ItemLog, Long> {
}
