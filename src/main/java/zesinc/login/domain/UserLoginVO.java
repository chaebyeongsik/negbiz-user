/*
 * Copyright (c) 2012 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.login.domain;

import java.util.Date;

import zesinc.web.validate.annotation.marker.Digits;
import zesinc.web.validate.annotation.marker.MaxLength;
import zesinc.web.vo.BaseVO;
import zesinc.web.vo.IUserSessVO;

/**
 * 사용자 로그인 정보 VO 객체
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2012. 4. 30.    방기배
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public class UserLoginVO extends BaseVO implements IUserSessVO {

    /** serialVersionUID */
    private static final long serialVersionUID = -1251326348198211677L;

    /** 사용자 비밀번호 */
    private String loginUserPassword;

    /** 사용자 아이디 */
    private String loginUserId;

    /** 사용자 비밀번호 */
    private String userPswd;

    /** 사용자 아이디 */
    private String userId;

    /** 사용자 고유키 */
    private String userIdntfNm;

    /** 사용자명 */
    private String userNm;

    /** 이메일 */
    private String email;

    /** 이메일1 */
    private String emlId;

    /** 이메일2 */
    private String emlSiteNm;

    /** 가입유형 (공인인증, I-PIN, 주민등록) */
    private int joinTypeSn;

    /** 전화번호1 */
    @MaxLength(max = 4)
    @Digits
    private String rgnTelno;

    /** 전화번호2 */
    @MaxLength(max = 4)
    @Digits
    private String telofcTelno;

    /** 전화번호3 */
    @MaxLength(max = 4)
    @Digits
    private String indivTelno;
    /** 휴대전화번호1 */
    @MaxLength(max = 4)
    @Digits
    private String mblRgnTelno;

    /** 휴대전화번호2 */
    @MaxLength(max = 4)
    @Digits
    private String mblTelofcTelno;

    /** 휴대전화번호3 */
    @MaxLength(max = 4)
    @Digits
    private String mblIndivTelno;

    /** 우편번호 */
    @MaxLength(max = 5)
    @Digits
    private String zip;

    /** 기본주소 */
    @MaxLength(max = 200)
    private String userAddr;

    /** 상세주소 */
    @MaxLength(max = 200)
    private String daddr;

    /** 등록일시 */
    private Date regDt;

    /** 수정일시 */
    private Date updtDt;

    /** 최근접속일시 */
    private Date lastCntnDt;

    /** 관심항목명 */
    private String itrstArtclCn;

    /** 로그인결과 */
    private String lgnRsltNo;

    /** 사용자유형 */
    private String userTypeNm;

    /** 등록자ID */
    @MaxLength(max = 20)
    private String rgtrId;

    /** 수정자ID */
    @MaxLength(max = 20)
    private String mdfrId;

    /** 회원등급코드목록 */
    private String userGrdCdDsctn;

    /** 회원상태 (1001:정상, 1002:탈퇴) */
    private String userSttsSn;

    /** 비밀번호 변경일 */
    private String pswdChgDt;

    /** 비밀번호 변경일수 */
    private Integer pswdChgDtCo;

    /** 나이 */
    private Integer age;

    /** 연령 */
    private String ageType;

    /** 성별 */
    private String gndrClsfSn;

    /** 성별 */
    private Integer wholMbrCnt;

    /** 유효한 ID 여부 */
    private boolean isValidId = false;

    /** 유효한 비밀번호 여부 */
    private boolean isValidPwd = false;

    /** 리턴 URL */
    private String returnUrl;

    /**
     * String loginUserPassword을 반환
     * 
     * @return String loginUserPassword
     */
    public String getLoginUserPassword() {
        return loginUserPassword;
    }

    /**
     * loginUserPassword을 설정
     * 
     * @param loginUserPassword 을(를) String loginUserPassword로 설정
     */
    public void setLoginUserPassword(String loginUserPassword) {
        this.loginUserPassword = loginUserPassword;
    }

    /**
     * String loginUserId을 반환
     * 
     * @return String loginUserId
     */
    public String getLoginUserId() {
        return loginUserId;
    }

    /**
     * loginUserId을 설정
     * 
     * @param loginUserId 을(를) String loginUserId로 설정
     */
    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }

    /**
     * String userPswd을 반환
     * 
     * @return String userPswd
     */
    public String getUserPswd() {
        return userPswd;
    }

    /**
     * userPswd을 설정
     * 
     * @param userPswd 을(를) String userPswd로 설정
     */
    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }

    /*
     * (non-Javadoc)
     * @see zesinc.login.domain.IUserSessVO#getUserId()
     */
    @Override
    public String getUserId() {
        return userId;
    }

    /**
     * userId을 설정
     * 
     * @param userId 을(를) String userId로 설정
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * String userIdntfNm을 반환
     * 
     * @return String userIdntfNm
     */
    public String getUserIdntfNm() {
        return userIdntfNm;
    }

    /**
     * userIdntfNm을 설정
     * 
     * @param userIdntfNm 을(를) String userIdntfNm로 설정
     */
    public void setUserIdntfNm(String userIdntfNm) {
        this.userIdntfNm = userIdntfNm;
    }

    /*
     * (non-Javadoc)
     * @see zesinc.login.domain.IUserSessVO#getUserNm()
     */
    @Override
    public String getUserNm() {
        return userNm;
    }

    /**
     * userNm을 설정
     * 
     * @param userNm 을(를) String userNm로 설정
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    /*
     * (non-Javadoc)
     * @see zesinc.login.domain.IUserSessVO#getEmail()
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * email을 설정
     * 
     * @param email 을(를) String email로 설정
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * String emlId을 반환
     * 
     * @return String emlId
     */
    public String getEmlId() {
        return emlId;
    }

    /**
     * emlId을 설정
     * 
     * @param emlId 을(를) String emlId로 설정
     */
    public void setEmlId(String emlId) {
        this.emlId = emlId;
    }

    /**
     * String emlSiteNm을 반환
     * 
     * @return String emlSiteNm
     */
    public String getEmlSiteNm() {
        return emlSiteNm;
    }

    /**
     * emlSiteNm을 설정
     * 
     * @param emlSiteNm 을(를) String emlSiteNm로 설정
     */
    public void setEmlSiteNm(String emlSiteNm) {
        this.emlSiteNm = emlSiteNm;
    }

    /**
     * int joinTypeSn을 반환
     * 
     * @return int joinTypeSn
     */
    public int getJoinTypeSn() {
        return joinTypeSn;
    }

    /**
     * joinTypeSn을 설정
     * 
     * @param joinTypeSn 을(를) int joinTypeSn로 설정
     */
    public void setJoinTypeSn(int joinTypeSn) {
        this.joinTypeSn = joinTypeSn;
    }

    /*
     * (non-Javadoc)
     * @see zesinc.login.domain.IUserSessVO#getRgnTelno()
     */
    @Override
    public String getRgnTelno() {
        return rgnTelno;
    }

    /**
     * rgnTelno을 설정
     * 
     * @param rgnTelno 을(를) String rgnTelno로 설정
     */
    public void setRgnTelno(String rgnTelno) {
        this.rgnTelno = rgnTelno;
    }

    /*
     * (non-Javadoc)
     * @see zesinc.login.domain.IUserSessVO#getTelofcTelno()
     */
    @Override
    public String getTelofcTelno() {
        return telofcTelno;
    }

    /**
     * telofcTelno을 설정
     * 
     * @param telofcTelno 을(를) String telofcTelno로 설정
     */
    public void setTelofcTelno(String telofcTelno) {
        this.telofcTelno = telofcTelno;
    }

    /*
     * (non-Javadoc)
     * @see zesinc.login.domain.IUserSessVO#getIndivTelno()
     */
    @Override
    public String getIndivTelno() {
        return indivTelno;
    }

    /**
     * indivTelno을 설정
     * 
     * @param indivTelno 을(를) String indivTelno로 설정
     */
    public void setIndivTelno(String indivTelno) {
        this.indivTelno = indivTelno;
    }

    /*
     * (non-Javadoc)
     * @see zesinc.login.domain.IUserSessVO#getMblRgnTelno()
     */
    @Override
    public String getMblRgnTelno() {
        return mblRgnTelno;
    }

    /**
     * mblRgnTelno을 설정
     * 
     * @param mblRgnTelno 을(를) String mblRgnTelno로 설정
     */
    public void setMblRgnTelno(String mblRgnTelno) {
        this.mblRgnTelno = mblRgnTelno;
    }

    /*
     * (non-Javadoc)
     * @see zesinc.login.domain.IUserSessVO#getMblTelofcTelno()
     */
    @Override
    public String getMblTelofcTelno() {
        return mblTelofcTelno;
    }

    /**
     * mblTelofcTelno을 설정
     * 
     * @param mblTelofcTelno 을(를) String mblTelofcTelno로 설정
     */
    public void setMblTelofcTelno(String mblTelofcTelno) {
        this.mblTelofcTelno = mblTelofcTelno;
    }

    /*
     * (non-Javadoc)
     * @see zesinc.login.domain.IUserSessVO#getMblIndivTelno()
     */
    @Override
    public String getMblIndivTelno() {
        return mblIndivTelno;
    }

    /**
     * mblIndivTelno을 설정
     * 
     * @param mblIndivTelno 을(를) String mblIndivTelno로 설정
     */
    public void setMblIndivTelno(String mblIndivTelno) {
        this.mblIndivTelno = mblIndivTelno;
    }


    /**
     * String zip을 반환
     * 
     * @return String zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * zip을 설정
     * 
     * @param zip 을(를) String zip로 설정
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * String userAddr을 반환
     * 
     * @return String userAddr
     */
    public String getUserAddr() {
        return userAddr;
    }

    /**
     * userAddr을 설정
     * 
     * @param userAddr 을(를) String userAddr로 설정
     */
    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    /**
     * String daddr을 반환
     * 
     * @return String daddr
     */
    public String getDaddr() {
        return daddr;
    }

    /**
     * daddr을 설정
     * 
     * @param daddr 을(를) String daddr로 설정
     */
    public void setDaddr(String daddr) {
        this.daddr = daddr;
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

    /**
     * Date updtDt을 반환
     * 
     * @return Date updtDt
     */
    public Date getUpdtDt() {
        return updtDt;
    }

    /**
     * updtDt을 설정
     * 
     * @param updtDt 을(를) Date updtDt로 설정
     */
    public void setUpdtDt(Date updtDt) {
        this.updtDt = updtDt;
    }

    /**
     * Date lastCntnDt을 반환
     * 
     * @return Date lastCntnDt
     */
    public Date getLastCntnDt() {
        return lastCntnDt;
    }

    /**
     * lastCntnDt을 설정
     * 
     * @param lastCntnDt 을(를) Date lastCntnDt로 설정
     */
    public void setLastCntnDt(Date lastCntnDt) {
        this.lastCntnDt = lastCntnDt;
    }

    /**
     * String itrstArtclCn을 반환
     * 
     * @return String itrstArtclCn
     */
    public String getItrstArtclCn() {
        return itrstArtclCn;
    }

    /**
     * itrstArtclCn을 설정
     * 
     * @param itrstArtclCn 을(를) String itrstArtclCn로 설정
     */
    public void setItrstArtclCn(String itrstArtclCn) {
        this.itrstArtclCn = itrstArtclCn;
    }

    /**
     * String lgnRsltNo을 반환
     * 
     * @return String lgnRsltNo
     */
    public String getLgnRsltNo() {
        return lgnRsltNo;
    }

    /**
     * lgnRsltNo을 설정
     * 
     * @param lgnRsltNo 을(를) String lgnRsltNo로 설정
     */
    public void setLgnRsltNo(String lgnRsltNo) {
        this.lgnRsltNo = lgnRsltNo;
    }

    /**
     * String userTypeNm을 반환
     * 
     * @return String userTypeNm
     */
    public String getUserTypeNm() {
        return userTypeNm;
    }

    /**
     * userTypeNm을 설정
     * 
     * @param userTypeNm 을(를) String userTypeNm로 설정
     */
    public void setUserTypeNm(String userTypeNm) {
        this.userTypeNm = userTypeNm;
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
     * String mdfrId을 반환
     * 
     * @return String mdfrId
     */
    public String getMdfrId() {
        return mdfrId;
    }

    /**
     * mdfrId을 설정
     * 
     * @param mdfrId 을(를) String mdfrId로 설정
     */
    public void setMdfrId(String mdfrId) {
        this.mdfrId = mdfrId;
    }

    /**
     * String userGrdCdDsctn을 반환
     * 
     * @return String userGrdCdDsctn
     */
    @Override
    public String getUserGrdCdDsctn() {
        return userGrdCdDsctn;
    }

    /**
     * userGrdCdDsctn을 설정
     * 
     * @param userGrdCdDsctn 을(를) String userGrdCdDsctn로 설정
     */
    public void setUserGrdCdDsctn(String userGrdCdDsctn) {
        this.userGrdCdDsctn = userGrdCdDsctn;
    }

    /**
     * String userSttsSn을 반환
     * 
     * @return String userSttsSn
     */
    public String getUserSttsSn() {
        return userSttsSn;
    }

    /**
     * userSttsSn을 설정
     * 
     * @param userSttsSn 을(를) String userSttsSn로 설정
     */
    public void setUserSttsSn(String userSttsSn) {
        this.userSttsSn = userSttsSn;
    }

    /**
     * String pswdChgDt을 반환
     * 
     * @return String pswdChgDt
     */
    public String getPswdChgDt() {
        return pswdChgDt;
    }

    /**
     * pswdChgDt을 설정
     * 
     * @param pswdChgDt 을(를) String pswdChgDt로 설정
     */
    public void setPswdChgDt(String pswdChgDt) {
        this.pswdChgDt = pswdChgDt;
    }

    /**
     * Integer pswdChgDtCo을 반환
     * 
     * @return Integer pswdChgDtCo
     */
    public Integer getPswdChgDtCo() {
        return pswdChgDtCo;
    }

    /**
     * pswdChgDtCo을 설정
     * 
     * @param pswdChgDtCo 을(를) Integer pswdChgDtCo로 설정
     */
    public void setPswdChgDtCo(Integer pswdChgDtCo) {
        this.pswdChgDtCo = pswdChgDtCo;
    }

    /*
     * (non-Javadoc)
     * @see zesinc.login.domain.IUserSessVO#getAge()
     */
    @Override
    public Integer getAge() {
        return age;
    }

    /**
     * age을 설정
     * 
     * @param age 을(를) Integer age로 설정
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /*
     * (non-Javadoc)
     * @see zesinc.login.domain.IUserSessVO#getAgeType()
     */
    @Override
    public String getAgeType() {
        return ageType;
    }

    /**
     * ageType을 설정
     * 
     * @param ageType 을(를) String ageType로 설정
     */
    public void setAgeType(String ageType) {
        this.ageType = ageType;
    }

    /*
     * (non-Javadoc)
     * @see zesinc.login.domain.IUserSessVO#getGndrClsfSn()
     */
    @Override
    public String getGndrClsfSn() {
        return gndrClsfSn;
    }

    /**
     * gndrClsfSn을 설정
     * 
     * @param gndrClsfSn 을(를) String gndrClsfSn로 설정
     */
    public void setGndrClsfSn(String gndrClsfSn) {
        this.gndrClsfSn = gndrClsfSn;
    }

    /**
     * Integer wholMbrCnt을 반환
     * 
     * @return Integer wholMbrCnt
     */
    public Integer getWholMbrCnt() {
        return wholMbrCnt;
    }

    /**
     * wholMbrCnt을 설정
     * 
     * @param wholMbrCnt 을(를) Integer wholMbrCnt로 설정
     */
    public void setWholMbrCnt(Integer wholMbrCnt) {
        this.wholMbrCnt = wholMbrCnt;
    }

    /**
     * boolean isValidId을 반환
     * 
     * @return boolean isValidId
     */
    public boolean isValidId() {
        return isValidId;
    }

    /**
     * isValidId을 설정
     * 
     * @param isValidId 을(를) boolean isValidId로 설정
     */
    public void setValidId(boolean isValidId) {
        this.isValidId = isValidId;
    }

    /**
     * boolean isValidPwd을 반환
     * 
     * @return boolean isValidPwd
     */
    public boolean isValidPwd() {
        return isValidPwd;
    }

    /**
     * isValidPwd을 설정
     * 
     * @param isValidPwd 을(를) boolean isValidPwd로 설정
     */
    public void setValidPwd(boolean isValidPwd) {
        this.isValidPwd = isValidPwd;
    }

    /**
     * String returnUrl을 반환
     * 
     * @return String returnUrl
     */
    public String getReturnUrl() {
        return returnUrl;
    }

    /**
     * returnUrl을 설정
     * 
     * @param returnUrl 을(를) String returnUrl로 설정
     */
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

}
