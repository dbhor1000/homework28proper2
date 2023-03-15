package com.example.homework28proper2.services;

import com.example.homework28proper2.model.Ingredient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {

    final private FilesService filesService;

    public IngredientServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    private static Map<Long, Ingredient> ingredientsMap = new TreeMap<>();
    private static long ingredientId = 0;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {

        ingredientsMap.putIfAbsent(ingredientId++, ingredient);
        saveIngredientsToFile();
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
                saveIngredientsToFile();
                return ingredient;
            }


        return null;
    }

    @Override
    public boolean deleteIngredient(long ingredientNumber) {

        if (ingredientsMap.containsKey(ingredientNumber)) {

                ingredientsMap.remove(ingredientNumber);
                saveIngredientsToFile();
                return true;
            }

        return false;
    }

    @PostConstruct
    private void init() {
        readIngredientsFromFile();
    }

    private void saveIngredientsToFile() {

        try {
            String json = new ObjectMapper().writeValueAsString(ingredientsMap);
            filesService.saveIngredientsToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void readIngredientsFromFile() {

        try {
            String json = filesService.readIngredientsFromFile();
            ingredientsMap = new ObjectMapper().readValue(json, new TypeReference<Map<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}