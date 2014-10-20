package com.foursquare4j.response;

import java.util.List;

public class Badge implements FoursquareResponse {

    public static class Image implements FoursquareResponse {

        private String prefix;
        private List<Integer> sizes;
        private String name;
    }

    public static class Unlocks implements FoursquareResponse {

        private List<Checkin> checkins;
    }
    
    private String id;
    private String badgeId;
    private String name;
    private String description;
    private Image image;
    private Unlocks unlocks;

}