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
/* ===== 카테고리 탭 (원본처럼) ===== */
.search-category {
    display: flex;
    gap: 6px;
    margin: 30px 0 40px;
    border-bottom: 1px solid #e5e5e5;
}

.search-category .category-btn {
    padding: 12px 22px;
    border: 1px solid #e5e5e5;
    border-bottom: none;
    border-radius: 12px 12px 0 0;
    background: #f9f9f9;
    font-size: 14px;
    color: #999;
    text-decoration: none;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    min-width: 90px;
}

.search-category .category-btn:hover {
    color: #333;
}

/* 선택된 탭 */
.search-category .category-btn.active {
    background: #fff;
    color: #000;
    font-weight: 600;
}

/* ===== 검색 결과 그리드 ===== */
.search-product-list {
    display: grid;
    grid-template-columns: repeat(5, 1fr);
    gap: 30px;
}

/* 카드 전체 높이 통일 */
.search-product-item {
    text-align: center;
}

.search-product-thumb {
    position: relative;
    width: 100%;
    height: 220px;
    overflow: hidden;   /* 🔴 다시 hidden으로 */
}

.search-product-thumb img {
    position: absolute;
    left: 50%;
    bottom: -30px;      /* 🔴 핵심: -100% ❌ */
    transform: translateX(-50%);
    max-width: 85%;
    height: auto;
}


/* 상품명 여백 조정 */
.search-product-name {
    margin-top: 14px;
    font-size: 14px;
    color: #333;
    line-height: 1.4;
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
        
        <!-- 카테고리 버튼 -->
<!-- 카테고리 탭 -->
<nav class="page-tab">
    <ul class="page-tab__list">

        <li class="page-tab__item <c:if test='${empty param.categoryId}'>page-tab__item--active</c:if>">
            <a href="${pageContext.request.contextPath}/search/list.do?keyword=${keyword}"
               class="page-tab__link">
                <span class="page-tab__text">전체</span>
            </a>
        </li>

        <li class="page-tab__item <c:if test='${param.categoryId eq "1"}'>page-tab__item--active</c:if>">
            <a href="${pageContext.request.contextPath}/search/list.do?keyword=${keyword}&categoryId=1"
               class="page-tab__link">
                <span class="page-tab__text">아이스크림</span>
            </a>
        </li>

        <li class="page-tab__item <c:if test='${param.categoryId eq "2"}'>page-tab__item--active</c:if>">
            <a href="${pageContext.request.contextPath}/search/list.do?keyword=${keyword}&categoryId=2"
               class="page-tab__link">
                <span class="page-tab__text">프리팩</span>
            </a>
        </li>

        <li class="page-tab__item <c:if test='${param.categoryId eq "3"}'>page-tab__item--active</c:if>">
            <a href="${pageContext.request.contextPath}/search/list.do?keyword=${keyword}&categoryId=3"
               class="page-tab__link">
                <span class="page-tab__text">아이스크림 케이크</span>
            </a>
        </li>

        <li class="page-tab__item <c:if test='${param.categoryId eq "4"}'>page-tab__item--active</c:if>">
            <a href="${pageContext.request.contextPath}/search/list.do?keyword=${keyword}&categoryId=4"
               class="page-tab__link">
                <span class="page-tab__text">디저트</span>
            </a>
        </li>

        <li class="page-tab__item <c:if test='${param.categoryId eq "5"}'>page-tab__item--active</c:if>">
            <a href="${pageContext.request.contextPath}/search/list.do?keyword=${keyword}&categoryId=5"
               class="page-tab__link">
                <span class="page-tab__text">음료</span>
            </a>
        </li>

        <li class="page-tab__item <c:if test='${param.categoryId eq "6"}'>page-tab__item--active</c:if>">
            <a href="${pageContext.request.contextPath}/search/list.do?keyword=${keyword}&categoryId=6"
               class="page-tab__link">
                <span class="page-tab__text">커피</span>
            </a>
        </li>

    </ul>
</nav>



        

        <!-- 검색 결과 -->
        <article class="search__content">

            <c:choose>
                <c:when test="${not empty list}">
                    <ul class="search-product-list">

                        <c:forEach var="dto" items="${list}">
                            <li class="search-product-item">
                                <div class="search-product-thumb">
                                    <img src="${pageContext.request.contextPath}${dto.imgPath}"
     onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/resources/images/no-image.png';"
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
