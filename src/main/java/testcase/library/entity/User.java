package testcase.library.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRoles role;
}
