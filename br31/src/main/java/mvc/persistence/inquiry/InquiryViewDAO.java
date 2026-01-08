package mvc.persistence.inquiry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import mvc.domain.inquiry.InquiryViewDTO;

public class InquiryViewDAO {

    public InquiryViewDTO selectOne(Long inquiryId) {

        InquiryViewDTO dto = null;

        String sql =
            "SELECT " +
            " i.\"inquiry_id\", " +
            " i.\"counsel_type\", " +
            " i.\"detail_type\", " +
            " i.\"title\", " +
            " i.\"occur_date\", " +
            " i.\"content\", " +
            " i.\"reg_date\", " +
            " a.\"answer\" " +
            "FROM \"inquiry\" i " +
            "LEFT JOIN \"inquiry_answer\" a " +
            "ON i.\"inquiry_id\" = a.\"inquiry_id\" " +
            "WHERE i.\"inquiry_id\" = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionProvider.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, inquiryId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                dto = new InquiryViewDTO();

                dto.setInquiryId(rs.getLong("inquiry_id"));
                dto.setCounselType(rs.getString("counsel_type"));
                dto.setDetailType(rs.getString("detail_type"));
                dto.setTitle(rs.getString("title"));
                dto.setOccurDate(rs.getDate("occur_date"));
                dto.setContent(rs.getString("content"));
                dto.setRegDate(rs.getDate("reg_date"));
                dto.setAnswer(rs.getString("answer")); // null 가능
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
            JdbcUtil.close(conn);
        }

        return dto;
    }
}
