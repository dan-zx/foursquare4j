package com.foursquare4j.http;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class RequestBuilderTest {

    @Test
    public void testCallRequest() throws Exception {
        Integer statusCode = new RequestBuilder("http://www.google.com/")
            .setMethod(Method.GET)
            .setDefaultHeaders()
            .call();
        
        assertThat(statusCode).isNotNull().isEqualTo(200);
    }

    @Test
    public void testCallForResultRequest() throws Exception {
        String result = new RequestBuilder("http://www.google.com/")
            .setMethod(Method.GET)
            .setDefaultHeaders()
            .callForResult();
        
        assertThat(result).isNotNull().isNotEmpty();
    }
}