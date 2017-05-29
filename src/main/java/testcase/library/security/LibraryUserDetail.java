package testcase.library.security;

import testcase.library.entity.User;


public class LibraryUserDetail extends org.springframework.security.core.userdetails.User {
    private User user;

    public LibraryUserDetail(User user) {
        super(user.getName(), user.getPassword(), user.getAuthorities());
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
