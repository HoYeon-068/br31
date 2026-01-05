package mvc.persistence.story;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.domain.story.CampaignInfoDTO;

public class CampaignInfoDAOImpl implements CampaignInfoDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public CampaignInfoDAOImpl() {}

    public CampaignInfoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<CampaignInfoDTO> campaignInfoAll() {
        String sql =
            " SELECT c.\"campaign_id\", c.\"campaign_name\", " +
            "        cai.\"campaign_activity_title\", cai.\"campaign_activity_ym\", cai.\"campaign_activity_description\", cai.\"campaign_activity_img_path\" " +
            " FROM \"campaign_activity_info\" cai " +
            " JOIN \"campaign\" c ON c.\"campaign_id\" = cai.\"campaign_id\" " +
            " ORDER BY c.\"campaign_id\", cai.\"campaign_activity_ym\" ";

        List<CampaignInfoDTO> list = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CampaignInfoDTO dto = new CampaignInfoDTO();
                dto.setCampaign_id(rs.getInt("campaign_id"));
                dto.setCampaign_name(rs.getString("campaign_name"));
                dto.setCampaign_activity_title(rs.getString("campaign_activity_title"));
                dto.setCampaign_activity_ym(rs.getString("campaign_activity_ym"));
                dto.setCampaign_activity_description(rs.getString("campaign_activity_description"));
                dto.setCampaign_activity_img_path(rs.getString("campaign_activity_img_path"));
                list.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (pstmt != null) pstmt.close(); } catch (Exception ignore) {}
        }

        return list;
    }
}
