package com.foursquare4j.response;

public class Stats implements FoursquareResponse {

    private Integer checkinsCount;
    private Integer usersCount;
    private Integer tipCount;

    public Integer getCheckinsCount() {
        return checkinsCount;
    }

    protected void setCheckinsCount(Integer checkinsCount) {
        this.checkinsCount = checkinsCount;
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    protected void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public Integer getTipCount() {
        return tipCount;
    }

    protected void setTipCount(Integer tipCount) {
        this.tipCount = tipCount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((checkinsCount == null) ? 0 : checkinsCount.hashCode());
        result = prime * result + ((tipCount == null) ? 0 : tipCount.hashCode());
        result = prime * result + ((usersCount == null) ? 0 : usersCount.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Stats other = (Stats) obj;
        if (checkinsCount == null) {
            if (other.checkinsCount != null) return false;
        } else if (!checkinsCount.equals(other.checkinsCount)) return false;
        if (tipCount == null) {
            if (other.tipCount != null) return false;
        } else if (!tipCount.equals(other.tipCount)) return false;
        if (usersCount == null) {
            if (other.usersCount != null) return false;
        } else if (!usersCount.equals(other.usersCount)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Stats [checkinsCount=").append(checkinsCount).append(", usersCount=").append(usersCount).append(", tipCount=").append(tipCount).append("]");
        return builder.toString();
    }
}