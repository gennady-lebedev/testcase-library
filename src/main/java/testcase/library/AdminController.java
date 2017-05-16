package testcase.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import testcase.library.entity.User;
import testcase.library.entity.UserRepository;

@RestController
@RequestMapping("/api/security")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public AdminController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @RequestMapping(value = "/enlist-users", method = RequestMethod.GET)
    public Iterable<User> enlistUsers() {
        return userRepository.findAll();
    }
}
