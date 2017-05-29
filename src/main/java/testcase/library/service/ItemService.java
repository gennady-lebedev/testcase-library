package testcase.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testcase.library.entity.Book;
import testcase.library.entity.Item;
import testcase.library.entity.ItemStatus;
import testcase.library.entity.User;
import testcase.library.error.CantDelete;
import testcase.library.error.IncompatibleItemStatus;
import testcase.library.repository.ItemLogRepository;
import testcase.library.repository.ItemRepository;

@Service
public class ItemService {

    @ItemAudit
    public Item makeDraft(Book book) {
        Item item = new Item();
        item.setBook(book);
        item.setStatus(ItemStatus.DRAFT);
        return itemRepository.save(item);
    }

    public Item updateBook(Item item, Book book) {
        if(item.getStatus() == ItemStatus.ON_HANDS)
            throw new IncompatibleItemStatus(item, ItemStatus.ON_HANDS); //TODO change exception
        item.setBook(book);
        return itemRepository.save(item);
    }

    @ItemAudit
    public Item hideItem(Item item) {
        item.setStatus(ItemStatus.DRAFT);
        return itemRepository.save(item);
    }

    @ItemAudit
    public Item giveItem(Item item, User user) {
        item.setStatus(ItemStatus.ON_HANDS);
        item.setHolder(user);
        return itemRepository.save(item);
    }

    @ItemAudit
    public Item returnItem(Item item) {
        item.setStatus(ItemStatus.RETURNED);
        item.setHolder(null);
        return itemRepository.save(item);
    }

    @ItemAudit
    public Item putOnShelf(Item item, String place) {
        item.setStatus(ItemStatus.ON_SHELF);
        if(place != null && !place.equals(item.getPlace())) {
            item.setPlace(place);
        }
        return itemRepository.save(item);
    }

    @ItemAudit
    public Item deleteItem(Item item) {
        item.setStatus(ItemStatus.DELETED);
        return itemRepository.save(item);
    }

    public void purgeItem(Item item) {
        if(item.getStatus() == ItemStatus.DELETED) {
            if(logRepository.countAllByItemAndStatusNot(item, ItemStatus.DRAFT) == 0) {
                itemRepository.delete(item);
            }
        }

        throw new CantDelete("item", item.getId()); //TODO add validation binding errors
    }

    private final ItemRepository itemRepository;
    private final ItemLogRepository logRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemLogRepository logRepository) {
        this.itemRepository = itemRepository;
        this.logRepository = logRepository;
    }
}
