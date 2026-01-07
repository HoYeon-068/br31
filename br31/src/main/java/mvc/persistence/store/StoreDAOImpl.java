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
	public String select(
	        String[] service_info,   // 서비스 체크박스 (AND 조건)
	        String store_name,        // 매장명 검색
	        String sido,              // 시/도
	        String sigungu,           // 시/군/구
	        String[] store_type       // 매장 타입 체크박스 (IN 조건)
	) throws SQLException {

	    System.out.println("진입 셀렉트");

	    StringBuilder sql = new StringBuilder();

	    sql.append(
	        "SELECT * FROM ( " +
	        "  SELECT s.* " +
	        "  FROM \"store\" s "
	    );

	    boolean hasWhere = false;

	    // 🔹 서비스 AND 조건
	    if (service_info != null && service_info.length > 0) {
	        sql.append(
	            " WHERE s.\"store_id\" IN ( " +
	            "   SELECT ss.\"store_id\" " +
	            "   FROM \"store_services\" ss " +
	            "   JOIN \"services\" sv ON ss.\"services_id\" = sv.\"services_id\" " +
	            "   WHERE sv.\"service_name\" IN ("
	        );

	        for (int i = 0; i < service_info.length; i++) {
	            sql.append("?");
	            if (i < service_info.length - 1) sql.append(",");
	        }

	        sql.append(
	            ") " +
	            "   GROUP BY ss.\"store_id\" " +
	            "   HAVING COUNT(DISTINCT sv.\"service_name\") = ? " +
	            " ) "
	        );

	        hasWhere = true;
	    }

	    // 🔹 매장명 검색
	    if (store_name != null && !store_name.trim().isEmpty()) {
	        sql.append(hasWhere ? " AND " : " WHERE ");
	        sql.append(" s.\"store_name\" LIKE ? ");
	        hasWhere = true;
	    }

	    // 🔹 시/도 검색
	    if (sido != null && !sido.trim().isEmpty()) {
	        sql.append(hasWhere ? " AND " : " WHERE ");
	        sql.append(" s.\"sido\" LIKE ? ");
	        hasWhere = true;
	    }

	    // 🔹 시/군/구 검색
	    if (sigungu != null && !sigungu.trim().isEmpty()) {
	        sql.append(hasWhere ? " AND " : " WHERE ");
	        sql.append(" s.\"sigungu\" LIKE ? ");
	        hasWhere = true;
	    }

	    // 🔹 매장 타입 체크박스 (OR 조건)
	    if (store_type != null && store_type.length > 0) {
	        sql.append(hasWhere ? " AND " : " WHERE ");
	        sql.append(" s.\"store_type\" IN (");

	        for (int i = 0; i < store_type.length; i++) {
	            sql.append("?");
	            if (i < store_type.length - 1) sql.append(",");
	        }

	        sql.append(") ");
	        hasWhere = true;
	    }

	    // 🔹 정렬 + 100개 제한
	    sql.append(
	        " ORDER BY s.\"store_id\" " +
	        ") WHERE ROWNUM <= 100"
	    );

	    StringBuilder json = new StringBuilder();
	    json.append("[");

	    try {
	        pstmt = conn.prepareStatement(sql.toString());

	        int idx = 1;

	        // 🔹 서비스 바인딩
	        if (service_info != null && service_info.length > 0) {
	            for (String service : service_info) {
	                pstmt.setString(idx++, service);
	            }
	            pstmt.setInt(idx++, service_info.length);
	        }

	        // 🔹 매장명 바인딩
	        if (store_name != null && !store_name.trim().isEmpty()) {
	            pstmt.setString(idx++, "%" + store_name.trim() + "%");
	        }

	        // 🔹 시/도 바인딩
	        if (sido != null && !sido.trim().isEmpty()) {
	            pstmt.setString(idx++, "%" + sido.trim() + "%");
	        }

	        // 🔹 시/군/구 바인딩
	        if (sigungu != null && !sigungu.trim().isEmpty()) {
	            pstmt.setString(idx++, "%" + sigungu.trim() + "%");
	        }

	        // 🔹 매장 타입 바인딩
	        if (store_type != null && store_type.length > 0) {
	            for (String type : store_type) {
	                pstmt.setString(idx++, type);
	            }
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
