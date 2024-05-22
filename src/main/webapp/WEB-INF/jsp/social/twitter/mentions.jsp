<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>트위터 Mentions</title>
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

    <h3>트위터 Mentions</h3>

    <div class="feed">
        <ul class="imagedList">
            <c:forEach items="${mentions}" var="mention">
                <li class="imagedItem">
                    <div class="image">
                        <c:if test="${not empty mention.profileImageUrl}">
                            <img src="<c:out value="${mention.profileImageUrl}"/>" align="left" />
                        </c:if>
                    </div>
                    <div class="content">
                        <strong><a href="http://twitter.com/<c:out value="${mention.fromUser}" />">
                                <c:out value="${mention.fromUser}" />
                            </a></strong><br />
                        <c:out value="${mention.text}" />
                        <br /> <span class="postTime"><c:out value="${mention.createdAt}" /></span>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>

</body>
</html>