package testcase.library.entity;

public enum UserRoles {
    ADMIN("ROLE_ADMIN"),
    LIBRARIAN("ROLE_LIBRARIAN"),
    READER("ROLE_READER");

    private final String roleName;
    UserRoles(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
