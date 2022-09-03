package com.example.touring.Models;

public class StateModel {
    private String id;
    private String name;
    private String urlImage;

    public StateModel(String id, String name, String urlImage) {
        this.id = id;
        this.name = name;
        this.urlImage = urlImage;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
