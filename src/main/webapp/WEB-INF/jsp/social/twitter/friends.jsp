<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>트위터 Friends</title>
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

<script src="http://platform.twitter.com/anywhere.js?id=7yWLgCOuQhIpPyffm0o2Vg&v=1" type="text/javascript"></script>
<script type="text/javascript">
    twttr.anywhere(function(T) {
        T(".feed").linkifyUsers();
    });
</script>
</head>
<body>

    <h3>트위터 Friends</h3>

    <ul class="imagedList">
        <c:forEach items="${friends}" var="friend">
            <li class="imagedItem">
                <div class="image">
                    <c:if test="${not empty friend.profileImageUrl}">
                        <img src="<c:out value="${friend.profileImageUrl}"/>" width="48" height="48" align="left" />
                    </c:if>
                </div>
                <div class="content">
                    <p>
                        <a href="http://twitter.com/<c:out value="${friend.screenName}" />">
                            <c:out value="${friend.screenName}" />
                        </a>
                    </p>
                </div>
            </li>
        </c:forEach>
    </ul>
</body>
</html>