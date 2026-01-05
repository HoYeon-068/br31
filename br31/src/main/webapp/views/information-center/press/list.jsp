<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>배스킨라빈스 | 보도자료</title>

   
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendors.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
    <script src="${pageContext.request.contextPath}/resources/js/vendors.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
</head>

<body>

<jsp:include page="/views/layout/header.jsp" />

<div class="site-container">

    <section id="content" class="press-list board-list">

        <header class="page-header">
            <div class="page-header__container">
                <div class="page-header__content">
                    <h2 class="page-header__title">보도자료</h2>
                </div>
                <div class="page-header__content">
                    <p class="page-header__text">
                        배스킨라빈스의 보도자료들을<br>
                        모아 보여드립니다.
                    </p>
                </div>
            </div>
        </header>

        <div class="board-list__content">
            <div class="board-list__table-wrap">

                <p class="board-list__total">총 <strong>397</strong>건</p>

                <table class="board-list__table">
                    <colgroup>
                        <col width="66px">
                        <col>
                        <col width="80px">
                    </colgroup>
                    <tbody>
                        <tr class="board-list__table-list">
                            <td class="board-list__table-number">397</td>
                            <td class="board-list__table-title">
                                <a href="view.do">
                                    SPC 배스킨라빈스, 전국 매장에서 만나는 미니 ‘쁘띠 케이크’ 출시
                                </a>
                            </td>
                            <td class="board-list__table-date">2025.12.19</td>
                        </tr>
                    </tbody>
                </table>

            </div>
        </div>

    </section>
</div>

<jsp:include page="/views/layout/footer.jsp" />

</body>
</html>
