<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>배스킨라빈스 | 공정거래자율준수</title>

  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendors.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
     <script src="${pageContext.request.contextPath}/resources/js/vendors.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
</head>

<body id="baskinrobbins-fairtrade"
      class="baskinrobbins-fairtrade">

<jsp:include page="/views/layout/header.jsp" />

<div class="site-container">

  
    <nav class="page-menu">
        <ul class="page-menu__list">
            <li class="page-menu__item">
                <a href="../customer/list.do" class="page-menu__link">
                    <div class="page-menu__box">
                        <span class="page-menu__name">고객센터</span>
                    </div>
                </a>
            </li>
            <li class="page-menu__item">
                <a href="../customer/ccm.do" class="page-menu__link">
                    <div class="page-menu__box">
                        <span class="page-menu__name">소비자중심경영(CCM)</span>
                    </div>
                </a>
            </li>
            <li class="page-menu__item">
                <a href="../notice/list.do" class="page-menu__link">
                    <div class="page-menu__box">
                        <span class="page-menu__name">공지사항</span>
                    </div>
                </a>
            </li>
            <li class="page-menu__item">
                <a href="../press/list.do" class="page-menu__link">
                    <div class="page-menu__box">
                        <span class="page-menu__name">보도자료</span>
                    </div>
                </a>
            </li>
            <li class="page-menu__item page-menu__item--active">
                <a href="list.do" class="page-menu__link">
                    <div class="page-menu__box">
                        <span class="page-menu__name">공정거래자율준수</span>
                    </div>
                </a>
            </li>
        </ul>
    </nav>

 
    <section id="content" class="press-list board-view">

        <header class="page-header">
            <div class="page-header__container">
                <div class="page-header__content">
                    <h2 class="page-header__title">공정거래자율준수</h2>
                </div>
            </div>
        </header>

        <article class="board-view__article">

         
            <header class="board-view__header">
                <h3 class="board-view__title">
                    공정거래 자율준수 프로그램(CP) 도입 선포식 개최
                </h3>
                <p class="board-view__date">2024.09.30</p>
            </header>

           
            <div class="board-view__container">
                <div class="board-view__content">

                    <p style="text-align:center;">
                        <img
                            src="${pageContext.request.contextPath}/resources/images/upload/ckeditor/062d03aad76776bef4972de76d004627.jpg"
                            alt="">
                    </p>

                    <p style="text-align:center;">&nbsp;</p>

                    <p style="text-align:center;">
                        <strong>
                            <span style="font-size:18px;">
                                공정거래 자율준수 프로그램(CP) 도입 선포식
                            </span>
                        </strong>
                    </p>

                </div>
            </div>

           
            <p class="board-view__back">
                <a href="list.do">목록</a>
            </p>

        </article>

    </section>
</div>

<jsp:include page="/views/layout/footer.jsp" />

</body>
</html>
