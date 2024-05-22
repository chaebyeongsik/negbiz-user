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
<title>신청상세</title>

<link rel="stylesheet" href="/resources/user/form/css/frmBD.css">
<op:jsTag type="user" items="opform, opvalidate, opPassword" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/user/form/js/formInfo.js"></script>

<script type="text/javascript">
	//<![CDATA[
	$(function(){
		$("#opPreviousList").click(function(){
			location.href = "/user/form/BD_selectFormList.do";			
		});
	});	
	//]]>
</script>


</head>
<body>
	<div class="frmBD">
		<input type="hidden" id="startDt" value="${selectForm.bgngYmd}" /> 
		<input type="hidden" id="endDt" value="${selectForm.endYmd}" /> 
		<input type="hidden" id="rspnsNope" value="${selectForm.rspnsNope}" /> 
		<input type="hidden" id="lmtNope" value="${selectForm.lmtNope}" />

		<table class="cStyle view">
			<colgroup>
				<col style="width: 180px">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th class="text-left">제목</th>
					<td>${selectForm.formTtl}</td>
				</tr>
				<tr>
					<th class="text-left">신청기간</th>
					<td>${selectForm.bgngYmd}- ${selectForm.endYmd}</td>
				</tr>
				<tr>
					<th class="text-left">신청인원 / 제한인원</th>
					<td><c:choose>
							<c:when test="${empty selectForm.lmtNope}">-</c:when>
							<c:when
								test="${not empty selectForm.lmtNope && empty selectForm.rspnsNope}">
                   			0 / ${selectForm.lmtNope}
                   		</c:when>
							<c:otherwise>${selectForm.rspnsNope} / ${selectForm.lmtNope}</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<th class="text-left">내용</th>
					<td class="cont">${selectForm.formExpln }</td>
				</tr>
				<tr>
					<th class="text-left">첨부파일</th>
					<td><op:fileDownload fileList="${selectForm.fileList}" /></td>
				</tr>
			</tbody>
		</table>


		<div class="frm_btn">
			<button type="button" id="opPreviousList">목록</button>

			<form name="dataForm" id="dataForm" method="get"
				action="BD_insertFormRspns.do" class="form-inline">
				<input type="hidden" name="q_formSn" id="q_formSn"
					value="${paramMap.q_formSn}" />
				<button type="button" class="submit" onclick="save()">신청하기</button>
			</form>
		</div>
</body>
</html>