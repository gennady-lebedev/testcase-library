package testcase.library.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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

    private ItemStatus status;

    @ManyToOne
    @JoinColumn(name = "holder")
    private User holder;

    @ManyToOne
    @JoinColumn(name = "made_by")
    private User madeBy;

    private LocalDateTime timestamp;
}
