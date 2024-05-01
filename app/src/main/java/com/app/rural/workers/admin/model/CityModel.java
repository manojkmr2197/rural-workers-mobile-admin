package com.app.rural.workers.admin.model;

public class CityModel {

    int cityId;
    String cityNameEnglish,cityNameTamil,cityIsEnabled;

    public CityModel(int cityId, String cityNameEnglish, String cityNameTamil, String cityIsEnabled) {
        this.cityId = cityId;
        this.cityNameEnglish = cityNameEnglish;
        this.cityNameTamil = cityNameTamil;
        this.cityIsEnabled = cityIsEnabled;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityNameEnglish() {
        return cityNameEnglish;
    }

    public void setCityNameEnglish(String cityNameEnglish) {
        this.cityNameEnglish = cityNameEnglish;
    }

    public String getCityNameTamil() {
        return cityNameTamil;
    }

    public void setCityNameTamil(String cityNameTamil) {
        this.cityNameTamil = cityNameTamil;
    }

    public String getCityIsEnabled() {
        return cityIsEnabled;
    }

    public void setCityIsEnabled(String cityIsEnabled) {
        this.cityIsEnabled = cityIsEnabled;
    }
}
