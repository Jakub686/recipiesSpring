package com.github.jakub686.recipiesSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @PostMapping("/api/recipe/new")
    public ResponseEntity<String> addRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe newRecipe = recipeService.save(new Recipe(
                recipe.getId(), recipe.getName(), recipe.getCategory(), LocalDateTime.now(), recipe.getDescription(),
                recipe.getIngredients(), recipe.getDirections()));
        return new ResponseEntity<>("{\"id\": " + newRecipe.getId() + "}", HttpStatus.OK);
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<String> putRecipe(@PathVariable long id, @Valid @RequestBody Recipe recipe) {
        Recipe recipe1 = recipeService.findRecipeById(id);

        if (recipeService.findRecipeById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            recipe1.setName(recipe.getName());
            recipe1.setCategory(recipe.getCategory());
            recipe1.setDate(LocalDateTime.now());
            recipe1.setDescription(recipe.getDescription());
            recipe1.setIngredients(recipe.getIngredients());
            recipe1.setDirections(recipe.getDirections());
            recipeService.save(recipe1);
            return new ResponseEntity<>("{\"id\": " + recipe1.getId() + "}", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable long id) {
        if (recipeService.findRecipeById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return recipeService.findRecipeById(id);
        }
    }
}
