package mvc.persistence.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;
import mvc.domain.search.ProductDTO;

public class ProductDAO {

    /* =======================
     *  상품 검색
     * ======================= */
    public List<ProductDTO> search(String keyword) {

        List<ProductDTO> list = new ArrayList<>();

        if (keyword == null) keyword = "";
        keyword = keyword.trim();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionProvider.getConnection();

            String sql =
                "SELECT " +
                "  p.\"products_id\", p.\"category_id\", p.\"product_name\", p.\"english_name\", p.\"sub_title\", " +
                "  p.\"description\", p.\"product_status\", p.\"img_path\", p.\"bg_color\", p.\"span_color\", " +
                "  p.\"poster_path\", p.\"price\", p.\"release_date\", " +
                "  T.\"tags\" " +
                "FROM \"products\" p " +
                "LEFT JOIN ( " +
                "   SELECT \"products_id\", " +
                "          LISTAGG('#' || tag, ' ') WITHIN GROUP (ORDER BY tag) AS \"tags\" " +
                "   FROM ( " +
                "       SELECT DISTINCT \"products_id\", TRIM(\"tag\") AS tag " +
                "       FROM \"product_tag\" " +
                "   ) " +
                "   GROUP BY \"products_id\" " +
                ") T ON p.\"products_id\" = T.\"products_id\" " +
                "WHERE p.\"product_status\" = '판매중' ";

            if (!keyword.isEmpty()) {
                sql +=
                    "AND ( " +
                    "  NVL(p.\"product_name\", '')  LIKE '%' || ? || '%' " +
                    "  OR NVL(p.\"english_name\", '') LIKE '%' || ? || '%' " +
                    "  OR NVL(p.\"sub_title\", '')    LIKE '%' || ? || '%' " +
                    ") ";
            }

            sql += "ORDER BY p.\"product_name\"";

            pstmt = conn.prepareStatement(sql);

            int idx = 1;
            if (!keyword.isEmpty()) {
                pstmt.setString(idx++, keyword);
                pstmt.setString(idx++, keyword);
                pstmt.setString(idx++, keyword);
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO dto = new ProductDTO();
                dto.setProductsId(rs.getInt("products_id"));
                dto.setCategoryId(rs.getInt("category_id"));
                dto.setProductName(rs.getString("product_name"));
                dto.setEnglishName(rs.getString("english_name"));
                dto.setSubTitle(rs.getString("sub_title"));
                dto.setDescription(rs.getString("description"));
                dto.setProductStatus(rs.getString("product_status"));
                dto.setImgPath(rs.getString("img_path"));
                dto.setBgColor(rs.getString("bg_color"));
                dto.setPosterPath(rs.getString("poster_path"));
                dto.setPrice(rs.getInt("price"));
                dto.setReleaseDate(rs.getDate("release_date"));
                dto.setTags(rs.getString("tags")); // ⭐ 해시태그
                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
            JdbcUtil.close(conn);
        }

        return list;
    }

