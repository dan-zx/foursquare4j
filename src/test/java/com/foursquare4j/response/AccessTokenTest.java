/*
 * Copyright 2014-2015 Daniel Pedraza-Arcega
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

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class AccessTokenTest {

    @Test
    public void testIsAccessTokenResponse() throws Exception {
        AccessTokenResponse response = new AccessTokenResponse(getJsonFrom("responses/access-token.json"));
        
        assertThat(response).isNotNull();
        assertThat(response.getException()).isNull();
        assertThat(response.getAccessToken()).isNotNull().isNotEmpty().isEqualTo("JHKSDFKHJEFAKE23094LKJSDLJK2394KJLS");
    }

    @Test
    public void testIsExceptionResponse() throws Exception {
        AccessTokenResponse response = new AccessTokenResponse(getJsonFrom("responses/error-access-token.json"));
        
        assertThat(response).isNotNull();
        assertThat(response.getException()).isNotNull().hasMessage("invalid_grant");
        assertThat(response.getAccessToken()).isNull();
    }
    
    private String getJsonFrom(String file) throws Exception {
        Path path = Paths.get(this.getClass().getClassLoader().getResource(file).toURI());
        StringBuilder sb = new StringBuilder();
        for (String line : Files.readAllLines(path))
            sb.append(line).append('\n');
        return sb.toString();
    }
}