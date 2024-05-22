/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.cms.evl.domain;

import java.util.Date;

import zesinc.web.vo.PageVO;

/**
 * 사용자메뉴평가 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-06-09.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class CmsEvlVO extends PageVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -8746472577377026753L;

    /** 도메인코드 */
    private Integer siteSn;
    /** 사용자메뉴코드 */
    private String userMenuEngNm;
    /** 항목번호 */
    private Integer artclSn;
    /** 등록일자 */
    private String regYmd;
    /** 순번 */
    private Integer regSn;
    /** 의견글내용 */
    private String opnnCn;
    /** 등록IP */
    private String rgtrIpAddr;
    /** 등록자명 */
    private String rgtrNm;
    /** 등록자ID */
    private String rgtrId;
    /** 등록일시 */
    private Date regDt;

    /**
     * 도메인코드 설정
     * 
     * @param siteSn을(를) Integer siteSn로 설정
     */
    public void setSiteSn(Integer siteSn) {
        this.siteSn = siteSn;
    }

    /**
     * 도메인코드 반환
     * 
     * @return Integer siteSn
     */
    public Integer getSiteSn() {
        return siteSn;
    }

    /**
     * 사용자메뉴코드 설정
     * 
     * @param userMenuEngNm을(를) String userMenuEngNm로 설정
     */
    public void setUserMenuEngNm(String userMenuEngNm) {
        this.userMenuEngNm = userMenuEngNm;
    }

    /**
     * 사용자메뉴코드 반환
     * 
     * @return String userMenuEngNm
     */
    public String getUserMenuEngNm() {
        return userMenuEngNm;
    }

    /**
     * 항목번호 설정
     * 
     * @param artclSn을(를) Integer artclSn로 설정
     */
    public void setArtclSn(Integer artclSn) {
        this.artclSn = artclSn;
    }

    /**
     * 항목번호 반환
     * 
     * @return Integer artclSn
     */
    public Integer getArtclSn() {
        return artclSn;
    }

    /**
     * 등록일자 설정
     * 
     * @param regYmd을(를) String regYmd로 설정
     */
    public void setRegYmd(String regYmd) {
        this.regYmd = regYmd;
    }

    /**
     * 등록일자 반환
     * 
     * @return String regYmd
     */
    public String getRegYmd() {
        return regYmd;
    }

    /**
     * Integer regSn을 반환
     * 
     * @return Integer regSn
     */
    public Integer getRegSn() {
        return regSn;
    }

    /**
     * regSn을 설정
     * 
     * @param regSn 을(를) Integer regSn로 설정
     */
    public void setRegSn(Integer regSn) {
        this.regSn = regSn;
    }

    /**
     * String opnnCn을 반환
     * 
     * @return String opnnCn
     */
    public String getOpnnCn() {
        return opnnCn;
    }

    /**
     * opnnCn을 설정
     * 
     * @param opnnCn 을(를) String opnnCn로 설정
     */
    public void setOpnnCn(String opnnCn) {
        this.opnnCn = opnnCn;
    }

    /**
     * String rgtrIpAddr을 반환
     * 
     * @return String rgtrIpAddr
     */
    public String getRgtrIpAddr() {
        return rgtrIpAddr;
    }

    /**
     * rgtrIpAddr을 설정
     * 
     * @param rgtrIpAddr 을(를) String rgtrIpAddr로 설정
     */
    public void setRgtrIpAddr(String rgtrIpAddr) {
        this.rgtrIpAddr = rgtrIpAddr;
    }

    /**
     * String rgtrNm을 반환
     * 
     * @return String rgtrNm
     */
    public String getRgtrNm() {
        return rgtrNm;
    }

    /**
     * rgtrNm을 설정
     * 
     * @param rgtrNm 을(를) String rgtrNm로 설정
     */
    public void setRgtrNm(String rgtrNm) {
        this.rgtrNm = rgtrNm;
    }

    /**
     * String rgtrId을 반환
     * 
     * @return String rgtrId
     */
    public String getRgtrId() {
        return rgtrId;
    }

    /**
     * rgtrId을 설정
     * 
     * @param rgtrId 을(를) String rgtrId로 설정
     */
    public void setRgtrId(String rgtrId) {
        this.rgtrId = rgtrId;
    }

    /**
     * Date regDt을 반환
     * 
     * @return Date regDt
     */
    public Date getRegDt() {
        return regDt;
    }

    /**
     * regDt을 설정
     * 
     * @param regDt 을(를) Date regDt로 설정
     */
    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }

}
