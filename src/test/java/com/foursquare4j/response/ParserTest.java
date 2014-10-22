package com.foursquare4j.response;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ParserTest {

    @Test
    public void testParseError() throws Exception {
        String json = getJsonFrom("responses/error.json");
        Result.Meta expectedMeta = new Result.Meta();
        expectedMeta.setCode(410);
        expectedMeta.setErrorType("param_error");
        expectedMeta.setErrorDetail("The Foursquare API no longer supports requests that do not pass in a version parameter. For more details see https://developer.foursquare.com/overview/versioning");
        Result<User> expectedResult = new Result<>();
        expectedResult.setMeta(expectedMeta);
        Result<User> actualResult = Parser.parse(json, "user", User.class);

        assertThat(actualResult).isNotNull().isEqualTo(expectedResult);
    }

    @Test
    public void testParseUser() throws Exception {
        String json = getJsonFrom("responses/user.json");
        Result<User> actualResult = Parser.parse(json, "user", User.class);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
    }
    
    @Test
    public void testParseVenue() throws Exception {
        String json = getJsonFrom("responses/venue.json");
        Result<Venue> actualResult = Parser.parse(json, "venue", Venue.class);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getMeta()).isNotNull();
        assertThat(actualResult.getMeta().getCode()).isNotNull().isEqualTo(200);
        assertThat(actualResult.getResponse()).isNotNull();
    }

    private String getJsonFrom(String file) throws Exception {
        Path path = Paths.get(this.getClass().getClassLoader().getResource(file).toURI());
        StringBuilder sb = new StringBuilder();
        for (String line : Files.readAllLines(path)) sb.append(line).append('\n');
        return sb.toString();
    }
}
