package com.example.homework28proper2.services;

public interface FilesService {

    boolean saveIngredientsToFile(String json);
    String readIngredientsFromFile();
    boolean saveRecipesToFile(String json);
    String readRecipesFromFile();
}
