package com.foursquare4j.response;

import java.util.Objects;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class AccessTokenResponse {

    public static class AccessTokenException extends Exception {

        private static final long serialVersionUID = 1L;

        public AccessTokenException(String json) {
            super(new JsonParser().parse(json).getAsJsonObject().get("error").getAsString());
        }
    }

    private final String accessToken;
    private final AccessTokenException exception;

    public AccessTokenResponse(String json) {
        JsonElement obj = new JsonParser().parse(json).getAsJsonObject().get("access_token");
        if (obj == null) {
            exception = new AccessTokenException(json);
            accessToken = null;
        } else {
            accessToken = obj.getAsString();
            exception = null;
        }
    }

    public String getAccessToken() {
        return accessToken;
    }

    public AccessTokenException getException() {
        return exception;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, getException());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AccessTokenResponse other = (AccessTokenResponse) obj;
        if (accessToken == null) {
            if (other.accessToken != null) return false;
        } else if (!accessToken.equals(other.accessToken)) return false;
        if (exception == null) {
            if (other.exception != null) return false;
        } else if (!getException().equals(other.exception)) return false;
        return true;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("AccessTokenResponse [accessToken=").append(accessToken).append("]").append(", exception=").append(exception).toString();
    }
}