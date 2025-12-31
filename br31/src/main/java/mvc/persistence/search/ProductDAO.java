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

    /**
     * 상품 검색 리스트
     * - 검색어 길이 무관
     * - 검색어 없으면 전체 조회
     */
    public List<ProductDTO> search(String keyword) {

        List<ProductDTO> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        if (keyword == null) keyword = "";

        String sql =
        	    "SELECT products_id, category_id, product_name, " +
        	    "       description, product_status, img_path, img_s_path, bg_color, " +
        	    "       poster_path, price, release_date " +
        	    "FROM \"products\" " +
        	    "WHERE product_status = 'Y' " +
        	    "AND product_name LIKE '%' || ? || '%' " +
        	    "ORDER BY product_name";
        
        
     


        try {
            conn = ConnectionProvider.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, keyword);
         
           

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO dto = new ProductDTO();
                dto.setProductsId(rs.getInt("products_id"));
                dto.setCategoryId(rs.getInt("category_id"));
                dto.setProductName(rs.getString("product_name"));
                dto.setDescription(rs.getString("description"));
                dto.setProductStatus(rs.getString("product_status"));
                dto.setImgPath(rs.getString("img_path"));
                dto.setImgSPath(rs.getString("img_s_path"));
                dto.setBgColor(rs.getString("bg_color"));
                dto.setPosterPath(rs.getString("poster_path"));
                dto.setPrice(rs.getInt("price"));
                dto.setReleaseDate(rs.getDate("release_date"));

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

    /**
     * 검색 결과 총 개수
     */
    public int getTotalCount(String keyword) {

        int count = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        if (keyword == null) keyword = "";

        String sql =
        	    "SELECT COUNT(*) " +
        	    "FROM \"products\" " +
        	    "WHERE product_status = 'Y' " +
        	    "AND product_name LIKE '%' || ? || '%'";



        try {
            conn = ConnectionProvider.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, keyword);
       
          

            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
            JdbcUtil.close(conn);
        }

        return count;
    }
}
