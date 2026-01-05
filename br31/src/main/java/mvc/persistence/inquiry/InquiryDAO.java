package mvc.persistence.inquiry;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import mvc.domain.inquiry.InquiryDTO;

public class InquiryDAO {

    public int insert(InquiryDTO dto) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String sql =
            "INSERT INTO \"inquiry\" ( " +
            "  \"inquiry_id\", " +
            "  \"counsel_type\", " +
            "  \"detail_type\", " +
            "  \"title\", " +
            "  \"occur_date\", " +
            "  \"content\", " +
            "  \"name\", " +
            "  \"phone\", " +
            "  \"email\", " +
            "  \"post_pw\", " +
            "  \"reg_date\", " +
            "  \"user_id\", " +
            "  \"store_id\" " +
            ") VALUES ( " +
            "  inquiry_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, ? " +
            ")";

        try {
            conn = ConnectionProvider.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, dto.getCounselType());
            pstmt.setString(2, dto.getDetailType());
            pstmt.setString(3, dto.getTitle());
            pstmt.setDate(4, new java.sql.Date(dto.getOccurDate().getTime()));
            pstmt.setString(5, dto.getContent());
            pstmt.setString(6, dto.getName());
            pstmt.setString(7, dto.getPhone());
            pstmt.setString(8, dto.getEmail());
            pstmt.setString(9, dto.getPostPw());
            pstmt.setString(10, dto.getUserId());
            pstmt.setObject(11, dto.getStoreId());

            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(pstmt);
            JdbcUtil.close(conn);
        }

        return result;
    }
}
