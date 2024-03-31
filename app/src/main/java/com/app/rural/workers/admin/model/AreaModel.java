package com.app.rural.workers.admin.model;

public class AreaModel {
    int id,cityId;
    String nameEnglish,nameTamil,status;

    public AreaModel(int id, String nameEnglish, String nameTamil, String status) {
        this.id = id;
        this.nameEnglish = nameEnglish;
        this.nameTamil = nameTamil;
        this.status = status;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public String getNameTamil() {
        return nameTamil;
    }

    public void setNameTamil(String nameTamil) {
        this.nameTamil = nameTamil;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
