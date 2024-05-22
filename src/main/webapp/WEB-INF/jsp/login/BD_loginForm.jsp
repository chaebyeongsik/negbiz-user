<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<%
    /*
     * TODO : 로그인 관련 메시지 관리 추가 후 적용
     */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>로그인</title>

<op:jsTag type="user" items="opform, opvalidate, opPassword" />

<script>
    //<![CDATA[
    $().ready(function() {

        $("#dataForm").submit(function() {
            if ($("#userId").val() == "") {
                $("#userId").focus();
                opWarningMsg("로그인 아이디를 입력해주세요");

                return false;
            } else if ($("#userPswd").val() == "") {
                opWarningMsg("로그인 비밀번호를 입력해주세요");
                $("#userPswd").focus();

                return false;
            }

            var enc_val = opEncrypt($("#userPswd").val());
            $("#userPswd").val(enc_val);

            return true;
        });

    });
    //]]>
</script>


</head>
<body class="full-width page-condensed">

    <!-- Login wrapper -->
    <div>
        <form id="dataForm" method="post" action="ND_loginAction.do">
            <input type="hidden" name="returnUrl" value="${userLoginVo.returnUrl}" />

            <div class="popup-header">
                <a href="/user/join/BD_userTypeChoose.do" class="pull-left" title="회원가입"><i class="icon-user-plus"></i></a> <span class="text-semibold">로그인</span>
                <div class="btn-group pull-right">
                </div>
            </div>

            <div class="well col-md-12">
                <div class="form-group has-feedback">
                    <label for="picId">아이디</label>
                    <input type="text" name="userId" id="userId" class="form-control" placeholder="아이디" value="" />
                    <i class="icon-users form-control-feedback"></i>
                </div>

                <div class="form-group has-feedback">
                    <label for="picPswd">비밀번호</label>
                    <input type="password" name="userPswd" id="userPswd" class="form-control" placeholder="비밀번호" value="" />
                    <i class="icon-lock form-control-feedback"></i>
                </div>

                <div class="row form-actions">
                    <div class="col-xs-12">
                        <button type="submit" class="btn btn-warning pull-right">
                            <i class="icon-menu2"></i> 로그인
                        </button>
                    </div>
                </div>

            </div>

        </form>
    </div>
    <!-- /login wrapper -->

</body>
</html>