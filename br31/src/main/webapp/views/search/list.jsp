<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>배스킨라빈스 | 검색</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendors.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">

    <script src="${pageContext.request.contextPath}/resources/js/vendors.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/app.js"></script>

    <!-- 검색 페이지 전용 CSS (공통 파일 미수정) -->
    <style>
        .search-product-list {
            display: grid;
            grid-template-columns: repeat(5, 1fr);
            gap: 30px;
        }

        .search-product-item {
            text-align: center;
        }

        .search-product-thumb img {
            width: 100%;
            height: auto;
            display: block;
        }

        .search-product-name {
            margin-top: 10px;
            font-size: 14px;
            color: #333;
        }
    </style>
</head>

<body id="baskinrobbins-search" class="baskinrobbins-search">

<jsp:include page="/views/layout/header.jsp" />

<div class="site-container">

<section id="content" class="search">

    <!-- 제목 -->
    <header class="page-header">
        <div class="page-header__container">
            <div class="page-header__content">
                <h2 class="page-header__title">검색</h2>
            </div>
        </div>
    </header>

    <div class="search__container">

        <!-- 검색 폼 -->
        <form action="${pageContext.request.contextPath}/search/list.do"
              method="get"
              class="search-form">

            <div class="search-form__container">
                <label class="search-form__label">
                    <input type="text"
                           class="search-form__input"
                           name="keyword"
                           value="${keyword}"
                           placeholder="제품명을 입력하세요">

                    <button type="submit"
                            class="search-form__button search-form__button--search">
                        <span class="search-form__text">검색</span>
                    </button>
                </label>
            </div>
        </form>

        <!-- 검색 결과 요약 -->
        <div class="search-result">
            <p class="search-result__title">
                <strong class="search-result__point">
                    <c:out value="${keyword}" />
                </strong>
                검색결과 총
                <strong>${totalCount}</strong>건
            </p>
        </div>

        <!-- 검색 결과 -->
        <article class="search__content">

            <c:choose>
                <c:when test="${not empty list}">
                    <ul class="search-product-list">

                        <c:forEach var="dto" items="${list}">
                            <li class="search-product-item">
                                <div class="search-product-thumb">
                                    <img src="${pageContext.request.contextPath}${dto.imgPath}"
                                         alt="${dto.productName}">
                                </div>
                                <p class="search-product-name">
                                    ${dto.productName}
                                </p>
                            </li>
                        </c:forEach>

                    </ul>
                </c:when>

                <c:otherwise>
                    <div style="padding:40px; text-align:center;">
                        검색 결과가 없습니다.
                    </div>
                </c:otherwise>
            </c:choose>

        </article>

    </div>
</section>

</div>

<jsp:include page="/views/layout/footer.jsp" />

</body>
</html>
