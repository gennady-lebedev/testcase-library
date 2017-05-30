package testcase.library.error;

import lombok.Getter;

@Getter
public class NotFound extends LibraryException {
    public NotFound(String type, Long id) {
        super(String.format("Can't find %s #%d", type, id), type, id);
    }
}
