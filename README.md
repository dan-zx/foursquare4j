Foursquare4J
============

Library created to perform requests to Foursquare API on Java.

### Usage:

```java
// Create a new FoursquareApi. 
FoursquareApi foursquareApi = new FoursquareApi("Client ID", "Client Secret");

// Optionally set the authentication access token if you already have one
// Remeber that some API Endpoints doesn't require an access token.
foursquareApi.setAccessToken("Access Token");

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

### Methods implemented:

* getAccessToken (in server app with native auth)
* getUser (any)
* getVenue (any)
* getCategories
* searchVenues
* exploreVenues