package myrres.foodOrganizer.recipeSettings.controller;


import myrres.foodOrganizer.recipeSettings.recipe.Recipe;
import myrres.foodOrganizer.recipeSettings.recipe.RecipeRepository;
import myrres.foodOrganizer.userSettings.user.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/recipe")
public class RecipeController {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;


    @PostMapping()
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
        recipe.setUserId(user.getIdUser());
        recipe.setDate(new Date());
        recipe.setLikes(0);
        return ResponseEntity.ok(recipeRepository.save(recipe));

    }


//    @GetMapping("search/{search}")
//    public ResponseEntity<List<Recipe>> searchRecipes(@PathVariable String search){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        var user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
//        return ResponseEntity.ok(recipeRepository.findAllByNameOrDescriptionContains(search, user.getIdUser()));
//    }


    @GetMapping("filter")
    public ResponseEntity<List<Recipe>> filterRecipes(@RequestParam(required = false) String search, @RequestParam(required = false) List<Integer> categories,
                                                      @RequestParam boolean mine, @RequestParam boolean image){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
        if (user!=null){

            return ResponseEntity.ok(recipeRepository.findAllByFilters(search, categories, user.getIdUser(), mine, image));
        }
        return ResponseEntity.badRequest().build();
    }


    @GetMapping("all")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        if (user != null) {
            return ResponseEntity.ok(recipeRepository.findAllByUserIdOrPublishedIsTrue(user.getIdUser()));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping()
    public ResponseEntity<List<Recipe>> getUsersRecipes(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user =userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
        if (user!=null){
            return ResponseEntity.ok(recipeRepository.findAllByUserId(user.getIdUser()));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user =userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
        if (user!=null){
            var recipe = recipeRepository.findById(id).orElseThrow(()-> new RuntimeException("Recipe not found"));
            if (recipe.getUserId().equals(user.getIdUser())) {
                recipeRepository.delete(recipe);
                return ResponseEntity.ok("Recipe deleted");
            }
        }
        return ResponseEntity.badRequest().build();
    }


    @GetMapping("details/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id){
        return ResponseEntity.ok(recipeRepository.findById(id).orElseThrow(()-> new RuntimeException("Recipe not found")));
    }

    @PutMapping("details/{id}")
    public ResponseEntity<Recipe> changeRecipe(@PathVariable Long id, @RequestBody Recipe recipe){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user =userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
        if (user!=null){
            var recipeToUpdate = recipeRepository.findById(id).orElseThrow(()-> new RuntimeException("Recipe not found"));
            if (recipeToUpdate.getUserId().equals(user.getIdUser())) {
                recipeToUpdate.setName(recipe.getName());
                recipeToUpdate.setDescription(recipe.getDescription());
                recipeToUpdate.setIngredients(recipe.getIngredients());
                recipeToUpdate.setCategory(recipe.getCategory());
                recipeToUpdate.setPublished(recipe.isPublished());
                return ResponseEntity.ok(recipeRepository.save(recipeToUpdate));
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
