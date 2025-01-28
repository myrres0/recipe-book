package myrres.foodOrganizer.rest.controller;


import lombok.RequiredArgsConstructor;
import myrres.foodOrganizer.jpa.entity.Recipe;
import myrres.foodOrganizer.jpa.repository.RecipeRepository;
import myrres.foodOrganizer.jpa.repository.UserRepository;
import myrres.foodOrganizer.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private RecipeService recipeService;

    @PostMapping()
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
        return ResponseEntity.ok().build();

    }

    @GetMapping("filter")
    public ResponseEntity<List<Recipe>> filterRecipes(@RequestParam(required = false) String search, @RequestParam(required = false) List<Integer> categories,
                                                      @RequestParam boolean mine, @RequestParam boolean image) {
        List<Recipe> recipes = recipeService.filterRecipes(search, categories, mine, image);
        if (CollectionUtils.isEmpty(recipes)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipes);
    }


    @GetMapping("all")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        if (CollectionUtils.isEmpty(recipes)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipes);
    }

    @GetMapping()
    public ResponseEntity<List<Recipe>> getUsersRecipes() {
        List<Recipe> recipes = recipeService.getUsersRecipes();
        if (CollectionUtils.isEmpty(recipes)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipes);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
        String result = recipeService.deleteRecipe(id);
        return ResponseEntity.ok(result);
    }


    @GetMapping("details/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }

    @PutMapping("details/{id}")
    public ResponseEntity<Recipe> changeRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.changeRecipe(id, recipe));
    }
}
