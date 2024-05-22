/*
 * Copyright (c) 2012 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.cms.support;

import zesinc.core.config.Config;

/**
 * 사용자메뉴 상수 객체
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 6. 23.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class CmsSupport {

    /**
     * 상속 불가 및 외부에서 생성 불가.
     */
    private CmsSupport() {
    }

    // -------------------------------------------------------------------------------------------------------------
    // 최상위 DUMMY 메뉴 생성 정보
    // -------------------------------------------------------------------------------------------------------------

    /** 최상위 기본 메뉴코드 */
    public static String HIGH_CMS_CD = Config.getString("webapp-config.defaultCode.highCmsCd", "web");
    /** dummy 메뉴명 */
    public static String DUMMY_MENU_NM = Config.getString("cms-config.dummyRoot.menuNm", "dummy");
    /** dummy 탑 메뉴 코드 */
    public static String DUMMY_TOP_MENU = Config.getString("cms-config.dummyRoot.hghrkMenuEngNm", "ROOT");
    /** dummy 부모 메뉴 코드 */
    public static String DUMMY_RARNT_MENU = Config.getString("cms-config.dummyRoot.parentMenuCode", "DOMAIN");
    /** dummy 메뉴 뎁스 */
    public static Integer DUMMY_MENU_DP = Config.getInt("cms-config.dummyRoot.menuDepth", -1);
    /** dummy 정렬순번 */
    public static Integer DUMMY_SORT_RODR = Config.getInt("cms-config.dummyRoot.sortOrder", -1);

    // -------------------------------------------------------------------------------------------------------------
    // 파일 생성 기본 경로
    // -------------------------------------------------------------------------------------------------------------
    /** 데코레이터 파일 위치 */
    public static String DECORATOR_FILE_ROOT = Config.getString("cms-config.basePath.decoratorFileRoot", "WEB-INF/jsp/common/decorator");
    /** 메뉴데이터 파일 위치 */
    public static String MENU_FILE_ROOT = Config.getString("cms-config.basePath.menuDataRoot", "/webcontent/menu/data");
    /** 일반리소스 파일 위치 */
    public static String RESOURCE_FILE_ROOT = Config.getString("cms-config.basePath.resourceFileRoot", "/webcontent/menu/resources");

    // -------------------------------------------------------------------------------------------------------------
    // 기타 정보
    // -------------------------------------------------------------------------------------------------------------
    /** CCL URL 접두어 */
    public static String COPY_URL_PREFIX = Config.getString("webapp-config.ccl.url-prefix");
    /** CCL URL 접미어 */
    public static String COPY_URL_POSTFIX = Config.getString("webapp-config.ccl.url-postfix");

    // -------------------------------------------------------------------------------------------------------------
    // 키값 정보
    // -------------------------------------------------------------------------------------------------------------
    public static final String DECORATOR_MENU = "menudecorator";
    public static final String DECORATOR_KEY = "decorator";
    public static final String DECORATOR_NAME = "web-decorator";

    /** 사용자 이용권한이 없는 경우 이동 페이지 */
    public static final String NO_AUTH_PAGE = "NoAuth";
    /** 사용자 이용권한 명칭 */
    public static final String USER_GRADE_INFO = "userGradeInfo";
    public static final String NO_SESSION = "회원전용 서비스입니다.";

}
