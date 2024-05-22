<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>게시물 상세</title>

<op:jsTag type="libs" items="colorbox, highlight, ui" />
<op:jsTag type="user" items="ui, opform" />

<!-- 기능별 스크립트 정의 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/samples/crud/samples.crud.js"></script>

<script type="text/javascript">
        //<![CDATA[

        /*
         * 공통 초기화 기능
         */
        $(document).ready(function() {
        });

        //]]>
    </script>
</head>
<body>

    <!-- 검색 -->
    <form name="dataForm" id="dataForm" method="post" action="BD_crud.view.do">

        <%-- opCustomPrefix 파라미터는 현재 프로그램에서 사용할 접두사를 쉼표로 구분하여 작성한다. . 띄어 쓰이 없이 작성 --%>
        <input type="hidden" name="opCustomPrefix" id="opCustomPrefix" value="q_,qq_" />
        <%-- q_로 시작되는 파라미터만 hidden input로 생성한다. 2단째 상세화면이라면 q_,qq_ 형식으로 쉼표로 구분하여 작성한다. 띄어 쓰이 없이 작성--%>
        <op:pagerParam view="view" customPrefix="q_" />
        <%-- 페이징 관련 파라미터 생성. rowPerPage 설정 시 목록표시 갯수 선택 생성됨 --%>
        <op:pagerParam title="2차 목록 셈플용 페이지 (실제 쿼리가 없어 수치는 없음)" customPrefix="qq_" />

    </form>

    <!-- 내용보기 -->
    <div class="block table-responsive">
        <table class="table table-bordered" summary="${baseVo.ttl} 게시글 정보입니다.">
            <caption class="hidden">${baseVo.ttl}상세보기</caption>
            <colgroup>
                <col width="15%" />
                <col />
            </colgroup>
            <thead>
                <tr>
                    <th colspan="2">
                        <div class="col-md-9">
                            <span class="text-info">[${baseVo.clsfNo}]</span> ${baseVo.ttl}
                        </div>
                        <div class="col-md-3 text-right">
                            <c:choose>
                                <c:when test="${baseVo.rlsYn eq 'Y'}">공개</c:when>
                                <c:otherwise>비공개</c:otherwise>
                            </c:choose>
                            <a href="#" class="public">미공개</a>
                        </div>
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>작성자</th>
                    <td>${baseVo.rgtrNm}</td>
                </tr>
                <tr>
                    <th>조회</th>
                    <td>${baseVo.inqCnt}</td>
                </tr>
                <tr>
                    <th>등록일</th>
                    <td>
                        ${baseVo.regDt}
                        <c:if test="${not empty baseVo.updtDt}"> 
                            (최종 수정 : ${baseVo.updtDt}) by ${baseVo.updusrNm}
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th>IP</th>
                    <td>${baseVo.ipAddr}</td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>${baseVo.docContsCn}</td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td>
                        <op:fileDownload fileList="${baseVo.fileList}" />
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- //내용보기 -->

    <!-- 버튼 -->
    <div class="row block">
        <div class="col-md-12 btn-group">
            <div class="pull-right">
                <button type="button" class="btn btn-success" onclick="opInsertForm();">등록</button>
                <button type="button" class="btn btn-success" onclick="opUpdateForm();">수정</button>
                <button type="button" class="btn btn-danger" onclick="opDelete();">삭제</button>
                <button type="button" class="btn btn-primary" onclick="opList();">목록</button>
            </div>
        </div>
    </div>
    <!-- //버튼 -->

    <c:forEach items="${dataList}" var="dataVo">
        <!-- 내용보기 -->
        <div class="block table-responsive">
            <table class="table table-bordered" summary="게시판 답변내용 작성페이지입니다.">
                <caption class="hidden">게시판 답변글입력 페이지</caption>
                <colgroup>
                    <col width="15%" />
                    <col width="85%" />
                </colgroup>
                <tbody>
                    <tr>
                        <th>답변자</th>
                        <td>${dataVo.replyNm}</td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td>${dataVo.replyContents}</td>
                    </tr>
                    <tr>
                        <th>첨부파일</th>
                        <td>
                            <op:fileDownload fileList="${dataVo.fileList}" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <!-- //내용보기 -->

        <!-- 버튼 -->
        <div class="row block">
            <div class="col-md-12 btn-group">
                <div class="pull-right">
                    <button type="button" class="btn btn-success" onclick="opReplyUpdateForm(${dataVo.rfrncDocNo});">수정</button>
                    <button type="button" class="btn btn-danger" onclick="opReplyDelete(${dataVo.rfrncDocNo});">삭제</button>
                    <button type="button" class="btn btn-primary" onclick="opList();">목록</button>
                </div>
            </div>
        </div>
        <!-- //버튼 -->

    </c:forEach>

</body>
</html>