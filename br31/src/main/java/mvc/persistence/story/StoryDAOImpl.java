package mvc.persistence.story;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.domain.story.StoryDTO;

public class StoryDAOImpl implements StoryDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StoryDTO vo = null;

	// 1. 생성자 DI 
	public StoryDAOImpl() {
		super(); 
	} 
	public StoryDAOImpl(Connection conn) {
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
	public List<StoryDTO> storyList() {
		String sql = " SELECT \"story_year\", \"story_logo_img_path\", \"story_title\", \"story_description\", \"story_img_path\" "
				+ " FROM \"story\" "
				+ " ORDER BY \"story_year\" ";
		
		ArrayList<StoryDTO> list = null;
		
		int story_year;
		String story_logo_img_path, story_title, story_description, story_img_path ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList<StoryDTO>();
				do {
					story_year = rs.getInt("story_year");
					story_logo_img_path = rs.getString("story_logo_img_path");
					story_title = rs.getString("story_title");
					story_description = rs.getString("story_description");
					story_img_path = rs.getString("story_img_path");
					
					vo = new StoryDTO().builder()
								.story_year(story_year)
								.story_logo_img_path(story_logo_img_path)
								.story_title(story_title)
								.story_description(story_description)
								.story_img_path(story_img_path)
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
