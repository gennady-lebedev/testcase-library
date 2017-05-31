package testcase.library.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import testcase.library.entity.Book;
import testcase.library.entity.Item;
import testcase.library.entity.ItemStatus;
import testcase.library.entity.User;
import testcase.library.repository.BookRepository;
import testcase.library.repository.ItemLogRepository;
import testcase.library.repository.ItemRepository;
import testcase.library.repository.UserRepository;
import testcase.library.service.ItemService;

import java.time.LocalDate;

@Controller
@RequestMapping("/items")
public class ItemController {
    @GetMapping
    public String getItems(
            @RequestParam(name = "status", required = false) ItemStatus status,
            Model model,
            Pageable pageable
    ) {
        model.addAttribute("title", "All items");
        if(status == null) {
            model.addAttribute("items", itemRepository.findAll(pageable));
        } else {
            model.addAttribute("items", itemRepository.findAllByStatus(status, pageable));
        }
        return "items-list";
    }

    @GetMapping("/overdue")
    public String getOverdueItems(Model model, Pageable pageable) {
        model.addAttribute("title", "Overdue Items");
        model.addAttribute("items",
                itemRepository.findAllByStatusAndDueDateLessThan(ItemStatus.ON_HANDS, LocalDate.now(), pageable));
        return "items-list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getItem(@PathVariable("id") Long id, Model model, Pageable pageable) {
        Item item = itemRepository.getById(id);

        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("item", item);
        for(ItemStatus status: ItemStatus.values()) {
            model.addAttribute(status.name(), status);
        }
        model.addAttribute("logs", logRepository.findByItemOrderByTimestampDesc(item, pageable));

        if(item.getBook() != null) {
            model.addAttribute("title", item.getBook().getTitle() + " #" + item.getId()) ;
        } else {
            model.addAttribute("title", "New Item");
        }
        return "item";
    }

    @RequestMapping(value = "/make", method = RequestMethod.POST)
    public String makeDraft(@RequestParam("book") Long bookId) {
        Book book = bookRepository.getById(bookId);
        return "redirect:/items/" + itemService.makeDraft(book).getId();
    }

    @RequestMapping(value = "/{id}/hide", method = RequestMethod.POST)
    public String returnToDraft(@PathVariable("id") Long itemId) {
        Item item = itemRepository.getById(itemId);
        return "redirect:/items/" + itemService.hideItem(item).getId();
    }

    @RequestMapping(value = "/{id}/give", method = RequestMethod.POST)
    public String giveItem(@PathVariable("id") Long itemId, @RequestParam("user") Long userId) {
        Item item = itemRepository.getById(itemId);
        User user = userRepository.getById(userId);
        return "redirect:/items/" + itemService.giveItem(item, user).getId();
    }

    @RequestMapping(value = "/{id}/return", method = RequestMethod.POST)
    public String returnItem(@PathVariable("id") Long itemId) {
        Item item = itemRepository.getById(itemId);
        return "redirect:/items/" + itemService.returnItem(item).getId();
    }

    @RequestMapping(value = "/{id}/shelf", method = RequestMethod.POST)
    public String putOnShelf(@PathVariable("id") Long itemId, @RequestParam("place") String place) {
        Item item = itemRepository.getById(itemId);
        return "redirect:/items/" + itemService.putOnShelf(item, place).getId();
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String deleteItem(@PathVariable("id") Long itemId) {
        Item item = itemRepository.getById(itemId);
        return "redirect:/items/" + itemService.deleteItem(item).getId();
    }

    @RequestMapping(value = "/{id}/purge", method = RequestMethod.POST)
    public String purgeItem(@PathVariable("id") Long itemId) {
        Item item = itemRepository.getById(itemId);
        itemService.purgeItem(item);
        return "redirect:/items";
    }

    private final ItemRepository itemRepository;
    private final ItemLogRepository logRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemRepository itemRepository, ItemLogRepository logRepository, BookRepository bookRepository, UserRepository userRepository, ItemService itemService) {
        this.itemRepository = itemRepository;
        this.logRepository = logRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.itemService = itemService;
    }
}
