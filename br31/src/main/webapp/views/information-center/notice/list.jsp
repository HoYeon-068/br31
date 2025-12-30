<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>배스킨라빈스 | 공지사항</title>

 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendors.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
     <script src="${pageContext.request.contextPath}/resources/js/vendors.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
</head>

<body>

<jsp:include page="/views/layout/header.jsp" />

<div class="site-container">

    <nav class="page-menu">
        <ul class="page-menu__list">
            <li class="page-menu__item page-menu__item--active">
               <a href="${pageContext.request.contextPath}/notice/list.do" class="page-menu__link">
                    <div class="page-menu__box">
                        <span class="page-menu__name">공지사항</span>
                    </div>
                </a>
            </li>
            <li class="page-menu__item">
                <a href="${pageContext.request.contextPath}/press/list.do" class="page-menu__link">
                    <div class="page-menu__box">
                        <span class="page-menu__name">보도자료</span>
                    </div>
                </a>
            </li>
        </ul>
    </nav>

    <section id="content" class="notice-list board-list">

        <header class="page-header">
            <div class="page-header__container">
                <div class="page-header__content">
                    <h2 class="page-header__title">공지사항</h2>
                </div>
                <div class="page-header__content">
                    <p class="page-header__text">
                        배스킨라빈스의 신제품 안내, 신규 CF 등<br>
                        다양한 소식을 알려드립니다.
                    </p>
                </div>
            </div>
        </header>

        <div class="board-list__content">
            <div class="board-list__table-wrap">

             <p class="board-list__total">
    총 <strong>${totalCount}</strong>건

</p>


                <table class="board-list__table">
                    <colgroup>
                        <col width="66px">
                        <col>
                        <col width="80px">
                    </colgroup>
                    <tbody>
						<c:forEach var="dto" items="${list}">
    					<tr class="board-list__table-list">
        				<td class="board-list__table-number">
           							${dto.NOTICE_NO}
        				</td>
        				<td class="board-list__table-title">
            			<a href="${pageContext.request.contextPath}/notice/view.do?NOTICE_ID=${dto.NOTICE_ID}">
    ${dto.TITLE}
</a>

        				</td>
					        <td class="board-list__table-date">
					            ${dto.REG_DATE}
					        </td>
    </tr>
</c:forEach>
</tbody>

                </table>
                <div class="paging">
    <c:forEach var="i" begin="1" end="${totalPage}">
        <a href="${pageContext.request.contextPath}/notice/list.do?page=${i}"
           class="${i == currentPage ? 'active' : ''}">
            ${i}
        </a>
    </c:forEach>
</div>
                

            </div>
        </div>

    </section>
</div>

<jsp:include page="/views/layout/footer.jsp" />

</body>
</html>
