package testcase.library.error;

import lombok.Getter;

@Getter
public class LibraryException extends RuntimeException {
    protected String type;
    protected Long id;

    public LibraryException(String type, Long id) {
        super(String.format("Something happens processing %s #%d", type, id));
        this.type = type;
        this.id = id;
    }

    public LibraryException(String message, String type, Long id) {
        super(message);
        this.type = type;
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
