package mvc.persistence.inquiry;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;

import mvc.domain.inquiry.InquiryCreateDTO;

public class InquiryCreateDAO {

	public int insert(InquiryCreateDTO dto) {

	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    int result = 0;

	    String sql =
	        "INSERT INTO \"inquiry\" ( " +
	        "  \"inquiry_id\", \"counsel_type\", \"detail_type\", \"title\", " +
	        "  \"occur_date\", \"content\", \"name\", \"phone\", \"email\", " +
	        "  \"post_pw\", \"reg_date\", \"user_id\", \"store_id\" " +
	        ") VALUES ( " +
	        "  inquiry_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, ? " +
	        ")";

	    try {
	        conn = ConnectionProvider.getConnection();
	        pstmt = conn.prepareStatement(sql);

	        pstmt.setString(1, dto.getCounselType());
	        pstmt.setString(2, dto.getDetailType());
	        pstmt.setString(3, dto.getTitle());

	        if (dto.getOccurDate() != null) {
	            pstmt.setDate(4, new java.sql.Date(dto.getOccurDate().getTime()));
	        } else {
	            pstmt.setNull(4, java.sql.Types.DATE);
	        }

	        pstmt.setString(5, dto.getContent());
	        pstmt.setString(6, dto.getName());

	        if (dto.getPhone() != null) {
	            pstmt.setString(7, dto.getPhone());
	        } else {
	            pstmt.setNull(7, java.sql.Types.VARCHAR);
	        }

	        if (dto.getEmail() != null) {
	            pstmt.setString(8, dto.getEmail());
	        } else {
	            pstmt.setNull(8, java.sql.Types.VARCHAR);
	        }

	        pstmt.setString(9, dto.getPostPw());
	        pstmt.setString(10, dto.getUserId());

	        if (dto.getStoreId() != null) {
	            pstmt.setLong(11, dto.getStoreId());
	        } else {
	            pstmt.setNull(11, java.sql.Types.NUMERIC);
	        }

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
