package com.example.apidatav2;

public class Example {
    private String cpID;
    private String Area;
    private String develop;
    private String location;
    private int lotAvail;
    private String lotType;
    private String agency;

    public Example(String cpId, String area, String Develop, String Location, int LotAvail, String LotType, String Agency) {
        cpID = cpId;
        Area = area;
        develop = Develop;
        location = Location;
        lotAvail = LotAvail;
        lotType = LotType;
        agency = Agency;
    }



    public String getCpId() {
        return cpID;
    }

    public String getArea() {
        return Area;
    }

    public String getDevelop() {
        return develop;
    }

    public String getLocation() {
        return location;
    }

    public int getLotAvail() {
        return lotAvail;
    }

    public String getLotType() {
        return lotType;
    }

    public String getAgency() {
        return agency;
    }


}
