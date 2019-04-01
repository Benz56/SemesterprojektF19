/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojektf19.domain;

import java.util.ArrayList;

/**
 *
 * @author hala_ & Soffi
 */
public class Inquiry {
    private String shortInfo; 
    private String background;
    private ArrayList<Services> selectedServices; 
    private ArrayList<Offers> selectedOffers;
    private String origin;
    private boolean citizenAgreed;

    public Inquiry(String shortInfo) {
        this.shortInfo = shortInfo;
        this.selectedServices = new ArrayList();
        this.selectedOffers = new ArrayList();
    }
    
    public Inquiry(String shortInfo, String background, String origin, boolean citizenAgreed) {
        this(shortInfo);
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

    public ArrayList getServices() {
        return selectedServices;
    }

    public void setServices(ArrayList services) {
        this.selectedServices = services;
    }

    public ArrayList getOffers() {
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

    public void addService(Services service){
        selectedServices.add(service);
    }
    public void removeService (Services service){
        selectedServices.remove(service);
    }
    public void addOffer (Offers offer){
        selectedOffers.add(offer);
    }
    public void removeOffer (Offers offer){
        selectedOffers.remove(offer);
    }
}
