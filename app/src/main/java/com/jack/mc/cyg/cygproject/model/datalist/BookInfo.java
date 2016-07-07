package com.jack.mc.cyg.cygproject.model.datalist;

/**
 * Created by Jack on 16/6/16.
 */
public class BookInfo {

    private int id;
    private String name;
    private String picSmall;
    private String picBig;
    private String description;
    private String learner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getPicBig() {
        return picBig;
    }

    public void setPicBig(String picBig) {
        this.picBig = picBig;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLearner() {
        return learner;
    }

    public void setLearner(String learner) {
        this.learner = learner;
    }
}