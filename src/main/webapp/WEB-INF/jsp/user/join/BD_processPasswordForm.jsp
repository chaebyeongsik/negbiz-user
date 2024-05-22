<%--
/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
--%>

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

<op:jsTag type="user" items="opform, opvalidate, opPassword" />
<op:jsTag type="libs" items="form" />

<script type="text/javascript">
    //<![CDATA[

    <valid:script type="msgbox" />
    /* 공통 초기화 기능 */
    $(document).ready(function() {
        $("#dataForm").submit(function() {
            return validate();
        });
    });

    /* 사용자 정의 추가 검증(필요한 검증 코드 삽입) 기본 검증후 메소드가 자동 호출된다. */
    var customValidate = function() {
        var upwd = $("#userPswd").val();
        if($.trim(upwd) != ''){
            var enc_val = opEncrypt(upwd);
            $("#userPswd").val(enc_val);
        }
    };
    //]]>
</script>
</head>
<body>

    <form name="dataForm" id="dataForm" method="post" action="/user/join/ND_processCheckPassword.do" class="form-horizontal">

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="userId" class="control-label col-xs-2">
                        사용자ID
                    </label>
                    <div class="col-xs-10">
                        <div class="row">
                            <div class="col-xs-6">
                                ${sessionScope['__usk'].userIdntfNm}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="userNm" class="control-label col-xs-2">
                        비밀번호
                    </label>
                    <div class="col-xs-10">
                        <div class="row">
                            <div class="col-xs-6">
                                <input type="password" name="userPswd" id="userPswd" value="" class="form-control" placeholder="비밀번호" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <valid:msg name="userPswd" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 버튼 -->
        <div class="block clearfix">
            <div class="col-xs-12 btn-group">
                <div class="pull-right">
                    <button type="submit" class="btn btn-success">확인</button>
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>