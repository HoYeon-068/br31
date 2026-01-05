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

<body id="baskinrobbins-play-recipe-view" class="baskinrobbins-play-recipe-view">

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
    <div id="content" class="recipe-view">

        <header class="page-header">
            <div class="page-header__container">
                <div class="page-header__content">
                    <h2 class="page-header__title">BR Recipe</h2>
                    <div class="event-view__header">
                        다양한 전문가들이 제안하는 배라 행복레시피
                    </div>
                </div>
            </div>
        </header>

        <!-- article -->
        <article class="recipe-view-article recipe-view-recipe10">
            <header class="recipe-view-content-header">
                <p class="recipe-view-content-header__category">아티스트</p>
                <h3 class="recipe-view-content-header__title">AI Artist Inspired by Ice Cream</h3>
                <p class="recipe-view-content-header__date">2023.03.01</p>
            </header>

            <div class="recipe-view-message">
                <div class="recipe-view-message__container">
                    <p class="recipe-view-message__title">By Artists and Creators</p>

                    <p class="recipe-view-message__text">
                        사진가, 플로리스트, 작가, 화가, 댄서, 뮤지션, 비주얼 아티스트….<br>
                        10명의 창작자들이 저마다의 감각으로 배스킨라빈스를 표현했다.<br>
                        <strong class="recipe-view-message__strong">
                            배스킨라빈스와 함께 페어링하면 더욱 좋을 행복 레시피들
                        </strong>
                    </p>
                </div>
            </div>

            <div class="recipe-view-article__container">
                <div class="recipe-view-article__header">
                    <p class="recipe-view-article__header-category">
                        <span class="recipe-view-article__header-category-text">BR RECIPE</span>
                    </p>

                    <h4 class="recipe-view-article__header-title">
                        AI Artist Inspired<br>
                        by Ice Cream
                    </h4>
                </div>

                <div class="recipe-view-recipe10-intro">
                    <div class="recipe-view-article-item__frame">
                        <img src="${pageContext.request.contextPath}/resources/images/play/recipe/recipe10/img_1.jpg"
                             alt="AI Artist Inspired by Ice Cream"
                             class="recipe-view-article-item__image">
                    </div>

                    <div class="recipe-view-recipe10-intro__container">
                        <div class="recipe-view-recipe10-intro__content-header">
                            <h5>AI 아티스트가 그린 아이스크림 나라</h5>

                            <p>
                                우주를 떠다니는 슈팅스타 행성이 팝핑 캔디를 뱉어내며 폭발하고,<br>
                                영영 녹지  않는 체리 쥬빌레 궁전에서 피카소 그림 속 여인이 살아 움직이며 아이스크림 콘을 맛본다.<br>
                                인공지능 달리 2가 배스킨라빈스로 그려본 아이스크림 동화 나라
                                <strong>Recipe by 달리 2 AI 아티스트</strong>
                            </p>
                        </div>
                    </div>
                </div>

                <div class="recipe-view-article-item recipe-view-article-item--horizontal recipe-view-recipe10-item-1">
                    <div class="recipe-view-article-item-content">
                        <div>
                            <span class="recipe-view-article-item__number">01</span>
                            <h5 class="recipe-view-article-item__title">
                                <strong>아크릴로 그린 아이스크림 궁전</strong>
                            </h5>
                            <p class="recipe-view-article-item__text">
                                ‘배스킨라빈스 아이스크림으로 지은 궁전을 아크릴 물감으로 그려줘.’<br>
                                지시어를 입력하자 ‘체리 쥬빌레’의 고운 핑크빛으로 채운 아랍 스타일의<br>
                                궁전이 뚝딱 완성됐다. 배스킨라빈스 왕족이 머무는 이 궁전에서 행사가<br>
                                열리는 날이면 모든 시민들이 자유롭게 출입해 100가지 플레이버를<br>
                                맛볼 수 있다는 상상의 나래를 펼쳐본다.
                            </p>
                        </div>
                    </div>

                    <img src="${pageContext.request.contextPath}/resources/images/play/recipe/recipe10/img_2.jpg"
                         alt="아크릴로 그린 아이스크림 궁전"
                         class="recipe-view-article-item__image">
                </div>

                <div class="recipe-view-article-item recipe-view-article-item--horizontal recipe-view-recipe10-item-2">
                    <img src="${pageContext.request.contextPath}/resources/images/play/recipe/recipe10/img_3.jpg"
                         alt="아이스크림을 먹는 여인"
                         class="recipe-view-article-item__image">

                    <div class="recipe-view-article-item-content">
                        <div>
                            <span class="recipe-view-article-item__number">02</span>
                            <h5 class="recipe-view-article-item__title">
                                <strong>아이스크림을 먹는 여인</strong>
                            </h5>
                            <p class="recipe-view-article-item__text">
                                여인을 소재로 한 다수의 작품을 남긴 피카소. 특히 ‘장밋빛 시대’라<br>
                                불렸던 시점의 강렬한 색감과 행복한 느낌의 화풍으로 아이스크림을<br>
                                먹는 여인을 그린다면 어떤 모습일까? 의뢰를 받은 달리 2는 손에 커다란<br>
                                아이스크림 콘을 들고 행복한 표정으로 음미하는 여인들을 그려냈다.
                            </p>
                        </div>
                    </div>
                </div>

                <div class="recipe-view-article-item recipe-view-article-item--vertical recipe-view-recipe10-item-3">
                    <div class="recipe-view-article-item-content">
                        <div>
                            <span class="recipe-view-article-item__number">03</span>
                            <h5 class="recipe-view-article-item__title">
                                <strong>우주에서 폭발하는 아이스크림 행성</strong>
                            </h5>
                            <p class="recipe-view-article-item__text">
                                완전한 암흑 속 밝게 빛나는 ‘슈팅스타’ 행성. 팝핑 캔디가 금방이라도 터질 듯한 모습을 하고 있다.
                                이 순간의 달콤함을 놓치지 않으려는 듯<br>
                                커다란 은색 스푼이 날아와 꽂힌다. 주로 원색이나 어두운 색조를 사용하는 화가 반 고흐 스타일로 완성하도록
                                지시어를 입력한 결과물이다.
                            </p>
                        </div>
                    </div>

                    <img src="${pageContext.request.contextPath}/resources/images/play/recipe/recipe10/img_4.jpg"
                         alt="우주에서 폭발하는 아이스크림 행성"
                         class="recipe-view-article-item__image">
                </div>
            </div>
        </article>

        <!-- 목록 버튼 (php -> jsp) -->
        <div class="recipe-view__box">
            <a href="${pageContext.request.contextPath}/views/play/recipe/list.jsp?category=ALL&page=1"
               class="recipe-view__button">목록</a>
        </div>

    </div>
</section>

<!-- FOOTER -->
<jsp:include page="/views/layout/footer.jsp" />

</body>
</html>
