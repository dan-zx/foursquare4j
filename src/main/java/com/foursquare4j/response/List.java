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

import java.util.Arrays;

public class List {

    public class Item {

        private String id;
        private User user;
        private Photo photo;
        private Venue venue;
        private Tip tip;
        private String note;
        private Long createdAt;
        private List[] listed;

        public String getId() {
            return id;
        }

        protected void setId(String id) {
            this.id = id;
        }

        public User getUser() {
            return user;
        }

        protected void setUser(User user) {
            this.user = user;
        }

        public Photo getPhoto() {
            return photo;
        }

        protected void setPhoto(Photo photo) {
            this.photo = photo;
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

        public String getNote() {
            return note;
        }

        protected void setNote(String note) {
            this.note = note;
        }

        public Long getCreatedAt() {
            return createdAt;
        }

        protected void setCreatedAt(Long createdAt) {
            this.createdAt = createdAt;
        }

        public List[] getListed() {
            return listed;
        }

        protected void setListed(List[] listed) {
            this.listed = listed;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            result = prime * result + Arrays.hashCode(listed);
            result = prime * result + ((note == null) ? 0 : note.hashCode());
            result = prime * result + ((photo == null) ? 0 : photo.hashCode());
            result = prime * result + ((tip == null) ? 0 : tip.hashCode());
            result = prime * result + ((user == null) ? 0 : user.hashCode());
            result = prime * result + ((venue == null) ? 0 : venue.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Item other = (Item) obj;
            if (!getOuterType().equals(other.getOuterType())) return false;
            if (createdAt == null) {
                if (other.createdAt != null) return false;
            } else if (!createdAt.equals(other.createdAt)) return false;
            if (id == null) {
                if (other.id != null) return false;
            } else if (!id.equals(other.id)) return false;
            if (!Arrays.equals(listed, other.listed)) return false;
            if (note == null) {
                if (other.note != null) return false;
            } else if (!note.equals(other.note)) return false;
            if (photo == null) {
                if (other.photo != null) return false;
            } else if (!photo.equals(other.photo)) return false;
            if (tip == null) {
                if (other.tip != null) return false;
            } else if (!tip.equals(other.tip)) return false;
            if (user == null) {
                if (other.user != null) return false;
            } else if (!user.equals(other.user)) return false;
            if (venue == null) {
                if (other.venue != null) return false;
            } else if (!venue.equals(other.venue)) return false;
            return true;
        }

        private List getOuterType() {
            return List.this;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Item [id=").append(id).append(", user=").append(user).append(", photo=").append(photo).append(", venue=").append(venue).append(", tip=").append(tip).append(", note=").append(note).append(", createdAt=").append(createdAt).append(", listed=").append(Arrays.toString(listed)).append("]");
            return builder.toString();
        }
    }

    private String id;
    private String name;
    private String description;
    private User user;
    private Boolean following;
    private Group<User> followers;
    private Boolean editable;
    private Boolean collaborative;
    private Group<User> collaborators;
    private String canonicalUrl;
    private Photo photo;
    private Integer venueCount;
    private Integer visitedCount;
    private Group<Item> listItems;

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

    public String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    protected void setUser(User user) {
        this.user = user;
    }

    public Boolean getFollowing() {
        return following;
    }

    protected void setFollowing(Boolean following) {
        this.following = following;
    }

    public Group<User> getFollowers() {
        return followers;
    }

    protected void setFollowers(Group<User> followers) {
        this.followers = followers;
    }

    public Boolean getEditable() {
        return editable;
    }

    protected void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Boolean getCollaborative() {
        return collaborative;
    }

    protected void setCollaborative(Boolean collaborative) {
        this.collaborative = collaborative;
    }

    public Group<User> getCollaborators() {
        return collaborators;
    }

    protected void setCollaborators(Group<User> collaborators) {
        this.collaborators = collaborators;
    }

    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    protected void setCanonicalUrl(String canonicalUrl) {
        this.canonicalUrl = canonicalUrl;
    }

    public Photo getPhoto() {
        return photo;
    }

    protected void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Integer getVenueCount() {
        return venueCount;
    }

    protected void setVenueCount(Integer venueCount) {
        this.venueCount = venueCount;
    }

    public Integer getVisitedCount() {
        return visitedCount;
    }

    protected void setVisitedCount(Integer visitedCount) {
        this.visitedCount = visitedCount;
    }

    public Group<Item> getListItems() {
        return listItems;
    }

    protected void setListItems(Group<Item> listItems) {
        this.listItems = listItems;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((canonicalUrl == null) ? 0 : canonicalUrl.hashCode());
        result = prime * result + ((collaborative == null) ? 0 : collaborative.hashCode());
        result = prime * result + ((collaborators == null) ? 0 : collaborators.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((editable == null) ? 0 : editable.hashCode());
        result = prime * result + ((followers == null) ? 0 : followers.hashCode());
        result = prime * result + ((following == null) ? 0 : following.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((listItems == null) ? 0 : listItems.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((photo == null) ? 0 : photo.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((venueCount == null) ? 0 : venueCount.hashCode());
        result = prime * result + ((visitedCount == null) ? 0 : visitedCount.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        List other = (List) obj;
        if (canonicalUrl == null) {
            if (other.canonicalUrl != null) return false;
        } else if (!canonicalUrl.equals(other.canonicalUrl)) return false;
        if (collaborative == null) {
            if (other.collaborative != null) return false;
        } else if (!collaborative.equals(other.collaborative)) return false;
        if (collaborators == null) {
            if (other.collaborators != null) return false;
        } else if (!collaborators.equals(other.collaborators)) return false;
        if (description == null) {
            if (other.description != null) return false;
        } else if (!description.equals(other.description)) return false;
        if (editable == null) {
            if (other.editable != null) return false;
        } else if (!editable.equals(other.editable)) return false;
        if (followers == null) {
            if (other.followers != null) return false;
        } else if (!followers.equals(other.followers)) return false;
        if (following == null) {
            if (other.following != null) return false;
        } else if (!following.equals(other.following)) return false;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        if (listItems == null) {
            if (other.listItems != null) return false;
        } else if (!listItems.equals(other.listItems)) return false;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        if (photo == null) {
            if (other.photo != null) return false;
        } else if (!photo.equals(other.photo)) return false;
        if (user == null) {
            if (other.user != null) return false;
        } else if (!user.equals(other.user)) return false;
        if (venueCount == null) {
            if (other.venueCount != null) return false;
        } else if (!venueCount.equals(other.venueCount)) return false;
        if (visitedCount == null) {
            if (other.visitedCount != null) return false;
        } else if (!visitedCount.equals(other.visitedCount)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("List [id=").append(id).append(", name=").append(name).append(", description=").append(description).append(", user=").append(user).append(", following=").append(following).append(", followers=").append(followers).append(", editable=").append(editable).append(", collaborative=").append(collaborative).append(", collaborators=").append(collaborators).append(", canonicalUrl=").append(canonicalUrl).append(", photo=").append(photo).append(", venueCount=").append(venueCount).append(", visitedCount=").append(visitedCount).append(", listItems=").append(listItems).append("]");
        return builder.toString();
    }
}