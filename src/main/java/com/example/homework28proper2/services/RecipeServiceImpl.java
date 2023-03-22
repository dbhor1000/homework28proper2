package com.example.homework28proper2.services;

import com.example.homework28proper2.model.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.TreeMap;

@Component("RecipeService")
public class RecipeServiceImpl implements RecipeService {

    private static Map<Long, Recipe> recipesMap = new TreeMap<>();
    private static long recipeId = 0;
    final private FilesService filesService;

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {

        recipesMap.putIfAbsent(recipeId++, recipe);
        saveRecipesToFile();
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
                saveRecipesToFile();
                return recipe;
            }

        return null;
    }

    @Override
    public boolean deleteRecipe(long recipeNumber) {

            if (recipesMap.containsKey(recipeNumber)) {

                recipesMap.remove(recipeNumber);
                saveRecipesToFile();
                return true;
            }

        return false;
    }

    @PostConstruct
    private void init() {
        readRecipesFromFile();
    }

    private void saveRecipesToFile() {

        try {
            String json = new ObjectMapper().writeValueAsString(recipesMap);
            filesService.saveRecipesToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void readRecipesFromFile() {

        try {
            String json = filesService.readRecipesFromFile();
            recipesMap = new ObjectMapper().readValue(json, new TypeReference<Map<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    //Домашняя работа от 21.03

    @Override
    public Path saveRecipesAsOrderedFile() throws IOException {

        Path path = filesService.addTempFile("recipesReadable");
        for(Recipe recipe : recipesMap.values()){
            try(Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){
                writer.append("Dish name: " + recipe.getName() + "\nDish cooking time: " + recipe.getCookingTime()  + " минут" + "\nDish ingredients " + recipe.getIngredients()  + "\nCooking steps: " + recipe.getCookingSteps());
                writer.append("\n");
            }
        }
        return path;
    }
}