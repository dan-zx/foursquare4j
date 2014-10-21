package com.foursquare4j.response;

import java.util.Arrays;

public class Venue implements FoursquareResponse {

    private String id;
    private String name;
    private Contact contact;
    private Location location;
    private Category[] categories;
    private Boolean verified;
    private Stats stats;
    private String url;
    private Hours hours;
    private Hours popular;
    private Menu menu;
    private Price price;
    private Double rating;
    private Group<Special> specials;
    private Groups<User> hereNow;
    private String storeId;
    private String description;
    private Long createdAt;
    private Mayor mayor;
    private Groups<Tip> tips;
    private Groups<List> listed;
    private String[] tags;
    private Count beenHere;
    private String shortUrl;
    private String canonicalUrl;
    private Special[] specialsNearby;
    private Groups<Photo> photos;
    private Groups<User> likes;
    private Boolean like;
    private Boolean dislike;
    private Phrase[] phrases;
    private AttributeGroups attributes;

    // TODO: private ? roles
    // TODO: private ? flags
    // TODO: private ? page
    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    protected void setContact(Contact contact) {
        this.contact = contact;
    }

    public Location getLocation() {
        return location;
    }

    protected void setLocation(Location location) {
        this.location = location;
    }

    public Category[] getCategories() {
        return categories;
    }

    protected void setCategories(Category[] categories) {
        this.categories = categories;
    }

    public Boolean getVerified() {
        return verified;
    }

    protected void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Stats getStats() {
        return stats;
    }

    protected void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getUrl() {
        return url;
    }

    protected void setUrl(String url) {
        this.url = url;
    }

    public Hours getHours() {
        return hours;
    }

    protected void setHours(Hours hours) {
        this.hours = hours;
    }

    public Hours getPopular() {
        return popular;
    }

    protected void setPopular(Hours popular) {
        this.popular = popular;
    }

    public Menu getMenu() {
        return menu;
    }

    protected void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Price getPrice() {
        return price;
    }

