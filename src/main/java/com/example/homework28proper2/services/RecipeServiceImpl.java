package com.example.homework28proper2.services;

import com.example.homework28proper2.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("RecipeService")
public class RecipeServiceImpl implements RecipeService {

    private static Map<Long, Recipe> recipesMap = new HashMap<>();
    private static long recipeId = 0;

    @Override
    public Recipe addRecipe(Recipe recipe) {

        recipesMap.putIfAbsent(recipeId++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(long recipeNumber) {

        return recipesMap.get(recipeNumber);

    }

    @Override
    public Recipe editRecipe(long recipeNumber, Recipe recipe) {

            if (recipesMap.containsKey(recipeNumber)) {

                recipesMap.put(recipeNumber, recipe);
                return recipe;
            }

        return null;
    }

    @Override
    public boolean deleteRecipe(long recipeNumber) {

            if (recipesMap.containsKey(recipeNumber)) {

                recipesMap.remove(recipeNumber);
                return true;
            }

        return false;
    }


}