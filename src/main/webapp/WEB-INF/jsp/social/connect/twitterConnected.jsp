<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ page session="false"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>Twitter 인증성공</title>
<op:jsTag type="libs" items="jquery" />

<style type="text/css">
</style>

<script type="text/javascript">
    //<![CDATA[
    /* 공통 초기화 기능 */
    try {
        if(opener.opSocialLoginSuccess) {
            opener.opSocialLoginSuccess("twitter");
            self.close();
        }
    } finally {
    }
    //]]>
</script>
</head>
<body>
</body>
</html>
