package mvc.command.search;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.domain.search.BoardSearchDTO;
import mvc.persistence.search.BoardSearchDAO;

public class BoardSearchHandler implements CommandHandler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {

        String keyword = request.getParameter("keyword");
        if (keyword == null) keyword = "";
        keyword = keyword.trim();

        // 🔴 어디서 검색했는지 판단 (notice / press)
        String referer = request.getHeader("Referer");
        String boardType = "notice"; // 기본값

        if (referer != null && referer.contains("/press/")) {
            boardType = "press";
        }

        BoardSearchDAO dao = new BoardSearchDAO();
        List<BoardSearchDTO> list;
        
        

        // 🔴 검색어 없을 때: 각 게시판 단독 조회
        if (keyword.isEmpty()) {
            if ("press".equals(boardType)) {
                list = dao.searchPressOnly();
            } else {
                list = dao.searchNoticeOnly();
            }
        }
        // 🔴 검색어 있을 때: 공지 + 보도 통합 검색
        else {
            list = dao.searchAll(keyword);
        }
        
        String contextPath = request.getContextPath();

        for (BoardSearchDTO dto : list) {
            if ("NOTICE".equals(dto.getType())) {
                dto.setViewUrl(
                    contextPath + "/notice/view.do?id=" + dto.getId()
                );
            } else if ("PRESS".equals(dto.getType())) {
                dto.setViewUrl(
                    contextPath + "/press/view.do?id=" + dto.getId()
                );
            }
        }


        // 🔴 JSP 호환용 세팅 (둘 다 넣음)
        request.setAttribute("searchList", list);
        request.setAttribute("list", list);
        request.setAttribute("totalCount", list.size());
        request.setAttribute("keyword", keyword);

        // 🔴 검색 결과 전용 페이지
        return "/WEB-INF/views/search/board_list.jsp";

    }
}
