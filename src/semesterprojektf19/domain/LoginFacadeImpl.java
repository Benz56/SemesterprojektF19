package semesterprojektf19.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import semesterprojektf19.domain.accesscontrol.Role;
import semesterprojektf19.persistence.Persistence;

public class LoginFacadeImpl implements LoginFacade {

    private final CitizenManager citizens;
    private Worker worker;

    public LoginFacadeImpl() {
        citizens = new CitizenManager();
    }

    @Override
    public Map<String, String> login(String username, String password) {
        Map<String, String> details = new HashMap<>();
        if (username.equals("admin") && password.equals("admin")) { //Tillader at logge ind uden bruger.
            details.put("role", "admin");
        } else {
            Object person = Persistence.INSTANCE.authenticate(username, password);
            worker = person != null ? (Worker) person : null;
            if (this.worker != null) {
                worker.addCase(new Case(worker, new Citizen(UUID.randomUUID(), "Poul", "Jensen", "01-08-1997", 4535, "Ryttergade", 23543434, Role.CITIZEN), new Inquiry("Hej")));
                worker.addCase(new Case(worker, new Citizen(UUID.randomUUID(), "sdf", "sdf", "01-08-1997", 4535, "sdf", 23543434, Role.CITIZEN), new Inquiry("sfsd")));
                worker.addCase(new Case(worker, new Citizen(UUID.randomUUID(), "klih", "gdf", "01-08-1997", 4535, "dsf", 23543434, Role.CITIZEN), new Inquiry("sdfdshh")));
                details.put("role", worker.getRole().toString().toLowerCase());
                details.put("firstname", worker.getFirstName());
                details.put("lastname", worker.getLastName());
                details.put("citizens", worker.getCitizens().stream().map(Citizen::toString).collect(Collectors.joining()));
                //Her skal tilføjes bogsted og fagområder.
            }
        }
        return details.isEmpty() ? null : details;
    }

    /*@Override
    public void createCase(String firstName, String lastName, String birthday, String controlNumber, int phoneNumber, String address, String shortInfo) {
        Citizen citizen = citizens.createCitizen(new Citizen(firstName, lastName, birthday, controlNumber, phoneNumber, address));
        Case case1 = new Case(new Inquiry(shortInfo));
        citizen.addCase(case1);
        // Tilføj sag til aktuel sagsbehandlers liste
    }*/
}
