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
<title>일정 관리 - 달력보기</title>

<style type="text/css">
.day-line {
    padding: 0px;
    margin-top: -2px;
    margin-right: -2px;
    float: right;
}

.label-day {
    font-weight: normal;
    z-index: 1000;
}

.opSchdulList {
    
}

.table-schdul>thead>tr>th {
    color: #FFFFFF; 
    background-color: #8AA0A9;
    text-align: center;
    font-weight: bold;
    font-size: 12pt;
    width: 14.28%;
}

.table-schdul>tbody>tr>td {
    padding: 3px;
    min-height: 150px;
    vertical-align: top;
    width: 14.28%;
}
</style>

<op:jsTag type="intra" items="opform, ui" />

<script type="text/javascript">
    //<![CDATA[

    /* 공통 초기화 기능 */
    $(document).ready(function() {

        opSelected("q_year", "${paramMap.q_year}", "");
        opSelected("q_month", "${paramMap.q_month}", "");
        // 상세화면
        $(".opSchdulPopup").click(function(event) {
            var option = "chrome, centerscreen, dependent=yes, width=1024, height=550, dialog=yes, modal=yes, ";
            option += "resizable=yes, scrollbars=yes, location=0, status=0, menubar=0, toolbar=0";
            var href = $(this).attr("href");
            var popupPreview = window.open(href, "opPopupPreview", option);
            popupPreview.focus();

            return false;
        });
    });

    //]]>
</script>
</head>
<body>
    <div id="tabs" class="tabbable page-tabs">
        <ul class="nav nav-tabs">
            <li><a href="BD_selectSchdulList.do?q_seCdId=${paramMap.q_seCdId}">목록으로 보기</a></li>
            <li class="active"><a href="BD_selectSchdulCal.do?q_seCdId=${paramMap.q_seCdId}">달력으로 보기</a></li>
        </ul>
    </div>

    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectSchdulCal.do" class="form-inline">
            <input type="hidden" name="q_seCdId" id="q_seCdId" value="${paramMap.q_seCdId}" />
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <div class="block">
                    <div class="form-group">
                        <label for="q_searchVal" class="">제목</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                    </div>
                    <div class="form-group">
                        <label for="q_year" class="control-label">년도</label>
                        <select name="q_year" id="q_year" class="form-control" style="width: 150px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <c:forEach items="${yearList}" var="years">
                                <option value="${years}">${years}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="q_month" class="control-label">월</label>
                        <select name="q_month" id="q_month" class="form-control" style="width: 150px;">
                            <option value=""><op:message cdId="common.selectOption" /></option>
                            <c:forEach items="${monthList}" var="month">
                                <option value="${month}">${month}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성
                <op:pagerParam title="일정  목록" ignores="" />
                --%>
            </fieldset>
        </form>
    </div>

    <div class="block table-responsive">
        <table class="table table-bordered table-schdul">
            <thead>
                <tr>
                    <th class="text-danger">일요일</th>
                    <th>월요일</th>
                    <th>화요일</th>
                    <th>수요일</th>
                    <th>목요일</th>
                    <th>금요일</th>
                    <th class="text-info">토요일</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${dataList}" var="monthList" varStatus="monthIdx">
                    <tr>
                        <c:forEach items="${monthList}" var="weekList" varStatus="weekIdx">
                            <td class="opScchdulDayTd" data-schdul-day="${weekList.year}-${weekList.month}-${weekList.day}">
                                <div class="day-line">
                                    <%-- 휴일 주말 평일 문자 디자인 --%>
                                    <c:choose>
                                        <c:when test="${paramMap.q_month eq weekList.month}">
                                            <strong> <c:choose>
                                                    <c:when test="${weekList.isHoliday}">
                                                        <span class="label label-danger">${weekList.day}</span>
                                                    </c:when>
                                                    <c:when test="${weekList.isSaturDay}">
                                                        <span class="label label-info">${weekList.day}</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="label label-primary">${weekList.day}</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </strong>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${weekList.isHoliday}">
                                                    <span class="label label-default label-day">${weekList.day}</span>
                                                </c:when>
                                                <c:when test="${weekList.isSaturDay}">
                                                    <span class="label label-default label-day">${weekList.day}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="label label-default label-day">${weekList.day}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <c:if test="${not empty weekList}">
                                    <ul>
                                        <c:forEach items="${weekList.schdulList}" var="dataVo" varStatus="dayIdx">
                                            <li><a href="PD_selectSchdul.do?q_seCdId=${dataVo.seCdId}&amp;q_schdlSeCdId=${dataVo.schdlSeCdId}&amp;q_plcCdId=${dataVo.plcCdId}&amp;q_regSn=${dataVo.regSn}" onclick="return false;" style="background-color: ${dataVo.colorNm}; padding:3px;" class="opSchdulPopup">${dataVo.ttl}</a></li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>