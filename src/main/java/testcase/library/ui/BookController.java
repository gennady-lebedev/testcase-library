package testcase.library.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import testcase.library.entity.Book;
import testcase.library.repository.BookRepository;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getBooks(Model model, Pageable pageable) {
        model.addAttribute("books", bookRepository.findAll(pageable));
        return "books-list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getBook(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findOne(id);
        if(book == null)
            return "404";

        model.addAttribute("book", book);
        return "book";
    }
}
