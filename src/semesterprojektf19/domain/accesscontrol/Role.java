package semesterprojektf19.domain.accesscontrol;

import java.util.Arrays;
import java.util.List;

public enum Role {
    EMPLOYEE(), //TODO implementer permisions.
    CITIZEN(),
    ADMIN(Permission.ALL);

    private final List<Permission> permissions;

    private Role(Permission... permissions) {
        this.permissions = Arrays.asList(permissions);
    }

    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission) || permissions.contains(Permission.ALL);
    }
}
