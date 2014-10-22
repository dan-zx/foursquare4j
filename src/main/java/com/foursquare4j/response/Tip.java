package com.foursquare4j.response;

public class Tip {

    private String id;
    private String text;
    private Long createdAt;
    private String status;
    private Photo photo;
    private User user;
    private Venue venue;
    private Groups<User> todo;
    private Groups<User> likes;

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    protected void setText(String text) {
        this.text = text;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    protected void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    protected void setStatus(String status) {
        this.status = status;
    }

    public Photo getPhoto() {
        return photo;
    }

    protected void setPhoto(Photo photo) {
        this.photo = photo;
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

    public Groups<User> getTodo() {
        return todo;
    }

    protected void setTodo(Groups<User> todo) {
        this.todo = todo;
    }

    public Groups<User> getLikes() {
        return likes;
    }

    protected void setLikes(Groups<User> likes) {
        this.likes = likes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((likes == null) ? 0 : likes.hashCode());
        result = prime * result + ((photo == null) ? 0 : photo.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((todo == null) ? 0 : todo.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((venue == null) ? 0 : venue.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Tip other = (Tip) obj;
        if (createdAt == null) {
            if (other.createdAt != null) return false;
        } else if (!createdAt.equals(other.createdAt)) return false;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        if (likes == null) {
            if (other.likes != null) return false;
        } else if (!likes.equals(other.likes)) return false;
        if (photo == null) {
            if (other.photo != null) return false;
        } else if (!photo.equals(other.photo)) return false;
        if (status == null) {
            if (other.status != null) return false;
        } else if (!status.equals(other.status)) return false;
        if (text == null) {
            if (other.text != null) return false;
        } else if (!text.equals(other.text)) return false;
        if (todo == null) {
            if (other.todo != null) return false;
        } else if (!todo.equals(other.todo)) return false;
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
        builder.append("Tip [id=").append(id).append(", text=").append(text).append(", createdAt=").append(createdAt).append(", status=").append(status).append(", photo=").append(photo).append(", user=").append(user).append(", venue=").append(venue).append(", todo=").append(todo).append(", likes=").append(likes).append("]");
        return builder.toString();
    }
}