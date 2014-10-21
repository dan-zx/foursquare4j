package com.foursquare4j.http;

public enum Method {

    OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT, PATCH;
    
    public String getValue() {
        return name().toLowerCase();
    }
}
