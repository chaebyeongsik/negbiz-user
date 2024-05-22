/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.main;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import zesinc.user.banner.BannerService;
import zesinc.user.banner.domain.BannerVO;
import zesinc.user.form.domain.FormInfoVO;
import zesinc.user.main.domain.MainBbsVO;
import zesinc.web.spring.controller.UserController;
import zesinc.web.vo.BaseVO;

/**
 * 메인컨트롤러
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 7. 24.    ZES-INC   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller
public class MainController extends UserController {

    @Resource(name = "opUserMainService")
    private MainService opUserMainService;

    @Resource(name = "opUserBannerService")
    private BannerService opUserBannerService;


    @RequestMapping(value = "/index.do")
    public void index(HttpServletRequest request, Model model) {
        BaseVO paramVo = new BaseVO();

        // 베너 공통 파라미터. 도메인코드
        paramVo.addParam("q_siteSn", "1");

        // 메인 팝업존
        paramVo.addParam("q_bnnTypeNm", "1020");
        List<BannerVO> popupZoneList = opUserBannerService.selectBannerList(paramVo);
        model.addAttribute("popupZoneList", popupZoneList);

        // 메인 베너
        paramVo.addParam("q_bnnTypeNm", "1010");
        List<BannerVO> bannerList = opUserBannerService.selectBannerList(paramVo);
        model.addAttribute("bannerList", bannerList);

        // 메인 퀵링크
        /*paramVo.addParam("q_bnnTypeNm", "1030");
        List<BannerVO> quickLinkList = opUserBannerService.selectBannerList(paramVo);
        model.addAttribute("quickLinkList", quickLinkList);*/

        // 메인 비쥬얼
        paramVo.addParam("q_bnnTypeNm", "1040");
        List<BannerVO> visaulList = opUserBannerService.selectBannerList(paramVo);
        model.addAttribute("visaulList", visaulList);

        

        // 게시판 공통 파라미터. 목록 추출 건수
        paramVo.addParam("q_pagingEndNum", 5);

        // 1001 공지사항 게시판
        paramVo.addParam("q_bbsSn", "1001");
        paramVo.addParam("q_clsfNo", "");
        paramVo.addParam("q_lwrkClsfSn", "");
        List<MainBbsVO> bbsList1001 = opUserMainService.selectMainBbsList(paramVo);
        model.addAttribute("bbsList1001", bbsList1001);

        // 1002 자료실 게시판
        paramVo.addParam("q_bbsSn", "1002");
        paramVo.addParam("q_clsfNo", "");
        paramVo.addParam("q_lwrkClsfSn", "");
        List<MainBbsVO> bbsList1002 = opUserMainService.selectMainBbsList(paramVo);
        model.addAttribute("bbsList1002", bbsList1002);

        // 1003 자유 게시판
        paramVo.addParam("q_bbsSn", "1003");
        paramVo.addParam("q_clsfNo", "");
        paramVo.addParam("q_lwrkClsfSn", "");
        List<MainBbsVO> bbsList1003 = opUserMainService.selectMainBbsList(paramVo);
        model.addAttribute("bbsList1003", bbsList1003);

        // 1004 포토 갤러리 게시판
        paramVo.addParam("q_bbsSn", "1004");
        paramVo.addParam("q_clsfNo", "");
        paramVo.addParam("q_lwrkClsfSn", "");
        List<MainBbsVO> bbsList1004 = opUserMainService.selectMainBbsList(paramVo);
        model.addAttribute("bbsList1004", bbsList1004);
        
        
        // 폼 신청
        List<FormInfoVO> formList = opUserMainService.selectMainFormList(paramVo);
        model.addAttribute("formList", formList);
        System.out.println("formList : "+formList);

    }

    @RequestMapping(value = "/ND_quickMenu.do")
    public void quickMenu(HttpServletRequest request, Model model) {
        BaseVO paramVo = new BaseVO();
        // 베너 공통 파라미터. 도메인코드
        paramVo.addParam("q_siteSn", "1");

        // 퀵
        paramVo.addParam("q_bnnTypeNm", "1030");
        List<BannerVO> quickLinkList = opUserBannerService.selectBannerList(paramVo);
        model.addAttribute("quickLinkList", quickLinkList);
    }
}
