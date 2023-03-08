package com.example.homework28proper2.model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private String name;
    private int cookingTime;
    List<Ingredient> ingredients = new ArrayList<>();
    List<String> cookingSteps = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }
}
