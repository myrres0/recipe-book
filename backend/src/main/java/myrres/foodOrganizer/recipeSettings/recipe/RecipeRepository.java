package myrres.foodOrganizer.recipeSettings.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findAllByUserId(Long idUser);

    List<Recipe> findAllByUserIdOrPublishedIsTrue(Long idUser);

    List<Recipe> findAllByCategory(RecipeCategory category);


    @Query("""
    SELECT recipe FROM Recipe recipe WHERE
    (recipe.name LIKE %:search% OR recipe.description LIKE %:search%)
    AND (recipe.published = true OR recipe.userId = :id)
    """)
    List<Recipe> findAllByNameOrDescriptionContains(String search, Long id);

    @Query("""
    SELECT DISTINCT recipe
    FROM Recipe recipe
    WHERE (:search IS NULL OR
            recipe.name LIKE %:search% OR recipe.description LIKE %:search%)
    AND (:categories IS NULL OR recipe.category IN :categories)
    AND (:mine = true AND recipe.userId = :id
          OR :mine = false AND recipe.published = true AND recipe.userId = :id)
    AND (:image = false OR (recipe.image != 'https://via.placeholder.com/150' AND recipe.image IS NOT NULL))
    """)
    List<Recipe> findAllByFilters(String search, List<Integer> categories, Long id, boolean image, boolean mine);
}
