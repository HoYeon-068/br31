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

    <meta name="description" content="배스킨라빈스의 이벤트 상세 페이지입니다.">
    <meta property="og:title" content="[배스킨라빈스] 이벤트">
    <meta property="og:description" content="배스킨라빈스의 이벤트 상세 페이지입니다.">
    <meta property="og:image" content="${pageContext.request.contextPath}/resources/images/common/img_share.png">
    <meta property="og:type" content="website">

    <!-- 프로젝트 리소스 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendors.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">

    <script src="${pageContext.request.contextPath}/resources/js/vendors.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/app.js"></script>

    <!-- (선택) view 페이지에서만 필요한 스타일이면 여기 -->
    <style>
        /* 기존에 있던 find-store2 관련은 제거하고,
           실제 마크업(find-store)에 맞춰서 overlay 느낌으로 조정 가능 */
        .find-store {
            display: none;
        }
    </style>
</head>

<body id="baskinrobbins-play-event-view" class="baskinrobbins-play-event-view">

<div class="skipnav"><a href="#content">본문 영역으로 바로가기</a></div>

<!-- HEADER -->
<jsp:include page="/views/layout/header.jsp" />

<!-- CONTENT -->
<section class="site-container">

    <!-- 상단 page-menu -->
    <nav class="page-menu">
        <ul class="page-menu__list">
            <li class="page-menu__item page-menu__item--active">
                <a href="${pageContext.request.contextPath}/views/play/event/list.jsp" class="page-menu__link">
                    <div class="page-menu__box"><span class="page-menu__name">이벤트</span></div>
                </a>
            </li>
            <li class="page-menu__item">
                <a href="${pageContext.request.contextPath}/views/play/event/list.jsp" class="page-menu__link">
                    <div class="page-menu__box"><span class="page-menu__name">배라광장</span></div>
                </a>
            </li>
            <li class="page-menu__item">
                <a href="${pageContext.request.contextPath}/views/play/event/list.jsp" class="page-menu__link">
                    <div class="page-menu__box"><span class="page-menu__name">BR 레시피</span></div>
                </a>
            </li>
            <li class="page-menu__item">
                <a href="${pageContext.request.contextPath}/views/play/event/list.jsp" class="page-menu__link">
                    <div class="page-menu__box"><span class="page-menu__name">마이플레이버 리스트</span></div>
                </a>
            </li>
        </ul>
    </nav>

    <!-- 본문 -->
    <div id="content" class="event-view event-view--play">
        <header class="page-header">
            <div class="page-header__container">
                <div class="page-header__content">
                    <h2 class="page-header__title">Event</h2>

                    <div class="event-view__header">
                        <p class="event-view__category">프로모션</p>

                        <h3 class="event-view__title">
                            포켓몬 랜덤 키링(6종 랜덤) 이벤트
                        </h3>

                        <p class="event-view__time">2025-12-09 ~ 소진 시 까지</p>
                    </div>
                </div>
            </div>
        </header>

        <!-- 상세 내용 1 -->
        <div class="event-view__content">
            <p style="text-align: center;">
                <img alt="" src="${pageContext.request.contextPath}/resources/images/upload/ckeditor/82e7c43999207a5b1ce822023b01b067.jpg" />
            </p>
        </div>

        <!-- 상세 내용 2 -->
        <div class="event-view__content">
            <p><strong><span style="font-size:12px; color:#999999;">[유의사항]</span></strong></p>
            <p><span style="font-size:12px; color:#999999;">
                - 영수증 당 1개 구매가능<br />
                - 행사일정 : 2025.12.09 ~ 소진 시<br />
                - 소진 시 행사 자동 종료(매장별 상이)<br />
                - 일부 매장 제외 (매장 사전 문의)<br />
                - 결제 금액의 5% 해피포인트 적립<br />
                - 상기 이미지는 연출된 컷으로 실 제품과 차이가 있을 수 있습니다.
            </span></p>
        </div>

        <!-- 행사 매장 보기 -->
        <div class="event-view__controll">
            <div class="event-view__inner">
                <!-- ✅ 버튼 클래스/셀렉터 통일: find-store-button -->
                <button type="button"
                        class="event-view__button event-view__button--round find-store-button">
                    행사 매장 보기
                </button>

                <!-- ✅ 팝업 마크업은 find-store 하나로 통일 -->
                <div class="find-store">
                    <div class="find-store__container">
                        <div class="find-store__content">
                            <h4 class="find-store__title">행사 매장 보기</h4>

                            <div class="find-store__box">
                                <ul class="find-store__list">
                                    <li class="find-store__item">가로수길</li>
                                    <li class="find-store__item">가산디지털단지역</li>
                                    <li class="find-store__item">강릉내곡</li>
                                    <li class="find-store__item">광주능평</li>
                                    <li class="find-store__item">광주삼각</li>
                                    <li class="find-store__item">광주회덕</li>
                                    <li class="find-store__item">광화문</li>
                                    <li class="find-store__item">굽은다리역</li>
                                    <li class="find-store__item">김해삼계</li>
                                    <li class="find-store__item">다산역</li>
                                    <li class="find-store__item">대구도남</li>
                                    <li class="find-store__item">대구동성로</li>
                                    <li class="find-store__item">대학로</li>
                                    <li class="find-store__item">마산합성</li>
                                    <li class="find-store__item">방이역</li>
                                    <li class="find-store__item">부산서면중앙</li>
                                    <li class="find-store__item">부산송정비치</li>
                                    <li class="find-store__item">부평명동</li>
                                    <li class="find-store__item">서초그랑자이</li>
                                    <li class="find-store__item">송도더스카이</li>
                                    <li class="find-store__item">송산그린시티</li>
                                    <li class="find-store__item">시청역</li>
                                    <li class="find-store__item">양재사옥</li>
                                    <li class="find-store__item">양재하나로</li>
                                    <li class="find-store__item">양주광적</li>
                                    <li class="find-store__item">양주회천</li>
                                    <li class="find-store__item">여수미평</li>
                                    <li class="find-store__item">용산센트럴파크</li>
                                    <li class="find-store__item">위례중앙</li>
                                    <li class="find-store__item">의정부송산</li>
                                    <li class="find-store__item">이천마장</li>
                                    <li class="find-store__item">중앙대</li>
                                    <li class="find-store__item">중화역</li>
                                    <li class="find-store__item">천호로데오</li>
                                    <li class="find-store__item">청담</li>
                                    <li class="find-store__item">파르나스몰</li>
                                    <li class="find-store__item">학동역</li>
                                    <li class="find-store__item">화성기산</li>
                                    <li class="find-store__item">화성봉담</li>
                                    <li class="find-store__item">화성비봉</li>
                                    <li class="find-store__item">휘경주공</li>
                                    <li class="find-store__item">SPC스퀘어</li>
                                    <li class="find-store__item">WORKSHOP</li>

                                    <!-- ✅ no-data는 숨김 기본 -->
                                    <li class="find-store__item find-store__item--no-data" style="display:none;">
                                        찾으시는 매장이 없습니다
                                    </li>
                                </ul>
                            </div>

                            <div class="find-store__search">
                                <input type="text" placeholder="찾으시는 매장명을 입력하세요" class="find-store__input">
                                <button type="button" class="find-store__search-button">
                                    <span class="find-store__hidden">검색</span>
                                </button>
                            </div>

                            <button type="button" class="find-store__close-button">
                                <span class="find-store__hidden">닫기</span>
                            </button>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <!-- 목록 버튼 -->
        <div class="event-view__box">
            <a href="list.jsp?category=ALL" class="event-view__button event-view__button--black">목록</a>
        </div>

        <!-- 진행중인 이벤트 -->
        <article class="event-view-list">
            <h3 class="event-view-list__title">진행중인 이벤트</h3>
            <ul class="event-view-list__list">
                <li class="event-view-list__item">
                    <a href="view.jsp?seq=453" class="event-view-list__link">
                        <div class="event-view-list__frame">
                            <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/3a198c8f4729ff3521621dd88a8b9d52.png"
                                 alt="12월 배라위크! 레디팩 2+1, 아메리카노 1+1"
                                 class="event-view-list__image">
                            <p class="event-view-list__count">D-00</p>
                        </div>
                        <div class="event-view-list__content">
                            <p class="event-view-list__category">프로모션</p>
                            <p class="event-view-list__name">12월 배라위크! 레디팩 2+1, 아메리카노 1+1</p>
                            <p class="event-view-list__time">2025-12-15 ~ 2025-12-21</p>
                        </div>
                    </a>
                </li>

                <li class="event-view-list__item">
                    <a href="view.jsp?seq=446" class="event-view-list__link">
                        <div class="event-view-list__frame">
                            <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/3147aa0e45352e88600c680545d67ac7.png"
                                 alt="12월 이달의 더블주니어!"
                                 class="event-view-list__image">
                            <p class="event-view-list__count">D-10</p>
                        </div>
                        <div class="event-view-list__content">
                            <p class="event-view-list__category">프로모션</p>
                            <p class="event-view-list__name">12월 이달의 더블주니어!</p>
                            <p class="event-view-list__time">2025-12-01 ~ 2025-12-31</p>
                        </div>
                    </a>
                </li>
            </ul>
        </article>

    </div>
