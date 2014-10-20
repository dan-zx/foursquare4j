package com.foursquare4j.response;

public class List implements FoursquareResponse {

    public class Item implements FoursquareResponse {

        private String id;
        private User user;
        private Photo photo;
        // private Venue venue;
        private Tip tip;
        private String note;
        private Long createdAt;
        private java.util.List<List> listed;
    }

    private String id;
    private String name;
    private String description;
    private User user;
    private Boolean following;
    private Group<User> followers;
    private Boolean editable;
    private Boolean collaborative;
    private Group<User> collaborators;
    private String canonicalUrl;
    private Photo photo;
    private Integer venueCount;
    private Integer visitedCount;
    private Group<Item> listItems;
}
