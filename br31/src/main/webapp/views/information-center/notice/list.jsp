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
	
	<style>
/* ===== 게시판 검색 ===== */
.board-search {
    margin: 60px auto 40px;
    max-width: 720px;
    width: 100%;
}

.board-search__inner {
    position: relative;
    border-bottom: 2px solid #000;
}

.board-search__input {
    width: 100%;
    border: none;
    outline: none;
    font-size: 16px;
    padding: 10px 44px 10px 0;
    background: transparent;
}

.board-search__input::placeholder {
    color: #aaa;
}

.board-search__button {
    position: absolute;
    top: 50%;
    right: 0;
    width: 39px;
    height: 39px;
    transform: translateY(-50%);
    border: none;
    background: url(${pageContext.request.contextPath}/resources/images/information-center/btn_search.png)
                no-repeat center center / auto 39px;
    cursor: pointer;
}

.board-search__input {
    width: 100%;
    border: none;
    outline: none;
    font-size: 16px;
    padding: 10px 44px 10px 0;
    background: transparent;
    text-align: center;        /* 🔴 핵심 */
}

/* placeholder도 가운데 */
.board-search__input::placeholder {
    color: #aaa;
    text-align: center;        /* 🔴 핵심 */
}


</style>
	
	
</head>

<body>

<jsp:include page="/views/layout/header.jsp" />



<div class="site-container">

<jsp:include page="/views/information-center/_customerMenu.jsp" />


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
        
<form action="${pageContext.request.contextPath}/notice/list.do"
      method="get"
      class="board-search"
      onsubmit="return submitNoticeSearch(this);">

    <input type="hidden" name="source" value="notice">

    <div class="board-search__inner">
        <input type="text"
               name="keyword"
               class="board-search__input"
               placeholder="검색어를 입력해주세요"
               value="${keyword}">
        <button type="submit" class="board-search__button"></button>
    </div>
</form>


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

<script>
function submitNoticeSearch(form) {
    if (form.keyword.value.trim() !== "") {
        form.action = "${pageContext.request.contextPath}/search/board.do";
        form.insertAdjacentHTML(
            "beforeend",
            '<input type="hidden" name="source" value="notice">'
        );
    }
    return true;
}
</script>


</body>
</html>
