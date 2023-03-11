package com.example.homework28proper2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {

    private String name;
    private int amount;
    private String weight_units;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getWeight_units() {
        return weight_units;
    }

    public void setWeight_units(String weight_units) {
        this.weight_units = weight_units;
    }
}

