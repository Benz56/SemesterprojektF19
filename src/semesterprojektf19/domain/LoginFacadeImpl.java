package semesterprojektf19.domain;

import semesterprojektf19.aquaintance.UserContainer;
import java.util.HashMap;
import java.util.Map;
import semesterprojektf19.domain.accesscontrol.Role;
import semesterprojektf19.persistence.Persistence;
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
            UserContainer.setUser(new Worker(
                    details.get("uuid"),
                    details.get("fname"), 
                    details.get("lname"),
                    details.get("bday"),
                    details.get("cnumber"),
                    details.get("addr"),
                    details.get("phone"),
                    Role.valueOf(details.get("role").toUpperCase()),
                    new Institution(details.get("instituion"), (details.get("instituionaddr"))
                    )));
        }
        return details.isEmpty() ? null : details;
    }

    public Map<String, String> loginOld(String username, String password) {
        Map<String, String> details = new HashMap<>();
        if (username.equals("admin") && password.equals("admin")) { //Tillader at logge ind uden bruger.
            details.put("role", "admin");
            details.put("firstname", "admin");
            details.put("lastname", "admin");
        } else if (Persistence.INSTANCE.authenticateDB(username, password)) {
            details.put("role", "admin");
            details.put("firstname", "admin");
            details.put("lastname", "admin");
        } else {
            Object person = Persistence.INSTANCE.authenticate(username, password);
            if (person != null) {
                Worker worker = (Worker) person;
                UserContainer.setUser(worker);
                details.put("role", worker.getRole().toString().toLowerCase());
                details.put("firstname", worker.getFirstName());
                details.put("lastname", worker.getLastName());
                //Her skal tilføjes bosted
            }
        }
        return details.isEmpty() ? null : details;
    }
}
