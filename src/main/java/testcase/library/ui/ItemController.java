package testcase.library.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import testcase.library.entity.Item;
import testcase.library.repository.BookRepository;
import testcase.library.repository.ItemLogRepository;
import testcase.library.repository.ItemRepository;

import javax.validation.Valid;

@Controller
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemLogRepository logRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository, ItemLogRepository logRepository, BookRepository bookRepository) {
        this.itemRepository = itemRepository;
        this.logRepository = logRepository;
        this.bookRepository = bookRepository;
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

    @RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
    public String getItem(@PathVariable("id") Long id, Model model) {
        Item item = itemRepository.findOne(id);
        if(item == null)
            return "404";

        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("item", item);
        return "item";
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public String updateItem(@Valid Item item, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("books", bookRepository.findAll());
            model.addAttribute("item", item);
            return "item";
        }

        if(item.getId() != null) {
            Item found = itemRepository.findOne(item.getId());
            if(item.getHolder() == null && found.getHolder() != null) {
                item.setHolder(found.getHolder());
            }
        }

        Item saved = itemRepository.save(item);
        return "redirect:/items/" + saved.getId();
    }

    @RequestMapping(value = "/items/new", method = RequestMethod.GET)
    public String newItemPage(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute(new Item());
        return "item";
    }

    @RequestMapping(value = "/logs", method = RequestMethod.GET)
    public String getLogs(Model model, Pageable pageable) {
        model.addAttribute("logs", logRepository.findAll(pageable));
        return "logs-list";
    }
}
