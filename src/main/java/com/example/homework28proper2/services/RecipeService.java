package com.example.homework28proper2.services;

import com.example.homework28proper2.model.Recipe;

import java.io.IOException;
import java.nio.file.Path;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);
    Recipe getRecipe(long recipeNumber);
    Recipe editRecipe(long recipeNumber, Recipe recipe);
    boolean deleteRecipe(long recipeNumber);
    Path saveRecipesAsOrderedFile() throws IOException;
}