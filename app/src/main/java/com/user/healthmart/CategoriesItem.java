package com.user.healthmart;

public class CategoriesItem {
    private String name;
    private int imageResourceId;

    public CategoriesItem(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
