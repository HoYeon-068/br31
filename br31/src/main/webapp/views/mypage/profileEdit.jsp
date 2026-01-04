<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
<meta property="og:url" content="https://www.baskinrobbins.co.kr">
<meta property="og:title" content="배스킨라빈스">
<meta property="og:description" content="행복을 전하는 프리미엄 아이스크림, 배스킨라빈스 공식 홈페이지 입니다.">
<meta property="og:image" content="https://www.baskinrobbins.co.kr${pageContext.request.contextPath}/resources/images/common/img_share.png">
<meta property="og:type" content="website">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendors.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/join.css" />

<script src="${pageContext.request.contextPath}/resources/js/vendors.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
</head>

<body>
<jsp:include page="/views/layout/header.jsp" />

<div class="page-title">
  <div class="title">프로필 수정</div>
</div>

<div class="frame">

<form class="join-form"
      action="${pageContext.request.contextPath}/mypage/profileEditSubmit.do"
      method="post">

  <!-- 서버로 체크값 전달  -->
  <input type="hidden" id="nicknameChecked" name="nicknameChecked" value="false" />
  <input type="hidden" id="emailChecked"    name="emailChecked"    value="false" />
  <input type="hidden" id="phoneChecked"    name="phoneChecked"    value="false" />

  <!-- ================== 프로필 이미지 ================== -->
  <div class="profile-line">

    <!-- 현재 프로필 썸네일(미리보기 대상) -->
    <c:choose>
      <c:when test="${not empty loginUser.profile_img_path}">
        <img id="profileThumb"
             src="${pageContext.request.contextPath}${loginUser.profile_img_path}"
             alt="profile"
             class="profile-thumb">
      </c:when>
      <c:otherwise>
        <img id="profileThumb"
             src="${pageContext.request.contextPath}/resources/images/mypage/img_profile_1.png"
             alt="profile"
             class="profile-thumb">
      </c:otherwise>
    </c:choose>

    <!-- A/B 선택 -->
    <div class="profile-select">
      <label class="profile-select__item">
        <input type="radio" name="profile_img" value="A"
          <c:if test="${empty loginUser.profile_img_path || loginUser.profile_img_path == '/resources/images/mypage/img_profile_1.png'}">checked</c:if>
        >
        <span class="profile-select__box">
          <img src="${pageContext.request.contextPath}/resources/images/mypage/img_profile_1.png" alt="프로필 A">
        </span>
      </label>

      <label class="profile-select__item">
        <input type="radio" name="profile_img" value="B"
          <c:if test="${loginUser.profile_img_path == '/resources/images/mypage/img_profile_2.png'}">checked</c:if>
        >
        <span class="profile-select__box">
          <img src="${pageContext.request.contextPath}/resources/images/mypage/img_profile_2.png" alt="프로필 B">
        </span>
      </label>
    </div>
  </div>

  <!-- ================== 기본정보 ================== -->
  <div class="row">
    <div class="lab">아이디</div>
    <div class="ctrl">
      <input type="text" value="${loginUser.user_id}" disabled="disabled"/>
      <input type="hidden" name="user_id" value="${loginUser.user_id}">
    </div>
  </div>

  <div class="row">
    <div class="lab">비밀번호</div>
    <div class="ctrl">
      <input type="password" name="oldPassword" placeholder="기존 비밀번호" />
    </div>
  </div>

  <div class="row">
    <div class="lab">새 비밀번호</div>
    <div class="ctrl">
      <input type="password" name="newPassword" placeholder="새 비밀번호를 입력해주세요" />
    </div>
  </div>

  <div class="row">
    <div class="lab">이름</div>
    <div class="ctrl">
      <input type="text" value="${loginUser.name}" disabled="disabled"/>
      <input type="hidden" name="name" value="${loginUser.name}">
    </div>
  </div>

  <!-- ================== 닉네임 ================== -->
  <div class="row">
    <div class="lab">닉네임</div>
    <div class="ctrl">
      <input type="text" name="nickname" id="nickname"
             value="${loginUser.nickname}"
             placeholder="닉네임을 입력해 주세요" />
      <button type="button" class="subbtn" id="btnNickCheck">중복확인</button>
    </div>
  </div>

  <!-- ================== 이메일 ================== -->
  <div class="row">
    <div class="lab">이메일</div>
    <div class="ctrl email">
      <!-- 간단히 전체 이메일을 분해하지 않고, 사용자가 다시 선택하도록 두고 싶으면 value는 비워도 됨 -->
      <c:set var="emailFull" value="${loginUser.email}" />
      <c:set var="emailId" value="${fn:split(emailFull,'@')[0]}" />
      <c:set var="emailDomain" value="${fn:split(emailFull,'@')[1]}" />

      <input type="text" name="email_id" id="email_id" value="${emailId}" placeholder="예: scoop" />
      <span class="at">@</span>

      <select name="email_domain" id="email_domain">
        <option value="">선택하기</option>
        <option value="naver.com" <c:if test="${emailDomain=='naver.com'}">selected</c:if>>naver.com</option>
        <option value="gmail.com" <c:if test="${emailDomain=='gmail.com'}">selected</c:if>>gmail.com</option>
        <option value="daum.net"  <c:if test="${emailDomain=='daum.net'}">selected</c:if>>daum.net</option>
        <option value="kakao.com" <c:if test="${emailDomain=='kakao.com'}">selected</c:if>>kakao.com</option>
      </select>

      <button type="button" class="subbtn" id="btnEmailCheck">중복확인</button>
    </div>
  </div>

  <!-- ================== 휴대폰 ================== -->
  <div class="row phone-row">
    <div class="lab">휴대폰</div>

    <div class="phone-wrap">
      <div class="ctrl phone">
        <input type="text" name="phone_no" id="phone_no"
               value="${loginUser.phone_no}"
               placeholder="숫자만 입력해주세요." />
        <button type="button" class="subbtn" id="btnPhoneSend">인증번호 받기</button>
      </div>

      <div class="ctrl phone verify">
        <input type="text" id="phone_code" placeholder="인증번호 6자리" />
        <button type="button" class="subbtn" id="btnPhoneVerify">인증확인</button>
      </div>

      <span class="msg" id="phoneMsg"></span>
    </div>
  </div>

  <div class="cta">
    <button type="submit" class="joinbtn">저장</button>
  </div>

