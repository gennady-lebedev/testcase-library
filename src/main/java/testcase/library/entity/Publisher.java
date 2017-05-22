package testcase.library.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "publishers")
public class Publisher {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    @NotNull
    @Size(min = 3, max = 100)
    private String title;
}
