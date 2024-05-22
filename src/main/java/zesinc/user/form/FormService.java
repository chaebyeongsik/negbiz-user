package zesinc.user.form;

import java.util.HashMap;
import java.util.List;

import zesinc.user.form.domain.FormGroupVO;
import zesinc.user.form.domain.FormInfoVO;
import zesinc.user.form.domain.FormRspnsHeadVO;
import zesinc.user.form.domain.FormRspnsVO;
import zesinc.user.form.domain.GroupItemVO;
import zesinc.user.form.domain.ItemOptVO;
import zesinc.web.support.pager.Pager;

/**
 * 폼빌더 정보 서비스 클래스
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
public interface FormService {
	
	/**
     * 신청 목록 조회
     * 
     * @param formVo
     * @return
     */
	Pager<FormInfoVO> selectFormPageList(FormInfoVO formVo);

	/**
     * 신청 상세 조회
     * 
     * @param formVo
     * @return
     */
	FormInfoVO selectForm(FormInfoVO formVo);
	
	/**
     * 신청화면 그룹 목록 조회
     * 
     * @param groupVo
     * @return
     */
	List<FormGroupVO> selectFormGroupList(FormGroupVO groupVo);
	
	/**
     * 신청화면 항목 목록 조회
     * 
     * @param itemVo
     * @return
     */
	List<GroupItemVO> selectFormGroupItemList(GroupItemVO itemVo);
	
	/**
     * 신청화면 옵션 목록 조회
     * 
     * @param optVo
     * @return
     */
	List<ItemOptVO> selectItemOptList(ItemOptVO optVo);
	
	/**
     * 신청하기 (응답 제출)
     * 
     * @param paramMap
     * @return
     */
	Integer insertFormRspns(HashMap<String, Object> paramMap);

	/**
     * 항목모음 및 응답모음
     * 
     * @param rspnsVo
     * @return
     */
	FormRspnsHeadVO insertRspnsAnsCns(FormRspnsVO rspnsVo);
	
}
