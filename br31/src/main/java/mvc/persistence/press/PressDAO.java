package mvc.persistence.press;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mvc.domain.press.PressDTO;

public class PressDAO {

    private Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myoracle");
        return ds.getConnection();
    }

    // 보도자료 목록 (페이징 + 최신순)
    public List<PressDTO> selectList(int startRow, int endRow) {

        List<PressDTO> list = new ArrayList<>();

        String sql =
        		"SELECT * FROM ( " +
        			    "  SELECT ROWNUM rn, p.* FROM ( " +
        			    "    SELECT \"pr_id\", \"pr_no\", \"title\", \"reg_date\" " +
        			    "    FROM BR31.\"press_release\" " +
        			    "    ORDER BY \"reg_date\" DESC " +
        			    "  ) p " +
        			    ") WHERE rn BETWEEN ? AND ?";

        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PressDTO dto = new PressDTO();
                    dto.setPr_id(rs.getInt("pr_id"));
                    dto.setPr_no(rs.getString("pr_no"));
                    dto.setTitle(rs.getString("title"));
                    dto.setReg_date(rs.getDate("reg_date"));
                    list.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 전체 글 수
    public int getTotalCount() {

        int count = 0;
        String sql = "SELECT COUNT(*) FROM BR31.\"press_release\"";


        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
        ) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    // 보도자료 상세
    public PressDTO selectOne(int pr_id) {

        PressDTO dto = null;

        String sql =
        	    "SELECT \"pr_id\", \"pr_no\", \"title\", \"content\", \"reg_date\" " +
        	    "FROM BR31.\"press_release\" WHERE \"pr_id\" = ?";


        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, pr_id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    dto = new PressDTO();
                    dto.setPr_id(rs.getInt("pr_id"));
                    dto.setPr_no(rs.getString("pr_no"));
                    dto.setTitle(rs.getString("title"));
                    dto.setContent(rs.getString("content"));
                    dto.setReg_date(rs.getDate("reg_date"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;
    }
}