</section>

<!-- FOOTER -->
<jsp:include page="/views/layout/footer.jsp" />

<!-- ✅ 스크립트는 반드시 body 끝에서, html 닫기 전에 -->
<script>
    (function() {
        const $popup = document.querySelector('.find-store');
        const $btn = document.querySelector('.find-store-button');
        const $close = document.querySelector('.find-store__close-button');
        const $input = document.querySelector('.find-store__input');
        const $items = Array.from(document.querySelectorAll('.find-store__list .find-store__item'))
            .filter(el => !el.classList.contains('find-store__item--no-data'));
        const $noData = document.querySelector('.find-store__item--no-data');

        function openPopup() {
            if ($popup) $popup.style.display = 'block';
        }
        function resetPopup() {
            if ($input) $input.value = '';
            $items.forEach(el => el.style.display = '');
            if ($noData) $noData.style.display = 'none';
        }
        function closePopup() {
            if ($popup) $popup.style.display = 'none';
            resetPopup();
        }

        if ($btn) $btn.addEventListener('click', openPopup);
        if ($close) $close.addEventListener('click', closePopup);

        if ($input) {
            $input.addEventListener('input', function() {
                const keyword = ($input.value || '').toLowerCase().trim();
                let found = false;

                $items.forEach(el => {
                    const text = (el.textContent || '').toLowerCase();
                    const show = keyword === '' || text.includes(keyword);
                    el.style.display = show ? '' : 'none';
                    if (show) found = true;
                });

                if ($noData) $noData.style.display = found ? 'none' : '';
            });
        }

        // 바깥 클릭 시 닫기
        document.addEventListener('click', function(e) {
            if (!$popup || $popup.style.display !== 'block') return;

            const insidePopup = $popup.contains(e.target);
            const isButton = $btn && ($btn === e.target || $btn.contains(e.target));

            if (!insidePopup && !isButton) {
                closePopup();
            }
        });

        // 초기 상태
        closePopup();
    })();
</script>

</body>
</html>
