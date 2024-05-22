<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>트위터 Trends</title>
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

    <h3>트위터 Trends</h3>

    <c:forEach items="${trends.trends}" var="trend">
        <c:url var="searchUrl" value="/social/twitter/search.do">
            <c:param name="query">
                <c:out value="${trend.query}" escapeXml="false" />
            </c:param>
        </c:url>
        <a href="${searchUrl}">
            <c:out value="${trend.name}" />
        </a>
        <br />
    </c:forEach>

</body>
</html>
