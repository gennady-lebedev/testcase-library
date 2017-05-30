package testcase.library.service;

import org.springframework.stereotype.Service;
import testcase.library.entity.Item;
import testcase.library.entity.ItemLog;
import testcase.library.entity.User;
import testcase.library.repository.ItemLogRepository;

import java.time.LocalDateTime;

@Service
public class LogService {
    public void logItem(Item item, User user) {
        ItemLog log = new ItemLog();
        log.setItem(item);
        log.setStatus(item.getStatus());
        log.setHolder(item.getHolder());
        log.setDueDate(item.getDueDate());
        log.setTimestamp(LocalDateTime.now());
        log.setMadeBy(user);
        repository.save(log);
    }

    private final ItemLogRepository repository;

    public LogService(ItemLogRepository repository) {
        this.repository = repository;
    }
}
