package testcase.library.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import testcase.library.entity.Author;
import testcase.library.repository.AuthorRepository;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorRepository repository;

    @Autowired
    public AuthorController(AuthorRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAuthors(Model model, Pageable pageable) {
        model.addAttribute("books", repository.findAll(pageable));
        return "authors-list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getAuthor(@PathVariable("id") Long id, Model model) {
        Author author = repository.findOne(id);
        if(author == null)
            return "404";

        model.addAttribute("author", author);
        return "author";
    }
}
