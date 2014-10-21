package com.foursquare4j.response;

import java.util.Arrays;

public class AttributeGroups extends Count {

    public static class Attribute implements FoursquareResponse {

        private String displayName;
        private String displayValue;

        public String getDisplayName() {
            return displayName;
        }

        protected void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayValue() {
            return displayValue;
        }

        protected void setDisplayValue(String displayValue) {
            this.displayValue = displayValue;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
            result = prime * result + ((displayValue == null) ? 0 : displayValue.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Attribute other = (Attribute) obj;
            if (displayName == null) {
                if (other.displayName != null) return false;
            } else if (!displayName.equals(other.displayName)) return false;
            if (displayValue == null) {
                if (other.displayValue != null) return false;
            } else if (!displayValue.equals(other.displayValue)) return false;
            return true;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Attribute [displayName=").append(displayName).append(", displayValue=").append(displayValue).append("]");
            return builder.toString();
        }
    }

    private String type;
    private String name;
    private String summary;
    private Group<Attribute>[] groups;

    public String getType() {
        return type;
    }

    protected void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    protected void setSummary(String summary) {
        this.summary = summary;
    }

    public Group<Attribute>[] getGroups() {
        return groups;
    }

    protected void setGroups(Group<Attribute>[] groups) {
        this.groups = groups;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(groups);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((summary == null) ? 0 : summary.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        AttributeGroups other = (AttributeGroups) obj;
        if (!Arrays.equals(groups, other.groups)) return false;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        if (summary == null) {
            if (other.summary != null) return false;
        } else if (!summary.equals(other.summary)) return false;
        if (type == null) {
            if (other.type != null) return false;
        } else if (!type.equals(other.type)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AttributeGroups [type=").append(type).append(", name=").append(name).append(", summary=").append(summary).append(", groups=").append(Arrays.toString(groups)).append(", count=").append(getCount()).append("]");
        return builder.toString();
    }
}