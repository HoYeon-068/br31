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
    
</head>

<body id="baskinrobbins-search"
      class="baskinrobbins-search">

<jsp:include page="/views/layout/header.jsp" />

<div class="site-container">


    <section id="content" class="search">

      
        <header class="page-header">
            <div class="page-header__container">
                <div class="page-header__content">
                    <h2 class="page-header__title">검색</h2>
                </div>
            </div>
        </header>

        <div class="search__container">

          
          <form action="${pageContext.request.contextPath}/search/list.do"
      method="get"
      class="search-form">

                <div class="search-form__container">
                    <label class="search-form__label">
                        <input type="text"
                               class="search-form__input"
                               name="keyword"
                               value="${param.keyword}"
                               placeholder="제품명을 입력하세요">

                        <button type="submit"
                                class="search-form__button search-form__button--search">
                            <span class="search-form__text">검색</span>
                        </button>
                    </label>
                </div>
            </form>

           
            <div class="search-result">
                <p class="search-result__title">
                    <strong class="search-result__point">
                        <c:out value="${param.keyword}" />
                    </strong>
                    검색결과 총
                    <strong>${totalCount}</strong>건
                </p>
            </div>

         
            <nav class="page-tab">
                <ul class="page-tab__list">
                    <li class="page-tab__item ${empty param.category ? 'page-tab__item--active' : ''}">
                        <a href="list.do?keyword=${param.keyword}" class="page-tab__link">
                            <span class="page-tab__text">전체</span>
                        </a>
                    </li>
                    <li class="page-tab__item">
                        <a href="list.do?keyword=${param.keyword}&category=icecream"
                           class="page-tab__link">
                            <span class="page-tab__text">아이스크림</span>
                        </a>
                    </li>
                    <li class="page-tab__item">
                        <a href="list.do?keyword=${param.keyword}&category=cake"
                           class="page-tab__link">
                            <span class="page-tab__text">아이스크림 케이크</span>
                        </a>
                    </li>
                </ul>
            </nav>

           
            <article class="search__content">
                <div class="menu-list">
                    <ul class="menu-list__list">

                        <c:forEach var="dto" items="${list}">
                         <li class="menu-list__item"
    style="--menu-list-color:${dto.bgColor}">
    <a href="#" class="menu-list__link">
        <img src="${pageContext.request.contextPath}${dto.imgPath}"
             class="menu-list__image" alt="">

    </a>
    <strong class="menu-list__title">${dto.productName}</strong>
</li>

                        </c:forEach>

                        <c:if test="${empty list}">
                            <li style="padding:40px; text-align:center;">
                                검색 결과가 없습니다.
                            </li>
                        </c:if>

                    </ul>
                </div>
            </article>

        </div>

    </section>
</div>

<jsp:include page="/views/layout/footer.jsp" />

</body>
</html>
