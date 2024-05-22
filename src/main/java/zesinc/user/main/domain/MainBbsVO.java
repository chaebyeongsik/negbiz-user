/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.main.domain;

import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.web.vo.BaseVO;

/**
 * 메인 화면 게시물 데이터 VO
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
public class MainBbsVO extends BaseVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 553839548448599779L;

    private Integer bbsSn;
    /** 게시판명 */
    private String bbsNm;
    /** 게시글일련번호 */
    private String bbsDocNo;
    /** 분류코드 */
    private Integer clsfNo;
    /** 분류명 */
    private String clsfNm;
    /** 하위분류코드 */
    private Integer lwrkClsfSn;
    /** 하위분류코드명 */
    private String lwrkClsfNm;
    /** 제목 */
    private String ttl;
    /** 첨부파일 일련번호 */
    private Integer fileSn;
    /** 첨부파일 목록 */
    private List<FileVO> fileList;
    /** 조회수 */
    private Integer inqCnt;
    /** 부서코드 */
    private String deptCdId;
    /** 부서명 */
    private String deptNm;
    /** 등록자ID */
    private String rgtrId;
    /** 등록자명 */
    private String rgtrNm;
    /** 등록일시 */
    private String regDt;
    /** 등록일부터 경과일 */
    private Integer passDay;
    /** 확장컬럼1 */
    private String flctnColCn1;
    /** 확장컬럼2 */
    private String flctnColCn2;
    /** 확장컬럼3 */
    private String flctnColCn3;
    /** 확장컬럼4 */
    private String flctnColCn4;
    /** 확장컬럼5 */
    private String flctnColCn5;
    /** 확장컬럼6 */
    private String flctnColCn6;
    /** 확장컬럼7 */
    private String flctnColCn7;
    /** 확장컬럼8 */
    private String flctnColCn8;
    /** 확장컬럼9 */
    private String flctnColCn9;
    /** 확장컬럼10 */
    private String flctnColCn10;

    /**
     * Integer bbsSn을 반환
     * 
     * @return Integer bbsSn
     */
    public Integer getBbsSn() {
        return bbsSn;
    }

    /**
     * bbsSn을 설정
     * 
     * @param bbsSn 을(를) Integer bbsSn로 설정
     */
    public void setBbsSn(Integer bbsSn) {
        this.bbsSn = bbsSn;
    }

    /**
     * String bbsNm을 반환
     * 
     * @return String bbsNm
     */
    public String getBbsNm() {
        return bbsNm;
    }

    /**
     * bbsNm을 설정
     * 
     * @param bbsNm 을(를) String bbsNm로 설정
     */
    public void setBbsNm(String bbsNm) {
        this.bbsNm = bbsNm;
    }

    /**
     * String bbsDocNo을 반환
     * 
     * @return String bbsDocNo
     */
    public String getBbsDocNo() {
        return bbsDocNo;
    }

    /**
     * bbsDocNo을 설정
     * 
     * @param bbsDocNo 을(를) String bbsDocNo로 설정
     */
    public void setBbsDocNo(String bbsDocNo) {
        this.bbsDocNo = bbsDocNo;
    }

    /**
     * Integer clsfNo을 반환
     * 
     * @return Integer clsfNo
     */
    public Integer getClsfNo() {
        return clsfNo;
    }

    /**
     * clsfNo을 설정
     * 
     * @param clsfNo 을(를) Integer clsfNo로 설정
     */
    public void setClsfNo(Integer clsfNo) {
        this.clsfNo = clsfNo;
    }

    /**
     * String clsfNm을 반환
     * 
     * @return String clsfNm
     */
    public String getClsfNm() {
        return clsfNm;
    }

    /**
     * clsfNm을 설정
     * 
     * @param clsfNm 을(를) String clsfNm로 설정
     */
    public void setClsfNm(String clsfNm) {
        this.clsfNm = clsfNm;
    }

    /**
     * Integer lwrkClsfSn을 반환
     * 
     * @return Integer lwrkClsfSn
     */
    public Integer getLwrkClsfSn() {
        return lwrkClsfSn;
    }

    /**
     * lwrkClsfSn을 설정
     * 
     * @param lwrkClsfSn 을(를) Integer lwrkClsfSn로 설정
     */
    public void setLwrkClsfSn(Integer lwrkClsfSn) {
        this.lwrkClsfSn = lwrkClsfSn;
    }

    /**
     * String lwrkClsfNm을 반환
     * 
     * @return String lwrkClsfNm
     */
    public String getLwrkClsfNm() {
        return lwrkClsfNm;
    }

    /**
     * lwrkClsfNm을 설정
     * 
     * @param lwrkClsfNm 을(를) String lwrkClsfNm로 설정
     */
    public void setLwrkClsfNm(String lwrkClsfNm) {
        this.lwrkClsfNm = lwrkClsfNm;
    }

    /**
     * String ttl을 반환
     * 
     * @return String ttl
     */
    public String getTtl() {
        return ttl;
    }

    /**
     * ttl을 설정
     * 
     * @param ttl 을(를) String ttl로 설정
     */
    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    /**
     * Integer fileSn을 반환
     * 
     * @return Integer fileSn
     */
    public Integer getFileSn() {
        return fileSn;
    }

    /**
     * fileSn을 설정
     * 
     * @param fileSn 을(를) Integer fileSn로 설정
     */
    public void setFileSn(Integer fileSn) {
        this.fileSn = fileSn;
    }

    /**
     * List<FileVO> fileList을 반환
     * 
     * @return List<FileVO> fileList
     */
    public List<FileVO> getFileList() {
        return fileList;
    }

    /**
     * fileList을 설정
     * 
     * @param fileList 을(를) List<FileVO> fileList로 설정
     */
    public void setFileList(List<FileVO> fileList) {
        this.fileList = fileList;
    }

    /**
     * Integer inqCnt을 반환
     * 
     * @return Integer inqCnt
     */
    public Integer getInqCnt() {
        return inqCnt;
    }

    /**
     * inqCnt을 설정
     * 
     * @param inqCnt 을(를) Integer inqCnt로 설정
     */
    public void setInqCnt(Integer inqCnt) {
        this.inqCnt = inqCnt;
    }

    /**
     * String deptCdId을 반환
     * 
     * @return String deptCdId
     */
    public String getDeptCdId() {
        return deptCdId;
    }

    /**
     * deptCdId을 설정
     * 
     * @param deptCdId 을(를) String deptCdId로 설정
     */
    public void setDeptCdId(String deptCdId) {
        this.deptCdId = deptCdId;
    }

    /**
     * String deptNm을 반환
     * 
     * @return String deptNm
     */
    public String getDeptNm() {
        return deptNm;
    }

    /**
     * deptNm을 설정
     * 
     * @param deptNm 을(를) String deptNm로 설정
     */
    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
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
     * String regDt을 반환
     * 
     * @return String regDt
     */
    public String getRegDt() {
        return regDt;
    }

    /**
     * regDt을 설정
     * 
     * @param regDt 을(를) String regDt로 설정
     */
    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    /**
     * Integer passDay을 반환
     * 
     * @return Integer passDay
     */
    public Integer getPassDay() {
        return passDay;
    }

    /**
     * passDay을 설정
     * 
     * @param passDay 을(를) Integer passDay로 설정
     */
    public void setPassDay(Integer passDay) {
        this.passDay = passDay;
    }

    /**
     * String flctnColCn1을 반환
     * 
     * @return String flctnColCn1
     */
    public String getFlctnColCn1() {
        return flctnColCn1;
    }

    /**
     * flctnColCn1을 설정
     * 
     * @param flctnColCn1 을(를) String flctnColCn1로 설정
     */
    public void setFlctnColCn1(String flctnColCn1) {
        this.flctnColCn1 = flctnColCn1;
    }

    /**
     * String flctnColCn2을 반환
     * 
     * @return String flctnColCn2
     */
    public String getFlctnColCn2() {
        return flctnColCn2;
    }

    /**
     * flctnColCn2을 설정
     * 
     * @param flctnColCn2 을(를) String flctnColCn2로 설정
     */
    public void setFlctnColCn2(String flctnColCn2) {
        this.flctnColCn2 = flctnColCn2;
    }

    /**
     * String flctnColCn3을 반환
     * 
     * @return String flctnColCn3
     */
    public String getFlctnColCn3() {
        return flctnColCn3;
    }

    /**
     * flctnColCn3을 설정
     * 
     * @param flctnColCn3 을(를) String flctnColCn3로 설정
     */
    public void setFlctnColCn3(String flctnColCn3) {
        this.flctnColCn3 = flctnColCn3;
    }

    /**
     * String flctnColCn4을 반환
     * 
     * @return String flctnColCn4
     */
    public String getFlctnColCn4() {
        return flctnColCn4;
    }

    /**
     * flctnColCn4을 설정
     * 
     * @param flctnColCn4 을(를) String flctnColCn4로 설정
     */
    public void setFlctnColCn4(String flctnColCn4) {
        this.flctnColCn4 = flctnColCn4;
    }

    /**
     * String flctnColCn5을 반환
     * 
     * @return String flctnColCn5
     */
    public String getFlctnColCn5() {
        return flctnColCn5;
    }

    /**
     * flctnColCn5을 설정
     * 
     * @param flctnColCn5 을(를) String flctnColCn5로 설정
     */
    public void setFlctnColCn5(String flctnColCn5) {
        this.flctnColCn5 = flctnColCn5;
    }

    /**
     * String flctnColCn6을 반환
     * 
     * @return String flctnColCn6
     */
    public String getFlctnColCn6() {
        return flctnColCn6;
    }

    /**
     * flctnColCn6을 설정
     * 
     * @param flctnColCn6 을(를) String flctnColCn6로 설정
     */
    public void setFlctnColCn6(String flctnColCn6) {
        this.flctnColCn6 = flctnColCn6;
    }

    /**
     * String flctnColCn7을 반환
     * 
     * @return String flctnColCn7
     */
    public String getFlctnColCn7() {
        return flctnColCn7;
    }

    /**
     * flctnColCn7을 설정
     * 
     * @param flctnColCn7 을(를) String flctnColCn7로 설정
     */
    public void setFlctnColCn7(String flctnColCn7) {
        this.flctnColCn7 = flctnColCn7;
    }

    /**
     * String flctnColCn8을 반환
     * 
     * @return String flctnColCn8
     */
    public String getFlctnColCn8() {
        return flctnColCn8;
    }

    /**
     * flctnColCn8을 설정
     * 
     * @param flctnColCn8 을(를) String flctnColCn8로 설정
     */
    public void setFlctnColCn8(String flctnColCn8) {
        this.flctnColCn8 = flctnColCn8;
    }

    /**
     * String flctnColCn9을 반환
     * 
     * @return String flctnColCn9
     */
    public String getFlctnColCn9() {
        return flctnColCn9;
    }

    /**
     * flctnColCn9을 설정
     * 
     * @param flctnColCn9 을(를) String flctnColCn9로 설정
     */
    public void setFlctnColCn9(String flctnColCn9) {
        this.flctnColCn9 = flctnColCn9;
    }

    /**
     * String flctnColCn10을 반환
     * 
     * @return String flctnColCn10
     */
    public String getFlctnColCn10() {
        return flctnColCn10;
    }

    /**
     * flctnColCn10을 설정
     * 
     * @param flctnColCn10 을(를) String flctnColCn10로 설정
     */
    public void setFlctnColCn10(String flctnColCn10) {
        this.flctnColCn10 = flctnColCn10;
    }

}
