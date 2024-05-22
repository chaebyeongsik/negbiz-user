/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.cms.preview.domain;

import zesinc.web.vo.BaseVO;

/**
 * 사용자메뉴컨텐츠 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-06-01.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class CmsPreviewVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -1823950632523172432L;

    /** 도메인코드 */
    private Integer siteSn;

    /** 도메인명 */
    private String siteNm;

    /** 사용자메뉴코드 */
    private String userMenuEngNm;

    /** 컨텐츠일련번호 */
    private Integer contsSn;

    /** 상단컨텐츠 */
    private String strtContsCn;

    /** 본문컨텐츠 */
    private String mainContsCn;

    /**
     * Integer siteSn을 반환
     * 
     * @return Integer siteSn
     */
    public Integer getSiteSn() {
        return siteSn;
    }

    /**
     * siteSn을 설정
     * 
     * @param siteSn 을(를) Integer siteSn로 설정
     */
    public void setSiteSn(Integer siteSn) {
        this.siteSn = siteSn;
    }

    /**
     * String siteNm을 반환
     * 
     * @return String siteNm
     */
    public String getSiteNm() {
        return siteNm;
    }

    /**
     * siteNm을 설정
     * 
     * @param siteNm 을(를) String siteNm로 설정
     */
    public void setSiteNm(String siteNm) {
        this.siteNm = siteNm;
    }

    /**
     * String userMenuEngNm을 반환
     * 
     * @return String userMenuEngNm
     */
    public String getUserMenuEngNm() {
        return userMenuEngNm;
    }

    /**
     * userMenuEngNm을 설정
     * 
     * @param userMenuEngNm 을(를) String userMenuEngNm로 설정
     */
    public void setUserMenuEngNm(String userMenuEngNm) {
        this.userMenuEngNm = userMenuEngNm;
    }

    /**
     * Integer contsSn을 반환
     * 
     * @return Integer contsSn
     */
    public Integer getContsSn() {
        return contsSn;
    }

    /**
     * contsSn을 설정
     * 
     * @param contsSn 을(를) Integer contsSn로 설정
     */
    public void setContsSn(Integer contsSn) {
        this.contsSn = contsSn;
    }

    /**
     * String strtContsCn을 반환
     * 
     * @return String strtContsCn
     */
    public String getStrtContsCn() {
        return strtContsCn;
    }

    /**
     * strtContsCn을 설정
     * 
     * @param strtContsCn 을(를) String strtContsCn로 설정
     */
    public void setStrtContsCn(String strtContsCn) {
        this.strtContsCn = strtContsCn;
    }

    /**
     * String mainContsCn을 반환
     * 
     * @return String mainContsCn
     */
    public String getMainContsCn() {
        return mainContsCn;
    }

    /**
     * mainContsCn을 설정
     * 
     * @param mainContsCn 을(를) String mainContsCn로 설정
     */
    public void setMainContsCn(String mainContsCn) {
        this.mainContsCn = mainContsCn;
    }

}