    /* =======================
     *  상품 검색 + 카테고리
     * ======================= */
    public List<ProductDTO> search(String keyword, Integer categoryId) {

        List<ProductDTO> list = new ArrayList<>();

        if (keyword == null) keyword = "";
        keyword = keyword.trim();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionProvider.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ");
            sql.append(" p.\"products_id\", p.\"category_id\", p.\"product_name\", p.\"english_name\", p.\"sub_title\", ");
            sql.append(" p.\"description\", p.\"product_status\", p.\"img_path\", p.\"bg_color\", p.\"span_color\", ");
            sql.append(" p.\"poster_path\", p.\"price\", p.\"release_date\", ");
            sql.append(" T.\"tags\" ");
            sql.append("FROM \"products\" p ");
            sql.append("LEFT JOIN ( ");
            sql.append("   SELECT \"products_id\", ");
            sql.append("          LISTAGG('#' || tag, ' ') WITHIN GROUP (ORDER BY tag) AS \"tags\" ");
            sql.append("   FROM ( ");
            sql.append("       SELECT DISTINCT \"products_id\", TRIM(\"tag\") AS tag ");
            sql.append("       FROM \"product_tag\" ");
            sql.append("   ) ");
            sql.append("   GROUP BY \"products_id\" ");
            sql.append(") T ON p.\"products_id\" = T.\"products_id\" ");
            sql.append("WHERE p.\"product_status\" = '판매중' ");

            if (!keyword.isEmpty()) {
                sql.append("AND ( ");
                sql.append(" NVL(p.\"product_name\", '')  LIKE '%' || ? || '%' ");
                sql.append(" OR NVL(p.\"english_name\", '') LIKE '%' || ? || '%' ");
                sql.append(" OR NVL(p.\"sub_title\", '')    LIKE '%' || ? || '%' ");
                sql.append(") ");
            }

            if (categoryId != null) {
                sql.append("AND p.\"category_id\" = ? ");
            }

            sql.append("ORDER BY p.\"product_name\"");

            pstmt = conn.prepareStatement(sql.toString());

            int idx = 1;
            if (!keyword.isEmpty()) {
                pstmt.setString(idx++, keyword);
                pstmt.setString(idx++, keyword);
                pstmt.setString(idx++, keyword);
            }
            if (categoryId != null) {
                pstmt.setInt(idx++, categoryId);
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO dto = new ProductDTO();
                dto.setProductsId(rs.getInt("products_id"));
                dto.setCategoryId(rs.getInt("category_id"));
                dto.setProductName(rs.getString("product_name"));
                dto.setEnglishName(rs.getString("english_name"));
                dto.setSubTitle(rs.getString("sub_title"));
                dto.setDescription(rs.getString("description"));
                dto.setProductStatus(rs.getString("product_status"));
                dto.setImgPath(rs.getString("img_path"));
                dto.setBgColor(rs.getString("bg_color"));
                dto.setPosterPath(rs.getString("poster_path"));
                dto.setPrice(rs.getInt("price"));
                dto.setReleaseDate(rs.getDate("release_date"));
                dto.setTags(rs.getString("tags")); // ⭐ 해시태그
                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
            JdbcUtil.close(conn);
        }

        return list;
    }

    /* =======================
     *  검색 결과 개수
     * ======================= */
    public int getTotalCount(String keyword) {

        if (keyword == null) keyword = "";
        keyword = keyword.trim();

        int count = 0;

        String sql =
            "SELECT COUNT(*) " +
            "FROM \"products\" " +
            "WHERE \"product_status\" = '판매중' ";

        if (!keyword.isEmpty()) {
            sql +=
                "AND ( " +
                " NVL(\"product_name\", '')  LIKE '%' || ? || '%' " +
                " OR NVL(\"english_name\", '') LIKE '%' || ? || '%' " +
                " OR NVL(\"sub_title\", '')    LIKE '%' || ? || '%' " +
                ") ";
        }

        try (
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            if (!keyword.isEmpty()) {
                pstmt.setString(1, keyword);
                pstmt.setString(2, keyword);
                pstmt.setString(3, keyword);
            }

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) count = rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public int getTotalCount(String keyword, Integer categoryId) {

        if (keyword == null) keyword = "";
        keyword = keyword.trim();

        int count = 0;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) ");
        sql.append("FROM \"products\" ");
        sql.append("WHERE \"product_status\" = '판매중' ");

        if (!keyword.isEmpty()) {
            sql.append("AND ( ");
            sql.append(" NVL(\"product_name\", '')  LIKE '%' || ? || '%' ");
            sql.append(" OR NVL(\"english_name\", '') LIKE '%' || ? || '%' ");
            sql.append(" OR NVL(\"sub_title\", '')    LIKE '%' || ? || '%' ");
            sql.append(") ");
        }

        if (categoryId != null) {
            sql.append("AND \"category_id\" = ? ");
        }

        try (
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString())
        ) {

            int idx = 1;

            if (!keyword.isEmpty()) {
                pstmt.setString(idx++, keyword);
                pstmt.setString(idx++, keyword);
                pstmt.setString(idx++, keyword);
            }

            if (categoryId != null) {
                pstmt.setInt(idx++, categoryId);
            }

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) count = rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}
