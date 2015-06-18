/*
 * Copyright 2014-2015 Daniel Pedraza-Arcega
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

public class ExploreVenueGroups {

    public static class VenueRecommendation extends Recomendation {

        private Venue venue;

        public Venue getVenue() {
            return venue;
        }

        protected void setVenue(Venue venue) {
            this.venue = venue;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = super.hashCode();
            result = prime * result + ((venue == null) ? 0 : venue.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!super.equals(obj)) return false;
            if (getClass() != obj.getClass()) return false;
            VenueRecommendation other = (VenueRecommendation) obj;
            if (venue == null) {
                if (other.venue != null) return false;
            } else if (!venue.equals(other.venue)) return false;
            return true;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("VenueRecommendation [venue=").append(venue).append(", reasons=").append(getReasons()).append("]");
            return builder.toString();
        }
    }

    // TODO: private ? keywords;
    // TODO: private ? warning;
    private Group<VenueRecommendation>[] groups;
    private Integer suggestedRadius;
    private String headerLocation;
    private String headerFullLocation;
    private String headerMessage;

    public Group<VenueRecommendation>[] getGroups() {
        return groups;
    }

    protected void setGroups(Group<VenueRecommendation>[] groups) {
        this.groups = groups;
    }

    public Integer getSuggestedRadius() {
        return suggestedRadius;
    }

    protected void setSuggestedRadius(Integer suggestedRadius) {
        this.suggestedRadius = suggestedRadius;
    }

    public String getHeaderLocation() {
        return headerLocation;
    }

    protected void setHeaderLocation(String headerLocation) {
        this.headerLocation = headerLocation;
    }

    public String getHeaderFullLocation() {
        return headerFullLocation;
    }

    protected void setHeaderFullLocation(String headerFullLocation) {
        this.headerFullLocation = headerFullLocation;
    }

    public String getHeaderMessage() {
        return headerMessage;
    }

    protected void setHeaderMessage(String headerMessage) {
        this.headerMessage = headerMessage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(groups);
        result = prime * result + ((headerFullLocation == null) ? 0 : headerFullLocation.hashCode());
        result = prime * result + ((headerLocation == null) ? 0 : headerLocation.hashCode());
        result = prime * result + ((headerMessage == null) ? 0 : headerMessage.hashCode());
        result = prime * result + ((suggestedRadius == null) ? 0 : suggestedRadius.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ExploreVenueGroups other = (ExploreVenueGroups) obj;
        if (!Arrays.equals(groups, other.groups)) return false;
        if (headerFullLocation == null) {
            if (other.headerFullLocation != null) return false;
        } else if (!headerFullLocation.equals(other.headerFullLocation)) return false;
        if (headerLocation == null) {
            if (other.headerLocation != null) return false;
        } else if (!headerLocation.equals(other.headerLocation)) return false;
        if (headerMessage == null) {
            if (other.headerMessage != null) return false;
        } else if (!headerMessage.equals(other.headerMessage)) return false;
        if (suggestedRadius == null) {
            if (other.suggestedRadius != null) return false;
        } else if (!suggestedRadius.equals(other.suggestedRadius)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ExploreVenueGroups [groups=").append(Arrays.toString(groups)).append(", suggestedRadius=").append(suggestedRadius).append(", headerLocation=").append(headerLocation).append(", headerFullLocation=").append(headerFullLocation).append(", headerMessage=").append(headerMessage).append("]");
        return builder.toString();
    }
}