package com.foursquare4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Properties;

import com.foursquare4j.http.Header;
import com.foursquare4j.http.Method;
import com.foursquare4j.http.RequestBuilder;
import com.foursquare4j.response.AccessTokenResponse;
import com.foursquare4j.response.Category;
import com.foursquare4j.response.Parser;
import com.foursquare4j.response.Result;
import com.foursquare4j.response.User;
import com.foursquare4j.response.Venue;

import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FoursquareApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(FoursquareApi.class);
    private static final String VERSION = "20141021";

    private final String clientId;
    private final String clientSecret;

    private String accessToken;
    private Properties urls;

    public FoursquareApi(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        loadUrlsFile();
    }

    public AccessTokenResponse getAccessToken(String authorizationCode) {
        String url = newUriBuilder(getString("foursquare.auth.url"))
                .addParameter("client_id", clientId)
                .addParameter("client_secret", clientSecret)
                .addParameter("grant_type", "authorization_code")
                .addParameter("code", authorizationCode)
                .toString();
        String json = new RequestBuilder(url)
            .setMethod(Method.GET)
            .setDefaultHeaders()
            .addHeader(Header.ACCEPT, ContentType.APPLICATION_JSON.getMimeType())
            .addHeader(Header.ACCEPT_CHARSET, StandardCharsets.UTF_8.name())
            .callForResult();
        LOGGER.debug("Response ---> {}", json);
        return new AccessTokenResponse(json);
    }
    
    public Result<User> getUser(String userId) {
        URIBuilder uriBuilder = newUriBuilder(getString("foursquare.api.url"))
                .setPath(getString("foursquare.api.path.users", userId))
                .addParameter("client_id", clientId)
                .addParameter("client_secret", clientSecret)
                .addParameter("v", VERSION);
        if (isAuthenticated()) uriBuilder.addParameter("oauth_token", accessToken);
        String json = new RequestBuilder(uriBuilder.toString())
            .setMethod(Method.GET)
            .setDefaultHeaders()
            .addHeader(Header.ACCEPT, ContentType.APPLICATION_JSON.getMimeType())
            .addHeader(Header.ACCEPT_CHARSET, StandardCharsets.UTF_8.name())
            .callForResult();
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, "user", User.class);
    }
    
    public Result<Venue> getVenue(String venueId) {
        URIBuilder uriBuilder = newUriBuilder(getString("foursquare.api.url"))
                .setPath(getString("foursquare.api.path.venues", venueId))
                .addParameter("client_id", clientId)
                .addParameter("client_secret", clientSecret)
                .addParameter("v", VERSION);
        if (isAuthenticated()) uriBuilder.addParameter("oauth_token", accessToken);
        String json = new RequestBuilder(uriBuilder.toString())
            .setMethod(Method.GET)
            .setDefaultHeaders()
            .addHeader(Header.ACCEPT, ContentType.APPLICATION_JSON.getMimeType())
            .addHeader(Header.ACCEPT_CHARSET, StandardCharsets.UTF_8.name())
            .callForResult();
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, "venue", Venue.class);
    }

    public Result<Category[]> getCategories() {
        String url = newUriBuilder(getString("foursquare.api.url"))
                .setPath(getString("foursquare.api.path.categories"))
                .addParameter("client_id", clientId)
                .addParameter("client_secret", clientSecret)
                .addParameter("v", VERSION)
                .toString();
        String json = new RequestBuilder(url)
            .setMethod(Method.GET)
            .setDefaultHeaders()
            .addHeader(Header.ACCEPT, ContentType.APPLICATION_JSON.getMimeType())
            .addHeader(Header.ACCEPT_CHARSET, StandardCharsets.UTF_8.name())
            .callForResult();
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, "categories", Category[].class);
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    private void loadUrlsFile() {
        urls = new Properties();
        InputStream stream = null;
        try {
            stream = this.getClass().getResourceAsStream("/com/foursquare4j/urls.properties");
            urls.load(stream);
        } catch (IOException ex) {
            LOGGER.error("Couldn't load urls file", ex);
        } finally {
            try {
                stream.close();
            } catch (IOException ex) { }
        }
    }

    private String getString(String key, Object... params) {
        if (params.length > 0) return MessageFormat.format(urls.getProperty(key), params);
        else return urls.getProperty(key);
    }

    private boolean isAuthenticated() {
        return accessToken != null && !accessToken.trim().isEmpty();
    }

    private URIBuilder newUriBuilder(String url) {
        try {
            return new URIBuilder(url);
        } catch (URISyntaxException e) {
            return null;
        }
    }
}