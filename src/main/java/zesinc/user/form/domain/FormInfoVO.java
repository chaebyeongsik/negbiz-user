package zesinc.user.form.domain;

import java.util.Arrays;
import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.web.vo.PageVO;

/**
 * 폼정보 VO 클래스
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
public class FormInfoVO extends PageVO {

	private static final long serialVersionUID = 6657553667603973547L;

	/** 폼일련번호 */
	private Integer formSn;

	/** 폼제목 */
	private String formTtl;

	/** 폼설명 */
	private String formExpln;

	/** 시작일자 */
	private String bgngYmd;

	/** 종료일자 */
	private String endYmd;

	/** 응답인원수 */
	private Integer rspnsNope;
	
	/** 제한인원수 */
	private Integer lmtNope;

	/** 파일일련번호 */
	private Integer fileSn;

	/** 파일경로명 */
	private String filePathNm;

	/** 선착순여부 */
	private String frstcmYn;

	/** 게시여부 */
	private String pstgYn;

	/** 작성자정보수집상태번호 */
	private String wrtrInfoClctSttsNo;

	/** 개인정보수집동의내용 */
	private String prvcClctAgreCn;

	/** 삭제여부 */
	private String delYn;

	/** 삭제일시 */
	private String delDt;

	/** 데이터유무여부 */
	private String dataYn;

	/** 등록자ID */
	private String rgtrId;

	/** 등록일시 */
	private String regDt;

	/** 수정자ID */
	private String mdfrId;

	/** 수정일시 */
	private String updtDt;
	
	/** 파일 경로 */
	private String fileUrlAddr;
	 
	/** 사이트일련번호 */
	private Integer siteSn;
					 
	/** 파일 ID 목록 */
	private String[] fileIds;
	 
	/** 첨부파일 목록 */
	private List<FileVO> fileList;
	
	/** 폼 그룹 목록 */
	private List<FormGroupVO> groupList;
	

	public Integer getFormSn() { return formSn; }

	public void setFormSn(Integer formSn) {	this.formSn = formSn; }

	public String getFormTtl() { return formTtl; }

	public void setFormTtl(String formTtl) { this.formTtl = formTtl; }

	public String getFormExpln() { return formExpln; }

	public void setFormExpln(String formExpln) { this.formExpln = formExpln; }

	public Integer getSiteSn() { return siteSn; }

	public void setSiteSn(Integer siteSn) {	this.siteSn = siteSn; }

	public String getBgngYmd() { return bgngYmd; }

	public void setBgngYmd(String bgngYmd) { this.bgngYmd = bgngYmd; }

	public String getEndYmd() { return endYmd; }

	public void setEndYmd(String endYmd) { this.endYmd = endYmd; }

	public Integer getRspnsNope() {	return rspnsNope; }

	public void setRspnsNope(Integer rspnsNope) { this.rspnsNope = rspnsNope; }

	public Integer getLmtNope() { return lmtNope; }

	public void setLmtNope(Integer lmtNope) { this.lmtNope = lmtNope; }

	public Integer getFileSn() { return fileSn; }

	public void setFileSn(Integer fileSn) {	this.fileSn = fileSn; }

	public String getFilePathNm() {	return filePathNm; }

	public void setFilePathNm(String filePathNm) { this.filePathNm = filePathNm; }

	public String getFrstcmYn() { return frstcmYn; }

	public void setFrstcmYn(String frstcmYn) { this.frstcmYn = frstcmYn; }

	public String getPstgYn() { return pstgYn; }

	public void setPstgYn(String pstgYn) { this.pstgYn = pstgYn; }

	public String getWrtrInfoClctSttsNo() { return wrtrInfoClctSttsNo; }

	public void setWrtrInfoClctSttsNo(String wrtrInfoClctSttsNo) { this.wrtrInfoClctSttsNo = wrtrInfoClctSttsNo; }

	public String getPrvcClctAgreCn() { return prvcClctAgreCn; }

	public void setPrvcClctAgreCn(String prvcClctAgreCn) { this.prvcClctAgreCn = prvcClctAgreCn; }

	public String getDelYn() { return delYn; }

	public void setDelYn(String delYn) { this.delYn = delYn; }

	public String getDelDt() { return delDt; }

	public void setDelDt(String delDt) { this.delDt = delDt; }

	public String getDataYn() { return dataYn; }

	public void setDataYn(String dataYn) { this.dataYn = dataYn; }

	public String getRgtrId() { return rgtrId; }

	public void setRgtrId(String rgtrId) { this.rgtrId = rgtrId; }

	public String getRegDt() { return regDt; }

	public void setRegDt(String regDt) { this.regDt = regDt; }

	public String getMdfrId() { return mdfrId; }

	public void setMdfrId(String mdfrId) { this.mdfrId = mdfrId; }

	public String getUpdtDt() { return updtDt; }

	public void setUpdtDt(String updtDt) { this.updtDt = updtDt; }

	public String[] getFileIds() { return fileIds; }

	public void setFileIds(String[] fileIds) { this.fileIds = fileIds; }

	public List<FileVO> getFileList() { return fileList; }

	public void setFileList(List<FileVO> fileList) { this.fileList = fileList; }
	
	public List<FormGroupVO> getGroupList() { return groupList; }

	public void setGroupList(List<FormGroupVO> groupList) { this.groupList = groupList; }

	public String getFileUrlAddr() { return fileUrlAddr; }

	public void setFileUrlAddr(String fileUrlAddr) { this.fileUrlAddr = fileUrlAddr; }

	
	@Override
	public String toString() {
		return "FormInfoVO [formSn=" + formSn + ", formTtl=" + formTtl + ", formExpln=" + formExpln + ", bgngYmd="
				+ bgngYmd + ", endYmd=" + endYmd + ", rspnsNope=" + rspnsNope + ", lmtNope=" + lmtNope + ", fileSn="
				+ fileSn + ", filePathNm=" + filePathNm + ", frstcmYn=" + frstcmYn + ", pstgYn=" + pstgYn
				+ ", wrtrInfoClctSttsNo=" + wrtrInfoClctSttsNo + ", prvcClctAgreCn=" + prvcClctAgreCn + ", delYn="
				+ delYn + ", delDt=" + delDt + ", dataYn=" + dataYn + ", rgtrId=" + rgtrId + ", regDt=" + regDt
				+ ", mdfrId=" + mdfrId + ", updtDt=" + updtDt + ", fileUrlAddr=" + fileUrlAddr + ", siteSn=" + siteSn
				+ ", fileIds=" + Arrays.toString(fileIds) + ", fileList=" + fileList + ", groupList=" + groupList + "]";
	}

}
