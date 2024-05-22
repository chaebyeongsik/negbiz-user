<%--
/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
--%>

<%@ page import="zesinc.user.support.UserType" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<title>사용자 관리</title>

<op:jsTag type="user" items="opform" />

<!-- 기능별 스크립트 삽입 부 -->

<script type="text/javascript">
    //<![CDATA[
    $(document).ready(function() {

    });
    //]]>
</script>
</head>
<body>
    <!-- 버튼 -->
    <div class="block">
        <div class="row">
            <div class="col-md-6">
                <a href="/user/join/BD_insertUserJoinForm.do?userTypeNm=I" class="btn btn-default btn-block">개인사용자</a>
            </div>
            <div class="col-md-6">
                <a href="/user/join/BD_insertUserJoinForm.do?userTypeNm=E" class="btn btn-success btn-block">기업사용자</a>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
</body>
</html>