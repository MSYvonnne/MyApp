package com.example.myapp;

public class RateItem {

    //创建实体对象
    private int id;
    private String curName;
    private String curRate;

    public RateItem() {
        this.curName = "";
        this.curRate = "";
    }

    public RateItem(String curName, String curRate) {
        this.curName = curName;
        this.curRate = curRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public String getCurRate() {
        return curRate;
    }

    public void setCurRate(String curRate) {
        this.curRate = curRate;
    }

}
