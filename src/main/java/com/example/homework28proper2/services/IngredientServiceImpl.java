package com.example.homework28proper2.services;

import com.example.homework28proper2.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static Map<Long, Ingredient> ingredientsMap = new HashMap<>();
    private static long ingredientId = 0;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {

        ingredientsMap.putIfAbsent(ingredientId++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(long ingredientNumber) {

        return ingredientsMap.get(ingredientNumber);

    }

    @Override
    public Ingredient editIngredient(long ingredientNumber, Ingredient ingredient) {

        if (ingredientsMap.containsKey(ingredientNumber)) {

                ingredientsMap.put(ingredientNumber, ingredient);
                return ingredient;
            }


        return null;
    }

    @Override
    public boolean deleteIngredient(long ingredientNumber) {

        if (ingredientsMap.containsKey(ingredientNumber)) {

                ingredientsMap.remove(ingredientNumber);
                return true;
            }

        return false;
    }


}