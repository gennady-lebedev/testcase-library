package testcase.library.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRoles role;

    public final List<GrantedAuthority> getAuthorities() {
        if(role == UserRoles.ADMIN) {
            return AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_LIBRARIAN");
        } else if(role == UserRoles.LIBRARIAN) {
            return AuthorityUtils.createAuthorityList("ROLE_LIBRARIAN");
        } else {
            return AuthorityUtils.createAuthorityList("ROLE_READER");
        }
    }
}
