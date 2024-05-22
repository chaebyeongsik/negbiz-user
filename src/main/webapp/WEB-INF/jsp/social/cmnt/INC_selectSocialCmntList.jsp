<%--
/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<div class="col-md-12 table-responsive">
    <!-- 리스트 -->
    <table class="table table-bordered table-striped table-hover op-table-list" summary="SNS커멘트 목록으로 프로필, 내용, 닉네임, 등록일 정보를 제공합니다.">
        <caption class="hidden">SNS커멘트 목록</caption>
        <colgroup>
            <col width="12%" />
            <col width="" />
            <col width="12%" />
            <col width="12%" />
        </colgroup>
        <thead>
            <tr>
                <th class="text-center">프로필</th>
                <th class="text-center">내용</th>
                <th class="text-center">닉네임</th>
                <th class="text-center">등록일</th>
            </tr>
        </thead>
        <tbody>
            <c:set var="index" value="${pager.indexNo}" />
            <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                <tr id="opSocialRow_${dataVo.siteSn}_${dataVo.userMenuEngNm}_${dataVo.snsSn}">
                    <td class="text-center">
                        <c:if test="${not empty dataVo.profilAdres}">
                            <a href="${dataVo.profilAdres}" target="_blank" title="새창으로 ${dataVo.ncnm}님의 SNS로 이동합니다. ">
                        </c:if>
                        <c:choose>
                            <c:when test="${not empty dataVo.profilImageUrl}">
                                <img src="${dataVo.profilImageUrl}" alt="${dataVo.ncnm} 사진" />
                            </c:when>
                            <c:otherwise>${dataVo.ncnm}</c:otherwise>
                        </c:choose>
                        <c:if test="${not empty dataVo.profilAdres}">
                            </a>
                        </c:if>
                    </td>
                    <c:set var="paddingLeft" value="${dataVo.opnnGrdSn * 30 + 10}" />
                    <td style="padding-left: ${paddingLeft}px;">
                        <c:choose>
                            <c:when test="${dataVo.delYn eq 'Y'}">
                                ※ 작성자에 의해 삭제되었습니다.
                            </c:when>
                            <c:when test="${dataVo.picDelYn eq 'Y'}">
                                ※ 관리자가 내용을 표시하지 않도록 처리한 글입니다.
                            </c:when>
                            <c:otherwise>
                                <%-- 본인글 확인용 --%>
                                <c:set var="css" value="none" />
                                <c:set var="doneLoop" value="true" />
                                <c:forEach var="account" items="${ACCOUNT_LIST}">
                                    <c:if test="${account eq dataVo.reprsntAcnt and doneLoop}">
                                        <c:set var="css" value="inline" />
                                        <c:set var="doneLoop" value="false" />
                                    </c:if>
                                </c:forEach>

                                <op:nrToBr content="${dataVo.msgCn}" />
                                <div>
                                    <p class="text-success">${dataVo.svcId}</p>
                                </div>
                                <c:if test="${dataVo.snsSn eq dataVo.rfrncDocNo}">
                                    <button type="button" id="opSocialRefen_${dataVo.siteSn}_${dataVo.userMenuEngNm}_${dataVo.snsSn}" class="btn btn-xs btn-success opSocialRefen">댓글</button>
                                </c:if>
                                <button type="button" id="opSocialReComend_${dataVo.siteSn}_${dataVo.userMenuEngNm}_${dataVo.snsSn}" class="btn btn-xs btn-info opSocialReComend">추천 ${dataVo.rcmdtnCnt}</button>
                                <button type="button" id="opSocialNonReComend_${dataVo.siteSn}_${dataVo.userMenuEngNm}_${dataVo.snsSn}" class="btn btn-xs btn-warning opSocialNonReComend">비추천 ${dataVo.dercmCnt}</button>
                                <button type="button" id="opSocialSttemnt_${dataVo.siteSn}_${dataVo.userMenuEngNm}_${dataVo.snsSn}" class="btn btn-xs btn-danger opSocialSttemnt">신고 ${dataVo.dclrCnt}</button>
                                <button type="button" id="opSocialDelete_${dataVo.siteSn}_${dataVo.userMenuEngNm}_${dataVo.snsSn}" style="display:${css};" class="btn btn-xs btn-danger opSocialDelete">삭제</button>
                                <c:if test="${dataVo.snsSn eq dataVo.rfrncDocNo}">
                                    <div id="opSocialRefenForm_${dataVo.siteSn}_${dataVo.userMenuEngNm}_${dataVo.snsSn}" class="opSocialRefenForm" style="display:none;">

                                        <form name="socialRfrnDataForm" action="/social/cmnt/ND_insertSocialCmnt.do" method="post">
                                            <%-- 부모글일련번호 --%>
                                            <input type="hidden" name="rfrncDocNo" value="${dataVo.snsSn}" />

                                            <div class="row" style="margin-top: 3px;">
                                                <div class="col-xs-2">
                                                    <img class="socialPrimeImg" src="/resources/social/images/on/png/48x48/user.png" />
                                                </div>
                                                <div class="col-xs-10">
                                                    <textarea name="socialMessage" id="opSocialMessage${status.index}" rows="3" cols="50" class="form-control"></textarea>
                                                </div>
                                            </div>

                                            <div class="row" style="margin-top: 3px;">
                                                <div class="col-xs-2">&nbsp;</div>
                                                <div class="col-xs-5 ">
                                                    ※ 입력글자 제한 <span id="opSocialMessage${status.index}Cnt">0</span> / 250
                                                </div>
                                                <div class="col-xs-5 btn-group">
                                                    <div class="pull-right">
                                                        <button type="submit" value="등록" class="btn btn-sm btn-success">등록</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="text-center">${dataVo.ncnm}</td>
                    <td class="text-center">
                        <fmt:formatDate value="${dataVo.regDt}" pattern="yyyy-MM-dd" />
                    </td>
                </tr>
            </c:forEach>
            <op:no-data obj="${pager}" colspan="4" />
        </tbody>
    </table>
    <!-- //리스트 -->
</div>

<!-- 페이징 -->
<op:pager pager="${pager}" script="opSocialMovePage" />
<!-- //페이징 -->
