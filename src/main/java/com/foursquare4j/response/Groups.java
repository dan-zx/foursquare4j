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

public class Groups<T> extends Count {

    private String summary;
    private Group<T>[] groups;

    public String getSummary() {
        return summary;
    }

    protected void setSummary(String summary) {
        this.summary = summary;
    }

    public Group<T>[] getGroups() {
        return groups;
    }

    protected void setGroups(Group<T>[] groups) {
        this.groups = groups;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(groups);
        result = prime * result + ((summary == null) ? 0 : summary.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        Groups<T> other = (Groups<T>) obj;
        if (!Arrays.equals(groups, other.groups)) return false;
        if (summary == null) {
            if (other.summary != null) return false;
        } else if (!summary.equals(other.summary)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Groups [summary=").append(summary).append(", groups=").append(Arrays.toString(groups)).append(", count()=").append(getCount()).append("]");
        return builder.toString();
    }
}