package zesinc.user.bbs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.component.file.domain.FileVO;
import zesinc.component.file.support.UploadHelper;
import zesinc.core.cache.CacheService;
import zesinc.core.lang.Validate;
import zesinc.core.privacy.PrivacyFilter;
import zesinc.core.privacy.PrivacyFilterFactory;
import zesinc.core.privacy.PrivacyResultVO;
import zesinc.login.domain.UserLoginVO;
import zesinc.user.bbs.domain.BbsCtgryVO;
import zesinc.user.bbs.domain.BbsVO;
import zesinc.user.bbs.support.BbsUtil;
import zesinc.user.bbs.support.BbsValidate;
import zesinc.web.spring.controller.UserController;
import zesinc.web.spring.resolver.ParamArgumentResolver;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.support.pager.Pager;
import zesinc.web.support.tag.bbsTmplat.support.BbsSearchSupport;
import zesinc.web.utils.Converter;
import zesinc.web.utils.DomnCacheUtil;
import zesinc.web.utils.MessageUtil;
import zesinc.web.utils.PrhibtUtil;
import zesinc.web.utils.XssUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.vo.FeedVO;
import zesinc.web.vo.IUserSessVO;
import zesinc.web.vo.cache.BbsConfigCacheVO;
import zesinc.web.vo.cache.BbsCtgryCacheVO;
import zesinc.web.vo.cache.BbsDomnCacheVO;
import zesinc.web.vo.cache.BbsItemCacheVO;

/**
 * 게시판 정보 컨트롤러 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-07-16.    황신욱   최초작성
 *  2019-10-25.    woogi 첨부파일허용확장자 대소문자구분없이 체크
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("게시판 관리")
@RequestMapping(value = { "/**/user/bbs" })
public class BbsController extends UserController {

    @Resource(name = "opBbsService")
    private BbsService opBbsService;

    /**
     * 각 템플릿별 베이스 URL
     */
    private String getTemplateFolderName(String skinPath) {
        return ("common/bbsTmplats/" + skinPath + "/");
    }