</form>
</div>

<!--  1) 비밀번호 변경 AJAX (기존 유지)-->
<script>
document.querySelector(".join-form").addEventListener("submit", async function(e){
  const oldPwd = document.querySelector('input[name="oldPassword"]').value.trim();
  const newPwd = document.querySelector('input[name="newPassword"]').value.trim();

  // 비번 둘 다 비어있으면 그냥 통과(비번 변경 안 함)
  if(oldPwd === "" && newPwd === "") return;

  // 둘 중 하나만 입력하면 막기
  if(oldPwd === "" || newPwd === ""){
    e.preventDefault();
    alert("기존 비밀번호와 새 비밀번호를 모두 입력해주세요.");
    return;
  }

  e.preventDefault(); // 일단 멈추고 AJAX로 비번 변경부터

  const params = new URLSearchParams();
  params.append("oldPassword", oldPwd);
  params.append("newPassword", newPwd);

  const res = await fetch("${pageContext.request.contextPath}/mypage/pwdChange.do", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded; charset=UTF-8"},
    body: params.toString()
  });

  const text = await res.text();

  if(text.trim() === "OK"){
    this.submit(); // 비번 변경 성공 → 원래 form submit
  }else if(text.trim() === "WRONG_OLD"){
    alert("기존 비밀번호가 틀렸습니다.");
  }else{
    alert("비밀번호 변경 실패: " + text);
  }
});
</script>

<!--2) 프로필 A/B 선택 시 썸네일 미리보기-->
<script>
document.addEventListener("DOMContentLoaded", () => {
  const thumb = document.getElementById("profileThumb");
  const radios = document.querySelectorAll('input[name="profile_img"]');

  radios.forEach(r => {
    r.addEventListener("change", () => {
      if(!thumb) return;
      if (r.value === "A") thumb.src = "${pageContext.request.contextPath}/resources/images/mypage/img_profile_1.png";
      if (r.value === "B") thumb.src = "${pageContext.request.contextPath}/resources/images/mypage/img_profile_2.png";
    });
  });
});
</script>

</body>
</html>
