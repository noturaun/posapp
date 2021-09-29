package com.noturaun.posapp.entity;

public class Enterprise {
    Integer id;
    String name;
    String pic;
    String registeredAddress;
    String registeredPhone;
    String businessAddress;
    String businessPhone;

    public Enterprise() {
    }

    public Enterprise(Integer id, String name, String pic, String registeredAddress, String registeredPhone, String businessAddress, String businessPhone) {
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.registeredAddress = registeredAddress;
        this.registeredPhone = registeredPhone;
        this.businessAddress = businessAddress;
        this.businessPhone = businessPhone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getRegisteredPhone() {
        return registeredPhone;
    }

    public void setRegisteredPhone(String registeredPhone) {
        this.registeredPhone = registeredPhone;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }
}
