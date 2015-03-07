/*
 * Copyright 2015 Daniel Pedraza-Arcega
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

public class Page {

    public static class Link {

        private String title;
        private String url;

        public String getTitle() {
            return title;
        }

        protected void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        protected void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((title == null) ? 0 : title.hashCode());
            result = prime * result + ((url == null) ? 0 : url.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Link other = (Link) obj;
            if (title == null) {
                if (other.title != null) return false;
            } else if (!title.equals(other.title)) return false;
            if (url == null) {
                if (other.url != null) return false;
            } else if (!url.equals(other.url)) return false;
            return true;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Link [title=").append(title).append(", url=").append(url).append("]");
            return builder.toString();
        }
    }

    private String title;
    private String description;
    private String banner;
    private Group<Link> links;

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public String getBanner() {
        return banner;
    }

    protected void setBanner(String banner) {
        this.banner = banner;
    }

    public Group<Link> getLinks() {
        return links;
    }

    protected void setLinks(Group<Link> links) {
        this.links = links;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((banner == null) ? 0 : banner.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((links == null) ? 0 : links.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Page other = (Page) obj;
        if (banner == null) {
            if (other.banner != null) return false;
        } else if (!banner.equals(other.banner)) return false;
        if (description == null) {
            if (other.description != null) return false;
        } else if (!description.equals(other.description)) return false;
        if (links == null) {
            if (other.links != null) return false;
        } else if (!links.equals(other.links)) return false;
        if (title == null) {
            if (other.title != null) return false;
        } else if (!title.equals(other.title)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Page [title=").append(title).append(", description=").append(description).append(", banner=").append(banner).append(", links=").append(links).append("]");
        return builder.toString();
    }
}