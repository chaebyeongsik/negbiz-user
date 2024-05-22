<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>메인페이지</title>
<script type="text/javascript">
    $().ready(function() {
    });
</script>
</head>
<body>

    <!-- Header Carousel -->
    <header id="myCarousel" class="carousel slide">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <c:forEach items="${visaulList}" var="visaulVo" varStatus="visaulStatus">
                <li data-target="#myCarousel" data-slide-to="${visaulStatus.index}" <c:if test="${visaulStatus.index eq 0}">class="active"</c:if>></li>
            </c:forEach>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <c:forEach items="${visaulList}" var="visaulVo" varStatus="visaulStatus">
                <div class="item <c:if test="${visaulStatus.index eq 0}">active</c:if>">
                    <%--                  style="background-image:url('${visaulVo.filePathNm}');" --%>
                    <div class="fill">
                        <a href="${visaulVo.linkUrlAddr}" <c:if test="${visaulVo.linkTypeNm eq '2010'}">target="_blank"</c:if>>
                            <img alt="${visaulVo.imgAltrtvNm}" src="${visaulVo.filePathNm}" width="100%" height="100%">
                        </a>
                    </div>
                    <div class="carousel-caption">
                        <h2>${visaulVo.linkExpln}</h2>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>
    </header>

    <!-- Page Content -->
    <div class="container">

        <!-- Marketing Icons Section -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">방문을 환영합니다.</h1>
            </div>
            <div class="col-md-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>
                            <i class="fa fa-fw fa-check"></i>
                            <a href="/user/bbs/BD_selectBbsList.do?q_bbsSn=1001">공지사항</a>
                        </h4>
                    </div>
                    <ul class="list-group">
                        <c:choose>
                            <c:when test="${not empty bbsList1001}">
                                <c:forEach items="${bbsList1001}" var="bbsList1001Vo">
                                    <li class="list-group-item">
                                        <a href="/user/bbs/BD_selectBbsList.do?q_bbsSn=${bbsList1001Vo.bbsSn}&amp;q_bbsDocNo=${bbsList1001Vo.bbsDocNo}">${bbsList1001Vo.ttl}</a>
                                        [${bbsList1001Vo.regDt}]
                                        <c:if test="${bbsList1001Vo.passDay gt 7}">
                                            <span class="label label-danger">New</span>
                                        </c:if>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <li class="list-group-item">등록된 게시물이 없습니다.</li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
            <div class="col-md-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>
                            <i class="fa fa-fw fa-gift"></i>
                            <a href="/user/bbs/BD_selectBbsList.do?q_bbsSn=1002">자료실</a>
                        </h4>
                    </div>
                    <ul class="list-group">
                        <c:choose>
                            <c:when test="${not empty bbsList1002}">
                                <c:forEach items="${bbsList1002}" var="bbsList1002Vo">
                                    <li class="list-group-item">
                                        <a href="/user/bbs/BD_selectBbsList.do?q_bbsSn=${bbsList1002Vo.bbsSn}&amp;q_bbsDocNo=${bbsList1002Vo.bbsDocNo}">${bbsList1002Vo.ttl}</a>
                                        [${bbsList1002Vo.regDt}]
                                        <c:if test="${bbsList1002Vo.passDay gt 7}">
                                            <span class="label label-danger">New</span>
                                        </c:if>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <li class="list-group-item">등록된 게시물이 없습니다.</li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
            <div class="col-md-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>
                            <i class="fa fa-fw fa-compass"></i>
                            <a href="/user/bbs/BD_selectBbsList.do?q_bbsSn=1003">자유게시판</a>
                        </h4>
                    </div>
                    <ul class="list-group">
                        <c:choose>
                            <c:when test="${not empty bbsList1003}">
                                <c:forEach items="${bbsList1003}" var="bbsList1003Vo">
                                    <li class="list-group-item">
                                        <a href="/user/bbs/BD_selectBbsList.do?q_bbsSn=${bbsList1003Vo.bbsSn}&amp;q_bbsDocNo=${bbsList1003Vo.bbsDocNo}">${bbsList1003Vo.ttl}</a>
                                        [${bbsList1003Vo.regDt}]
                                        <c:if test="${bbsList1003Vo.passDay gt 7}">
                                            <span class="label label-danger">New</span>
                                        </c:if>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <li class="list-group-item">등록된 게시물이 없습니다.</li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>

            <div class="col-md-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>
                            <i class="fa fa-fw fa-compass"></i> 팝업존
                        </h4>
                    </div>
                    <!-- 팝업존 Section -->
                    <div class="panel-body">
                        <div id="carousel-example-popupzone" class="carousel slide" data-ride="carousel">
                            <!-- Indicators -->
                            <ol class="carousel-indicators">
                                <c:forEach items="${popupZoneList}" var="popupZoneVo" varStatus="popupZoneStatus">
                                    <li data-target="#myCarousel" data-slide-to="${popupZoneStatus.index}" <c:if test="${popupZoneStatus.index eq 0}">class="active"</c:if>></li>
                                </c:forEach>
                            </ol>

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner">
                                <c:forEach items="${popupZoneList}" var="popupZoneVo" varStatus="popupZoneStatus">
                                    <div class="item <c:if test="${popupZoneStatus.index eq 0}">active</c:if>">
                                        <div class="fill">
                                            <a href="${popupZoneVo.linkUrlAddr}" <c:if test="${popupZoneVo.linkTypeNm eq '2010'}">target="_blank"</c:if>>
                                                <img alt="${popupZoneVo.imgAltrtvNm}" src="${popupZoneVo.filePathNm}" width="100%" height="200">
                                            </a>
                                        </div>
                                        <div class="carousel-caption">
                                            <h2>${popupZoneVo.linkExpln}</h2>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                            <!-- Controls -->
                            <a class="left carousel-control" href="#carousel-example-popupzone" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-popupzone" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>
                    <!-- /.row -->
                </div>
            </div>


        </div>
        <!-- /.row -->
        <div class="row">
        	<div class="col-md-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>
                            <i class="fa fa-fw fa-check"></i>
                            <a href="/user/form/BD_selectFormList.do?">신청</a>
                        </h4>
                    </div>
                    <ul class="list-group">
                        <c:choose>
                            <c:when test="${not empty formList}">
                                <c:forEach items="${formList}" var="formVo">
                                    <li class="list-group-item">
                                        <a href="/user/form/BD_selectForm.do?q_formSn=${formVo.formSn}">${formVo.formTtl}</a>
                                        [${formVo.bgngYmd} ~ [${formVo.endYmd}]
                                        <c:if test="${formVo.passDay lt 7}">
                                            <span class="label label-danger">New</span>
                                        </c:if>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <li class="list-group-item">등록된 게시물이 없습니다.</li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
          </div>

        <!-- 포토겔러리 Section -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">포토겔러리</h2>
            </div>
            <c:choose>
                <c:when test="${not empty bbsList1005}">
                    <c:forEach items="${bbsList1005}" var="bbsList1005Vo">
                        <div class="col-md-4 col-sm-6">
                            <a href="/user/bbs/BD_selectBbsList.do?q_bbsSn=${bbsList1005Vo.bbsSn}&amp;q_bbsDocNo=${bbsList1005Vo.bbsDocNo}">
                                <c:choose>
                                    <c:when test="${not empty bbsList1005Vo.fileList }">
                                        <c:set var="thumbVo" value="${bbsList1005Vo.fileList[0]}" />
                                        <c:set var="fileExpln" value="${thumbVo.orgnlFileNm}" />
                                        <c:if test="${not empty thumbVo.fileExpln}">
                                            <c:set var="fileExpln" value="${thumbVo.fileExpln}" />
                                        </c:if>
                                        <img class="img-responsive img-portfolio img-hover" src="${thumbVo.thmbPathNm}" alt="${fileExpln}" width="700" height="450">
                                    </c:when>
                                    <c:otherwise>
                                        <img class="img-responsive img-portfolio img-hover" src="http://placehold.it/700x450" alt="등록된 사진이 없습니다.">
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="col-md-4 col-sm-6">
                        <p>등록된 게시물이 없습니다.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <!-- /.row -->

        <hr>

        <!-- 베너 Section -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">베너목록</h2>
            </div>
            <c:forEach items="${bannerList}" var="bannerVo" varStatus="bannerStatus">
                <div class="col-md-2 col-sm-4 col-xs-6">
                    <a href="${bannerVo.linkUrlAddr}" <c:if test="${bannerVo.linkTypeNm eq '2010'}">target="_blank"</c:if>>
                        <img class="img-responsive customer-img" src="${bannerVo.filePathNm}" alt="${bannerVo.imgAltrtvNm}" width="120" height="100">
                    </a>
                </div>
            </c:forEach>
        </div>
        <!-- /.row -->

        <hr>

    </div>
    <!-- /.container -->

    <!-- 메인컨텐츠 -->
</body>
</html>