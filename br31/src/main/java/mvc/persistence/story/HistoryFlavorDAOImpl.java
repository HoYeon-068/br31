package mvc.persistence.story;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.domain.story.HistoryFlavorDTO;

public class HistoryFlavorDAOImpl implements HistoryFlavorDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private HistoryFlavorDTO vo = null;

	// 1. 생성자 DI 
	public HistoryFlavorDAOImpl() {
		super(); 
	} 
	public HistoryFlavorDAOImpl(Connection conn) {
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
	public List<HistoryFlavorDTO> listByYear(int year) {
		String sql = " SELECT \"history_img_path\", \"history_ice_name\" "
				+ " FROM \"history_flavor\" "
				+ " WHERE \"history_year\" = ? "
				+ " ORDER BY \"history_year\", \"history_month\" ";
		
		List<HistoryFlavorDTO> list = new ArrayList<>();
		
		String  history_img_path, history_ice_name;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, year);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList<HistoryFlavorDTO>();
				do {
					history_img_path = rs.getString("history_img_path");
					history_ice_name = rs.getString("history_ice_name");
					
					
					vo = new HistoryFlavorDTO().builder()
								.history_img_path(history_img_path)
								.history_ice_name(history_ice_name)
								.build();
					
					list.add(vo);
					
				}while(rs.next());
				
			}
			
		} catch (Exception e) {
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
	
	
	@Override
	public List<HistoryFlavorDTO> listByTheme(int themeId) {
		String sql = " SELECT h.\"history_img_path\", h.\"history_ice_name\" "
					+ " FROM \"history_flavor_theme\" hft "
					+ " JOIN \"history_flavor\" h ON hft.\"history_ice_id\" = h.\"history_ice_id\" "
					+ " JOIN \"theme\" t ON hft.\"theme_id\" = t.\"theme_id\" "
					+ " WHERE t.\"theme_id\" = ? "
					+ " ORDER BY h.\"history_year\", h.\"history_month\" ";
		
		List<HistoryFlavorDTO> list = new ArrayList<>();
		
		String history_img_path, history_ice_name;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, themeId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				do {
					history_img_path = rs.getString("history_img_path");
					history_ice_name = rs.getString("history_ice_name");
					
					vo = new HistoryFlavorDTO().builder()
							.history_img_path(history_img_path)
							.history_ice_name(history_ice_name)
							.build();
					list.add(vo);
				}while(rs.next());
			}
			
		} catch (Exception e) {
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
