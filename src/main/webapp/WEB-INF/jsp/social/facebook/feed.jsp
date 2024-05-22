<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>페이스북 피드세</title>
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

    <h3>페이스북 피드</h3>

    <form method="POST" action="<c:url value="/social/facebook/feed.do" />">
        <textarea id="message" name="message" rows="2" cols="60"></textarea>
        <br />
        <input type="submit" value="등록" />
    </form>

    <div class="feed">
        <ul class="feedList">
            <c:forEach items="${feed}" var="post">
                <li class="post">
                    <p>
                        <c:if test="${not empty post.picture}">
                            <img src="<c:out value="${post.picture}"/>" align="top" />
                        </c:if>
                        <c:out value="${post.message}" />
                        -
                        <c:out value="${post.name}" />
                    </p>
                </li>
            </c:forEach>
        </ul>
    </div>

</body>
</html>