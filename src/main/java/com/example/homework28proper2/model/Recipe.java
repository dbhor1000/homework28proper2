package com.example.homework28proper2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    private String name;
    private int cookingTime;
    List<Ingredient> ingredients = new ArrayList<>();
    List<String> cookingSteps = new ArrayList<>();

}
