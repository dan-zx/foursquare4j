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
package com.foursquare4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;

import com.foursquare4j.http.Header;
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
import org.apache.http.entity.ContentType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to handle methods used to perform requests to Foursquare.
 * 
 * @author Daniel Pedraza-Arcega.
 * @since 1.0
 */
public class FoursquareApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(FoursquareApi.class);
    private static final String VERSION = "20141021";

    private final String clientId;
    private final String clientSecret;

    private String accessToken;
    private Locale locale;
    private Properties urls;

    /**
     * Builds a new FoursquareApi.
     * 
     * @param clientId your Foursquare client id.
     * @param clientSecret your Foursquare client secret.
     */
    public FoursquareApi(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        locale = Locale.ENGLISH;
        loadUrlsFile();
    }

    /**
     * Retrieves the access token from Foursquare when using native authentication in clients.
     * 
     * @param authorizationCode an authorization code.
     * @return a AccessTokenResponse.
     */
    public AccessTokenResponse getAccessToken(String authorizationCode) {
        String url = newUriBuilder(getString("foursquare.auth.url"))
                .addParameter("client_id", clientId)
                .addParameter("client_secret", clientSecret)
                .addParameter("grant_type", "authorization_code")
                .addParameter("code", authorizationCode)
                .toString();
        String json = newRequestBuilder(url)
            .setMethod(Method.GET)
            .callForResult();
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
        String url = newApiUriBuilder()
                .setPath(getString("foursquare.api.path.users", userId))
                .toString();
        String json = newRequestBuilder(url)
                .setMethod(Method.GET)
                .callForResult();
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, "user", User.class);
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
        String url = newApiUriBuilder()
                .setPath(getString("foursquare.api.path.venues", venueId))
                .toString();
        String json = newRequestBuilder(url)
                .setMethod(Method.GET)
                .callForResult();
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
    public Result<Category[]> getCategories() {
        String url = newApiUriBuilder()
                .setPath(getString("foursquare.api.path.venue_categories"))
                .toString();
        String json = newRequestBuilder(url)
                .setMethod(Method.GET)
                .callForResult();
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
        String json = newRequestBuilder(uriBuilder.toString())
                .setMethod(Method.GET)
                .callForResult();
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
        String json = newRequestBuilder(uriBuilder.toString())
                .setMethod(Method.GET)
                .callForResult();
        LOGGER.debug("Response ---> {}", json);
        return Parser.parse(json, ExploreVenueGroups.class);
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

    /** Loads the url.properties file. */
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

    /**
     * Gets a string, optionally formatted, from the url.properties file.
     * 
     * @param key the string key.
     * @param params the format parameters.
     * @return a string from the url.properties file.
     */
    private String getString(String key, Object... params) {
        if (params.length > 0) return MessageFormat.format(urls.getProperty(key), params);
        else return urls.getProperty(key);
    }

    /**
     * Builds a new URIBuilder with the given url.
     * 
     * @param url a valid url.
     * @return a new URIBuilder or {@code null} if the url was not valid.
     */
    private URIBuilder newUriBuilder(String url) {
        try {
            return new URIBuilder(url);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    /**
     * Builds a new URIBuilder preinitialized with the Foursquare API url parameters.
     * 
     * @return a new URIBuilder.
     */
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

    /**
     * Builds a new RequestBuilder preinitialized with locale and json headers.
     * 
     * @param url a URL.
     * @return a new RequestBuilder.
     */
    private RequestBuilder newRequestBuilder(String url) {
        return new RequestBuilder(url)
            .addAcceptLanguageHeader(locale)
            .addHeader(Header.ACCEPT, ContentType.APPLICATION_JSON.getMimeType())
            .addHeader(Header.ACCEPT_CHARSET, StandardCharsets.UTF_8.name());
    }
}