/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.cms.preview;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import zesinc.core.cache.CacheService;
import zesinc.core.config.Config;
import zesinc.core.lang.Validate;
import zesinc.user.cms.preview.domain.CmsPreviewVO;
import zesinc.web.spring.controller.UserController;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.DomnCacheUtil;
import zesinc.web.vo.cache.CmsCacheVO;
import zesinc.web.vo.cache.DomnCacheVO;

/**
 * 컨텐츠 및 레이아웃 미리보기
 * ORIGIN 설정은 웹서버 설정에서도 덮어쓰일 수 있으므로, 브라우저 디버거로 확인 후
 * 프로그램과 다른 값이 표시되는 경우 웹서버의 설정을 확인해야 함.
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016. 3. 11.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 */
@Controller
@RequestMapping(value = { "/user/cms/preview" })
public class CmsPreviewController extends UserController {

    /** ORIGIN Header 설정 값 */
    private static final String ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";

    /** 최상위 기본 메뉴코드 */
    public static String HIGH_CMS_CD = Config.getString("webapp-config.defaultCode.highCmsCd", "web");

    @Resource(name = "opUserCmsPreviewService")
    private CmsPreviewService opUserCmsPreviewService;

    /**
     * 사용자단 레이아웃/컨텐츠 미리보기
     */
    @RequestMapping(value = "PV_LayoutContentPreView.do")
    public String previewLayoutCntnts(HttpServletRequest request, HttpServletResponse response, Model model, CmsPreviewVO cmsPreviewVo) {

        // CORS 요청에 대한 응답 허용(관리자 사이트에서 미리보기 요청)
        response.setHeader(ALLOW_ORIGIN, "*");
        response.setHeader(ALLOW_CREDENTIALS, "true");

        /*
         * 파라미터에 따른 컨텐츠 정보
         */
        String strtContsCn = request.getParameter("mainContsCn");
        String mainContsCn = request.getParameter("mainContsCn");
        CmsPreviewVO dataVo = null;
        if(Validate.isNotEmpty(cmsPreviewVo.getString("q_userMenuEngNm"))) {
            dataVo = opUserCmsPreviewService.selectUserMenuData(cmsPreviewVo);
        } else {
            dataVo = new CmsPreviewVO();
        }
        if(Validate.isNotEmpty(mainContsCn)) {
            dataVo.setMainContsCn(mainContsCn);
            dataVo.setStrtContsCn(strtContsCn);
        }
        model.addAttribute(BaseConfig.KEY_DATA_VO, dataVo);

        /*
         * 사용자메뉴 레이아웃 정보
         */
        CmsCacheVO userMenuCacheVo = opUserCmsPreviewService.selectUserMenuPreview(cmsPreviewVo);
        request.setAttribute(BaseConfig.USER_MENU_INFO_KEY, userMenuCacheVo);

        if(Validate.isNotEmpty(userMenuCacheVo)) {
            DomnCacheVO domnVo = DomnCacheUtil.getDomainCache(cmsPreviewVo.getInteger("q_siteSn"));
            String cacheKey = domnVo.getSiteNm() + "_" + domnVo.getPortSn() + "_" + domnVo.getSitePathNm();

            // 케쉬에서 메뉴코드 Map을 가져 온다.
            @SuppressWarnings("unchecked")
            HashMap<String, CmsCacheVO> allMenuCodeMap =
                (HashMap<String, CmsCacheVO>) CacheService.get(cacheKey + BaseConfig.USER_MENU_ENG_NM_MAP_KEY);

            CmsCacheVO userTopMenuVo = null;
            CmsCacheVO userSubMenuVo = null;
            // 최상위 메뉴인 경우 메인 페이지에 해당하므로 1뎁스 메뉴를 서브 메뉴로 사용할 수 있도록 변경해준다.
            if(HIGH_CMS_CD.equals(userMenuCacheVo.getHghrkMenuEngNm())) {
                userTopMenuVo = allMenuCodeMap.get(userMenuCacheVo.getUserMenuEngNm());
            } else {
                userSubMenuVo = allMenuCodeMap.get(userMenuCacheVo.getHghrkMenuEngNm());
                userTopMenuVo = allMenuCodeMap.get(userSubMenuVo.getUpMenuEngNm());
            }

            // 현재 메뉴의 탑 메뉴 정보
            request.setAttribute(BaseConfig.USER_TOP_MENU_INFO_KEY, userTopMenuVo);
            // 현재 메뉴의 좌측 메뉴 정보
            request.setAttribute(BaseConfig.USER_SUB_MENU_INFO_KEY, userSubMenuVo);
        }

        return "common/decorator/PV_UserPreview";
    }
}
