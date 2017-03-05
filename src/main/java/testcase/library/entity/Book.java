package testcase.library.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "isbn")
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
}
