package mvc.persistence.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import mvc.domain.consulting.BizFaqDTO;
import mvc.domain.store.StoreDTO;

public class StoreDAOImpl implements StoreDAO{
	
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StoreDTO vo = null;
	
	// 1. 생성자 DI 
	public StoreDAOImpl() {
		super(); 
	} 
	public StoreDAOImpl(Connection conn) {
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
	public String select() throws SQLException {

	    String sql =
	        "SELECT * " +
	        "FROM ( " +
	        "    SELECT * " +
	        "    FROM \"store\" " +
	        "    ORDER BY \"store_id\" " +
	        ") " +
	        "WHERE ROWNUM <= 100";

	    StringBuilder json = new StringBuilder();
	    json.append("[");

	    try {
	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        
	        System.out.println("실행");
	        boolean first = true;
	        

	        while (rs.next()) {
	        	
	            if (!first) {
	                json.append(",");
	            }
	            first = false;

	            json.append("{");

	            json.append("\"storeId\":").append(rs.getInt("store_id")).append(",");
	            json.append("\"managerId\":").append(rs.getInt("manager_id")).append(",");
	            json.append("\"storeName\":\"").append(rs.getString("store_name")).append("\",");
	            json.append("\"storeType\":\"").append(rs.getString("store_type")).append("\",");
	            json.append("\"storeTel\":\"").append(rs.getString("store_tel")).append("\",");
	            json.append("\"businessHours\":\"").append(rs.getString("business_hours")).append("\",");
	            json.append("\"storeStatus\":\"").append(rs.getString("store_status")).append("\",");
	            json.append("\"sido\":\"").append(rs.getString("sido")).append("\",");
	            json.append("\"sigungu\":\"").append(rs.getString("sigungu")).append("\",");
	            json.append("\"street\":\"").append(rs.getString("street")).append("\",");
	            json.append("\"addressDetail\":\"").append(rs.getString("address_detail")).append("\",");

	            json.append("\"openDate\":\"").append(rs.getDate("open_date")).append("\",");

	            json.append("\"latitude\":").append(rs.getDouble("latitude")).append(",");
	            json.append("\"longitude\":").append(rs.getDouble("longitude"));

	            json.append("}");
	        }

	        json.append("]");

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "[]";   // 오류 시 빈 JSON 배열
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	    }
	    
	    return json.toString();
	}
	
}
