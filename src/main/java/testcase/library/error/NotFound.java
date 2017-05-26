package testcase.library.error;

import lombok.Getter;

@Getter
public class NotFound extends RuntimeException {
    private String type;
    private Long id;

    public NotFound(String type, Long id) {
        super(String.format("Can't find %s #%d", type, id));
        this.type = type;
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
