package zesinc.user.form;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import zesinc.user.form.domain.FormGroupVO;
import zesinc.user.form.domain.FormInfoVO;
import zesinc.user.form.domain.FormRspnsHeadVO;
import zesinc.user.form.domain.FormRspnsVO;
import zesinc.user.form.domain.GroupItemVO;
import zesinc.user.form.domain.ItemOptVO;
import zesinc.web.support.pager.Pager;

/**
 * 폼빌더 정보 DAO 클래스
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
@Mapper("opFormDao")
public interface FormMapper {
	
	/**
     * 신청 목록 조회
     * 
     * @param formVo
     * @return
     */
	List<FormInfoVO> selectFormList(FormInfoVO formVo);
	
	/**
     * 신청 목록 건수
     * 
     * @param formVo
     * @return
     */
	Integer selectFormListCount(FormInfoVO formVo);

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
     * 응답헤더 등록
     * 
     * @param optVo
     * @return
     */
	Integer insertFormRspnsHead(FormRspnsHeadVO headVo);
	
	/**
     * 응답헤더번호 가져오기
     * 
     * @param optVo
     * @return
     */
	Integer selectRspnsHeadNo();

	/**
     * 응답인원 업데이트
     * 
     * @param optVo
     * @return
     */
	Integer insertLmtNopeCount(FormRspnsHeadVO headVo);
	
	/**
     * 응답내용 등록
     * 
     * @param optVo
     * @return
     */
	Integer insertFormRspnsCn(FormRspnsVO rspnsVO);
	
	/**
     * 응답리스트 조회
     * 
     * @param rspnsVo
     * @return
     */
	List<FormRspnsVO> selectRspns(FormRspnsVO rspnsVo);

	/**
     * 항목리스트 조회
     * 
     * @param rspnsVo
     * @return
     */
	List<GroupItemVO> selectRspnsIemList(GroupItemVO groupItemVO);

	/**
     * 항목모음 및 응답모음
     * 
     * @param rspnsVo
     * @return
     */
	Integer updateRspnsHeadAnsCn(FormRspnsHeadVO rspnsAnsList);

}
