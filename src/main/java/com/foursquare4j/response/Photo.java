/*
 * Copyright 2014 Daniel Pedraza-Arcega
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.foursquare4j.response;

public class Photo {

    private String id;
    private Long createdAt;
    private String prefix;
    private String suffix;
    private String visibility;
    private Source source;
    private User user;
    private Venue venue;
    private Tip tip;
    private Checkin checkin;

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    protected void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getPrefix() {
        return prefix;
    }

    protected void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    protected void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getVisibility() {
        return visibility;
    }

    protected void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Source getSource() {
        return source;
    }

    protected void setSource(Source source) {
        this.source = source;
    }

    public User getUser() {
        return user;
    }

    protected void setUser(User user) {
        this.user = user;
    }

    public Venue getVenue() {
        return venue;
    }

    protected void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Tip getTip() {
        return tip;
    }

    protected void setTip(Tip tip) {
        this.tip = tip;
    }

    public Checkin getCheckin() {
        return checkin;
    }

    protected void setCheckin(Checkin checkin) {
        this.checkin = checkin;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((checkin == null) ? 0 : checkin.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((suffix == null) ? 0 : suffix.hashCode());
        result = prime * result + ((tip == null) ? 0 : tip.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((venue == null) ? 0 : venue.hashCode());
        result = prime * result + ((visibility == null) ? 0 : visibility.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Photo other = (Photo) obj;
        if (checkin == null) {
            if (other.checkin != null) return false;
        } else if (!checkin.equals(other.checkin)) return false;
        if (createdAt == null) {
            if (other.createdAt != null) return false;
        } else if (!createdAt.equals(other.createdAt)) return false;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        if (prefix == null) {
            if (other.prefix != null) return false;
        } else if (!prefix.equals(other.prefix)) return false;
        if (source == null) {
            if (other.source != null) return false;
        } else if (!source.equals(other.source)) return false;
        if (suffix == null) {
            if (other.suffix != null) return false;
        } else if (!suffix.equals(other.suffix)) return false;
        if (tip == null) {
            if (other.tip != null) return false;
        } else if (!tip.equals(other.tip)) return false;
        if (user == null) {
            if (other.user != null) return false;
        } else if (!user.equals(other.user)) return false;
        if (venue == null) {
            if (other.venue != null) return false;
        } else if (!venue.equals(other.venue)) return false;
        if (visibility == null) {
            if (other.visibility != null) return false;
        } else if (!visibility.equals(other.visibility)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Photo [id=").append(id).append(", createdAt=").append(createdAt).append(", prefix=").append(prefix).append(", suffix=").append(suffix).append(", visibility=").append(visibility).append(", source=").append(source).append(", user=").append(user).append(", venue=").append(venue).append(", tip=").append(tip).append(", checkin=").append(checkin).append("]");
        return builder.toString();
    }
}