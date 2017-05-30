package testcase.library.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import testcase.library.error.LibraryException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
class GlobalDefaultExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView
    defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        if(e instanceof LibraryException) {
            LibraryException le = (LibraryException) e;
            mav.addObject("type", le.getType());
            mav.addObject("id", le.getId());
        }
        mav.setViewName(DEFAULT_ERROR_VIEW);
        log.error("Exception handled in request {}: ",req, e);
        return mav;
    }
}
