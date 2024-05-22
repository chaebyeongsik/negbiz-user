package zesinc.user.form.domain;

import java.util.Arrays;
import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.web.vo.PageVO;

public class GroupItemVO extends PageVO {

	private static final long serialVersionUID = 665755366763973547L;
	
	// TN_FORM_GROUP_ITEM
	
	/** 폼일련번호 */
	private Integer formSn;
	
	/** 그룹일련번호 */
	private Integer groupSn;

	/** 항목일련번호 */
	private Integer artclSn;

	/** 항목명 */
	private String artclNm;

	/** 항목설명 */
	private String artclExpln;

	/** 항목유형코드 */
	private String artclTypeCd;

	/** 필수여부 */
	private String esntlYn;

	/** 제한값 */
	private Integer lmtVl;

	/** 초기값 */
	private Integer initVl;

	/** 제한파일크기 */
	private Integer lmtFileSz;

	/** 허가파일확장자내역 */
	private String prmsnFileExtnMttr;

	/** 복수선택수 */
	private Integer plChcCnt;
	
	/** 등록자ID */
	private String rgtrId;

	/** 등록일시 */
	private String regDt;
	
	/** 사용여부 */
	private String useYn;
	
	/** 폼 항목, 옵션 목록 */
	private List<ItemOptVO> optList;
	

	public Integer getFormSn() { return formSn;	}

	public void setFormSn(Integer formSn) { this.formSn = formSn; }

	public Integer getGroupSn() { return groupSn; }

	public void setGroupSn(Integer groupSn) { this.groupSn = groupSn; }

	public Integer getArtclSn() { return artclSn; }

	public void setArtclSn(Integer artclSn) { this.artclSn = artclSn; }

	public String getArtclNm() { return artclNm; }

	public void setArtclNm(String artclNm) { this.artclNm = artclNm; }

	public String getArtclExpln() { return artclExpln; }

	public void setArtclExpln(String artclExpln) { this.artclExpln = artclExpln; }

	public String getArtclTypeCd() { return artclTypeCd; }

	public void setArtclTypeCd(String artclTypeCd) { this.artclTypeCd = artclTypeCd; }

	public String getEsntlYn() { return esntlYn; }

	public void setEsntlYn(String esntlYn) { this.esntlYn = esntlYn; }

	public Integer getLmtVl() { return lmtVl; }

	public void setLmtVl(Integer lmtVl) { this.lmtVl = lmtVl; }

	public Integer getInitVl() { return initVl; }

	public void setInitVl(Integer initVl) { this.initVl = initVl; }

	public Integer getLmtFileSz() {	return lmtFileSz; }

	public void setLmtFileSz(Integer lmtFileSz) { this.lmtFileSz = lmtFileSz; }

	public String getPrmsnFileExtnMttr() { return prmsnFileExtnMttr; }

	public void setPrmsnFileExtnMttr(String prmsnFileExtnMttr) { this.prmsnFileExtnMttr = prmsnFileExtnMttr; }

	public String getRgtrId() { return rgtrId; }

	public void setRgtrId(String rgtrId) { this.rgtrId = rgtrId; }

	public String getRegDt() { return regDt; }

	public void setRegDt(String regDt) { this.regDt = regDt; }

	public Integer getPlChcCnt() { return plChcCnt; }

	public void setPlChcCnt(Integer plChcCnt) { this.plChcCnt = plChcCnt; }

	public List<ItemOptVO> getOptList() { return optList; }

	public void setOptList(List<ItemOptVO> optList) { this.optList = optList; }
	
	public String getUseYn() { return useYn; }

	public void setUseYn(String useYn) { this.useYn = useYn; }


	@Override
	public String toString() {
		return "GroupItemVO [formSn=" + formSn + ", groupSn=" + groupSn + ", artclSn=" + artclSn + ", artclNm="
				+ artclNm + ", artclExpln=" + artclExpln + ", artclTypeCd=" + artclTypeCd + ", esntlYn=" + esntlYn
				+ ", lmtVl=" + lmtVl + ", initVl=" + initVl + ", lmtFileSz=" + lmtFileSz + ", prmsnFileExtnMttr="
				+ prmsnFileExtnMttr + ", plChcCnt=" + plChcCnt + ", rgtrId=" + rgtrId + ", regDt=" + regDt + ", useYn="
				+ useYn + ", optList=" + optList + "]";
	}


}
