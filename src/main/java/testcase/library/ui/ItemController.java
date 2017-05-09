package testcase.library.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import testcase.library.entity.BookRepository;
import testcase.library.entity.ItemRepository;

@Controller
public class ItemController {

    private final ItemRepository itemRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository, BookRepository bookRepository) {
        this.itemRepository = itemRepository;
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String getItems(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "items-list";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String getBooks(Model model, Pageable pageable) {
        model.addAttribute("books", bookRepository.findAll(pageable));
        return "books-list";
    }
}
