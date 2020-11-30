package com.alpha.productscanner.dto;

public class Brand {
    String imagename;
    String name;
    String addres;
    float latitude;
    float longitude;
    String urlWebpage;
    String urlFacebook;
    String urlInstagram;
    String urltwitter;

    public  Brand (){}

    public Brand(String imagename, String name, String addres, float latitude, float longitude, String urlWebpage, String urlFacebook, String urlInstagram, String urltwitter) {
        this.imagename = imagename;
        this.name = name;
        this.addres = addres;
        this.latitude = latitude;
        this.longitude = longitude;
        this.urlWebpage = urlWebpage;
        this.urlFacebook = urlFacebook;
        this.urlInstagram = urlInstagram;
        this.urltwitter = urltwitter;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getUrlWebpage() {
        return urlWebpage;
    }

    public void setUrlWebpage(String urlWebpage) {
        this.urlWebpage = urlWebpage;
    }

    public String getUrlFacebook() {
        return urlFacebook;
    }

    public void setUrlFacebook(String urlFacebook) {
        this.urlFacebook = urlFacebook;
    }

    public String getUrlInstagram() {
        return urlInstagram;
    }

    public void setUrlInstagram(String urlInstagram) {
        this.urlInstagram = urlInstagram;
    }

    public String getUrltwitter() {
        return urltwitter;
    }

    public void setUrltwitter(String urltwitter) {
        this.urltwitter = urltwitter;
    }
}
