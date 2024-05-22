<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

<op:jsTag type="libs" items="form" />

<c:if test="${UserMenu.dgstfnIndctYn eq 'Y'}">

    <div class="row well">
        <div class="col-xs-12">
            <form id="opSatisfyForm" name="opSatisfyForm" method="post" action="/user/cms/evl/ND_insertCmsEvl.do">
                <input type="hidden" name="siteSn" value="${UserMenu.siteSn}" />
                <input type="hidden" name="userMenuEngNm" value="${UserMenu.userMenuEngNm}" />

                <fieldset>
                    <legend class="hide">현재 컨텐츠에 대한 만족도 조사</legend>

                    <label class="radio-inline">
                        <input type="radio" name="artclSn" id="artclSn_5" value="5">
                        매우만족
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="artclSn" id="artclSn_4" value="4">
                        만족
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="artclSn" id="artclSn_3" value="3">
                        보통
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="artclSn" id="artclSn_2" value="2">
                        불만족
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="artclSn" id="artclSn_1" value="1">
                        매우불만족
                    </label>
                    <div class="row">
                        <div class="col-xs-10">
                            <input type="text" name="opnnCn" title="컨텐츠에 대한 내용을 입력해주세요" value="" class="form-control" />
                        </div>
                        <div class="col-xs-2">
                            <input type="submit" class="btn btn-sm btn-success" value="만족도 등록" title="컨텐츠 만족도 등록" />
                        </div>
                    </div>

                </fieldset>
            </form>
        </div>
    </div>
    <script type="text/javascript">
                    //<![CDATA[
                    /* 초기화 */
                    $(document).ready(function() {
                        $("#opSatisfyForm").submit(function() {
                            var artclSnVal = $("#opSatisfyForm input[name='artclSn']:checked").val();
                            if(!artclSnVal) {
                                opWarningMsg("만족도 항목을 선택하세요.");
                                return false;
                            }
                            $(this).ajaxSubmit({
                                url : $(this).attr("action"),
                                type : "POST",
                                dataType : "json",
                                success : function(response) {
                                    if(response.result) {
                                        opSuccessMsg("만족도 평가를 등록하였습니다.");
                                    } else {
                                        opWarningMsg(response.message);
                                    }
                                },
                                error : function(data) {
                                    alert("오류가 발생하였습니다.");
                                    return false;
                                }
                            });
                            return false;
                        });
                    });
                    //]]>
                </script>
</c:if>
