/* 인증 성공시 처리 함수(팝업창으로 부터 전송됨) */
var opSocialLoginSuccess = function(providerId) {
    $("#" + providerId + "Connect").hide();
    $("#" + providerId + "Connected").show();
    var primeSocialProviderId = $("#primeSocialProviderId").val();
    if(primeSocialProviderId == "") {
        opSetSocialPrime(providerId);
    }

    var socialAccountCnt = new Number($("#socialAccountCnt").val());
    socialAccountCnt = socialAccountCnt + 1;
    $("#socialAccountCnt").val(socialAccountCnt);
};

/* 대표 계정 설정 */
var opSetSocialPrime = function(providerId) {
    $("#primeSocialProviderId").val(providerId);
    opSocialRemoveClass();
    $("#" + providerId + "_prime").addClass("primeSocialProviderMark");
    $("#providerId_" + providerId).prop("checked", true);

    $(".socialPrimeImg").attr("src", "/resources/social/images/on/png/48x48/" + providerId + ".png");
};

/* 댓글 목록 갱신 */
var opSocialCmntList = function(cPage) {
    var siteSn = $("#socialDomnCode").val();
    var userMenuEngNm = $("#socialUserMenuCode").val();
    var pgeUrl = $("#socialPgeUrl").val();
    var pageNum = (cPage ? cPage : 1);

    if(!siteSn || !userMenuEngNm) {
        return false;
    } else {
        var params = {
            siteSn : siteSn,
            userMenuEngNm : userMenuEngNm,
            pgeUrl : pgeUrl,
            q_currPage : pageNum 
        };
        var href = CTX_PATH + "/social/cmnt/INC_selectSocialCmntList.do";
        $("#opSocialCmntListDiv").load(href, params, function(responseTxt, statusTxt, response) {
            // 목록 로드시 이벤트설정
            if(statusTxt == "success") {
                $(".opSocialRefen").click(function() {
                    opSocialRefrn($(this));
                });
                $(".opSocialReComend").click(function() {
                    opSocialCmntAttr($(this));
                });
                $(".opSocialNonReComend").click(function() {
                    opSocialCmntAttr($(this));
                });
                $(".opSocialSttemnt").click(function() {
                    opSocialCmntAttr($(this));
                });
                $(".opSocialDelete").click(function() {
                    opSocialDelete($(this));
                });
                $("textarea[name='socialMessage']").keyup(function() {
                    return opSocialMessageCount($(this));
                });
                setSocialDataFormSubmit("socialRfrnDataForm");
            } else {
                opSysErrorMsg(responseTxt);
            }
        });
    }
};

/* 글자수 표시 및 오버시 잘르기 */
var opSocialMessageCount = function(targetObj) {

    var targetObjId = targetObj.attr("id");
    var socialMessage = $.trim(targetObj.val());
    $("#" + targetObjId + "Cnt").text(socialMessage.length);

    if(socialMessage.length < 5 || socialMessage.length > 250) {
        socialMessage = socialMessage.substring(0, 250);
        $("#" + targetObjId + "Cnt").text(socialMessage.length);
        if(socialMessage.length > 5) {
            targetObj.val(socialMessage);
        }

        return false;
    }
    return true;
};

// 로그인체크
var opSocialLoginCheck = function() {
    var socialAccountCnt = new Number($("#socialAccountCnt").val());
    if(socialAccountCnt < 1) {
        opWarningMsg("로그인 후 이용할 수 있습니다..");
        return false;
    }
    return true;
};

// 대표계정설정 체크
var opSocilaPrimAccountCheck = function() {
    var primeSocialProviderId = $("#primeSocialProviderId").val();
    if(!primeSocialProviderId) {
        opWarningMsg("대표계정을 설정해야 합니다. 아이콘을 클릭해 주세요.");
        return false;
    }
    return true;
};

// 댓글삭제
var opSocialDelete = function(btnObj) {
    if(opSocialLoginCheck() && confirm("정말 삭제하시겠습니까?")) {
        var socialId = btnObj.attr("id");
        var socialKeyArr = socialId.split("_");

        var param = {
            siteSn : socialKeyArr[1],
            userMenuEngNm : socialKeyArr[2],
            snsSn : socialKeyArr[3]
        };

        $.ajaxSettings.traditional = true;
        $.ajax({
            url : CTX_PATH + "/social/cmnt/ND_deleteSocialCmnt.do",
            type : "post",
            dataType : "json",
            data : param,
            success : function(response) {
                if(response.result) {
                    // 화면 갱신
                    opSocialCmntList();
                } else {
                    opErrorMsg(response.message);
                }
            },
            error : function(response) {
                opErrorMsg(response.message);
                return;
            }
        });
    }
};

// 댓글등록폼 생성
var opSocialRefrn = function(btnObj) {
    if(opSocialLoginCheck() && opSocilaPrimAccountCheck()) {
        $(".opSocialRefenForm").hide();
        var targetId = btnObj.attr("id").replace("opSocialRefen", "opSocialRefenForm");
        $("#" + targetId).show();
    }
};

