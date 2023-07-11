package com.veypo.meal_planner.pojo;

public class Recipe {
    /**
     * {
     *      "name": "Chocolate Cake",
     *      "content": {
     *         "description": "Best chocolate desert ever!"
     *      }
     *  }
     */
    private String name;
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
