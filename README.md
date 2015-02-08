Foursquare4J
============

Library created to perform requests to Foursquare API on Java.

### Maven dependency

```xml
<repositories>
    <repository>
      <id>dan_zx-repo</id>
      <name>Dan ZX Repository</name>
      <url>https://dl.dropboxusercontent.com/u/1995295/m2repository</url>
    </repository>
</repositories>

<dependencies>
  <dependency>
      <groupId>com.foursquare4j</groupId>
      <artifactId>foursquare4j</artifactId>
      <version>${version}</version>
    </dependency>
</dependencies>
```

### Gradle dependency


```groovy
repositories {
    maven {
        url 'https://dl.dropboxusercontent.com/u/1995295/m2repository'
    }
}

dependencies {
    compile 'com.foursquare4j:foursquare4j:${version}'
}
```

### Usage

```java
// Create a new FoursquareApi. 
FoursquareApi foursquareApi = new FoursquareApi("Client ID", "Client Secret");

// Optionally set the authentication access token if you already have one
// Remeber that some API Endpoints doesn't require an access token.
foursquareApi.setAccessToken("Access Token");

// Optionally, set the locale for internationalized responses
// By default, the responses are in English
foursquareApi.setLocale(new Locale.Builder().setLanguage("es").build());

// We can make request now.
Result<User> result = foursquareApi.getUser("self");

if (result.getMeta().getCode() == 200) {
  // if query was ok we can finally we do something with the data
  // TODO: Do something with the data
  System.out.println(result.getResponse());
} else {
  // TODO: Proper error handling
  System.out.println("Error occured: ");
  System.out.println("  code: " + result.getMeta().getCode());
  System.out.println("  type: " + result.getMeta().getErrorType());
  System.out.println("  detail: " + result.getMeta().getErrorDetail()); 
}
```

### Methods implemented

* getAccessToken (in server app with native auth)
* getUser
* getUserFriends
* getUserTips
* getUserVenueLikes
* getVenue
* getVenueCategories
* searchVenues
* exploreVenues
* getNextVenues


### License

    Copyright 2014 Daniel Pedraza-Arcega

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
