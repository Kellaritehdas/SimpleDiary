package com.kellaritehdas.diaryproject;

import java.io.Serializable;

public class Diary implements Serializable {
    int id;
    String name;
    String story;
    String date;

    public Diary(String name, String story, String date) {
        this.name = name;
        this.story = story;
        this.date = date;
    }

    public Diary(int id, String name, String story, String date) {
        this.id = id;
        this.name = name;
        this.story = story;
        this.date = date;
    }

    public Integer getID() {
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

    public String getStory() {
        return story;
    }

    public void setStory(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return date + "  " + name;
    }
}
