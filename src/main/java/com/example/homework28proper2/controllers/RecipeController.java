package com.example.homework28proper2.controllers;

import com.example.homework28proper2.model.Recipe;
import com.example.homework28proper2.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Контроллер для запросов, относящихся к сервису рецептов
@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "Контроллер для запросов к сервису рецептов")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping    //Эндпоинт для добавления рецепта
    @Operation(
            summary = "Добавление рецепта",
            description = "Добавление рецепта в мап"
    )
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        Recipe createdRecipe = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(createdRecipe);
    }

    @GetMapping("/{recipeNumber}")  //Эндпоинт для получения рецепта по номеру
    @Operation(
            summary = "Получение рецепта",
            description = "Получение рецепта под номером, указанным в адресной строке"
    )
    public ResponseEntity<Recipe> getRecipe(@PathVariable long recipeNumber){
        Recipe recipe = recipeService.getRecipe(recipeNumber);
        if (ObjectUtils.isEmpty(recipe)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/edit/{ingredientNumber}") //Эндпоинт для моидфикации рецепта по номеру
    @Operation(
            summary = "Модификация рецепта",
            description = "Модификация рецепта под номером, указанным в адресной строке"
    )
    public ResponseEntity<Recipe> editIngredient(@PathVariable long recipeNumber, @RequestBody Recipe recipe) {
        Recipe editedRecipe = recipeService.editRecipe(recipeNumber, recipe);
        if (ObjectUtils.isEmpty(recipe)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/delete/{ingredientNumber}")    //Эндпоинт для удаления рецепта по номеру
    @Operation(
            summary = "Удаление",
            description = "Удаление под номером, указанным в адресной строке"
    )
    public ResponseEntity<Void> deleteRecipe(@PathVariable long recipeNumber) {
        if (recipeService.deleteRecipe(recipeNumber)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}