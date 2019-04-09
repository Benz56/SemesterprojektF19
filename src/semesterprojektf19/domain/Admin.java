package semesterprojektf19.domain;

import java.util.UUID;

public class Admin extends Person {

    public Admin(UUID uuid, String firstName, String lastName, int phoneNumber, String address, Role role) {
        super(uuid, firstName, lastName, phoneNumber, address, role);
    }
}
