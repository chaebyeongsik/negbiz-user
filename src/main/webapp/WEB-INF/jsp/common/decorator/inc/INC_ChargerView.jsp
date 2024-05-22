<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${UserMenu.picIndctYn eq 'Y'}">
    <c:set var="chargerInfo" value="${UserMenu.indictMngr}" />
    <div class="row well">
        <div id="opChargerInfoLayout" class="col-md-12">
            <p>
            [ 담당자 : ${chargerInfo.deptNm} ${chargerInfo.picNm} ] 
            [ 전화번호 : ${chargerInfo.rgnTelno}-${chargerInfo.telofcTelno}-${chargerInfo.indivTelno} ]
            [ E-mail : ${chargerInfo.emlId}@${chargerInfo.emlSiteNm} ]
            </p>
        </div>
    </div>
</c:if>