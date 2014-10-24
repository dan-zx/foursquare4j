package com.foursquare4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.Properties;

import com.foursquare4j.http.Method;
import com.foursquare4j.http.RequestBuilder;
import com.foursquare4j.response.AccessTokenResponse;
import com.foursquare4j.response.Category;
import com.foursquare4j.response.ExploreVenueGroups;
import com.foursquare4j.response.Parser;
import com.foursquare4j.response.Result;
import com.foursquare4j.response.User;
import com.foursquare4j.response.Venue;

import org.apache.http.client.utils.URIBuilder;
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
            .setAcceptJsonHeaders()
            .callForResult();
        LOGGER.debug("Response ---> {}", json);
        return new AccessTokenResponse(json);
    }
    
    public Result<User> getUser(String userId) {
        String url = newApiUriBuilder()
                .setPath(getString("foursquare.api.path.users", userId))
                .toString();
        String json = new RequestBuilder(url)
            .setMethod(Method.GET)
            .setDefaultHeaders()
            .setAcceptJsonHeaders()
            .callForResult();
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, "user", User.class);
    }
    
    public Result<Venue> getVenue(String venueId) {
        String url = newApiUriBuilder()
                .setPath(getString("foursquare.api.path.venues", venueId))
                .toString();
        String json = new RequestBuilder(url)
            .setMethod(Method.GET)
            .setDefaultHeaders()
            .setAcceptJsonHeaders()
            .callForResult();
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, "venue", Venue.class);
    }

    public Result<Category[]> getCategories() {
        String url = newApiUriBuilder()
                .setPath(getString("foursquare.api.path.venue_categories"))
                .toString();
        String json = new RequestBuilder(url)
            .setMethod(Method.GET)
            .setDefaultHeaders()
            .setAcceptJsonHeaders()
            .callForResult();
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, "categories", Category[].class);
    }

    public Result<Venue[]> searchVenues(String ll, String near, Double llAcc, Integer alt, 
            Double altAcc, String query, Integer limit, String intent, Integer radius, String sw, 
            String ne, String categoryId, String url, String providerId, String linkedId) {
        URIBuilder uriBuilder = newApiUriBuilder()
                .setPath(getString("foursquare.api.path.search_venues"));
        if (ll != null) uriBuilder.addParameter("ll", ll);
        if (near != null) uriBuilder.addParameter("near", near);
        if (llAcc != null) uriBuilder.addParameter("llAcc", String.valueOf(llAcc));
        if (alt != null) uriBuilder.addParameter("alt", String.valueOf(alt));
        if (altAcc != null) uriBuilder.addParameter("altAcc", String.valueOf(altAcc));
        if (query != null) uriBuilder.addParameter("query", query);
        if (limit != null) uriBuilder.addParameter("limit", String.valueOf(limit));
        if (intent != null) uriBuilder.addParameter("intent", intent);
        if (radius != null) uriBuilder.addParameter("radius", String.valueOf(radius));
        if (sw != null) uriBuilder.addParameter("sw", sw);
        if (ne != null) uriBuilder.addParameter("ne", ne);
        if (categoryId != null) uriBuilder.addParameter("categoryId", categoryId);
        if (url != null) uriBuilder.addParameter("url", url);
        if (providerId != null) uriBuilder.addParameter("providerId", providerId);
        if (linkedId != null) uriBuilder.addParameter("linkedId", linkedId);
        String json = new RequestBuilder(uriBuilder.toString())
            .setMethod(Method.GET)
            .setDefaultHeaders()
            .setAcceptJsonHeaders()
            .callForResult();
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, "venues", Venue[].class);
    }

    public Result<ExploreVenueGroups> exploreVenues(String ll, String near, Double llAcc, 
            Integer alt, Double altAcc, Integer radius, String section, String query, Integer limit, 
            Integer offset, String novelty, String friendVisits, String time, String day, 
            Boolean venuePhotos, String lastVenue, Boolean openNow, Boolean sortByDistance, 
            Double price, Boolean saved, Boolean specials) {
        URIBuilder uriBuilder = newApiUriBuilder()
                .setPath(getString("foursquare.api.path.explore_venues"));
        if (ll != null) uriBuilder.addParameter("ll", ll);
        if (near != null) uriBuilder.addParameter("near", near);
        if (llAcc != null) uriBuilder.addParameter("llAcc", String.valueOf(llAcc));
        if (alt != null) uriBuilder.addParameter("alt", String.valueOf(alt));
        if (altAcc != null) uriBuilder.addParameter("altAcc", String.valueOf(altAcc));
        if (radius != null) uriBuilder.addParameter("radius", String.valueOf(radius));
        if (section != null) uriBuilder.addParameter("section", section);
        if (query != null) uriBuilder.addParameter("query", query);
        if (limit != null) uriBuilder.addParameter("limit", String.valueOf(limit));
        if (offset != null) uriBuilder.addParameter("offset", String.valueOf(offset));
        if (novelty != null) uriBuilder.addParameter("novelty", novelty);
        if (friendVisits != null) uriBuilder.addParameter("friendVisits", novelty);
        if (time != null) uriBuilder.addParameter("time", time);
        if (day != null) uriBuilder.addParameter("day", day);
        if (venuePhotos != null) uriBuilder.addParameter("venuePhotos", String.valueOf(venuePhotos ? 1 : 0));
        if (lastVenue != null) uriBuilder.addParameter("lastVenue", lastVenue);
        if (openNow != null) uriBuilder.addParameter("openNow", String.valueOf(openNow ? 1 : 0));
        if (sortByDistance != null) uriBuilder.addParameter("sortByDistance", String.valueOf(sortByDistance ? 1 : 0));
        if (price != null) uriBuilder.addParameter("price", String.valueOf(price));
        if (saved != null) uriBuilder.addParameter("saved", String.valueOf(saved ? 1 : 0));
        if (specials != null) uriBuilder.addParameter("specials", String.valueOf(specials ? 1 : 0));
        String json = new RequestBuilder(uriBuilder.toString())
            .setMethod(Method.GET)
            .setDefaultHeaders()
            .setAcceptJsonHeaders()
            .callForResult();
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, ExploreVenueGroups.class);
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

    private URIBuilder newUriBuilder(String url) {
        try {
            return new URIBuilder(url);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private URIBuilder newApiUriBuilder() {
        URIBuilder uriBuilder = newUriBuilder(getString("foursquare.api.url"))
                .addParameter("v", VERSION);
        if (accessToken != null) uriBuilder.addParameter("oauth_token", accessToken);
        else {
            uriBuilder.addParameter("client_id", clientId)
                    .addParameter("client_secret", clientSecret);
        }
        return uriBuilder;
    }
}