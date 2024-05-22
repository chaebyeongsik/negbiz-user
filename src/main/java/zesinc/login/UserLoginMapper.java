/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.login;

import zesinc.login.domain.UserLoginVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 사용자 로그인 DAO 클래스
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
@Mapper("opUserLoginDao")
public interface UserLoginMapper {

    /**
     * 사용자 정보 조회
     * 
     * @param userLoginVo
     * @return
     */
    UserLoginVO selectUserInfoByUserKey(UserLoginVO userLoginVo);

    /**
     * 사업자 기타 정보 조회
     * 
     * @param userLoginVo
     * @return
     */
    UserLoginVO selectEntrprsEtcInfo(UserLoginVO userLoginVo);

    /**
     * 개인 기타 정보 조회
     * 
     * @param userLoginVo
     * @return
     */
    UserLoginVO selectIndvdlEtcInfo(UserLoginVO userLoginVo);

    /**
     * 사용자 정보 조회
     * 
     * @param userLoginVo
     * @return
     */
    UserLoginVO selectUserInfo(UserLoginVO userLoginVo);

    /**
     * ID 조회
     * 
     * @param userLoginVo
     * @return
     */
    UserLoginVO selectId(UserLoginVO userLoginVo);

    /**
     * 사용자 접속 시도 로그 등록
     * 
     * @param userLoginVo
     * @return
     */
    int insertLoginTryLog(UserLoginVO userLoginVo);

    /**
     * 사용자정보 중 로그인건수 및 최근접속일자 갱신
     * 
     * @param userLoginVo
     */
    void updateUserLoginInfo(UserLoginVO userLoginVo);

    /**
     * 해당 사용자 오늘 접속 이력 건수 조회
     * 
     * @param userLoginVo
     * @return
     */
    int selectTodayLoginCo(UserLoginVO userLoginVo);

    /**
     * 사용자 전체 카운트 조회
     * 
     * @param userLoginVo
     * @return
     */
    int selectUserAllCount(UserLoginVO userLoginVo);

    /**
     * 사용자 접속 통계 등록
     * 
     * @param userLoginVo
     * @return
     */
    int insertLogStatsCount(UserLoginVO userLoginVo);

}
