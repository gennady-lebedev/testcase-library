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

    public void setStatus(ItemStatus status) {
        if(this.status != null && !this.status.isCompatible(status)) {
            throw new IncompatibleItemStatus(this, status);
        }
        this.status = status;
    }
}
