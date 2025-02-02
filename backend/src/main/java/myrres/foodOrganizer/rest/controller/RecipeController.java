package myrres.foodOrganizer.rest.controller;


import lombok.RequiredArgsConstructor;
import myrres.foodOrganizer.jpa.entity.RecipeEntity;
import myrres.foodOrganizer.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping()
    public ResponseEntity<?> addRecipe(@RequestBody RecipeEntity recipeEntity) {
        recipeService.addRecipe(recipeEntity);
        return ResponseEntity.ok().build();

    }

    @GetMapping("filter")
    public ResponseEntity<List<RecipeEntity>> filterRecipes(@RequestParam(required = false) String search, @RequestParam(required = false) List<Integer> categories,
                                                            @RequestParam boolean mine, @RequestParam boolean image) {
        List<RecipeEntity> recipeEntities = recipeService.filterRecipes(search, categories, mine, image);
        if (CollectionUtils.isEmpty(recipeEntities)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipeEntities);
    }


    @GetMapping("all")
    public ResponseEntity<List<RecipeEntity>> getAllRecipes() {
        List<RecipeEntity> recipeEntities = recipeService.getAllRecipes();
        if (CollectionUtils.isEmpty(recipeEntities)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipeEntities);
    }

    @GetMapping()
    public ResponseEntity<List<RecipeEntity>> getUsersRecipes() {
        List<RecipeEntity> recipeEntities = recipeService.getUsersRecipes();
        if (CollectionUtils.isEmpty(recipeEntities)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipeEntities);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
        String result = recipeService.deleteRecipe(id);
        return ResponseEntity.ok(result);
    }


    @GetMapping("details/{id}")
    public ResponseEntity<RecipeEntity> getRecipe(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }

    @PutMapping("details/{id}")
    public ResponseEntity<RecipeEntity> changeRecipe(@PathVariable Long id, @RequestBody RecipeEntity recipeEntity) {
        return ResponseEntity.ok(recipeService.changeRecipe(id, recipeEntity));
    }
}
