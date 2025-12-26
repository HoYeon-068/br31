<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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



<script type="module" src="https://bks0c7yrb0.execute-api.ap-northeast-2.amazonaws.com/v1/api/fontstream/djs/?sid=gAAAAABk3G1_eyGB8FmZaMXgewjzvKQwe0I-4Kj9Xj-dKpNnUlp_rsk4w6Z_0UeYWyfihX4Dle9eu9HBqxj-2haSIR5ke8aarBIUuDqDVOLuImctKnYplmDTPSV-Bfn2TzQR4jSr7yknqw7gbTlj_xE3x62PMBY9Y3jC5rjtwuoBrWb2FaAY21Z2idAGvnk9xlfgI9CdciJwW6IGsijBsI592KNSqOLc9CQ4zV1Jziva1IN_NNxkzeG_pkU7_0TogufO4qTNTYRr" charset="utf-8"></script>

<script src="${pageContext.request.contextPath}/resources/js/vendors.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/join.css" />
</head>



<body>

<jsp:include page="/views/layout/header.jsp" />

  <div class="page-title">
	  <div class="title">회원가입</div>
  </div>


<div class="frame">
  <!-- 안내 -->
  <div class="topline">
    <span class="req"><b>*</b> 모두 필수 입력</span>
  </div>

  <!-- 폼 -->
  <form class="join-form">
    <div class="row">
      <div class="lab">아이디</div>
      <div class="ctrl">
        <input type="text" placeholder="아이디를 입력해주세요" />
        <button type="button" class="subbtn">중복확인</button>
      </div>
    </div>

    <div class="row">
      <div class="lab">비밀번호</div>
      <div class="ctrl">
        <input type="password" placeholder="비밀번호를 입력해주세요" />
      </div>
    </div>

    <div class="row">
      <div class="lab">비밀번호확인</div>
      <div class="ctrl">
        <input type="password" placeholder="비밀번호를 한번 더 입력해주세요" />
      </div>
    </div>

    <div class="row">
      <div class="lab">이름</div>
      <div class="ctrl">
        <input type="text" placeholder="이름을 입력해 주세요" />
      </div>
    </div>

    <div class="row">
      <div class="lab">닉네임</div>
      <div class="ctrl">
        <input type="text" placeholder="닉네임을 입력해 주세요" />
        <button type="button" class="subbtn">중복확인</button>
      </div>
    </div>

    <div class="row">
      <div class="lab">이메일</div>
      <div class="ctrl email">
        <input type="text" placeholder="예: scoop" />
        <span class="at">@</span>
        
        <%
        	String [] domains = {"naver.com","gmail.com","daum.net","hanmail.net"
        						,"kakao.com","yahoo.co.kr","직접입력"};
        %>
        
        <select>
          <option value="">선택하기</option>
          <%
          	for(String d: domains){
          %>
			<option value="<%= d%>"><%= d%></option>          
          <%
          	}
          %>
        </select>
        
        <!--  이메일 직접 입력용 input 구현 필요!!  -->
        
        
      </div>
    </div>

    <div class="row">
      <div class="lab">휴대폰</div>
      <div class="ctrl phone">
        <input type="text" placeholder="숫자만 입력해주세요." />
        <button type="button" class="subbtn">인증번호 받기</button>
      </div>
    </div>

    <div class="row">
      <div class="lab">성별</div>
      <div class="ctrl radios">
        <label><input type="radio" name="gender" /> 남자</label>
        <label><input type="radio" name="gender" /> 여자</label>
        <label><input type="radio" name="gender" checked /> 선택안함</label>
      </div>
    </div>

    <div class="row">
      <div class="lab">생년월일</div>
      <div class="ctrl birth">
		<select name="birthY" >
			<option value="">YYYY</option>		
		<%
			int thisYear = LocalDate.now().getYear();
			for(int i = thisYear; i>= 1900; i--){
		%>				
			<option value="<%= i%>"><%= i%></option>
		<%
			}
		%>
		</select>
		<select name="birthM"  >
			<option value="">MM</option>		
		<%
			for(int i = 1; i<=12; i++){
		%>				
			<option value="<%= i%>"><%= i%></option>
		<%
			}
		%>
		</select>
		<select name="birthD" >
			<option value="">DD</option>		
		<%
			for(int i = 1; i<=31; i++){
		%>				
			<option value="<%= i%>"><%= i%></option>
		<%
			}
		%>
		</select>
      </div>
    </div>
  </form>

  <!-- 약관 구분선 -->
  <div class="divider"></div>

  <!-- 약관 -->
  <div class="terms-wrap">
    <div class="terms-title">이용약관동의</div>

    <div class="terms-box">
      <label class="trow all">
        <input type="checkbox" />
        <span>전체 동의합니다.</span>
      </label>
      <p class="t-desc">
        선택항목에 동의하지 않은 경우도 회원가입 및 일반적인 서비스 이용은 가능합니다.
      </p>

      <div class="tlist">
        <label class="trow">
          <input type="checkbox" />
          <span>이용약관 동의 (필수)</span>
          <a href="#" class="tlink">약관보기 &gt;</a>
        </label>

        <label class="trow">
          <input type="checkbox" />
          <span>개인정보 수집·이용 동의 (필수)</span>
          <a href="#" class="tlink">약관보기 &gt;</a>
        </label>

        <label class="trow">
          <input type="checkbox" />
          <span>마케팅 광고 활용을 위한 수집 및 이용 동의 (선택)</span>
          <a href="#" class="tlink">약관보기 &gt;</a>
        </label>

        <label class="trow">
          <input type="checkbox" />
          <span>이벤트 등 프로모션 정보 수신 동의 (선택)</span>
        </label>

        <div class="t-sub">
          <label><input type="checkbox" /> SMS</label>
          <label><input type="checkbox" /> 이메일</label>
        </div>

        <label class="trow">
          <input type="checkbox" />
          <span>본인은 만 14세 이상입니다. (필수)</span>
        </label>
      </div>
    </div>
  </div>

  <!-- 가입 버튼 -->
  <div class="cta">
    <button type="button" class="joinbtn">가입하기</button>
  </div>
</div>

  </body>
<jsp:include page="/views/layout/footer.jsp" />
