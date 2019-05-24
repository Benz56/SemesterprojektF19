package semesterprojektf19.domain.accesscontrol;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public enum Role {
    CASEWORKER(),
    SOCIALWORKER(),
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
