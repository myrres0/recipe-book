package myrres.foodOrganizer.jpa.repository;

import myrres.foodOrganizer.jpa.entity.RecipeEntity;
import myrres.foodOrganizer.rest.api.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    List<RecipeEntity> findAllByUserId(Long idUser);

    List<RecipeEntity> findAllByUserIdOrPublishedIsTrue(Long idUser);

    List<RecipeEntity> findAllByCategory(RecipeCategory category);


    @Query("""
    SELECT recipeEntity FROM RecipeEntity recipeEntity WHERE
    (recipeEntity.name LIKE %:search% OR recipeEntity.description LIKE %:search%)
    AND (recipeEntity.published = true OR recipeEntity.userId = :id)
    """)
    List<RecipeEntity> findAllByNameOrDescriptionContains(String search, Long id);

    @Query("""
    SELECT DISTINCT recipeEntity
    FROM RecipeEntity recipeEntity
    WHERE (:search IS NULL OR
            recipeEntity.name LIKE %:search% OR recipeEntity.description LIKE %:search%)
    AND (:categories IS NULL OR recipeEntity.category IN :categories)
    AND (:mine = true AND recipeEntity.userId = :id
          OR :mine = false AND recipeEntity.published = true AND recipeEntity.userId = :id)
    AND (:image = false OR (recipeEntity.image != 'https://via.placeholder.com/150' AND recipeEntity.image IS NOT NULL))
    """)
    List<RecipeEntity> findAllByFilters(String search, List<Integer> categories, Long id, boolean image, boolean mine);
}
