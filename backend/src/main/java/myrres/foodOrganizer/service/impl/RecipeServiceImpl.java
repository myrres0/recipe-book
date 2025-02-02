package myrres.foodOrganizer.service.impl;

import lombok.RequiredArgsConstructor;
import myrres.foodOrganizer.jpa.entity.RecipeEntity;
import myrres.foodOrganizer.jpa.repository.RecipeRepository;
import myrres.foodOrganizer.jpa.repository.UserRepository;
import myrres.foodOrganizer.service.RecipeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Override
    public void addRecipe(RecipeEntity recipeEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        recipeEntity.setUserId(user.getIdUser());
        recipeEntity.setDate(new Date());
        recipeEntity.setLikes(0);
        recipeRepository.save(recipeEntity);
    }

    @Override
    public List<RecipeEntity> filterRecipes(String search, List<Integer> categories, boolean mine, boolean image) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return user != null ? recipeRepository.findAllByFilters(search, categories, user.getIdUser(), mine, image) : null;
    }

    @Override
    public List<RecipeEntity> getAllRecipes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return user != null ? recipeRepository.findAllByUserIdOrPublishedIsTrue(user.getIdUser()) : null;
    }

    @Override
    public List<RecipeEntity> getUsersRecipes() {
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
    public RecipeEntity getRecipe(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    @Override
    public RecipeEntity changeRecipe(Long id, RecipeEntity recipeEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        if (user != null) {
            var recipeToUpdate = recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
            if (recipeToUpdate.getUserId().equals(user.getIdUser())) {
                recipeToUpdate.setName(recipeEntity.getName());
                recipeToUpdate.setDescription(recipeEntity.getDescription());
                recipeToUpdate.setIngredients(recipeEntity.getIngredients());
                recipeToUpdate.setCategory(recipeEntity.getCategory());
                recipeToUpdate.setPublished(recipeEntity.isPublished());
                recipeRepository.save(recipeToUpdate);
                return recipeToUpdate;
            }
        }
        throw new RuntimeException("User not found");
    }
}
