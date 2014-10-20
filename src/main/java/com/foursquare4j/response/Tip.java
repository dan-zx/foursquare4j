package com.foursquare4j.response;

public class Tip implements FoursquareResponse {

    private String id;
    private String text;
    private Long createdAt;
    private String status;
    private Photo photo;
    private User user;
    // private Venue venue;
    private Groups<User> todo;
    private Groups<User> likes;
}