package com.example.touring.Models;

import java.util.HashMap;

public class Region {
  private String id;
    private String name;
    private String image;
    private String content;
    private String type;

    public static Region toRegion(HashMap hashMap) {
        return new Region(
                hashMap.get("id").toString(),
                hashMap.get("name").toString(),
                hashMap.get("image").toString(),
                hashMap.get("content").toString(),
                hashMap.get("type").toString()
                );
    }

    public HashMap<String,String> toJson(){
        HashMap hashMap = new HashMap();
        hashMap.put("id" ,this.getId());
        hashMap.put("name" ,this.getName());
        hashMap.put("image" ,this.getImage());
        hashMap.put("content" ,this.getContent());
        hashMap.put("type" ,this.getType());

        return hashMap;
    }

    public Region(
            String id, String name, String image, String content,  String type) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.content = content;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }
}
