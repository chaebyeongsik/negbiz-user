<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>트위터 Profile</title>
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

    <h3>트위터 Profile</h3>

    <p>
        <c:out value="${profile.name}" />
    </p>

    <img src="<c:out value="${profile.profileImageUrl}"/>" />

    <dl>
        <dt>계정:</dt>
        <dd>
            <c:out value="${profile.id}" />
        </dd>
        <dt>이름:</dt>
        <dd>
            <a href="<c:out value="${profile.profileUrl}"/>" target="_blank">
                <c:out value="${profile.screenName}" />
            </a>
        </dd>
        <dt>소개:</dt>
        <dd>
            <c:out value="${profile.description}" />
        </dd>
        <dt>지역:</dt>
        <dd>
            <c:out value="${profile.location}" />
        </dd>
        <dt>URL:</dt>
        <dd>
            <a href="<c:out value="${profile.url}"/>">
                <c:out value="${profile.url}" />
            </a>
        </dd>
    </dl>

</body>
</html>
