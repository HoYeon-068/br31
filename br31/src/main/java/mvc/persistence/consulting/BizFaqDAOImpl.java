package mvc.persistence.consulting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mvc.domain.consulting.BizFaqDTO;
import mvc.domain.menu.IngredientDTO;
import mvc.domain.menu.MenuListDTO;
import mvc.domain.menu.MenuViewDTO;
import mvc.domain.menu.ProductDTO;

public class BizFaqDAOImpl implements BizFaqDAO{
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private BizFaqDTO vo = null;
	
	// 1. 생성자 DI 
	public BizFaqDAOImpl() {
		super(); 
	} 
	public BizFaqDAOImpl(Connection conn) {
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
	public List<BizFaqDTO> select() throws SQLException {
		
		String sql = "SELECT * FROM \"biz_faq\"";


		ArrayList<BizFaqDTO> list = null;

		int biz_faq_id;
		String question,answer;
		Date reg_date;
		
		
		
		try {			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<BizFaqDTO>();
				do {
					// seq, title, writer, email, writedate, readed
					biz_faq_id = rs.getInt("biz_faq_id");
					question = rs.getString("question");
					answer=rs.getString("answer");
					reg_date=rs.getDate("reg_date");

					vo = new BizFaqDTO().builder()
							.biz_faq_id(biz_faq_id)
							.question(question)
							.answer(answer)
							.reg_date(reg_date)
							.build();

					list.add(vo);
					System.out.println(question);
				} while (rs.next());

			}

		} catch (SQLException e) { 
			System.out.println("오류 ProductDAO");
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
