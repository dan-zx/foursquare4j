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
package com.foursquare4j.http;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Builder for {@link URI} instances.
 *
 * @author Daniel Pedraza-Arcega.
 * @since 1.0
 */
public class UriBuilder {

    private String host;
    private int port;
    private String fragment;
    private String userInfo;
    private String path;
    private String scheme;
    private Map<String, String> parameters;

    /** Constructs an instance for building an URI from scratch. */
    public UriBuilder() {
        parameters = new HashMap<>(); 
        port = -1;
        path = "";
    }

    /**
     * Constructs an instance from the string which must be a valid URI.
     *
     * @param string a valid URI in string form.
     */
    public UriBuilder(String string) {
        try {
            URI uri = new URI(string);
            host = uri.getHost();
            port = uri.getPort();
            fragment = uri.getFragment();
            parameters = parseParameters(uri.getQuery());
            userInfo = uri.getUserInfo();
            path = uri.getPath();
            scheme = uri.getScheme();
        } catch (URISyntaxException ex) {
            throw new IllegalArgumentException("Invalid url", ex);
        }
    }

    public UriBuilder scheme(String scheme) {
        this.scheme = scheme;
        return this;
    }

    public UriBuilder host(String host) {
        this.host = host;
        return this;
    }

    public UriBuilder port(int port) {
        this.port = port;
        return this;
    }

    public UriBuilder userInfo(String userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    public UriBuilder path(String path) {
        this.path = path;
        return this;
    }

    public UriBuilder addParameter(String param, String value) {
        parameters.put(param, value);
        return this;
    }

    public UriBuilder fragment(String fragment) {
        this.fragment = fragment;
        return this;
    }    

    public String getScheme() {
        return scheme;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

    public String getQuery() {
        if (parameters.isEmpty()) return null;
        return parameters.entrySet()
                .stream()
                .map(key -> String.format("%s=%s", decode(key.getKey()), decode(key.getValue())))
                .collect(Collectors.joining("&"));
    }

    public String getFragment() {
        return fragment;
    }       

    public URI toUri() {
        try {
            return new URI(scheme, userInfo, host, port, path, getQuery(), fragment);
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String toString() {
        return toUri().toASCIIString();
    }

    /**
     * Parses existing query paramaters.
     * @param query a query.
     * @return a Map with the existing paramaters.
     */
    private Map<String, String> parseParameters(String query) {
        Map<String, String> map = new HashMap<>();
        if (query != null && !query.trim().isEmpty()) {
            String[] params = query.split("&");
            for (String param : params) {
                int idx = param.indexOf('=');
                if (idx >= 0) map.put(decode(param.substring(0, idx)), decode(param.substring(idx + 1)));
            }
        }
        return map;
    }

    /**
     * Decodes a string in UTF-8.
     * 
     * @param value a string to be decoded.
     * @return the decoded value.
     */
    private String decode(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
}