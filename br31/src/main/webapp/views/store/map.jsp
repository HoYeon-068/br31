<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" /><!-- /Added by HTTrack -->
<head>
    <meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<title>배스킨라빈스</title>

<meta name="description" content="행복을 전하는 프리미엄 아이스크림, 배스킨라빈스 공식 홈페이지 입니다.">
<meta name="keywords" content="baskinrobbins, br31, 배스킨라빈스, 배라, 베라">
<meta name="author" content="배스킨라빈스">
<meta property="og:site_name" content="배스킨라빈스">
<meta property="og:url" content="../index.html">
<meta property="og:title" content="[배스킨라빈스] 매장찾기">
<meta property="og:description" content="가까운 배스킨라빈스 매장을 찾아보세요.">
<meta property="og:image" content="${pageContext.request.contextPath}/resources/images/common/img_share.png">
<meta property="og:type" content="website">


<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendors.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sdk1659.js?appkey=8c71dfbb0129b7e25a985c72328e967b"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/vendors.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>

</head>
<body id="baskinrobbins-store-map" class="baskinrobbins-store-map">

<div class="skipnav"><a href="#content">본문 영역으로 바로가기</a></div>

<jsp:include page="/views/layout/header.jsp" />
<div class="site-container">
    <nav class="page-menu">
        <ul class="page-menu__list">
            <!--            현재 페이지인 경우 page-menu__item--active 추가-->
            <li class="page-menu__item page-menu__item--active">
                <a href="map.html" class="page-menu__link">
                    <div class="page-menu__box">
                        <span class="page-menu__name">매장 찾기</span>
                    </div>
                </a>
            </li>
            <li class="page-menu__item">
                <a href="flavor.html" class="page-menu__link">
                    <div class="page-menu__box">
                        <span class="page-menu__name">100 flavor</span>
                    </div>
                </a>
            </li>
            <li class="page-menu__item">
                <a href="workshop.html" class="page-menu__link">
                    <div class="page-menu__box">
                        <span class="page-menu__name">Workshop</span>
                    </div>
                </a>
            </li>
            <li class="page-menu__item">
                <a href="delivary.html" class="page-menu__link">
                    <div class="page-menu__box">
                        <span class="page-menu__name">배달주문</span>
                    </div>
                </a>
            </li>
            <li class="page-menu__item">
                <a href="catering.html" class="page-menu__link">
                    <div class="page-menu__box">
                        <span class="page-menu__name">단체주문</span>
                    </div>
                </a>
            </li>
        </ul>
    </nav>

    <section id="content" class="store-map">
        <header class="page-header">
            <div class="page-header__container">
                <div class="page-header__content">
                    <h2 class="page-header__title">
                        Store
                    </h2>
                </div>
            </div>
        </header>

        <div class="store-map__container">
            <div class="store-map__content">
                <form action="${pageContext.request.contextPath}/views/api/store-list.jsp" class="store-map-form" method="get">
                    <fieldset class="store-map-form__fieldset">
                        <legend>매장 찾기</legend>

                        <div class="store-map-option">
                            <button type="button" class="store-map__button store-map-option__button">
                                옵션 선택
                            </button>

                            <div class="store-map-option__content">
                                <dl class="store-map-option__list">
                                    <div class="store-map-option__item store-type">
                                        <dt class="store-map-option__name">매장타입</dt>
                                        <dd class="store-map-option__area">
                                            <label class="store-map-option__label">
                                                <input type="checkbox" class="store-map-option__input" name="store_type" value="A">
                                                <span class="store-map-option__text">BR 31</span>
                                            </label>
                                        </dd>
                                        <dd class="store-map-option__area">
                                            <label class="store-map-option__label">
                                                <input type="checkbox" class="store-map-option__input" name="store_type" value="B">
                                                <span class="store-map-option__text">100flavor</span>
                                            </label>
                                        </dd>
                                    </div>
                                    <div class="store-map-option__item service-info">
                                        <dt class="store-map-option__name">제공 서비스</dt>
                                        <dd class="store-map-option__area">
                                            <label class="store-map-option__label">
                                                <input type="checkbox" class="store-map-option__input" name="service_info" value="A">
                                                <span class="store-map-option__text">주차</span>
                                            </label>
                                        </dd>
                                        <dd class="store-map-option__area">
                                            <label class="store-map-option__label">
                                                <input type="checkbox" class="store-map-option__input" name="service_info" value="B">
                                                <span class="store-map-option__text">배달</span>
                                            </label>
                                        </dd>
                                        <dd class="store-map-option__area">
                                            <label class="store-map-option__label">
                                                <input type="checkbox" class="store-map-option__input" name="service_info" value="C">
                                                <span class="store-map-option__text">픽업</span>
                                            </label>
                                        </dd>
                                        <dd class="store-map-option__area">
                                            <label class="store-map-option__label">
                                                <input type="checkbox" class="store-map-option__input" name="service_info" value="D">
                                                <span class="store-map-option__text">취식여부</span>
                                            </label>
                                        </dd>
                                        <dd class="store-map-option__area">
                                            <label class="store-map-option__label">
                                                <input type="checkbox" class="store-map-option__input" name="service_info" value="E">
                                                <span class="store-map-option__text">해피스테이션</span>
                                            </label>
                                        </dd>
                                        <dd class="store-map-option__area">
                                            <label class="store-map-option__label">
                                                <input type="checkbox" class="store-map-option__input" name="service_info" value="F">
                                                <span class="store-map-option__text">가챠머신</span>
                                            </label>
                                        </dd>

                                        <dd class="store-map-option__area">
                                            <label class="store-map-option__label">
                                                <input type="checkbox" class="store-map-option__input" name="service_info" value="I">
                                                <span class="store-map-option__text">KT 제휴</span>
                                            </label>
                                        </dd>

                                        <dd class="store-map-option__area">
                                            <label class="store-map-option__label">
                                                <input type="checkbox" class="store-map-option__input" name="service_info" value="J">
                                                <span class="store-map-option__text">SKT 제휴</span>
                                            </label>
                                        </dd>
                                        
                                        <dd class="store-map-option__area">
                                            <label class="store-map-option__label">
                                                <input type="checkbox" class="store-map-option__input" name="service_info" value="K">
                                                <span class="store-map-option__text">LGU+ 제휴</span>
                                            </label>
                                        </dd>
                                        
                                        <dd class="store-map-option__area">
                                            <label class="store-map-option__label">
                                                <input type="checkbox" class="store-map-option__input" name="service_info" value="L">
                                                <span class="store-map-option__text">맛보기 제휴</span>
                                            </label>
                                        </dd>                                        
                                        
                                        



                                    </div>
                                </dl>
                            </div>
                        </div>

                        <div class="store-map-city">
                            <select name="sido" class="store-map__button store-map-city__select dosi">
                                <option value="">도/시 선택</option>
                            </select>
                            <select name="gugun" class="store-map__button store-map-city__select gugun">
                                <option value="">구/군 선택</option>
                            </select>
                        </div>
                    </fieldset>

                    <div class="store-map-input">
                        <input type="text" placeholder="매장명" class="store-map-input__input store-name" name="store_name">
                    </div>

                    <button type="submit" class="store-map__submit">검색</button>
                </form>

                <div class="store-map-list">
                    <p class="store-map-list__result">검색결과 <span class="store-map-list__point-color"></span>개</p>
                    <div class="store-map-list__container">
                        <ul class="store-map-list__list">
                        <li class="store-map-list__item">
                        <a href="#" role="button" type="button" data-info="{&quot;store_type&quot;:&quot;B&quot;,&quot;store_type_name&quot;:&quot;BR 100flavor&quot;,&quot;store_code&quot;:&quot;61230&quot;,&quot;direct_div&quot;:&quot;Y&quot;,&quot;store_name&quot;:&quot;SPC스퀘어&quot;,&quot;local_no&quot;:&quot;02-565-1012&quot;,&quot;open_date&quot;:&quot;2022-04-29&quot;,&quot;operation_time&quot;:&quot;AM 10~PM 11&quot;,&quot;latitude&quot;:37.4943827585735,&quot;longitude&quot;:127.029802084664,&quot;addr_zipcode&quot;:&quot;AM 10~PM 11&quot;,&quot;addr_si&quot;:&quot;서울특별시&quot;,&quot;addr_gugun&quot;:&quot;강남구&quot;,&quot;addr_road&quot;:&quot;역삼동 831-23 2층&quot;,&quot;addr_detail&quot;:&quot;&quot;,&quot;service_info&quot;:&quot;주차, 배달, 픽업, 취식여부, 해피스테이션, 가챠머신, KT제휴, SKT제휴, LG U+제휴, 맛보기&quot;,&quot;distance&quot;:442.57112232945,&quot;index&quot;:0,&quot;seq&quot;:1}" class="store-map-list__button">
                            <div class="store-map-list__box">
                                <h3 class="store-map-list__title">SPC스퀘어</h3>
                                <address class="store-map-list__address">서울특별시 강남구 역삼동 831-23 2층 </address>
                                <dl class="store-map-list__content">
                                <dt class="store-map-list__name">연락처</dt>
                                <dd class="store-map-list__text">02-565-1012</dd>
                                <dt class="store-map-list__name">운영시간</dt>
                                <dd class="store-map-list__text">AM 10~PM 11</dd>
                                </dl>
                            </div>
                        </a>
                    </li>
                        </ul>
                    </div>
                </div>
            </div>

                        <div class="store-map-promotion">
                <button type="button" class="store-map-promotion__button">
                    <span class="store-map-promotion__button-text">매장 프로모션</span>
                </button>

                <ul class="store-map-promotion__list">
                                        <li class="store-map-promotion__item">
                        <a href="../play/event/viewe0b6.html?seq=438" class="store-map-promotion__link">
                            <div class="store-map-promotion__frame">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/7d9230dfb8da0d88e774f809cd8a341d.png" alt="파인트&#40;9,800원&#41; 이상 구매 시, 르세라핌 미니 셀피 SET 900원" class="store-map-promotion__image">
                            </div>
                            <p class="store-map-promotion__name">파인트&#40;9,800원&#41; 이상 구매 시, 르세라핌 미니 셀피 SET 900원</p>
                            <p class="store-map-promotion__date">2025-11-28 ~ 소진시 까지</p>
                        </a>
                    </li>
                                        <li class="store-map-promotion__item">
                        <a href="../play/event/view7d49.html?seq=437" class="store-map-promotion__link">
                            <div class="store-map-promotion__frame">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/f04a5f9b4383cb1d4b56d8626d7bd62c.png" alt="11월 배라위크! 레디팩 2&#43;2" class="store-map-promotion__image">
                            </div>
                            <p class="store-map-promotion__name">11월 배라위크! 레디팩 2&#43;2</p>
                            <p class="store-map-promotion__date">2025-11-24 ~ 2025-11-30</p>
                        </a>
                    </li>
                                        <li class="store-map-promotion__item">
                        <a href="../play/event/view8302.html?seq=436" class="store-map-promotion__link">
                            <div class="store-map-promotion__frame">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/b8a1c48b0e4a9e2e512301db4425286c.png" alt="크리스마스 아이스크림 케이크 사전예약 최대 30&#37; 혜택!" class="store-map-promotion__image">
                            </div>
                            <p class="store-map-promotion__name">크리스마스 아이스크림 케이크 사전예약 최대 30&#37; 혜택!</p>
                            <p class="store-map-promotion__date">2025-11-19 ~ 2025-12-16</p>
                        </a>
                    </li>
                                        <li class="store-map-promotion__item">
                        <a href="../play/event/view800d.html?seq=428" class="store-map-promotion__link">
                            <div class="store-map-promotion__frame">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/6c37e0ec06855a59b42ab74aec30a9aa.png" alt="11월 이달의 더블주니어! 이달의 맛 선택 시, 500원 추가하면 싱글레귤러를 더블주니어로 더블업!" class="store-map-promotion__image">
                            </div>
                            <p class="store-map-promotion__name">11월 이달의 더블주니어! 이달의 맛 선택 시, 500원 추가하면 싱글레귤러를 더블주니어로 더블업!</p>
                            <p class="store-map-promotion__date">2025-11-01 ~ 2025-11-30</p>
                        </a>
                    </li>
                                        <li class="store-map-promotion__item">
                        <a href="../play/event/view360b.html?seq=427" class="store-map-promotion__link">
                            <div class="store-map-promotion__frame">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/a1298e563c4bbaf1ce543d933b79c3ba.png" alt="11월 이달의 맛, 인스타그램에 사진을 올려주세요!" class="store-map-promotion__image">
                            </div>
                            <p class="store-map-promotion__name">11월 이달의 맛, 인스타그램에 사진을 올려주세요!</p>
                            <p class="store-map-promotion__date">2025-11-01 ~ 2025-11-26</p>
                        </a>
                    </li>
                                        <li class="store-map-promotion__item">
                        <a href="../play/event/view1148.html?seq=396" class="store-map-promotion__link">
                            <div class="store-map-promotion__frame">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/8c4602593cd42afb7a33e3351eeca9f6.png" alt="쿼터 이상 구매 시, 스머프 랜덤 키링 2,900원!" class="store-map-promotion__image">
                            </div>
                            <p class="store-map-promotion__name">쿼터 이상 구매 시, 스머프 랜덤 키링 2,900원!</p>
                            <p class="store-map-promotion__date">2025-08-14 ~ 소진시 까지</p>
                        </a>
                    </li>
                                        <li class="store-map-promotion__item">
                        <a href="../play/event/view6919.html?seq=385" class="store-map-promotion__link">
                            <div class="store-map-promotion__frame">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/0e55f845f27237246e26c604c219e7b6.png" alt="모구모구 블라스트 1종 구매 시, 모구모구 PET 음료 1개 증정" class="store-map-promotion__image">
                            </div>
                            <p class="store-map-promotion__name">모구모구 블라스트 1종 구매 시, 모구모구 PET 음료 1개 증정</p>
                            <p class="store-map-promotion__date">2025-07-22 ~ 소진시 까지</p>
                        </a>
                    </li>
                                        <li class="store-map-promotion__item">
                        <a href="../play/event/view2f98.html?seq=380" class="store-map-promotion__link">
                            <div class="store-map-promotion__frame">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/0b4516c5dd11234d7a74eabcb64dcb08.png" alt="민생회복 소비쿠폰 사용 가능" class="store-map-promotion__image">
                            </div>
                            <p class="store-map-promotion__name">민생회복 소비쿠폰 사용 가능</p>
                            <p class="store-map-promotion__date">2025-07-21 ~ 소진시 까지</p>
                        </a>
                    </li>
                                        <li class="store-map-promotion__item">
                        <a href="../play/event/viewb2b1.html?seq=342" class="store-map-promotion__link">
                            <div class="store-map-promotion__frame">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/e5546c5856ce3916f7dab4326579054a.png" alt="해피볼 티니핑 요정 프린세스 랜덤 피규어" class="store-map-promotion__image">
                            </div>
                            <p class="store-map-promotion__name">해피볼 티니핑 요정 프린세스 랜덤 피규어</p>
                            <p class="store-map-promotion__date">2025-05-01 ~ 소진시 까지</p>
                        </a>
                    </li>
                                        <li class="store-map-promotion__item">
                        <a href="../play/event/view68db.html?seq=99" class="store-map-promotion__link">
                            <div class="store-map-promotion__frame">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/0143a84def93d9c70ed42612fb8af488.png" alt="워크샵 by 배스킨라빈스 그랜드 오픈" class="store-map-promotion__image">
                            </div>
                            <p class="store-map-promotion__name">워크샵 by 배스킨라빈스 그랜드 오픈</p>
                            <p class="store-map-promotion__date">상시진행</p>
                        </a>
                    </li>
                                        <li class="store-map-promotion__item">
                        <a href="../play/event/viewd9d9.html?seq=2" class="store-map-promotion__link">
                            <div class="store-map-promotion__frame">
                                <img src="${pageContext.request.contextPath}/resources/images/upload/promotion/event/388539cae1fd5d2f219392ad712b21ff.png" alt="1회용 컵 사용 줄이기 안내" class="store-map-promotion__image">
                            </div>
                            <p class="store-map-promotion__name">1회용 컵 사용 줄이기 안내</p>
                            <p class="store-map-promotion__date">상시진행</p>
                        </a>
                    </li>
                    

                </ul>
            </div>
            
            <div id="store-map-field" class="store-map-field"></div>
        </div>
    </section>
