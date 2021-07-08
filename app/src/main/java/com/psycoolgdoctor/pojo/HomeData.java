package com.psycoolgdoctor.pojo;

public class HomeData {
    private int position;
    private String name, value;
    private int drawerImage;

    public HomeData(int position, String name, int drawerImage, String value) {
        this.position = position;
        this.name = name;
        this.drawerImage = drawerImage;
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawerImage() {
        return drawerImage;
    }

    public void setDrawerImage(int drawerImage) {
        this.drawerImage = drawerImage;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
