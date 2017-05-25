package testcase.library.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum ItemStatus {
    DRAFT, ON_SHELF, ON_HANDS, RETURNED, DELETED;

    private List<ItemStatus> allowedToSwitch = new ArrayList<>(3);

    static {
        Collections.addAll(DRAFT.allowedToSwitch, ON_SHELF, DELETED);
        Collections.addAll(ON_SHELF.allowedToSwitch, DRAFT, ON_HANDS, DELETED);
        Collections.addAll(ON_HANDS.allowedToSwitch, RETURNED, DELETED);
        Collections.addAll(RETURNED.allowedToSwitch, DRAFT, ON_SHELF);
        Collections.addAll(DELETED.allowedToSwitch, DRAFT);
    }

    public boolean isCompatible(ItemStatus newStatus) {
        return allowedToSwitch.contains(newStatus);
    }
}
