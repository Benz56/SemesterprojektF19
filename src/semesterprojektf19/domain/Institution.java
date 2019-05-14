package semesterprojektf19.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Institution implements Serializable {

    private final Map<UUID, Worker> workers = new HashMap<>();

    private final String name;
    private final String address;

    public Institution(String name, String adress) {
        this.name = name;
        this.address = adress;
    }

    public String getName() {
        return name;
    }

    public Map<UUID, Worker> getWorkers() {
        return workers;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.name.equals(((Institution) obj).name);
    }
    
}
