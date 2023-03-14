package com.example.homework28proper2.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {

    @Value("${path.to.dataIngredients.file}")
    private String dataFilesPath;
    @Value("${name.of.dataIngredients.file}")
    private String ingredientsFileName;
    @Value("${name.of.dataRecipes.file}")
    private String recipesFileName;

    @Override
    public boolean saveIngredientsToFile(String json) {

        try {
            Files.writeString(Path.of(dataFilesPath, ingredientsFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readIngredientsFromFile() {

        try {
            return Files.readString(Path.of(dataFilesPath, ingredientsFileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveRecipesToFile(String json) {

        try {
            Files.writeString(Path.of(dataFilesPath, recipesFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readRecipesFromFile() {

        try {
            return Files.readString(Path.of(dataFilesPath, recipesFileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
