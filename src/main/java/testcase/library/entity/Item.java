package testcase.library.entity;

import lombok.Data;
import testcase.library.error.IncompatibleItemStatus;

import javax.persistence.*;

@Entity
@Data
@Table(name = "items")
public class Item {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User holder;

    @Column(name = "place")
    private String place;

    public void setStatus(ItemStatus newStatus) {
        if(!status.isCompatible(newStatus)) {
            throw new IncompatibleItemStatus(this, newStatus);
        }
    }
}
