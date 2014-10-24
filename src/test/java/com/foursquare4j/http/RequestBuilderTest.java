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