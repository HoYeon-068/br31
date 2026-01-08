<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/layout/header_inquiry.jsp" />

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/inquiry.css">

<div class="voc-page">

<c:if test="${not empty dto}">

    <div class="voc-visual">
        <div class="voc-visual__inner">
            <h2>MY <span>VOC</span></h2>
        </div>
    </div>

    <div class="voc-create">
        <div class="voc-form">

            <div class="form-row">
                <div class="form-label">구분</div>
                <div class="form-field">${dto.counselType}</div>

                <div class="form-label">분류</div>
                <div class="form-field">${dto.detailType}</div>
            </div>

            <div class="form-row title-row">
                <div class="form-label">제목</div>
                <div class="form-field">${dto.title}</div>
            </div>

            <div class="form-row">
                <div class="form-label">발생일시</div>
                <div class="form-field">
                    <fmt:formatDate value="${dto.occurDate}" pattern="yyyy-MM-dd"/>
                </div>
            </div>

            <div class="form-row">
                <div class="form-label">내용</div>
                <div class="form-field">
                    <div class="voc-content">
                        <c:out value="${dto.content}" escapeXml="false"/>
                    </div>
                </div>
            </div>

            <div class="form-row">
                <div class="form-label">답변내용</div>
                <div class="form-field">
                    <c:choose>
                        <c:when test="${empty dto.answer}">
                            아직 답변이 등록되지 않았습니다.
                        </c:when>
                        <c:otherwise>
                            <div class="voc-content">
                                <c:out value="${dto.answer}" escapeXml="false"/>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

        </div>

        <div class="voc-submit">
            <a href="${pageContext.request.contextPath}/inquiry/list.do">
                <button type="button">목록</button>
            </a>
        </div>
    </div>

</c:if>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
