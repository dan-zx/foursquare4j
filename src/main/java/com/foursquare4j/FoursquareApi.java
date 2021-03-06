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
package com.foursquare4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foursquare4j.http.ContentType;
import com.foursquare4j.http.Header;
import com.foursquare4j.response.AccessTokenResponse;
import com.foursquare4j.response.Category;
import com.foursquare4j.response.ExploreVenueGroups;
import com.foursquare4j.response.Group;
import com.foursquare4j.response.List;
import com.foursquare4j.response.Parser;
import com.foursquare4j.response.Result;
import com.foursquare4j.response.User;
import com.foursquare4j.response.Venue;

import com.google.gson.reflect.TypeToken;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Class to handle methods used to perform requests to Foursquare.
 * 
 * @author Daniel Pedraza-Arcega.
 * @since 1.0
 */
public class FoursquareApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(FoursquareApi.class);
    private static final String VERSION = "20150208";

    private String clientId;
    private String clientSecret;
    private String accessToken;
    private Locale locale;
    private OkHttpClient client;

    /**
     * Builds a new FoursquareApi. Responses are in English by default.
     * 
     * @param clientId your Foursquare client id.
     * @param clientSecret your Foursquare client secret.
     */
    public FoursquareApi(String clientId, String clientSecret) {
        if (clientId == null) throw new IllegalArgumentException("clientId == null");
        if (clientSecret == null) throw new IllegalArgumentException("clientSecret == null");
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /**
     * Retrieves the access token from Foursquare when using native authentication in clients.
     * 
     * @param redirectUri your registered redirect uri. Pass {@code null} if you got the code from a
     *        native app (iOS or Android).
     * @param code the authorization code.
     * @return a AccessTokenResponse.
     */
    public AccessTokenResponse getAccessToken(String redirectUri, String code) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(authUrl()).newBuilder()
                .addQueryParameter("client_id", clientId)
                .addQueryParameter("client_secret", clientSecret)
                .addQueryParameter("grant_type", "authorization_code")
                .addQueryParameter("code", code);
        if (redirectUri != null) urlBuilder.addQueryParameter("redirect_uri", redirectUri);
        String json = newRequest(urlBuilder.toString());
        LOGGER.debug("Response ---> {}", json);
        return new AccessTokenResponse(json);
    }

    /**
     * Returns profile information for a given user, including selected badges and mayorships.
     * 
     * @see <a href="https://developer.foursquare.com/docs/users/users">User - Foursquare API
     *      Documentation</a>
     * @param userId Identity of the user to get details for. Pass self to get details of the acting
     *        user.
     * @return a User object wrapped in a Result object.
     */
    public Result<User> getUser(String userId) {
        String url = newApiUrlBuilder()
                .addPathSegment("users")
                .addPathSegment(userId)
                .toString();
        String json = newRequest(url);
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, "user", User.class);
    }

    /**
     * Returns an array of a user's friends.
     * 
     * @param userId Identity of the user to get friends of. Pass self to get friends of the acting
     *        user.
     * @param limit Number of results to return, up to 500.
     * @param offset Used to page through results.
     * @return a Group of Users wrapped in a Result object.
     */
    public Result<Group<User>> getUserFriends(String userId, Integer limit, Integer offset) {
        HttpUrl.Builder urlBuilder = newApiUrlBuilder()
                .addPathSegment("users")
                .addPathSegment(userId)
                .addPathSegment("friends");
        if (limit != null) urlBuilder.addQueryParameter("limit", String.valueOf(limit));
        if (offset != null) urlBuilder.addQueryParameter("offset", String.valueOf(offset));
        String json = newRequest(urlBuilder.toString());
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, "friends", new TypeToken<Group<User>>(){});
    }

    /**
     * Returns tips from a user.
     * 
     * @param userId Identity of the user to get tips from. Pass self to get tips of the acting
     *        user.
     * @param limit Number of results to return, up to 200.
     * @param offset The number of results to skip. Used to page through results.
     * @param llBounds optional Restricts the returned results to the input bounding box.
     * @param categoryId optional Restricts the returned results to venues matching the input
     *        category id.
     * @param sort optional Sorts the list items. Possible values are recent and nearby. recent
     *        sorts the list items by the date added to the list. nearby sorts the list items by the
     *        distance from the center of the provided llBounds.
     * @return a List of tips
     */
    public Result<List> getUserTips(String userId, Integer limit, Integer offset, 
            String llBounds, String categoryId, String sort) {
        HttpUrl.Builder urlBuilder = newApiUrlBuilder()
                .addPathSegment("lists")
                .addPathSegment(userId)
                .addPathSegment("tips");
        if (limit != null) urlBuilder.addQueryParameter("limit", String.valueOf(limit));
        if (offset != null) urlBuilder.addQueryParameter("offset", String.valueOf(offset));
        if (llBounds != null) urlBuilder.addQueryParameter("llBounds", llBounds);
        if (categoryId != null) urlBuilder.addQueryParameter("categoryId", categoryId);
        if (sort != null) urlBuilder.addQueryParameter("sort", sort);
        String json = newRequest(urlBuilder.toString());
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, "list", List.class);
    }

    /**
     * Returns a list of venues liked by the specified user.
     * 
     * @param userId User ID or self.
     * @param beforeTimestamp Seconds since epoch..
     * @param afterTimestamp Seconds since epoch.
     * @param categoryId Limits returned venues to those in this category. If specifying a top-level
     *        category, all sub-categories will also match the query.
     * @param limit Number of results to return.
     * @param offset Used to page through results.
     * @return a Group of Venues wrapped in a Result object.
     */
    public Result<Group<Venue>> getUserVenueLikes(String userId, Long beforeTimestamp, 
            Long afterTimestamp, String categoryId, Integer limit, Integer offset) {
        HttpUrl.Builder urlBuilder = newApiUrlBuilder()
                .addPathSegment("users")
                .addPathSegment(userId)
                .addPathSegment("venuelikes");
        if (beforeTimestamp != null) urlBuilder.addQueryParameter("beforeTimestamp", String.valueOf(beforeTimestamp));
        if (afterTimestamp != null) urlBuilder.addQueryParameter("afterTimestamp", String.valueOf(afterTimestamp));
        if (categoryId != null) urlBuilder.addQueryParameter("categoryId", categoryId);
        if (limit != null) urlBuilder.addQueryParameter("limit", String.valueOf(limit));
        if (offset != null) urlBuilder.addQueryParameter("offset", String.valueOf(offset));
        String json = newRequest(urlBuilder.toString());
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, "venues", new TypeToken<Group<Venue>>(){});
    }

    /**
     * Gives details about a venue, including location, mayorship, tags, tips, specials, and
     * category. Authenticated users will also receive information about who is here now.
     * 
     * @see <a href="https://developer.foursquare.com/docs/venues/venues">Venue Detail - Foursquare
     *      API Documentation</a>
     * @param venueId ID of venue to retrieve
     * @return a Venue object wrapped in a Result object.
     */
    public Result<Venue> getVenue(String venueId) {
        String url = newApiUrlBuilder()
                .addPathSegment("venues")
                .addPathSegment(venueId)
                .toString();
        String json = newRequest(url);
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, "venue", Venue.class);
    }

    /**
     * Returns a hierarchical list of categories applied to venues.
     * 
     * @see <a href="https://developer.foursquare.com/docs/venues/categories">Venue Categories -
     *      Foursquare API Documentation</a>
     * @return an array of Category objects wrapped in a Result object.
     */
    public Result<Category[]> getVenueCategories() {
        String url = newApiUrlBuilder()
                .addPathSegment("venues")
                .addPathSegment("categories")
                .toString();
        String json = newRequest(url);
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, "categories", Category[].class);
    }

    /**
     * Returns a list of venues near the current location, optionally matching a search term. To
     * ensure the best possible results, pay attention to the intent parameter below. And if you're
     * looking for "top" venues or recommended venues, use the explore endpoint instead. If lat and
     * long is provided, each venue includes a distance. If authenticated, the method will return
     * venue metadata related to you and your friends. If you do not authenticate, you will not get
     * this data.
     * 
     * @see <a href="https://developer.foursquare.com/docs/venues/search">Search Venues - Foursquare
     *      API Documentation</a>
     * @param ll required unless near is provided. Latitude and longitude of the user's location.
     *        (Required for query searches). Optional if using intent=global
     * @param near required unless ll is provided. A string naming a place in the world. If the near
     *        string is not geocodable, returns a failed_geocode error. Otherwise, searches within
     *        the bounds of the geocode. Adds a geocode object to the response. (Required for query
     *        searches)
     * @param llAcc Accuracy of latitude and longitude, in meters. (Does not currently affect search
     *        results.)
     * @param alt Altitude of the user's location, in meters. (Does not currently affect search
     *        results.)
     * @param altAcc Accuracy of the user's altitude, in meters. (Does not currently affect search
     *        results.)
     * @param query A search term to be applied against venue names.
     * @param limit Number of results to return, up to 50.
     * @param intent One of the values below, indicating your intent in performing the search. If no
     *        value is specified, defaults to checkin.
     *        <table summary="">
     *          <tbody>
     *             <tr>
     *                <td><tt>checkin</tt></td>
     *                <td>
     *                    Finds results that the current user (or, for userless requests, a typical 
     *                    user) is likely to check in to at the provided <tt>ll</tt> at the current 
     *                    moment in time. This is the intent we recommend most apps use.
     *                </td>
     *             </tr>
     *             <tr>
     *                <td><tt>browse</tt></td>
     *                <td>
     *                    Find venues within a given area. Unlike the <tt>checkin</tt> intent, 
     *                    <tt>browse</tt> searches an entire region instead of only finding Venues 
     *                    closest to a point. You must define a region to search be including either
     *                    the <tt>ll</tt> and <tt>radius</tt> parameters, or the <tt>sw</tt> and 
     *                    <tt>ne</tt>. The region will be a spherical cap if you include the 
     *                    <tt>ll</tt> and <tt>radius</tt> parameters, or it will be a bounding 
     *                    quadrangle if you include the <tt>sw</tt> and <tt>ne</tt> parameters.
     *                </td>
     *             </tr>
     *             <tr>
     *                <td><tt>global</tt></td>
     *                <td>
     *                    Finds the most globally relevant venues for the search, independent of 
     *                    location. Ignores all other parameters other than <tt>query</tt> and 
     *                    <tt>limit</tt>.
     *                </td>
     *             </tr>
     *             <tr>
     *                <td><tt>match</tt></td>
     *                <td>
     *                    Finds venues that are are nearly-exact matches for the given parameters. 
     *                    This intent is <strong>highly sensitive</strong> to the provided location. 
     *                    We recommend using this intent only when trying to correlate an existing 
     *                    place database with Foursquare's. The results will be sorted best match 
     *                    first, taking distance and spelling mistakes/variations into account.
     *                    <br><br>     <tt>query</tt> and <tt>ll</tt> are the only required 
     *                    parameters for this intent, but matching also supports <tt>phone</tt>, 
     *                    <tt>address</tt>, <tt>city</tt>, <tt>state</tt>, <tt>zip</tt>, and 
     *                    <tt>twitter</tt>. There's no specified format for these parameters—we do 
     *                    our best to normalize them and drop them from the search if unsuccessful.
     *                </td>
     *             </tr>
     *          </tbody>
     *       </table>
     * @param radius Limit results to venues within this many meters of the specified location.
     *        Defaults to a city-wide area. Only valid for requests with intent=browse, or requests
     *        with intent=checkin and categoryId or query. Does not apply to match intent requests.
     *        The maximum supported radius is currently 100,000 meters.
     * @param sw With ne, limits results to the bounding quadrangle defined by the latitude and
     *        longitude given by sw as its south-west corner, and ne as its north-east corner. The
     *        bounding quadrangle is only supported for intent=browse searches. Not valid with ll or
     *        radius. Bounding quadrangles with an area up to approximately 10,000 square kilometers
     *        are supported.
     * @param ne See sw
     * @param categoryId A comma separated list of categories to limit results to. If you specify
     *        categoryId specifying a radius may improve results. If specifying a top-level
     *        category, all sub-categories will also match the query. Does not apply to match intent
     *        requests.
     * @param url A third-party URL which we will attempt to match against our map of venues to
     *        URLs.
     * @param providerId Identifier for a known third party that is part of our map of venues to
     *        URLs, used in conjunction with linkedId.
     * @param linkedId 1002207971611 Identifier used by third party specified in providerId, which
     *        we will attempt to match against our map of venues to URLs.
     * @return an array of Venue objects wrapped in a Result object.
     */
    public Result<Venue[]> searchVenues(String ll, String near, Double llAcc, Integer alt, 
            Double altAcc, String query, Integer limit, String intent, Integer radius, String sw, 
            String ne, String categoryId, String url, String providerId, String linkedId) {
        HttpUrl.Builder urlBuilder = newApiUrlBuilder()
                .addPathSegment("venues")
                .addPathSegment("search");
        if (ll != null) urlBuilder.addQueryParameter("ll", ll);
        if (near != null) urlBuilder.addQueryParameter("near", near);
        if (llAcc != null) urlBuilder.addQueryParameter("llAcc", String.valueOf(llAcc));
        if (alt != null) urlBuilder.addQueryParameter("alt", String.valueOf(alt));
        if (altAcc != null) urlBuilder.addQueryParameter("altAcc", String.valueOf(altAcc));
        if (query != null) urlBuilder.addQueryParameter("query", query);
        if (limit != null) urlBuilder.addQueryParameter("limit", String.valueOf(limit));
        if (intent != null) urlBuilder.addQueryParameter("intent", intent);
        if (radius != null) urlBuilder.addQueryParameter("radius", String.valueOf(radius));
        if (sw != null) urlBuilder.addQueryParameter("sw", sw);
        if (ne != null) urlBuilder.addQueryParameter("ne", ne);
        if (categoryId != null) urlBuilder.addQueryParameter("categoryId", categoryId);
        if (url != null) urlBuilder.addQueryParameter("url", url);
        if (providerId != null) urlBuilder.addQueryParameter("providerId", providerId);
        if (linkedId != null) urlBuilder.addQueryParameter("linkedId", linkedId);
        String json = newRequest(urlBuilder.toString());
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, "venues", Venue[].class);
    }

    /**
     * Returns a list of recommended venues near the current location. If authenticated, the method
     * will potentially personalize the ranking based on you and your friends. If you do not
     * authenticate, you will not get this personalization.
     * 
     * @see <a href="https://developer.foursquare.com/docs/venues/explore">Explore Recommended and
     *      Popular Venues - Foursquare API Documentation</a>
     * @param ll required unless near is provided. Latitude and longitude of the user's location.
     * @param near required unless ll is provided. A string naming a place in the world. If the near
     *        string is not geocodable, returns a failed_geocode error. Otherwise, searches within
     *        the bounds of the geocode and adds a geocode object to the response.
     * @param llAcc Accuracy of latitude and longitude, in meters.
     * @param alt Altitude of the user's location, in meters.
     * @param altAcc Accuracy of the user's altitude, in meters.
     * @param radius Radius to search within, in meters. If radius is not specified, a suggested
     *        radius will be used based on the density of venues in the area.
     * @param section One of food, drinks, coffee, shops, arts, outdoors, sights, trending or
     *        specials, nextVenues (venues frequently visited after a given venue), or topPicks (a
     *        mix of recommendations generated without a query from the user). Choosing one of these
     *        limits results to venues with the specified category or property.
     * @param query A term to be searched against a venue's tips, category, etc. The query parameter
     *        has no effect when a section is specified.
     * @param limit Number of results to return, up to 50.
     * @param offset Used to page through results.
     * @param novelty Pass new or old to limit results to places the acting user hasn't been or has
     *        been, respectively. Omitting this parameter returns a mixture of old and new venues.
     * @param friendVisits Pass visited or notvisited to limit results to places the acting user's
     *        friends have or haven't been, respectively. Omitting this parameter returns a mixture
     *        of venues to which the user's friends have or haven't been.
     * @param time Pass any to retrieve results for any time of day. Omitting this parameter returns
     *        results targeted to the current time of day.
     * @param day Pass any to retrieve results for any day of the week. Omitting this parameter
     *        returns results targeted to the current day of the week.
     * @param venuePhotos Boolean flag to include a photo in the response for each venue, if one is
     *        available. Default is 0 (no photos). Photos are returned as part of the venue JSON
     *        object.
     * @param lastVenue A venue ID to use in combination with the intent=nextVenues parameter, which
     *        returns venues users often visit after a given venue. If intent=nextVenues is
     *        specified but lastVenue is not, the user's last check-in will be used if it is within
     *        2 hours. If the user has not checked in within the last 2 hours, no results will be
     *        returned.
     * @param openNow Boolean flag to only include venues that are open now. This prefers official
     *        provider hours but falls back to popular check-in hours.
     * @param sortByDistance Boolean flag to sort the results by distance instead of relevance.
     * @param price Comma separated list of price points. Currently the valid range of price points
     *        are [1,2,3,4], 1 being the least expensive, 4 being the most expensive. For food
     *        venues, in the United States, 1 is  {@literal <} $10 an entree, 2 is $10-$20 an 
     *        entree, 3 is $20-$30 an entree, 4 is {@literal >} $30 an entree.
     * @param saved Boolean flag to only include venues that the user has saved on their To-Do list
     *        or to another list.
     * @param specials Boolean flag to only include venues that have a special.
     * @return an ExploreVenueGroups objects wrapped in a Result object.
     */
    public Result<ExploreVenueGroups> exploreVenues(String ll, String near, Double llAcc, 
            Integer alt, Double altAcc, Integer radius, String section, String query, Integer limit, 
            Integer offset, String novelty, String friendVisits, String time, String day, 
            Boolean venuePhotos, String lastVenue, Boolean openNow, Boolean sortByDistance, 
            Double price, Boolean saved, Boolean specials) {
        HttpUrl.Builder urlBuilder = newApiUrlBuilder()
                .addPathSegment("venues")
                .addPathSegment("explore");
        if (ll != null) urlBuilder.addQueryParameter("ll", ll);
        if (near != null) urlBuilder.addQueryParameter("near", near);
        if (llAcc != null) urlBuilder.addQueryParameter("llAcc", String.valueOf(llAcc));
        if (alt != null) urlBuilder.addQueryParameter("alt", String.valueOf(alt));
        if (altAcc != null) urlBuilder.addQueryParameter("altAcc", String.valueOf(altAcc));
        if (radius != null) urlBuilder.addQueryParameter("radius", String.valueOf(radius));
        if (section != null) urlBuilder.addQueryParameter("section", section);
        if (query != null) urlBuilder.addQueryParameter("query", query);
        if (limit != null) urlBuilder.addQueryParameter("limit", String.valueOf(limit));
        if (offset != null) urlBuilder.addQueryParameter("offset", String.valueOf(offset));
        if (novelty != null) urlBuilder.addQueryParameter("novelty", novelty);
        if (friendVisits != null) urlBuilder.addQueryParameter("friendVisits", novelty);
        if (time != null) urlBuilder.addQueryParameter("time", time);
        if (day != null) urlBuilder.addQueryParameter("day", day);
        if (venuePhotos != null) urlBuilder.addQueryParameter("venuePhotos", String.valueOf(venuePhotos ? 1 : 0));
        if (lastVenue != null) urlBuilder.addQueryParameter("lastVenue", lastVenue);
        if (openNow != null) urlBuilder.addQueryParameter("openNow", String.valueOf(openNow ? 1 : 0));
        if (sortByDistance != null) urlBuilder.addQueryParameter("sortByDistance", String.valueOf(sortByDistance ? 1 : 0));
        if (price != null) urlBuilder.addQueryParameter("price", String.valueOf(price));
        if (saved != null) urlBuilder.addQueryParameter("saved", String.valueOf(saved ? 1 : 0));
        if (specials != null) urlBuilder.addQueryParameter("specials", String.valueOf(specials ? 1 : 0));
        String json = newRequest(urlBuilder.toString());
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, ExploreVenueGroups.class);
    }

    /**
     * Returns venues that people often check in to after the current venue. Up to 5 venues are
     * returned in each query, and results are sorted by how many people have visited that venue
     * after the current one. Homes are never returned in results.
     * 
     * @param venueId ID of the venue you want to see next venue information about.
     * @return Compact venues.
     */
    public Result<Group<Venue>> getNextVenues(String venueId) {
        String url = newApiUrlBuilder()
                .addPathSegment("venues")
                .addPathSegment(venueId)
                .addPathSegment("nextvenues")
                .toString();
        String json = newRequest(url);
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, "nextVenues", new TypeToken<Group<Venue>>(){});
    }

    /**
     * Sets the access token authentication.
     * 
     * @param accessToken a valid access token.
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Sets the locale for Foursquare responses.
     * 
     * @see <a
     *      href="https://developer.foursquare.com/overview/versioning#internationalization">Versioning
     *      &amp; Internationalization - Foursquare API Documentation</a>
     * @param locale a locale.
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Builds a new HttpUrl.Builder preinitialized with the Foursquare API url parameters.
     * 
     * @return a new HttpUrl.Builder.
     */
    private HttpUrl.Builder newApiUrlBuilder() {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(apiUrl()).newBuilder()
                .addQueryParameter("v", VERSION);
        if (accessToken != null) urlBuilder.addQueryParameter("oauth_token", accessToken);
        else {
            urlBuilder.addQueryParameter("client_id", clientId)
                .addQueryParameter("client_secret", clientSecret);
        }
        return urlBuilder;
    }

    /**
     * Makes a new request to given URL for JSON response.
     * 
     * @param url a URL.
     * @return a JSON response.
     * @throws RuntimeException when conection errors occur.
     */
    private String newRequest(String url) {
        LOGGER.debug("{}", url);
        Request.Builder requestBuilder = new Request.Builder().url(url)
            .header(Header.ACCEPT.getValue(), ContentType.APPLICATION_JSON.getMimeType())
            .header(Header.ACCEPT_CHARSET.getValue(), StandardCharsets.UTF_8.name());
        if (locale != null) requestBuilder.header(Header.ACCEPT_LANGUAGE.getValue(), locale.getLanguage());
        if (client == null) client = new OkHttpClient();
        try {
            Response response = client.newCall(requestBuilder.build()).execute();
            LOGGER.debug("Response code ---> {}", response.code());
            return response.body().string();
        } catch (IOException ex) {
            LOGGER.error("Error while making request", ex);
            throw new RuntimeException(ex);
        }
    }

    /** @return Foursquare authentication URL. Useful for mock responses. */
    public String authUrl() {
        return "https://foursquare.com/oauth2/access_token";
    }

    /** @return Foursquare API URL. Useful for mock responses. */
    public String apiUrl() {
        return "https://api.foursquare.com/v2";
    }
}