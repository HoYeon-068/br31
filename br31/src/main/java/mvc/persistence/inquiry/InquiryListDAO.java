package mvc.persistence.inquiry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.ConnectionProvider;
import mvc.domain.inquiry.InquiryListDTO;

public class InquiryListDAO {

    public List<InquiryListDTO> selectList(String userId) throws Exception {

        List<InquiryListDTO> list = new ArrayList<>();

        String sql =
        	    "SELECT " +
        	    " i.\"inquiry_id\", " +
        	    " i.\"reg_date\", " +
        	    " i.\"occur_date\", " +
        	    " i.\"counsel_type\", " +
        	    " i.\"detail_type\", " +
        	    " i.\"title\", " +
        	    " a.\"answer\" " +                     // ✅ 답변만 조회
        	    "FROM \"inquiry\" i " +
        	    "LEFT JOIN \"inquiry_answer\" a " +
        	    "ON i.\"inquiry_id\" = a.\"inquiry_id\" " +
        	    "WHERE i.\"user_id\" = ? " +
        	    "ORDER BY i.\"reg_date\" DESC";


        try (
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                	InquiryListDTO dto = new InquiryListDTO();
                	dto.setInquiryId(rs.getLong("inquiry_id"));
                	dto.setRegDate(rs.getTimestamp("reg_date"));
                	dto.setOccurDate(rs.getTimestamp("occur_date"));
                	dto.setCounselType(rs.getString("counsel_type"));
                	dto.setDetailType(rs.getString("detail_type"));
                	dto.setTitle(rs.getString("title"));
                	dto.setAnswer(rs.getString("answer"));    // ✅ 핵심
                	list.add(dto);

                }
            }
        }

        return list;
    }
}
