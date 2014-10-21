package com.foursquare4j.response;

import java.util.Arrays;

public class Special implements FoursquareResponse {

    private String id;
    private String type;
    private String message;
    private String description;
    private String finePrint;
    private Boolean unlocked;
    private String icon;
    private String title;
    private String state;
    private String progress;
    private String progressDescription;
    private String detail;
    private Integer target;
    private User[] friendsHere;

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

    public String getMessage() {
        return message;
    }

    protected void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public String getFinePrint() {
        return finePrint;
    }

    protected void setFinePrint(String finePrint) {
        this.finePrint = finePrint;
    }

    public Boolean getUnlocked() {
        return unlocked;
    }

    protected void setUnlocked(Boolean unlocked) {
        this.unlocked = unlocked;
    }

    public String getIcon() {
        return icon;
    }

    protected void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    protected void setState(String state) {
        this.state = state;
    }

    public String getProgress() {
        return progress;
    }

    protected void setProgress(String progress) {
        this.progress = progress;
    }

    public String getProgressDescription() {
        return progressDescription;
    }

    protected void setProgressDescription(String progressDescription) {
        this.progressDescription = progressDescription;
    }

    public String getDetail() {
        return detail;
    }

    protected void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getTarget() {
        return target;
    }

    protected void setTarget(Integer target) {
        this.target = target;
    }

    public User[] getFriendsHere() {
        return friendsHere;
    }

    protected void setFriendsHere(User[] friendsHere) {
        this.friendsHere = friendsHere;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((detail == null) ? 0 : detail.hashCode());
        result = prime * result + ((finePrint == null) ? 0 : finePrint.hashCode());
        result = prime * result + Arrays.hashCode(friendsHere);
        result = prime * result + ((icon == null) ? 0 : icon.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((progress == null) ? 0 : progress.hashCode());
        result = prime * result + ((progressDescription == null) ? 0 : progressDescription.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((unlocked == null) ? 0 : unlocked.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Special other = (Special) obj;
        if (description == null) {
            if (other.description != null) return false;
        } else if (!description.equals(other.description)) return false;
        if (detail == null) {
            if (other.detail != null) return false;
        } else if (!detail.equals(other.detail)) return false;
        if (finePrint == null) {
            if (other.finePrint != null) return false;
        } else if (!finePrint.equals(other.finePrint)) return false;
        if (!Arrays.equals(friendsHere, other.friendsHere)) return false;
        if (icon == null) {
            if (other.icon != null) return false;
        } else if (!icon.equals(other.icon)) return false;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        if (message == null) {
            if (other.message != null) return false;
        } else if (!message.equals(other.message)) return false;
        if (progress == null) {
            if (other.progress != null) return false;
        } else if (!progress.equals(other.progress)) return false;
        if (progressDescription == null) {
            if (other.progressDescription != null) return false;
        } else if (!progressDescription.equals(other.progressDescription)) return false;
        if (state == null) {
            if (other.state != null) return false;
        } else if (!state.equals(other.state)) return false;
        if (target == null) {
            if (other.target != null) return false;
        } else if (!target.equals(other.target)) return false;
        if (title == null) {
            if (other.title != null) return false;
        } else if (!title.equals(other.title)) return false;
        if (type == null) {
            if (other.type != null) return false;
        } else if (!type.equals(other.type)) return false;
        if (unlocked == null) {
            if (other.unlocked != null) return false;
        } else if (!unlocked.equals(other.unlocked)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Special [id=").append(id).append(", type=").append(type).append(", message=").append(message).append(", description=").append(description).append(", finePrint=").append(finePrint).append(", unlocked=").append(unlocked).append(", icon=").append(icon).append(", title=").append(title).append(", state=").append(state).append(", progress=").append(progress).append(", progressDescription=").append(progressDescription).append(", detail=").append(detail).append(", target=").append(target).append(", friendsHere=").append(Arrays.toString(friendsHere)).append("]");
        return builder.toString();
    }
}
