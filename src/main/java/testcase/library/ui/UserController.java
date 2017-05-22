package testcase.library.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import testcase.library.entity.User;
import testcase.library.repository.UserRepository;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserController(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin() {
        return "login";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model, Pageable pageable) {
        model.addAttribute("users", repository.findAll(pageable));
        return "users-list";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable("id") Long id, Model model) {
        User found = repository.findOne(id);
        if(found == null) {
            return "404";
        }

        model.addAttribute("user", found);
        return "user";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "user";
        }

        if(user.getId() != null) {
            User found = repository.findOne(user.getId());
            if(found != null) {
                user.setPassword(found.getPassword());
            } else {
                user.setPassword(encoder.encode(user.getPassword()));
            }
        }
        User saved = repository.save(user);
        return "redirect:/users/" + saved.getId();
    }

    @RequestMapping(value = "/users/new", method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String currentUser(@AuthenticationPrincipal(errorOnInvalidType = true) UserDetails principal, Model model) {
        System.out.println(principal);
        User user = repository.findByName(principal.getUsername());
        if(user == null)
            return "404";
        model.addAttribute("user", user);
        return "user";
    }
}
