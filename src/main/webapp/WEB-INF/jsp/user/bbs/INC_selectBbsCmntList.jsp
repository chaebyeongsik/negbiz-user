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
    <table class="table table-bordered table-striped table-hover op-table-list" summary="댓글 목록으로 번호, 내용, 등록자, 등록일 정보를 제공합니다.">
        <caption class="hidden">댓글 목록</caption>
        <colgroup>
            <col width="12%" />
            <col width="" />
            <col width="12%" />
            <col width="12%" />
        </colgroup>
        <thead>
            <tr>
                <th class="text-center">번호</th>
                <th class="text-center">내용</th>
                <th class="text-center">등록자</th>
                <th class="text-center">등록일</th>
            </tr>
        </thead>
        <tbody>
            <c:set var="index" value="${pager.indexNo}" />
            <c:forEach items="${pager.list}" var="dataVo" varStatus="status">
                <tr id="opCmntRow_${dataVo.bbsSn}_${dataVo.bbsDocNo}">
                    <td class="text-center">
                        ${index-status.index}
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
                                <c:if test="${loginVo.userId eq dataVo.rgtrId}">
                                    <c:set var="css" value="inline" />
                                </c:if>

                                <op:nrToBr content="${dataVo.opnnCn}" />
                                <div>
                                    <c:if test="${dataVo.opnnSn eq dataVo.opnnRfrncSn}">
                                        <button type="button" id="opCmntRefen__${dataVo.bbsSn}__${dataVo.bbsDocNo}__${dataVo.opnnSn}" class="btn btn-xs btn-success opCmntRefen">댓글</button>
                                    </c:if>
                                    <button type="button" id="opCmntReComend__${dataVo.bbsSn}__${dataVo.bbsDocNo}__${dataVo.opnnSn}" class="btn btn-xs btn-info opCmntReComend">추천 ${dataVo.rcmdtnCnt}</button>
                                    <button type="button" id="opCmntNonReComend__${dataVo.bbsSn}__${dataVo.bbsDocNo}__${dataVo.opnnSn}" class="btn btn-xs btn-warning opCmntNonReComend">비추천 ${dataVo.dercmCnt}</button>
                                    <button type="button" id="opCmntSttemnt__${dataVo.bbsSn}__${dataVo.bbsDocNo}__${dataVo.opnnSn}" class="btn btn-xs btn-danger opCmntSttemnt">신고 ${dataVo.dclrCnt}</button>
                                    <button type="button" id="opCmntDelete__${dataVo.bbsSn}__${dataVo.bbsDocNo}__${dataVo.opnnSn}" style="display:${css};" class="btn btn-xs btn-danger opCmntDelete">삭제</button>
                                </div>
                                <c:if test="${dataVo.opnnSn eq dataVo.opnnRfrncSn}">
                                    <div id="opCmntRefenForm__${dataVo.bbsSn}__${dataVo.bbsDocNo}__${dataVo.opnnSn}" class="opCmntRefenForm" style="display:none;">

                                        <form name="cmntRfrnDataForm" action="/user/bbs/ND_insertBbsCmnt.do" method="post">
                                            <%-- 부모글일련번호 --%>
                                            <input type="hidden" name="opnnSn" value="${dataVo.opnnSn}" />

                                            <div class="row" style="margin-top: 3px;">
                                                <div class="col-xs-12">
                                                    <textarea name="opnnCn" id="opCmntMessage${status.index}" rows="3" cols="50" class="form-control"></textarea>
                                                </div>
                                            </div>

                                            <div class="row" style="margin-top: 3px;">
                                                <div class="col-xs-7 ">
                                                    ※ 입력글자 제한 <span id="opCmntMessage${status.index}Cnt">0</span> / 1000
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
                    <td class="text-center">${dataVo.rgtrNm}</td>
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
<op:pager pager="${pager}" script="opBbsCmntMovePage" />
<!-- //페이징 -->
