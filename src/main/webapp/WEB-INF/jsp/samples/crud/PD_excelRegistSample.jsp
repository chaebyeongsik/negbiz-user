<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>엑셀 일괄등록 샘플</title>

    <op:jsTag type="libs" items="colorbox, highlight, ui"/>
    <op:jsTag type="user" items="ui, opform" />

    <!-- 기능별 스크립트 정의 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/samples/crud/samples.crud.js"></script>

    <script type="text/javascript">
        //<![CDATA[

        $(document).ready(function() {
        });

        var opSubmit = function(){
            //submit script
            opWarningMsg("opSubmit 스크립트 넣으시면됨.");
        };

        var opDeleteSelected = function(){
            opWarningMsg("opDeleteSelected 스크립트 넣으시면됨.");
        };
        //]]>
    </script>
</head>
<body>
    <div class="help-block text-right">
        <span class="mandatory">*</span> 일괄등록할 엑셀을 선택하여 주십시오.
    </div>
    <c:set value="아이디,이름,직급코드,부서코드,비밀번호,전화번호,팩스번호,휴대폰번호,이메일,담당업무" var="headerNm" />
    <c:set value="id,name,jbgdCdId,DeptCdId,picPswd,telno,Fxnum,MoblphonNo,Email,jbgdCdId" var="headerId" />
    <c:set value="M,M,S,S,M,M,M,M,L,L" var="columnSize" /><!-- s,m,l -->
    <op:excelToTable headerId="${headerId}" headerNm="${headerNm}" size="${columnSize}" />

</body>
</html>