<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>미리보기</title>

<c:choose>
    <c:when test="${not empty dataVo}">
        <c:out value="${dataVo.strtContsCn}" escapeXml="false" />
    </c:when>
    <c:otherwise>
        <c:out value="${param.strtContsCn}" escapeXml="false" />
    </c:otherwise>
</c:choose>

</head>
<body>

    <c:choose>
        <c:when test="${not empty dataVo}">
            <c:out value="${dataVo.mainContsCn}" escapeXml="false" />
        </c:when>
        <c:otherwise>
            <c:out value="${param.mainContsCn}" escapeXml="false" />
        </c:otherwise>
    </c:choose>

</body>
</html>