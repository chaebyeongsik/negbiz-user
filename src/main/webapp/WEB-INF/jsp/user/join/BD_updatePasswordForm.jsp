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

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/user/join/userJoin.js"></script>

<script type="text/javascript">
    //<![CDATA[

    <valid:script type="msgbox" />
    $(document).ready(function() {

        $("#userPswd").keyup(function() {
            opCheckValidateUserPassword($(this).val());
        });

        $("#confirmPassword").blur(function() {
            opCheckConfirmPassword();
        });
    });

    var customValidate = function() {

        // 비밀번호와 확인용 비밀번호가 일치하는지 체크
        if(!opCheckConfirmPassword())
            return false;

        // 비밀번호 유효성 체크
        if($("#allowPasswdCheckVal").val() != 'Y') {
            opWarningMsg("입력하신 비밀번호가 부적합한 비밀번호 조합입니다.\n비밀번호를 다시 입력하여 주시기바랍니다.");
            return false;
        }

        var upwd = $("#userPswd").val();
        var npwd = $("#newUserPswd").val();
        var cpwd = $("#confirmPassword").val();
        if($.trim(upwd) != ''){
            var enc_val = opEncrypt(upwd);
            $("#userPswd").val(enc_val);
        }
        if($.trim(npwd) != ''){
            var enc_val = opEncrypt(npwd);
            $("#newUserPswd").val(enc_val);
        }
        if($.trim(upwd) != ''){
            var enc_val = opEncrypt(cpwd);
            $("#confirmPassword").val(enc_val);
        }

        return true;
    };
    //]]>
</script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 항목은 필수 입력항목입니다.
    </div>
    <form name="dataForm" id="dataForm" method="post" action="ND_insertEntrprsUser.do" class="form-horizontal">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <label for="userPswd" class="control-label col-xs-3">
                        <span class="mandatory">*</span> 현재 사용자비밀번호
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <input type="password" name="userPswd" id="userPswd" value="${dataVo.userPswd}" class="form-control" placeholder="사용자비밀번호" />
                                <input type="hidden" name="allowPasswdCheckVal" id="allowPasswdCheckVal" />
                            </div>
                            <div class="col-xs-6" id="allowPasswd"></div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="help-block op-validate col-xs-9">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg"> (최소 9자, 최대 32자/영문(대/소문자),숫자,특수문자 조합) </span></li>
                                        <li class="list-group-item"><span class="validate-msg"> 비밀번호 등록 또는 변경시 보안등급 확인하여 안전하게 변경하시기 바랍니다. </span></li>
                                    </ul>
                                </div>
                                <div class="col-xs-3">
                                    <button type="button" class="btn btn-xs btn-info" onclick="opScrtyGradPop();">보안등급정책</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="userPswd" class="control-label col-xs-3">
                        <span class="mandatory">*</span> 새 사용자비밀번호
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <input type="password" name="newUserPswd" id="newUserPswd" value="" class="form-control" placeholder="새 사용자비밀번호" />
                                <input type="hidden" name="allowPasswdCheckVal" id="allowPasswdCheckVal" />
                            </div>
                            <div class="col-xs-6" id="allowPasswd"></div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="help-block op-validate col-xs-9">
                                    <ul class="list-group">
                                        <li class="list-group-item"><span class="validate-msg"> (최소 9자, 최대 32자/영문(대/소문자),숫자,특수문자 조합) </span></li>
                                        <li class="list-group-item"><span class="validate-msg"> 비밀번호 등록 또는 변경시 보안등급 확인하여 안전하게 변경하시기 바랍니다. </span></li>
                                    </ul>
                                </div>
                                <div class="col-xs-3">
                                    <button type="button" class="btn btn-xs btn-info" onclick="opScrtyGradPop();">보안등급정책</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmPassword" class="control-label col-xs-3">
                        <span class="mandatory">*</span> 새 사용자비밀번호 확인
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <input type="password" name="confirmPassword" id="confirmPassword" value="" class="form-control" placeholder="새 사용자비밀번호 확인" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 버튼 -->
        <div class="block clearfix">
            <div class="col-xs-12 btn-group">
                <div class="pull-right">
                    <button type="button" onclick="opInsertEntrprsUser()" class="btn btn-success">등록</button>
<!--                     <button type="reset" class="btn btn-default">재작성</button> -->
                </div>
            </div>
        </div>
        <!-- //버튼 -->
    </form>
</body>
</html>