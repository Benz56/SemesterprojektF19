package semesterprojektf19.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Institution implements Serializable {

    private String name;
    private String adress;
    private Map<UUID, Worker> workers;

    public Institution(String name, String adress) {
        this.name = name;
        this.adress = adress;
        this.workers = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<UUID, Worker> getWorkers() {
        return workers;
    }

}
