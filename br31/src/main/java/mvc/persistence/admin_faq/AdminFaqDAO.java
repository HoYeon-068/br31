package mvc.persistence.admin_faq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import mvc.domain.admin_faq.AdminFaqDTO;

public class AdminFaqDAO {

    /* FAQ 전체 목록 조회 */
    public List<AdminFaqDTO> selectList()
            throws SQLException, NamingException {

        List<AdminFaqDTO> list = new ArrayList<>();

        String sql =
            "SELECT faq_id, question, answer, reg_date, faq_category_id " +
            "FROM faq " +
            "ORDER BY faq_id DESC";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionProvider.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                AdminFaqDTO dto = new AdminFaqDTO();

                dto.setFaqId(rs.getLong("faq_id"));
                dto.setQuestion(rs.getString("question"));
                dto.setAnswer(rs.getString("answer"));
                dto.setRegDate(rs.getDate("reg_date"));
                dto.setFaqCategoryId(rs.getLong("faq_category_id"));

                list.add(dto);
            }

        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
            JdbcUtil.close(conn);
        }

        return list;
    }

    /* FAQ 단건 조회 */
    public AdminFaqDTO selectOne(Long faqId)
            throws SQLException, NamingException {

        AdminFaqDTO dto = null;

        String sql =
            "SELECT faq_id, question, answer, reg_date, faq_category_id " +
            "FROM faq " +
            "WHERE faq_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionProvider.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, faqId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                dto = new AdminFaqDTO();
                dto.setFaqId(rs.getLong("faq_id"));
                dto.setQuestion(rs.getString("question"));
                dto.setAnswer(rs.getString("answer"));
                dto.setRegDate(rs.getDate("reg_date"));
                dto.setFaqCategoryId(rs.getLong("faq_category_id"));
            }

        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
            JdbcUtil.close(conn);
        }

        return dto;
    }

    /* FAQ 등록 */
    public int insert(AdminFaqDTO dto)
            throws SQLException, NamingException {

        String sql =
            "INSERT INTO faq (faq_id, question, answer, reg_date, faq_category_id) " +
            "VALUES (faq_seq.NEXTVAL, ?, ?, SYSDATE, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionProvider.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, dto.getQuestion());
            pstmt.setString(2, dto.getAnswer());
            pstmt.setLong(3, dto.getFaqCategoryId());

            return pstmt.executeUpdate();

        } finally {
            JdbcUtil.close(pstmt);
            JdbcUtil.close(conn);
        }
    }

    /* FAQ 수정 (카테고리 제외) */
    public int update(AdminFaqDTO dto)
            throws SQLException, NamingException {

        String sql =
            "UPDATE faq " +
            "SET question = ?, answer = ? " +
            "WHERE faq_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionProvider.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, dto.getQuestion());
            pstmt.setString(2, dto.getAnswer());
            pstmt.setLong(3, dto.getFaqId());

            return pstmt.executeUpdate();

        } finally {
            JdbcUtil.close(pstmt);
            JdbcUtil.close(conn);
        }
    }

    /* FAQ 삭제 */
    public int delete(Long faqId)
            throws SQLException, NamingException {

        String sql = "DELETE FROM faq WHERE faq_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionProvider.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, faqId);

            return pstmt.executeUpdate();

        } finally {
            JdbcUtil.close(pstmt);
            JdbcUtil.close(conn);
        }
    }
}
