package com.foursquare4j;

import static org.assertj.core.api.Assertions.assertThat;

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
        foursquareApi = new FoursquareApi("B4BRRJR2DUYMKFHJEYFUF24QXWSJKA4XGMHOELJEQOW2XTAG", "ZNVGEZGTIGNDGTDQSD3ZYAKZWS0HTJNE3DMFIMOPS4WWEXVX");
        //foursquareApi.setAccessToken("AZLJIU2XC1QANMQH45WQSPW132JNOSUAPLIIRJSCEUN4YEYY");
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
}