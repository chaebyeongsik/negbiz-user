package zesinc.user.form.domain;

import java.util.Date;
import java.util.List;

import zesinc.web.vo.PageVO;

/**
 * 폼그룹 정보 VO 클래스
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
public class FormGroupVO extends PageVO {

	private static final long serialVersionUID = 6657553667603973547L;

	/** 폼일련번호 */
	private Integer formSn;

	/** 그룹일련번호 */
	private Integer groupSn;

	/** 그룹명 */
	private String groupNm;

	/** 그룹설명 */
	private String groupExpln;
	
	/** 등록자ID */	
	private String rgtrId;
	
	/** 등록일시 */
	private Date regDt;
	
	/** 사용여부 */
	private String useYn;
	
	/** 폼 그룹, 항목 목록 */
	private List<GroupItemVO> itemList;
	

	public Integer getFormSn() { return formSn; }

	public void setFormSn(Integer formSn) { this.formSn = formSn; }

	public Integer getGroupSn() { return groupSn; }

	public void setGroupSn(Integer groupSn) { this.groupSn = groupSn; }

	public String getGroupNm() { return groupNm; }

	public void setGroupNm(String groupNm) { this.groupNm = groupNm; }

	public String getGroupExpln() { return groupExpln; }

	public void setGroupExpln(String groupExpln) { this.groupExpln = groupExpln; }

	public String getRgtrId() { return rgtrId; }

	public void setRgtrId(String rgtrId) { this.rgtrId = rgtrId; }

	public Date getRegDt() { return regDt; }

	public void setRegDt(Date regDt) { this.regDt = regDt; }

	public List<GroupItemVO> getItemList() { return itemList; }

	public void setItemList(List<GroupItemVO> itemList) { this.itemList = itemList; }

	public String getUseYn() { return useYn; }

	public void setUseYn(String useYn) { this.useYn = useYn; }
	

	@Override
	public String toString() {
		return "FormGroupVO [formSn=" + formSn + ", groupSn=" + groupSn + ", groupNm=" + groupNm + ", groupExpln="
				+ groupExpln + ", rgtrId=" + rgtrId + ", regDt=" + regDt + ", useYn=" + useYn + ", itemList=" + itemList
				+ "]";
	}
	
}
