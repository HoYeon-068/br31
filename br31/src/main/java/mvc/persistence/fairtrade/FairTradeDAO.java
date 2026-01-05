package mvc.persistence.fairtrade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.util.ConnectionProvider;
import com.util.JdbcUtil;
import mvc.domain.fairtrade.FairTradeDTO;

public class FairTradeDAO {

    // 목록
    public List<FairTradeDTO> selectList() {

        List<FairTradeDTO> list = new ArrayList<>();
        String sql =
            "SELECT notice_id, notice_no, title, content, reg_date " +
            "FROM fair_trade " +
            "ORDER BY notice_no DESC";

        try (
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                FairTradeDTO dto = new FairTradeDTO();
                dto.setNoticeId(rs.getInt("notice_id"));
                dto.setNoticeNo(rs.getString("notice_no"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setRegDate(rs.getDate("reg_date"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 상세
    public FairTradeDTO selectOne(int noticeId) {

        FairTradeDTO dto = null;
        String sql =
            "SELECT notice_id, notice_no, title, content, reg_date " +
            "FROM fair_trade WHERE notice_id = ?";

        try (
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, noticeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    dto = new FairTradeDTO();
                    dto.setNoticeId(rs.getInt("notice_id"));
                    dto.setNoticeNo(rs.getString("notice_no"));
                    dto.setTitle(rs.getString("title"));
                    dto.setContent(rs.getString("content"));
                    dto.setRegDate(rs.getDate("reg_date"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
}
