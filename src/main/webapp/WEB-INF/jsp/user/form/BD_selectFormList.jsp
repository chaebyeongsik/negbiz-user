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
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>신청목록</title>

<link rel="stylesheet" href="/resources/user/form/css/frmBD.css">
<op:jsTag type="user" items="ui, opform, opvalidate, opPassword" />
	
<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/user/form/js/formInfo.js"></script>
<script type="text/javascript">
	//<![CDATA[
	$(function(){
		opSelected("q_searchKey", "${paramMap.q_searchKey}");
	});	
	//]]>
</script>
</head>
<body>
<div class="frm_search">
    <form name="dataForm" id="dataForm" method="get" action="BD_selectFormList.do">
    	<input type="hidden" name="q_currPage" id="q_currPage" value="${paramMap['q_currPage']}" />
    	<input type="hidden" name="q_rowPerPage" id="q_rowPerPage" value="${paramMap['q_rowPerPage']}" />
        <fieldset>
            <legend class="sr-only">목록검색조건</legend>
            
            <div class="search_select">
                <label for="q_searchKey" class="sr-only">항목</label>
                <select name="q_searchKey" id="q_searchKey">
                    <option value="">-- 검색선택 --</option>
                    <option value="1001" <c:if test="${paramMap.q_searchKey eq '1001'}">selected="selected"</c:if>>제목</option>
                    <option value="1002" <c:if test="${paramMap.q_searchKey eq '1002'}">selected="selected"</c:if>>내용</option>
                </select>
            </div>
            
            <div class="search_key">
                <label for="q_searchVal" class="sr-only">검색어</label>
                <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
            </div>

            <div class="frm_btn">
                <button type="submit" class="submit">검색</button>
                <button type="button" class="reset" onclick="opSearchReset();">초기화</button>
            </div>
        </fieldset>
    </form>	
</div>

<!-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성 -->
<op:pagerParam title="신청목록" ignores="" />

<div class="frmBD">
    <table class="cStyle list">
        <colgroup>
            <col style="width: 10%;">
            <col style="width: 50%;">
            <col style="width: 25%;">
            <col style="width: 15%;">
            <col>
        </colgroup>
        <thead>
            <tr>
                <th scope="col">순번</th>
                <th scope="col">제목</th>
                <th scope="col">신청기간</th>
                <th scope="col">신청인원</th>
            </tr>
        </thead>
        <tbody>
            <c:set var="index" value="${pager.indexNo}" />
            <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                <tr>
                    <td class="num">${index-status.index}</td>
                    <td class="subject" id="">
                        <a href="BD_selectForm.do?q_formSn=${dataVo.formSn}">
                            <c:if test="${dataVo.frstcmYn == 'Y'}"><strong>[선착순]</strong></c:if>${dataVo.formTtl}
                        </a>
                    </td>
                    <td class="cell">${dataVo.bgngYmd} <br>- ${dataVo.endYmd}</td>
                    <td class="cell">
                    	<c:choose>
                    		<c:when test="${empty dataVo.lmtNope && not empty dataVo.rspnsNope}">${dataVo.rspnsNope} / -</c:when>
                    		<c:when test="${not empty dataVo.lmtNope && empty dataVo.rspnsNope}">
                    			0 / ${dataVo.lmtNope}
                    		</c:when>
                    		<c:when test="${empty dataVo.lmtNope && empty dataVo.rspnsNope}">-</c:when>
                    		<c:otherwise>${dataVo.rspnsNope} / ${dataVo.lmtNope}</c:otherwise>
                    	</c:choose>
                    </td>
                </tr>
            </c:forEach>
            <op:no-data obj="${pager}" colspan="4"/>
        </tbody>
    </table>

    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</div>
</body>
</html>