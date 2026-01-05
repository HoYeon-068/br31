<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>배스킨라빈스 | 자주하는 질문</title>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/vendors.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/app.css">
<script src="${pageContext.request.contextPath}/resources/js/vendors.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>

<style>
/* FAQ 아이템 기본 */
.faq-list__item {
  position: relative;
  overflow: visible;
}

/* 답변 영역 (슬라이딩 핵심) */
.faq-list__answer {
  overflow: hidden;
  max-height: 0;
  margin-top: 16px;
  line-height: 1.6;
  transition: max-height 0.35s ease;
}



</style>


</head>

<body id="baskinrobbins-faq" class="baskinrobbins-faq">
	<c:set var="categoryId"
		value="${empty param.category ? 0 : param.category}" />


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
							고객의 소리를 적극 경청하고 고객만족향상 활동을<br> 지속적으로 실천합니다.
						</p>
					</div>
				</div>
			</header>

			<!-- 카테고리 탭 -->
			<nav class="page-tab">
				<ul class="page-tab__list">
					<li
						class="page-tab__item ${categoryId == 0 ? 'page-tab__item--active' : ''}">
						<a
						href="${pageContext.request.contextPath}/faq/list.do?category=0"
						class="page-tab__link">전체</a>
					</li>

					<li
						class="page-tab__item ${categoryId == 1 ? 'page-tab__item--active' : ''}">
						<a
						href="${pageContext.request.contextPath}/faq/list.do?category=1"
						class="page-tab__link">제품</a>
					</li>

					<li
						class="page-tab__item ${categoryId == 2 ? 'page-tab__item--active' : ''}">
						<a
						href="${pageContext.request.contextPath}/faq/list.do?category=2"
						class="page-tab__link">포인트</a>
					</li>

					<li
						class="page-tab__item ${categoryId == 3 ? 'page-tab__item--active' : ''}">
						<a
						href="${pageContext.request.contextPath}/faq/list.do?category=3"
						class="page-tab__link">회원</a>
					</li>

					<li
						class="page-tab__item ${categoryId == 4 ? 'page-tab__item--active' : ''}">
						<a
						href="${pageContext.request.contextPath}/faq/list.do?category=4"
						class="page-tab__link">기타</a>
					</li>

				</ul>
			</nav>

			<!-- FAQ 목록 -->
			<section class="faq-list__container">
				<div class="faq-list__content">
    <ul class="faq-list__list">
  <c:forEach var="dto" items="${list}">
<li class="faq-list__item">
  <button type="button" class="faq-list__title">
    ${dto.question}
  </button>

  <div class="faq-list__answer">
    <p>${dto.answer}</p>
  </div>
</li>



  </c:forEach>
</ul>

</div>


				<!-- 페이징 -->
				<ul class="pagination">

					<c:if test="${startPage > 1}">
						<li
							class="pagination__item pagination__item--icon pagination__item--prev">
							<a class="pagination__link"
							href="${pageContext.request.contextPath}/faq/list.do?category=${categoryId}&page=${startPage - 1}">
								이전 </a>
						</li>
					</c:if>

					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<li
							class="pagination__item ${i == currentPage ? 'pagination__item--current' : ''}">
							<a class="pagination__link"
							href="${pageContext.request.contextPath}/faq/list.do?category=${categoryId}&page=${i}">
								${i} </a>
						</li>
					</c:forEach>

					<c:if test="${endPage < totalPage}">
						<li
							class="pagination__item pagination__item--icon pagination__item--next">
							<a class="pagination__link"
							href="${pageContext.request.contextPath}/faq/list.do?category=${categoryId}&page=${endPage + 1}">
								다음 </a>
						</li>
					</c:if>

				</ul>


			</section>
		</section>
	</div>
<script>
document.querySelectorAll(".faq-list__title").forEach(btn => {
  btn.addEventListener("click", function () {

    const currentItem = this.closest(".faq-list__item");
    const currentAnswer = currentItem.querySelector(".faq-list__answer");

    // 다른 FAQ 닫기
    document.querySelectorAll(".faq-list__item").forEach(item => {
      if (item !== currentItem) {
        item.classList.remove("is-open");
        const answer = item.querySelector(".faq-list__answer");
        answer.style.maxHeight = null;
      }
    });

    // 현재 FAQ 토글
    if (currentItem.classList.contains("is-open")) {
      currentItem.classList.remove("is-open");
      currentAnswer.style.maxHeight = null;
    } else {
      currentItem.classList.add("is-open");
      currentAnswer.style.maxHeight = currentAnswer.scrollHeight + "px";
    }
  });
});
</script>




	<jsp:include page="/views/layout/footer.jsp" />



</body>
</html>
