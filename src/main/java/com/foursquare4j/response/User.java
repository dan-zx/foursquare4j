package com.foursquare4j.response;

public class User implements FoursquareResponse  {

    private String id;
    private String firstName;
    private String lastName;
    private Photo photo;
    private String relationship;
    private Groups<User> friends;
    private String type;
    // private Venue venue;
    private String homeCity;
    private String gender;
    private Contact contact;
    private String bio;
    private Groups<Tip> tips;
    private Group<List> lists;
    private Groups<User> followers;
    private Groups<User> following;
    //private Group<Badge> badges;
    // private Group<Venue> mayorships;
    private Group<Photo> photos;
    private Scores scores;
    private Group<Checkin> checkins;
    private Page pageInfo;
    private Boolean pings;
    private Count requests;

}