</div>
<jsp:include page="/views/layout/footer.jsp" />




<script>
/* ===============================
   기본 변수
================================ */
var contextPath = "${pageContext.request.contextPath}";
var map = null;
var markers = [];

/* ===============================
   매장 타입 정규화
================================ */
function normalizeStoreType(type) {
  if (!type) return "BR 31";
  if (type.includes("100")) return "100 FLAVOR";
  return "BR 31";
}

/* ===============================
   타입별 마커 아이콘 경로
   ★ 파일명은 네 실제 리소스에 맞게 수정
================================ */
var markerIconMap = {
  "BR 31": contextPath + "/resources/images/store/map/icon_map_marker_default.png",
  "100 FLAVOR": contextPath + "/resources/images/store/map/icon_map_marker_flavors.png"
};

/* ===============================
   MarkerImage 생성
================================ */
function makeMarkerImage(src) {
  var size = new kakao.maps.Size(137, 106);
  var offset = new kakao.maps.Point(36, 106);
  return new kakao.maps.MarkerImage(src, size, { offset: offset });
}

/* ===============================
   기존 마커 제거
================================ */
function clearMarkers() {
  markers.forEach(function(m) {
    m.setMap(null);
  });
  markers = [];
}

/* ===============================
   지도 초기화 (1회)
================================ */
$(function () {
  var container = document.getElementById("store-map-field");
  if (container && kakao.maps) {
    map = new kakao.maps.Map(container, {
      center: new kakao.maps.LatLng(37.5665, 126.9780),
      level: 6
    });
  }
});

