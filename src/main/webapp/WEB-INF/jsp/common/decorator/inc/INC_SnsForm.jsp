<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="zesinc.web.support.BaseConfig"%>
<%@ page import="zesinc.web.vo.cache.CmsCacheVO"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<c:if test="${UserMenu.snsUseYn eq 'Y'}">

    <%-- SNS 파라미터가 설정 된경우 해당 파라미터가 존재하는 경우에만 SNS 폼을 생성한다. --%>
    <c:if test="${empty UserMenu.snsPrmttNm or (not empty UserMenu.snsPrmttNm and not empty param[UserMenu.snsPrmttNm])}">

        <c:set var="pgeUrl" value="${UserMenu.snsUrl}"/>

        <%-- SNS 구분자용 snsParamtr이 CMS설정에 존재한다면 반영 --%>
        <c:if test="${not empty UserMenu.snsPrmttNm}">
            <c:set var="paramVar" value="${param[UserMenu.snsPrmttNm]}"/>
            <c:choose>
                <c:when test="${fn:indexOf(UserMenu.snsUrl, '?') > -1}">
                    <c:set var="pgeUrl" value="${pgeUrl}&amp;${UserMenu.snsPrmttNm}=${param[UserMenu.snsPrmttNm]}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="pgeUrl" value="${pgeUrl}?${UserMenu.snsPrmttNm}=${param[UserMenu.snsPrmttNm]}"/>
                </c:otherwise>
            </c:choose>
        </c:if>

<%
        boolean isSecure = request.isSecure();
        StringBuilder hostUrl = new StringBuilder();
        if(isSecure) {
            hostUrl.append("https://");
        } else {
            hostUrl.append("http://");
        }
        hostUrl.append(request.getServerName()).append(":");
        hostUrl.append(request.getServerPort());
        if(request.getContextPath() != null) {
            hostUrl.append(request.getContextPath());
        }
        request.setAttribute("hostUrl", hostUrl.toString());
%>

        <%-- 페이지 제목 --%>
        <c:set var="pgeTtl" value="${UserMenu.menuNm}"/>
        <c:if test="${not empty UserMenu.ttl}"><c:set var="pgeTtl" value="${UserMenu.ttl}"/></c:if>

        <script type="text/javascript">
            //<![CDATA[
            /* 초기화 */
            $(document).ready(function() {
                opLoadSocialLayout();
            });
            // 실제 SNS 화면을 로드
            var opLoadSocialLayout = function() {
                var params = {
                    siteSn : "${UserMenu.siteSn}",
                    userMenuEngNm : "${UserMenu.userMenuEngNm}",
                    pgeUrl : "${hostUrl}${pgeUrl}",
                    pgeTtl : "${pgeTtl}"
                };
                var href = "/social/connect/ND_status.do";
                $("#opSocialLayout").load(href, params, function(responseTxt, statusTxt, response) {
                    if(statusTxt == "success") {
                    } else {
                        opSysErrorMsg(responseTxt);
                    }
                });
            };
            //]]>
        </script>
        <div class="row well">
            <div id="opSocialLayout" class="col-md-12"></div>
        </div>
    </c:if>
</c:if>