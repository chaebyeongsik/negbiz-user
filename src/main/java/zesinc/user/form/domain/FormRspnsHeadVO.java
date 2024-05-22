package zesinc.user.form.domain;

import java.util.Date;

import zesinc.web.vo.BaseVO;

/**
 * 응답헤더 정보 VO 클래스
 *
 *  <pre>
 * << 개정이력(Modification Information) >>
 *
 *      수정일          수정자                 수정내용
 * --------------  --------  -------------------------------
 *   2023-02-22.     이초연     최초작성
 * </pre>
 *
 * @author (주)제스아이앤씨
 * @see
 * 
 */
public class FormRspnsHeadVO extends BaseVO {
	
	private static final long serialVersionUID = 6657553667603973547L;
			
	/** 폼일련번호 */
	private Integer formSn;
	
	/** 응답헤더번호 */
	private Integer rspnsHeadNo;
	
	/** 응답상태 */
	private String rspnsSttsNo;
	
	/** 응답일시 */
	private Date rspnsDt;
	
	/** 응답자명 */
	private String rspnsNm;
	
	/** 이메일아이디 */
	private String rspnsEmlId;
	
	/** 이메일사이트명 */
	private String rspnsEmlSiteNm;
	
	/** 지역전화번호 */
	private String rgnTelno;
	
	/** 국전화번호 */
	private String telofcTelno;
	
	/** 개별전화번호 */
	private String indivTelno;
	
	/** 응답항목명 모음 */
	private String rspnsArtclNms;
	
	/** 응답답변모음 */
	private String rspnsAnsCns;
	
	
	public Integer getFormSn() { return formSn; }

	public void setFormSn(Integer string) { this.formSn = string; }

	public Integer getRspnsHeadNo() { return rspnsHeadNo; }

	public void setRspnsHeadNo(Integer rspnsHeadNo) { this.rspnsHeadNo = rspnsHeadNo; }

	public String getRspnsSttsNo() { return rspnsSttsNo; }

	public void setRspnsSttsNo(String rspnsSttsNo) { this.rspnsSttsNo = rspnsSttsNo; }

	public Date getRspnsDt() { return rspnsDt; }

	public void setRspnsDt(Date rspnsDt) { this.rspnsDt = rspnsDt; }

	public String getRspnsNm() { return rspnsNm; }

	public void setRspnsNm(String rspnsNm) { this.rspnsNm = rspnsNm; }

	public String getRspnsEmlId() { return rspnsEmlId; }

	public void setRspnsEmlId(String rspnsEmlId) { this.rspnsEmlId = rspnsEmlId; }

	public String getRspnsEmlSiteNm() { return rspnsEmlSiteNm; }

	public void setRspnsEmlSiteNm(String rspnsEmlSiteNm) { this.rspnsEmlSiteNm = rspnsEmlSiteNm; }

	public String getRgnTelno() { return rgnTelno; }

	public void setRgnTelno(String rgnTelno) { this.rgnTelno = rgnTelno; }

	public String getTelofcTelno() { return telofcTelno; }

	public void setTelofcTelno(String telofcTelno) { this.telofcTelno = telofcTelno; }

	public String getIndivTelno() { return indivTelno; }

	public void setIndivTelno(String indivTelno) { this.indivTelno = indivTelno; }

	
	@Override
	public String toString() {
		return "FormRspnsHeadVO [formSn=" + formSn + ", rspnsHeadNo=" + rspnsHeadNo + ", rspnsSttsNo=" + rspnsSttsNo
				+ ", rspnsDt=" + rspnsDt + ", rspnsNm=" + rspnsNm + ", rspnsEmlId=" + rspnsEmlId + ", rspnsEmlSiteNm="
				+ rspnsEmlSiteNm + ", rgnTelno=" + rgnTelno + ", telofcTelno=" + telofcTelno + ", indivTelno="
				+ indivTelno + "]";
	}

	public String getRspnsArtclNms() {
		return rspnsArtclNms;
	}

	public void setRspnsArtclNms(String rspnsArtclNms) {
		this.rspnsArtclNms = rspnsArtclNms;
	}

	public String getRspnsAnsCns() {
		return rspnsAnsCns;
	}

	public void setRspnsAnsCns(String rspnsAnsCns) {
		this.rspnsAnsCns = rspnsAnsCns;
	}
		
}
