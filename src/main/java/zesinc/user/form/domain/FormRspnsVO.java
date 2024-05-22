package zesinc.user.form.domain;

import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.web.vo.BaseVO;

/**
 * 응답내용 정보 VO 클래스
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
public class FormRspnsVO extends BaseVO {
	
	private static final long serialVersionUID = 6657553667603973547L;	
	
	/** 폼일련번호 */
	private Integer formSn;

	/** 그룹일련번호 */
	private String groupSn;
	
	/** 항목일련번호 */
	private String artclSn;
	
	/** 응답헤더번호 */
	private Integer rspnsHeadNo;
	
	/** 응답일련번호 */
	private Integer rspnsSn;
	
	/** 응답답변모음 */
	private String rspnsAnsCn;
	
	/** 첨부파일 목록 */
	private List<FileVO> fileList;
	
	
	public Integer getFormSn() { return formSn; }

	public void setFormSn(Integer string) { this.formSn = string; }

	public String getGroupSn() { return groupSn; }

	public void setGroupSn(String groupSn) { this.groupSn = groupSn; }

	public String getArtclSn() { return artclSn; }

	public void setArtclSn(String artclSn) { this.artclSn = artclSn; }
	
	public Integer getRspnsHeadNo() { return rspnsHeadNo; }

	public void setRspnsHeadNo(Integer rspnsHeadNo) { this.rspnsHeadNo = rspnsHeadNo; }

	public Integer getRspnsSn() { return rspnsSn; }

	public void setRspnsSn(Integer rspnsSn) { this.rspnsSn = rspnsSn; }

	public String getRspnsAnsCn() { return rspnsAnsCn; }

	public void setRspnsAnsCn(String rspnsAnsCn) { this.rspnsAnsCn = rspnsAnsCn; }
	
	public void setRspnsAnsCn(Integer fileSn) {}
	
	public List<FileVO> getFileList() { return fileList; }

	public void setFileList(List<FileVO> fileList) { this.fileList = fileList; }

	
	@Override
	public String toString() {
		return "FormRspnsVO [formSn=" + formSn + ", groupSn=" + groupSn + ", artclSn=" + artclSn + ", rspnsHeadNo="
				+ rspnsHeadNo + ", rspnsSn=" + rspnsSn + ", rspnsAnsCn=" + rspnsAnsCn + ", fileList=" + fileList + "]";
	}
		
}
