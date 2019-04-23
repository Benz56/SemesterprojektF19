/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

/**
 *
 * @author Benjamin Staugaard | Benz56
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
