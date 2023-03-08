package com.example.homework28proper2.services;

import com.example.homework28proper2.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("RecipeService")
public class RecipeServiceImpl implements RecipeService {

    private static Map<Long, Recipe> recipesList = new HashMap<>();
    private static long recipeId = 0;

    @Override
    public Recipe addRecipe(Recipe recipe) {

        recipesList.putIfAbsent(recipeId++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(long recipeNumber) {

        return recipesList.get(recipeNumber);

    }

    @Override
    public Recipe editRecipe(long recipeNumber, Recipe recipe) {
        for(int i = 0; i < recipesList.size(); i++) {

            if (recipesList.containsKey(recipeNumber)) {

                recipesList.put(recipeNumber, recipe);
                return recipe;
            }
        }

        return null;
    }

    @Override
    public boolean deleteRecipe(long recipeNumber) {

        for (int i = 0; i < recipesList.size(); i++) {

            if (recipesList.containsKey(recipeNumber)) {

                recipesList.remove(recipeNumber);
                return true;
            }
        }

        return false;
    }


}