package com.example.user.eduapp.Search;

public class DataProvider {
    private String courseName;
    private String L1R5;
    private String L1R4;
    private String Rating;
    private String Location;

    public String getCourseName() {
        return courseName;
    }

    public String getL1R5() {
        return L1R5;
    }

    public String getL1R4() {
        return L1R4;
    }

    public String getLocation() {
        return Location;
    }

    public String getRating() {
        return Rating;
    }

    public DataProvider(String courseName, String L1R5, String L1R4, String Location,String Rating){
        this.courseName = courseName;
        this.L1R5 = L1R5;
        this.L1R4 = L1R4;
        this.Location = Location;
        this.Rating = Rating;
    }
}
