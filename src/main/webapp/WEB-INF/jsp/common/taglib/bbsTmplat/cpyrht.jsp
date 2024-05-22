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
                <c:if test="${bbsObj.cprgtUseYn eq 'Y' and not empty bbsObj.cprgtTypeNm}">
                    ${bbsObj.cprgtUseYn}
                </c:if>
            </c:when>
            <c:when test="${bbsType eq 'view'}">
                <c:choose>
                    <c:when test="${bbsObj.autTypeNm eq 'USER'}">
                        <c:if test="${bbsObj.cprgtUseYn eq 'Y' and not empty OpbbsCpyrhtTy}">
                            <a href="${bbsObj.cprgtCn}" target="_blank"><img alt="CCL By ${OpbbsCpyrhtTy}" src="/resources/libs/ccl/${OpbbsCpyrhtTy}-xs.png"></a>
                        </c:if>
                    </c:when>
                    <c:when test="${bbsObj.autTypeNm eq 'MNGR'}">
                        <c:if test="${bbsObj.cprgtUseYn eq 'Y' and not empty OpbbsCpyrhtTy}">
                            ${bbsObj.cprgtCn}
                        </c:if>
                    </c:when>
                </c:choose>
            </c:when>
            <c:when test="${bbsType eq 'value'}">
                ${OpbbsCpyrhtTy}
            </c:when>
            <c:when test="${bbsType eq 'text'}">
                ${bbsItemVo.colNm}
            </c:when>
            <c:when test="${bbsType eq 'label'}">
                <label for="cprgtUseYnY" class="<%= BbsStyleSupport.BBS_LABEL_CLASS %>">
                    <c:if test="${bbsItemVo.esntlYn eq 'Y'}">
                        <span class="<%= BbsStyleSupport.BBS_REQUIRE_CLASS %>">*</span>
                    </c:if>
                    ${bbsItemVo.colNm}
                </label>
            </c:when>
            <c:when test="${bbsType eq 'desc'}">
                ${bbsItemVo.bbsColExpln}
            </c:when>
            <c:when test="${bbsType eq 'form'}">
                <div class="form-group">
                    <div class="col-sm-10">
                        <script type="text/javascript">
                            //<![CDATA[
                            var toggleCpyrhtUseAt = function(radioObj) {
                                var val = $(radioObj).val();
                                if(val == "Y") {
                                    $(".cpyrht-group").show();
                                } else {
                                    $(".cpyrht-group").hide();
                                }
                            };
                            //]]>
                        </script>
                        <div class="row">
                            <div class="col-sm-8">
                                <label for="cprgtUseYnY" class="<%= BbsStyleSupport.BBS_RADIOLABEL_CLASS %>">
                                    <input type="radio" name="cprgtUseYn" id="cprgtUseYnY" value="Y" onclick="toggleCpyrhtUseAt(this);" <c:if test="${bbsObj.cprgtUseYn eq 'Y'}">checked="checked"</c:if> class="<%= BbsStyleSupport.BBS_RADIO_CLASS %>" />
                                    예
                                </label>
                                <label for="cprgtUseYnN" class="<%= BbsStyleSupport.BBS_RADIOLABEL_CLASS %>">
                                    <input type="radio" name="cprgtUseYn" id="cprgtUseYnN" value="N" onclick="toggleCpyrhtUseAt(this);" <c:if test="${empty bbsObj.cprgtUseYn or bbsObj.cprgtUseYn eq 'N'}">checked="checked"</c:if> class="<%= BbsStyleSupport.BBS_RADIO_CLASS %>" />
                                    아니오
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group cpyrht-group" <c:if test="${bbsObj.cprgtUseYn ne 'Y'}"> style="display:none;"</c:if>>
                    <label for="authrIndict" class="control-label col-sm-3">저작자표시 </label>
                    <div class="col-sm-9">
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="authrIndict" class="<%= BbsStyleSupport.BBS_RADIOLABEL_CLASS %>">
                                    <input type="radio" name="authrIndict" id="authrIndictBy" value="by" checked="checked" class="<%= BbsStyleSupport.BBS_RADIO_CLASS %>" />
                                    표시
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group cpyrht-group" <c:if test="${bbsObj.cprgtUseYn ne 'Y'}"> style="display:none;"</c:if>>
                    <label for="prftmkPurpsPerm" class="control-label col-sm-3">영리목적허용 </label>
                    <div class="col-sm-9">
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="prftmkPurpsPermY" class="<%= BbsStyleSupport.BBS_RADIOLABEL_CLASS %>">
                                    <input type="radio" name="prftmkPurpsPerm" id="prftmkPurpsPermY" value="" <c:if test="${empty prftmkPurpsPerm}">checked="checked"</c:if> class="<%= BbsStyleSupport.BBS_RADIO_CLASS %>" />
                                    허용
                                </label>
                                <label for="prftmkPurpsPermN" class="<%= BbsStyleSupport.BBS_RADIOLABEL_CLASS %>">
                                    <input type="radio" name="prftmkPurpsPerm" id="prftmkPurpsPermN" value="nc" <c:if test="${prftmkPurpsPerm eq 'nc'}">checked="checked"</c:if> class="<%= BbsStyleSupport.BBS_RADIO_CLASS %>" />
                                    비영리
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group cpyrht-group" <c:if test="${bbsObj.cprgtUseYn ne 'Y'}"> style="display:none;"</c:if>>
                    <label for="cntntsChangePerm" class="control-label col-sm-3">컨텐츠변경허용 </label>
                    <div class="col-sm-9">
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="cntntsChangePermY" class="<%= BbsStyleSupport.BBS_RADIOLABEL_CLASS %>">
                                    <input type="radio" name="cntntsChangePerm" id="cntntsChangePerm" value="" <c:if test="${empty cntntsChangePerm}">checked="checked"</c:if> class="<%= BbsStyleSupport.BBS_RADIO_CLASS %>" />
                                    허용
                                </label>
                                <label for="prftmkPurpsPermN" class="<%= BbsStyleSupport.BBS_RADIOLABEL_CLASS %>">
                                    <input type="radio" name="cntntsChangePerm" id="cntntsChangePermND" value="nd" <c:if test="${cntntsChangePerm eq 'nd'}">checked="checked"</c:if> class="<%= BbsStyleSupport.BBS_RADIO_CLASS %>" />
                                    변경금지
                                </label>
                                <label for="prftmkPurpsPermN" class="<%= BbsStyleSupport.BBS_RADIOLABEL_CLASS %>">
                                    <input type="radio" name="cntntsChangePerm" id="cntntsChangePermSA" value="sa" <c:if test="${cntntsChangePerm eq 'sa'}">checked="checked"</c:if> class="<%= BbsStyleSupport.BBS_RADIO_CLASS %>" />
                                    동일조건변경허락
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
        </c:choose>
