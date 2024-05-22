/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.join;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.core.config.Config;
import zesinc.core.lang.Validate;
import zesinc.login.domain.UserLoginVO;
import zesinc.user.join.domain.UserJoinVO;
import zesinc.user.support.UserConsts;
import zesinc.user.support.UserType;
import zesinc.web.auth.AuthWebType;
import zesinc.web.auth.anotation.OpenworksAuthWeb;
import zesinc.web.spring.controller.UserController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.CryptoUtil;
import zesinc.web.utils.MessageUtil;
import zesinc.web.utils.PasswdUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * 사용자 가입 Controller 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 7. 15.    박수정   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("사용자가입")
@RequestMapping(value = { "/user/join" })
public class UserJoinController extends UserController {

    @Resource(name = "opUserJoinService")
    private UserJoinService opUserJoinService;

    /**
     * 개인 사용자가입폼
     *
     * @param request
     * @param model
     * @param userJoinVo
     */
    @RequestMapping(value = "BD_insertUserJoinForm.do")
    public String insertIndvdlUserJoinForm(HttpServletRequest request, Model model, UserJoinVO userJoinVo) {

        if(Validate.isEmpty(userJoinVo.getUserTypeNm())) {
            return alertAndBack(model, "잘못된 접근입니다. 사용자를 선택하여주시기 바랍니다.");
        }
        ValidateResultHolder holder = ValidateUtil.validate(userJoinVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        String uri = "";
        if(UserType.INDVDL.getUserType().equals(userJoinVo.getUserTypeNm())) {
            model.addAttribute("indvdlUserIemEstbs", opUserJoinService.selectIndvdlUserInfoIemEstbsYn());
            model.addAttribute("userGradList", opUserJoinService.selectUserGradInUseList());
            uri = "/user/join/BD_insertIndvdlUserJoinForm";
        } else {
            model.addAttribute("entrprsUserIemEstbs", opUserJoinService.selectIndvdlUserInfoIemEstbsYn());
            uri = "/user/join/BD_insertEntrprsUserJoinForm";
        }

        return uri;
    }

    /**
     * 사용자유형 선택 화면
     *
     * @param request
     * @param model
     * @param userJoinVo
     */
    @RequestMapping(value = "BD_userTypeChoose.do")
    public void userTypeChoose(HttpServletRequest request, Model model, UserJoinVO userJoinVo) {

    }

    /**
     * 개인 사용자 등록 처리
     * @throws Exception
     */
    @RequestMapping(value = "ND_insertIndvdlUser.do", method = RequestMethod.POST)
    public String insertIndvdlUser(HttpServletRequest request, Model model, UserJoinVO userJoinVo) throws Exception {
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(userJoinVo);
        if(holder.isValid()) {
            // 중복아이디 검사
            if(0 < opUserJoinService.selectDupCheckUserId(userJoinVo)) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.overlapCode"));
            }

            // 암화화되어 넘어온 비밀번호 복호화(웹페이지에서 평문전송 방지를 위해 PBKDF2 방식으로 암호화되어 넘어옴)
            if (Validate.isNotEmpty(userJoinVo.getUserPswd())) {
                userJoinVo.setUserPswd(CryptoUtil.decrypt(String.valueOf(userJoinVo.getUserPswd())));
            }
            if (Validate.isNotEmpty(userJoinVo.getConfirmPassword())) {
                userJoinVo.setConfirmPassword(CryptoUtil.decrypt(String.valueOf(userJoinVo.getConfirmPassword())));
            }

            // 비밀번호 유효성 체크
            if(!PasswdUtil.isAllowPasswd(userJoinVo.getUserPswd())) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.allowPasswd"));
            }

            // 비밀번호와 비밀번호 확인 일치 검사
            if(!userJoinVo.getUserPswd().equals(userJoinVo.getConfirmPassword())) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("login.notMatchPwd"));
            }

            Integer key = opUserJoinService.insertIndvdlUser(userJoinVo);
            if(key != 1) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 기업 사용자 등록 처리
     * @throws Exception
     */
    @RequestMapping(value = "ND_insertEntrprsUser.do")
    public String insertEntrprsUser(HttpServletRequest request, Model model, UserJoinVO userJoinVo) throws Exception {
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(userJoinVo);
        if(holder.isValid()) {
            // 중복아이디 검사
            if(0 < opUserJoinService.selectDupCheckUserId(userJoinVo)) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.overlapCode"));
            }

            // 암화화되어 넘어온 비밀번호 복호화(웹페이지에서 평문전송 방지를 위해 PBKDF2 방식으로 암호화되어 넘어옴)
            if (Validate.isNotEmpty(userJoinVo.getUserPswd())) {
                userJoinVo.setUserPswd(CryptoUtil.decrypt(String.valueOf(userJoinVo.getUserPswd())));
            }
            if (Validate.isNotEmpty(userJoinVo.getConfirmPassword())) {
                userJoinVo.setConfirmPassword(CryptoUtil.decrypt(String.valueOf(userJoinVo.getConfirmPassword())));
            }

            // 비밀번호 유효성 체크
            if(!PasswdUtil.isAllowPasswd(userJoinVo.getUserPswd())) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.allowPasswd"));
            }

            // 비밀번호와 비밀번호 확인 일치 검사
            if(!userJoinVo.getUserPswd().equals(userJoinVo.getConfirmPassword())) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("login.notMatchPwd"));
            }

            Integer key = opUserJoinService.insertEntrprsUser(userJoinVo);
            if(key != 1) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.insertOk"));
    }

    /**
     * 개인 사용자 페이지 상세
     *
     * @throws Exception
     */
    @OpenworksAuthWeb(authWebType = AuthWebType.MEMBER)
    @RequestMapping(value = "BD_selectIndvdlUser.do")
    public void selectIndvdlUser(HttpServletRequest request, Model model, UserJoinVO userJoinVo) {
        model.addAttribute(BaseConfig.KEY_DATA_VO, opUserJoinService.selectIndvdlUserInfo(userJoinVo));
        model.addAttribute("userGradList", opUserJoinService.selectUserGradInUseList());
        model.addAttribute("indvdlUserIemEstbs", opUserJoinService.selectIndvdlUserInfoIemEstbsYn());
    }

    /**
     * 기업 사용자 페이지 상세
     *
     * @throws Exception
     */
    @OpenworksAuthWeb(authWebType = AuthWebType.MEMBER)
    @RequestMapping(value = "BD_selectEntrprsUser.do")
    public void selectEntrprsUser(HttpServletRequest request, Model model, UserJoinVO userJoinVo) {
        model.addAttribute(BaseConfig.KEY_DATA_VO, opUserJoinService.selectIndvdlUserInfo(userJoinVo));
        model.addAttribute("entrprsUserIemEstbs", opUserJoinService.selectEntrprsUserInfoIemEstbsYn());
    }

    /**
     * 개인 사용자 정보 수정 폼
     */
    @OpenworksAuthWeb(authWebType = AuthWebType.MEMBER)
    @RequestMapping(value = "BD_updateUserForm.do")
    public String updateIndvdlUserForm(HttpServletRequest request, Model model, UserJoinVO userJoinVo) {

        ValidateResultHolder holder = ValidateUtil.validate(userJoinVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        UserLoginVO dataVo = (UserLoginVO) OpHelper.getUserSession();
        userJoinVo.setUserId(dataVo.getUserId());
        userJoinVo.setUserTypeNm(dataVo.getUserTypeNm());

        if(UserType.INDVDL.getUserType().equals(userJoinVo.getUserTypeNm())) {
            model.addAttribute(BaseConfig.KEY_DATA_VO, opUserJoinService.selectIndvdlUserInfo(userJoinVo));
            model.addAttribute("userGradList", opUserJoinService.selectUserGradInUseList());
            model.addAttribute("indvdlUserIemEstbs", opUserJoinService.selectIndvdlUserInfoIemEstbsYn());
            return "/user/join/BD_updateIndvdlUserForm";
        } else {
            model.addAttribute("entrprsUserIemEstbs", opUserJoinService.selectEntrprsUserInfoIemEstbsYn());
            model.addAttribute(BaseConfig.KEY_DATA_VO, opUserJoinService.selectEntrprsUserInfo(userJoinVo));
            return "/user/join/BD_updateEntrprsUserForm";
        }
    }

    /**
     * 기업사용자 수정 폼
     */
    @OpenworksAuthWeb(authWebType = AuthWebType.MEMBER)
    @RequestMapping(value = "BD_updateEntrprsUserForm.do")
    public void updateEntrprsUserForm(HttpServletRequest request, Model model, UserJoinVO userJoinVo) {
        ValidateResultHolder holder = ValidateUtil.validate(userJoinVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        model.addAttribute(BaseConfig.KEY_DATA_VO, opUserJoinService.selectEntrprsUserInfo(userJoinVo));
        model.addAttribute("entrprsUserIemEstbs", opUserJoinService.selectEntrprsUserInfoIemEstbsYn());
    }

    /**
     * 개인 사용자 정보 수정 처리
     * @throws Exception
     */
    @OpenworksAuthWeb(authWebType = AuthWebType.MEMBER)
    @RequestMapping(value = "ND_updateIndvdlUser.do", method = RequestMethod.POST)
    public String updateIndvdlUser(HttpServletRequest request, Model model, UserJoinVO userJoinVo) throws Exception {
        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(userJoinVo);
        if(holder.isValid()) {
            // 암화화되어 넘어온 비밀번호 복호화(웹페이지에서 평문전송 방지를 위해 PBKDF2 방식으로 암호화되어 넘어옴)
            if (Validate.isNotEmpty(userJoinVo.getUserPswd())) {
                userJoinVo.setUserPswd(CryptoUtil.decrypt(String.valueOf(userJoinVo.getUserPswd())));
            }
            if (Validate.isNotEmpty(userJoinVo.getConfirmPassword())) {
                userJoinVo.setConfirmPassword(CryptoUtil.decrypt(String.valueOf(userJoinVo.getConfirmPassword())));
            }
            Integer key = 0;
            key = opUserJoinService.updateIndvdlUser(userJoinVo);

            if(key != 1) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 기업사용자 수정 처리
     * @throws Exception
     */
    @OpenworksAuthWeb(authWebType = AuthWebType.MEMBER)
    @RequestMapping(value = "ND_updateEntrprsUser.do")
    public String updateEntrprsUser(HttpServletRequest request, Model model, UserJoinVO userJoinVo) throws Exception {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(userJoinVo);
        if(holder.isValid()) {
            // 암화화되어 넘어온 비밀번호 복호화(웹페이지에서 평문전송 방지를 위해 PBKDF2 방식으로 암호화되어 넘어옴)
            if (Validate.isNotEmpty(userJoinVo.getUserPswd())) {
                userJoinVo.setUserPswd(CryptoUtil.decrypt(String.valueOf(userJoinVo.getUserPswd())));
            }
            if (Validate.isNotEmpty(userJoinVo.getConfirmPassword())) {
                userJoinVo.setConfirmPassword(CryptoUtil.decrypt(String.valueOf(userJoinVo.getConfirmPassword())));
            }
            Integer key = opUserJoinService.updateEntrprsUser(userJoinVo);

            if(key != 1) {
                return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.validateFail"));
        }

        return responseJson(model, MessageUtil.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

    /**
     * 사용자 아이디 중복체크
     */
    @RequestMapping(value = "ND_selectDupCheckUserId.do", method = RequestMethod.POST)
    public String selectDupCheckUserId(HttpServletRequest request, Model model, UserJoinVO userJoinVo) {

        return responseJson(model, opUserJoinService.selectDupCheckUserId(userJoinVo));
    }

    /**
     * 비밀번호 보안등급정책 팝업
     *
     * @param request
     * @param model
     */
    @RequestMapping(value = "PD_scrtyGrad.do")
    public void scrtyGradPop(HttpServletRequest request, Model model) {
        String SPECIAL_CHAR = Config.getString("passwd-config.passwdChars.specials");
        int PASSWD_MIN_LENGTH = Config.getInt("passwd-config.passwdMinLength");
        int PASSWD_MAX_LENGTH = Config.getInt("passwd-config.passwdMaxLength");

        model.addAttribute("SPECIAL_CHAR", SPECIAL_CHAR);
        model.addAttribute("PASSWD_MIN_LENGTH", PASSWD_MIN_LENGTH);
        model.addAttribute("PASSWD_MAX_LENGTH", PASSWD_MAX_LENGTH);
    }

    /**
     * processPasswordForm 설명
     *
     * @param request
     * @param model
     * @return
     */
    @OpenworksAuthWeb(authWebType = AuthWebType.MEMBER)
    @RequestMapping(value = "BD_processPasswordForm.do")
    public String processPasswordForm(HttpServletRequest request, Model model) {
        UserLoginVO dataVo = (UserLoginVO) OpHelper.getUserSession();
        if(dataVo == null) {
            return alertAndRedirect(model, MessageUtil.getMessage("common.validateFail"), UserConsts.USER_MAIN_URL);
        }

        return "/user/join/BD_processPasswordForm";
    }

    @OpenworksAuthWeb(authWebType = AuthWebType.MEMBER)
    @RequestMapping(value = "ND_processCheckPassword.do")
    public String processCheckPassword(HttpServletRequest request, Model model, UserJoinVO userJoinVo) throws Exception {
        UserLoginVO dataVo = (UserLoginVO) OpHelper.getUserSession();
        if(dataVo == null) {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        userJoinVo.setUserId(dataVo.getUserId());

        boolean result = opUserJoinService.processCheckPassword(userJoinVo);

        if(result) {
            return "redirect:/user/join/BD_updateUserForm.do";
        } else {
            return alertAndBack(model, MessageUtil.getMessage("login.notMatchPwd"));
        }
    }
}
