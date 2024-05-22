<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/spring-social/social/tags" prefix="social" %>

<h4><a href="<c:url value="/social/connect/status.do"/>">Connections</a></h4>

<h4><a href="<c:url value="/social/connect/twitter/connect.do"/>">Twitter</a></h4>
<social:connected provider="twitter">
<ul class="menu">
	<li><a href="<c:url value="/social/twitter/profile.do"/>">User Profile</a></li>
    <li><a href="<c:url value="/social/twitter/timeline.do"/>">Home Timeline</a></li>
    <li><a href="<c:url value="/social/twitter/userTimeline.do"/>">User Timeline</a></li>
    
	<li><a href="<c:url value="/social/twitter/friends.do"/>">Friends</a></li>
	<li><a href="<c:url value="/social/twitter/followers.do"/>">Followers</a></li>
	<li><a href="<c:url value="/social/twitter/messages.do"/>">Messages</a></li>
	<li><a href="<c:url value="/social/twitter/trends.do"/>">Trends</a></li>
</ul>
</social:connected>

<h4><a href="<c:url value="/social/connect/facebook/connect.do"/>">Facebook</a></h4>
<social:connected provider="facebook">
<ul class="menu">
	<li><a href="<c:url value="/social/facebook/profile.do"/>">User Profile</a></li>
	<li><a href="<c:url value="/social/facebook/feed.do"/>">Feed</a></li>
	<li><a href="<c:url value="/social/facebook/friends.do"/>">Friends</a></li>
	<li><a href="<c:url value="/social/facebook/albums.do"/>">Albums</a></li>
</ul>
</social:connected>
