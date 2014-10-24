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
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestBuilder.class);
    private static final int DEFAULT_TIMEOUT = 30000;

    private final String url;
    private final HttpClient httpClient;
    private final List<NameValuePair> formParams;
    private final List<BasicHeader> headers;
    private HttpRequestBase request;

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

    public RequestBuilder(String url) {
        this(url, DEFAULT_TIMEOUT);
    }

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

    public RequestBuilder addFormParam(String name, Object value) {
        if ((name != null && !name.trim().isEmpty()) && value != null) {
            formParams.add(new BasicNameValuePair(name, value.toString()));
        }

        return this;
    }

    public RequestBuilder addHeader(Header header, String value) {
        headers.add(new BasicHeader(header.getValue(), value));
        return this;
    }

    public RequestBuilder setDefaultHeaders() {
        String clientAcceptLanguage = Locale.getDefault().toString().replace('_', '-');
        headers.add(new BasicHeader(Header.ACCEPT_LANGUAGE.getValue(), clientAcceptLanguage));
        return this;
    }

    public RequestBuilder setAcceptJsonHeaders() {
        headers.add(new BasicHeader(Header.ACCEPT.getValue(), ContentType.APPLICATION_JSON.getMimeType()));
        headers.add(new BasicHeader(Header.ACCEPT_CHARSET.getValue(), StandardCharsets.UTF_8.name()));
        return this;
    }

    protected void setUpHeaders() {
        if (!headers.isEmpty()) {
            LOGGER.debug("Headers: {}", headers);
            request.setHeaders(headers.toArray(new org.apache.http.Header[headers.size()]));
        }
    }

    protected void setUpFormParams() throws UnsupportedEncodingException {
        if (!formParams.isEmpty() && request instanceof HttpEntityEnclosingRequest) {
            LOGGER.debug("Form params: {}", formParams);
            ((HttpEntityEnclosingRequest) request).setEntity(new UrlEncodedFormEntity(formParams));
        }
    }

    public Integer call() {
        try {
            setUpHeaders();
            setUpFormParams();
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
    
    public String callForResult() {
        String responseText = null;
        try {
            setUpHeaders();
            setUpFormParams();
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