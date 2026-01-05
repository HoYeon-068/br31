package mvc.persistence.faq;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;
import mvc.domain.faq.FaqDTO;

public class FaqDAO {

    private Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myoracle");
        return ds.getConnection();
    }

    // FAQ 목록 (카테고리별 + 페이징)
    public List<FaqDTO> selectList(int categoryId, int startRow, int endRow) {

        List<FaqDTO> list = new ArrayList<>();

        String sql =
        	    "SELECT * FROM ( " +
        	    "  SELECT ROWNUM rn, f.* FROM ( " +
        	    "    SELECT faq.faq_id, faq.question, faq.answer, faq.reg_date, " +
        	    "           cat.\"name\" AS category_name " +
        	    "    FROM faq faq " +
        	    "    JOIN BR31.\"faq_category\" cat " +
        	    "      ON faq.faq_category_id = cat.\"faq_category_id\" " +
        	    "    WHERE (? = 0 OR faq.faq_category_id = ?) " +
        	    "    ORDER BY faq.reg_date DESC " +
        	    "  ) f " +
        	    ") WHERE rn BETWEEN ? AND ?";


        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, categoryId);
            pstmt.setInt(2, categoryId);
            pstmt.setInt(3, startRow);
            pstmt.setInt(4, endRow);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    FaqDTO dto = new FaqDTO();
                    dto.setFaq_id(rs.getInt("faq_id"));
                    dto.setQuestion(rs.getString("question"));
                    dto.setAnswer(rs.getString("answer"));
                    dto.setReg_date(rs.getDate("reg_date"));
                    dto.setCategory_name(rs.getString("category_name"));
                    list.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    // 전체 개수
    public int getTotalCount(int categoryId) {

        int count = 0;
        String sql = "SELECT COUNT(*) FROM faq WHERE (? = 0 OR faq_category_id = ?)";

        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, categoryId);
            pstmt.setInt(2, categoryId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }


    // FAQ 상세
    public FaqDTO selectOne(int faqId) {

        FaqDTO dto = null;

        String sql =
        	    "SELECT faq.faq_id, faq.question, faq.answer, faq.reg_date, " +
        	    "       cat.\"name\" AS category_name " +
        	    "FROM faq faq " +
        	    "JOIN BR31.\"faq_category\" cat " +
        	    "  ON faq.faq_category_id = cat.\"faq_category_id\" " +
        	    "WHERE faq.faq_id = ?";


        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, faqId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    dto = new FaqDTO();
                    dto.setFaq_id(rs.getInt("faq_id"));
                    dto.setQuestion(rs.getString("question"));
                    dto.setAnswer(rs.getString("answer"));
                    dto.setReg_date(rs.getDate("reg_date"));
                    dto.setCategory_name(rs.getString("category_name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
}
