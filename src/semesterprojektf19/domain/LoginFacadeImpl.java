package semesterprojektf19.domain;

import semesterprojektf19.aquaintance.UserContainer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import semesterprojektf19.aquaintance.Column;
import semesterprojektf19.domain.accesscontrol.Role;
import semesterprojektf19.persistence.PersistenceFacade;
import semesterprojektf19.persistence.PersistenceFacadeImpl;

public class LoginFacadeImpl implements LoginFacade {

    PersistenceFacade persistenceFacade = new PersistenceFacadeImpl();

    @Override
    public Map<String, String> login(String username, String password) {
        Map<String, String> details = new HashMap<>();
        if (username.equals("admin") && password.equals("admin")) { //Tillader at logge ind uden bruger.
            details.put("role", "admin");
            details.put("fname", "admin");
            details.put("lname", "admin");
        } else {
            details = persistenceFacade.authenticate(username, password);
            System.out.println("Details returned: " + details);
            if (details != null) {
                UserContainer.setUser(new Worker(
                    UUID.fromString(details.get(Column.UUID.getColumnName())),
                    details.get(Column.FNAME.getColumnName()), 
                    details.get(Column.LNAME.getColumnName()),
                    Role.valueOf(details.get("role").toUpperCase()),
                    new Institution(details.get("instituion"), (details.get("instituionaddr"))
                    )));
            }
        }
        return details;
    }
}
