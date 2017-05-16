package testcase.library.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import testcase.library.repository.AuthorRepository;
import testcase.library.repository.ItemLogRepository;
import testcase.library.repository.ItemRepository;
import testcase.library.repository.PublisherRepository;

@Controller
public class ItemController {

    private final ItemRepository itemRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final ItemLogRepository logRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository, ItemLogRepository logRepository) {
        this.itemRepository = itemRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.logRepository = logRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String getItems(Model model, Pageable pageable) {
        model.addAttribute("items", itemRepository.findAll(pageable));
        return "items-list";
    }

    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public String getAuthors(Model model, Pageable pageable) {
        model.addAttribute("authors", authorRepository.findAll(pageable));
        return "authors-list";
    }

    @RequestMapping(value = "/publishers", method = RequestMethod.GET)
    public String getPublishers(Model model, Pageable pageable) {
        model.addAttribute("publishers", publisherRepository.findAll(pageable));
        return "publishers-list";
    }

    @RequestMapping(value = "/logs", method = RequestMethod.GET)
    public String getLogs(Model model, Pageable pageable) {
        model.addAttribute("logs", logRepository.findAll(pageable));
        return "logs-list";
    }
}
