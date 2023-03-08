package com.example.homework28proper2.services;

import com.example.homework28proper2.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static Map<Long, Ingredient> ingredientsList = new HashMap<>();
    private static long ingredientId = 0;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {

        ingredientsList.putIfAbsent(ingredientId++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(long ingredientNumber) {

        return ingredientsList.get(ingredientNumber);

    }

    @Override
    public Ingredient editIngredient(long ingredientNumber, Ingredient ingredient) {
        for(int i = 0; i < ingredientsList.size(); i++) {

            if (ingredientsList.containsKey(ingredientNumber)) {

                ingredientsList.put(ingredientNumber, ingredient);
                return ingredient;
            }
        }

        return null;
    }

    @Override
    public boolean deleteIngredient(long ingredientNumber) {

        for (int i = 0; i < ingredientsList.size(); i++) {

            if (ingredientsList.containsKey(ingredientNumber)) {

                ingredientsList.remove(ingredientNumber);
                return true;
            }
        }
        return false;
    }


}