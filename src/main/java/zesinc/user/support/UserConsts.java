/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.support;

import zesinc.core.config.Config;

/**
 * 사용자 정보 관련 상수값
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 7. 6.    ZES-INC   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class UserConsts {

    /** 사용자 메인 URL */
    public static final String USER_MAIN_URL = Config.getString("webapp-config.urls.userMain", "/index.do");
    /** 사용자 비밀번호 갱신 폼 URL */
    public static final String USER_RENEW_PSWD_URL = Config.getString("user-config.urls.userRenewPassword", "/user/join/BD_updatePasswordForm.do");
    /** 사용자 리턴 URL */
    public static final String USER_RETURN_URL = "__UserReturnUrl";
}
