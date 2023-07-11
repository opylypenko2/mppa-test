package com.veypo.meal_planner.pojo;

public class Content {

    private String description;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Content{" +
                "description='" + description + '\'' +
                '}';
    }
}
