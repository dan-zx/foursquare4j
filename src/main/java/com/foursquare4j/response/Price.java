package com.foursquare4j.response;

public class Price implements FoursquareResponse {

    private Integer tier;
    private String message;

    public Integer getTier() {
        return tier;
    }

    protected void setTier(Integer tier) {
        this.tier = tier;
    }

    public String getMessage() {
        return message;
    }

    protected void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((tier == null) ? 0 : tier.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Price other = (Price) obj;
        if (message == null) {
            if (other.message != null) return false;
        } else if (!message.equals(other.message)) return false;
        if (tier == null) {
            if (other.tier != null) return false;
        } else if (!tier.equals(other.tier)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Price [tier=").append(tier).append(", message=").append(message).append("]");
        return builder.toString();
    }
}