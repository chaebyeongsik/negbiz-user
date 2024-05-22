<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="(주)제스아이앤씨 기술연구소" />
<meta name="description" content="Openworks 포털관리 시스템" />

<title><sitemesh:write property="title" /></title>

<op:jsTag type="user" items="base" />

<link href="/resources/templateSample/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/templateSample/css/modern-business.css" rel="stylesheet">
<link href="/resources/templateSample/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script>
    //<![CDATA[
    $().ready(function() {

    });
    //]]>
</script>

<sitemesh:write property="head" />
</head>
<body style="padding-top: 0px;">
    <div style="padding:5px 20px 5px 5px;">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h6 class="panel-title">
                    <i class="icon-bubble4"></i>
                    <sitemesh:write property="title" />
                </h6>
            </div>
            <div class="panel-body">
                <sitemesh:write property="body" />
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 text-right">
                <button class="btn btn-primary btn-sm" onclick="opCloseWin();">
                    <i class="icon-cancel-circle"></i>닫기
                </button>
            </div>
        </div>
    </div>
</body>
</html>