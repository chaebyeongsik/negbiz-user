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
<title>일정 관리 - 목록보기</title>

<op:jsTag type="intra" items="opform, ui" />

<script type="text/javascript">
    //<![CDATA[
    /* 공통 초기화 기능 */
    $(document).ready(function() {
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
            <li class="active"><a href="BD_selectSchdulList.do?q_seCdId=${paramMap.q_seCdId}">목록 보기</a></li>
            <li><a href="BD_selectSchdulCal.do?q_seCdId=${paramMap.q_seCdId}">달력 보기</a></li>
        </ul>
    </div>

    <div class="block text-center">
        <form name="dataForm" id="dataForm" method="get" action="BD_selectSchdulList.do" class="form-inline">
            <input type="hidden" name="q_seCdId" id="q_seCdId" value="${paramMap.q_seCdId}" />
            <fieldset>
                <legend class="sr-only">목록검색조건</legend>
                <div class="block">
                    <div class="form-group">
                        <label for="q_searchVal" class="">제목</label>
                        <input type="text" name="q_searchVal" id="q_searchVal" value="${paramMap.q_searchVal}" class="form-control" placeholder="검색어를 입력하세요." />
                    </div>
                    <div class="form-group">
                        <label for="q_startDt" class="control-label">시작일</label>
                        <input type="text" class="from-date form-control" name="q_startDt" id="q_startDt" value="${paramMap.q_startDt}" style="width: 120px;" />
                    </div>
                    <div class="form-group">
                        <label for="q_endDt" class="control-label">종료일</label>
                        <input type="text" class="to-date form-control" name="q_endDt" id="q_endDt" value="${paramMap.q_endDt}" style="width: 120px;" />
                    </div>
                    <button type="submit" class="btn btn-info">검색</button>
                    <button type="button" class="btn btn-info" onclick="opSearchReset();">초기화</button>
                </div>
                <%-- 페이징 관련 파라미터 생성. 목록표시 갯수 선택 자동생성--%>
                <op:pagerParam title="일정 목록" ignores="" />
            </fieldset>
        </form>
    </div>

    <div class="block table-responsive">
        <!-- 리스트 -->
        <table class="table table-bordered table-striped table-hover op-table-list" summary="일정관리  목록으로 순번,제목,구분,일정구분,시작일,종료일,시작시간,종료시간,등록자명,등록일시 정보를 제공합니다.">
            <caption class="hidden">일정관리 목록</caption>
            <colgroup>
                <col width="50" />
                <col width="" />
                <col width="170" />
                <col width="300" />
            </colgroup>
            <thead>
                <tr>
                    <th class="text-center">순번</th>
                    <th class="text-center">제목</th>
                    <th class="text-center">구분</th>
                    <th class="text-center">기간</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="index" value="${pager.indexNo}" />
                <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                    <tr>
                        <td class="text-center">${index-status.index}</td>
                        <td>
                            <a href="PD_selectSchdul.do?q_seCdId=${dataVo.seCdId}&amp;q_schdlSeCdId=${dataVo.schdlSeCdId}&amp;q_plcCdId=${dataVo.plcCdId}&amp;q_regSn=${dataVo.regSn}" class="opSchdulPopup">${dataVo.ttl}</a>
                        </td>
                        <td class="text-center">${dataVo.seNm}
                            <c:choose>
                                <c:when test="${dataVo.seCdId eq 'program'}">
                                    ${dataVo.schdulSeNm}
                                </c:when>
                                <c:when test="${dataVo.seCdId eq 'restde'}">
                                    ${dataVo.restdeSeNm}
                                </c:when>
                            </c:choose>
                        </td>
                        <td class="text-left">
                            ${dataVo.bgngYmd} ~
                            <c:choose>
                                <c:when test="${dataVo.rpttYn eq 'Y' and empty dataVo.endYmd}">종료일</c:when>
                                <c:otherwise>${dataVo.endYmd}</c:otherwise>
                            </c:choose>
                            <c:if test="${not empty dataVo.bgngHr and not empty dataVo.endHr}">
                                [${dataVo.bgngHr} ~ ${dataVo.endHr}]
                             </c:if>
                            <div>
                                <c:choose>
                                    <c:when test="${dataVo.rpttYn eq 'Y'}">
                                        <c:if test="${not empty dataVo.rpttEndYmd}">
                                            <div class="text-info">종료일 : ${dataVo.rpttEndYmd}</div>
                                        </c:if>
                                        <c:choose>
                                            <c:when test="${dataVo.rpttTypeCd eq 'Y'}">
                                                <strong>매년</strong>
                                                <code>${fn:substring(dataVo.bgngYmd, 5, 7)}월 ${fn:substring(dataVo.bgngYmd, 8, -1)}일~${fn:substring(dataVo.endYmd, 5, 7)}월 ${fn:substring(dataVo.endYmd, 8, -1)}일</code>
                                            </c:when>
                                            <c:when test="${dataVo.rpttTypeCd eq 'M'}">
                                                <strong>매월</strong>
                                                <code>${fn:substring(dataVo.bgngYmd, 8, -1)}일~${fn:substring(dataVo.endYmd, 8, -1)}일</code>
                                            </c:when>
                                            <c:when test="${dataVo.rpttTypeCd eq 'W'}">
                                                <strong>매주</strong>
                                                <code>
                                                    <c:if test="${dataVo.sndayRsvtNo eq 0}">일 </c:if>
                                                    <c:if test="${dataVo.mndayRsvtNo eq 2}">월 </c:if>
                                                    <c:if test="${dataVo.tsdayRsvtNo eq 3}">화 </c:if>
                                                    <c:if test="${dataVo.wddayRsvtNo eq 4}">수 </c:if>
                                                    <c:if test="${dataVo.trdayRsvtNo eq 5}">목 </c:if>
                                                    <c:if test="${dataVo.frdayRsvtNo eq 6}">금 </c:if>
                                                    <c:if test="${dataVo.stdayRsvtNo eq 7}">토 </c:if>
                                                </code>
                                            </c:when>
                                            <c:when test="${dataVo.rpttTypeCd eq 'D'}">
                                                <strong>매일</strong>
                                            </c:when>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <!-- <strong>매일</strong> -->
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <op:no-data obj="${pager}" colspan="4" />
            </tbody>
        </table>
        <!-- //리스트 -->
    </div>

    <!-- 페이징 -->
    <op:pager pager="${pager}" />
    <!-- //페이징 -->
</body>
</html>