package mvc.persistence.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.ConnectionProvider;

import mvc.domain.search.BoardSearchDTO;

public class BoardSearchDAO {

    /* =========================
     * 공지사항만 조회 (검색어 없음)
     * ========================= */
    public List<BoardSearchDTO> searchNoticeOnly() {

        List<BoardSearchDTO> list = new ArrayList<>();

        String sql =
            "SELECT " +
            "  'NOTICE' AS type, " +
            "  NOTICE_ID AS id, " +
            "  ROW_NUMBER() OVER (ORDER BY NOTICE_ID DESC) AS no, " +
            "  TITLE AS title, " +
            "  REG_DATE AS reg_date " +
            "FROM NOTICE";

        try (
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                BoardSearchDTO dto = new BoardSearchDTO();
                dto.setType(rs.getString("type"));
                dto.setId(rs.getInt("id"));
                dto.setNo(rs.getString("no"));
                dto.setTitle(rs.getString("title"));
                dto.setRegDate(rs.getDate("reg_date"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /* =========================
     * 보도자료만 조회 (검색어 없음)
     * ※ 테이블/컬럼 큰따옴표 필수
     * ========================= */
    public List<BoardSearchDTO> searchPressOnly() {

        List<BoardSearchDTO> list = new ArrayList<>();

        String sql =
        	    "SELECT " +
        	    "  'PRESS' AS type, " +
        	    "  \"pr_id\" AS id, " +
        	    "  ROW_NUMBER() OVER (ORDER BY \"pr_id\" DESC) AS no, " +
        	    "  \"title\" AS title, " +
        	    "  \"reg_date\" AS reg_date " +
        	    "FROM \"press_release\"";

        try (
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                BoardSearchDTO dto = new BoardSearchDTO();
                dto.setType(rs.getString("type"));
                dto.setId(rs.getInt("id"));
                dto.setNo(rs.getString("no"));
                dto.setTitle(rs.getString("title"));
                dto.setRegDate(rs.getDate("reg_date"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /* =========================
     * 공지 + 보도 통합 검색 (검색어 있음)
     * ========================= */
    public List<BoardSearchDTO> searchAll(String keyword) {

        List<BoardSearchDTO> list = new ArrayList<>();

        String sql =
        	    "SELECT type, id, no, title, reg_date FROM ( " +

        	    "  SELECT " +
        	    "    'NOTICE' AS type, " +
        	    "    NOTICE_ID AS id, " +
        	    "    ROW_NUMBER() OVER (ORDER BY NOTICE_ID DESC) AS no, " +
        	    "    TITLE AS title, " +
        	    "    REG_DATE AS reg_date " +
        	    "  FROM NOTICE " +
        	    "  WHERE UPPER(TITLE) LIKE UPPER(?) " +
        	    "     OR UPPER(CONTENT) LIKE UPPER(?) " +

        	    "  UNION ALL " +

        	    "  SELECT " +
        	    "    'PRESS' AS type, " +
        	    "    \"pr_id\" AS id, " +
        	    "    ROW_NUMBER() OVER (ORDER BY \"pr_id\" DESC) AS no, " +
        	    "    \"title\" AS title, " +
        	    "    \"reg_date\" AS reg_date " +
        	    "  FROM \"press_release\" " +
        	    "  WHERE UPPER(\"title\") LIKE UPPER(?) " +
        	    "     OR UPPER(\"content\") LIKE UPPER(?) " +

        	    ") ORDER BY reg_date DESC";




        try (
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {

            String like = "%" + keyword + "%";
            pstmt.setString(1, like);
            pstmt.setString(2, like);
            pstmt.setString(3, like);
            pstmt.setString(4, like);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BoardSearchDTO dto = new BoardSearchDTO();
                    dto.setType(rs.getString("type"));
                    dto.setId(rs.getInt("id"));
                    dto.setNo(rs.getString("no"));
                    dto.setTitle(rs.getString("title"));
                    dto.setRegDate(rs.getDate("reg_date"));
                    list.add(dto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
