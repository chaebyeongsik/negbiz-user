<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty quickLinkList}">
	<ul>
		<c:forEach items="${quickLinkList}" var="quick" varStatus="status">
			<c:if test="${not empty quick.filePathNm}">
				<li>
					<c:if test="${not empty quick.linkUrlAddr}">
						<a href="${quick.linkUrlAddr}" target="${quick.linkTypeNm}" title="${quick.linkExpln}">
							<span class="icon"><img src="${quick.filePathNm}" alt="${quick.imgAltrtvNm}" width="100" /></span>
							<span class="txt">${quick.ttl}</span>
						</a>
					</c:if>
					<c:if test="${empty quick.linkUrlAddr}">
						<span class="icon"><img src="${quick.filePathNm}" alt="${quick.imgAltrtvNm}" width="100" /></span>
						<span class="txt">${quick.ttl}</span>
					</c:if>
				</li>
			</c:if>
		</c:forEach>
	</ul>
	<span><a class="btn-top" href="#top">TOP</a></span>
</c:if>
