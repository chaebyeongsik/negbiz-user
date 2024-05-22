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
    <div class="block clearfix">
        <div class="col-sm-12 btn-group">
        <c:if test="${not empty pager}">
            <div class="pull-left">
                <button type="button" class="btn btn-danger" onclick="opDelete();">삭제</button>
            </div>
        </c:if>
            <div class="pull-right">
                <button type="button" class="btn btn-success" onclick="opInsertIndvdlForm();">개인사용자등록</button>
                <button type="button" class="btn btn-success" onclick="opInsertEntrprsForm();">사업자사용자등록</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->
</body>
</html>