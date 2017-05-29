package testcase.library.service;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import testcase.library.entity.Item;
import testcase.library.security.LibraryUserDetail;

@Aspect
@Component
public class LogItemAdvice {

    @Pointcut("@annotation(ItemAudit) && execution(testcase.library.entity.Item testcase.library.service.ItemService.*(..))")
    public void getPointcut(){}

    @AfterReturning(pointcut = "getPointcut()", returning = "item")
    public void logItem(Item item) {
        LibraryUserDetail principal = (LibraryUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.logItem(item, principal.getUser());
    }

    private final LogService service;

    public LogItemAdvice(LogService service) {
        this.service = service;
    }
}
