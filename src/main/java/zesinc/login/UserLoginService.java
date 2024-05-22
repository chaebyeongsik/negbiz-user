/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.login;

import zesinc.login.domain.UserLoginVO;

/**
 * 사용자 로그인 Service 인터페이스
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
public interface UserLoginService {

    /**
     * ID, PWD 체크
     * @param userLoginVo
     * @return
     * @throws Exception
     */
    UserLoginVO processUserIdAndPwdCheck(UserLoginVO userLoginVo) throws Exception;

    /**
     * 사용자 접속 시도 로그 등록
     * @param userLoginVo
     */
    void insertLoginTryLog(UserLoginVO userLoginVo);

    /**
     * 사용자에 대한 로그 프로세스
     * @param userLoginVo
     */
    void processLogOfUser(UserLoginVO userLoginVo);

}
