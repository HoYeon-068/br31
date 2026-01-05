package mvc.persistence.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mvc.domain.notice.NoticeDTO;

public class NoticeDAO {

    private Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myoracle");
        return ds.getConnection();
    }

    // 공지사항 목록
    public List<NoticeDTO> selectList(int startRow, int endRow) {

        List<NoticeDTO> list = new ArrayList<>();

        String sql =
            "SELECT * FROM ( " +
            "  SELECT ROWNUM rn, n.* FROM ( " +
            "    SELECT NOTICE_ID, NOTICE_NO, TITLE, REG_DATE " +
            "    FROM NOTICE " +
            "    ORDER BY REG_DATE DESC " +
            "  ) n " +
            ") WHERE rn BETWEEN ? AND ?";

        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    NoticeDTO dto = new NoticeDTO();
                    dto.setNOTICE_ID(rs.getInt("NOTICE_ID"));
                    dto.setNOTICE_NO(rs.getString("NOTICE_NO"));
                    dto.setTITLE(rs.getString("TITLE"));
                    dto.setREG_DATE(rs.getDate("REG_DATE"));
                    list.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public int getTotalCount() {

        int count = 0;
        String sql = "SELECT COUNT(*) FROM NOTICE";

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

    
    // 공지사항 상세
    public NoticeDTO selectOne(int NOTICE_ID) {

        NoticeDTO dto = null;

        String sql =
            "SELECT NOTICE_ID, NOTICE_NO, TITLE, CONTENT, REG_DATE " +
            "FROM NOTICE " +
            "WHERE NOTICE_ID = ?";

        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, NOTICE_ID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    dto = new NoticeDTO();
                    dto.setNOTICE_ID(rs.getInt("NOTICE_ID"));
                    dto.setNOTICE_NO(rs.getString("NOTICE_NO"));
                    dto.setTITLE(rs.getString("TITLE"));
                    dto.setCONTENT(rs.getString("CONTENT"));
                    dto.setREG_DATE(rs.getDate("REG_DATE"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;
    }
}
