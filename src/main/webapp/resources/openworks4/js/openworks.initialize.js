/* ========================================================
 *
 * 공통 초기화 스크립트
 *
 * ======================================================== */

/**
 * 개발모드와 운영모드 설정에 따라서 Javascript도 같이 작동이 가능하도록 productionMode를 사용한다.
 */
var productionMode = false;
try {
    productionMode = OpConfig.productionMode;
} catch(e) {
}

$(document).ready(function() {

    /*
     * 상호 작용 스크립트 ==================================================
     */

    /**
     * 검색버튼 클릭시 자동으로 페이지 번호를 1로 갱신
     */
    $("button[type='submit']").click(function() {
        var text = $(this).text();
        if($.trim(text) == "검색") {
            var currPage = $("#q_currPage");
            if(currPage.length > 0) {
                currPage.val("1");
            }
        }
    });
    $("input[type='submit']").click(function() {
        var text = $(this).text();
        if($.trim(text) == "검색") {
            var currPage = $("#q_currPage");
            if(currPage.length > 0) {
                currPage.val("1");
            }
        }
    });


    /**
     * 대상 체크박스 전체 선택 및 색상변경
     */
    $("input[name=chk-all]").click(function() {
        var isChecked = this.checked;

        $("input[name=sns]").prop("checked", isChecked);
        if(isChecked) {
            $("tbody :checkbox").parent().parent().addClass("info");
        } else {
            $("tbody :checkbox").parent().parent().removeClass("info");
        }
    });

    /**
     * 대상 체크박스 선택에 따른 색상변경
     */
    $("input[name=sns]").click(function() {

        if($(this).is(":checked")) {
            $(this).parent().parent().addClass("info");
        } else {
            $(this).parent().parent().removeClass("info");
        }
    });

    /**
     * 팝업창 닫기(modal 창 포함)
     */
    $("#opCloseWin").click(function() {
        opCloseWin();
    });

    // ===== jQuery UI Datepicker =====//
    /**
     * Datepicker 자동 설정
     */
    $(".datepicker").datepicker({
        dateFormat : "yy-mm-dd",
        showOtherMonths : true
    });

    $(".datepicker-inline").datepicker({
        dateFormat : "yy-mm-dd",
        showOtherMonths : true
    });

    $(".datepicker-multiple").datepicker({
        dateFormat : "yy-mm-dd",
        showOtherMonths : true,
        numberOfMonths : 2
    });

    $(".datepicker-trigger").datepicker({
        dateFormat : "yy-mm-dd",
        showOn : "button",
        buttonImage : "images/interface/datepicker_trigger.png",
        buttonImageOnly : true,
        showOtherMonths : true
    });

    $(".from-date").datepicker({
        dateFormat : "yy-mm-dd",
        defaultDate : "+1w",
        numberOfMonths : 2,
        showOtherMonths : true,
        onClose : function(selectedDate) {
            $(".to-date").datepicker("option", "minDate", selectedDate);
        }
    });
    $(".to-date").datepicker({
        dateFormat : "yy-mm-dd",
        defaultDate : "+1w",
        numberOfMonths : 2,
        showOtherMonths : true,
        onClose : function(selectedDate) {
            $(".from-date").datepicker("option", "maxDate", selectedDate);
        }
    });

    $(".datepicker-restricted").datepicker({
        dateFormat : "yy-mm-dd",
        minDate : -20,
        maxDate : "+1M +10D",
        showOtherMonths : true
    });

});