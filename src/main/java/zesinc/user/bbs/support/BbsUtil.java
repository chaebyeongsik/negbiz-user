/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.bbs.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import zesinc.core.config.Config;
import zesinc.core.lang.Validate;
import zesinc.user.bbs.domain.BbsVO;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.CookieUtil;
import zesinc.web.vo.cache.BbsConfigCacheVO;

/**
 * 게시판 지원 유틸 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 10. 27.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class BbsUtil {

    public static String COPY_URL_PREFIX = Config.getString("webapp-config.ccl.url-prefix");
    public static String COPY_URL_POSTFIX = Config.getString("webapp-config.ccl.url-postfix");

    /**
     * 반복적인 설정 통합
     * 
     * @param model
     * @param bbsConfigVo
     * @param bbsVo
     */
    public static void setBbsConfigToRequest(Model model, BbsConfigCacheVO bbsConfigVo, BbsVO bbsVo) {

        model.addAttribute(BaseConfig.BBS_CONFIG_VO_KEY, bbsConfigVo);
    }

    /**
     * 게시판의 조회수 증가 체크를 위한 쿠키 체크
     */
    public static boolean isIncreateReadCnt(String cookieKey, int readCookieHour, HttpServletRequest request, HttpServletResponse response) {
        try {
            String cookieVal = CookieUtil.getCookie(request, cookieKey);
            if(!"Y".equals(cookieVal)) {
                CookieUtil.setCookie(response, cookieKey, "Y", readCookieHour * 60);
                return true;
            }
        } catch (Exception e) {}

        return false;
    }

    /**
     * 사용자단 CCL 정보 생성
     * 
     * @param bbsVo
     * @return
     */
    public static String makeCprgtTypeNm(BbsVO bbsVo) {

        StringBuilder sb = new StringBuilder();
        if(Validate.isNotEmpty(bbsVo.getAuthrIndict())) {
            sb.append(bbsVo.getAuthrIndict());
        }
        sb.append("-");
        if(Validate.isNotEmpty(bbsVo.getPrftmkPurpsPerm())) {
            sb.append(bbsVo.getPrftmkPurpsPerm());
        }
        sb.append("-");
        if(Validate.isNotEmpty(bbsVo.getCntntsChangePerm())) {
            sb.append(bbsVo.getCntntsChangePerm());
        }

        return sb.toString();
    }

    /**
     * 사용자단 CCL 내용 생성
     * 
     * @param bbsVo
     * @return
     */
    public static String makeCprgtCn(BbsVO bbsVo) {
        StringBuilder sb = new StringBuilder();
        sb.append(COPY_URL_PREFIX);
        if(Validate.isNotEmpty(bbsVo.getAuthrIndict())) {
            sb.append(bbsVo.getAuthrIndict());
            sb.append("-");
        }
        if(Validate.isNotEmpty(bbsVo.getPrftmkPurpsPerm())) {
            sb.append(bbsVo.getPrftmkPurpsPerm());
            sb.append("-");
        }
        if(Validate.isNotEmpty(bbsVo.getCntntsChangePerm())) {
            sb.append(bbsVo.getCntntsChangePerm());
            sb.append("-");
        }
        sb.deleteCharAt(sb.lastIndexOf("-"));
        sb.append(COPY_URL_POSTFIX);

        return sb.toString();
    }
}
