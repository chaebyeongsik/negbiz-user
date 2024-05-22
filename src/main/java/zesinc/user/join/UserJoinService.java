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

/**
 * 사용자 가입 Service 인터페이스
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
public interface UserJoinService {

    /**
     * 사용자 아이디 중복 체크
     *
     * @param userJoinVo
     * @return
     */
    Integer selectDupCheckUserId(UserJoinVO userJoinVo);

    /**
     * 개인 사용자 등록 처리
     *
     * @param userJoinVo
     * @return
     */
    Integer insertIndvdlUser(UserJoinVO userJoinVo);

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
     * 기업사용자 등록 처리
     *
     * @param userJoinVo
     * @return
     */
    Integer insertEntrprsUser(UserJoinVO userJoinVo);

    /**
     * 기업사용자 수정 폼
     *
     * @param userJoinVo
     * @return
     */
    UserJoinVO selectEntrprsUserInfo(UserJoinVO userJoinVo);

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
     * 사용자 등급 목록 조회(사용중인것만)
     * @return
     */
    List<UserJoinVO> selectUserGradInUseList();

    /**
     * 입력받은 비밀번호와 세션에 담긴 비밀번호 일치여부 확인
     * @param userJoinVo
     * @return
     * @throws Exception
     */
    boolean processCheckPassword(UserJoinVO userJoinVo) throws Exception;
}
