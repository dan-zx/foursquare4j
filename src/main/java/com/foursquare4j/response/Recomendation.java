package com.foursquare4j.response;

public class Recomendation {

    public static class Reason extends Count {

        private String summary;
        private String type;
        private String reasonName;
        private String message;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getReasonName() {
            return reasonName;
        }

        public void setReasonName(String reasonName) {
            this.reasonName = reasonName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = super.hashCode();
            result = prime * result + ((message == null) ? 0 : message.hashCode());
            result = prime * result + ((reasonName == null) ? 0 : reasonName.hashCode());
            result = prime * result + ((summary == null) ? 0 : summary.hashCode());
            result = prime * result + ((type == null) ? 0 : type.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!super.equals(obj)) return false;
            if (getClass() != obj.getClass()) return false;
            Reason other = (Reason) obj;
            if (message == null) {
                if (other.message != null) return false;
            } else if (!message.equals(other.message)) return false;
            if (reasonName == null) {
                if (other.reasonName != null) return false;
            } else if (!reasonName.equals(other.reasonName)) return false;
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
            builder.append("Reason [summary=").append(summary).append(", type=").append(type).append(", reasonName=").append(reasonName).append(", message=").append(message).append(", count=").append(getCount()).append("]");
            return builder.toString();
        }
    }

    private Group<Reason> reasons;

    public Group<Reason> getReasons() {
        return reasons;
    }

    public void setReasons(Group<Reason> reasons) {
        this.reasons = reasons;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((reasons == null) ? 0 : reasons.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Recomendation other = (Recomendation) obj;
        if (reasons == null) {
            if (other.reasons != null) return false;
        } else if (!reasons.equals(other.reasons)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Recomendation [reasons=").append(reasons).append("]");
        return builder.toString();
    }
}