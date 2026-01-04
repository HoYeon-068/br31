package mvc.persistence.story;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.domain.story.CampaignDTO;

public class CampaignDAOImpl implements CampaignDAO{

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private CampaignDTO vo = null;

	// 1. 생성자 DI 
	public CampaignDAOImpl() {
		super(); 
	} 
	public CampaignDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	// 2. Setter DI
	public void setConn(Connection conn) {
		this.conn = conn;
	} 
	public Connection getConn() {
		return conn;
	}
	
	@Override
	public List<CampaignDTO> campaignList() {
		
		
		String sql = " SELECT \"campaign_id\", \"campaign_name\", \"campaign_description\", \"campaign_summary\", \"campaign_img_path\", \"campaign_subtitle\" "
		           + " FROM \"campaign\" "
		           + " ORDER BY \"campaign_id\" ";

		
		List<CampaignDTO> list = new ArrayList<>();
		
		String campaign_name, campaign_description, campaign_summary, campaign_img_path, campaign_subtitle;
		int campaign_id;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList<CampaignDTO>();
				do {
					campaign_name = rs.getString("campaign_name");
					campaign_description = rs.getString("campaign_description");
					campaign_summary = rs.getString("campaign_summary");
					campaign_img_path = rs.getString("campaign_img_path");
					campaign_subtitle = rs.getString("campaign_subtitle");
					campaign_id = rs.getInt("campaign_id");
					
					vo = new CampaignDTO().builder()
								.campaign_id(campaign_id)
								.campaign_name(campaign_name)
								.campaign_description(campaign_description)
								.campaign_summary(campaign_summary)
								.campaign_img_path(campaign_img_path)
								.campaign_subtitle(campaign_subtitle)
								.build();
					
					list.add(vo);
					
				} while (rs.next());
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

}
