package com.foursquare4j.response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class Parser {

    private Parser() {
        throw new IllegalAccessError("This class cannot be instantiated or extended");
    }

    public static <T> Result<T> parse(String json, String responseType, Class<T> responseClass) {
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        Result.Meta meta = new Gson().fromJson(obj.get("meta"), Result.Meta.class);
        T response = new Gson().fromJson(obj.get("response").getAsJsonObject().get(responseType), responseClass);
        Result<T> result = new Result<>();
        result.setMeta(meta);
        result.setResponse(response);
        return result;
    }
}
