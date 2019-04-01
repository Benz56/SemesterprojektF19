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
    private ArrayList services; 
    private ArrayList offers;
    private String origin;
    private boolean citizenAgreed;

    public Inquiry(String shortInfo) {
        this.shortInfo = shortInfo;
    }
    
    public Inquiry(String shortInfo, String background, ArrayList services, ArrayList offers, String origin, boolean citizenAgreed) {
        this.shortInfo = shortInfo;
        this.background = background;
        this.services = services;
        this.offers = offers;
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
        return services;
    }

    public void setServices(ArrayList services) {
        this.services = services;
    }

    public ArrayList getOffers() {
        return offers;
    }

    public void setOffers(ArrayList offers) {
        this.offers = offers;
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

    
    
}