// 추천/비추천/신고
var opSocialCmntAttr = function(btnObj) {
    if(opSocialLoginCheck()) {
        var socialId = btnObj.attr("id");
        var socialKeyArr = socialId.split("_");

        var type = "";
        var name = "";
        if(socialKeyArr[0] == "opSocialReComend") {
            type = "recomend";
            name = "추천";
        } else if(socialKeyArr[0] == "opSocialNonReComend") {
            type = "nonrecom";
            name = "비추천";
        } else {
            type = "sttemnt";
            name = "신고";
        }

        var param = {
            siteSn : socialKeyArr[1],
            userMenuEngNm : socialKeyArr[2],
            snsSn : socialKeyArr[3],
            type : type
        };

        $.ajaxSettings.traditional = true;
        $.ajax({
            url : CTX_PATH + "/social/cmnt/ND_updateSocialCmntAttr.do",
            type : "post",
            dataType : "json",
            data : param,
            success : function(response) {
                if(response.result) {
                    // 화면 갱신
                    var count = btnObj.text().split(" ")[1];
                    var cnt = new Number(count) + 1;
                    btnObj.text(name + " " + cnt);
                } else {
                    opErrorMsg(response.message);
                }
            },
            error : function(response) {
                opErrorMsg(response.message);
                return;
            }
        });
    }
};

// 각 폼에 등록이벤트 설정
var setSocialDataFormSubmit = function(formName) {

    $("form[name='" + formName + "']").submit(function() {
        var socialMessageObj = $(this).find("textarea[name='socialMessage']");
        var message = $.trim(socialMessageObj.val());
        socialMessageObj.val(message);

        if(!opSocialMessageCount(socialMessageObj)) {
            opWarningMsg("메시지는 5자 이상 250자 이하로 작성해 주세요.");
            socialMessageObj.focus();
        } else if(!opSocilaPrimAccountCheck()) {
            return false;
        } else {
            var addParam = {};
            var hasFormId = $(this).attr("id");
            if(!hasFormId) {
                var socialProviderIds = [];
                $("#socialDataForm [name='socialProviderId']:checked").each(function() {
                    socialProviderIds.push($(this).val());
                });
                addParam = {
                    siteSn : $("#socialDataForm #socialDomnCode").val(),
                    userMenuEngNm : $("#socialDataForm #socialUserMenuCode").val(),
                    pgeSj : $("#socialDataForm #socialPgeSj").val(),
                    pgeUrl : $("#socialDataForm #socialPgeUrl").val(),
                    primeSocialProviderId : $("#socialDataForm #primeSocialProviderId").val(),
                    socialProviderId : socialProviderIds
                };
            }

            $.ajaxSettings.traditional = true;
            $(this).ajaxSubmit({
                type : "POST",
                dataType : "json",
                data : addParam,
                success : function(response) {
                    if(response.result) {
                        // 화면 갱신
                        opSocialCmntList();
                        socialMessageObj.val("");
                    } else {
                        opErrorMsg("댓글등록이 실패되었습니다 페이지를 새로 고침 후 다시 이용하세요.");
                    }
                },
                error : function(response) {
                    opErrorMsg(response.message);
                    return;
                }
            });
        }
        return false;
    });
};

/** 페이징 */
var opSocialMovePage = function(cpage) {
    opSocialCmntList(cpage);
};

/* 초기화 */
$(document).ready(function() {

    setSocialDataFormSubmit("socialDataForm");

    $("textarea[name='socialMessage']").keyup(function() {
        return opSocialMessageCount($(this));
    });

    // 인증창 팝업후 연동
    $(".connectButton").click(function(event) {
        event.preventDefault();
        window.open("", "opSocialSigninWin", "width=600,height=400");

        var action = $(this).attr("href");
        var providerId = $(this).attr("id").split("_")[0];
        if(providerId == "facebook") {
            action += "?scope=publish_actions&display=popup";
        }
        $("#socialSigninForm").attr("action", action);
        $("#socialSigninForm").submit();
    });

    // 인증 해제
    $("#all_signout").click(function(event) {
        event.preventDefault();
        $.ajax({
            type : "post",
            dataType : "json",
            url : $(this).attr("href"),
            success : function(response) {
                opSocialLogoutSuccess(new Number(response.value));
            },
            error : function(response, status, error) {
                opSysErrorMsg(response.responseText);
                return;
            }
        });
        return false;
    });

    // 대표 계정으로 설정
    $(".connectedButton").click(function(event) {
        event.preventDefault();
        var action = $(this).attr("id");
        opSetSocialPrime(action.split("_")[0]);
    });

    // 대표 계정은 체크박스를 해제하지 못하도록 설정
    $("#socialDataForm input[name='socialProviderId']").click(function() {
        var primeSocialProviderId = $("#primeSocialProviderId").val();
        var socialProviderId = $(this).val();
        if(primeSocialProviderId && (primeSocialProviderId == socialProviderId)) {
            opWarningMsg("대표계정은 선택취소 할수 없습니다.");
            $("#providerId_" + socialProviderId).prop("checked", true);
            return false;
        }
    });

    opSocialCmntList();
});
