/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.schdul;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import zesinc.core.lang.Validate;
import zesinc.user.schdul.domain.SchdulVO;
import zesinc.web.auth.AuthType;
import zesinc.web.auth.anotation.OpenworksAuth;
import zesinc.web.spring.controller.IntraController;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.ReptitDateUtil;

/**
 * 일정 정보 컨트롤러 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-12-08.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("일정 관리")
@RequestMapping(value = { "/**/user/schdul" })
public class SchdulController extends IntraController {

    /** jsp 파일경로 */
    private static final String VIEW_PATH = "user/schdul/";

    @Resource(name = "opSchdulService")
    private SchdulService opSchdulService;

    /**
     * 일정 상세
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "PD_selectSchdul.do")
    public String selectSchdul(HttpServletRequest request, Model model, SchdulVO schdulVo) {

        model.addAttribute(BaseConfig.KEY_DATA_VO, opSchdulService.selectSchdul(schdulVo));

        return VIEW_PATH + "PD_selectSchdul";
    }

    /**
     * 일정 페이지 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectSchdulList.do")
    public String selectSchdulList(HttpServletRequest request, Model model, SchdulVO schdulVo) {
        
        String q_startDt = schdulVo.getString("q_startDt");
        String q_endDt = schdulVo.getString("q_endDt");
        if(Validate.isEmpty(q_startDt) && Validate.isEmpty(q_endDt) ) {
            Calendar cal = Calendar.getInstance();
            String date = ReptitDateUtil.getToday();
            
            q_startDt = ReptitDateUtil.getMinMonthDate(date, cal);
            q_endDt = ReptitDateUtil.getMaxMonthDate(date, cal);
            
            schdulVo.addParam("q_startDt", q_startDt);
            schdulVo.addParam("q_endDt", q_endDt);
        }
        

        model.addAttribute(BaseConfig.KEY_PAGER, opSchdulService.selectSchdulPageList(schdulVo));

        return VIEW_PATH + "BD_selectSchdulList";
    }

    /**
     * 일정 달력 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "BD_selectSchdulCal.do")
    public String selectSchdulCal(HttpServletRequest request, Model model, SchdulVO schdulVo) {

        String schdulDate = "";
        String year = schdulVo.getString("q_year");
        String month = schdulVo.getString("q_month");

        if(Validate.isEmpty(year) || Validate.isEmpty(month)) {
            Calendar cal = Calendar.getInstance();
            String date = ReptitDateUtil.getToday();
            year = ReptitDateUtil.getYear(date, cal);
            month = ReptitDateUtil.getMonth(date, cal);
            schdulVo.addParam("q_year", year);
            schdulVo.addParam("q_month", month);
        }

        schdulDate = year + "-" + month + "-01";
        schdulVo.addParam("q_schdulDate", schdulDate);

        model.addAttribute("yearList", ReptitDateUtil.getRangeYearList(schdulDate, 5, 5));
        model.addAttribute("monthList", ReptitDateUtil.getRangeMonthList());
        model.addAttribute(BaseConfig.KEY_DATA_LIST, opSchdulService.selectSchdulCalList(schdulVo));

        return VIEW_PATH + "BD_selectSchdulCal";
    }

    /**
     * 일정관리상세 목록
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectSchdulDetailList.do")
    public String selectSchdulDetailList(HttpServletRequest request, Model model, SchdulVO schdulVo) {

        // model.addAttribute(BaseConfig.KEY_DATA_LIST,
        // opSchdulService.selectSchdulDetailList(schdulVo));

        return responseJson(model, opSchdulService.selectSchdulDetailList(schdulVo));
    }

}
