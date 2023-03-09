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

    public int getAmountNumber() {
        return amount;
    }

    public void setAmountNumber(int amount) {
        this.amount = amount;
    }

    public String getMeasureUnit() {
        return weight_units;
    }

    public void setMeasureUnit(String weight_units) {
        this.weight_units = weight_units;
    }
}

