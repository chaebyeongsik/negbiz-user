<%--
/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
--%>
<%@ page import="zesinc.user.support.UserType"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<c:set value="<%=UserType.INDVDL.getUserType()%>" var="indvdl" />
<c:set value="<%=UserType.ENTRPRS.getUserType()%>" var="entrprs" />

<!DOCTYPE html>
<html lang="ko">
<head>
<title>사용자 관리</title>

<op:jsTag type="user" items="opform, opvalidate, opPassword" />
<op:adres zip="zip" adres="adres" adres2="adres2" />
<%-- <op:jsTag type="libs" items="form" /> --%>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/user/join/userJoin.js"></script>

<script type="text/javascript">
    //<![CDATA[
    var ihidnumUseAt = '${indvdlUserIemEstbs.ihidnumUseAt}';//주민등록번호
    var emailUseAt = '${indvdlUserIemEstbs.emailUseAt}';//이메일
    var telnoUseAt = '${indvdlUserIemEstbs.telnoUseAt}';//전화번호
    var moblphonNoUseAt = '${indvdlUserIemEstbs.moblphonNoUseAt}';//휴대전화
    var adresUseAt = '${indvdlUserIemEstbs.adresUseAt}';//주소
    var brthdyUseAt = '${indvdlUserIemEstbs.brthdyUseAt}';//생일
    var sexdstnUseAt = '${indvdlUserIemEstbs.sexdstnUseAt}';//성별
    var mailingSvcUseAt = '${indvdlUserIemEstbs.mailingSvcUseAt}';//메일링서비스
    var smsRecptnUseAt = '${indvdlUserIemEstbs.smsRecptnUseAt}';//SMS수신
    //var captchaUseAt = '${indvdlUserIemEstbs.captchaUseAt}';//자동가입방지

    var lastAcdmcrUseAt = '${indvdlUserIemEstbs.lastAcdmcrUseAt}';//최종학력
    var mrrgUseAt = '${indvdlUserIemEstbs.mrrgUseAt}';//결혼
    var wrcNmUseAt = '${indvdlUserIemEstbs.wrcNmUseAt}';//직장명
    var rspofcUseAt = '${indvdlUserIemEstbs.rspofcUseAt}';//직책
    var wrcAdresUseAt = '${indvdlUserIemEstbs.wrcAdresUseAt}';//직장주소
    var wrcTelnoUseAt = '${indvdlUserIemEstbs.wrcTelnoUseAt}';//직장전화번호
    var intrstIemNmUseAt = '${indvdlUserIemEstbs.intrstIemNmUseAt}';//관심항목명

    <valid:script type="alert" />
    $(document).ready(function() {

        $("#userPswd").keyup(function() {
            opCheckValidateUserPassword($(this).val());
        });

        $("input[name=slrcldLrr]").click(function() {
            if($(this).val() == '1001') {
                $("#lpnh").attr("checked", false);
                $("#lpnh").attr("disabled", true);
            } else {
                $("#lpnh").attr("disabled", false);
            }
        });

        $("#emailCode").change(function() {
            opEmailCodeChange();
        });

        $("#brdt").blur(function() {
            opCheckBirthday();
        });

        $("#userId").keyup(function() {
            opValidateUserId();
        });

        $("#confirmPassword").blur(function() {
            opCheckConfirmPassword();
        });
    });

    var customValidate = function() {
        // 설정값이 필수입력사항인 항목 필수입력체크
        if(!opIndvdlInfoEssntlCheck())
            return false;

        // 아이디 중복 확인
        if($("#dupCheckVal").val() != 'Y') {
            opWarningMsg("입력하신 사용자 아이디가 이미 존재합니다.\n사용자 아이디를 다시 입력하여 주시기바랍니다.");
            return false;
        }
        // 비밀번호와 확인용 비밀번호가 일치하는지 체크
        if(!opCheckConfirmPassword())
            return false;

        // 비밀번호 유효성 체크
        if($("#allowPasswdCheckVal").val() != 'Y') {
            opWarningMsg("입력하신 비밀번호가 부적합한 비밀번호 조합입니다.\n비밀번호를 다시 입력하여 주시기바랍니다.");
            return false;
        }

        //생년월일 유효성 체크
        if(!opCheckBirthday())
            return false;

        //양력음력윤달 여부 값 판별 후 input(hidden)에 셋팅
        opSetSlrcldLrrLpnhAt();

        //사용자등급코드 input에 셋팅
        opSetUserGradCode();

        var upwd = $("#userPswd").val();
        var cpwd = $("#confirmPassword").val();
        if($.trim(upwd) != ''){
            var enc_val = opEncrypt(upwd);
            $("#userPswd").val(enc_val);
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
<form name="dataForm" id="dataForm" method="post" action="ND_insertIndvdlUser.do" class="form-horizontal">
    <div class="panel panel-default">
        <div class="panel-heading"><h6 class="panel-title"><i class="icon-bubble4"></i> 기본정보</h6></div>
        <div class="panel-body">
            <div class="form-group">
                <label for="userId" class="control-label col-xs-3">
                    <span class="mandatory">*</span> 사용자ID
                </label>
                <div class="col-xs-9">
                    <div class="row">
                        <div class="col-xs-6">
                            <input type="text" name="userId" id="userId" value="${dataVo.userId}" class="form-control" placeholder="사용자ID" />
                            <input type="hidden" name="dupCheckVal" id="dupCheckVal" value="" />
                        </div>
                        <div class="col-sm-6" id="dupCheckMessage" style="display: inline-block;"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <valid:msg name="userId" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="userNm" class="control-label col-xs-3">
                    <span class="mandatory">*</span> 사용자명
                </label>
                <div class="col-xs-9">
                    <div class="row">
                        <div class="col-xs-6">
                            <input type="text" name="userNm" id="userNm" value="${dataVo.userNm}" class="form-control" placeholder="사용자명" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <valid:msg name="userNm" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="userPswd" class="control-label col-xs-3">
                    <span class="mandatory">*</span> 사용자비밀번호
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
                <label for="confirmPassword" class="control-label col-xs-3">
                    <span class="mandatory">*</span> 사용자비밀번호 확인
                </label>
                <div class="col-xs-9">
                    <div class="row">
                        <div class="col-xs-6">
                            <input type="password" name="confirmPassword" id="confirmPassword" value="" class="form-control" placeholder="사용자비밀번호 확인" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12"></div>
                    </div>
                </div>
            </div>
            <c:if test="${indvdlUserIemEstbs.ihidnumUseAt ne 1002 }">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.ihidnumUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        주민등록번호
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-3">
                                <label for="rrno" class="sr-only">주민등록번호</label>
                                <input type="text" name="rrno" id="rrno" value="${dataVo.rrno}" maxlength="13" class="form-control" title="주민번호를 입력해주세요" />
                            </div>
                            <%-- <div class="col-xs-3">
                                <label for="ihidnum2" class="sr-only">주민등록번호 뒷자리</label>
                                <input type="text" name="ihidnum2" id="ihidnum2" value="${dataVo.ihidnum2}" maxlength="7" class="form-control" title="주민번호 뒷자리를 입력해주세요" />
                            </div> --%>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <valid:msg name="rrno" />
                                <%-- <valid:msg name="ihidnum2" /> --%>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.emailUseAt ne 1002 }">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.emailUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        이메일
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-4">
                                <label for="emlId" class="sr-only">이메일 앞부분</label>
                                <input type="text" name="emlId" id="emlId" value="${dataVo.emlId}" class="form-control" placeholder="이메일1" />
                            </div>
                            <div class="col-xs-4">
                                <label for="emlSiteNm" class="sr-only">이메일 뒷부분</label>
                                <div class="input-group">
                                    <span class="input-group-addon">@</span>
                                    <input type="text" name="emlSiteNm" id="emlSiteNm" value="${dataVo.emlSiteNm}" class="form-control" placeholder="이메일2" />
                                </div>
                            </div>
                            <div class="col-xs-4">
                                <op:cdId type="select" hghrkCdId="emailCode" cdId="emailCode" size="2" id="emailCode" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <valid:msg name="emlId" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="form-group">
                <label class="control-label col-xs-3">사용자등급 </label>
                <div class="col-xs-9">
                    <div class="block-inner">
                        <input type="hidden" name="userGrdCdDsctn" id="userGrdCdDsctn" value="${dataVo.userGrdCdDsctn}" />
                        <c:forEach items="${userGradList}" var="userGradVo" varStatus="vst">
                            <label for="userGrdCdId${vst.count}" class="checkbox-inline">
                                <input type="checkbox" name="userGrdCdId" id="userGrdCdId${vst.count}" value="${userGradVo.userGrdCdId}" class="styled" />
                                ${userGradVo.userGrdNm}
                            </label>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="joinTypeSn" class="control-label col-xs-3">가입유형 </label>
                <div class="col-xs-9">
                    <div class="row">
                        <div class="col-xs-6">
                            <op:cdId type="select" hghrkCdId="joinTypeSn" cdId="joinTypeSn" id="joinTypeSn" />
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${indvdlUserIemEstbs.telnoUseAt ne 1002 }">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.telnoUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        전화번호
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-4">
                                <label for="rgnTelno" class="sr-only">전화번호 국번</label>
                                <input type="text" name="rgnTelno" id="rgnTelno" value="${dataVo.rgnTelno}" class="form-control" title="국번을 입력해주세요" maxlength="4" />
                            </div>
                            <div class="col-xs-4">
                                <label for="telofcTelno" class="sr-only">전화번호 앞자리</label>
                                <input type="text" name="telofcTelno" id="telofcTelno" value="${dataVo.telofcTelno}" class="form-control" title="앞자리를 입력해주세요" maxlength="4" />
                            </div>
                            <div class="col-xs-4">
                                <label for="indivTelno" class="sr-only">전화번호 뒷자리</label>
                                <input type="text" name="indivTelno" id="indivTelno" value="${dataVo.indivTelno}" class="form-control" title="뒷자리를 입력해주세요" maxlength="4" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.moblphonNoUseAt ne 1002 }">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.moblphonNoUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        휴대전화번호
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-4">
                                <label for="mblRgnTelno" class="sr-only">휴대전화번호 앞자리</label>
                                <op:cdId type="select" hghrkCdId="mobileTy" cdId="mobileTy" id="mblRgnTelno" />
                            </div>
                            <div class="col-xs-4">
                                <label for="mblTelofcTelno" class="sr-only">휴대전화번호 중간자리</label>
                                <input type="text" name="mblTelofcTelno" id="mblTelofcTelno" value="${dataVo.mblTelofcTelno}" class="form-control" title="중간자리를 입력해주세요" maxlength="4" />
                            </div>
                            <div class="col-xs-4">
                                <label for="mblIndivTelno" class="sr-only">휴대전화번호 뒷자리</label>
                                <input type="text" name="mblIndivTelno" id="mblIndivTelno" value="${dataVo.mblIndivTelno}" class="form-control" title="뒷자리를 입력해주세요" maxlength="4" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.adresUseAt ne 1002 }">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.adresUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        주소
                    </label>
                    <div class="col-xs-9">
                        <div class="form-group">
                            <div class="col-xs-4">
                                <label for="zip" class="sr-only">우편번호</label>
                                <input type="text" name="zip" id="zip" value="${dataVo.zip}" class="form-control zip" placeholder="우편번호" maxlength="5" />
                            </div>
                            <div class="col-xs-4">
                                <button type="button" class="btn btn-default adresPopBtn">우편번호찾기</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-10">
                                <label for="userAddr" class="sr-only">기본주소</label>
                                <input type="text" name="userAddr" id="userAddr" value="${dataVo.userAddr}" class="form-control adres" placeholder="기본주소" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-10">
                                <label for="daddr" class="sr-only">상세주소</label>
                                <input type="text" name="daddr" id="daddr" value="${dataVo.daddr}" class="form-control adres2" placeholder="상세주소" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.brthdyUseAt ne 1002 }">
                <div class="form-group">
                    <label for="brdt" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.brthdyUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        생년월일
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <input type="text" name="brdt" id="brdt" value="${dataVo.brdt}" class="form-control" maxlength="8" placeholder="예) 20150101" />
                            </div>
                            <div class="col-xs-6">
                                <input type="hidden" id="brthYmdClsfSn" name="brthYmdClsfSn" value="" />
                                <input type="radio" name="slrcldLrr" id="slrcld" value="1001" checked="checked" />
                                <label for="brthYmdClsfSn1">양력</label>
                                <input type="radio" name="slrcldLrr" id="lrr" value="1002" />
                                <label for="brthYmdClsfSn2">음력</label>
                                <input type="checkbox" name="lpnh" id="lpnh" disabled="disabled" />
                                <label for="brthYmdClsfSn3">윤달</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <valid:msg name="brdt" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.sexdstnUseAt ne 1002 }">
                <div class="form-group">
                    <label for="gndrClsfSn" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.sexdstnUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        성별
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-12">
                                <op:cdId type="radio" hghrkCdId="gndrClsfSn" cdId="gndrClsfSn" id="gndrClsfSn" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.mailingSvcUseAt ne 1002 }">
                <div class="form-group">
                    <label for="emlRcptnAgreClsfSn" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.mailingSvcUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        메일링서비스
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <op:cdId type="radio" hghrkCdId="emlRcptnAgreClsfSn" cdId="emlRcptnAgreClsfSn" id="emlRcptnAgreClsfSn" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.smsRecptnUseAt ne 1002 }">
                <div class="form-group">
                    <label for="smsRcptnClsfSn" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.smsRecptnUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        SMS수신여부
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <op:cdId type="radio" hghrkCdId="smsRcptnClsfSn" cdId="smsRcptnClsfSn" id="smsRcptnClsfSn" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <%--
            <c:if test="${indvdlUserIemEstbs.captchaUseAt ne 1002 }">
                <div class="form-group">
                    <label for="smsRcptnClsfSn" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.captchaUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        자동가입방지
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6"></div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12"></div>
                        </div>
                    </div>
                </div>
            </c:if>
             --%>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading"><h6 class="panel-title"><i class="icon-bubble-plus"></i> 추가정보</h6></div>
        <div class="panel-body">
            <c:if test="${indvdlUserIemEstbs.lastAcdmcrUseAt != 1002 }">
                <div class="form-group">
                    <label for="lastAcbgNo" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.lastAcdmcrUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        최종학력
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <op:cdId type="select" hghrkCdId="lastAcbgNo" cdId="lastAcbgNo" id="lastAcbgNo" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.mrrgUseAt != 1002 }">
                <div class="form-group">
                    <label for="mrgSeSn" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.mrrgUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        결혼여부
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <op:cdId type="radio" hghrkCdId="mrgSeSn" cdId="mrgSeSn" id="mrgSeSn" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.wrcNmUseAt != 1002 }">
                <div class="form-group">
                    <label for="wrcNm" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.wrcNmUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        직장명
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <input type="text" name="wrcNm" id="wrcNm" class="form-control" placeholder="직장명" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.rspofcUseAt != 1002 }">
                <div class="form-group">
                    <label for="jbttlNm" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.rspofcUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        직책
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-6">
                                <input type="text" name="jbttlNm" id="jbttlNm" class="form-control" placeholder="직책" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.wrcAdresUseAt != 1002 }">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.wrcAdresUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        직장주소
                    </label>
                    <div class="col-xs-9">
                        <div class="form-group">
                            <div class="col-xs-4">
                                <label for="wrcZip" class="sr-only">직장주소 - 우편번호</label>
                                <input type="text" name="wrcZip" id="wrcZip" class="form-control zip" placeholder="우편번호" maxlength="5" />
                            </div>
                            <div class="col-xs-4">
                                <button type="button" class="btn btn-default adresPopBtn">우편번호찾기</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-10">
                                <label for="wrcAddr" class="sr-only">직장주소 - 기본주소</label>
                                <input type="text" name="wrcAddr" id="wrcAddr" class="form-control adres" placeholder="기본주소" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-10">
                                <label for="wrcDaddr" class="sr-only">직장주소 - 상세주소</label>
                                <input type="text" name="wrcDaddr" id="wrcDaddr" class="form-control adres2" placeholder="상세주소" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.wrcTelnoUseAt != 1002 }">
                <div class="form-group">
                    <label for="wrcRgnTelno" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.wrcTelnoUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        직장전화번호
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-4">
                                <label for="wrcRgnTelno" class="sr-only">직장전화번호 국번</label>
                                <input type="text" name="wrcRgnTelno" id="wrcRgnTelno" class="form-control" title="국번을 입력해주세요" maxlength="4" />
                            </div>
                            <div class="col-xs-4">
                                <label for="wrcTelofcTelno" class="sr-only">직장전화번호 앞자리</label>
                                <input type="text" name="wrcTelofcTelno" id="wrcTelofcTelno" class="form-control" title="앞자리를 입력해주세요" maxlength="4" />
                            </div>
                            <div class="col-xs-4">
                                <label for="wrcIndivTelno" class="sr-only">직장전화번호 뒷자리</label>
                                <input type="text" name="wrcIndivTelno" id="wrcIndivTelno" class="form-control" title="뒷자리를 입력해주세요" maxlength="4" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${indvdlUserIemEstbs.intrstIemNmUseAt != 1002 }">
                <div class="form-group" id="intrstIemDiv">
                    <label for="itrstArtclCn" class="control-label col-xs-3">
                        <c:if test="${indvdlUserIemEstbs.intrstIemNmUseAt eq 1003 }">
                            <span class="mandatory">*</span>
                        </c:if>
                        관심항목
                    </label>
                    <div class="col-xs-9">
                        <div class="row">
                            <div class="col-xs-12">
                                <input type="hidden" name="itrstArtclCn" id="itrstArtclCn" />
                                <op:cdId type="checkbox" hghrkCdId="userIntrstIem" cdId="indvdlUserIntrstIem" id="intrstIemNmCode"/>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
    <!-- 버튼 -->
    <div class="block clearfix">
        <div class="col-xs-12 btn-group">
            <div class="pull-right">
                <button type="button" onclick="opInsertIndvdlUser()" class="btn btn-success">등록</button>
<!--                     <button type="reset" class="btn btn-default">재작성</button> -->
            </div>
        </div>
    </div>
    <!-- //버튼 -->
</form>
</body>
</html>