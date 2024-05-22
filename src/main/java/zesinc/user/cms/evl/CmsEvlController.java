/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.cms.evl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.core.lang.Validate;
import zesinc.login.domain.UserLoginVO;
import zesinc.user.cms.evl.domain.CmsEvlVO;
import zesinc.web.spring.controller.UserController;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;

/**
 * 사용자메뉴평가 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-06-09.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller
@RequestMapping(value = { "/user/cms/evl" })
public class CmsEvlController extends UserController {

    @Resource(name = "opUserCmsEvlService")
    private CmsEvlService opUserCmsEvlService;

    /**
     * 사용자메뉴평가 등록(로그인 한 회원만 등록가능)
     * 해제가 필요한 경우 악의적 평가에 대한 내용을 담당자에게 전달 후 해제 하기 바람
     */
    @RequestMapping(value = "ND_insertCmsEvl.do", method = RequestMethod.POST)
    public String insertCmsEvl(HttpServletRequest request, Model model, CmsEvlVO cmsEvlVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(cmsEvlVo);
        if(holder.isValid()) {

            UserLoginVO loginVo = (UserLoginVO) getUserSession();
            if(Validate.isEmpty(loginVo)) {
                return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.allowLoginUser"));
            } else {
                cmsEvlVo.setRgtrId(loginVo.getUserId());
                cmsEvlVo.setRgtrNm(loginVo.getUserNm());
            }
            cmsEvlVo.setRgtrIpAddr(getIpAddr());

            Integer key = opUserCmsEvlService.insertCmsEvl(cmsEvlVo);
            if(key != 1) {
                return responseJson(model, Boolean.FALSE, key, MessageUtil.getMessage("common.alreadyApply"));
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }
        return responseJson(model, Boolean.TRUE, MessageUtil.getMessage("common.updateOk"));
    }

}
