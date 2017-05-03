package testcase.library.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "entity_logs")
@Data
public class ItemLog {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @Column(name = "item_id")
    private Item item;

    @ManyToOne
    @Column(name = "user_id")
    private User who; // operator/librarian/admin only

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date when;

    private String what;
}
