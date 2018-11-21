package com.batchu.duoihinhbatchu.model;

public class Quest {


    private int index;
    private int resImage;
    private String result;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getResImage() {
        return resImage;
    }

    public void setResImage(int resImage) {
        this.resImage = resImage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }



    public Quest(int resImage, String result) {
        this.resImage = resImage;
        this.result = result;
    }

    public Quest() {

    }

    public Quest( int index, int  resImage, String result) {
        this.resImage = resImage;
        this.index = index;
        this.result = result;
    }
}
