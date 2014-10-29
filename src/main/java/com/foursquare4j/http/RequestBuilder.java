/*
 * Copyright 2014 Daniel Pedraza-Arcega
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP request helper class.
 * 
 * @author Daniel Pedraza-Arcega.
 */
public class RequestBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestBuilder.class);
    private static final int DEFAULT_TIMEOUT = 30000;

    private final String url;
    private final HttpClient httpClient;
    private final List<NameValuePair> formParams;
    private final List<BasicHeader> headers;
    private HttpRequestBase request;

    /**
     * Builds a new RequestBuilder.
     * 
     * @param url the url to use form connection.
     * @param timeout the timeout.
     */
    public RequestBuilder(String url, int timeout) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();
        httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .build();
        formParams = new ArrayList<>();
        headers = new ArrayList<>();
        this.url = url;
    }

    /**
     * Builds a new RequestBuilder with a default timeout of {@link #DEFAULT_TIMEOUT}.
     * 
     * @param url the url to use form connection.
     */
    public RequestBuilder(String url) {
        this(url, DEFAULT_TIMEOUT);
    }

    /**
     * Sets the connection method.
     * 
     * @param method a method.
     * @return this object.
     */
    public RequestBuilder setMethod(Method method) {
        switch (method) {
            case GET:
                request = new HttpGet(url);
                break;
            case POST:
                request = new HttpPost(url);
                break;
            case PUT:
                request = new HttpPut(url);
                break;
            case DELETE:
                request = new HttpDelete(url);
                break;
            default: request = null;
        }

        return this;
    }

    /**
     * Adds a form parameter.
     * 
     * @param name the name of the parameter.
     * @param value the value of the parameter
     * @return this object.
     */
    public RequestBuilder addFormParam(String name, String value) {
        if (name != null && value != null) formParams.add(new BasicNameValuePair(name, value));
        return this;
    }

    /**
     * Adds a header.
     * 
     * @param header a header.
     * @param value a value.
     * @return this object.
     */
    public RequestBuilder addHeader(Header header, String value) {
        if (value != null) headers.add(new BasicHeader(header.getValue(), value));
        return this;
    }

    /**
     * Adds the Accept-Language header with the given locale.
     * 
     * @param locale a locale.
     * @return this object.
     */
    public RequestBuilder addAcceptLanguageHeader(Locale locale) {
        String clientAcceptLanguage = locale.toString().replace('_', '-');
        headers.add(new BasicHeader(Header.ACCEPT_LANGUAGE.getValue(), clientAcceptLanguage));
        return this;
    }

    /** Appends headers, if any, to the request. */
    private void setupHeaders() {
        if (!headers.isEmpty()) {
            LOGGER.debug("Headers: {}", headers);
            request.setHeaders(headers.toArray(new org.apache.http.Header[headers.size()]));
        }
    }

    /** Appends form params, if any, to the request. */
    private void setupFormParams() throws UnsupportedEncodingException {
        if (!formParams.isEmpty() && request instanceof HttpEntityEnclosingRequest) {
            LOGGER.debug("Form params: {}", formParams);
            ((HttpEntityEnclosingRequest) request).setEntity(new UrlEncodedFormEntity(formParams));
        }
    }

    /**
     * Sends the HTTP request to the given url in the constructor using the headers, parameters and
     * method specified the builder methods.
     * 
     * @return the request code or {@code null} if any error occurred.
     */
    public Integer call() {
        try {
            setupHeaders();
            setupFormParams();
            LOGGER.debug("URL: {}", request.getURI());
            LOGGER.debug("Method: {}", request.getMethod());
            HttpResponse response = httpClient.execute(request);
            return response.getStatusLine().getStatusCode();
        } catch (ClientProtocolException ex) {
            LOGGER.error("Wrong request format", ex);
        } catch (SocketTimeoutException ex) {
            LOGGER.error("Connection timed out", ex);
        } catch (ConnectTimeoutException ex) {
            LOGGER.error("Connection timed out", ex);
        } catch (UnsupportedEncodingException ex) {
            LOGGER.error("Wrong encoding used in form params", ex);
        } catch (IOException ex) {
            LOGGER.error("Unknown error", ex);
        } catch (Exception ex) {
            LOGGER.error("Unknown error", ex);
        }

        return null;
    }

    /**
     * Sends the HTTP request to the given url in the constructor using the headers, parameters and
     * method specified the builder methods.
     * 
     * @return the response text or {@code null} if any error occurred.
     */
    public String callForResult() {
        String responseText = null;
        try {
            setupHeaders();
            setupFormParams();
            LOGGER.debug("URL: {}", request.getURI());
            LOGGER.debug("Method: {}", request.getMethod());
            HttpResponse response = httpClient.execute(request);
            LOGGER.debug("Status code: {}", response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            responseText = entity != null ? EntityUtils.toString(entity, StandardCharsets.UTF_8) : null;
        } catch (ClientProtocolException ex) {
            LOGGER.error("Wrong request format", ex);
        } catch (SocketTimeoutException ex) {
            LOGGER.error("Connection timed out", ex);
        } catch (ConnectTimeoutException ex) {
            LOGGER.error("Connection timed out", ex);
        } catch (UnsupportedEncodingException ex) {
            LOGGER.error("Wrong encoding used in form params", ex);
        } catch (IOException ex) {
            LOGGER.error("Unknown error", ex);
        } catch (Exception ex) {
            LOGGER.error("Unknown error", ex);
        }

        return responseText;
    }
}