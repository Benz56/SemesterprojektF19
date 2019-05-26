package semesterprojektf19.acquaintance;

import semesterprojektf19.domain.Person;

/**
 *
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public class UserContainer {

    public static Person user;

    /**
     * @return the logged in user or null if Admin.
     */
    public static Person getUser() {
        return user;
    }

    public static void setUser(Person user) {
        UserContainer.user = user;
    }
}
