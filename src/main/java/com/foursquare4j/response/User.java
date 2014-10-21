package com.foursquare4j.response;

public class User implements FoursquareResponse {

    private String id;
    private String firstName;
    private String lastName;
    private Photo photo;
    private String relationship;
    private Groups<User> friends;
    private String type;
    private Venue venue;
    private String homeCity;
    private String gender;
    private Contact contact;
    private String bio;
    private Groups<Tip> tips;
    private Group<List> lists;
    private Groups<User> followers;
    private Groups<User> following;
    private Group<Badge> badges;
    private Group<Venue> mayorships;
    private Group<Photo> photos;
    private Scores scores;
    private Group<Checkin> checkins;
    private Page pageInfo;
    private Boolean pings;
    private Count requests;

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Photo getPhoto() {
        return photo;
    }

    protected void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getRelationship() {
        return relationship;
    }

    protected void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Groups<User> getFriends() {
        return friends;
    }

    protected void setFriends(Groups<User> friends) {
        this.friends = friends;
    }

    public String getType() {
        return type;
    }

    protected void setType(String type) {
        this.type = type;
    }

    public Venue getVenue() {
        return venue;
    }

    protected void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getHomeCity() {
        return homeCity;
    }

    protected void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public String getGender() {
        return gender;
    }

    protected void setGender(String gender) {
        this.gender = gender;
    }

    public Contact getContact() {
        return contact;
    }

    protected void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getBio() {
        return bio;
    }

    protected void setBio(String bio) {
        this.bio = bio;
    }

    public Groups<Tip> getTips() {
        return tips;
    }

    protected void setTips(Groups<Tip> tips) {
        this.tips = tips;
    }

    public Group<List> getLists() {
        return lists;
    }

    protected void setLists(Group<List> lists) {
        this.lists = lists;
    }

    public Groups<User> getFollowers() {
        return followers;
    }

    protected void setFollowers(Groups<User> followers) {
        this.followers = followers;
    }

    public Groups<User> getFollowing() {
        return following;
    }

    protected void setFollowing(Groups<User> following) {
        this.following = following;
    }

    public Group<Badge> getBadges() {
        return badges;
    }

    protected void setBadges(Group<Badge> badges) {
        this.badges = badges;
    }

    public Group<Venue> getMayorships() {
        return mayorships;
    }

    protected void setMayorships(Group<Venue> mayorships) {
        this.mayorships = mayorships;
    }

    public Group<Photo> getPhotos() {
        return photos;
    }

    protected void setPhotos(Group<Photo> photos) {
        this.photos = photos;
    }

    public Scores getScores() {
        return scores;
    }

    protected void setScores(Scores scores) {
        this.scores = scores;
    }

    public Group<Checkin> getCheckins() {
        return checkins;
    }

    protected void setCheckins(Group<Checkin> checkins) {
        this.checkins = checkins;
    }

    public Page getPageInfo() {
        return pageInfo;
    }

