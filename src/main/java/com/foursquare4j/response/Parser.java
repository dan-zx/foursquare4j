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

public final class Parser {

    private Parser() {
        throw new IllegalAccessError("This class cannot be instantiated or extended");
    }

    public static <T> Result<T> parse(String json, Class<T> responseClass) {
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        Result.Meta meta = new Gson().fromJson(obj.get("meta"), Result.Meta.class);
        T response = new Gson().fromJson(obj.get("response"), responseClass);
        Result<T> result = new Result<>();
        result.setMeta(meta);
        result.setResponse(response);
        return result;
    }

    public static <T> Result<T> parse(String json, TypeToken<T> type) {
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        Result.Meta meta = new Gson().fromJson(obj.get("meta"), Result.Meta.class);
        T response = new Gson().fromJson(obj.get("response"), type.getType());
        Result<T> result = new Result<>();
        result.setMeta(meta);
        result.setResponse(response);
        return result;
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