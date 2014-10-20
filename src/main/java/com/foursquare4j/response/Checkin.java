package com.foursquare4j.response;

public class Checkin implements FoursquareResponse {

    private String id;
    private String type;
    private Integer timeZoneOffset;
    private Long createdAt;
    private Boolean isPrivate;
    private String shout;
    private User user;
    // private Venue venue;
    private Location location;
    private Source source;
    private Event event;
    private Group<Photo> photos;
    private Group<Comment> comments;
    private Groups<User> likes;
    private Group<Checkin> overlaps;
    private Scores scores;
}