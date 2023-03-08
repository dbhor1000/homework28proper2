package com.example.homework28proper2.controllers;

import com.example.homework28proper2.model.Ingredient;
import com.example.homework28proper2.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService){

        this.ingredientService = ingredientService;

    }

    @PostMapping("/addIngredient")
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {

        Ingredient addedIngredient = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(addedIngredient);

    }


    @GetMapping("/{ingredientNumber}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable long ingredientNumber) {

        Ingredient ingredient = ingredientService.getIngredient(ingredientNumber);

        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ingredient);
    }

    @PutMapping("/edit/{ingredientNumber}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable long ingredientNumber, @RequestBody Ingredient ingredient) {

        Ingredient editedIngredient = ingredientService.editIngredient(ingredientNumber, ingredient);

        if (ingredient == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ingredient);
    }

    @DeleteMapping("/delete/{ingredientNumber}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable long ingredientNumber) {

        if (ingredientService.deleteIngredient(ingredientNumber)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}