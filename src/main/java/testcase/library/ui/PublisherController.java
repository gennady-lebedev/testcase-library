package testcase.library.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import testcase.library.entity.Publisher;
import testcase.library.repository.PublisherRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherRepository repository;

    @Autowired
    public PublisherController(PublisherRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getPublishers(Model model, Pageable pageable) {
        model.addAttribute("publishers", repository.findAll(pageable));
        return "publishers-list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getPublisher(@PathVariable("id") Long id, Model model) {
        Publisher publisher = repository.findOne(id);
        if(publisher == null)
            return "404";

        model.addAttribute("publisher", publisher);
        return "publisher";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updatePublisher(@Valid Publisher publisher, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("publisher", publisher);
            return "publisher";
        }

        Publisher saved = repository.save(publisher);
        return "redirect:/publishers/" + saved.getId();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPublisher(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publisher";
    }
}
