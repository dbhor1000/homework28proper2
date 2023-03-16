package com.example.homework28proper2.services;

import java.io.File;

public interface FilesService {

    boolean saveIngredientsToFile(String json);
    String readIngredientsFromFile();
    boolean saveRecipesToFile(String json);
    String readRecipesFromFile();
    File getDataFileIngredients();
    File getDataFileRecipes();

    boolean cleanDataFileIngredients();

    boolean cleanDataFileRecipes();
}
