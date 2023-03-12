package com.example.homework28proper2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {

    private String name;
    private int amount;
    private String weight_units;

}

