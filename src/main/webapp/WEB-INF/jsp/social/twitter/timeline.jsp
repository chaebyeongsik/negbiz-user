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

<script src="http://platform.twitter.com/anywhere.js?id=7yWLgCOuQhIpPyffm0o2Vg&v=1" type="text/javascript"></script>
<script type="text/javascript">
    twttr.anywhere(function(T) {
        T(".feed").linkifyUsers();
    });
</script>
</head>
<body>

    <h3>
        Twitter
        <c:out value="${timelineName}" />
        타임라인
    </h3>

    <h4>트윗 등록</h4>
    <form action="<c:url value="/social/twitter/timeline.do" />" method="post">
        <textarea name="message" rows="2" cols="80"></textarea>
        <input type="submit" value="Tweet 등록" />
    </form>

    <h4>트윗 검색</h4>
    <form action="<c:url value="/social/twitter/search.do" />" method="get">
        <p>
            <input type="text" name="query" value="<c:out value="${param.query}"/>" />
            <input type="submit" value="검색" />
        </p>
    </form>

    <ul class="choices">
        <li><a href="/social/twitter/timeline.do">Home Timeline</a></li>
        <li><a href="/social/twitter/userTimeline.do">User Timeline</a></li>
        <li><a href="/social/twitter/mentions.do">트위터 Mentions</a></li>
        <li><a href="/social/twitter/favorites.do">트위터 Favorites</a></li>
        <li><a href="/social/twitter/friends.do">트위터 Friends</a></li>
        <li><a href="/social/twitter/followers.do">트위터 Follower</a></li>
        <li><a href="/social/twitter/trends.do">트위터 Trends</a></li>
    </ul>

    <div class="feed">
        <ul class="imagedList">
            <c:forEach items="${timeline}" var="tweet">
                <li class="imagedItem">
                    <div class="image">
                        <c:if test="${not empty tweet.profileImageUrl}">
                            <img src="<c:out value="${tweet.profileImageUrl}"/>" align="left" />
                        </c:if>
                    </div>
                    <div class="content">
                        <strong><a href="http://twitter.com/<c:out value="${tweet.fromUser}" />">
                                <c:out value="${tweet.fromUser}" />
                            </a></strong><br />
                        <c:out value="${tweet.text}" />
                        <br /> <span class="postTime"><c:out value="${tweet.createdAt}" /></span>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>

</body>
</html>