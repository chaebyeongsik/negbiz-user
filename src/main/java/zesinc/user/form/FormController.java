package zesinc.user.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import zesinc.component.file.domain.FileVO;
import zesinc.component.file.support.UploadHelper;
import zesinc.core.lang.Validate;
import zesinc.user.form.domain.FormGroupVO;
import zesinc.user.form.domain.FormInfoVO;
import zesinc.user.form.domain.FormRspnsHeadVO;
import zesinc.user.form.domain.FormRspnsVO;
import zesinc.user.form.domain.GroupItemVO;
import zesinc.user.form.domain.ItemOptVO;
import zesinc.web.spring.controller.UserController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.support.pager.Pager;
import zesinc.web.utils.DomnCacheUtil;
import zesinc.web.utils.MessageUtil;
import zesinc.web.vo.cache.DomnCacheVO;

/**
 * 폼빌더 정보 컨트롤러 클래스
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
@Controller("폼 관리")
@RequestMapping(value = { "/user/form" })
public class FormController extends UserController{

	@Resource(name = "opFormService")
	private FormService opFormService;
	
	/**
	 * 신청 메뉴 목록 화면
	 */
	@RequestMapping(value = "BD_selectFormList.do")
    public String selectFormList(HttpServletRequest request, Model model, FormInfoVO formVo) {		
	
		// 현재 사이트 도메인 코드
		DomnCacheVO domnVo = DomnCacheUtil.getDomnCache();
		Integer siteSn = domnVo.getSiteSn();
		formVo.setSiteSn(siteSn);
		
		// 신청 목록 정보
		Pager<FormInfoVO> pager = opFormService.selectFormPageList(formVo);
		model.addAttribute(BaseConfig.KEY_PAGER, pager);
		
		String uri = "/user/form/BD_selectFormList";
        return uri;
    }
	
	/**
	 * 신청 메뉴 상세 화면
	 */
	@RequestMapping(value = "BD_selectForm.do")
    public String selectForm(HttpServletRequest request, HttpServletResponse response, Model model, FormInfoVO formVo) {
		
		// 현재 사이트 도메인 코드
		DomnCacheVO domnVo = DomnCacheUtil.getDomnCache();
		Integer siteSn = domnVo.getSiteSn();
		formVo.setSiteSn(siteSn);
		
		// 신청 상세 정보
		FormInfoVO selectForm = opFormService.selectForm(formVo);
		model.addAttribute("selectForm", selectForm);
		
		String uri = "/user/form/BD_selectForm";
        return uri;
    }
		
	/**
	 * 신청 화면
	 */
	@RequestMapping(value = "BD_insertFormRspns.do")
    public String insertFormRspnsBD(HttpServletRequest request, Model model, FormInfoVO formVo, FormGroupVO groupVo, GroupItemVO itemVo, ItemOptVO optVo) {
		
		// 현재 사이트 도메인 코드
		DomnCacheVO domnVo = DomnCacheUtil.getDomnCache();
		Integer siteSn = domnVo.getSiteSn();
		formVo.setSiteSn(siteSn);
		
		// 신청 상세 정보
		FormInfoVO selectForm = opFormService.selectForm(formVo);		
		List<FormGroupVO> groupList = opFormService.selectFormGroupList(groupVo);
		
		// 폼 그룹 정보
		for(int i=0; i<groupList.size(); i++) {
			Integer formSn = groupList.get(i).getFormSn();			// 폼일련번호 데이터 가져오기
			Integer groupSn = groupList.get(i).getGroupSn(); 		// 그룹일련번호 데이터 가져오기
			FormGroupVO group = groupList.get(i);
			
			itemVo.setFormSn(formSn);
			itemVo.setGroupSn(groupSn);
			
			List<GroupItemVO> itemList = opFormService.selectFormGroupItemList(itemVo);
			group.setItemList(itemList);
			
			// 폼 그룹 - 항목 정보
			for(int j=0; j<itemList.size(); j++) {
				Integer artclSn = itemList.get(j).getArtclSn();		// 항목일련번호 데이터 가져오기
				GroupItemVO item = itemList.get(j);
				
				optVo.setFormSn(formSn);
				optVo.setGroupSn(groupSn);
				optVo.setArtclSn(artclSn);
				
				List<ItemOptVO> optList = opFormService.selectItemOptList(optVo);
				item.setOptList(optList);
			}
		}
		 model.addAttribute("selectForm", selectForm);
		 model.addAttribute("selectGroupList", groupList);
							
		 String uri = "/user/form/BD_insertFormRspns";
		 return uri;
    }
	
	/**
	 * 신청하기 (응답 제출)
	 */
	@RequestMapping(value = "ND_insertFormRspns.do", method = RequestMethod.POST)
    public String insertFormRspns(HttpServletRequest request, Model model ,@RequestParam HashMap<String, Object> paramMap) {
		
		// 첨부파일 정보 세팅
		String[] realFilenm = ((String) paramMap.get("realFileNms")).split(",");
		String[] filenms = ((String) paramMap.get("fileNms")).split(",");
		String fileNm = "";
		List<FileVO> fileList = UploadHelper.upload(request, "rspnsFile", Boolean.TRUE);
		
		for(int i=0; i < fileList.size(); i++) {
			boolean file = false;
			List<FileVO> fileList1 =  new ArrayList<FileVO>();
			for(int j=0; j < filenms.length; j++) {
				if(realFilenm[i].toString().equals(filenms[j].toString()) ) {
					file = true;
				}
			}
			if(file) {
				fileList1.add(fileList.get(i));
				paramMap.put(realFilenm[i], fileList1);
			}
		}
		
		for(int i = 0; i < filenms.length; i++) {
			int cnt = 0;
			for(int j = 0; j < realFilenm.length; j++) {
				if((filenms[i].toString()).equals(realFilenm[j].toString())) {
					cnt++;
				}
			}
			if(cnt < 1) {
				fileNm = fileNm + filenms[i].toString() + ",";
			}
		}
		if(fileNm.length() > 0) {
			fileNm = fileNm.substring(0, (fileNm.length() - 1));
			filenms = fileNm.split(",");
			for(int i = 0; i < filenms.length; i++) {
				List<FileVO> fileList1 =  new ArrayList<FileVO>();
				paramMap.put(filenms[i], fileList1);
			}
		}
		
		// 응답 내용 저장
		Integer insertCnt = opFormService.insertFormRspns(paramMap);
		if(insertCnt != 1) {
			return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
		} 
      
		String url = "BD_selectFormList.do?" + OpHelper.getSearchQueryString(request);
		return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), url);
    }
	
}