/* ===============================
   검색 버튼 클릭
================================ */
$("button.store-map__submit").on("click", function (e) {
  e.preventDefault();

  $(".store-map-list__list").empty();

  $.ajax({
    url: contextPath + "/store/mapSearch.ajax",
    type: "GET",
    dataType: "json",
    cache: false,

    success: function (data) {
      console.log("응답 데이터:", data);

      if (!Array.isArray(data)) return;

      $("span.store-map-list__point-color").text(data.length);

      clearMarkers();

      $.each(data, function (index, store) {

        /* ===== 값 정리 ===== */
        var storeType = normalizeStoreType(store.storeType);
        var iconSrc = markerIconMap[storeType];

        var addrDetail = (store.addressDetail === "null" || store.addressDetail == null)
                          ? "" : store.addressDetail;

        /* ===== data-info ===== */
        var info = {
          index: index,
          latitude: store.latitude,
          longitude: store.longitude
        };

        var encodedInfo = encodeURIComponent(JSON.stringify(info));

        /* ===== 리스트 HTML ===== */
        var li = ''
          + '<li class="store-map-list__item">'
          + '  <a href="#" role="button" class="store-map-list__button" data-info="' + encodedInfo + '">'
          + '    <div class="store-map-list__box">'
          + '      <h3 class="store-map-list__title">' + (store.storeName || "") + '</h3>'
          + '      <address class="store-map-list__address">'
          +        (store.sido || "") + ' ' + (store.sigungu || "") + ' '
          +        (store.street || "") + ' ' + addrDetail
          + '      </address>'
          + '      <dl class="store-map-list__content">'
          + '        <dt class="store-map-list__name">연락처</dt>'
          + '        <dd class="store-map-list__text">' + (store.storeTel || "") + '</dd>'
          + '        <dt class="store-map-list__name">운영시간</dt>'
          + '        <dd class="store-map-list__text">' + (store.businessHours || "") + '</dd>'
          + '      </dl>'
          + '    </div>'
          + '  </a>'
          + '</li>';

        $(".store-map-list__list").append(li);

        /* ===== 마커 생성 ===== */
        if (map && store.latitude && store.longitude) {
          var position = new kakao.maps.LatLng(store.latitude, store.longitude);
          var markerImg = makeMarkerImage(iconSrc);

          var marker = new kakao.maps.Marker({
            position: position,
            image: markerImg
          });

          marker.setMap(map);
          markers.push(marker);

          /* 마커 클릭 → 지도 이동 */
          kakao.maps.event.addListener(marker, "click", function () {
            map.panTo(position);
          });
        }
      });

      /* 첫 매장으로 이동 */
      if (map && data.length > 0) {
        map.panTo(new kakao.maps.LatLng(data[0].latitude, data[0].longitude));
      }
    },

    error: function () {
      alert("에러~~~");
    }
  });
});

/* ===============================
   리스트 클릭 → 지도 이동
================================ */
$(document).on("click", ".store-map-list__button", function (e) {
  e.preventDefault();

  var info = JSON.parse(decodeURIComponent($(this).attr("data-info")));

  if (map && info.latitude && info.longitude) {
    map.panTo(new kakao.maps.LatLng(info.latitude, info.longitude));
  }
});
</script>


</body>

</html>
