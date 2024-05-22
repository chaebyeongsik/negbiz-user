/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.schdul.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.component.file.FileService;
import zesinc.core.lang.Validate;
import zesinc.user.schdul.SchdulMapper;
import zesinc.user.schdul.SchdulService;
import zesinc.user.schdul.domain.CalendarSchdulVO;
import zesinc.user.schdul.domain.SchdulVO;
import zesinc.user.schdul.support.SchdulSupport;
import zesinc.web.support.pager.Pager;
import zesinc.web.utils.ReptitDateUtil;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 일정 정보 서비스 구현 클레스
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

@Service("opSchdulService")
public class SchdulServiceImpl extends EgovAbstractServiceImpl implements SchdulService {

    @Resource(name = "opSchdulDao")
    private SchdulMapper opSchdulDao;
    // 첨부파일
    @Resource(name = "opFileService")
    private FileService opFileService;

    @Override
    public SchdulVO selectSchdul(SchdulVO schdulVo) {

        SchdulVO dataVo = opSchdulDao.selectSchdul(schdulVo);
        dataVo.setBgngHr(SchdulSupport.getFormatTime(dataVo.getBgngHr()));
        dataVo.setEndHr(SchdulSupport.getFormatTime(dataVo.getEndHr()));

        // 첨부파일
        if(Validate.isNotEmpty(dataVo.getFileSn())) {
            dataVo.setFileList(opFileService.selectFileList(dataVo.getFileSn()));
        }

        return dataVo;
    }

    @Override
    public Pager<SchdulVO> selectSchdulPageList(SchdulVO schdulVo) {
        // 사용자단의 경우 공개인 것만
        schdulVo.addParam("q_rlsYn", "Y");
        List<SchdulVO> dataList = opSchdulDao.selectSchdulList(schdulVo);
        Integer totalNum = opSchdulDao.selectSchdulListCount(schdulVo);

        for(SchdulVO dataVo : dataList) {
            dataVo.setBgngHr(SchdulSupport.getFormatTime(dataVo.getBgngHr()));
            dataVo.setEndHr(SchdulSupport.getFormatTime(dataVo.getEndHr()));
        }

        return new Pager<SchdulVO>(dataList, schdulVo, totalNum);
    }

    @Override
    public List<List<CalendarSchdulVO>> selectSchdulCalList(SchdulVO schdulVo) {

        String schdulDate = schdulVo.getString("q_schdulDate");
        // Calendar 객체에서 해당 월을 일자를 모두 구한다.
        Calendar cal = Calendar.getInstance();
        String firstMonthDay = ReptitDateUtil.getMinMonthDate(schdulDate, cal);
        String lastMonthDay = ReptitDateUtil.getMaxMonthDate(schdulDate, cal);
        String minWeekDay = ReptitDateUtil.getMinWeekDate(firstMonthDay, cal);
        String maxWeekDay = ReptitDateUtil.getMaxWeekDate(lastMonthDay, cal);

        List<String> calendarDays = ReptitDateUtil.getRangeDateList(minWeekDay, maxWeekDay);

        // 사용자단의 경우 공개인 것만
        schdulVo.addParam("q_rlsYn", "Y");
        schdulVo.addParam("q_bgngYmd", firstMonthDay);
        schdulVo.addParam("q_endYmd", lastMonthDay);
        List<SchdulVO> schdulDetailList = opSchdulDao.selectSchdulDetailList(schdulVo);

        // 전체 목록
        List<List<CalendarSchdulVO>> calList = new ArrayList<List<CalendarSchdulVO>>();
        // 주간 목록
        List<CalendarSchdulVO> weekList = new ArrayList<CalendarSchdulVO>();
        // 일일 일정 목록
        List<SchdulVO> dayList = null;
        // 날짜와 해당일 일정목록 정보 VO
        CalendarSchdulVO csVo = null;

        int dayCnt = calendarDays.size();
        for(int i = 0 ; i < dayCnt ; i++) {
            String calendarDay = calendarDays.get(i);

            // 일요일 확인자
            boolean isSunday = ((i % 7) == 0);
            if(isSunday) {
                // 한주 목록을 전체 목록에 추가 하고, 한주 목록을 새로 시작한다.
                calList.add(weekList);
                weekList = new ArrayList<CalendarSchdulVO>();
            }

            /*
             * 현재 날짜의 일정만 담는다.
             * 성능이유로 쿼리를 매일 날리는 것 보다, 한번에 가져와 비교하며 담는다.
             */
            dayList = new ArrayList<SchdulVO>();
            for(SchdulVO detailVo : schdulDetailList) {
                if(detailVo.getBgngYmd().equals(calendarDay)) {
                    dayList.add(detailVo);
                }
            }

            Boolean isHoliday = (ReptitDateUtil.getDayWeek(calendarDay, cal) == 1 ? Boolean.TRUE : Boolean.FALSE);
            Boolean isSaturDay = (ReptitDateUtil.getDayWeek(calendarDay, cal) == 7 ? Boolean.TRUE : Boolean.FALSE);
            String[] arrDate = calendarDay.split("-");

            csVo = new CalendarSchdulVO();
            csVo.setYear(arrDate[0]);
            csVo.setMonth(arrDate[1]);
            csVo.setDay(arrDate[2]);
            csVo.setIsHoliday(isHoliday);
            csVo.setIsSaturDay(isSaturDay);

            csVo.setSchdulList(dayList);

            weekList.add(csVo);
        }
        calList.add(weekList);

        return calList;
    }

    @Override
    public List<SchdulVO> selectSchdulDetailList(SchdulVO schdulVo) {

        return opSchdulDao.selectSchdulDetailList(schdulVo);
    }

}
