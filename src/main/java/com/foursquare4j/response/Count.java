package com.foursquare4j.response;

public class Count implements FoursquareResponse {

    private Long count;

    public Long getCount() {
        return count;
    }

    protected void setCount(Long count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((count == null) ? 0 : count.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Count other = (Count) obj;
        if (count == null) {
            if (other.count != null) return false;
        } else if (!count.equals(other.count)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Count [count=").append(count).append("]");
        return builder.toString();
    }
}