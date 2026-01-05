package mvc.persistence.plaza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.domain.plaza.ConsultingDTO;
import mvc.domain.plaza.PlazaSelectDTO;
import mvc.domain.plaza.PlazaViewDTO;

public class PlazaDAOImpl implements PlazaDAO{

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private PlazaSelectDTO psdto = null;
	private PlazaViewDTO pvdto = null;
	
	
	
	
	public PlazaDAOImpl() {
		super();
	}
	
	public PlazaDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}




	@Override
	public List<PlazaSelectDTO> select() {
		String sql = " SELECT \"plaza_id\" ,\"title\", \"plaza_category_id\" ,\"content\", SUBSTR(\"user_id\", 1, 3) || RPAD('*', LENGTH(\"user_id\") - 3, '*') AS masked_user_id "
				+ " FROM \"plaza\"";
		
		ArrayList<PlazaSelectDTO> list = new ArrayList<>();
		
		int plazaId, plazaCategoryId;
		String title, content, userId;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<PlazaSelectDTO>();
				do {
					plazaId = rs.getInt("plaza_id");
					title = rs.getString("title");
					content = rs.getString("content");
					userId = rs.getString("masked_user_id");
					plazaCategoryId = rs.getInt("plaza_category_id");
					
					psdto = new PlazaSelectDTO().builder()
							.plazaId(plazaId)
							.title(title)
							.content(content)
							.userId(userId)
							.plazaCategoryId(plazaCategoryId)
							.build();
					
					list.add(psdto);
				} while (rs.next());
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			    if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<PlazaSelectDTO> select(String categoryId) {
		
		String sql = "SELECT \"plaza_id\" ,\"title\", \"plaza_category_id\" ,\"content\", SUBSTR(\"user_id\", 1, 3) || RPAD('*', LENGTH(\"user_id\") - 3, '*') AS masked_user_id "
				+ "FROM \"plaza\" "
				+ "WHERE \"plaza_category_id\" = ?";
		
		ArrayList<PlazaSelectDTO> list = new ArrayList<>();
		
		int plazaId, plazaCategoryId = 0;
		String title, content, userId;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(categoryId));
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				list = new ArrayList<PlazaSelectDTO>();
				do {
					plazaId = rs.getInt("plaza_id");
					title = rs.getString("title");
					content = rs.getString("content");
					userId = rs.getString("masked_user_id");
					plazaCategoryId = rs.getInt("plaza_category_id");
					
					psdto = new PlazaSelectDTO().builder()
							.plazaId(plazaId)
							.title(title)
							.content(content)
							.userId(userId)
							.plazaCategoryId(plazaCategoryId)
							.build();
					
					list.add(psdto);
				} while (rs.next());
			}
		} catch (SQLException e) {
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
	public PlazaViewDTO view(int seq) {
		
		String sql = "SELECT \"name\" ,\"plaza_id\", \"title\", \"content\", \"is_author_public\", \"plaza_category_id\" "
				+ " FROM \"plaza\" p "
				+ " JOIN \"user\" u ON u.\"user_id\" = p.\"user_id\" "
				+ " WHERE \"plaza_id\" = ?";
		
		PlazaViewDTO dto = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = PlazaViewDTO.builder()
						.name(rs.getString("name"))
						.plazaId(rs.getInt("plaza_id"))
						.title(rs.getString("title"))
						.content(rs.getString("content"))
						.isAuthorPublics(rs.getInt("is_author_public"))
						.plazaCategoryId(rs.getInt("plaza_category_id"))
						.build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return dto;
	}

	@Override
	public int insertConsulting(ConsultingDTO dto) {

	    String sql = "INSERT INTO \"consulting\" ( "
	            + "  \"investment_amount\", \"desired_opening_date\", \"store_area\", "
	            + "  \"personal_info_consent\", \"content\", \"name\", \"tel\", \"time\", "
	            + "  \"email\", \"preferred_region\", \"zipcode\", \"address\", \"status\" "
	            + ") VALUES ( "
	            + "  ?, ?, ?, "
	            + "  ?, ?, ?, ?, ?, "
	            + "  ?, ?, ?, ?, 0 "
	            + ")";

	    int rowCount = 0;

	    try {
	        pstmt = conn.prepareStatement(sql);
	        int idx = 1;
	        pstmt.setString(idx++, dto.getInvestmentAmount());
	        pstmt.setString(idx++, dto.getDesiredOpeningDate());
	        pstmt.setString(idx++, dto.getStoreArea());
	        pstmt.setInt(idx++, dto.getPersonalInfoConsent());
	        pstmt.setString(idx++, dto.getContent());
	        pstmt.setString(idx++, dto.getName());
	        pstmt.setString(idx++, dto.getTel());
	        pstmt.setString(idx++, dto.getTime());
	        pstmt.setString(idx++, dto.getEmail());
	        pstmt.setString(idx++, dto.getPreferredRegion());
	        pstmt.setString(idx++, dto.getZipcode());
	        pstmt.setString(idx++, dto.getAddress());

	        rowCount = pstmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return rowCount;
	}


}
