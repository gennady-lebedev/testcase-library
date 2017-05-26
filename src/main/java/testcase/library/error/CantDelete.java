package testcase.library.error;

public class CantDelete extends RuntimeException {
    private String type;
    private Long id;

    public CantDelete(String type, Long id) {
        super(String.format("Can't delete %s #%d", type, id));
        this.type = type;
        this.id = id;
    }
}
