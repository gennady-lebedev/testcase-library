package testcase.library.service;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import testcase.library.entity.Item;
import testcase.library.error.LibraryException;
import testcase.library.security.LibraryUserDetail;

@Aspect
@Component
public class LogItemAdvice {

    @Pointcut("@annotation(ItemAudit) && execution(testcase.library.entity.Item testcase.library.service.ItemService.*(..))")
    public void getPointcut(){}

    @AfterReturning(pointcut = "getPointcut()", returning = "item")
    public void logItem(Item item) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof LibraryUserDetail) {
            LibraryUserDetail userDetail = (LibraryUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            service.logItem(item, userDetail.getUser());
        } else {
            throw new LibraryException(
                    "Unauthorized access to the item #" + item.getId(),
                    "item",
                    item.getId()
            );
        }
    }

    private final LogService service;

    public LogItemAdvice(LogService service) {
        this.service = service;
    }
}
