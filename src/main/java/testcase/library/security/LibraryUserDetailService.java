package testcase.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import testcase.library.entity.User;
import testcase.library.repository.UserRepository;
import testcase.library.entity.UserRoles;

import java.util.List;

@Component
public class LibraryUserDetailService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public LibraryUserDetailService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = repository.findByName(username);
        if(user == null)
            throw new UsernameNotFoundException("User with name '" +
                    username + "' does not exist");


        return new LibraryUserDetail(user);
    }
}
