package testcase.library.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import testcase.library.entity.Author;
import testcase.library.repository.AuthorRepository;

import javax.validation.Valid;

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
        model.addAttribute("authors", repository.findAll(pageable));
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

    @RequestMapping(method = RequestMethod.POST)
    public String updateAuthor(@Valid Author author, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("author", author);
            return "author";
        }

        Author saved = repository.save(author);
        return "redirect:/authors/" + saved.getId();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAuthorPage(Model model) {
        model.addAttribute(new Author());
        return "author";
    }
}
