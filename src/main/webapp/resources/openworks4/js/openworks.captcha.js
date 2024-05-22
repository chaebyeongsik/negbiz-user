/*
 * <div id="opCaptchaOption" /> Tag가 존재하면 자동으로 생성한다.
 * 정확히는 아래 기본옵션의 opCaptchaOption.CAPTCHA_ID 로 사용하며, 변경이 필요한 경우
 * opCaptchaOption  옵션을 오버라이드 재정의하여 사용하면 된다.
 */

/*
 * Captcha에서 인증 성공시에 반환하는 인증 코드를 자동으로 Form에 추가해 준다.
 */
var opVerifyCallback = function(response) {
    if($("#" + opCaptchaOption.FORM_ID + " #opCaptchaResponse").attr("id")) {
        $("#" + opCaptchaOption.FORM_ID + " #opCaptchaResponse").val(response);
    } else {
        var input = "<input type=\"hidden\" id=\"opCaptchaResponse\" name=\"opCaptchaResponse\" value=\"" + response + "\" />";
        $("#" + opCaptchaOption.FORM_ID).prepend(input);
    }
};

/*
 * Captcha에서 인증 시간이 오버된 경우 호출(인증키가 무효됨)
 */
var opExpiredCallback = function() {
    grecaptcha.reset(widget);
};

/*
 * 기본 옵션
 */
var opCaptchaOption = {
    CAPTCHA_ID : "opCaptChaPan",
    FORM_ID : OpConfig.defaultForm,
    SITE_KEY : OpConfig.captcha.siteKey,
    TYPE : "image",
    VERIFY_CALLBACK : opVerifyCallback,
    EXPIRED_CALLBACK : opExpiredCallback
};

var widget;
/* 공통 초기화 기능 */
var onloadCallback = function() {
    if(!$("#" + opCaptchaOption.CAPTCHA_ID).attr("id")) {
        return false;
    }

    widget = grecaptcha.render(opCaptchaOption.CAPTCHA_ID, {
        "sitekey" : opCaptchaOption.SITE_KEY,
        "type" : opCaptchaOption.TYPE,
        "callback" : opCaptchaOption.VERIFY_CALLBACK,
        "expired-callback" : opCaptchaOption.EXPIRED_CALLBACK
    });
};