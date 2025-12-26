package mvc.persistence.event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mvc.domain.event.EventDTO;

public class EventDAOImpl implements EventDAO{
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private EventDTO dto = null;
	
	// 생성자
	public EventDAOImpl() {
		super();
	}
	
	public EventDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	
	// get/set
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<EventDTO> select() {
		String sql = "SELECT \"event_id\", \"title\", \"start_date\", \"end_date\", \"img_path\", \"evt_category_id\" "
				+ "FROM \"event\" "
				+ "ORDER BY "
				+ "    CASE "
				+ "        WHEN \"start_date\" IS NULL AND \"end_date\" IS NULL THEN 1 "
				+ "        ELSE 0 "
				+ "    END, "
				+ "    \"start_date\" DESC";
		
		ArrayList<EventDTO> list = new ArrayList<>();
		
		int eventId, evtCategoryId;
		String title, imgPath;
		Date startDate, endDate;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<EventDTO>();
				do {
					eventId = rs.getInt("event_id");
					title = rs.getString("title");
					startDate = rs.getDate("start_date");
					endDate = rs.getDate("end_date");
					imgPath = rs.getString("img_path");
					evtCategoryId = rs.getInt("evt_category_id");
					
					dto = new EventDTO().builder()
							.eventId(eventId)
							.title(title)
							.startDate(startDate)
							.endDate(endDate)
							.imgPath(imgPath)
							.evtCategoryId(evtCategoryId)
							.build();
					
					list.add(dto);
					
					
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
	public List<EventDTO> select(String category) {
		String sql = "SELECT \"event_id\", \"title\", \"start_date\", \"end_date\", \"img_path\", \"evt_category_id\" "
				+ "FROM \"event\" "
				+ "WHERE \"evt_category_id\" = ? "
				+ "ORDER BY "
				+ "    CASE "
				+ "        WHEN \"start_date\" IS NULL AND \"end_date\" IS NULL THEN 1 "
				+ "        ELSE 0 "
				+ "    END, "
				+ "    \"start_date\" DESC";
		
		ArrayList<EventDTO> list = new ArrayList<>();
		
		int eventId, evtCategoryId;
		String title, imgPath;
		Date startDate, endDate;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(category));
			System.out.println(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<EventDTO>();
				do {
					eventId = rs.getInt("event_id");
					title = rs.getString("title");
					startDate = rs.getDate("start_date");
					endDate = rs.getDate("end_date");
					imgPath = rs.getString("img_path");
					evtCategoryId = rs.getInt("evt_category_id");
					
					dto = new EventDTO().builder()
							.eventId(eventId)
							.title(title)
							.startDate(startDate)
							.endDate(endDate)
							.imgPath(imgPath)
							.evtCategoryId(evtCategoryId)
							.build();
					
					list.add(dto);
					
					
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
	public EventDTO view(int seq) {

	    // 1) 이벤트 상세
	    String sql1 =
	        "SELECT " +
	        "  e.\"event_id\", e.\"evt_category_id\", c.\"category_name\", " +
	        "  e.\"title\", e.\"start_date\", e.\"end_date\", " +
	        "  e.\"img_path\", e.\"event_detail_img\", " +
	        "  e.\"notice_title\", e.\"notice_content\", e.\"store_scope\" " +
	        "FROM \"event\" e " +
	        "JOIN \"event_category\" c ON e.\"evt_category_id\" = c.\"evt_category_id\" " +
	        "WHERE e.\"event_id\" = ?";

	    // 2) 매장 리스트
	    String sql2 =
	        "SELECT s.\"store_name\" " +
	        "FROM \"event_store\" es " +
	        "JOIN \"store\" s ON es.\"store_id\" = s.\"store_id\" " +
	        "WHERE es.\"event_id\" = ? " +
	        "ORDER BY s.\"store_name\"";

	    EventDTO dto = null;

	    try {
	        // 이벤트 상세
	        pstmt = conn.prepareStatement(sql1);
	        pstmt.setInt(1, seq);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            dto = EventDTO.builder()
	                    .eventId(rs.getInt("event_id"))
	                    .evtCategoryId(rs.getInt("evt_category_id"))
	                    .categoryName(rs.getString("category_name"))
	                    .title(rs.getString("title"))
	                    .startDate(rs.getDate("start_date"))
	                    .endDate(rs.getDate("end_date"))
	                    .imgPath(rs.getString("img_path"))
	                    .eventDetailImg(rs.getString("event_detail_img"))
	                    .noticeTitle(rs.getString("notice_title"))
	                    .noticeContent(rs.getString("notice_content"))
	                    .storeScope(rs.getString("store_scope")) 
	                    .build();
	        }

	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();

	        // 매장 리스트: dto가 있고, store_scope가 NONE이 아닐 때
	        if (dto != null && dto.getStoreScope() != null && !"NONE".equals(dto.getStoreScope())) {
	            pstmt = conn.prepareStatement(sql2);
	            pstmt.setInt(1, seq);
	            rs = pstmt.executeQuery();

	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (SQLException ignore) {}
	        try { if (pstmt != null) pstmt.close(); } catch (SQLException ignore) {}
	    }

	    return dto;
	}


	@Override
	public int insert(EventDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(EventDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int seq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> selectStoreNames(int eventId) {

	    String sql =
	    		"SELECT s.\"store_name\" " +
		        "FROM \"event_store\" es " +
		        "JOIN \"store\" s ON es.\"store_id\" = s.\"store_id\" " +
		        "WHERE es.\"event_id\" = ? " +
		        "ORDER BY s.\"store_name\"";

	    List<String> stores = new ArrayList<>();

	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, eventId);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                stores.add(rs.getString(1)); // store_name
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return stores;
	}

	@Override
	public List<EventDTO> selectOngoing(int currentEventId) {
		String sql =
		        "SELECT * FROM ( " +
		        "  SELECT " +
		        "    e.\"event_id\", e.\"title\", e.\"start_date\", e.\"end_date\", e.\"img_path\", e.\"evt_category_id\" " +
		        "  FROM \"event\" e " +
		        "  WHERE e.\"event_id\" <> ? " +
		        "    AND (e.\"end_date\" IS NULL OR e.\"end_date\" >= TRUNC(SYSDATE)) " +
		        "  ORDER BY CASE WHEN e.\"end_date\" IS NULL THEN 1 ELSE 0 END, e.\"start_date\" DESC " +
		        ") WHERE ROWNUM <= 2";

		    List<EventDTO> list = new ArrayList<>();

		    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setInt(1, currentEventId); // 현재 이벤트값
		        
		        try (ResultSet rs = pstmt.executeQuery()) {
		            while (rs.next()) {
		                EventDTO dto = EventDTO.builder()
		                    .eventId(rs.getInt("event_id"))
		                    .title(rs.getString("title"))
		                    .startDate(rs.getDate("start_date"))
		                    .endDate(rs.getDate("end_date"))
		                    .imgPath(rs.getString("img_path"))
		                    .evtCategoryId(rs.getInt("evt_category_id"))
		                    .build();
		                list.add(dto);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return list;
		}




}