    /**
     * 게시판 페이지 목록
     */
    @RequestMapping(value = "BD_selectBbsList.do")
    public String selectBbsList(HttpServletRequest request, Model model, BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));

        // 대상 게시판을 못찾은 경우 오류
        if(Validate.isEmpty(bbsConfigVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        // 템플릿이 없는 경우
        BbsDomnCacheVO domnCacheVo = null;
        Map<Integer, BbsDomnCacheVO> skins = bbsConfigVo.getSkins();
        if(Validate.isNotEmpty(skins)) {
            Integer siteSn = DomnCacheUtil.getSiteSn();
            domnCacheVo = skins.get(siteSn);
            if(Validate.isEmpty(domnCacheVo.getLstTmpltNm())) {
                return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        // Request에 설정 주입
        BbsUtil.setBbsConfigToRequest(model, bbsConfigVo, bbsVo);

        if(Validate.isEmpty(request.getParameter("q_rowPerPage"))) {
            bbsVo.addParam("q_rowPerPage", bbsConfigVo.getPagePstCnt());
            setPageParam(bbsVo);
        }

        // 게시판 항목설정에 따른 검색 대상 및 검색 유형 설정
        String searchKeyTy = bbsVo.getString("q_searchKeyTy");
        if(Validate.isNotEmpty(searchKeyTy)) {
            if(searchKeyTy.indexOf("___") > -1) {
                String[] search = searchKeyTy.split("___");
                bbsVo.addParam("q_searchKey", BbsSearchSupport.getDBColumnNm(search[0]));
                bbsVo.addParam("q_srchType", search[1]);
            }
        }

        // 일반글 목록
        bbsVo.setNtcPstYn("N");
        bbsVo.setPstgUseYn(bbsConfigVo.getPstgUseYn());
        Pager<BbsVO> pager = opBbsService.selectBbsPageList(bbsVo);
        model.addAttribute(BaseConfig.KEY_PAGER, pager);

        // 공지사용시 게시물에 공지사항 목록을 추가. 단! 1페이지에만 표시
        if(bbsConfigVo.getNtcUseYn().equals("Y")) {
            Integer currPage = bbsVo.getInteger("q_currPage");
            if(currPage < 2) {
                addNoticeBbsList(bbsVo, model);
            }
        }

        // FEED 사용시 FEED URL 반환
        if(Validate.isNotEmpty(bbsConfigVo.getNfeedUseYn()) && bbsConfigVo.getNfeedUseYn().equals("Y")) {
            model.addAttribute("RSS_URL", makeFeedUrl(request, bbsConfigVo, bbsVo, "Rss"));
            model.addAttribute("ATOM_URL", makeFeedUrl(request, bbsConfigVo, bbsVo, "Atom"));
        }

        String path = getTemplateFolderName(domnCacheVo.getLstTmpltNm());
        return path + "PD_bbs.list";
    }

    // 공지리스트 리스트를 셋팅
    private void addNoticeBbsList(BbsVO bbsVo, Model model) {
        bbsVo.setNtcPstYn("Y");
        List<BbsVO> noticeList = opBbsService.selectBbsNoticeList(bbsVo);

        model.addAttribute(BaseConfig.KEY_NOTICE_LIST, noticeList);
    }

    /**
     * 게시판 상세
     */
    @RequestMapping(value = "BD_selectBbs.do")
    public String selectBbs(HttpServletRequest request, HttpServletResponse response, Model model, BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));

        // 대상 게시판을 못찾은 경우 오류
        if(Validate.isEmpty(bbsConfigVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        // 템플릿이 없는 경우
        BbsDomnCacheVO domnCacheVo = null;
        Map<Integer, BbsDomnCacheVO> skins = bbsConfigVo.getSkins();
        if(Validate.isNotEmpty(skins)) {
            Integer siteSn = DomnCacheUtil.getSiteSn();
            domnCacheVo = skins.get(siteSn);
            if(Validate.isEmpty(domnCacheVo.getPstTmpltNm())) {
                return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        // Request에 설정 주입
        BbsUtil.setBbsConfigToRequest(model, bbsConfigVo, bbsVo);

        BbsVO dataVo = opBbsService.selectBbs(bbsVo);

        // 대상 게시물이 없는 경우
        if(Validate.isEmpty(dataVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.noData"));
        }

        // 비공개글인 경우 인증여부 및 등록자와 일치하는지 확인
        if(dataVo.getRlsYn().equals("N")) {
            IUserSessVO loginVo = getUserSession();
            if(Validate.isEmpty(loginVo) || !dataVo.getRgtrId().equals(loginVo.getUserId())) {
                return alertAndBack(model, MessageUtil.getMessage("common.needAuth"));
            }
        }

        // 삭제 처리된 게시물은 그냥 리턴
        if(dataVo.getDelYn().equals("Y") || dataVo.getMngrDelYn().equals("Y")) {
            return alertAndBack(model, MessageUtil.getMessage("common.noData"));
        }

        // 조회수 증가를 위한 쿠키 체크
        if(BbsUtil.isIncreateReadCnt("bbs" + bbsVo.getParam("q_bbsSn") + "_" + bbsVo.getParam("q_bbsDocNo"),
            bbsConfigVo.getInqCntLmtHrCnt(), request, response)) {
            opBbsService.updateBbsInqCnt(bbsVo);
            dataVo.setInqCnt(dataVo.getInqCnt() + 1);
        }

        // 에디터사용이 N일 때 \n이 있으면 <br/> 태그로 전환
        if("N".equals(bbsConfigVo.getMngrEdtrUseYn())) {
            dataVo.setDocContsCn(Converter.translateBR(dataVo.getDocContsCn()));
        }

        // 금지단어 마스킹
        if(bbsConfigVo.getPhbwdUseYn().equals("Y")) {
            String docContsCn = PrhibtUtil.maskPrhibtWrd(bbsConfigVo.getPhbwdCdId(), dataVo.getDocContsCn());
            String ttl = PrhibtUtil.maskPrhibtWrd(bbsConfigVo.getPhbwdCdId(), dataVo.getTtl());
            dataVo.setDocContsCn(docContsCn);
            dataVo.setTtl(ttl);
        }

        // 개인정보 마스킹
        if(bbsConfigVo.getPhbwdUseYn().equals("Y") && Validate.isNotEmpty(dataVo.getDocContsCn())) {
            PrivacyFilter pf = null;
            try {
                pf = PrivacyFilterFactory.getInstance(dataVo.getDocContsCn());
            } catch (Exception e) {
                logger.error("개인정보 마스킹 오류 : {}", e);
            }
            if(Validate.isNotEmpty(pf)) {
                PrivacyResultVO result = pf.doFilter();
                String docContsCn = PrhibtUtil.maskPrhibtWrd(result.getFilterList(), dataVo.getDocContsCn());
                dataVo.setDocContsCn(docContsCn);
            }
        }

        model.addAttribute(BaseConfig.KEY_DATA_VO, dataVo);

        String path = getTemplateFolderName(domnCacheVo.getPstTmpltNm());
        return path + "PD_bbs.view";
    }

    /**
     * 게시판 입력 폼
     */
    @RequestMapping(value = { "BD_insertBbsForm.do", "BD_updateBbsForm.do", "BD_replyBbsForm.do" })
    public String insertBbsForm(HttpServletRequest request, Model model, BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));

        // 대상 게시판을 못찾은 경우 오류
        if(Validate.isEmpty(bbsConfigVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        // 인증 여부 확인
        UserLoginVO loginVo = (UserLoginVO) OpHelper.getUserSession();
        if(Validate.isEmpty(loginVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.needAuth"));
        }

        // 템플릿이 없는 경우
        BbsDomnCacheVO domnCacheVo = null;
        Map<Integer, BbsDomnCacheVO> skins = bbsConfigVo.getSkins();
        if(Validate.isNotEmpty(skins)) {
            Integer siteSn = DomnCacheUtil.getSiteSn();
            domnCacheVo = skins.get(siteSn);
            if(Validate.isEmpty(domnCacheVo.getInptTmpltNm())) {
                return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        // Request에 설정 주입
        BbsUtil.setBbsConfigToRequest(model, bbsConfigVo, bbsVo);

        ValidateResultHolder holder = BbsValidate.validate(bbsConfigVo, bbsVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        BbsVO dataVo = new BbsVO();
        dataVo.setBbsSn(bbsVo.getBbsSn());

        // URI로 기능 구분
        String requestUri = request.getRequestURI();
        if(requestUri.endsWith("BD_updateBbsForm.do")) {
            dataVo = opBbsService.selectBbs(bbsVo);

            // 대상 게시물이 없는 경우
            if(Validate.isEmpty(dataVo)) {
                return alertAndBack(model, MessageUtil.getMessage("common.noData"));
            }
            // *** 수정폼 ***
            if(!dataVo.getRgtrId().equals(loginVo.getUserId())) {
                return alertAndBack(model, MessageUtil.getMessage("common.needAuth"));
            }

            model.addAttribute("ACTION", "ND_updateBbs.do");
        } else if(requestUri.endsWith("BD_replyBbsForm.do")) {
            // *** 답변등록폼 ***
            dataVo = new BbsVO();
            dataVo.setBbsSn(bbsVo.getInteger("q_bbsSn"));
            dataVo.setBbsDocNo(bbsVo.getString("q_bbsDocNo"));

            model.addAttribute("ACTION", "ND_insertBbs.do");
        } else {
            // *** 신규등록폼 ***
            model.addAttribute("ACTION", "ND_insertBbs.do");
        }

        model.addAttribute(BaseConfig.KEY_DATA_VO, dataVo);

        String path = getTemplateFolderName(domnCacheVo.getInptTmpltNm());
        return path + "PD_bbs.form";
    }

    /**
     * 게시글 만족도평가하기
     */
    @RequestMapping(value = "ND_updateStsfdgEvl.do")
    public String updateStsfdgEvl(HttpServletRequest request, HttpServletResponse response, Model model, BbsVO bbsVo) {

        Integer result = opBbsService.updateStsfdgEvl(bbsVo);
        if(Validate.isEmpty(result)) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        bbsVo.addParam("q_bbsSn", bbsVo.getBbsSn());
        bbsVo.addParam("q_bbsDocNo", bbsVo.getBbsDocNo());
        BbsVO resultVo = opBbsService.selectBbs(bbsVo);

        return responseJson(model, MessageUtil.TRUE, resultVo);
    }

    /**
     * 게시글 추천하기
     */
    @RequestMapping(value = "ND_updateRecomend.do")
    public String updateRecoment(HttpServletRequest request, HttpServletResponse response, Model model, BbsVO bbsVo) {

        Integer result = opBbsService.updateRecoment(bbsVo);
        if(Validate.isEmpty(result)) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        bbsVo.addParam("q_bbsSn", bbsVo.getBbsSn());
        bbsVo.addParam("q_bbsDocNo", bbsVo.getBbsDocNo());
        BbsVO resultVo = opBbsService.selectBbs(bbsVo);

        return responseJson(model, MessageUtil.TRUE, resultVo.getRcmdtnCnt());
    }

    /**
     * 게시글 신고하기
     */
    @RequestMapping(value = "ND_updateSttemnt.do")
    public String updateSttemnt(HttpServletRequest request, HttpServletResponse response, Model model, BbsVO bbsVo) {

        Integer result = opBbsService.updateSttemnt(bbsVo);
        if(Validate.isEmpty(result)) {
            return responseJson(model, MessageUtil.FALSE, MessageUtil.getMessage("common.processFail"));
        }

        bbsVo.addParam("q_bbsSn", bbsVo.getBbsSn());
        bbsVo.addParam("q_bbsDocNo", bbsVo.getBbsDocNo());
        BbsVO resultVo = opBbsService.selectBbs(bbsVo);

        return responseJson(model, MessageUtil.TRUE, resultVo.getDclrCnt());
    }

    /**
     * 게시물 등록
     */
    @RequestMapping(value = "ND_insertBbs.do")
    public String insertBbs(HttpServletRequest request, HttpServletResponse response, Model model, BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));

        // 대상 게시판을 못찾은 경우 오류
        if(Validate.isEmpty(bbsConfigVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        // 인증 여부 확인
        IUserSessVO loginVo = getUserSession();
        if(Validate.isEmpty(loginVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.needAuth"));
        }

        /*
         * 환경설정에 따른 게시물 입력값 검증
         */
        ValidateResultHolder holder = BbsValidate.validate(bbsConfigVo, bbsVo);
        if(holder.isValid()) {
            bbsVo.setRgtrId(loginVo.getUserId());
            bbsVo.setRgtrNm(loginVo.getUserNm());
            bbsVo.setIpAddr(getIpAddr());
            bbsVo.setUserIdntfNm(loginVo.getUserIdntfNm());

            List<FileVO> fileList = UploadHelper.upload(request, "bbs");
            bbsVo.setFileList(fileList);

            BbsItemCacheVO bbsItemCacheVo = bbsConfigVo.getBbsItemMap().get("file");
            if ("Y".equals(bbsItemCacheVo.getEsntlYn()) && fileList.size() == 0) {
                String colNm = bbsItemCacheVo.getColNm();
                if (Validate.isNotEmpty(bbsItemCacheVo.getScrnNm())) {
                    colNm = bbsItemCacheVo.getScrnNm();
                }
                return alertAndBack(model, colNm + " : " + MessageUtil.getMessage("validator.required"));
            }

            // 파일 확장자 체크
            boolean isExt = false;
            for(FileVO baseFileVo : fileList) {
                String ext = baseFileVo.getFileExtnNm().toLowerCase();
                String strExtList = bbsConfigVo.getPrmsnFileExtnMttr().toLowerCase();
                if( !(strExtList.indexOf(ext) > -1) ) isExt = true;
            }
            if (isExt) { // 허용확장자가 아닌경우 업로드한 파일 삭제
                if(opBbsService.deleteFile(fileList)) {
                    return alertAndBack(model, "허용된 확장자만 첨부할 수 있습니다.");
                } else {
                    return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
                }
            }

            // 폼 업로드시 업로드 파일 용량을 체크한다.
            if(!checkUploadFileSize(fileList, bbsConfigVo)) {
                if(opBbsService.deleteFile(fileList)) {
                    return alertAndBack(model, MessageUtil.getMessage("file.fileSizeOver"));
                } else {
                    return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
                }
            }

            // 썸네일 사용시
            if(bbsConfigVo.getThmbUseYn().equals("Y")) {
                UploadHelper.makeThumbNail(fileList, bbsConfigVo.getWdthSz(), bbsConfigVo.getVrtcSz());
            }

            // 에디터 사용 상태가 아닌 경우 HTML 태그 제거
            if(bbsConfigVo.getUserEdtrUseYn().equals("N")) {
                bbsVo.setDocContsCn(XssUtil.cleanTag(bbsVo.getDocContsCn(), XssUtil.TYPE.ALL));
            }

            // CntnBrwsrNm 정보
            String agent = request.getHeader("User-Agent");
            if(Validate.isNotEmpty(agent)) {
                bbsVo.setCntnBrwsrNm(agent);
            }

            Integer key = opBbsService.insertBbs(bbsVo);
            if(key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        String url = "BD_selectBbsList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), url);
    }

    /**
     * 게시물 수정
     */
    @RequestMapping(value = "ND_updateBbs.do")
    public String updateBbs(HttpServletRequest request, HttpServletResponse response, Model model, BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));

        // 대상 게시판을 못찾은 경우 오류
        if(Validate.isEmpty(bbsConfigVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.invalidParam"));
        }

        // *** 수정데이터 ***
        BbsVO dataVo = opBbsService.selectBbs(bbsVo);
        if(Validate.isEmpty(dataVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.noData"));
        }

        // 인증여부 및 등록자와 일치하는지 확인
        IUserSessVO loginVo = getUserSession();
        if(Validate.isEmpty(loginVo) || !dataVo.getRgtrId().equals(loginVo.getUserId())) {
            return alertAndBack(model, MessageUtil.getMessage("common.needAuth"));
        }

        /*
         * 환경설정에 따른 게시물 입력값 검증
         */
        ValidateResultHolder holder = BbsValidate.validate(bbsConfigVo, bbsVo);
        if(holder.isValid()) {
            if(Validate.isNotEmpty(loginVo)) {
                bbsVo.setMdfrId(loginVo.getUserId());
                bbsVo.setUpdusrNm(loginVo.getUserNm());
                bbsVo.setIpAddr(getIpAddr());
                bbsVo.setUserIdntfNm(loginVo.getUserIdntfNm());
            }

            List<FileVO> fileList = UploadHelper.upload(request, "bbs");
            bbsVo.setFileList(fileList);

            BbsItemCacheVO bbsItemCacheVo = bbsConfigVo.getBbsItemMap().get("file");
            if ("Y".equals(bbsItemCacheVo.getEsntlYn()) && fileList.size() == 0) {
                String colNm = bbsItemCacheVo.getColNm();
                if (Validate.isNotEmpty(bbsItemCacheVo.getScrnNm())) {
                    colNm = bbsItemCacheVo.getScrnNm();
                }
                return alertAndBack(model, colNm + " : " + MessageUtil.getMessage("validator.required"));
            }

            // 파일 확장자 체크
            boolean isExt = false;
            for(FileVO baseFileVo : fileList) {
                String ext = baseFileVo.getFileExtnNm().toLowerCase();
                String strExtList = bbsConfigVo.getPrmsnFileExtnMttr().toLowerCase();
                if( !(strExtList.indexOf(ext) > -1) ) isExt = true;
            }
            if (isExt) { // 허용확장자가 아닌경우 업로드한 파일 삭제
                if(opBbsService.deleteFile(fileList)) {
                    return alertAndBack(model, "허용된 확장자만 첨부할 수 있습니다.");
                } else {
                    return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
                }
            }

            // 폼 업로드시 업로드 파일 용량을 체크한다.
            if(!checkUploadFileSize(fileList, bbsConfigVo)) {
                if(opBbsService.deleteFile(fileList)) {
                    return alertAndBack(model, MessageUtil.getMessage("file.fileSizeOver"));
                } else {
                    return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
                }
            }

            // 썸네일 사용시
            if(bbsConfigVo.getThmbUseYn().equals("Y")) {
                UploadHelper.makeThumbNail(fileList, bbsConfigVo.getWdthSz(), bbsConfigVo.getVrtcSz());
            }

            // 에디터 사용 상태가 아닌 경우 HTML 태그 제거
            if(bbsConfigVo.getUserEdtrUseYn().equals("N")) {
                bbsVo.setDocContsCn(XssUtil.cleanTag(bbsVo.getDocContsCn(), XssUtil.TYPE.ALL));
            }

            Integer key = opBbsService.updateBbs(bbsVo);
            if(key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        String url = "BD_selectBbs.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
    }

    /**
     * 게시판 삭제
     *
     * @param request
     * @param model
     */
    @RequestMapping(value = "ND_deleteBbs.do", method = RequestMethod.POST)
    public String deleteBbs(HttpServletRequest request, Model model, BbsVO bbsVo) {

        BbsVO dataVo = opBbsService.selectBbs(bbsVo);
        if(Validate.isEmpty(dataVo)) {
            return alertAndBack(model, MessageUtil.getMessage("common.noData"));
        }

        // 인증여부 및 등록자와 일치하는지 확인
        IUserSessVO loginVo = getUserSession();
        if(Validate.isEmpty(loginVo) || !dataVo.getRgtrId().equals(loginVo.getUserId())) {
            return alertAndBack(model, MessageUtil.getMessage("common.needAuth"));
        }

        Integer cnt = opBbsService.deleteBbs(bbsVo);
        if(cnt != 1) {
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }

        String url = "BD_selectBbsList.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
    }

    /**
     * 게시판 RSS 목록
     *
     * @param request
     * @param model
     */
    @RequestMapping(value = "ND_selectRssList.do")
    public String selectRssList(HttpServletRequest request, Model model, BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));
        FeedVO feedVo = new FeedVO();

        // 대상 게시판을 못찾은 경우 빈 목록 반환
        if(Validate.isEmpty(bbsConfigVo)) {
            return responseRSS(model, feedVo);
        }

        // FEED 사용여부가 Y가 아니면 빈 목록 반환
        if(!bbsConfigVo.getNfeedUseYn().equals("Y")) {
            return responseRSS(model, feedVo);
        }

        String baseLink = makeFeedDataUrl(request, bbsConfigVo, bbsVo);
        feedVo.setLink(baseLink);
        feedVo.setTitle(bbsConfigVo.getBbsNm());
        feedVo.setDescription(bbsConfigVo.getBbsExpln());

        // 공개여부/삭제여부 등 외부 미공개 데이터는 제외 한 100개 목록
        bbsVo.addParam("q_pagingStartNum", 1);
        bbsVo.addParam("q_pagingEndNum", 100);

        List<FeedVO> dataList = opBbsService.selectFeedList(bbsVo);
        feedVo.setItemList(dataList);

        // 각각의 게시물 link 생성
        for(FeedVO dataVo : dataList) {
            dataVo.setLink(baseLink + "&q_bbsDocNo=" + dataVo.getParam1());
        }

        return responseRSS(model, feedVo);
    }

    /**
     * 게시판 ATOM 목록
     *
     * @param request
     * @param model
     */
    @RequestMapping(value = "ND_selectAtomList.do")
    public String selectAtomList(HttpServletRequest request, Model model, BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));
        FeedVO feedVo = new FeedVO();

        // 대상 게시판을 못찾은 경우 빈 목록 반환
        if(Validate.isEmpty(bbsConfigVo)) {
            return responseAtom(model, feedVo);
        }
        // FEED 사용여부가 Y가 아니면 빈 목록 반환
        if(!bbsConfigVo.getNfeedUseYn().equals("Y")) {
            return responseAtom(model, feedVo);
        }

        String baseLink = makeFeedDataUrl(request, bbsConfigVo, bbsVo);
        feedVo.setLink(baseLink);
        feedVo.setTitle(bbsConfigVo.getBbsNm());
        feedVo.setDescription(bbsConfigVo.getBbsExpln());

        // 공개여부/삭제여부 등 외부 미공개 데이터는 제외 한 100개 목록
        bbsVo.addParam("q_pagingStartNum", 1);
        bbsVo.addParam("q_pagingEndNum", 100);

        List<FeedVO> dataList = opBbsService.selectFeedList(bbsVo);
        feedVo.setItemList(dataList);

        // 각각의 게시물 link 생성
        for(FeedVO dataVo : dataList) {
            dataVo.setLink(baseLink + "&q_bbsDocNo=" + dataVo.getParam1());
        }

        return responseAtom(model, feedVo);
    }

    /**
     * FEED Link용 기본 URL을 생성
     *
     * @param request
     * @param bbsConfigVo
     * @param bbsVo
     * @return
     */
    private String makeFeedDataUrl(HttpServletRequest request, BbsConfigCacheVO bbsConfigVo, BbsVO bbsVo) {

        StringBuilder link = new StringBuilder();
        if(request.isSecure()) {
            link.append("https");
        } else {
            link.append("http");
        }
        link.append("://").append(request.getServerName()).append(":").append(request.getServerPort());
        link.append("/user/bbs/BD_selectBbs.do?");
        link.append("q_bbsSn=").append(bbsConfigVo.getBbsSn());

        String clsfNo = bbsVo.getString("q_clsfNo");
        if(Validate.isNotEmpty(clsfNo)) {
            link.append("&q_clsfNo=").append(clsfNo);
            String lwrkClsfSn = bbsVo.getString("q_lwrkClsfSn");
            if(Validate.isNotEmpty(lwrkClsfSn)) {
                link.append("&q_lwrkClsfSn=").append(lwrkClsfSn);
            }
        }
        return link.toString();
    }

    /**
     * FEED URL을 생성
     *
     * @param request
     * @param bbsConfigVo
     * @param bbsVo
     * @return
     */
    private String makeFeedUrl(HttpServletRequest request, BbsConfigCacheVO bbsConfigVo, BbsVO bbsVo, String feedName) {

        StringBuilder link = new StringBuilder();
        if(request.isSecure()) {
            link.append("https");
        } else {
            link.append("http");
        }
        link.append("://").append(request.getServerName()).append(":").append(request.getServerPort());
        link.append("/user/bbs/");
        link.append("ND_select").append(feedName).append("List.do?");
        link.append("q_bbsSn=").append(bbsConfigVo.getBbsSn());

        String clsfNo = bbsVo.getString("q_clsfNo");
        if(Validate.isNotEmpty(clsfNo)) {
            link.append("&amp;q_clsfNo=").append(clsfNo);
            String lwrkClsfSn = bbsVo.getString("q_lwrkClsfSn");
            if(Validate.isNotEmpty(lwrkClsfSn)) {
                link.append("&amp;q_lwrkClsfSn=").append(lwrkClsfSn);
            }
        }
        return link.toString();
    }

    /**
     * 하위 카테고리 목록
     */
    @RequestMapping(value = "selectLwrkClsfSn.do")
    public String selectLwrkClsfSn(HttpServletRequest request, HttpServletResponse response, Model model, BbsCtgryVO bbsCtgryVo) {

        BbsConfigCacheVO bbsConfigCacheVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsCtgryVo.getParam("q_bbsSn"));

        if(Validate.isEmpty(bbsConfigCacheVo)) {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.invalidParam"));
        }

        List<BbsCtgryCacheVO> lwprtCtgryList = new ArrayList<BbsCtgryCacheVO>();
        Integer clsfNo = bbsCtgryVo.getInteger("q_clsfNo");

        List<BbsCtgryCacheVO> ctgryList = bbsConfigCacheVo.getBbsCtgryList();
        if(Validate.isNotEmpty(ctgryList)) {
            for(BbsCtgryCacheVO bbsCtgryCacheVo : ctgryList) {
                if(bbsCtgryCacheVo.getClsfNo().equals(clsfNo)) {
                    lwprtCtgryList = bbsCtgryCacheVo.getLwprtCtgryList();
                }
            }
        }

        return responseJson(model, Boolean.TRUE, lwprtCtgryList);
    }

    // 폼 업로드시 업로드 파일 용량을 체크한다.
    private boolean checkUploadFileSize(List<FileVO> fileList, BbsConfigCacheVO bbsconfigVo) {

        Long allFileSize = 0L;
        boolean uploadAt = true;

        // 업로드 파일 사이즈 제한이 있는 경우(사이즈 0일 경우 무제한 용량)
        for(FileVO baseFileVo : fileList) {
            try {
                Long fileSzNm = baseFileVo.getByteFileSz();

                if(bbsconfigVo.getLmtFileSz() > 0) {
                    Long lmtFileSz = Long.valueOf(((Integer) (bbsconfigVo.getLmtFileSz() * 1024 * 1024)).toString());
                    if(fileSzNm > lmtFileSz) {
                        uploadAt = false;
                    }
                }
                allFileSize += fileSzNm;
            } catch (Exception e) {
                uploadAt = false;
            }
        }

        // 업로드 총 파일 사이즈 제한이 있는 경우
        if(bbsconfigVo.getWholUldSz() > 0) {
            Long allMxmmFileSize = Long.valueOf(((Integer) (bbsconfigVo.getWholUldSz() * 1024 * 1024)).toString());
            if(allFileSize > allMxmmFileSize) {
                return false;
            }
        }

        return uploadAt;
    }
    /**
     * 페이징 정보가 없는 경우 자동 설정
     * 기본은 프레임워크에서 하지만, 최초 페이지를 열때 지정된 페이징 수가 없는 경우에 한하여 적용
     *
     * @param bbsVo
     */
    private void setPageParam(BbsVO bbsVo) {
    	
        Map<String, Object> paramMap = bbsVo.getParamMap();

        int currPage = bbsVo.getInteger(BaseConfig.PREFIX_SEARCH_PARAM + "currPage");
        int rowPerPage = bbsVo.getInteger(BaseConfig.PREFIX_SEARCH_PARAM + "rowPerPage");
        int iStartNum = Pager.getStartNum(currPage, rowPerPage) + ParamArgumentResolver.addStartNum;
        int iEndNum = Pager.getEndNum(iStartNum, rowPerPage);

        paramMap.put(BaseConfig.PREFIX_SEARCH_PARAM + "rowPerPage", rowPerPage);
        paramMap.put(BaseConfig.PREFIX_SEARCH_PARAM + "pagingStartNum", Integer.valueOf(iStartNum));
        paramMap.put(BaseConfig.PREFIX_SEARCH_PARAM + "pagingEndNum", Integer.valueOf(iEndNum));
    }
}
