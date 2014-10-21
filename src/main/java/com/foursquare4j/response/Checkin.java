package com.foursquare4j.response;

public class Checkin implements FoursquareResponse {

    private String id;
    private String type;
    private Integer timeZoneOffset;
    private Long createdAt;
    private Boolean isPrivate;
    private String shout;
    private User user;
    private Venue venue;
    private Location location;
    private Source source;
    private Event event;
    private Group<Photo> photos;
    private Group<Comment> comments;
    private Groups<User> likes;
    private Group<Checkin> overlaps;
    private Scores scores;

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    protected void setType(String type) {
        this.type = type;
    }

    public Integer getTimeZoneOffset() {
        return timeZoneOffset;
    }

    protected void setTimeZoneOffset(Integer timeZoneOffset) {
        this.timeZoneOffset = timeZoneOffset;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    protected void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    protected void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getShout() {
        return shout;
    }

    protected void setShout(String shout) {
        this.shout = shout;
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

    public Location getLocation() {
        return location;
    }

    protected void setLocation(Location location) {
        this.location = location;
    }

    public Source getSource() {
        return source;
    }

    protected void setSource(Source source) {
        this.source = source;
    }

    public Event getEvent() {
        return event;
    }

    protected void setEvent(Event event) {
        this.event = event;
    }

    public Group<Photo> getPhotos() {
        return photos;
    }

    protected void setPhotos(Group<Photo> photos) {
        this.photos = photos;
    }

    public Group<Comment> getComments() {
        return comments;
    }

    protected void setComments(Group<Comment> comments) {
        this.comments = comments;
    }

    public Groups<User> getLikes() {
        return likes;
    }

    protected void setLikes(Groups<User> likes) {
        this.likes = likes;
    }

    public Group<Checkin> getOverlaps() {
        return overlaps;
    }

    protected void setOverlaps(Group<Checkin> overlaps) {
        this.overlaps = overlaps;
    }

    public Scores getScores() {
        return scores;
    }

    protected void setScores(Scores scores) {
        this.scores = scores;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((event == null) ? 0 : event.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((isPrivate == null) ? 0 : isPrivate.hashCode());
        result = prime * result + ((likes == null) ? 0 : likes.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((overlaps == null) ? 0 : overlaps.hashCode());
        result = prime * result + ((photos == null) ? 0 : photos.hashCode());
        result = prime * result + ((scores == null) ? 0 : scores.hashCode());
        result = prime * result + ((shout == null) ? 0 : shout.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((timeZoneOffset == null) ? 0 : timeZoneOffset.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((venue == null) ? 0 : venue.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Checkin other = (Checkin) obj;
        if (comments == null) {
            if (other.comments != null) return false;
        } else if (!comments.equals(other.comments)) return false;
        if (createdAt == null) {
            if (other.createdAt != null) return false;
        } else if (!createdAt.equals(other.createdAt)) return false;
        if (event == null) {
            if (other.event != null) return false;
        } else if (!event.equals(other.event)) return false;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        if (isPrivate == null) {
            if (other.isPrivate != null) return false;
        } else if (!isPrivate.equals(other.isPrivate)) return false;
        if (likes == null) {
            if (other.likes != null) return false;
        } else if (!likes.equals(other.likes)) return false;
        if (location == null) {
            if (other.location != null) return false;
        } else if (!location.equals(other.location)) return false;
        if (overlaps == null) {
            if (other.overlaps != null) return false;
        } else if (!overlaps.equals(other.overlaps)) return false;
        if (photos == null) {
            if (other.photos != null) return false;
        } else if (!photos.equals(other.photos)) return false;
        if (scores == null) {
            if (other.scores != null) return false;
        } else if (!scores.equals(other.scores)) return false;
        if (shout == null) {
            if (other.shout != null) return false;
        } else if (!shout.equals(other.shout)) return false;
        if (source == null) {
            if (other.source != null) return false;
        } else if (!source.equals(other.source)) return false;
        if (timeZoneOffset == null) {
            if (other.timeZoneOffset != null) return false;
        } else if (!timeZoneOffset.equals(other.timeZoneOffset)) return false;
        if (type == null) {
            if (other.type != null) return false;
        } else if (!type.equals(other.type)) return false;
        if (user == null) {
            if (other.user != null) return false;
        } else if (!user.equals(other.user)) return false;
        if (venue == null) {
            if (other.venue != null) return false;
        } else if (!venue.equals(other.venue)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Checkin [id=").append(id).append(", type=").append(type).append(", timeZoneOffset=").append(timeZoneOffset).append(", createdAt=").append(createdAt).append(", isPrivate=").append(isPrivate).append(", shout=").append(shout).append(", user=").append(user).append(", venue=").append(venue).append(", location=").append(location).append(", source=").append(source).append(", event=").append(event).append(", photos=").append(photos).append(", comments=").append(comments).append(", likes=").append(likes).append(", overlaps=").append(overlaps).append(", scores=").append(scores).append("]");
        return builder.toString();
    }
}