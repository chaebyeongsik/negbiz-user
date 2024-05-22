/**
 * 목록
 */
var opList = function() {
    $("#q_bbsDocNo").val("");
    location.href = "BD_selectBbsList.do?" + opSearchQueryString("dataForm");
};

/**
 * 상세
 */
var opView = function(bbsDocNo) {
    $("#q_bbsDocNo").val(bbsDocNo);
    location.href = "BD_selectBbs.do?" + opSearchQueryString("dataForm");
};

/**
 * 등록 폼
 */
var opInsertForm = function(pageTy) {
    $("#q_bbsDocNo").val("");
    location.href = "BD_insertBbsForm.do?" + opSearchQueryString("dataForm");
};

/**
 * 수정 폼
 */
var opUpdateForm = function(pageTy) {
    location.href = "BD_updateBbsForm.do?" + opSearchQueryString("dataForm");
};

/**
 * 답글 폼
 */
var opReplyForm = function(pageTy) {
    location.href = "BD_replyBbsForm.do?" + opSearchQueryString("dataForm");
};

/**
 * 삭제
 */
var opDelete = function() {
    if(confirm(OpMessage.bbs.deleteConfirm)) {
        $("#dataForm").attr("action", "ND_deleteBbs.do");
        $("#dataForm").submit();
    }
};

/**
 * 게시글 만족도평가
 */
var opUpdateStsfdgEvl = function(btn, bbsSn, bbsDocNo, incrsLmttTime) {
    if(Number($("#stsfdgForm select[name=dgstfnSumScr]").val()) < 1) {
        opWarningMsg("만족도 점수를 선택하세요.");
        $("#stsfdgForm select[name=dgstfnSumScr]").focus();
        return false;
    }
    if((timeWait = opCheckCookieTime("stsfdgEvlCookie:" + bbsSn + ":" + bbsDocNo)) > 0) {
        $("#stsfdgForm select[name=dgstfnSumScr]").val("0");
        opWarningMsg(timeWait + "시간 후 다시 평가하실 수 있습니다.");
        return false;
    }
    var confirm = window.confirm("이 게시물을 평가하시겠습니까?");
    if(confirm != true)
        return;

    $("#stsfdgForm").ajaxSubmit({
        url : "ND_updateStsfdgEvl.do",
        type : "POST",
        dataType : "json",
        success : function(response) {
            if(response.result) {
                var result = response.value;
                if(Number(result.dgstfnSumScr) > 0) {
                    $("#stsfdgScoreAvg").html(Math.round(result.stsfdgEvlAvrg * 10) / 10);
                    $("#stsfdgSum").html(result.dgstfnSumScr);
                    $("#stsfdgCo").html(result.dgstfnEvlCnt);
                    opSetCookieExpTime("stsfdgEvlCookie:" + bbsSn + ":" + bbsDocNo, incrsLmttTime);
                } else {
                    opWarningMsg('지금은 평가하실 수 없습니다.');
                }
                $("#stsfdgForm select[name=dgstfnSumScr]").val("0");
            } else {
                opJsonMsg(response);
            }
        }
    });
};

/**
 * 게시글 추천
 */
var opUpdateRecomend = function(btn, bbsSn, bbsDocNo, incrsLmttTime) {
    if((timeWait = opCheckCookieTime("recommendCookie:" + bbsSn + ":" + bbsDocNo)) > 0) {
        opWarningMsg(timeWait + "시간 후 다시 추천하실 수 있습니다.");
        return false;
    }
    var confirm = window.confirm("이 게시물을 추천하시겠습니까?");
    if(confirm != true)
        return;

    $(btn).ajaxSubmit({
        url : "ND_updateRecomend.do",
        type : "POST",
        dataType : "json",
        data : {
            bbsSn : bbsSn,
            bbsDocNo : bbsDocNo
        },
        success : function(response) {
            if(response.result) {
                if(Number(response.value) > 0) {
                    $(btn).val("추천 " + response.value);
                    opSetCookieExpTime("recommendCookie:" + bbsSn + ":" + bbsDocNo, incrsLmttTime);
                } else {
                    opWarningMsg('지금은 추천하실 수 없습니다.');
                }
            } else {
                opJsonMsg(response);
            }
        }
    });
};

