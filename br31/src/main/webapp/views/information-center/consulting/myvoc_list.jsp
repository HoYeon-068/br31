<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/views/layout/header_inquiry.jsp" />

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/inquiry.css">
      

<div class="voc-page">

    <div class="voc-visual">
        <div class="voc-visual__inner">
            <h2>MY <span>VOC</span></h2>
        </div>
    </div>

    <div class="voc-search">
        <form>
            <label>접수일</label>
            <input type="text">
            <span>~</span>
            <input type="text">
            <button type="submit">검색</button>
        </form>
    </div>

    <table class="voc-table">
        <thead>
            <tr>
                <th>접수일</th>
                <th>발생일시</th>
                <th>구분</th>
                <th>분류</th>
                <th>제목</th>
                <th>상태</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td colspan="6" class="noData">
                    등록된 VOC가 없습니다.
                </td>
            </tr>
        </tbody>
    </table>

</div>

<jsp:include page="/views/layout/footer.jsp" />
