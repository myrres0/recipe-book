package myrres.foodOrganizer.service;

import myrres.foodOrganizer.jpa.entity.Recipe;

import java.util.List;

public interface RecipeService {
    public void addRecipe(Recipe recipe);

    List<Recipe> filterRecipes(String search, List<Integer> categories, boolean mine, boolean image);

    List<Recipe> getAllRecipes();

    List<Recipe> getUsersRecipes();

    String deleteRecipe(Long id);

    Recipe getRecipe(Long id);

    Recipe changeRecipe(Long id, Recipe recipe);

}
