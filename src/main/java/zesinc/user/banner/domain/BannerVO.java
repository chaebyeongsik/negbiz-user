/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.banner.domain;

import zesinc.web.vo.BaseVO;

/**
 * 배너 정보 VO 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-07-19.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class BannerVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 4420213379805772935L;

    /** 순번 */
    private Integer regSn;

    /** 제목 */
    private String ttl;

    /** 시작일 */
    private String bgngYmd;

    /** 종료일 */
    private String endYmd;

    /** 사용여부 */
    private String useYn;

    /** 링크유형 */
    private String linkTypeNm;

    /** 배너유형 */
    private String bnnTypeNm;

    /** 링크URL */
    private String linkUrlAddr;

    /** 이미지대체텍스트 */
    private String imgAltrtvNm;

    /** 링크설명 */
    private String linkExpln;

    /** 파일경로 */
    private String filePathNm;

    /**
     * 순번 설정
     * 
     * @param regSn을(를) Integer regSn로 설정
     */
    public void setRegSn(Integer regSn) {
        this.regSn = regSn;
    }

    /**
     * 순번 반환
     * 
     * @return Integer regSn
     */
    public Integer getRegSn() {
        return regSn;
    }

    /**
     * 제목 설정
     * 
     * @param ttl을(를) String ttl로 설정
     */
    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    /**
     * 제목 반환
     * 
     * @return String ttl
     */
    public String getTtl() {
        return ttl;
    }

    /**
     * 시작일 설정
     * 
     * @param bgngYmd을(를) String bgngYmd로 설정
     */
    public void setBgngYmd(String bgngYmd) {
        this.bgngYmd = bgngYmd;
    }

    /**
     * 시작일 반환
     * 
     * @return String bgngYmd
     */
    public String getBgngYmd() {
        return bgngYmd;
    }

    /**
     * 종료일 설정
     * 
     * @param endYmd을(를) String endYmd로 설정
     */
    public void setEndYmd(String endYmd) {
        this.endYmd = endYmd;
    }

    /**
     * 종료일 반환
     * 
     * @return String endYmd
     */
    public String getEndYmd() {
        return endYmd;
    }

    /**
     * 사용여부 설정
     * 
     * @param useYn을(를) String useYn로 설정
     */
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    /**
     * 사용여부 반환
     * 
     * @return String useYn
     */
    public String getUseYn() {
        return useYn;
    }

    /**
     * 링크유형 설정
     * 
     * @param linkTypeNm을(를) String linkTypeNm로 설정
     */
    public void setLinkTypeNm(String linkTypeNm) {
        this.linkTypeNm = linkTypeNm;
    }

    /**
     * 링크유형 반환
     * 
     * @return String linkTypeNm
     */
    public String getLinkTypeNm() {
        return linkTypeNm;
    }

    /**
     * 배너유형 설정
     * 
     * @param bnnTypeNm을(를) String bnnTypeNm로 설정
     */
    public void setBnnTypeNm(String bnnTypeNm) {
        this.bnnTypeNm = bnnTypeNm;
    }

    /**
     * 배너유형 반환
     * 
     * @return String bnnTypeNm
     */
    public String getBnnTypeNm() {
        return bnnTypeNm;
    }

    /**
     * 링크URL 설정
     * 
     * @param linkUrlAddr을(를) String linkUrlAddr로 설정
     */
    public void setLinkUrlAddr(String linkUrlAddr) {
        this.linkUrlAddr = linkUrlAddr;
    }

    /**
     * 링크URL 반환
     * 
     * @return String linkUrlAddr
     */
    public String getLinkUrlAddr() {
        return linkUrlAddr;
    }

    /**
     * 이미지대체텍스트 설정
     * 
     * @param imgAltrtvNm을(를) String imgAltrtvNm로 설정
     */
    public void setImgAltrtvNm(String imgAltrtvNm) {
        this.imgAltrtvNm = imgAltrtvNm;
    }

    /**
     * 이미지대체텍스트 반환
     * 
     * @return String imgAltrtvNm
     */
    public String getImgAltrtvNm() {
        return imgAltrtvNm;
    }

    /**
     * 링크설명 설정
     * 
     * @param linkExpln을(를) String linkExpln로 설정
     */
    public void setLinkExpln(String linkExpln) {
        this.linkExpln = linkExpln;
    }

    /**
     * 링크설명 반환
     * 
     * @return String linkExpln
     */
    public String getLinkExpln() {
        return linkExpln;
    }

    /**
     * 파일경로 설정
     * 
     * @param filePathNm을(를) String filePathNm로 설정
     */
    public void setFilePathNm(String filePathNm) {
        this.filePathNm = filePathNm;
    }

    /**
     * 파일경로 반환
     * 
     * @return String filePathNm
     */
    public String getFilePathNm() {
        return filePathNm;
    }

}
