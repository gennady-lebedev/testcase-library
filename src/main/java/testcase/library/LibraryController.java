package testcase.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.MetricsEndpointMetricReader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testcase.library.entity.Item;
import testcase.library.entity.ItemRepository;

@RestController
@RequestMapping("/api")
public class LibraryController {

    private final ItemRepository repository;

    @Autowired
    public LibraryController(ItemRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/items/{id}")
    public Item getItem(@PathVariable("id") Long id) {
        return repository.findOne(id);
    }
}
