package testcase.library.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import testcase.library.entity.Book;
import testcase.library.repository.AuthorRepository;
import testcase.library.repository.BookRepository;
import testcase.library.repository.PublisherRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
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
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("publishers", publisherRepository.findAll());
        return "book";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateBook(@Valid Book book, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            model.addAttribute("authors", authorRepository.findAll());
            model.addAttribute("publishers", publisherRepository.findAll());
            return "book";
        }

        Book saved = bookRepository.save(book);
        return "redirect:/books/" + saved.getId();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("publishers", publisherRepository.findAll());
        return "book";
    }
}
