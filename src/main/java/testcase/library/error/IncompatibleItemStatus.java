package testcase.library.error;

import lombok.Getter;
import testcase.library.entity.Item;
import testcase.library.entity.ItemStatus;

@Getter
public class IncompatibleItemStatus extends RuntimeException {
    private Item item;
    private ItemStatus newStatus;

    public IncompatibleItemStatus(Item item, ItemStatus newStatus) {
        super(String.format(
                "Can't change item #%d status '%s' to '%s'",
                item.getId(),
                item.getStatus(),
                newStatus
        ));
        this.item = item;
        this.newStatus = newStatus;
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
