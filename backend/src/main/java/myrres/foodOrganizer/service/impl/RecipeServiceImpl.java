package myrres.foodOrganizer.service.impl;

import lombok.RequiredArgsConstructor;
import myrres.foodOrganizer.jpa.entity.Recipe;
import myrres.foodOrganizer.jpa.repository.RecipeRepository;
import myrres.foodOrganizer.jpa.repository.UserRepository;
import myrres.foodOrganizer.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;
    private UserRepository userRepository;

    @Override
    public void addRecipe(Recipe recipe) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        recipe.setUserId(user.getIdUser());
        recipe.setDate(new Date());
        recipe.setLikes(0);
        recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> filterRecipes(String search, List<Integer> categories, boolean mine, boolean image) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return user != null ? recipeRepository.findAllByFilters(search, categories, user.getIdUser(), mine, image) : null;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return user != null ? recipeRepository.findAllByUserIdOrPublishedIsTrue(user.getIdUser()) : null;
    }

    @Override
    public List<Recipe> getUsersRecipes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return user != null ? recipeRepository.findAllByUserId(user.getIdUser()) : null;
    }

    @Override
    public String deleteRecipe(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        if (user != null) {
            var recipe = recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
            if (recipe.getUserId().equals(user.getIdUser())) {
                recipeRepository.delete(recipe);
                return String.format("Recipe with id %s deleted", recipe.getId());
            }
        }
        return null;
    }

    @Override
    public Recipe getRecipe(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    @Override
    public Recipe changeRecipe(Long id, Recipe recipe) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        if (user != null) {
            var recipeToUpdate = recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
            if (recipeToUpdate.getUserId().equals(user.getIdUser())) {
                recipeToUpdate.setName(recipe.getName());
                recipeToUpdate.setDescription(recipe.getDescription());
                recipeToUpdate.setIngredients(recipe.getIngredients());
                recipeToUpdate.setCategory(recipe.getCategory());
                recipeToUpdate.setPublished(recipe.isPublished());
                recipeRepository.save(recipeToUpdate);
                return recipeToUpdate;
            }
        }
        throw new RuntimeException("User not found");
    }
}
