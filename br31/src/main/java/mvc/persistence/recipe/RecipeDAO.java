package mvc.persistence.recipe;

import java.util.List;
import mvc.domain.recipe.RecipeCategoryDTO;
import mvc.domain.recipe.RecipeListDTO;
import mvc.domain.recipe.RecipeViewDTO;

public interface RecipeDAO {
    List<RecipeCategoryDTO> selectCategories();

    int count(Integer categoryId);

    List<RecipeListDTO> selectList(Integer categoryId, int offset, int pageSize);

    RecipeViewDTO view(int recipeId);
}
