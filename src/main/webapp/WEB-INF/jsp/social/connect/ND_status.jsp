<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<op:jsTag type="libs" items="form" />
<script type="text/javascript" src="/resources/openworks4/js/openworks.social.js"></script>

<style type="text/css">
.primeSocialProviderMark {
    border-bottom-color: red !important;
}

.opSocialImg {
    border-bottom-style: solid;
    border-bottom-width: 3px;
    border-bottom-color: #FFFFFF;
}
</style>
<script type="text/javascript">
    //<![CDATA[

<%--  인증해제 성공시 처리 함수  --%>
    var opSocialLogoutSuccess = function(disconnectCnt) {
        <c:forEach var="providerId" items="${providerIds}">
        $("#${providerId}Connect").show();
        $("#${providerId}Connected").hide();
        $("#providerId_${providerId}").prop("checked", false);
        </c:forEach>

        <c:if test="${not empty sessionScope['__usk']}">
        opSetSocialPrime("user");
        </c:if>

        var socialAccountCnt = new Number($("#socialAccountCnt").val()) - disconnectCnt;
        $("#socialAccountCnt").val(socialAccountCnt);
        opSocialCmntList();
    };

<%-- 대표 계정 표시 제거 --%>
    var opSocialRemoveClass = function() {
        $("#user_prime").removeClass("primeSocialProviderMark");
        <c:forEach var="providerId" items="${providerIds}">
        $("#${providerId}_prime").removeClass("primeSocialProviderMark");
        </c:forEach>
    };

    /* 초기화 */
    $(document).ready(function() {

    });

    //]]>
</script>

<c:set var="connStatus" value="N" />

<form id="socialSigninForm" name="socialSigninForm" method="post" target="opSocialSigninWin" action="/login/PD_snsLoginForm.do">
    <button type="submit" class="sr-only">로그인</button>
</form>

<form id="socialDataForm" name="socialDataForm" action="/social/cmnt/ND_insertSocialCmnt.do" method="post">
    <%-- 도메인 --%>
    <input type="hidden" id="socialDomnCode" name="siteSn" value="${param.siteSn}" />
    <%-- 사용자메뉴코드 --%>
    <input type="hidden" id="socialUserMenuCode" name="userMenuEngNm" value="${param.userMenuEngNm}" />
    <%-- 페이지제목 --%>
    <input type="hidden" id="socialPgeTtl" name="pgeTtl" value="${param.pgeTtl}" />
    <%-- 페이지제목 --%>
    <input type="hidden" id="socialPgeUrl" name="pgeUrl" value="${param.pgeUrl}" />
    <%-- 대표계정 --%>
    <input type="hidden" id="primeSocialProviderId" name="primeSocialProviderId" value="" />
    <%-- 로그인된 계정 수 --%>
    <input type="hidden" id="socialAccountCnt" name="socialAccountCnt" value="${socialAccountCnt}" />

    <div class="row">
        <div class="col-xs-2">소셜로그인</div>

        <div class="col-xs-7">
            <div class="row">

                <%-- 회원 로그인 여부 --%>
                <c:choose>
                    <c:when test="${empty sessionScope['__usk']}">
                        <c:set var="connStatus" value="N" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="connStatus" value="Y" />
                    </c:otherwise>
                </c:choose>

                <div id="userConnect" class="col-xs-1" <c:if test="${connStatus eq 'Y'}">style="display:none;"</c:if>>
                    <a href="/login/PD_snsLoginForm.do" id="user_signin" class="connectButton">
                        <img class="opSocialImg" src="/resources/social/images/off/png/32x32/user.png" data-toggle="tooltip" data-placement="top" title="회원로그인" />
                    </a>
                </div>

                <div id="userConnected" class="col-xs-1" <c:if test="${connStatus eq 'N'}">style="display:none;"</c:if>>
                    <a href="#" id="user_href" class="connectedButton" data-toggle="tooltip" data-placement="top" title="Openworks를 대표계정으로 사용">
                        <img id="user_prime" class="opSocialImg" src="/resources/social/images/on/png/32x32/user.png" alt="회원" />
                    </a>
                    <input type="checkbox" id="providerId_user" name="socialProviderId" value="user" />
                </div>

                <%-- SNS --%>
                <c:forEach var="providerId" items="${providerIds}">
                    <c:set var="connections" value="${connectionMap[providerId]}" />
                    <c:set var="connectUrl" value="/social/connect/${providerId}/connect.do" />
                    <c:set var="disconnectUrl" value="/social/connect/${providerId}/disconnect.do" />

                    <%-- SNS 인증 여부 --%>
                    <c:choose>
                        <c:when test="${empty connections}">
                            <c:set var="connStatus" value="N" />
                        </c:when>
                        <c:otherwise>
                            <c:set var="connStatus" value="Y" />
                        </c:otherwise>
                    </c:choose>

                    <div id="${providerId}Connect" class="col-xs-1" <c:if test="${connStatus eq 'Y'}">style="display:none;"</c:if>>
                        <a href="${connectUrl}" id="${providerId}_signin" class="connectButton" data-toggle="tooltip" data-placement="top" title="${providerId} 인증 페이지를 새장으로 엽니다.">
                            <img class="opSocialImg" src="/resources/social/images/off/png/32x32/${providerId}.png" alt="${providerId} 로그인" />
                        </a>
                    </div>
                    <div id="${providerId}Connected" class="col-xs-1" <c:if test="${connStatus eq 'N'}">style="display:none;"</c:if>>
                        <a href="#" id="${providerId}_href" class="connectedButton" data-toggle="tooltip" data-placement="top" title="${providerId}를 대표계정으로 사용">
                            <img id="${providerId}_prime" class="opSocialImg" src="/resources/social/images/on/png/32x32/${providerId}.png" alt="${providerId} 로그아웃" />
                        </a>
                        <input type="checkbox" id="providerId_${providerId}" name="socialProviderId" value="${providerId}" />
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="col-xs-3  pull-right">
            <a href="/social/connect/all/disconnect.do" id="all_signout" class="disconnectButton" data-toggle="tooltip" data-placement="top" title="회원정보를 제외한 모든 SNS를 로그아웃 합니다.">
                <span class="glyphicon glyphicon-exclamation-sign"> SNS 전체로그아웃</span>
            </a>
        </div>
    </div>

    <div class="row" style="margin-top: 3px;">
        <div class="col-xs-2">
            <img id="socialPrimeImg" src="/resources/social/images/on/png/48x48/user.png" />
        </div>
        <div class="col-xs-10">
            <textarea name="socialMessage" id="opSocialMessage" rows="3" cols="50" class="form-control"></textarea>
        </div>
    </div>

    <div class="row" style="margin-top: 3px;">
        <div class="col-xs-2">&nbsp;</div>
        <div class="col-xs-5 ">※ 입력글자 제한 <span id="opSocialMessageCnt">0</span> / 250</div>
        <div class="col-xs-5 btn-group">
            <div class="pull-right">
                <button type="submit" value="등록" class="btn btn-sm btn-success">등록</button>
            </div>
        </div>
    </div>
</form>

<%-- 댓글 목록 --%>
<div class="row" id="opSocialCmntListDiv" style="margin-top: 3px;"></div>
