package zesinc.user.form.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import zesinc.component.file.FileService;
import zesinc.component.file.domain.FileVO;
import zesinc.core.lang.Validate;
import zesinc.user.bbs.domain.BbsVO;
import zesinc.user.form.FormMapper;
import zesinc.user.form.FormService;
import zesinc.user.form.domain.FormGroupVO;
import zesinc.user.form.domain.FormInfoVO;
import zesinc.user.form.domain.FormRspnsHeadVO;
import zesinc.user.form.domain.FormRspnsVO;
import zesinc.user.form.domain.GroupItemVO;
import zesinc.user.form.domain.ItemOptVO;
import zesinc.web.support.pager.Pager;

/**
 * 폼빌더 정보 서비스 구현 클래스
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
@Service("opFormService")
public class FormServiceImpl extends EgovAbstractServiceImpl implements FormService{
	
	// 촘정보
	@Resource(name = "opFormDao")
	private FormMapper opFormDao;
	
	// 첨부파일
    @Resource(name = "opFileService")
    private FileService opFileService;
		
    /**
	 * 신청 메뉴 목록 화면
	 */
	@Override
    public Pager<FormInfoVO> selectFormPageList(FormInfoVO formVo) {
        List<FormInfoVO> dataList = opFormDao.selectFormList(formVo);
        Integer totalNum = opFormDao.selectFormListCount(formVo);
        
        return new Pager<FormInfoVO>(dataList, formVo, totalNum);
    }
	
	/**
	 * 신청 메뉴 상세 화면
	 */
	@Override
	public FormInfoVO selectForm(FormInfoVO formVo) {
		FormInfoVO dataVo = opFormDao.selectForm(formVo);
		
		if (Validate.isNotEmpty(dataVo.getFileSn())) {
			dataVo.setFileList(opFileService.selectFileList(dataVo.getFileSn()));
		}
		return dataVo;
	}

	/**
	 * 신청화면 그룹 목록 조회
	 */
	@Override
	public List<FormGroupVO> selectFormGroupList(FormGroupVO groupVo) {
		return opFormDao.selectFormGroupList(groupVo);
	}
	
	/**
	 * 신청화면 항목 목록 조회
	 */
	@Override
	public List<GroupItemVO> selectFormGroupItemList(GroupItemVO itemVo) {
		return opFormDao.selectFormGroupItemList(itemVo);
	}
	
	/**
	 * 신청화면 옵션 목록 조회
	 */
	@Override
	public List<ItemOptVO> selectItemOptList(ItemOptVO optVo) {
		return opFormDao.selectItemOptList(optVo);
	}

	/**
	 * 신청하기 (응답 제출)
	 */
	@Override
	public Integer insertFormRspns(HashMap<String, Object> paramMap) {	
		
		Integer formSn = Integer.parseInt(paramMap.get("q_formSn").toString());
		
		// 응답 헤더 저장
		String wrtrInfoNo = paramMap.get("wrtrInfoNo").toString();
		FormRspnsHeadVO headVo = new FormRspnsHeadVO();
		
		// 작성자정보 수집 코드에 
		if(wrtrInfoNo.equals("0")) {
			headVo.setFormSn(formSn);
		} else if(wrtrInfoNo.equals("1")) {
			headVo.setFormSn(formSn);
			headVo.setRspnsNm(paramMap.get("rspnsNm").toString());
			headVo.setRspnsEmlId(paramMap.get("rspnsEmlId").toString());
			headVo.setRspnsEmlSiteNm(paramMap.get("rspnsEmlSiteNm").toString());
		} else if(wrtrInfoNo.equals("2")) {
			headVo.setFormSn(formSn);
			headVo.setRspnsNm(paramMap.get("rspnsNm").toString());
			headVo.setRspnsEmlId(paramMap.get("rspnsEmlId").toString());
			headVo.setRspnsEmlSiteNm(paramMap.get("rspnsEmlSiteNm").toString());
			headVo.setRgnTelno(paramMap.get("rgnTelno").toString());
			headVo.setTelofcTelno(paramMap.get("telofcTelno").toString());
			headVo.setIndivTelno(paramMap.get("indivTelno").toString());
		}
		Integer insertCnt = opFormDao.insertFormRspnsHead(headVo);
		insertCnt = opFormDao.insertLmtNopeCount(headVo);
		
		// 응답 내용 저장
		Set<String> keySet = paramMap.keySet();
		FormRspnsVO rspnsVo = new FormRspnsVO();
		Integer headNo = opFormDao.selectRspnsHeadNo();
		
		for(String key : keySet) {		
			System.out.println("key : " + key + " / value : " + paramMap.get(key));
			String[] grp = null;
			String groupSn = "";				
			String artclSn = "";
			
			// 파일 유형의 응답 저장
			if(key.contains("file_") ) {
				grp = key.split("_");
				groupSn = grp[1];
				artclSn = grp[2];
				System.out.println("groupSn : " + grp[1] + "/ artclSn : " + grp[2]);
				
				if(Validate.isNotEmpty(paramMap.get(key))) {
		            // 첨부파일
					rspnsVo.setFileList((List<FileVO>) paramMap.get(key));
		            Integer fileSn = opFileService.insertFileList(rspnsVo.getFileList());
		            rspnsVo.setFormSn(formSn);
					rspnsVo.setGroupSn(groupSn);
					rspnsVo.setArtclSn(artclSn);
					rspnsVo.setRspnsHeadNo(headNo);
		            rspnsVo.setRspnsAnsCn(fileSn.toString());
		            
		            insertCnt = opFormDao.insertFormRspnsCn(rspnsVo);	
		        } else {
		        	rspnsVo.setFormSn(formSn);
					rspnsVo.setGroupSn(groupSn);
					rspnsVo.setArtclSn(artclSn);
					rspnsVo.setRspnsHeadNo(headNo);
		            rspnsVo.setRspnsAnsCn("");
		            
		            insertCnt = opFormDao.insertFormRspnsCn(rspnsVo);
		        }
			}
			
			// 파일 유형 제외한 다른 유형의 응답 저장
			if(key.contains("item_") ) {
				grp = key.split("_");
				groupSn = grp[1];
				artclSn = grp[2];
				System.out.println("groupSn : " + grp[1] + "/ artclSn : " + grp[2]);
				
				if(Validate.isNotEmpty(paramMap.get(key))) {
					rspnsVo.setFormSn(formSn);
					rspnsVo.setGroupSn(groupSn);
					rspnsVo.setArtclSn(artclSn);
					rspnsVo.setRspnsHeadNo(headNo);
					rspnsVo.setRspnsAnsCn((String) paramMap.get(key));
		            
		            insertCnt = opFormDao.insertFormRspnsCn(rspnsVo);	
		        } else {
		        	rspnsVo.setFormSn(formSn);
					rspnsVo.setGroupSn(groupSn);
					rspnsVo.setArtclSn(artclSn);
					rspnsVo.setRspnsHeadNo(headNo);
					rspnsVo.setRspnsAnsCn("");
		            
		            insertCnt = opFormDao.insertFormRspnsCn(rspnsVo);
		        }	
			}
		}		
        FormRspnsHeadVO rspnsAnsList = new FormRspnsHeadVO();

        // 항목 및 응답 모음 등록
		rspnsAnsList = insertRspnsAnsCns(rspnsVo);
		insertCnt = opFormDao.updateRspnsHeadAnsCn(rspnsAnsList);
		
		return insertCnt;
	}

	/**
	 * 항목모음 및 응답모음
	 */
    @Override
    public FormRspnsHeadVO insertRspnsAnsCns(FormRspnsVO rspnsVo) {    	
    	Integer insertCnt = 0;
    	
        //항목리스트
        GroupItemVO groupItemVO = new GroupItemVO();
        groupItemVO.setFormSn(rspnsVo.getFormSn());
        List<GroupItemVO> iemList = opFormDao.selectRspnsIemList(groupItemVO);
        
        //응답리스트
        List<FormRspnsVO> rspnsList = opFormDao.selectRspns(rspnsVo);
        
        FormRspnsHeadVO rspnsAnsList = new FormRspnsHeadVO();
        String[] rspnsAnsArray = new String[iemList.size()];
        String artclNms = "";
        String rspnsAns = "";
        
        for(int i =0; i< iemList.size(); i++) {
			artclNms += iemList.get(i).getArtclNm();
			artclNms +="[!@]";
    	}
        Arrays.fill(rspnsAnsArray, "-");
        
        for(FormRspnsVO rspns:rspnsList) {
        	for(int k =0; k< iemList.size(); k++) {
				if(rspns.getGroupSn().toString().equals(iemList.get(k).getGroupSn().toString()) && rspns.getArtclSn().toString().equals(iemList.get(k).getArtclSn().toString())){					
	        		if(rspns.getRspnsAnsCn() != null) {
	        			if(!rspns.getRspnsAnsCn().isEmpty()) {
		        			if(iemList.get(k).getArtclTypeCd().equals("fileIem")) {
		    					rspnsAnsArray[k] = "fileIem_"+rspns.getRspnsAnsCn();
		    				}
		    				else if(iemList.get(k).getArtclTypeCd().equals("addrIem")) {
		    					String[] str = rspns.getRspnsAnsCn().split("\\[##]",-1);
		    					rspnsAnsArray[k] = str[1] + " " + str[2] + " (" + str[0] + ")";
		    				}
		    				else if(rspns.getRspnsAnsCn().contains("[##]")){ 
		    	        		String[] str = rspns.getRspnsAnsCn().split("\\[##]",-1);
		    	        		rspnsAnsArray[k] = "";
		    	        		for (int j = 0; j < str.length; j++) {
		    	        			rspnsAnsArray[k] += str[j] + " / ";	
		    					}
		    	        		rspnsAnsArray[k] = rspnsAnsArray[k].substring(0, rspnsAnsArray[k].length() - 2);
		    	        	}
		    				else {
		    					rspnsAnsArray[k] = rspns.getRspnsAnsCn();
		    				}
		    			}
	        		}
					
				}
        	}
        }

		for(int k =0; k< rspnsAnsArray.length; k++) {
			rspnsAns += rspnsAnsArray[k] + "[!@]";
		}
		rspnsAnsList.setRspnsHeadNo(rspnsVo.getRspnsHeadNo()); 
		rspnsAnsList.setRspnsArtclNms(artclNms.substring(0, artclNms.length() - 4));
		rspnsAnsList.setRspnsAnsCns(rspnsAns.substring(0, rspnsAns.length() - 4));
		
		return rspnsAnsList;
    }	
    
}