/**
 * 게시글 신고
 */
var opUpdateSttemnt = function(btn, bbsSn, bbsDocNo, incrsLmttTime) {
    if((timeWait = opCheckCookieTime("sttemntCookie:" + bbsSn + ":" + bbsDocNo)) > 0) {
        opWarningMsg(timeWait + "시간 후 다시 신고하실 수 있습니다.");
        return false;
    }
    var confirm = window.confirm("이 게시물을 신고하시겠습니까?");
    if(confirm != true)
        return;

    $(btn).ajaxSubmit({
        url : "ND_updateSttemnt.do",
        type : "POST",
        dataType : "json",
        data : {
            bbsSn : bbsSn,
            bbsDocNo : bbsDocNo
        },
        success : function(response) {
            if(response.result) {
                if(Number(response.value) > 0) {
                    $(btn).val("신고 " + response.value);
                    opSetCookieExpTime("sttemntCookie:" + bbsSn + ":" + bbsDocNo, incrsLmttTime);
                } else {
                    opWarningMsg('지금은 신고하실 수 없습니다.');
                }
            } else {
                opJsonMsg(response);
            }
        }
    });
};

/**
 * 다단 카테고리인 경우 현재 카테고리 키/값을 파라미터로 하여 하위 카테고리를 불러와 생성한다.
 */
var opLoadLwprtClCode = function(selectObj, bbsSn, targetId) {
    var value = $(selectObj).val();
    var param = {
        q_bbsSn : bbsSn,
        q_clsfNo : value
    };
    $.ajax({
        url : "selectLwprtClCode.do",
        type : "post",
        dataType : "json",
        data : param,
        success : function(response) {
            if(response.result) {
                $("#" + targetId).html("");
                var lwprtClList = response.value;
                var lwprtClListCnt = lwprtClList.length;
                $("#" + targetId).append("<option value=\"\">" + OpMessage.common.selectOption + "</option>");
                for(var i = 0 ; i < lwprtClListCnt ; i++) {
                    $("#" + targetId).append("<option value=\"" + lwprtClList[i].lwrkClsfSn + "\">" + lwprtClList[i].lwrkClsfNm + "</option>");
                }
            } else {
                opErrorMsg(response.message);
            }
        },
        error : function(response) {
            opSysErrorMsg(response.responseText);
            return;
        }
    });
};

// RSS 링크
var opRssLinkView = function(rssUrl) {
    window.open(rssUrl, "RSS_VIEW", "", "");
};

// ATOM 링크
var opAtomLinkView = function(atomUrl) {
    window.open(atomUrl, "ATOM_VIEW", "", "");
};

/**
 * SUMMARY Toggle
 */
var opToggleSumry = function() {
    $(".bbsSummary").toggle();
};

// *********************** 쿠키관련 함수 ***************************//
// 쿠키에 저장된 값으로 앞으로 얼마나 유효한지 확인
var opCheckCookieTime = function(cookieName) {
    var cukie = $.cookie(cookieName);
    if(cukie == null)
        return 0; // 쿠키가 없다
    var nowTime = new Date();
    var expTime = new Date(eval(cukie));
    if((expTime.getTime() - nowTime.getTime()) > 0)
        return (Math.ceil((expTime.getTime() - nowTime.getTime()) / 1000 / 60 / 60));
    else
        return 0;
};

// 쿠키이름과 시간설정
var opSetCookieExpTime = function(cookieName, readCookieHour) {
    var expDate = new Date();
    expDate.setTime(expDate.getTime() + (60 * 60 * 1000) * readCookieHour);
    $.cookie(cookieName, expDate.getTime(), {
        path : '/',
        expires : expDate
    });
};

// *********************** 쿠키관련 함수 끝 ***************************//

var reCaptchaValidate = function(){
    if (grecaptcha.getResponse() == ""){
        alert("자동입력 방지를 체크해주세요.");
        return false;
    }else{
        return true;
    }
}
