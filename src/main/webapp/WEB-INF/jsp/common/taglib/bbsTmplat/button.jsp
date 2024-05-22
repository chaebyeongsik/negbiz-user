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
<%@ page import="zesinc.web.support.tag.bbsTmplat.support.BbsStyleSupport"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:choose>
    <c:when test="${bbsType eq 'list'}">
        <c:if test="${bbsConfigVo.nfeedUseYn eq 'Y'}">
            <button type="button" class="btn btn-primary" onclick="opRssLinkView('${RSS_URL}');">RSS</button>
            <button type="button" class="btn btn-primary" onclick="opAtomLinkView('${ATOM_URL}');">ATOM</button>
        </c:if>
        <button type="button" class="btn btn-info" onclick="opToggleSumry();">요약보기</button>
        <button type="button" class="btn btn-primary" onclick="opInsertForm();">등록</button>
    </c:when>
    <c:when test="${bbsType eq 'view'}">
        <button type="button" class="btn btn-primary" onclick="opInsertForm();">등록</button>
        <c:if test="${bbsConfigVo.lyrUseYn eq 'Y' and bbsObj.ntcPstYn ne 'Y'}">
            <button type="button" class="btn btn-primary" onclick="opReplyForm();">답글</button>
        </c:if>
        <c:if test="${not empty loginVo and loginVo.userId eq bbsObj.rgtrId}">
            <button type="button" class="btn btn-primary" onclick="opUpdateForm();">수정</button>
            <button type="button" class="btn btn-warning" onclick="opDelete();">삭제</button>
        </c:if>
        <button type="button" class="btn btn-info" onclick="opList();">목록</button>
    </c:when>
    <c:when test="${bbsType eq 'form'}">
        <button type="submit" class="btn btn-success">저장</button>
        <button type="button" class="btn btn-info" onclick="opList();">목록</button>
    </c:when>
</c:choose>
