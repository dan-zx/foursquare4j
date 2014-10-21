package com.foursquare4j.response;

import java.util.Arrays;

public class Badge implements FoursquareResponse {

    public static class Image implements FoursquareResponse {

        private String prefix;
        private Integer[] sizes;
        private String name;

        public String getPrefix() {
            return prefix;
        }

        protected void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public Integer[] getSizes() {
            return sizes;
        }

        protected void setSizes(Integer[] sizes) {
            this.sizes = sizes;
        }

        public String getName() {
            return name;
        }

        protected void setName(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
            result = prime * result + Arrays.hashCode(sizes);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Image other = (Image) obj;
            if (name == null) {
                if (other.name != null) return false;
            } else if (!name.equals(other.name)) return false;
            if (prefix == null) {
                if (other.prefix != null) return false;
            } else if (!prefix.equals(other.prefix)) return false;
            if (!Arrays.equals(sizes, other.sizes)) return false;
            return true;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Image [prefix=").append(prefix).append(", sizes=").append(Arrays.toString(sizes)).append(", name=").append(name).append("]");
            return builder.toString();
        }
    }

    public static class Unlock implements FoursquareResponse {

        private Checkin[] checkins;

        public Checkin[] getCheckins() {
            return checkins;
        }

        protected void setCheckins(Checkin[] checkins) {
            this.checkins = checkins;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(checkins);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Unlock other = (Unlock) obj;
            if (!Arrays.equals(checkins, other.checkins)) return false;
            return true;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Unlock [checkins=").append(Arrays.toString(checkins)).append("]");
            return builder.toString();
        }
    }

    private String id;
    private String badgeId;
    private String name;
    private String description;
    private Image image;
    private Unlock[] unlocks;

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    public String getBadgeId() {
        return badgeId;
    }

    protected void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
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

    public Image getImage() {
        return image;
    }

    protected void setImage(Image image) {
        this.image = image;
    }

    public Unlock[] getUnlocks() {
        return unlocks;
    }

    protected void setUnlocks(Unlock[] unlocks) {
        this.unlocks = unlocks;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((badgeId == null) ? 0 : badgeId.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + Arrays.hashCode(unlocks);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Badge other = (Badge) obj;
        if (badgeId == null) {
            if (other.badgeId != null) return false;
        } else if (!badgeId.equals(other.badgeId)) return false;
        if (description == null) {
            if (other.description != null) return false;
        } else if (!description.equals(other.description)) return false;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        if (image == null) {
            if (other.image != null) return false;
        } else if (!image.equals(other.image)) return false;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        if (!Arrays.equals(unlocks, other.unlocks)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Badge [id=").append(id).append(", badgeId=").append(badgeId).append(", name=").append(name).append(", description=").append(description).append(", image=").append(image).append(", unlocks=").append(Arrays.toString(unlocks)).append("]");
        return builder.toString();
    }
}