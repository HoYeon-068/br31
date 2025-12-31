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

            System.out.println("### ProductDAO FINAL VERSION");
            System.out.println("### keyword = [" + keyword + "]");
            System.out.println("### DB USER = " + conn.getMetaData().getUserName());

            String sql;

            if (keyword.isEmpty()) {

                sql =
                    "SELECT " +
                    "  \"products_id\", \"category_id\", \"product_name\", \"english_name\", \"sub_title\", " +
                    "  \"description\", \"product_status\", \"img_path\", \"bg_color\", \"span_color\", " +
                    "  \"poster_path\", \"price\", \"release_date\" " +
                    "FROM \"products\" " +
                    "WHERE \"product_status\" = '판매중' " +
                    "ORDER BY \"product_name\"";

                pstmt = conn.prepareStatement(sql);

            } else {

                sql =
                    "SELECT " +
                    "  \"products_id\", \"category_id\", \"product_name\", \"english_name\", \"sub_title\", " +
                    "  \"description\", \"product_status\", \"img_path\", \"bg_color\", \"span_color\", " +
                    "  \"poster_path\", \"price\", \"release_date\" " +
                    "FROM \"products\" " +
                    "WHERE \"product_status\" = '판매중' " +
                    "AND ( " +
                    "  NVL(\"product_name\", '')  LIKE '%' || ? || '%' " +
                    "  OR NVL(\"english_name\", '') LIKE '%' || ? || '%' " +
                    "  OR NVL(\"sub_title\", '')    LIKE '%' || ? || '%' " +
                    ") " +
                    "ORDER BY \"product_name\"";

                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, keyword);
                pstmt.setString(2, keyword);
                pstmt.setString(3, keyword);
            }

            System.out.println("### EXEC SQL >>> " + sql);

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
                list.add(dto);
            }

            System.out.println("### search result size = " + list.size());

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

        String sql;

        if (keyword.isEmpty()) {
            sql =
                "SELECT COUNT(*) " +
                "FROM \"products\" " +
                "WHERE \"product_status\" = '판매중'";
        } else {
            sql =
                "SELECT COUNT(*) " +
                "FROM \"products\" " +
                "WHERE \"product_status\" = '판매중' " +
                "AND ( " +
                "  NVL(\"product_name\", '')  LIKE '%' || ? || '%' " +
                "  OR NVL(\"english_name\", '') LIKE '%' || ? || '%' " +
                "  OR NVL(\"sub_title\", '')    LIKE '%' || ? || '%' " +
                ")";
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

            System.out.println("### EXEC COUNT SQL >>> " + sql);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) count = rs.getInt(1);

            System.out.println("### totalCount = " + count);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}
