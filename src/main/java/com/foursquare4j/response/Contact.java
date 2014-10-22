package com.foursquare4j.response;

public class Contact {

    private String email;
    private String facebook;
    private String twitter;
    private String phone;

    public String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    protected void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    protected void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getPhone() {
        return phone;
    }

    protected void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((facebook == null) ? 0 : facebook.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((twitter == null) ? 0 : twitter.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Contact other = (Contact) obj;
        if (email == null) {
            if (other.email != null) return false;
        } else if (!email.equals(other.email)) return false;
        if (facebook == null) {
            if (other.facebook != null) return false;
        } else if (!facebook.equals(other.facebook)) return false;
        if (phone == null) {
            if (other.phone != null) return false;
        } else if (!phone.equals(other.phone)) return false;
        if (twitter == null) {
            if (other.twitter != null) return false;
        } else if (!twitter.equals(other.twitter)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Contact [email=").append(email).append(", facebook=").append(facebook).append(", twitter=").append(twitter).append(", phone=").append(phone).append("]");
        return builder.toString();
    }
}