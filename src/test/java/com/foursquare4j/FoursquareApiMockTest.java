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
package com.foursquare4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.foursquare4j.response.AccessTokenResponse;

import com.foursquare4j.test.util.HttpStatus;
import com.foursquare4j.response.Category;
import com.foursquare4j.response.ExploreVenueGroups;
import com.foursquare4j.response.Group;
import com.foursquare4j.response.List;
import com.foursquare4j.response.Result;
import com.foursquare4j.response.User;
import com.foursquare4j.response.Venue;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class FoursquareApiMockTest {

    private static MockWebServer mockWebServer;
    private static FoursquareApi foursquareApi;

    @BeforeClass
    public static void setUpClass() throws Exception {
        foursquareApi = mock(FoursquareApi.class, CALLS_REAL_METHODS);
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        String mockUrl = mockWebServer.getUrl("/").toString();
        when(foursquareApi.authUrl()).thenReturn(mockUrl);
        when(foursquareApi.apiUrl()).thenReturn(mockUrl);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void testAccessToken() throws Exception {
        mockWebServer.enqueue(new MockResponse()
            .setStatus(HttpStatus.OK.toString())
            .setBody(getJsonFrom("responses/access-token.json")));
        
        AccessTokenResponse response = foursquareApi.getAccessToken("http://nowhere.com", "fakeCode");
        
        assertThat(response).isNotNull();
        assertThat(response.getException()).isNull();
        assertThat(response.getAccessToken()).isNotNull().isNotEmpty().isEqualTo("JHKSDFKHJEFAKE23094LKJSDLJK2394KJLS");
    }

    @Test
    public void testAccessTokenError() throws Exception {
        mockWebServer.enqueue(new MockResponse()
            .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString())
            .setBody(getJsonFrom("responses/error-access-token.json")));
        
        AccessTokenResponse response = foursquareApi.getAccessToken("http://nowhere.com", "fakeCode");
        
        assertThat(response).isNotNull();
        assertThat(response.getException()).isNotNull().hasMessage("invalid_grant");
        assertThat(response.getAccessToken()).isNull();
    }

    @Test
    public void testGetUser() throws Exception {
        mockWebServer.enqueue(new MockResponse()
            .setStatus(HttpStatus.OK.toString())
            .setBody(getJsonFrom("responses/user.json")));
        
        Result<User> actualResult = foursquareApi.getUser("any");

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
    }

    @Test
    public void testGetUserFriends() throws Exception {
        mockWebServer.enqueue(new MockResponse()
            .setStatus(HttpStatus.OK.toString())
            .setBody(getJsonFrom("responses/friends.json")));

        Result<Group<User>> actualResult = foursquareApi.getUserFriends("any", null, null);
        
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
        assertThat(actualResult.getResponse().getCount()).isNotNull();
        assertThat(actualResult.getResponse().getItems()).isNotNull().isNotEmpty();
    }

    @Test
    public void testGetUserTips() throws Exception {
        mockWebServer.enqueue(new MockResponse()
            .setStatus(HttpStatus.OK.toString())
            .setBody(getJsonFrom("responses/tips.json")));

        Result<List> actualResult = foursquareApi.getUserTips("any", null, null, null, null, null);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
        assertThat(actualResult.getResponse().getListItems().getCount()).isNotNull();
        assertThat(actualResult.getResponse().getListItems().getItems()).isNotNull().isNotEmpty();
    }

    @Test
    public void testGetUserVenueLikes() throws Exception {
        mockWebServer.enqueue(new MockResponse()
            .setStatus(HttpStatus.OK.toString())
            .setBody(getJsonFrom("responses/venuelikes.json")));

        Result<Group<Venue>> actualResult = foursquareApi.getUserVenueLikes("any", null, null, null, null, null);
        
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
        assertThat(actualResult.getResponse().getCount()).isNotNull();
        assertThat(actualResult.getResponse().getItems()).isNotNull().isNotEmpty();
    }

    @Test
    public void testGetVenue() throws Exception {
        mockWebServer.enqueue(new MockResponse()
            .setStatus(HttpStatus.OK.toString())
            .setBody(getJsonFrom("responses/venue.json")));

        Result<Venue> actualResult = foursquareApi.getVenue("any");

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
    }

    @Test
    public void testGetVenueCategories() throws Exception {
        mockWebServer.enqueue(new MockResponse()
            .setStatus(HttpStatus.OK.toString())
            .setBody(getJsonFrom("responses/categories.json")));

        Result<Category[]> actualResult = foursquareApi.getVenueCategories();

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull().isNotEmpty();
    }

    @Test
    public void testSearchVenues() throws Exception {
        mockWebServer.enqueue(new MockResponse()
            .setStatus(HttpStatus.OK.toString())
            .setBody(getJsonFrom("responses/venues.json")));

        Result<Venue[]> actualResult = foursquareApi.searchVenues("19.44,-98.55", null, null, null, null, null, 5, null, 1000, null, null, null, null, null, null);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull().isNotEmpty();
    }

    @Test
    public void testExploreVenues() throws Exception {
        mockWebServer.enqueue(new MockResponse()
            .setStatus(HttpStatus.OK.toString())
            .setBody(getJsonFrom("responses/explore.json")));

        Result<ExploreVenueGroups> actualResult = foursquareApi.exploreVenues("19.026731,-98.234854", null, null, null, null, 1000, null, null, 5, null, null, null, null, null, null, null, null, null, null, null, null);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
        assertThat(actualResult.getResponse().getGroups()).isNotNull().isNotEmpty();

    }

    @Test 
    public void testGetNextVenues() throws Exception {
        mockWebServer.enqueue(new MockResponse()
            .setStatus(HttpStatus.OK.toString())
            .setBody(getJsonFrom("responses/nextvenues.json")));

        Result<Group<Venue>> actualResult = foursquareApi.getNextVenues("4b9d70a1f964a520e2ac36e3");
        
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
        assertThat(actualResult.getResponse().getCount()).isNotNull();
        assertThat(actualResult.getResponse().getItems()).isNotNull().isNotEmpty();
    }

    @Test 
    public void testError() throws Exception {
        mockWebServer.enqueue(new MockResponse()
            .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString())
            .setBody(getJsonFrom("responses/error.json")));

        Result<Group<Venue>> actualResult = foursquareApi.getNextVenues("whatever");
        
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(500);
        assertThat(actualResult.getMeta().getErrorType()).isNotNull().isEqualTo("generic_error");
        assertThat(actualResult.getMeta().getErrorDetail()).isNotNull().isEqualTo("Generic error");
        assertThat(actualResult.getResponse()).isNull();
    }

    private String getJsonFrom(String file) throws Exception {
        Path path = Paths.get(getClass().getClassLoader().getResource(file).toURI());
        StringBuilder json = new StringBuilder();
        for (String line : Files.readAllLines(path)) json.append(line).append('\n');
        return json.toString();
    }
}