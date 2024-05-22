<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta name="keywords" content="지도좌표생성기" />
<meta name="description" content="지도좌표생성기" />
<title>지도좌표생성기
</title>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2690267fa3de91870a2a8da724e4b159&libraries=services"></script>
<script type="text/javascript">
	var geoCode = function(addrStr) {
		var addr = addrStr;
		
		var faddr_lat = "";
		var faddr_lng = "";

		if(addr == "") {
			alert("주소를 입력해 주세요.");
			return flase;
		}
		var geocoder = new daum.maps.services.Geocoder();

		geocoder.addressSearch(addr, function(result, status) {
			// 정상적으로 검색이 완료됐으면 
			if (status === daum.maps.services.Status.OK) {
                        console.log(result[0].y, result[0].x);
                        $("#geoCode").val(result[0].y+','+result[0].x);
			}
		});
	};
</script>
</head>
<body>
<input type="text" id="locAddr" name="locAddr" value=""/>
<button type="button" onclick="geoCode($('#locAddr').val());">지도검색</button>
<input type="text" id="geoCode" name="geoCode" value=""/>
</body>
</html>