package testcase.library.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ItemLogRepository extends JpaRepository<ItemLog, Long> {
}
