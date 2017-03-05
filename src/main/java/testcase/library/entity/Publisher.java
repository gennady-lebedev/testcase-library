package testcase.library.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "publishers")
public class Publisher {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;
}
