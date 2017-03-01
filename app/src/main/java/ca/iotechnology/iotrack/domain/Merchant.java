package ca.iotechnology.iotrack.domain;

/**
 * Created by Puneet on 16/02/2017.
 */

public class Merchant {
    private String icon;
    private long numberOfCoupons;
    private String name;
    private String description;
    private String logoUrl;

    public Merchant(String name, String description, String logoUrl, long coupons, String icon){
        this.name = name;
        this.description = description;
        this.logoUrl = logoUrl;
        this.numberOfCoupons = coupons;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getNumberOfCoupons() {
        return numberOfCoupons;
    }

    public void setNumberOfCoupons(long numberOfCoupons) {
        this.numberOfCoupons = numberOfCoupons;
    }
}
