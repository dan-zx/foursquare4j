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

import java.util.Objects;

public class Result<T> {

    public static class Meta {

        private Integer code;
        private String errorType;
        private String errorDetail;

        public Integer getCode() {
            return code;
        }

        protected void setCode(Integer code) {
            this.code = code;
        }

        public String getErrorType() {
            return errorType;
        }

        protected void setErrorType(String errorType) {
            this.errorType = errorType;
        }

        public String getErrorDetail() {
            return errorDetail;
        }

        protected void setErrorDetail(String errorDetail) {
            this.errorDetail = errorDetail;
        }

        @Override
        public int hashCode() {
            return Objects.hash(code, errorType, errorDetail);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Meta other = (Meta) obj;
            if (code == null) {
                if (other.code != null) return false;
            } else if (!code.equals(other.code)) return false;
            if (errorDetail == null) {
                if (other.errorDetail != null) return false;
            } else if (!errorDetail.equals(other.errorDetail)) return false;
            if (errorType == null) {
                if (other.errorType != null) return false;
            } else if (!errorType.equals(other.errorType)) return false;
            return true;
        }

        @Override
        public String toString() {
            return new StringBuilder().append("Meta [code=").append(code).append(", errorType=")
                    .append(errorType).append(", errorDetail=").append(errorDetail).append("]")
                    .toString();
        }
    }

    private Meta meta;
    private T response;

    public Meta getMeta() {
        return meta;
    }

    protected void setMeta(Meta meta) {
        this.meta = meta;
    }

    public T getResponse() {
        return response;
    }

    protected void setResponse(T response) {
        this.response = response;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meta, response);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Result<T> other = (Result<T>) obj;
        if (meta == null) {
            if (other.meta != null) return false;
        } else if (!meta.equals(other.meta)) return false;
        if (response == null) {
            if (other.response != null) return false;
        } else if (!response.equals(other.response)) return false;
        return true;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Response [meta=").append(meta).append(", response=")
                .append(response).append("]").toString();
    }
}