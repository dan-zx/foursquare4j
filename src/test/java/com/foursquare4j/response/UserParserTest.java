package com.foursquare4j.response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserParserTest {

    @Test
    public void testParseUserFromJson() throws Exception {
        String json = getJsonContentFrom("responses/user.json");
        System.out.println(json);
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        Result.Meta meta = new Gson().fromJson(obj.get("meta"), Result.Meta.class);
        User u = new Gson().fromJson(obj.get("response").getAsJsonObject().get("user"), User.class);
        System.out.println(u);
    }

    private String getJsonContentFrom(String file) throws Exception {
        Path path = Paths.get(UserParserTest.class.getClassLoader().getResource(file).toURI());
        StringBuilder sb = new StringBuilder();
        for (String line : Files.readAllLines(path)) sb.append(line).append('\n');
        return sb.toString();
    }
}