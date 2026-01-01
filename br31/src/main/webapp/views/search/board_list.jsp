<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>배스킨라빈스 | 검색 결과</title>

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
            text-align: center;
        }

        .board-search__input::placeholder {
            color: #aaa;
            text-align: center;
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
    </style>
</head>

<body>

<jsp:include page="/views/layout/header.jsp" />

<div class="site-container">

    <!-- 고객센터 공통 메뉴 -->
    <jsp:include page="/views/information-center/_customerMenu.jsp" />

    <section id="content" class="notice-list board-list">

        <!-- 페이지 헤더 -->
        <header class="page-header">
            <div class="page-header__container">
                <div class="page-header__content">
                    <h2 class="page-header__title">검색 결과</h2>
                </div>
                <div class="page-header__content">
                    <p class="page-header__text">
                        입력하신 검색어에 대한<br>
                        공지사항 및 보도자료 검색 결과입니다.
                    </p>
                </div>
            </div>
        </header>

        <!-- 검색 폼 -->
        <form action="${pageContext.request.contextPath}/search/board.do"
              method="get"
              class="board-search">
            <div class="board-search__inner">
                <input type="text"
                       name="keyword"
                       class="board-search__input"
                       placeholder="검색어를 입력해주세요"
                       value="${keyword}">
                <button type="submit" class="board-search__button"></button>
            </div>
        </form>

        <!-- 검색 결과 -->
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

                    <c:choose>
                        <c:when test="${not empty searchList}">
                            <c:forEach var="dto" items="${searchList}">

                                <tr class="board-list__table-list">

                                    <!-- 번호 -->
                                    <td class="board-list__table-number">
                                        ${dto.no}
                                    </td>

                                    <!-- 제목 (타입별 링크 분기) -->
                                    <td class="board-list__table-title">
                                        <c:choose>
                                            <c:when test="${dto.type eq 'NOTICE'}">
                                                <a href="${pageContext.request.contextPath}/notice/view.do?id=${dto.id}">
                                                    ${dto.title}
                                                </a>
                                            </c:when>
                                            <c:when test="${dto.type eq 'PRESS'}">
                                                <a href="${pageContext.request.contextPath}/press/view.do?id=${dto.id}">
                                                    ${dto.title}
                                                </a>
                                            </c:when>
                                        </c:choose>
                                    </td>

                                    <!-- 날짜 -->
                                    <td class="board-list__table-date">
                                        ${dto.regDate}
                                    </td>

                                </tr>

                            </c:forEach>
                        </c:when>

                        <c:otherwise>
                            <tr>
                                <td colspan="3" style="padding:40px; text-align:center;">
                                    검색 결과가 없습니다.
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>

                    </tbody>
                </table>

            </div>
        </div>

    </section>
</div>

<jsp:include page="/views/layout/footer.jsp" />

</body>
</html>
