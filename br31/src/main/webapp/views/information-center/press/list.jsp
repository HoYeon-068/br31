<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>배스킨라빈스 | 보도자료</title>

   
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendors.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
    <script src="${pageContext.request.contextPath}/resources/js/vendors.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
</head>

<body>

<jsp:include page="/views/layout/header.jsp" />

<div class="site-container">

    <section id="content" class="press-list board-list">

        <header class="page-header">
            <div class="page-header__container">
                <div class="page-header__content">
                    <h2 class="page-header__title">보도자료</h2>
                </div>
                <div class="page-header__content">
                    <p class="page-header__text">
                        배스킨라빈스의 보도자료들을<br>
                        모아 보여드립니다.
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
            ${dto.pr_no}
        </td>
        <td class="board-list__table-title">
            <a href="${pageContext.request.contextPath}/press/view.do?pr_id=${dto.pr_id}">
                ${dto.title}
            </a>
        </td>
        <td class="board-list__table-date">
            ${dto.reg_date}
        </td>
    </tr>
</c:forEach>
</tbody>

                </table>
  <div class="paging">

    <!-- 이전 블록 -->
    <c:if test="${startPage > 1}">
        <a href="${pageContext.request.contextPath}/press/list.do?page=${startPage - 1}">
            &lt;
        </a>
    </c:if>

    <!-- 페이지 번호 -->
    <c:forEach var="i" begin="${startPage}" end="${endPage}">
        <a href="${pageContext.request.contextPath}/press/list.do?page=${i}"
           class="${i == currentPage ? 'active' : ''}">
            ${i}
        </a>
    </c:forEach>

    <!-- 다음 블록 -->
    <c:if test="${endPage < totalPage}">
        <a href="${pageContext.request.contextPath}/press/list.do?page=${endPage + 1}">
            &gt;
        </a>
    </c:if>

</div>

                

            </div>
        </div>

    </section>
</div>

<jsp:include page="/views/layout/footer.jsp" />

</body>
</html>
