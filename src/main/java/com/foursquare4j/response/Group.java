package com.foursquare4j.response;

import java.util.List;

public class Group<T> extends Count {

    private String type;
    private String name;
    private List<T> items;

}