package com.github.jakub686.recipiesSpring;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findRecipeById(Long id);

    List<Recipe> findByCategory(String category);

    List<Recipe> findByName(String name);
}