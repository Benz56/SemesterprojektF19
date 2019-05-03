package semesterprojektf19.domain;

import java.util.HashMap;
import java.util.Map;
import semesterprojektf19.persistence.Persistence;

public class LoginFacadeImpl implements LoginFacade {

    @Override
    public Map<String, String> login(String username, String password) {
        Map<String, String> details = new HashMap<>();
        if (username.equals("admin") && password.equals("admin")) { //Tillader at logge ind uden bruger.
            details.put("role", "admin");
            details.put("firstname", "admin");
            details.put("lastname", "admin");
        } else if(Persistence.INSTANCE.authenticateDB(username, password)) {
            details.put("role", "admin");
            details.put("firstname", "admin");
            details.put("lastname", "admin");
        } 
        else {
            Object person = Persistence.INSTANCE.authenticate(username, password);
            if (person != null) {
                Worker worker = (Worker) person;
                UserContainer.setUser(worker);
                details.put("role", worker.getRole().toString().toLowerCase());
                details.put("firstname", worker.getFirstName());
                details.put("lastname", worker.getLastName());
                //Her skal tilføjes bogsted og fagområder.
            }
        }
        return details.isEmpty() ? null : details;
    }
}
