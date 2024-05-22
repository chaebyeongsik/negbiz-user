<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%
response.setStatus(200);
%>
<html>
<head>
<title>로그인이 필요한 서비스입니다</title>

<op:jsTag type="libs" items="jquery" />

<script type="text/javascript">
    alert("로그인이 필요한 서비스 입니다.");
    <c:if test="${not empty url}">
        self.location.href = "${url}";
    </c:if>
    <c:if test="${empty url}">
        self.location.href = "/index.do";
    </c:if>
</script>
</head>

<body>

</body>

</html>