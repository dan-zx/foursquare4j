package com.foursquare4j.response;

public class Location {

    private String name;
    private String address;
    private String crossStreet;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Double lat;
    private Double lng;
    private Double distance;
    private Boolean isFuzzed;

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    protected void setAddress(String address) {
        this.address = address;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    protected void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }

    public String getCity() {
        return city;
    }

    protected void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    protected void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    protected void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    protected void setCountry(String country) {
        this.country = country;
    }

    public Double getLat() {
        return lat;
    }

    protected void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    protected void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getDistance() {
        return distance;
    }

    protected void setDistance(Double distance) {
        this.distance = distance;
    }

    public Boolean getIsFuzzed() {
        return isFuzzed;
    }

    protected void setIsFuzzed(Boolean isFuzzed) {
        this.isFuzzed = isFuzzed;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((crossStreet == null) ? 0 : crossStreet.hashCode());
        result = prime * result + ((distance == null) ? 0 : distance.hashCode());
        result = prime * result + ((isFuzzed == null) ? 0 : isFuzzed.hashCode());
        result = prime * result + ((lat == null) ? 0 : lat.hashCode());
        result = prime * result + ((lng == null) ? 0 : lng.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Location other = (Location) obj;
        if (address == null) {
            if (other.address != null) return false;
        } else if (!address.equals(other.address)) return false;
        if (city == null) {
            if (other.city != null) return false;
        } else if (!city.equals(other.city)) return false;
        if (country == null) {
            if (other.country != null) return false;
        } else if (!country.equals(other.country)) return false;
        if (crossStreet == null) {
            if (other.crossStreet != null) return false;
        } else if (!crossStreet.equals(other.crossStreet)) return false;
        if (distance == null) {
            if (other.distance != null) return false;
        } else if (!distance.equals(other.distance)) return false;
        if (isFuzzed == null) {
            if (other.isFuzzed != null) return false;
        } else if (!isFuzzed.equals(other.isFuzzed)) return false;
        if (lat == null) {
            if (other.lat != null) return false;
        } else if (!lat.equals(other.lat)) return false;
        if (lng == null) {
            if (other.lng != null) return false;
        } else if (!lng.equals(other.lng)) return false;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        if (postalCode == null) {
            if (other.postalCode != null) return false;
        } else if (!postalCode.equals(other.postalCode)) return false;
        if (state == null) {
            if (other.state != null) return false;
        } else if (!state.equals(other.state)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Location [name=").append(name).append(", address=").append(address).append(", crossStreet=").append(crossStreet).append(", city=").append(city).append(", state=").append(state).append(", postalCode=").append(postalCode).append(", country=").append(country).append(", lat=").append(lat).append(", lng=").append(lng).append(", distance=").append(distance).append(", isFuzzed=").append(isFuzzed).append("]");
        return builder.toString();
    }
}