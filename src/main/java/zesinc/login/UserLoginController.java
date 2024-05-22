/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.login;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.core.lang.Validate;
import zesinc.login.domain.UserLoginVO;
import zesinc.user.support.UserConsts;
import zesinc.web.spring.controller.UserController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.MessageUtil;

/**
 * 사용자단 로그인 컨트롤러
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 7. 19.    박수정   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("사용자로그인")
@RequestMapping(value = { "/login" })
public class UserLoginController extends UserController {

    @Resource(name = "opUserLoginService")
    private UserLoginService opUserLoginService;

    /**
     * 로그인 액션
     */
    @RequestMapping(value = "BD_loginForm.do")
    public void loginForm(HttpServletRequest request, Model model, UserLoginVO userLoginVo) {
        logger.debug("userLoginVo.getReturnUrl() ?? " + userLoginVo.getReturnUrl());
        if(Validate.isEmpty(userLoginVo.getReturnUrl())) {
            logger.debug("Request.referer ?? " + request.getHeader("referer"));
            userLoginVo.setReturnUrl(request.getHeader("referer"));
            // URL이 현재 도메인과 같은지 비교해봐야 함, 다르다면 메인으로 보내기
        }
    }

    /**
     * 로그인 액션
     * @throws Exception
     */
    @RequestMapping(value = "ND_loginAction.do", method = RequestMethod.POST)
    public String loginAction(HttpServletRequest request, Model model, UserLoginVO userLoginVo) throws Exception {

        UserLoginVO dataVo = opUserLoginService.processUserIdAndPwdCheck(userLoginVo);

        String ipAddr = request.getRemoteAddr();
        dataVo.setIpAddr(ipAddr);
        if(dataVo.isValidId()) {
            if(dataVo.isValidPwd()) {
                dataVo.setLgnRsltNo("ok");
                OpHelper.setSession(request, BaseConfig.USER_SESSION_KEY, dataVo);
                opUserLoginService.processLogOfUser(userLoginVo);
            } else {
                dataVo.setLgnRsltNo("pswdFail");
                return alertAndBack(model, MessageUtil.getMessage("login.notMatch"));
            }
            opUserLoginService.insertLoginTryLog(dataVo);

        }

        String returnUrl = userLoginVo.getReturnUrl();
        if(Validate.isEmpty(returnUrl) || returnUrl.contains("ND_")) {
            returnUrl = UserConsts.USER_MAIN_URL;
        }

//        if(dataVo.getPswdChgDtCo() > 90) {
//            OpHelper.setSession(request, UserConsts.USER_RETURN_URL, returnUrl);
//            returnUrl = UserConsts.USER_RENEW_PSWD_URL;
//        }
        userLoginVo.setReturnUrl(returnUrl);

        return "redirect:" + userLoginVo.getReturnUrl();
    }

    /**
     * 로그아웃 액션
     */
    @RequestMapping(value = "ND_logoutAction.do")
    public String logoutAction(HttpServletRequest request, Model model, UserLoginVO userLoginVo) {

        HttpSession session = request.getSession(true);

        @SuppressWarnings("unchecked")
        Enumeration<String> enumer = session.getAttributeNames();
        while(enumer.hasMoreElements()) {
            String key = enumer.nextElement();
            session.removeAttribute(key);
        }

        session.invalidate();

        return "redirect:" + UserConsts.USER_MAIN_URL;
    }

    /**
     * SNS 연계화면의 아이콘을 통한 로그인 액션 폼
     */
    @RequestMapping(value = "PD_snsLoginForm.do")
    public void selectSnsLoginForm(HttpServletRequest request, Model model, UserLoginVO userLoginVo) {
    }

    /**
     * SNS 연계화면의 아이콘을 통한 로그인 처리.
     * 팝업으로 실행되고 로그인 성공 후 부모창에 로그인 정보를
     * 리턴한 후 스스로 창을 닫는다.
     * @throws Exception
     */
    @RequestMapping(value = "ND_processSnsLogin.do")
    public String processSnsLogin(HttpServletRequest request, Model model, UserLoginVO userLoginVo) throws Exception {

        String viewUrl = loginAction(request, model, userLoginVo);

        // 위의 loginAction 반환 결과가 redirect 라면 로그인 성공
        if(viewUrl.startsWith("redirect:")) {
            // 비밀번호 만료 --> SNS 연계 종료 처리 그냥 본창으로 비번 처리 화면으로 이동시킴
            if(userLoginVo.getReturnUrl().equals(UserConsts.USER_RENEW_PSWD_URL)) {
                return responseJson(model, Boolean.FALSE, "expire", UserConsts.USER_RENEW_PSWD_URL);
            }
        } else if(viewUrl.equals(BaseConfig.ALERT_AND_BACK)) {
            return responseJson(model, Boolean.FALSE, "notMatch", MessageUtil.getMessage("login.notMatch"));
        }

        return responseJson(model, Boolean.TRUE);
    }

}
