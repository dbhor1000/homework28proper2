package com.example.homework28proper2.controllers;

import com.example.homework28proper2.model.Recipe;
import com.example.homework28proper2.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {

        Recipe createdRecipe = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(createdRecipe);

    }

    @GetMapping("/{recipeNumber}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable long recipeNumber){

        Recipe recipe = recipeService.getRecipe(recipeNumber);

        if (recipe == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/edit/{ingredientNumber}")
    public ResponseEntity<Recipe> editIngredient(@PathVariable long recipeNumber, @RequestBody Recipe recipe) {

        Recipe editedRecipe = recipeService.editRecipe(recipeNumber, recipe);

        if (recipe == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/delete/{ingredientNumber}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable long recipeNumber) {

        if (recipeService.deleteRecipe(recipeNumber)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }




}