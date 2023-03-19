package com.example.homework28proper2.services;

import org.springframework.web.multipart.MultipartFile;

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

    boolean uploadIngredients(MultipartFile file);

    boolean uploadRecipes(MultipartFile file);
}
