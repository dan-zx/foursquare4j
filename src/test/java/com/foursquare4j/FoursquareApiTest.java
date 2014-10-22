package com.foursquare4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.foursquare4j.response.Category;
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
        assertThat(actualResult.getResponse()).isNotNull().isNotEmpty().hasSize(5);
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