    protected void setPageInfo(Page pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Boolean getPings() {
        return pings;
    }

    protected void setPings(Boolean pings) {
        this.pings = pings;
    }

    public Count getRequests() {
        return requests;
    }

    protected void setRequests(Count requests) {
        this.requests = requests;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((badges == null) ? 0 : badges.hashCode());
        result = prime * result + ((bio == null) ? 0 : bio.hashCode());
        result = prime * result + ((checkins == null) ? 0 : checkins.hashCode());
        result = prime * result + ((contact == null) ? 0 : contact.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((followers == null) ? 0 : followers.hashCode());
        result = prime * result + ((following == null) ? 0 : following.hashCode());
        result = prime * result + ((friends == null) ? 0 : friends.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((homeCity == null) ? 0 : homeCity.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((lists == null) ? 0 : lists.hashCode());
        result = prime * result + ((mayorships == null) ? 0 : mayorships.hashCode());
        result = prime * result + ((pageInfo == null) ? 0 : pageInfo.hashCode());
        result = prime * result + ((photo == null) ? 0 : photo.hashCode());
        result = prime * result + ((photos == null) ? 0 : photos.hashCode());
        result = prime * result + ((pings == null) ? 0 : pings.hashCode());
        result = prime * result + ((relationship == null) ? 0 : relationship.hashCode());
        result = prime * result + ((requests == null) ? 0 : requests.hashCode());
        result = prime * result + ((scores == null) ? 0 : scores.hashCode());
        result = prime * result + ((tips == null) ? 0 : tips.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((venue == null) ? 0 : venue.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        User other = (User) obj;
        if (badges == null) {
            if (other.badges != null) return false;
        } else if (!badges.equals(other.badges)) return false;
        if (bio == null) {
            if (other.bio != null) return false;
        } else if (!bio.equals(other.bio)) return false;
        if (checkins == null) {
            if (other.checkins != null) return false;
        } else if (!checkins.equals(other.checkins)) return false;
        if (contact == null) {
            if (other.contact != null) return false;
        } else if (!contact.equals(other.contact)) return false;
        if (firstName == null) {
            if (other.firstName != null) return false;
        } else if (!firstName.equals(other.firstName)) return false;
        if (followers == null) {
            if (other.followers != null) return false;
        } else if (!followers.equals(other.followers)) return false;
        if (following == null) {
            if (other.following != null) return false;
        } else if (!following.equals(other.following)) return false;
        if (friends == null) {
            if (other.friends != null) return false;
        } else if (!friends.equals(other.friends)) return false;
        if (gender == null) {
            if (other.gender != null) return false;
        } else if (!gender.equals(other.gender)) return false;
        if (homeCity == null) {
            if (other.homeCity != null) return false;
        } else if (!homeCity.equals(other.homeCity)) return false;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        if (lastName == null) {
            if (other.lastName != null) return false;
        } else if (!lastName.equals(other.lastName)) return false;
        if (lists == null) {
            if (other.lists != null) return false;
        } else if (!lists.equals(other.lists)) return false;
        if (mayorships == null) {
            if (other.mayorships != null) return false;
        } else if (!mayorships.equals(other.mayorships)) return false;
        if (pageInfo == null) {
            if (other.pageInfo != null) return false;
        } else if (!pageInfo.equals(other.pageInfo)) return false;
        if (photo == null) {
            if (other.photo != null) return false;
        } else if (!photo.equals(other.photo)) return false;
        if (photos == null) {
            if (other.photos != null) return false;
        } else if (!photos.equals(other.photos)) return false;
        if (pings == null) {
            if (other.pings != null) return false;
        } else if (!pings.equals(other.pings)) return false;
        if (relationship == null) {
            if (other.relationship != null) return false;
        } else if (!relationship.equals(other.relationship)) return false;
        if (requests == null) {
            if (other.requests != null) return false;
        } else if (!requests.equals(other.requests)) return false;
        if (scores == null) {
            if (other.scores != null) return false;
        } else if (!scores.equals(other.scores)) return false;
        if (tips == null) {
            if (other.tips != null) return false;
        } else if (!tips.equals(other.tips)) return false;
        if (type == null) {
            if (other.type != null) return false;
        } else if (!type.equals(other.type)) return false;
        if (venue == null) {
            if (other.venue != null) return false;
        } else if (!venue.equals(other.venue)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [id=").append(id).append(", firstName=").append(firstName).append(", lastName=").append(lastName).append(", photo=").append(photo).append(", relationship=").append(relationship).append(", friends=").append(friends).append(", type=").append(type).append(", venue=").append(venue).append(", homeCity=").append(homeCity).append(", gender=").append(gender).append(", contact=").append(contact).append(", bio=").append(bio).append(", tips=").append(tips).append(", lists=").append(lists).append(", followers=").append(followers).append(", following=").append(following).append(", badges=").append(badges).append(", mayorships=").append(mayorships).append(", photos=").append(photos).append(", scores=").append(scores).append(", checkins=").append(checkins).append(", pageInfo=").append(pageInfo).append(", pings=").append(pings).append(", requests=").append(requests).append("]");
        return builder.toString();
    }
}