package com.foursquare4j.response;

public class Photo implements FoursquareResponse {

    private String id;
    private Long createdAt;
    private String prefix;
    private String suffix;
    private String visibility;
    private Source source;
    private User user;
    // private Venue venue;
    private Tip tip;
    private Checkin checkin;
}