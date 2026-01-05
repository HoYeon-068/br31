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

    <meta name="description" content="배스킨라빈스에게 다양한 의견을 전달해주세요.">
    <meta property="og:site_name" content="배스킨라빈스">
    <meta property="og:url" content="${pageContext.request.contextPath}/index.jsp">
    <meta property="og:title" content="[배스킨라빈스] 배라광장">
    <meta property="og:description" content="배스킨라빈스에게 다양한 의견을 전달해주세요.">
    <meta property="og:image" content="${pageContext.request.contextPath}/resources/images/common/img_share.png">
    <meta property="og:type" content="website">

    <!-- 프로젝트 리소스 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendors.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">

    <script src="${pageContext.request.contextPath}/resources/js/vendors.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
</head>

<body id="baskinrobbins-play-plaza-view" class="baskinrobbins-play-plaza-view">

<div class="skipnav"><a href="#content">본문 영역으로 바로가기</a></div>

<!-- HEADER -->
<jsp:include page="/views/layout/header.jsp" />

<section class="site-container">

    <!-- 상단 page-menu -->
    <nav class="page-menu">
        <ul class="page-menu__list">
            <li class="page-menu__item">
                <a href="${pageContext.request.contextPath}/views/play/event/list.jsp" class="page-menu__link">
                    <div class="page-menu__box"><span class="page-menu__name">이벤트</span></div>
                </a>
            </li>
            <li class="page-menu__item page-menu__item--active">
                <a href="${pageContext.request.contextPath}/views/play/plaza/list.jsp" class="page-menu__link">
                    <div class="page-menu__box"><span class="page-menu__name">배라광장</span></div>
                </a>
            </li>
            <li class="page-menu__item">
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
    <div id="content" class="plaza-view">
        <header class="page-header">
            <div class="page-header__container">
                <div class="page-header__content">
                    <!-- ✅ 카테고리별 타이틀(더미) -->
                    <h2 class="page-header__title">
                        아이스크림 맛 제안 요청
                    </h2>
                </div>
            </div>
        </header>

        <article class="plaza-view__container">

            <!-- ✅ 지금은 더미. 나중에 로그인/CSRF 붙이면 hidden으로 세팅 -->
            <input type="hidden" name="csrf_token" value="DUMMY_CSRF_TOKEN">

            <header class="plaza-view-header">

                <!-- ✅ 더미 데이터: 제품 이름(혹은 글 주제) -->
                <div class="plaza-view-header__box">
                    <p class="plaza-view-header__name">제품 이름</p>
                    <h3 class="plaza-view-header__title">허니치즈트랩 재출시</h3>
                </div>

                <!-- ✅ 더미 데이터: 작성자 -->
                <div class="plaza-view-header__box">
                    <p class="plaza-view-header__name">이름</p>
                    <p class="plaza-view-header__text">비공개</p>
                </div>

                <!-- 필요하면 작성일/조회수 같은 것도 여기에 박으면 됨 -->
                <%-- 
                <div class="plaza-view-header__box">
                    <p class="plaza-view-header__name">작성일</p>
                    <p class="plaza-view-header__text">2025-12-21</p>
                </div>
                --%>
            </header>

            <!-- 본문 -->
            <div class="plaza-view__editor">
                매년 겨울마다 출시되었던 저의 최애아이스크림 허니치즈트랩이 이번년도 겨울엔 나오지 않았어요😱😭😭
                이것땜에 겨울이 되면 배라 맨날 갔는데.... 이제 허니치즈트랩이 없는 겨울은 기다려지지않네요...😨
            </div>

            <!-- 버튼 영역 -->
            <div class="plaza-view__buttons">
                <!-- ✅ 좋아요 버튼 (data-seq만 유지) -->
                <button type="button" class="plaza-view-control__like" data-seq="25">
                    <span class="plaza-view-control__like-text">좋아요</span>
                </button>

                <!-- ✅ 목록 복귀 (더미 파라미터 유지) -->
                <a href="${pageContext.request.contextPath}/views/play/plaza/list.jsp?page=1&category=ALL"
                   class="plaza-view__button">
                    목록
                </a>
            </div>

        </article>
    </div>
</section>

<!-- FOOTER -->
<jsp:include page="/views/layout/footer.jsp" />

</body>
</html>
