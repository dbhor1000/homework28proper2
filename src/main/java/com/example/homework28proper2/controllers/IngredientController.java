package com.example.homework28proper2.controllers;

import com.example.homework28proper2.model.Ingredient;
import com.example.homework28proper2.services.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Контроллер для обработки запросов, относящихся к сервису ингредиентов
@RestController
@RequestMapping("/ingredient")
@Tag(name = "Игредиенты", description = "Контроллер для запросов к сервису ингредиентов")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService){

        this.ingredientService = ingredientService;

    }

    @PostMapping("/addIngredient")  //Эндпоинт для добавления ингредиента
    @Operation(
            summary = "Добавление ингредиента",
            description = "Добавление нового ингредиента в карту"
    )
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {

        Ingredient addedIngredient = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(addedIngredient);

    }


    @GetMapping("/{ingredientNumber}")  //Эндпоинт для получения ингредиента по номеру
    @Operation(
            summary = "Вывод ингредиента",
            description = "Вывод ингредиента под номером, указанным в адресной строке"
    )
    public ResponseEntity<Ingredient> getIngredient(@PathVariable long ingredientNumber) {

        Ingredient ingredient = ingredientService.getIngredient(ingredientNumber);

        if (ObjectUtils.isEmpty(ingredient)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ingredient);
    }

    @PutMapping("/edit/{ingredientNumber}") //Эндпоинт для модификации ингредиента по номеру
    @Operation(
            summary = "Редактирование ингредиента",
            description = "Редактирование ингредиента под номером, указанным в адресной строке"
    )
    public ResponseEntity<Ingredient> editIngredient(@PathVariable long ingredientNumber, @RequestBody Ingredient ingredient) {

        Ingredient editedIngredient = ingredientService.editIngredient(ingredientNumber, ingredient);

        if (ObjectUtils.isEmpty(ingredient)) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ingredient);
    }

    @DeleteMapping("/delete/{ingredientNumber}") //Эндпоинт для удаления ингредиента
    @Operation(
            summary = "Удаление ингредиента",
            description = "Удаление ингредиента под номером, указанным в адресной строке"
    )
    public ResponseEntity<Void> deleteIngredient(@PathVariable long ingredientNumber) {

        if (ingredientService.deleteIngredient(ingredientNumber)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}