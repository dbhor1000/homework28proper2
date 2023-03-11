package com.example.homework28proper2.controllers;

import com.example.homework28proper2.model.Recipe;
import com.example.homework28proper2.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Контроллер для запросов, относящихся к сервису рецептов
@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping    //Эндпоинт для добавления рецепта
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        Recipe createdRecipe = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(createdRecipe);
    }

    @GetMapping("/{recipeNumber}")  //Эндпоинт для получения рецепта по номеру
    public ResponseEntity<Recipe> getRecipe(@PathVariable long recipeNumber){
        Recipe recipe = recipeService.getRecipe(recipeNumber);
        if (recipe == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/edit/{ingredientNumber}") //Эндпоинт для моидфикации рецепта по номеру
    public ResponseEntity<Recipe> editIngredient(@PathVariable long recipeNumber, @RequestBody Recipe recipe) {
        Recipe editedRecipe = recipeService.editRecipe(recipeNumber, recipe);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/delete/{ingredientNumber}")    //Эндпоинт для удаления рецепта по номеру
    public ResponseEntity<Void> deleteRecipe(@PathVariable long recipeNumber) {
        if (recipeService.deleteRecipe(recipeNumber)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}