<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>배스킨라빈스 | 공지사항</title>

 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendors.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
     <script src="${pageContext.request.contextPath}/resources/js/vendors.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
</head>

<body id="baskinrobbins-notice"
      class="baskinrobbins-notice">

<jsp:include page="/views/layout/header.jsp" />

<div class="site-container">

   
    <section id="content" class="notice-list board-view">

        <header class="page-header">
            <div class="page-header__container">
                <div class="page-header__content">
                    <h2 class="page-header__title">공지사항</h2>
                </div>
            </div>
        </header>

        <article class="board-view__article">

            <!-- 제목 / 날짜 -->
            <header class="board-view__header">
             <h3 class="board-view__title">${dto.TITLE}</h3>
                <p class="board-view__date">${dto.REG_DATE}</p>
            </header>

            <div class="board-view__container">
               <div class="board-view__content">
    					${dto.CONTENT}

                  
                    <p style="text-align:center;">
                        <img
                            src="${pageContext.request.contextPath}/resources/images/upload/ckeditor/415406dd385bb4cb55dea023f7440267.png"
                            alt="">
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
