package testcase.library.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "item_logs")
@Data
public class ItemLog {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "item_status")
    private ItemStatus status;

    @ManyToOne
    @JoinColumn(name = "item_holder")
    private User holder;

    @Column(name = "item_due_date")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "made_by")
    private User madeBy;

    private LocalDateTime timestamp;
}
