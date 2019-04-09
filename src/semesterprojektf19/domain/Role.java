package semesterprojektf19.domain;

import java.util.Arrays;
import java.util.List;

public enum Role {
    EMPLOYEE("permissions", "skrives", "her"), //TODO implementer permisions.
    ADMIN("*");

    private final List<String> permissions;

    private Role(String... permissions) {
        this.permissions = Arrays.asList(permissions);
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission) || permissions.contains("*");
    }
}
