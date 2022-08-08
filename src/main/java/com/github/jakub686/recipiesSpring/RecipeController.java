package com.github.jakub686.recipiesSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
