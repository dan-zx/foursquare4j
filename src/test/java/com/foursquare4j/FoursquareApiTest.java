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

import java.io.InputStream;
import java.util.Properties;

import com.foursquare4j.response.Category;
import com.foursquare4j.response.ExploreVenueGroups;
import com.foursquare4j.response.Group;
import com.foursquare4j.response.List;
import com.foursquare4j.response.Result;
import com.foursquare4j.response.User;
import com.foursquare4j.response.Venue;

import org.junit.BeforeClass;
import org.junit.Test;

public class FoursquareApiTest {

    private static FoursquareApi foursquareApi;
    private static Properties configs;

    @BeforeClass
    public static void setUpClass() throws Exception {
        configs = loadConfigProperties();
        foursquareApi = new FoursquareApi(configs.getProperty("foursquare.app.client_id"), configs.getProperty("foursquare.app.client_secret"));
    }
    
    @Test
    public void testGetUser() throws Exception {
        Result<User> actualResult = foursquareApi.getUser("88260846");

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
    }

    @Test
    public void testGetUserFriends() throws Exception {
        foursquareApi.setAccessToken(configs.getProperty("foursquare.app.access_token"));
        Result<Group<User>> actualResult = foursquareApi.getUserFriends("self", null, null);
        foursquareApi.setAccessToken(null);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
        assertThat(actualResult.getResponse().getCount()).isNotNull();
        assertThat(actualResult.getResponse().getItems()).isNotNull().isNotEmpty();
    }

    @Test
    public void testGetUserTips() throws Exception {
        foursquareApi.setAccessToken(configs.getProperty("foursquare.app.access_token"));
        Result<List> actualResult = foursquareApi.getUserTips("self", null, null, null, null, null);
        foursquareApi.setAccessToken(null);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
        assertThat(actualResult.getResponse().getListItems().getCount()).isNotNull();
        assertThat(actualResult.getResponse().getListItems().getItems()).isNotNull().isNotEmpty();
    }

    @Test
    public void testGetUserVenueLikes() throws Exception {
        foursquareApi.setAccessToken(configs.getProperty("foursquare.app.access_token"));
        Result<Group<Venue>> actualResult = foursquareApi.getUserVenueLikes("57562206", null, null, null, null, null);
        foursquareApi.setAccessToken(null);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
        assertThat(actualResult.getResponse().getCount()).isNotNull();
        assertThat(actualResult.getResponse().getItems()).isNotNull().isNotEmpty();
    }

    @Test
    public void testGetVenue() throws Exception {
        Result<Venue> actualResult = foursquareApi.getVenue("40a55d80f964a52020f31ee3");

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
    }

    @Test
    public void testGetVenueCategories() throws Exception {
        Result<Category[]> actualResult = foursquareApi.getVenueCategories();

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull().isNotEmpty();
    }

    @Test
    public void testSearchVenues() throws Exception {
        Result<Venue[]> actualResult = foursquareApi.searchVenues("19.026731,-98.234854", null, null, null, null, null, 5, null, 1000, null, null, null, null, null, null);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull().isNotEmpty();
    }

    @Test
    public void testExploreVenues() throws Exception {
        Result<ExploreVenueGroups> actualResult = foursquareApi.exploreVenues("19.026731,-98.234854", null, null, null, null, 1000, null, null, 5, null, null, null, null, null, null, null, null, null, null, null, null);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
        assertThat(actualResult.getResponse().getGroups()).isNotNull().isNotEmpty();

    }

    @Test 
    public void testGetNextVenues() throws Exception {
        Result<Group<Venue>> actualResult = foursquareApi.getNextVenues("4b9d70a1f964a520e2ac36e3");

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
        assertThat(actualResult.getResponse().getCount()).isNotNull();
        assertThat(actualResult.getResponse().getItems()).isNotNull().isNotEmpty();
    }

    private static Properties loadConfigProperties() throws Exception {
        try (InputStream stream = FoursquareApiTest.class.getResourceAsStream("/fsq-configs.properties")) {
            Properties configs = new Properties();
            configs.load(stream);
            return configs;
        }
    }
}