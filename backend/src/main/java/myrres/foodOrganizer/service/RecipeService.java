package myrres.foodOrganizer.service;

import myrres.foodOrganizer.jpa.entity.RecipeEntity;

import java.util.List;

public interface RecipeService {
    public void addRecipe(RecipeEntity recipeEntity);

    List<RecipeEntity> filterRecipes(String search, List<Integer> categories, boolean mine, boolean image);

    List<RecipeEntity> getAllRecipes();

    List<RecipeEntity> getUsersRecipes();

    String deleteRecipe(Long id);

    RecipeEntity getRecipe(Long id);

    RecipeEntity changeRecipe(Long id, RecipeEntity recipeEntity);

}
