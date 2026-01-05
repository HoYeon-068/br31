package mvc.persistence.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	@Override
	public String select(String[] service_info) throws SQLException {
	    System.out.println("진입 셀렉트");

	    StringBuilder sql = new StringBuilder();

	    sql.append(
	        "SELECT * FROM ( " +
	        "  SELECT " +
	        "    s.\"store_id\", " +
	        "    s.\"manager_id\", " +
	        "    s.\"store_name\", " +
	        "    s.\"store_type\", " +
	        "    s.\"store_tel\", " +
	        "    s.\"business_hours\", " +
	        "    s.\"store_status\", " +
	        "    s.\"sido\", " +
	        "    s.\"sigungu\", " +
	        "    s.\"street\", " +
	        "    s.\"address_detail\", " +
	        "    s.\"open_date\", " +
	        "    s.\"latitude\", " +
	        "    s.\"longitude\" " +
	        "  FROM \"store\" s " +
	        "  LEFT JOIN \"store_services\" ss ON s.\"store_id\" = ss.\"store_id\" " +
	        "  LEFT JOIN \"services\" sv ON ss.\"services_id\" = sv.\"services_id\" "
	    );

	    // 🔹 서비스 AND 조건
	    if (service_info != null && service_info.length > 0) {
	        sql.append(" WHERE sv.\"service_name\" IN (");
	        for (int i = 0; i < service_info.length; i++) {
	            sql.append("?");
	            if (i < service_info.length - 1) sql.append(",");
	        }
	        sql.append(") ");

	        sql.append(
	            " GROUP BY " +
	            " s.\"store_id\", s.\"manager_id\", s.\"store_name\", s.\"store_type\", " +
	            " s.\"store_tel\", s.\"business_hours\", s.\"store_status\", " +
	            " s.\"sido\", s.\"sigungu\", s.\"street\", s.\"address_detail\", " +
	            " s.\"open_date\", s.\"latitude\", s.\"longitude\" " +
	            " HAVING COUNT(DISTINCT sv.\"service_name\") = ? "
	        );
	    } else {
	        sql.append(
	            " GROUP BY " +
	            " s.\"store_id\", s.\"manager_id\", s.\"store_name\", s.\"store_type\", " +
	            " s.\"store_tel\", s.\"business_hours\", s.\"store_status\", " +
	            " s.\"sido\", s.\"sigungu\", s.\"street\", s.\"address_detail\", " +
	            " s.\"open_date\", s.\"latitude\", s.\"longitude\" "
	        );
	    }

	    sql.append(
	        " ORDER BY s.\"store_id\" " +
	        ") WHERE ROWNUM <= 100"
	    );

	    StringBuilder json = new StringBuilder();
	    json.append("[");

	    try {
	        pstmt = conn.prepareStatement(sql.toString());

	        // 🔹 바인딩
	        int idx = 1;
	        if (service_info != null && service_info.length > 0) {
	            for (String service : service_info) {
	                pstmt.setString(idx++, service);
	            }
	            pstmt.setInt(idx, service_info.length); // HAVING COUNT
	        }

	        rs = pstmt.executeQuery();
	        System.out.println("실행");

	        boolean first = true;

	        while (rs.next()) {
	            if (!first) json.append(",");
	            first = false;

	            json.append("{");

	            json.append("\"storeId\":").append(rs.getInt("store_id")).append(",");
	            json.append("\"managerId\":").append(rs.getInt("manager_id")).append(",");
	            json.append("\"storeName\":\"").append(safe(rs.getString("store_name"))).append("\",");
	            json.append("\"storeType\":\"").append(safe(rs.getString("store_type"))).append("\",");
	            json.append("\"storeTel\":\"").append(safe(rs.getString("store_tel"))).append("\",");
	            json.append("\"businessHours\":\"").append(safe(rs.getString("business_hours"))).append("\",");
	            json.append("\"storeStatus\":\"").append(safe(rs.getString("store_status"))).append("\",");
	            json.append("\"sido\":\"").append(safe(rs.getString("sido"))).append("\",");
	            json.append("\"sigungu\":\"").append(safe(rs.getString("sigungu"))).append("\",");
	            json.append("\"street\":\"").append(safe(rs.getString("street"))).append("\",");
	            json.append("\"addressDetail\":\"").append(safe(rs.getString("address_detail"))).append("\",");

	            if (rs.getDate("open_date") != null) {
	                json.append("\"openDate\":\"").append(rs.getDate("open_date")).append("\",");
	            } else {
	                json.append("\"openDate\":\"\",");
	            }

	            json.append("\"latitude\":").append(rs.getDouble("latitude")).append(",");
	            json.append("\"longitude\":").append(rs.getDouble("longitude"));

	            json.append("}");
	        }

	        json.append("]");

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "[]";
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	    }

	    return json.toString();
	}
	private String safe(String s) {
	    return s == null ? "" : s.replace("\"", "\\\"");
	}


	
}
