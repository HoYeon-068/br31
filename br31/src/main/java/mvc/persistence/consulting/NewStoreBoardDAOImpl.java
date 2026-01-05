package mvc.persistence.consulting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mvc.domain.consulting.NewStoreBoardDTO;

public class NewStoreBoardDAOImpl implements NewStoreBoardDAO{
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private NewStoreBoardDTO vo = null;
	
	// 1. 생성자 DI 
	public NewStoreBoardDAOImpl() {
		super(); 
	} 
	public NewStoreBoardDAOImpl(Connection conn) {
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
	public List<NewStoreBoardDTO> select() throws SQLException {
		String sql = "SELECT * FROM \"new_store_board\"";

		ArrayList<NewStoreBoardDTO> list = null;

		int newStoreBoardId, viewCount, isDeleted;

		String userId, sido, sigungu, marketArea, currentBusiness,
		       floor, area, keyMoney, deposit, description, rentalFee;

		Date createdAt;

		double latitude, longitude;

		
		
		
		try {			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<NewStoreBoardDTO>();
				do {
					
					newStoreBoardId = rs.getInt("new_store_board_id");
					viewCount = rs.getInt("view_count");
					isDeleted = rs.getInt("is_deleted");
					
					userId = rs.getString("user_id");
					sido=rs.getString("sido");
					sigungu=rs.getString("sigungu");
					marketArea=rs.getString("market_area");
					currentBusiness=rs.getString("current_business");
					floor=rs.getString("floor");
					area=rs.getString("area");
					keyMoney=rs.getString("key_money");
					deposit=rs.getString("deposit");
					description=rs.getString("description");
					rentalFee=rs.getString("rental_fee");
					
					createdAt=rs.getDate("created_at");
					
					latitude=rs.getDouble("latitude");
					longitude=rs.getDouble("longitude");

					vo = new NewStoreBoardDTO().builder()
							.newStoreBoardId(newStoreBoardId)
							.viewCount(viewCount)
							.isDeleted(isDeleted)
							.userId(userId)
							.sido(sido)
							.sigungu(sigungu)
							.marketArea(marketArea)
							.currentBusiness(currentBusiness)
							.floor(floor)
							.area(area)
							.keyMoney(keyMoney)
							.deposit(deposit)
							.description(description)
							.rentalFee(rentalFee)
							.createdAt(createdAt)
							.latitude(latitude)
							.longitude(longitude)
							.build();
							

					list.add(vo);
				} while (rs.next());

			}

		} catch (SQLException e) { 
			System.out.println("오류 NewStoreBoardDAO");
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
	public List<NewStoreBoardDTO> select(int currentPage, int numberPerPage, String loc) throws SQLException {
		String sql =
			    "SELECT * FROM ( " +
			    "  SELECT ROWNUM rnum, A.* FROM ( " +
			    "    SELECT * FROM \"new_store_board\" ";
		if(!loc.equals("")){
			sql+=" WHERE \"sido\" = ? ";
		}
			 sql+=   "    ORDER BY \"new_store_board_id\" DESC " +
			    "  ) A WHERE ROWNUM <= ? " +
			    ") WHERE rnum >= ?";
		
		
		int start = (currentPage-1)*numberPerPage + 1;
		int end = start + numberPerPage -1;
		
		ArrayList<NewStoreBoardDTO> list = null;

		int newStoreBoardId, viewCount, isDeleted;

		String userId, sido, sigungu, marketArea, currentBusiness,
		       floor, area, keyMoney, deposit, description, rentalFee;

		Date createdAt;

		double latitude, longitude;

		
		
		
		try {
			int index=1;
			
			pstmt = conn.prepareStatement(sql);
			
			if(!loc.equals("")){
				pstmt.setString(index++, loc);
			}
			
			// ? end
			pstmt.setInt(index++, end);
			
			// ? start
			pstmt.setInt(index++, start);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<NewStoreBoardDTO>();
				do {
					
					newStoreBoardId = rs.getInt("new_store_board_id");
					viewCount = rs.getInt("view_count");
					isDeleted = rs.getInt("is_deleted");
					
					userId = rs.getString("user_id");
					sido=rs.getString("sido");
					sigungu=rs.getString("sigungu");
					marketArea=rs.getString("market_area");
					currentBusiness=rs.getString("current_business");
					floor=rs.getString("floor");
					area=rs.getString("area");
					keyMoney=rs.getString("key_money");
					deposit=rs.getString("deposit");
					description=rs.getString("description");
					rentalFee=rs.getString("rental_fee");
					
					createdAt=rs.getDate("created_at");
					
					latitude=rs.getDouble("latitude");
					longitude=rs.getDouble("longitude");

					vo = new NewStoreBoardDTO().builder()
							.newStoreBoardId(newStoreBoardId)
							.viewCount(viewCount)
							.isDeleted(isDeleted)
							.userId(userId)
							.sido(sido)
							.sigungu(sigungu)
							.marketArea(marketArea)
							.currentBusiness(currentBusiness)
							.floor(floor)
							.area(area)
							.keyMoney(keyMoney)
							.deposit(deposit)
							.description(description)
							.rentalFee(rentalFee)
							.createdAt(createdAt)
							.latitude(latitude)
							.longitude(longitude)
							.build();
							

					list.add(vo);
				} while (rs.next());

			}

		} catch (SQLException e) { 
			System.out.println("오류 NewStoreBoardDAO");
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
	public int getTotalPages(int numberPerPage) throws SQLException {
		String sql = "SELECT CEIL(COUNT(*)/?)"
				+ " FROM \"new_store_board\"";              

		int totalPages = 0;	 

		try {			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, numberPerPage); 
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalPages = rs.getInt(1);
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

		return totalPages;
	}
	@Override
	public NewStoreBoardDTO selectOne(int seq) throws SQLException {
		String sql = "SELECT * FROM \"new_store_board\" WHERE \"new_store_board_id\"= ? ";



		int newStoreBoardId, viewCount, isDeleted;

		String userId, sido, sigungu, marketArea, currentBusiness,
		       floor, area, keyMoney, deposit, description, rentalFee;

		Date createdAt;

		double latitude, longitude;

		
		
		
		try {			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,seq);
			rs = pstmt.executeQuery();

			if (rs.next()) {
					
					newStoreBoardId = rs.getInt("new_store_board_id");
					viewCount = rs.getInt("view_count");
					isDeleted = rs.getInt("is_deleted");
					
					userId = rs.getString("user_id");
					sido=rs.getString("sido");
					sigungu=rs.getString("sigungu");
					marketArea=rs.getString("market_area");
					currentBusiness=rs.getString("current_business");
					floor=rs.getString("floor");
					area=rs.getString("area");
					keyMoney=rs.getString("key_money");
					deposit=rs.getString("deposit");
					description=rs.getString("description");
					rentalFee=rs.getString("rental_fee");
					
					createdAt=rs.getDate("created_at");
					
					latitude=rs.getDouble("latitude");
					longitude=rs.getDouble("longitude");

					vo = new NewStoreBoardDTO().builder()
							.newStoreBoardId(newStoreBoardId)
							.viewCount(viewCount)
							.isDeleted(isDeleted)
							.userId(userId)
							.sido(sido)
							.sigungu(sigungu)
							.marketArea(marketArea)
							.currentBusiness(currentBusiness)
							.floor(floor)
							.area(area)
							.keyMoney(keyMoney)
							.deposit(deposit)
							.description(description)
							.rentalFee(rentalFee)
							.createdAt(createdAt)
							.latitude(latitude)
							.longitude(longitude)
							.build();
							


			}

		} catch (SQLException e) { 
			System.out.println("오류 NewStoreBoardDAO");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		} 

		return vo;
	}
	@Override
	public int getTotalPages(int numberPerPage, String loc) throws SQLException {
		String sql = "SELECT CEIL(COUNT(*)/?)"
				+ " FROM \"new_store_board\""
				+ " WHERE \"sido\"= ? ";              

		int totalPages = 0;	 

		try {			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, numberPerPage); 
			pstmt.setString(2, loc);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalPages = rs.getInt(1);
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

		return totalPages;
	}
	@Override
	public int getTotalNum(String loc) throws SQLException {
		String sql = "SELECT COUNT(*)"
				+ " FROM \"new_store_board\""
				+ " WHERE \"sido\"= ? ";              

		int totalNum = 0;	 

		try {			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, loc);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalNum = rs.getInt(1);
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

		return totalNum;
	}
	@Override
	public int getTotalNum() throws SQLException {
		String sql = "SELECT COUNT(*)"
				+ " FROM \"new_store_board\"";              

		int totalNum = 0;	 

		try {			
			pstmt = conn.prepareStatement(sql); 
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalNum = rs.getInt(1);
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

		return totalNum;
	}
	

}
