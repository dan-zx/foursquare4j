package com.foursquare4j.response;

public class Page implements FoursquareResponse {

    public static class Link implements FoursquareResponse {

        private String title;
        private String url;
    }

    private String title;
    private String description;
    private String banner;
    private Group<Link> links;
}