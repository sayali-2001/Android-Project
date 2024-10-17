package com.user.healthmart;

public class ReccomendedProduct {

    private String name;
    private String company;
    private String location;
    private String price;
    private int imageResId;

    public ReccomendedProduct(String name, String company, String location, String price, int imageResId) {
        this.name = name;
        this.company = company;
        this.location = location;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    @Override
    public String toString() {
        return "ReccomendedProduct{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", price='" + price + '\'' +
                ", imageResId=" + imageResId +
                '}';
    }
}
