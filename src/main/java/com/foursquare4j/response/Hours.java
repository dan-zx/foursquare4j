package com.foursquare4j.response;

import java.util.Arrays;

public class Hours implements FoursquareResponse {

    public static class Timeframe implements FoursquareResponse {

        public static class RenderedTime implements FoursquareResponse {

            private String renderedTime;

            public String getRenderedTime() {
                return renderedTime;
            }

            protected void setRenderedTime(String renderedTime) {
                this.renderedTime = renderedTime;
            }

            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((renderedTime == null) ? 0 : renderedTime.hashCode());
                return result;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null) return false;
                if (getClass() != obj.getClass()) return false;
                RenderedTime other = (RenderedTime) obj;
                if (renderedTime == null) {
                    if (other.renderedTime != null) return false;
                } else if (!renderedTime.equals(other.renderedTime)) return false;
                return true;
            }

            @Override
            public String toString() {
                StringBuilder builder = new StringBuilder();
                builder.append("RenderedTime [renderedTime=").append(renderedTime).append("]");
                return builder.toString();
            }
        }

        private String days;
        private RenderedTime[] open;

        public String getDays() {
            return days;
        }

        protected void setDays(String days) {
            this.days = days;
        }

        public RenderedTime[] getOpen() {
            return open;
        }

        protected void setOpen(RenderedTime[] open) {
            this.open = open;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((days == null) ? 0 : days.hashCode());
            result = prime * result + Arrays.hashCode(open);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Timeframe other = (Timeframe) obj;
            if (days == null) {
                if (other.days != null) return false;
            } else if (!days.equals(other.days)) return false;
            if (!Arrays.equals(open, other.open)) return false;
            return true;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Timeframe [days=").append(days).append(", open=").append(Arrays.toString(open)).append("]");
            return builder.toString();
        }
    }

    private String status;
    private Boolean isOpen;
    private Timeframe[] timeFrames;
    // TODO: private ? segments;
    private String lable;
    private String renderedTime;

    public String getStatus() {
        return status;
    }

    protected void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    protected void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Timeframe[] getTimeFrames() {
        return timeFrames;
    }

    protected void setTimeFrames(Timeframe[] timeFrames) {
        this.timeFrames = timeFrames;
    }

    public String getLable() {
        return lable;
    }

    protected void setLable(String lable) {
        this.lable = lable;
    }

    public String getRenderedTime() {
        return renderedTime;
    }

    protected void setRenderedTime(String renderedTime) {
        this.renderedTime = renderedTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((isOpen == null) ? 0 : isOpen.hashCode());
        result = prime * result + ((lable == null) ? 0 : lable.hashCode());
        result = prime * result + ((renderedTime == null) ? 0 : renderedTime.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + Arrays.hashCode(timeFrames);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Hours other = (Hours) obj;
        if (isOpen == null) {
            if (other.isOpen != null) return false;
        } else if (!isOpen.equals(other.isOpen)) return false;
        if (lable == null) {
            if (other.lable != null) return false;
        } else if (!lable.equals(other.lable)) return false;
        if (renderedTime == null) {
            if (other.renderedTime != null) return false;
        } else if (!renderedTime.equals(other.renderedTime)) return false;
        if (status == null) {
            if (other.status != null) return false;
        } else if (!status.equals(other.status)) return false;
        if (!Arrays.equals(timeFrames, other.timeFrames)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Hours [status=").append(status).append(", isOpen=").append(isOpen).append(", timeFrames=").append(Arrays.toString(timeFrames)).append(", lable=").append(lable).append(", renderedTime=").append(renderedTime).append("]");
        return builder.toString();
    }
}