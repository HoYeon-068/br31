package mvc.persistence.plaza;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.domain.plaza.CollaboDTO;
import mvc.domain.plaza.ConsultingDTO;
import mvc.domain.plaza.PlazaSelectDTO;
import mvc.domain.plaza.PlazaViewDTO;
import mvc.domain.plaza.PlazaWriteDTO;

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
		String sql =
			    " SELECT \"plaza_id\", \"title\", \"plaza_category_id\", \"content\", " +
			    "        SUBSTR(\"user_id\", 1, 3) || RPAD('*', LENGTH(\"user_id\") - 3, '*') AS masked_user_id " +
			    " FROM \"plaza\" " +
			    " WHERE \"status\" = 0 " +
			    " ORDER BY \"plaza_id\" DESC";
		
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
							.liked(false)
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
		
		String sql =
			    " SELECT \"plaza_id\", \"title\", \"plaza_category_id\", \"content\", " +
			    "        SUBSTR(\"user_id\", 1, 3) || RPAD('*', LENGTH(\"user_id\") - 3, '*') AS masked_user_id " +
			    " FROM \"plaza\" " +
			    " WHERE \"plaza_category_id\" = ? AND \"status\" = 0 " +
			    " ORDER BY \"plaza_id\" DESC";
		
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
							.liked(false)
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
				+ " WHERE \"plaza_id\" = ? AND p.\"status\" = 0 ";
		
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
						.liked(false)
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

	@Override
	public int insertPlazaReturnId(PlazaWriteDTO dto) {
		 String sql =
			        "BEGIN " +
			        "  INSERT INTO \"plaza\" ( " +
			        "    \"plaza_id\", \"title\", \"content\", \"reg_date\", \"is_author_public\", \"user_id\", " +
			        "    \"personal_info_consent\", \"terms_agreement\", \"plaza_category_id\", \"status\", \"idea_offer_agreement\" " +
			        "  ) VALUES ( " +
			        "    plaza_seq.NEXTVAL, ?, ?, SYSDATE, ?, ?, ?, ?, ?, 0, ? " +
			        "  ) RETURNING \"plaza_id\" INTO ?; " +
			        "END;";

			    int plazaId = 0;
			    CallableStatement cstmt = null;

			    try {
			        cstmt = conn.prepareCall(sql);

			        int idx = 1;
			        cstmt.setString(idx++, dto.getTitle());
			        cstmt.setString(idx++, dto.getContent());
			        cstmt.setInt(idx++, dto.getIsAuthorPublic());
			        cstmt.setString(idx++, dto.getUserId());
			        cstmt.setInt(idx++, dto.getPersonalInfoConsent());
			        cstmt.setInt(idx++, dto.getTermsAgreement());
			        cstmt.setInt(idx++, dto.getPlazaCategoryId());
			        cstmt.setInt(idx++, dto.getIdeaOfferAgreement());

			        cstmt.registerOutParameter(idx, java.sql.Types.NUMERIC);

			        cstmt.execute();

			        plazaId = cstmt.getInt(idx);

			    } catch (SQLException e) {
			        e.printStackTrace();
			    } finally {
			        try {
			            if (cstmt != null) cstmt.close();
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }
			    }

			    return plazaId;
	}

	@Override
	public int insertPlazaFile(int plazaId, String fileName, String origName) {
		String sql =
		        "INSERT INTO \"plaza_file\" (\"plaza_file_id\", \"plaza_id\", \"file_name\", \"orig_name\") " +
		        "VALUES (PLAZA_FILE_SEQ.NEXTVAL, ?, ?, ?)";

		    int rowCount = 0;

		    try {
		        pstmt = conn.prepareStatement(sql);
		        int idx = 1;
		        pstmt.setInt(idx++, plazaId);
		        pstmt.setString(idx++, fileName);
		        pstmt.setString(idx++, origName);

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

	@Override
	public int insertCollabo(CollaboDTO dto) {
		String sql =
		        "INSERT INTO \"collabo\" (\"collabo_id\",\"tel\",\"company_name\",\"company_email\",\"name\",\"plaza_id\") " +
		        "VALUES (collabo_seq.NEXTVAL, ?, ?, ?, ?, ?)";

		    int rowCount = 0;

		    try {
		        pstmt = conn.prepareStatement(sql);
		        int idx = 1;
		        pstmt.setString(idx++, dto.getTel());
		        pstmt.setString(idx++, dto.getCompanyName());
		        pstmt.setString(idx++, dto.getCompanyEmail());
		        pstmt.setString(idx++, dto.getName());
		        pstmt.setInt(idx++, dto.getPlazaId());

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

	@Override
	public boolean existsLike(int plazaId, String userId) {
		String sql = "SELECT 1 FROM \"plaza_like\" WHERE \"plaza_id\"=? AND \"user_id\"=?";
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, plazaId);
	        pstmt.setString(2, userId);
	        rs = pstmt.executeQuery();
	        return rs.next();
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
	    return false;
	}

	@Override
	public int insertLike(int plazaId, String userId) {
		String sql =
		        "INSERT INTO \"plaza_like\" (\"like_id\", \"plaza_id\", \"user_id\") " +
		        "VALUES (PLAZA_LIKE_SEQ.NEXTVAL, ?, ?)";

		    int rowCount = 0;

		    try {
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, plazaId);
		        pstmt.setString(2, userId);

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

	@Override
	public int deleteLike(int plazaId, String userId) {
		String sql = "DELETE FROM \"plaza_like\" WHERE \"plaza_id\"=? AND \"user_id\"=?";

	    int rowCount = 0;

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, plazaId);
	        pstmt.setString(2, userId);

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

	@Override
    public List<PlazaSelectDTO> select(String loginUserId, boolean withLike) {

        boolean useLike = withLike && loginUserId != null && !loginUserId.isBlank();

        String sql;
        if (useLike) {
            sql =
                " SELECT p.\"plaza_id\", p.\"title\", p.\"plaza_category_id\", p.\"content\", " +
                "        SUBSTR(p.\"user_id\", 1, 3) || RPAD('*', LENGTH(p.\"user_id\") - 3, '*') AS masked_user_id, " +
                "        CASE WHEN pl.\"like_id\" IS NULL THEN 0 ELSE 1 END AS liked " +
                " FROM \"plaza\" p " +
                " LEFT JOIN \"plaza_like\" pl " +
                "   ON pl.\"plaza_id\" = p.\"plaza_id\" " +
                "  AND pl.\"user_id\" = ? " +
                " ORDER BY p.\"plaza_id\" DESC";
        } else {
            sql =
                " SELECT p.\"plaza_id\", p.\"title\", p.\"plaza_category_id\", p.\"content\", " +
                "        SUBSTR(p.\"user_id\", 1, 3) || RPAD('*', LENGTH(p.\"user_id\") - 3, '*') AS masked_user_id, " +
                "        0 AS liked " +
                " FROM \"plaza\" p " +
                " ORDER BY p.\"plaza_id\" DESC";
        }

        ArrayList<PlazaSelectDTO> list = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(sql);
            if (useLike) {
                pstmt.setString(1, loginUserId);
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                psdto = PlazaSelectDTO.builder()
                    .plazaId(rs.getInt("plaza_id"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .userId(rs.getString("masked_user_id"))
                    .plazaCategoryId(rs.getInt("plaza_category_id"))
                    .liked(rs.getInt("liked") == 1)
                    .build();

                list.add(psdto);
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
    public List<PlazaSelectDTO> select(String categoryId, String loginUserId, boolean withLike) {

        boolean useLike = withLike && loginUserId != null && !loginUserId.isBlank();

        String sql;
        if (useLike) {
            sql =
                " SELECT p.\"plaza_id\", p.\"title\", p.\"plaza_category_id\", p.\"content\", " +
                "        SUBSTR(p.\"user_id\", 1, 3) || RPAD('*', LENGTH(p.\"user_id\") - 3, '*') AS masked_user_id, " +
                "        CASE WHEN pl.\"like_id\" IS NULL THEN 0 ELSE 1 END AS liked " +
                " FROM \"plaza\" p " +
                " LEFT JOIN \"plaza_like\" pl " +
                "   ON pl.\"plaza_id\" = p.\"plaza_id\" " +
                "  AND pl.\"user_id\" = ? " +
                " WHERE p.\"plaza_category_id\" = ? " +
                " ORDER BY p.\"plaza_id\" DESC";
        } else {
            sql =
                " SELECT p.\"plaza_id\", p.\"title\", p.\"plaza_category_id\", p.\"content\", " +
                "        SUBSTR(p.\"user_id\", 1, 3) || RPAD('*', LENGTH(p.\"user_id\") - 3, '*') AS masked_user_id, " +
                "        0 AS liked " +
                " FROM \"plaza\" p " +
                " WHERE p.\"plaza_category_id\" = ? " +
                " ORDER BY p.\"plaza_id\" DESC";
        }

        ArrayList<PlazaSelectDTO> list = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(sql);

            if (useLike) {
                pstmt.setString(1, loginUserId);
                pstmt.setInt(2, Integer.parseInt(categoryId));
            } else {
                pstmt.setInt(1, Integer.parseInt(categoryId));
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                psdto = PlazaSelectDTO.builder()
                    .plazaId(rs.getInt("plaza_id"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .userId(rs.getString("masked_user_id"))
                    .plazaCategoryId(rs.getInt("plaza_category_id"))
                    .liked(rs.getInt("liked") == 1)
                    .build();

                list.add(psdto);
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
    public PlazaViewDTO view(int seq, String loginUserId, boolean withLike) {

        boolean useLike = withLike && loginUserId != null && !loginUserId.isBlank();

        String sql;
        if (useLike) {
            sql =
                " SELECT u.\"name\", p.\"plaza_id\", p.\"title\", p.\"content\", p.\"is_author_public\", p.\"plaza_category_id\", " +
                "        CASE WHEN pl.\"like_id\" IS NULL THEN 0 ELSE 1 END AS liked " +
                " FROM \"plaza\" p " +
                " JOIN \"user\" u ON u.\"user_id\" = p.\"user_id\" " +
                " LEFT JOIN \"plaza_like\" pl " +
                "   ON pl.\"plaza_id\" = p.\"plaza_id\" " +
                "  AND pl.\"user_id\" = ? " +
                " WHERE p.\"plaza_id\" = ? ";
        } else {
            sql =
                " SELECT u.\"name\", p.\"plaza_id\", p.\"title\", p.\"content\", p.\"is_author_public\", p.\"plaza_category_id\", " +
                "        0 AS liked " +
                " FROM \"plaza\" p " +
                " JOIN \"user\" u ON u.\"user_id\" = p.\"user_id\" " +
                " WHERE p.\"plaza_id\" = ? ";
        }

        PlazaViewDTO dto = null;

        try {
            pstmt = conn.prepareStatement(sql);

            if (useLike) {
                pstmt.setString(1, loginUserId);
                pstmt.setInt(2, seq);
            } else {
                pstmt.setInt(1, seq);
            }

            rs = pstmt.executeQuery();

            if (rs.next()) {
                dto = PlazaViewDTO.builder()
                    .name(rs.getString("name"))
                    .plazaId(rs.getInt("plaza_id"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .isAuthorPublics(rs.getInt("is_author_public"))
                    .plazaCategoryId(rs.getInt("plaza_category_id"))
                    .liked(rs.getInt("liked") == 1)
                    .build();
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

        return dto;
    }

    
    // 관리자페이지
	@Override
	public List<ConsultingDTO> selectAdminList() {
		
		String sql =
		        "SELECT \"consulting_id\", \"name\", \"tel\", \"email\", \"preferred_region\", \"status\" " +
		        "FROM \"consulting\" " +
		        "ORDER BY \"consulting_id\" DESC";
		
		ArrayList<ConsultingDTO> list = new ArrayList<>();
		
		try {
			System.out.println("> Consulting.selectAdminList()..... ");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ConsultingDTO dto = new ConsultingDTO();
				dto.setConsultingId(rs.getInt("consulting_id"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));
				dto.setEmail(rs.getString("email"));
				dto.setPreferredRegion(rs.getString("preferred_region"));
				dto.setStatus(rs.getInt("status"));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("> Consulting.selectAdminList()..... Exception");
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
	public ConsultingDTO selectAdminView(int consultingId) {
		String sql = "SELECT * FROM \"consulting\" "
				+ " WHERE \"consulting_id\" = ?";
		
		ConsultingDTO dto = null;
		
		try {
			System.out.println("> Consulting.selectAdminView()..... ");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, consultingId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new ConsultingDTO();
				dto.setConsultingId(rs.getInt("consulting_id"));
	            dto.setInvestmentAmount(rs.getString("investment_amount"));
	            dto.setDesiredOpeningDate(rs.getString("desired_opening_date"));
	            dto.setStoreArea(rs.getString("store_area"));
	            dto.setPersonalInfoConsent(rs.getInt("personal_info_consent"));
	            dto.setContent(rs.getString("content"));
	            dto.setName(rs.getString("name"));
	            dto.setTel(rs.getString("tel"));
	            dto.setTime(rs.getString("time"));
	            dto.setEmail(rs.getString("email"));
	            dto.setPreferredRegion(rs.getString("preferred_region"));
	            dto.setZipcode(rs.getString("zipcode"));
	            dto.setAddress(rs.getString("address"));
	            dto.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("> Consulting.selectAdminView()..... Exception");
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
	public int updateAdminStatus(int consultingId, int status) {
		
		String sql =
		        "UPDATE \"consulting\" " +
		        "SET \"status\" = ? " +
		        "WHERE \"consulting_id\" = ?";
		
		int rowCount = 0;
		
		try {
			System.out.println("> Consulting.updateAdminStatus()..... ");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, status);
			pstmt.setInt(2, consultingId);
			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("> Consulting.updateAdminStatus()..... Exception");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return rowCount;
	}

	@Override
	public List<PlazaSelectDTO> adminSelect(String categoryId) {
		String sql =
		        " SELECT p.\"plaza_id\", p.\"title\", p.\"plaza_category_id\", p.\"content\", p.\"status\", " +
		        "        SUBSTR(p.\"user_id\", 1, 3) || RPAD('*', LENGTH(p.\"user_id\") - 3, '*') AS masked_user_id " +
		        " FROM \"plaza\" p " +
		        (categoryId == null || categoryId.equals("ALL") ? "" : " WHERE p.\"plaza_category_id\" = ? ") +
		        " ORDER BY p.\"plaza_id\" DESC";

		    ArrayList<PlazaSelectDTO> list = new ArrayList<>();

		    try {
		        pstmt = conn.prepareStatement(sql);
		        if (!(categoryId == null || categoryId.equals("ALL"))) {
		            pstmt.setInt(1, Integer.parseInt(categoryId));
		        }

		        rs = pstmt.executeQuery();
		        while (rs.next()) {
		            psdto = PlazaSelectDTO.builder()
		                .plazaId(rs.getInt("plaza_id"))
		                .title(rs.getString("title"))
		                .content(rs.getString("content"))
		                .userId(rs.getString("masked_user_id"))
		                .plazaCategoryId(rs.getInt("plaza_category_id"))
		                .status(rs.getInt("status"))       
		                .build();
		            list.add(psdto);
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
	public PlazaViewDTO adminView(int seq) {
		String sql =
		        " SELECT u.\"name\", p.\"plaza_id\", p.\"title\", p.\"content\", p.\"is_author_public\", p.\"plaza_category_id\", p.\"status\" " +
		        " FROM \"plaza\" p " +
		        " JOIN \"user\" u ON u.\"user_id\" = p.\"user_id\" " +
		        " WHERE p.\"plaza_id\" = ? ";

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
		                .status(rs.getInt("status"))
		                .build();
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

		    return dto;
	}

	@Override
	public int updatePlazaStatus(int plazaId, int status) {
		String sql = "UPDATE \"plaza\" SET \"status\" = ? WHERE \"plaza_id\" = ?";

	    int rowCount = 0;

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, status);
	        pstmt.setInt(2, plazaId);
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
