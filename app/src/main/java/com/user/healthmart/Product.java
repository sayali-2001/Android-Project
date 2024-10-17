package com.user.healthmart;

import android.net.Uri;

public class Product {
    private String name;
    private int quantity;
//    private String details;
    private String price;
    private Uri imageUri;


    public Product(String name, String price, Uri imageUri) {
        this.name = name;

        this.price = price;
        this.imageUri = imageUri;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageResourceId(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public void setQuantity(int i) {
    }

//    public String getDetails() {
//        return details;
//    }

//    public void setDetails(String details) {
//        this.details = details;
//    }
}
