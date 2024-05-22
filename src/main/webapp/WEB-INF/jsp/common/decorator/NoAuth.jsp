<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="(주)제스아이앤씨 기술연구소" />
<meta name="description" content="Openworks 포털관리 시스템" />

<op:jsTag type="user" items="base" />

<script type="text/javascript">
    //<![CDATA[
    var goLogin = function() {
        location.href = CTX_PATH + OpConfig.urls.userLogin;
    }
    //]]>
</script>
</head>
<body>

    <div style="text-align: center; padding: 100px;">
        <h1>이용권한이 없습니다.</h1>
        <div>${userGradeInfo} 회원만이용이가능합니다.</div>
        <button type="button" onclick="goLogin();">로그인하기</button>
        <button type="button" onclick="history.back();">돌아가기</button>
    </div>

</body>
</html>