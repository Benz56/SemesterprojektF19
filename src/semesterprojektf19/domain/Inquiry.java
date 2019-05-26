package semesterprojektf19.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gruppe 22 på SE/ST E19, MMMI, Syddansk Universitet
 */
public class Inquiry implements Serializable {

    private String shortInfo, background, origin;
    private List<Services> selectedServices = new ArrayList<>();
    private List<Offers> selectedOffers = new ArrayList<>();
    private boolean citizenAgreed;

    public Inquiry(String shortInfo) {
        this(shortInfo, null, null, false);
    }

    public Inquiry(String shortInfo, String background, String origin, boolean citizenAgreed) {
        this.shortInfo = shortInfo;
        this.background = background;
        this.origin = origin;
        this.citizenAgreed = citizenAgreed;
    }

    public String getShortInfo() {
        return shortInfo;
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public List<Services> getServices() {
        return selectedServices;
    }

    public void setServices(ArrayList services) {
        this.selectedServices = services;
    }

    public List<Offers> getOffers() {
        return selectedOffers;
    }

    public void setOffers(ArrayList offers) {
        this.selectedOffers = offers;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isCitizenAgreed() {
        return citizenAgreed;
    }

    public void setCitizenAgreed(boolean citizenAgreed) {
        this.citizenAgreed = citizenAgreed;
    }

    public void addService(Services service) {
        selectedServices.add(service);
    }

    public void removeService(Services service) {
        selectedServices.remove(service);
    }

    public void addOffer(Offers offer) {
        selectedOffers.add(offer);
    }

    public void removeOffer(Offers offer) {
        selectedOffers.remove(offer);
    }
}
