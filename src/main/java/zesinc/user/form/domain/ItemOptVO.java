package zesinc.user.form.domain;

import java.util.Arrays;
import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.web.vo.PageVO;

/**
 * 폼옵션 정보 VO 클래스
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
public class ItemOptVO extends PageVO {

	private static final long serialVersionUID = 6657553667603973547L;

	/** 폼일련번호 */
	private Integer formSn;
	
	/** 그룹일련번호 */
	private Integer groupSn;
	
	/** 항목일련번호 */
	private Integer artclSn;

	/** 옵션일련번호 */
	private Integer optSn;

	/** 옵션내용 */
	private String optCn;
	
	/** 파일 ID 목록 */
	private String[] fileIds;
	 
	/** 첨부파일 목록 */
	private List<FileVO> fileList;
	

	public Integer getFormSn() { return formSn; }

	public void setFormSn(Integer formSn) { this.formSn = formSn; }

	public Integer getGroupSn() { return groupSn; }

	public void setGroupSn(Integer groupSn) { this.groupSn = groupSn; }

	public Integer getArtclSn() { return artclSn; }

	public void setArtclSn(Integer artclSn) { this.artclSn = artclSn; }

	public Integer getOptSn() {	return optSn; }

	public void setOptSn(Integer optSn) { this.optSn = optSn; }

	public String getOptCn() { return optCn; }

	public void setOptCn(String optCn) { this.optCn = optCn; }

	public String[] getFileIds() { return fileIds; }

	public void setFileIds(String[] fileIds) { this.fileIds = fileIds; }

	public List<FileVO> getFileList() { return fileList; }

	public void setFileList(List<FileVO> fileList) { this.fileList = fileList; }

	
	@Override
	public String toString() {
		return "ItemOptVO [formSn=" + formSn + ", groupSn=" + groupSn + ", artclSn=" + artclSn + ", optSn=" + optSn
				+ ", optCn=" + optCn + ", fileIds=" + Arrays.toString(fileIds) + ", fileList=" + fileList + "]";
	}
	
}
