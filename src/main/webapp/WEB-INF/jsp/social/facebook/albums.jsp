<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>페이스북 엘범 목록</title>
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

    <h3>페이스북 엘범 목록</h3>

    <ul class="albums">
        <c:forEach items="${albums}" var="album">
            <li><a href="<c:url value="/social/facebook/album/${album.id}/photos.do"/>">
                    <c:out value="${album.name}" />
                </a></li>
        </c:forEach>
    </ul>

</body>
</html>