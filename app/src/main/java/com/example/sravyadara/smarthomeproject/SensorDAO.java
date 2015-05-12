package com.example.sravyadara.smarthomeproject;

/**
 * Created by sravyadara on 4/28/15.
 */
public class SensorDAO {

    private String coordinates;
    private String zipCode;
    private String networkName;
    private String Address;

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }


    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

}
