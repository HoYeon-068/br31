<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="date=no">
    <meta name="format-detection" content="address=no">
    <meta name="format-detection" content="email=no">

    <title>배스킨라빈스</title>

    <meta name="description" content="행복을 전하는 프리미엄 아이스크림, 배스킨라빈스 공식 홈페이지 입니다.">
    <meta name="keywords" content="baskinrobbins, br31, 배스킨라빈스, 배라, 베라">
    <meta name="author" content="배스킨라빈스">

    <meta property="og:site_name" content="배스킨라빈스">
    <meta property="og:url" content="${pageContext.request.contextPath}/">
    <meta property="og:title" content="[배스킨라빈스] BR레시피">
    <meta property="og:description" content="다양한 전문가들이 제안하는 배스킨라빈스 행복 레시피 입니다.">
    <meta property="og:image" content="${pageContext.request.contextPath}/resources/images/common/img_share.png">
    <meta property="og:type" content="website">

    <!-- 프로젝트 리소스 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendors.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">

    <script src="${pageContext.request.contextPath}/resources/js/vendors.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
</head>

<body id="baskinrobbins-play-recipe-list" class="baskinrobbins-play-recipe-list">

<div class="skipnav"><a href="#content">본문 영역으로 바로가기</a></div>

<!-- HEADER -->
<jsp:include page="/views/layout/header.jsp" />

