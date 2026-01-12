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
        	    " i.\"store_id\", " +                 // ✅ inquiry에 저장된 store_id
        	    " s.\"store_name\" AS store_name, " + // ✅ 표시용(명확히 alias)
        	    " a.\"answer\", " +
        	    " a.\"reg_date\" AS answer_date " +
        	    "FROM \"inquiry\" i " +
        	    "LEFT JOIN \"store\" s " +
        	    " ON i.\"store_id\" = s.\"store_id\" " +
        	    "LEFT JOIN \"inquiry_answer\" a " +
        	    " ON i.\"inquiry_id\" = a.\"inquiry_id\" " +
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

                dto.setStoreId(rs.getLong("store_id"));          // ✅ 핵심: store_id 세팅
                dto.setStoreName(rs.getString("store_name"));    // ✅ 출력용: JOIN 결과 세팅

                dto.setAnswer(rs.getString("answer"));
                dto.setAnswerDate(rs.getDate("answer_date"));
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
