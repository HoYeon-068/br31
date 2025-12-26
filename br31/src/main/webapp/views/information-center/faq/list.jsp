<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>배스킨라빈스 | 자주하는 질문</title>

  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendors.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
     <script src="${pageContext.request.contextPath}/resources/js/vendors.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
</head>

<body id="baskinrobbins-faq"
      class="baskinrobbins-faq">

<jsp:include page="/views/layout/header.jsp" />

<div class="site-container">
    <section id="content" class="faq-list">

        <header class="page-header">
            <div class="page-header__container">
                <div class="page-header__content">
                    <h2 class="page-header__title">자주하는 질문</h2>
                </div>
                <div class="page-header__content">
                    <p class="page-header__text">
                        고객의 소리를 적극 경청하고 고객만족향상 활동을<br>
                        지속적으로 실천합니다.
                    </p>
                </div>
            </div>
        </header>

        <!-- 카테고리 탭 -->
        <nav class="page-tab">
            <ul class="page-tab__list">
                <li class="page-tab__item page-tab__item--active">
                    <a href="list.do" class="page-tab__link">
                        <span class="page-tab__text">전체</span>
                    </a>
                </li>
                <li class="page-tab__item">
                    <a href="list.do?category=A" class="page-tab__link">
                        <span class="page-tab__text">제품</span>
                    </a>
                </li>
                <li class="page-tab__item">
                    <a href="list.do?category=B" class="page-tab__link">
                        <span class="page-tab__text">포인트</span>
                    </a>
                </li>
                <li class="page-tab__item">
                    <a href="list.do?category=C" class="page-tab__link">
                        <span class="page-tab__text">회원</span>
                    </a>
                </li>
                <li class="page-tab__item">
                    <a href="list.do?category=D" class="page-tab__link">
                        <span class="page-tab__text">기타</span>
                    </a>
                </li>
            </ul>
        </nav>

        <!-- FAQ 목록 -->
        <section class="faq-list__container">
            <div class="faq-list__content">
                <ul class="faq-list__list">

                    <li class="faq-list__item">
                        <button type="button" class="faq-list__title">
                            가격 인상 후 모바일 교환권 사용 시 해피포인트는 어떻게 적립 되나요?
                        </button>
                        <div class="faq-list__answer">
                            <p>
                                가격 조정 이전 구매된 교환권은 사용 시
                                인상 전 교환권 가격(액면가)의 0.1%가 적립됩니다.
                            </p>
                        </div>
                    </li>

                    <li class="faq-list__item">
                        <button type="button" class="faq-list__title">
                            가격 인상 후 단종된 제품의 모바일 교환권은 어떻게 사용하나요?
                        </button>
                        <div class="faq-list__answer">
                            <p>
                                단종 제품 모바일 교환권은 인상 전 판매가 기준
                                다른 제품으로 교환하여 구매 진행 가능합니다.<br>
                                [예시] 찰떡콩떡 블라스트(Regular) – 단종 제품,
                                인상 전 가격인 4,800원으로 제품 교환 가능
                            </p>
                        </div>
                    </li>

                    <li class="faq-list__item">
                        <button type="button" class="faq-list__title">
                            모든 제품의 가격이 인상되었나요?
                        </button>
                        <div class="faq-list__answer">
                            <p>
                                아이스크림, 디저트, 아이스크림 음료류 가격만 인상되었으며
                                커피 및 아이스크림 케이크 제품은 변동되지 않습니다.
                            </p>
                        </div>
                    </li>

                    <li class="faq-list__item">
                        <button type="button" class="faq-list__title">
                            와플콘 전용매장은 무엇인가요?
                        </button>
                        <div class="faq-list__answer">
                            <p>
                                배스킨라빈스는 고객님들께 참신한 미식경험을 선사하기 위해
                                프리미엄 직제조 와플콘 전용매장을 운영 중에 있습니다.
                            </p>
                        </div>
                    </li>

                </ul>
            </div>

            <!-- 페이징 -->
            <ul class="pagination">
                <li class="pagination__item pagination__item--icon pagination__item--prev pagination__item--disabled">
                    <a href="#" class="pagination__link">
                        <span class="pagination__name">이전</span>
                    </a>
                </li>

                <li class="pagination__item pagination__item--current">
                    <strong class="pagination__link">
                        <span class="pagination__name">1</span>
                    </strong>
                </li>

                <li class="pagination__item">
                    <a href="#" class="pagination__link">
                        <span class="pagination__name">2</span>
                    </a>
                </li>

                <li class="pagination__item pagination__item--icon pagination__item--next">
                    <a href="#" class="pagination__link">
                        <span class="pagination__name">다음</span>
                    </a>
                </li>
            </ul>

        </section>
    </section>
</div>

<jsp:include page="/views/layout/footer.jsp" />

</body>
</html>
