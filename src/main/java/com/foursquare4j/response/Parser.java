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
package com.foursquare4j.response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * Class that converts JSON response into response objects.
 * 
 * @author Daniel Pedraza-Arcega.
 * @since 1.0
 */
public final class Parser {

    private Parser() {
        throw new IllegalAccessError("This class cannot be instantiated or extended");
    }

    /**
     * Converts the given JSON into an object of the given class.
     * 
     * @param json a JSON response.
     * @param responseClass the class of the object to be returned.
     * @return a Result object with the converted JSON.
     */
    public static <T> Result<T> parse(String json, Class<T> responseClass) {
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        Result.Meta meta = new Gson().fromJson(obj.get("meta"), Result.Meta.class);
        T response = new Gson().fromJson(obj.get("response"), responseClass);
        Result<T> result = new Result<>();
        result.setMeta(meta);
        result.setResponse(response);
        return result;
    }

    /**
     * Converts the given JSON into an object of the given class.
     * 
     * @param json a JSON response.
     * @param type the type of the generic object to be returned.
     * @return a Result object with the converted JSON.
     */
    public static <T> Result<T> parse(String json, TypeToken<T> type) {
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        Result.Meta meta = new Gson().fromJson(obj.get("meta"), Result.Meta.class);
        T response = new Gson().fromJson(obj.get("response"), type.getType());
        Result<T> result = new Result<>();
        result.setMeta(meta);
        result.setResponse(response);
        return result;
    }

    /**
     * Converts the given JSON into an object of the given class.
     * 
     * @param json a JSON response.
     * @param responseType the object to be parsed in the JSON response.
     * @param responseClass the class of the object to be returned.
     * @return a Result object with the converted JSON.
     */
    public static <T> Result<T> parse(String json, String responseType, Class<T> responseClass) {
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        Result.Meta meta = new Gson().fromJson(obj.get("meta"), Result.Meta.class);
        T response = new Gson().fromJson(obj.get("response").getAsJsonObject().get(responseType), responseClass);
        Result<T> result = new Result<>();
        result.setMeta(meta);
        result.setResponse(response);
        return result;
    }

    /**
     * Converts the given JSON into an object of the given class.
     * 
     * @param json a JSON response.
     * @param responseType the object to be parsed in the JSON response.
     * @param type the type of the generic object to be returned.
     * @return a Result object with the converted JSON.
     */
    public static <T> Result<T> parse(String json, String responseType, TypeToken<T> type) {
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        Result.Meta meta = new Gson().fromJson(obj.get("meta"), Result.Meta.class);
        T response = new Gson().fromJson(obj.get("response").getAsJsonObject().get(responseType), type.getType());
        Result<T> result = new Result<>();
        result.setMeta(meta);
        result.setResponse(response);
        return result;
    }
}