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

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.foursquare4j.response.Category;
import com.foursquare4j.response.ExploreVenueGroups;
import com.foursquare4j.response.Result;
import com.foursquare4j.response.User;
import com.foursquare4j.response.Venue;

import org.junit.Before;
import org.junit.Test;

public class FoursquareApiTest {

    private FoursquareApi foursquareApi;

    @Before
    public void setUp() throws Exception {
        Properties configs = loadConfigProperties();
        foursquareApi = new FoursquareApi(configs.getProperty("foursquare.app.client_id"), configs.getProperty("foursquare.app.client_secret"));
        foursquareApi.setAccessToken(configs.getProperty("foursquare.app.access_token"));
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
    public void testGetVenue() throws Exception {
        Result<Venue> actualResult = foursquareApi.getVenue("40a55d80f964a52020f31ee3");

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
    }

    @Test
    public void testGetCategories() throws Exception {
        Result<Category[]> actualResult = foursquareApi.getCategories();

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
    public void testExploreVenues()throws Exception {
        Result<ExploreVenueGroups> actualResult = foursquareApi.exploreVenues("19.026731,-98.234854", null, null, null, null, 1000, null, null, 5, null, null, null, null, null, null, null, null, null, null, null, null);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
        assertThat(actualResult.getResponse().getGroups()).isNotNull().isNotEmpty();

    }

    private Properties loadConfigProperties() {
        Properties configs = new Properties();
        InputStream stream = null;
        try {
            stream = this.getClass().getResourceAsStream("/fsq-configs.properties");
            configs.load(stream);
            return configs;
        } catch (IOException ex) { 
            return null;
        } finally {
            try {
                stream.close();
            } catch (IOException ex) { }
        }
    }
}