/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.main;

import java.util.List;

import zesinc.user.form.domain.FormInfoVO;
import zesinc.user.main.domain.MainBbsVO;
import zesinc.web.vo.BaseVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 메인 홈페이지 표시 데이터용 Dao
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016. 4. 7.    yesno   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opUserMainDao")
public interface MainMapper {

    /**
     * 게시글 목록. 파라미터 조정으로 게시판별로 반환
     * 
     * @param baseVo
     * @return
     */
    List<MainBbsVO> selectMainBbsList(BaseVO baseVo);

	List<FormInfoVO> selectMainFormList(BaseVO baseVo);

}
