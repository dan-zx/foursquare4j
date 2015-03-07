/*
 * Copyright 2015 Daniel Pedraza-Arcega
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

public class UriBuilderTest {

    @Test
    public void testParseFullUrl() {
        UriBuilder uriBuilder = new UriBuilder("foo://username:password@example.com:8042/over/there/index.dtb?name=narwhal&type=animal#nose");

        assertThat(uriBuilder.getScheme()).isNotNull().isNotEmpty().isEqualTo("foo");
        assertThat(uriBuilder.getUserInfo()).isNotNull().isNotEmpty().isEqualTo("username:password");
        assertThat(uriBuilder.getHost()).isNotNull().isNotEmpty().isEqualTo("example.com");
        assertThat(uriBuilder.getPort()).isEqualTo(8042);
        assertThat(uriBuilder.getPath()).isNotNull().isNotEmpty().isEqualTo("/over/there/index.dtb");
        assertThat(uriBuilder.getQuery()).isNotNull().isNotEmpty().isEqualTo("name=narwhal&type=animal");
        assertThat(uriBuilder.getFragment()).isNotNull().isNotEmpty().isEqualTo("nose");
    }

    @Test
    public void testParseParcialUrl() {
        UriBuilder uriBuilder = new UriBuilder("http://www.example.com");

        assertThat(uriBuilder.getScheme()).isNotNull().isNotEmpty().isEqualTo("http");
        assertThat(uriBuilder.getUserInfo()).isNull();
        assertThat(uriBuilder.getHost()).isNotNull().isNotEmpty().isEqualTo("www.example.com");
        assertThat(uriBuilder.getPort()).isEqualTo(-1);
        assertThat(uriBuilder.getPath()).isNotNull().isEmpty();
        assertThat(uriBuilder.getQuery()).isNull();
        assertThat(uriBuilder.getFragment()).isNull();
    }

    @Test
    public void testBuildFullUrl() {
        UriBuilder uriBuilder = new UriBuilder()
            .scheme("foo")
            .userInfo("username:password")
            .host("example.com")
            .port(8042)
            .path("/over/there/index.dtb")
            .addParameter("name", "narwhal")
            .addParameter("type", "animal")
            .fragment("nose");
        
        assertThat(uriBuilder.getScheme()).isNotNull().isNotEmpty().isEqualTo("foo");
        assertThat(uriBuilder.getUserInfo()).isNotNull().isNotEmpty().isEqualTo("username:password");
        assertThat(uriBuilder.getHost()).isNotNull().isNotEmpty().isEqualTo("example.com");
        assertThat(uriBuilder.getPort()).isEqualTo(8042);
        assertThat(uriBuilder.getPath()).isNotNull().isNotEmpty().isEqualTo("/over/there/index.dtb");
        assertThat(uriBuilder.getQuery()).isNotNull().isNotEmpty().isEqualTo("name=narwhal&type=animal");
        assertThat(uriBuilder.getFragment()).isNotNull().isNotEmpty().isEqualTo("nose");
        assertThat(uriBuilder.toString()).isNotNull().isNotEmpty().isEqualTo("foo://username:password@example.com:8042/over/there/index.dtb?name=narwhal&type=animal#nose");
    }

    @Test
    public void testBuildParcialUrl() {
        UriBuilder uriBuilder = new UriBuilder()
            .scheme("http")
            .host("www.example.com");
        
        assertThat(uriBuilder.getScheme()).isNotNull().isNotEmpty().isEqualTo("http");
        assertThat(uriBuilder.getUserInfo()).isNull();
        assertThat(uriBuilder.getHost()).isNotNull().isNotEmpty().isEqualTo("www.example.com");
        assertThat(uriBuilder.getPort()).isEqualTo(-1);
        assertThat(uriBuilder.getPath()).isNotNull().isEmpty();
        assertThat(uriBuilder.getQuery()).isNull();
        assertThat(uriBuilder.getFragment()).isNull();
        assertThat(uriBuilder.toString()).isNotNull().isNotEmpty().isEqualTo("http://www.example.com");
    }
}