package testcase.library.error;

public class CantDelete extends LibraryException {
    public CantDelete(String type, Long id) {
        super(String.format("Can't delete %s #%d", type, id), type, id);
    }
}
