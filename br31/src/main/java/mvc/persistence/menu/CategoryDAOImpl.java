package mvc.persistence.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.domain.menu.CategoryDTO;

public class CategoryDAOImpl implements CategoryDAO{
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private CategoryDTO vo=null;
	
	// 1. 생성자 DI 
	public CategoryDAOImpl() {
		super(); 
	} 
	public CategoryDAOImpl(Connection conn) {
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
	public List<CategoryDTO> select() throws SQLException {
		String sql =
		        "SELECT * " +
		        "FROM \"category\" " +
		        "ORDER BY \"category_id\"";


		
		ArrayList<CategoryDTO> list = null;
		

		int category_id;
		String category_name;

		try {			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				list=new ArrayList<CategoryDTO>();
				// seq, title, writer, email, writedate, readed
				
				do {
					System.out.println("진입");
					category_id = rs.getInt("category_id");
					category_name=rs.getString("category_name");

					vo = new CategoryDTO().builder()
							.category_id(category_id)
							.category_name(category_name)
							.build();
					list.add(vo);
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

}
