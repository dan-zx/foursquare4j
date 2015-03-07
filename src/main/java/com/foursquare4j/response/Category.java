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

import java.util.Arrays;

public class Category {

    private String id;
    private String name;
    private String pluralName;
    private String shortName;
    private Photo icon;
    private Boolean primary;
    private Category[] categories;

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

    public String getPluralName() {
        return pluralName;
    }

    protected void setPluralName(String pluralName) {
        this.pluralName = pluralName;
    }

    public String getShortName() {
        return shortName;
    }

    protected void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Photo getIcon() {
        return icon;
    }

    protected void setIcon(Photo icon) {
        this.icon = icon;
    }

    public Boolean getPrimary() {
        return primary;
    }

    protected void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public Category[] getCategories() {
        return categories;
    }

    protected void setCategories(Category[] categories) {
        this.categories = categories;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(categories);
        result = prime * result + ((icon == null) ? 0 : icon.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((pluralName == null) ? 0 : pluralName.hashCode());
        result = prime * result + ((primary == null) ? 0 : primary.hashCode());
        result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Category other = (Category) obj;
        if (!Arrays.equals(categories, other.categories)) return false;
        if (icon == null) {
            if (other.icon != null) return false;
        } else if (!icon.equals(other.icon)) return false;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        if (pluralName == null) {
            if (other.pluralName != null) return false;
        } else if (!pluralName.equals(other.pluralName)) return false;
        if (primary == null) {
            if (other.primary != null) return false;
        } else if (!primary.equals(other.primary)) return false;
        if (shortName == null) {
            if (other.shortName != null) return false;
        } else if (!shortName.equals(other.shortName)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Category [id=").append(id).append(", name=").append(name).append(", pluralName=").append(pluralName).append(", shortName=").append(shortName).append(", icon=").append(icon).append(", primary=").append(primary).append(", categories=").append(Arrays.toString(categories)).append("]");
        return builder.toString();
    }
}