    protected void setPrice(Price price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    protected void setRating(Double rating) {
        this.rating = rating;
    }

    public Group<Special> getSpecials() {
        return specials;
    }

    protected void setSpecials(Group<Special> specials) {
        this.specials = specials;
    }

    public Groups<User> getHereNow() {
        return hereNow;
    }

    protected void setHereNow(Groups<User> hereNow) {
        this.hereNow = hereNow;
    }

    public String getStoreId() {
        return storeId;
    }

    protected void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    protected void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Mayor getMayor() {
        return mayor;
    }

    protected void setMayor(Mayor mayor) {
        this.mayor = mayor;
    }

    public Groups<Tip> getTips() {
        return tips;
    }

    protected void setTips(Groups<Tip> tips) {
        this.tips = tips;
    }

    public Groups<List> getListed() {
        return listed;
    }

    protected void setListed(Groups<List> listed) {
        this.listed = listed;
    }

    public String[] getTags() {
        return tags;
    }

    protected void setTags(String[] tags) {
        this.tags = tags;
    }

    public Count getBeenHere() {
        return beenHere;
    }

    protected void setBeenHere(Count beenHere) {
        this.beenHere = beenHere;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    protected void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    protected void setCanonicalUrl(String canonicalUrl) {
        this.canonicalUrl = canonicalUrl;
    }

    public Special[] getSpecialsNearby() {
        return specialsNearby;
    }

    protected void setSpecialsNearby(Special[] specialsNearby) {
        this.specialsNearby = specialsNearby;
    }

    public Groups<Photo> getPhotos() {
        return photos;
    }

    protected void setPhotos(Groups<Photo> photos) {
        this.photos = photos;
    }

    public Groups<User> getLikes() {
        return likes;
    }

    protected void setLikes(Groups<User> likes) {
        this.likes = likes;
    }

    public Boolean getLike() {
        return like;
    }

    protected void setLike(Boolean like) {
        this.like = like;
    }

    public Boolean getDislike() {
        return dislike;
    }

    protected void setDislike(Boolean dislike) {
        this.dislike = dislike;
    }

    public Phrase[] getPhrases() {
        return phrases;
    }

    protected void setPhrases(Phrase[] phrases) {
        this.phrases = phrases;
    }

    public AttributeGroups getAttributes() {
        return attributes;
    }

    protected void setAttributes(AttributeGroups attributes) {
        this.attributes = attributes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
        result = prime * result + ((beenHere == null) ? 0 : beenHere.hashCode());
        result = prime * result + ((canonicalUrl == null) ? 0 : canonicalUrl.hashCode());
        result = prime * result + Arrays.hashCode(categories);
        result = prime * result + ((contact == null) ? 0 : contact.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((dislike == null) ? 0 : dislike.hashCode());
        result = prime * result + ((hereNow == null) ? 0 : hereNow.hashCode());
        result = prime * result + ((hours == null) ? 0 : hours.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((like == null) ? 0 : like.hashCode());
        result = prime * result + ((likes == null) ? 0 : likes.hashCode());
        result = prime * result + ((listed == null) ? 0 : listed.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((mayor == null) ? 0 : mayor.hashCode());
        result = prime * result + ((menu == null) ? 0 : menu.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((photos == null) ? 0 : photos.hashCode());
        result = prime * result + Arrays.hashCode(phrases);
        result = prime * result + ((popular == null) ? 0 : popular.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((rating == null) ? 0 : rating.hashCode());
        result = prime * result + ((shortUrl == null) ? 0 : shortUrl.hashCode());
        result = prime * result + ((specials == null) ? 0 : specials.hashCode());
        result = prime * result + Arrays.hashCode(specialsNearby);
        result = prime * result + ((stats == null) ? 0 : stats.hashCode());
        result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
        result = prime * result + Arrays.hashCode(tags);
        result = prime * result + ((tips == null) ? 0 : tips.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        result = prime * result + ((verified == null) ? 0 : verified.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Venue other = (Venue) obj;
        if (attributes == null) {
            if (other.attributes != null) return false;
        } else if (!attributes.equals(other.attributes)) return false;
        if (beenHere == null) {
            if (other.beenHere != null) return false;
        } else if (!beenHere.equals(other.beenHere)) return false;
        if (canonicalUrl == null) {
            if (other.canonicalUrl != null) return false;
        } else if (!canonicalUrl.equals(other.canonicalUrl)) return false;
        if (!Arrays.equals(categories, other.categories)) return false;
        if (contact == null) {
            if (other.contact != null) return false;
        } else if (!contact.equals(other.contact)) return false;
        if (createdAt == null) {
            if (other.createdAt != null) return false;
        } else if (!createdAt.equals(other.createdAt)) return false;
        if (description == null) {
            if (other.description != null) return false;
        } else if (!description.equals(other.description)) return false;
        if (dislike == null) {
            if (other.dislike != null) return false;
        } else if (!dislike.equals(other.dislike)) return false;
        if (hereNow == null) {
            if (other.hereNow != null) return false;
        } else if (!hereNow.equals(other.hereNow)) return false;
        if (hours == null) {
            if (other.hours != null) return false;
        } else if (!hours.equals(other.hours)) return false;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        if (like == null) {
            if (other.like != null) return false;
        } else if (!like.equals(other.like)) return false;
        if (likes == null) {
            if (other.likes != null) return false;
        } else if (!likes.equals(other.likes)) return false;
        if (listed == null) {
            if (other.listed != null) return false;
        } else if (!listed.equals(other.listed)) return false;
        if (location == null) {
            if (other.location != null) return false;
        } else if (!location.equals(other.location)) return false;
        if (mayor == null) {
            if (other.mayor != null) return false;
        } else if (!mayor.equals(other.mayor)) return false;
        if (menu == null) {
            if (other.menu != null) return false;
        } else if (!menu.equals(other.menu)) return false;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        if (photos == null) {
            if (other.photos != null) return false;
        } else if (!photos.equals(other.photos)) return false;
        if (!Arrays.equals(phrases, other.phrases)) return false;
        if (popular == null) {
            if (other.popular != null) return false;
        } else if (!popular.equals(other.popular)) return false;
        if (price == null) {
            if (other.price != null) return false;
        } else if (!price.equals(other.price)) return false;
        if (rating == null) {
            if (other.rating != null) return false;
        } else if (!rating.equals(other.rating)) return false;
        if (shortUrl == null) {
            if (other.shortUrl != null) return false;
        } else if (!shortUrl.equals(other.shortUrl)) return false;
        if (specials == null) {
            if (other.specials != null) return false;
        } else if (!specials.equals(other.specials)) return false;
        if (!Arrays.equals(specialsNearby, other.specialsNearby)) return false;
        if (stats == null) {
            if (other.stats != null) return false;
        } else if (!stats.equals(other.stats)) return false;
        if (storeId == null) {
            if (other.storeId != null) return false;
        } else if (!storeId.equals(other.storeId)) return false;
        if (!Arrays.equals(tags, other.tags)) return false;
        if (tips == null) {
            if (other.tips != null) return false;
        } else if (!tips.equals(other.tips)) return false;
        if (url == null) {
            if (other.url != null) return false;
        } else if (!url.equals(other.url)) return false;
        if (verified == null) {
            if (other.verified != null) return false;
        } else if (!verified.equals(other.verified)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Venue [id=").append(id).append(", name=").append(name).append(", contact=").append(contact).append(", location=").append(location).append(", categories=").append(Arrays.toString(categories)).append(", verified=").append(verified).append(", stats=").append(stats).append(", url=").append(url).append(", hours=").append(hours).append(", popular=").append(popular).append(", menu=").append(menu).append(", price=").append(price).append(", rating=").append(rating).append(", specials=").append(specials).append(", hereNow=").append(hereNow).append(", storeId=").append(storeId).append(", description=").append(description).append(", createdAt=").append(createdAt).append(", mayor=").append(mayor).append(", tips=").append(tips).append(", listed=").append(listed).append(", tags=").append(Arrays.toString(tags)).append(", beenHere=").append(beenHere).append(", shortUrl=").append(shortUrl).append(", canonicalUrl=").append(canonicalUrl).append(", specialsNearby=").append(Arrays.toString(specialsNearby)).append(", photos=").append(photos).append(", likes=").append(likes).append(", like=").append(like).append(", dislike=").append(dislike).append(", phrases=").append(Arrays.toString(phrases)).append(", attributes=").append(attributes).append("]");
        return builder.toString();
    }
}