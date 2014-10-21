package com.foursquare4j.response;

public class Menu implements FoursquareResponse {

    private String url;
    private String mobileUrl;

    public String getUrl() {
        return url;
    }

    protected void setUrl(String url) {
        this.url = url;
    }

    public String getMobileUrl() {
        return mobileUrl;
    }

    protected void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mobileUrl == null) ? 0 : mobileUrl.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Menu other = (Menu) obj;
        if (mobileUrl == null) {
            if (other.mobileUrl != null) return false;
        } else if (!mobileUrl.equals(other.mobileUrl)) return false;
        if (url == null) {
            if (other.url != null) return false;
        } else if (!url.equals(other.url)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Menu [url=").append(url).append(", mobileUrl=").append(mobileUrl).append("]");
        return builder.toString();
    }
}