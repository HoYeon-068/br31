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
    <meta property="og:url" content="${pageContext.request.contextPath}/index.html">
    <meta property="og:title" content="[배스킨라빈스] 이벤트">
    <meta property="og:description" content="배스킨라빈스의 이벤트 메뉴입니다.">
    <meta property="og:image" content="${pageContext.request.contextPath}/resources/images/common/img_share.png">
    <meta property="og:type" content="website">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendors.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">

    <script src="${pageContext.request.contextPath}/resources/js/vendors.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
</head>

<body id="baskinrobbins-play-event-list" class="baskinrobbins-play-event-list">

<div class="skipnav">
    <a href="#content">본문 영역으로 바로가기</a>
</div>

<!-- HEADER -->
<jsp:include page="/views/layout/header.jsp" />

<!-- CONTENT -->
<section class="site-container">

    <!-- 상단 페이지 메뉴 -->
   <nav class="page-menu">
        <ul class="page-menu__list">
            <li class="page-menu__item page-menu__item--active">
                <a href="${pageContext.request.contextPath}/views/play/event/list.jsp" class="page-menu__link">
                    <div class="page-menu__box"><span class="page-menu__name">이벤트</span></div>
                </a>
            </li>
            <li class="page-menu__item">
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

    <!-- 실제 컨텐츠 -->
    <div id="content" class="event-list">

        <!-- 페이지 헤더 -->
        <header class="page-header">
            <div class="page-header__container">
                <div class="page-header__content">
                    <h2 class="page-header__title">Event</h2>
                </div>
                <div class="page-header__content">
                    <p class="page-header__text">
                        배스킨라빈스와 함께 해피포인트앱/오프라인 매장에서 진행하는<br>
                        다양한 이벤트를 확인해보세요
                    </p>
                </div>
            </div>
        </header>

        <!-- 탭 -->
        <nav class="page-tab">
            <ul class="page-tab__list">
                <li class="page-tab__item page-tab__item--active">
                    <a href="list.php" class="page-tab__link">
                        <span class="page-tab__text">전체</span>
                    </a>
                </li>
                <li class="page-tab__item">
                    <a href="list.jsp?category=A" class="page-tab__link">
                        <span class="page-tab__text">프로모션</span>
                    </a>
                </li>
                <li class="page-tab__item">
                    <a href="list.jsp?category=C" class="page-tab__link">
                        <span class="page-tab__text">제휴혜택</span>
                    </a>
                </li>
            </ul>
        </nav>

        <!-- 이벤트 리스트 -->
            <div>
            <div class="event-list-list card-list">
                <ul class="event-list-list__list card-list__list">
                                    <li class="card-list__item event-list-list__item--promotion">
                        <a href="view.jsp?seq=457&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/81c90c5b10893995daa2f42d02edf460.jpg" alt="포켓몬 랜덤 키링&#40;6종 랜덤&#41; 이벤트" class="event-list-list__image">
                                                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">프로모션</span>
                                    포켓몬 랜덤 키링&#40;6종 랜덤&#41; 이벤트                                </h3>

                                <p class="event-list-list__date">2025-12-09 ~ 소진 시 까지</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--benefits">
                        <a href="view.jsp?seq=455&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/664096682c9daa97c8fa05eef3747966.jpg" alt="크리스마스 케이크 최대 30&#37; 혜택 &#40;카카오페이/네이버페이/토스페이/해피앱&#41;" class="event-list-list__image">
                                <p class="event-list-list__count">D-04</p>                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">제휴혜택</span>
                                    크리스마스 케이크 최대 30&#37; 혜택 &#40;카카오페이/네이버페이/토스페이/해피앱&#41;                                </h3>

                                <p class="event-list-list__date">2025-12-17 ~ 2025-12-25</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--benefits">
                        <a href="view.jsp?seq=454&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/b56a23aeef965877a21c71a6c89ac0fd.png" alt="12월 배달·픽업 특별한 할인 혜택" class="event-list-list__image">
                                <p class="event-list-list__count">D-07</p>                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">제휴혜택</span>
                                    12월 배달·픽업 특별한 할인 혜택                                </h3>

                                <p class="event-list-list__date">2025-12-15 ~ 2025-12-28</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--promotion">
                        <a href="view.jsp?seq=453&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/3a198c8f4729ff3521621dd88a8b9d52.png" alt="12월 배라위크! 레디팩 2&#43;1, 아메리카노 1&#43;1" class="event-list-list__image">
                                <p class="event-list-list__count">D-00</p>                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">프로모션</span>
                                    12월 배라위크! 레디팩 2&#43;1, 아메리카노 1&#43;1                                </h3>

                                <p class="event-list-list__date">2025-12-15 ~ 2025-12-21</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--benefits">
                        <a href="view.jsp?seq=452&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/f98e95a7ad8722a7dc3ee80d27c209d6.jpg" alt="12월 카드·포인트 특별한 제휴 혜택" class="event-list-list__image">
                                <p class="event-list-list__count">D-10</p>                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">제휴혜택</span>
                                    12월 카드·포인트 특별한 제휴 혜택                                </h3>

                                <p class="event-list-list__date">2025-12-01 ~ 2025-12-31</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--benefits">
                        <a href="view.jsp?seq=448&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/cf749178e2d4a3a29c2fc4d1aec9c35b.jpg" alt="구매금액의 최대 100&#37; 현대 H.Point 사용 가능!" class="event-list-list__image">
                                <p class="event-list-list__count">D-10</p>                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">제휴혜택</span>
                                    구매금액의 최대 100&#37; 현대 H.Point 사용 가능!                                </h3>

                                <p class="event-list-list__date">2025-12-01 ~ 2025-12-31</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--promotion">
                        <a href="view.jsp?seq=446&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/3147aa0e45352e88600c680545d67ac7.png" alt="12월 이달의 더블주니어! 이달의 맛 선택 시, 500원 추가하면 싱글레귤러를 더블주니어로 더블업!" class="event-list-list__image">
                                <p class="event-list-list__count">D-10</p>                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">프로모션</span>
                                    12월 이달의 더블주니어! 이달의 맛 선택 시, 500원 추가하면 싱글레귤러를 더블주니어로 더블업!                                </h3>

                                <p class="event-list-list__date">2025-12-01 ~ 2025-12-31</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--promotion">
                        <a href="view.jsp?seq=444&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/48e5ae5686e8f63ec14caf189898f34f.png" alt="12월 이달의 맛, 인스타그램에 사진을 올려주세요!" class="event-list-list__image">
                                <p class="event-list-list__count">D-05</p>                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">프로모션</span>
                                    12월 이달의 맛, 인스타그램에 사진을 올려주세요!                                </h3>

                                <p class="event-list-list__date">2025-12-01 ~ 2025-12-26</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--benefits">
                        <a href="view.jsp?seq=441&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/6985e45be3bb78f58e0212ec565d45e2.png" alt="유플투쁠, 쿼터&#40;18,500원&#41; 구매 시 최대 9천원 OFF" class="event-list-list__image">
                                <p class="event-list-list__count">D-10</p>                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">제휴혜택</span>
                                    유플투쁠, 쿼터&#40;18,500원&#41; 구매 시 최대 9천원 OFF                                </h3>

                                <p class="event-list-list__date">2025-12-09 ~ 2025-12-31</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--promotion">
                        <a href="view.jsp?seq=438&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/7d9230dfb8da0d88e774f809cd8a341d.png" alt="파인트&#40;9,800원&#41; 이상 구매 시, 르세라핌 미니 셀피 SET 900원" class="event-list-list__image">
                                                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">프로모션</span>
                                    파인트&#40;9,800원&#41; 이상 구매 시, 르세라핌 미니 셀피 SET 900원                                </h3>

                                <p class="event-list-list__date">2025-11-28 ~ 소진 시 까지</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--promotion">
                        <a href="view.jsp?seq=396&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/8c4602593cd42afb7a33e3351eeca9f6.png" alt="쿼터 이상 구매 시, 스머프 랜덤 키링 2,900원!" class="event-list-list__image">
                                                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">프로모션</span>
                                    쿼터 이상 구매 시, 스머프 랜덤 키링 2,900원!                                </h3>

                                <p class="event-list-list__date">2025-08-14 ~ 소진 시 까지</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--promotion">
                        <a href="view.jsp?seq=385&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/0e55f845f27237246e26c604c219e7b6.png" alt="모구모구 블라스트 1종 구매 시, 모구모구 PET 음료 1개 증정" class="event-list-list__image">
                                                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">프로모션</span>
                                    모구모구 블라스트 1종 구매 시, 모구모구 PET 음료 1개 증정                                </h3>

                                <p class="event-list-list__date">2025-07-22 ~ 소진 시 까지</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--promotion">
                        <a href="view.jsp?seq=380&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/0b4516c5dd11234d7a74eabcb64dcb08.png" alt="민생회복 소비쿠폰 사용 가능" class="event-list-list__image">
                                                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">프로모션</span>
                                    민생회복 소비쿠폰 사용 가능                                </h3>

                                <p class="event-list-list__date">2025-07-21 ~ 소진 시 까지</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--promotion">
                        <a href="view.jsp?seq=342&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/e5546c5856ce3916f7dab4326579054a.png" alt="해피볼 티니핑 요정 프린세스 랜덤 피규어" class="event-list-list__image">
                                                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">프로모션</span>
                                    해피볼 티니핑 요정 프린세스 랜덤 피규어                                </h3>

                                <p class="event-list-list__date">2025-05-01 ~ 소진 시 까지</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--promotion">
                        <a href="view.jsp?seq=99&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/0143a84def93d9c70ed42612fb8af488.png" alt="워크샵 by 배스킨라빈스 그랜드 오픈" class="event-list-list__image">
                                                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">프로모션</span>
                                    워크샵 by 배스킨라빈스 그랜드 오픈                                </h3>

                                <p class="event-list-list__date">상시 운영</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--benefits">
                        <a href="view.jsp?seq=9&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/872ac902cefee2ecab1774466e203c6c.png" alt="T멤버십 회원 대상 싱글레귤러 최대 50&#37; 할인 또는 적립" class="event-list-list__image">
                                                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">제휴혜택</span>
                                    T멤버십 회원 대상 싱글레귤러 최대 50&#37; 할인 또는 적립                                </h3>

                                <p class="event-list-list__date">상시 운영</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--benefits">
                        <a href="view.jsp?seq=10&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/02de337a0b96ddbc1500461cccc6801c.png" alt="KT 멤버십 배스킨라빈스 특별한 제휴 혜택!" class="event-list-list__image">
                                                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">제휴혜택</span>
                                    KT 멤버십 배스킨라빈스 특별한 제휴 혜택!                                </h3>

                                <p class="event-list-list__date">상시 운영</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--benefits">
                        <a href="view.jsp?seq=122&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/37ca9bacd1069187ca9c50f64eda5e3f.png" alt="LG U&#43; 멤버십 배스킨라빈스 특별한 제휴 혜택!" class="event-list-list__image">
                                                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">제휴혜택</span>
                                    LG U&#43; 멤버십 배스킨라빈스 특별한 제휴 혜택!                                </h3>

                                <p class="event-list-list__date">상시 운영</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--benefits">
                        <a href="view.jsp?seq=314&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/feefab979b65d3197e6671326820c63d.png" alt="T 우주패스 구독 혜택 &#40;모바일교환권&#41;" class="event-list-list__image">
                                <p class="event-list-list__count">D-41</p>                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">제휴혜택</span>
                                    T 우주패스 구독 혜택 &#40;모바일교환권&#41;                                </h3>

                                <p class="event-list-list__date">2025-03-01 ~ 2026-01-31</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--benefits">
                        <a href="view.jsp?seq=309&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/051904573a6faab9482caafffa2d6bfc.png" alt="LG U&#43; 구독 혜택 &#40;모바일교환권&#41;" class="event-list-list__image">
                                <p class="event-list-list__count">D-41</p>                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">제휴혜택</span>
                                    LG U&#43; 구독 혜택 &#40;모바일교환권&#41;                                </h3>

                                <p class="event-list-list__date">2025-03-01 ~ 2026-01-31</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--benefits">
                        <a href="view.jsp?seq=253&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/8cd1cd1a26445abb0ad98fad3171582f.png" alt="삼성카드 보너스포인트 100&#37; 사용" class="event-list-list__image">
                                <p class="event-list-list__count">D-296</p>                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">제휴혜택</span>
                                    삼성카드 보너스포인트 100&#37; 사용                                </h3>

                                <p class="event-list-list__date">2025-10-14 ~ 2026-10-13</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--benefits">
                        <a href="view.jsp?seq=25&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/cd99fb2f46c76c61090adc4c446f29c0.png" alt="이제 배라에서도 애플페이" class="event-list-list__image">
                                                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">제휴혜택</span>
                                    이제 배라에서도 애플페이                                </h3>

                                <p class="event-list-list__date">상시 운영</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--benefits">
                        <a href="view.jsp?seq=11&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/c9b25ee4553b2ecfc6da305bdc5de276.png" alt="해피앱에서 5&#37; 적립 놓치지 마세요!" class="event-list-list__image">
                                                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">제휴혜택</span>
                                    해피앱에서 5&#37; 적립 놓치지 마세요!                                </h3>

                                <p class="event-list-list__date">상시 운영</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--benefits">
                        <a href="view.jsp?seq=7&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/8e5747a04cbeb6c854716c824d19ec27.png" alt="현대카드 M포인트 50&#37; 사용" class="event-list-list__image">
                                                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">제휴혜택</span>
                                    현대카드 M포인트 50&#37; 사용                                </h3>

                                <p class="event-list-list__date">상시 운영</p>
                            </div>
                        </a>
                    </li>
                                    <li class="card-list__item event-list-list__item--promotion">
                        <a href="view.jsp?seq=2&category=ALL" class=" card-list__link">
                            <div class="card-list__box">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/388539cae1fd5d2f219392ad712b21ff.png" alt="1회용 컵 사용 줄이기 안내" class="event-list-list__image">
                                                            </div>

                            <div class="card-list__content">
                                <h3 class="event-list-list__title">
                                    <span class="event-list-list__category">프로모션</span>
                                    1회용 컵 사용 줄이기 안내                                </h3>

                                <p class="event-list-list__date">상시 운영</p>
                            </div>
                        </a>
                    </li>
                
                </ul>
            </div>
        </div>

    </div>
</section>

<!-- FOOTER -->
<jsp:include page="/views/layout/footer.jsp" />

</body>
</html>
