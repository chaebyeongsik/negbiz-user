/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.join;

import java.util.List;

import zesinc.user.join.domain.UserInfoIemEstbsVO;
import zesinc.user.join.domain.UserJoinVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 사용자 가입 DAO 클래스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 7. 15.    ZES-INC   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Mapper("opUserJoinDao")
public interface UserJoinMapper {

    /**
     * 사용자 상세 조회(BY USER_IDNTF_NM)
     * 
     * @param userJoinVo
     * @return
     */
    UserJoinVO selectUserByUserKey(UserJoinVO userJoinVo);

    /**
     * 사용자 상세 조회(BY USER_ID)
     * 
     * @param userJoinVo
     * @return
     */
    UserJoinVO selectUserByUserId(UserJoinVO userJoinVo);

    /**
     * 사용자 아이디 중복 체크
     * 
     * @param userJoinVo
     * @return
     */
    Integer selectDupCheckUserId(UserJoinVO userJoinVo);

    /**
     * 사용자 등록 처리
     * 
     * @param userJoinVo
     * @return
     */
    Integer insertIndvdlUser(UserJoinVO userJoinVo);

    /**
     * 사용자 기타 정보 등록 처리
     * 
     * @param userJoinVo
     * @return
     */
    Integer insertIndvdlUserEtcInfo(UserJoinVO userJoinVo);

    /**
     * 개인 사용자 기타 정보 수정 처리
     * 
     * @param userJoinVo
     * @return
     */
    Integer updateIndvdlUserEtcInfo(UserJoinVO userJoinVo);

    /**
     * 개인 사용자 정보 조회
     * 
     * @param userJoinVo
     * @return
     */
    UserJoinVO selectIndvdlUserInfo(UserJoinVO userJoinVo);

    /**
     * 개인 사용자 정보 수정 처리
     * 
     * @param userJoinVo
     * @return
     */
    Integer updateIndvdlUser(UserJoinVO userJoinVo);

    /**
     * 기업 사용자 등록 처리
     * 
     * @param userJoinVo
     * @return
     */
    Integer insertEntrprsUser(UserJoinVO userJoinVo);

    /**
     * 기업사용자 기타정보 등록 처리
     * 
     * @param userJoinVo
     * @return
     */
    Integer insertEntrprsUserEtcInfo(UserJoinVO userJoinVo);

    /**
     * 기업사용자 정보 조회
     * 
     * @param userJoinVo
     * @return
     */
    UserJoinVO selectEntrprsUserInfo(UserJoinVO userJoinVo);

    /**
     * 기업사용자 기타정보 수정 처리
     * 
     * @param userJoinVo
     * @return
     */
    Integer updateEntrprsUserEtcInfo(UserJoinVO userJoinVo);

    /**
     * 기업사용자 정보 수정 처리
     * 
     * @param userJoinVo
     * @return
     */
    Integer updateEntrprsUser(UserJoinVO userJoinVo);

    /**
     * 회원가입 시 회원가입양식의 항목설정 조회 - 개인사용자
     * 
     * @return
     */
    UserInfoIemEstbsVO selectIndvdlUserInfoIemEstbsYn();

    /**
     * 회원가입 시 회원가입양식의 항목설정 조회 - 기업사용자
     * 
     * @return
     */
    UserInfoIemEstbsVO selectEntrprsUserInfoIemEstbsYn();

    /**
     * 사용자 등급 목록 조회
     * 
     * @param userJoinVo
     * @return
     */
    List<UserJoinVO> selectUserGradList(UserJoinVO userJoinVo);
}