<section class="site-container">

    <!-- page-menu -->
    <nav class="page-menu">
        <ul class="page-menu__list">
            <li class="page-menu__item">
                <a href="${pageContext.request.contextPath}/views/play/event/list.jsp" class="page-menu__link">
                    <div class="page-menu__box"><span class="page-menu__name">이벤트</span></div>
                </a>
            </li>
            <li class="page-menu__item">
                <a href="${pageContext.request.contextPath}/views/play/plaza/list.jsp" class="page-menu__link">
                    <div class="page-menu__box"><span class="page-menu__name">배라광장</span></div>
                </a>
            </li>
            <li class="page-menu__item page-menu__item--active">
                <a href="${pageContext.request.contextPath}/views/play/recipe/list.jsp" class="page-menu__link">
                    <div class="page-menu__box"><span class="page-menu__name">BR 레시피</span></div>
                </a>
            </li>
            <li class="page-menu__item">
                <a href="${pageContext.request.contextPath}/views/play/myflavor/list.jsp" class="page-menu__link">
                    <div class="page-menu__box"><span class="page-menu__name">마이플레이버 리스트</span></div>
                </a>
            </li>
        </ul>
    </nav>

    <!-- CONTENT -->
    <div id="content" class="recipe-list">

        <header class="page-header">
            <div class="page-header__container">
                <div class="page-header__content">
                    <h2 class="page-header__title">BR Recipe</h2>
                </div>
                <div class="page-header__content">
                    <p class="page-header__text">다양한 전문가들이 제안하는 배라 행복레시피</p>
                </div>
            </div>
        </header>

        <!-- 탭 -->
        <nav class="page-tab">
            <ul class="page-tab__list">
                <!-- ✅ 아래는 하드코딩 버전(원본 유지). 실제로는 request param으로 active 처리하면 됨 -->
                <li class="page-tab__item page-tab__item--active">
                    <a href="list.jsp?category=ALL" class="page-tab__link"><span class="page-tab__text">전체</span></a>
                </li>
                <li class="page-tab__item">
                    <a href="list.jsp?category=A" class="page-tab__link"><span class="page-tab__text">셰프 &amp; 파티시에</span></a>
                </li>
                <li class="page-tab__item">
                    <a href="list.jsp?category=B" class="page-tab__link"><span class="page-tab__text">바리스타 &amp; 바텐더</span></a>
                </li>
                <li class="page-tab__item">
                    <a href="list.jsp?category=C" class="page-tab__link"><span class="page-tab__text">아티스트</span></a>
                </li>
                <li class="page-tab__item">
                    <a href="list.jsp?category=D" class="page-tab__link"><span class="page-tab__text">인플루언서</span></a>
                </li>
            </ul>
        </nav>

        <div class="recipe-list-list card-list">
            <ul class="card-list__list">

                <!-- ✅ 지금은 원본 HTML을 그대로 옮김(나중에 DB 리스트로 c:forEach 돌리면 됨) -->
                <li class="card-list__item">
                    <a href="view.jsp?seq=78&category=ALL&page=1" class="card-list__link">
                        <div class="card-list__box">
                            <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/brRecipe/adbe005b20b5e56a263206c52b523b3d.jpg"
                                 alt="Roasted Strawberry Pavlova"
                                 class="recipe-list-list__image">
                        </div>
                        <div class="card-list__content">
                            <p class="recipe-list-list__category">아티스트</p>
                            <h3 class="recipe-list-list__title">AI Artist Inspired by Ice Cream</h3>
                            <p class="recipe-list-list__text">AI 아티스트가 그린 아이스크림 나라</p>
                        </div>
                    </a>
                </li>

                <li class="card-list__item">
                    <a href="view.jsp?seq=77&category=ALL&page=1" class="card-list__link">
                        <div class="card-list__box">
                            <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/brRecipe/0382a9fe97c0d26c325791fd192c1a07.jpg"
                                 alt="Roasted Strawberry Pavlova"
                                 class="recipe-list-list__image">
                        </div>
                        <div class="card-list__content">
                            <p class="recipe-list-list__category">아티스트</p>
                            <h3 class="recipe-list-list__title">Metaverse Ice Cream</h3>
                            <p class="recipe-list-list__text">메타버스 아이스크림</p>
                        </div>
                    </a>
                </li>

                <li class="card-list__item">
                    <a href="view.jsp?seq=75&category=ALL&page=1" class="card-list__link">
                        <div class="card-list__box">
                            <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/brRecipe/45fe5c525fba0ae0a32631513a356ff4.jpg"
                                 alt="Roasted Strawberry Pavlova"
                                 class="recipe-list-list__image">
                        </div>
                        <div class="card-list__content">
                            <p class="recipe-list-list__category">아티스트</p>
                            <h3 class="recipe-list-list__title">Dancing with Ice Cream</h3>
                            <p class="recipe-list-list__text">아이스크림과 함께 춤을</p>
                        </div>
                    </a>
                </li>

                <li class="card-list__item">
                    <a href="view.jsp?seq=74&category=ALL&page=1" class="card-list__link">
                        <div class="card-list__box">
                            <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/brRecipe/b7cb1900c370ab198dfa57529ed439bd.jpg"
                                 alt="Roasted Strawberry Pavlova"
                                 class="recipe-list-list__image">
                        </div>
                        <div class="card-list__content">
                            <p class="recipe-list-list__category">아티스트</p>
                            <h3 class="recipe-list-list__title">Ice Cream Flower</h3>
                            <p class="recipe-list-list__text">꽃으로 표현한 아이스크림</p>
                        </div>
                    </a>
                </li>

                <li class="card-list__item">
                    <a href="view.jsp?seq=73&category=ALL&page=1" class="card-list__link">
                        <div class="card-list__box">
                            <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/brRecipe/e108fd4c14d42a9f4752751dc0036a14.jpg"
                                 alt="Roasted Strawberry Pavlova"
                                 class="recipe-list-list__image">
                        </div>
                        <div class="card-list__content">
                            <p class="recipe-list-list__category">아티스트</p>
                            <h3 class="recipe-list-list__title">A Spring Garden</h3>
                            <p class="recipe-list-list__text">봄의 정원</p>
                        </div>
                    </a>
                </li>

                <li class="card-list__item">
                    <a href="view.jsp?seq=72&category=ALL&page=1" class="card-list__link">
                        <div class="card-list__box">
                            <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/brRecipe/f460ce24f202b3a40822b3998f040998.jpg"
                                 alt="Roasted Strawberry Pavlova"
                                 class="recipe-list-list__image">
                        </div>
                        <div class="card-list__content">
                            <p class="recipe-list-list__category">아티스트</p>
                            <h3 class="recipe-list-list__title">Sweet Devil Sprites</h3>
                            <p class="recipe-list-list__text">아이스크림 속 작은 악마들</p>
                        </div>
                    </a>
                </li>

            </ul>

            <!-- pagination (원본 html 유지 + php -> jsp) -->
            <nav>
                <ul class="pagination">
                    <li class="pagination__item pagination__item--icon pagination__item--prev pagination__item--disabled">
                        <a href="?category=ALL&page=1" class="pagination__link">
                            <span class="pagination__name">이전</span>
                        </a>
                    </li>

                    <li class="pagination__item pagination__item--current" aria-current="page">
                        <strong class="pagination__link"><span class="pagination__name">1</span></strong>
                    </li>

                    <li class="pagination__item">
                        <a href="?category=ALL&page=2" class="pagination__link"><span class="pagination__name">2</span></a>
                    </li>
                    <li class="pagination__item">
                        <a href="?category=ALL&page=3" class="pagination__link"><span class="pagination__name">3</span></a>
                    </li>
                    <li class="pagination__item">
                        <a href="?category=ALL&page=4" class="pagination__link"><span class="pagination__name">4</span></a>
                    </li>
                    <li class="pagination__item">
                        <a href="?category=ALL&page=5" class="pagination__link"><span class="pagination__name">5</span></a>
                    </li>

                    <li class="pagination__item pagination__item--icon pagination__item--next">
                        <a href="?category=ALL&page=6" class="pagination__link">
                            <span class="pagination__name">다음</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>

    </div>
</section>

<!-- FOOTER -->
<jsp:include page="/views/layout/footer.jsp" />

</body>
</html>
