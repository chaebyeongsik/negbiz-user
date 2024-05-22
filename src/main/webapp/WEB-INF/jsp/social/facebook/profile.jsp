<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>게시물 상세</title>
<op:jsTag type="libs" items="jquery" />

<style type="text/css">
</style>

<script type="text/javascript">
    //<![CDATA[
    /* 공통 초기화 기능 */
    $(document).ready(function() {
    });
    //]]>
</script>
</head>
<body>

    <h3>페이스북 프로필</h3>
    <p>
        <c:out value="${profile.firstName}" />
    </p>

    <dl>
        <dt>계정 :</dt>
        <dd>
            <c:out value="${profile.id}" />
        </dd>
        <dt>이름 :</dt>
        <dd>
            <c:out value="${profile.name}" />
        </dd>
        <dt>사진 :</dt>
        <dd>
            <img src="http://graph.facebook.com/<c:out value="${profile.id}"/>/picture" align="middle" />
        </dd>
        <dt>Email:</dt>
        <dd>
            <c:out value="${profile.email}" />
        </dd>
        <dt>지역:</dt>
        <dd>
            <c:out value="${profile.address.country}" />
            <c:out value="${profile.address.city}" />
            <c:out value="${profile.address.street}" />
        </dd>
    </dl>

</body>
</html>