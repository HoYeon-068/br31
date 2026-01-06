package mvc.persistence.recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.domain.recipe.RecipeCategoryDTO;
import mvc.domain.recipe.RecipeListDTO;
import mvc.domain.recipe.RecipeViewDTO;

public class RecipeDAOImpl implements RecipeDAO {

    private Connection conn;

    public RecipeDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<RecipeCategoryDTO> selectCategories() {
        String sql = "SELECT \"recipe_category_id\", \"category_name\" " +
                     "FROM \"recipe_category\" " +
                     "ORDER BY \"recipe_category_id\"";

        List<RecipeCategoryDTO> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(RecipeCategoryDTO.builder()
                        .recipeCategoryId(rs.getInt("recipe_category_id"))
                        .categoryName(rs.getString("category_name"))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public int count(Integer categoryId) {
        String sqlAll = "SELECT COUNT(*) cnt FROM \"recipe\"";
        String sqlCat = "SELECT COUNT(*) cnt FROM \"recipe\" WHERE \"recipe_category_id\" = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(categoryId == null ? sqlAll : sqlCat)) {
            if (categoryId != null) pstmt.setInt(1, categoryId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt("cnt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<RecipeListDTO> selectList(Integer categoryId, int offset, int pageSize) {

        int startRow = offset + 1;          // 1부터 시작
        int endRow   = offset + pageSize;   // 포함

        String sql =
            "SELECT * FROM ( " +
            "  SELECT t.*, ROWNUM rn FROM ( " +
            "    SELECT " +
            "      c.\"category_name\", r.\"title\", r.\"sub_title\", r.\"thumbnail\", r.\"recipe_id\", r.\"recipe_category_id\" " +
            "    FROM \"recipe\" r " +
            "    JOIN \"recipe_category\" c ON r.\"recipe_category_id\" = c.\"recipe_category_id\" " +
            (categoryId == null ? "" : "    WHERE r.\"recipe_category_id\" = ? ") +
            "    ORDER BY r.\"recipe_id\" ASC " +
            "  ) t " +
            "  WHERE ROWNUM <= ? " +
            ") " +
            "WHERE rn >= ?"; 

        List<RecipeListDTO> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int idx = 1;

            if (categoryId != null) {
                pstmt.setInt(idx++, categoryId);
            }

            pstmt.setInt(idx++, endRow);
            pstmt.setInt(idx++, startRow);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(RecipeListDTO.builder()
                        .categoryName(rs.getString("category_name"))
                        .title(rs.getString("title"))
                        .subTitle(rs.getString("sub_title"))
                        .thumbnail(rs.getString("thumbnail"))
                        .recipeId(rs.getInt("recipe_id"))
                        .recipeCategoryId(rs.getInt("recipe_category_id"))
                        .build());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public RecipeViewDTO view(int recipeId) {
        String sql =
            "SELECT " +
            "  r.\"recipe_id\", r.\"title\", r.\"sub_title\", r.\"thumbnail\", r.\"content_html\", " +
            "  r.\"recipe_category_id\", c.\"category_name\" " +
            "FROM \"recipe\" r " +
            "JOIN \"recipe_category\" c ON r.\"recipe_category_id\" = c.\"recipe_category_id\" " +
            "WHERE r.\"recipe_id\" = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, recipeId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return RecipeViewDTO.builder()
                            .recipeId(rs.getInt("recipe_id"))
                            .title(rs.getString("title"))
                            .subTitle(rs.getString("sub_title"))
                            .thumbnail(rs.getString("thumbnail"))
                            .contentHtml(rs.getString("content_html"))
                            .recipeCategoryId(rs.getInt("recipe_category_id"))
                            .categoryName(rs.getString("category_name"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
