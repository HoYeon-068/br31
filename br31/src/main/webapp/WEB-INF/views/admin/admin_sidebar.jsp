<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<nav class="col-md-2 bg-dark text-white vh-100 p-3">
  <h5 class="mb-4">ADMIN</h5>

  <ul class="nav flex-column">
    <li class="nav-item mb-2">
      <a class="nav-link text-white" href="${pageContext.request.contextPath}/admin/main.do">관리자 메인</a>
    </li>

    <li class="nav-item mb-2">
      <a class="nav-link text-white menu-toggle" href="javascript:void(0);">
        상품 관리
      </a>
      <ul class="nav flex-column ms-3 submenu">
        <li class="nav-item">
          <a class="nav-link text-white" href="${pageContext.request.contextPath}/admin/main.do?view=productList">상품 목록</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="/admin/product/form.do">상품 등록</a>
        </li>
      </ul>
    </li>

    <li class="nav-item mb-2">
      <a class="nav-link text-white" href="/admin/notice/list.do">공지사항 관리</a>
    </li>

    <li class="nav-item mb-2">
      <a class="nav-link text-white" href="${pageContext.request.contextPath}/admin/main.do?view=FAQList">FAQ 관리</a>
    </li>

    <li class="nav-item mb-2">
      <a class="nav-link text-white" href="/admin/inquiry/list.do">1:1 문의 관리</a>
    </li>


	<!-- 회원 관리 -->
    <li class="nav-item mb-2">
      <a class="nav-link text-white" href="${pageContext.request.contextPath}/admin/main.do?view=adminUser">회원 관리</a>
    </li>
    
    
  </ul>
</nav>
