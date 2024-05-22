/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.samples.crud;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import zesinc.component.file.support.UploadHelper;
import zesinc.core.lang.Validate;
import zesinc.login.domain.UserLoginVO;
import zesinc.samples.crud.domain.CrudReplyVO;
import zesinc.samples.crud.domain.CrudVO;
import zesinc.web.spring.controller.UserController;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.helper.CaptChaHelper;
import zesinc.web.support.helper.OpHelper;
import zesinc.web.utils.MessageUtil;
import zesinc.web.validate.ValidateResultHolder;
import zesinc.web.validate.ValidateUtil;
import zesinc.web.vo.IUserSessVO;

/**
 * CRUD 셈플 프로그램 Controller 클레스
 * 
 * @author (주)제스아이엔씨 기술연구소
 * 
 *         <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 1. 11.    방기배   최초작성
 * </pre>
 * @see
 */
@Controller("CRUD 셈플")
@RequestMapping(value = { "/samples/crud" })
public class CrudController extends UserController {

    @Resource(name = "opCrudService")
    private CrudService opCrudService;

    /**
     * 상세
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = "BD_crud.view.do")
    public void selectCrud(HttpServletRequest request, Model model, CrudVO crudVo,
        CrudReplyVO crudReplyVo) {

        // 원본글 상세
        model.addAttribute(BaseConfig.KEY_BASE_VO, opCrudService.selectCrud(crudVo));

        // 답글 목록
        crudReplyVo.setParamMap(crudVo.getParamMap());
        model.addAttribute(BaseConfig.KEY_DATA_LIST, opCrudService.selectCrudReplyList(crudReplyVo));
    }

    /**
     * 목록
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = "BD_crud.list.do")
    public void selectCrudList(HttpServletRequest request, Model model, CrudVO crudVo) {

        model.addAttribute(BaseConfig.KEY_PAGER, opCrudService.selectCrudList(crudVo));
    }

    /**
     * 입력 폼
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = { "BD_crud.insert.form.do", "BD_crud.update.form.do" })
    public void insertCrudForm(HttpServletRequest request, Model model, CrudVO crudVo) {

        // 스크립트단에 동일한 validator를 설정할 수 있도록 한다.
        ValidateResultHolder holder = ValidateUtil.validate(crudVo);
        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);

        if(Validate.isNotEmpty(crudVo.getParamMap().get("q_regSn"))) {
            model.addAttribute(BaseConfig.KEY_DATA_VO, opCrudService.selectCrud(crudVo));
        }
    }

    /**
     * 등록
     * 
     * @param request
     * @param model
     * @throws Exception
     */
    @RequestMapping(value = "ND_crud.insert.do", method = RequestMethod.POST)
    public String insertCrud(HttpServletRequest request, Model model, CrudVO crudVo)
        throws Exception {

        boolean valid = CaptChaHelper.isValid(request);
        if(!valid) {
            return alertAndBack(model, "Captcha faild");
        }

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(crudVo);
        if(holder.isValid()) {

            UserLoginVO loginVo = (UserLoginVO) getUserSession();
            if(loginVo == null) {
                loginVo = new UserLoginVO();
            }
            crudVo.setRgtrId(loginVo.getUserId());
            crudVo.setRgtrNm(loginVo.getUserNm());
            crudVo.setIpAddr(getIpAddr());

            crudVo.setFileList(UploadHelper.upload(request, "crud"));
            // 등록 실행
            Integer key = opCrudService.insertCrud(crudVo);
            if(key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        String url = "BD_crud.list.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), url);
    }

    /**
     * 수정
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = "ND_crud.update.do", method = RequestMethod.POST)
    public String updateCrud(HttpServletRequest request, Model model, CrudVO crudVo) {

        // 검증
        ValidateResultHolder holder = ValidateUtil.validate(crudVo);
        if(holder.isValid()) {

            UserLoginVO loginVo = (UserLoginVO) getUserSession();
            if(loginVo == null) {
                loginVo = new UserLoginVO();
            }
            crudVo.setRgtrId(loginVo.getUserId());
            crudVo.setRgtrNm(loginVo.getUserNm());
            crudVo.setIpAddr(getIpAddr());

            crudVo.setFileList(UploadHelper.upload(request, "crud"));

            // 등록 실행
            Integer key = opCrudService.updateCrud(crudVo);
            if(key != 1) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }

        String url = "BD_crud.view.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
    }

    /**
     * 삭제
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = "ND_crud.delete.do", method = RequestMethod.POST)
    public String deleteCrud(HttpServletRequest request, Model model, CrudVO crudVo) {

        Integer cnt = opCrudService.deleteCrud(crudVo);
        if(cnt != 1) {
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }

        String url = "BD_crud.list.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
    }

    /**
     * 목록 삭제
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = "ND_crud.list.delete.do", method = RequestMethod.POST)
    public String deleteCrudList(HttpServletRequest request, Model model, CrudVO crudVo) {

        Integer cnt = opCrudService.deleteCrudList(crudVo);
        if(cnt <= 0) {
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }

        String url = "BD_crud.list.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
    }

    /**
     * 답변 폼
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = { "BD_crud.reply.insert.form.do", "BD_crud.reply.update.form.do" })
    public String insertCrudReplyForm(HttpServletRequest request, Model model, CrudVO crudVo,
        CrudReplyVO crudReplyVo) {

        ValidateResultHolder holder = ValidateUtil.validate(crudReplyVo);

        model.addAttribute(BaseConfig.KEY_VALIDATE, holder);
        model.addAttribute(BaseConfig.KEY_BASE_VO, opCrudService.selectCrud(crudVo));

        if(Validate.isNotEmpty(crudReplyVo.getParamMap().get("q_replySeq"))) {
            model.addAttribute(BaseConfig.KEY_DATA_VO, opCrudService.selectCrudReply(crudReplyVo));
        }

        return "samples/crud/BD_crud.reply.form";
    }

    /**
     * 답변 등록
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = "ND_crud.reply.insert.do", method = RequestMethod.POST)
    public String insertCrudReply(HttpServletRequest request, Model model, CrudReplyVO crudReplyVo) {

        ValidateResultHolder holder = ValidateUtil.validate(crudReplyVo);
        if(holder.isValid()) {

            IUserSessVO loginVo = (IUserSessVO) getUserSession();

            crudReplyVo.setRgtrId(loginVo.getUserId());
            crudReplyVo.setRgtrNm(loginVo.getUserNm());
            crudReplyVo.setIpAddr(getIpAddr());

            crudReplyVo.setFileList(UploadHelper.upload(request, "crud"));
            // 등록 실행
            Object key = opCrudService.insertCrudReply(crudReplyVo);
            if(Validate.isEmpty(key)) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }
        String url = "BD_crud.list.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.insertOk"), url);
    }

    /**
     * 답변 수정
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = "ND_crud.reply.update.do", method = RequestMethod.POST)
    public String updateCrudReply(HttpServletRequest request, Model model,
        CrudReplyVO crudReplyVo) {

        IUserSessVO loginVo = (IUserSessVO) getUserSession();

        crudReplyVo.setMdfrId(loginVo.getUserId());
        crudReplyVo.setUpdusrNm(loginVo.getUserNm());
        crudReplyVo.setIpAddr(getIpAddr());

        ValidateResultHolder holder = ValidateUtil.validate(crudReplyVo);
        if(holder.isValid()) {

            crudReplyVo.setFileList(UploadHelper.upload(request, "crud"));
            // 수정 실행
            Integer key = opCrudService.updateCrudReply(crudReplyVo);

            if(Validate.isEmpty(key)) {
                return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
            }
        } else {
            return alertAndBack(model, MessageUtil.getMessage("common.validateFail"));
        }
        String url = "BD_crud.list.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.updateOk"), url);
    }

    /**
     * 답변 삭제
     * 
     * @param request
     * @param model
     */
    @RequestMapping(value = "ND_crud.reply.delete.do", method = RequestMethod.POST)
    public String deleteCrudReply(HttpServletRequest request, Model model,
        CrudReplyVO crudReplyVo) {

        Integer key = opCrudService.deleteCrudReply(crudReplyVo);
        if(Validate.isEmpty(key)) {
            return alertAndBack(model, MessageUtil.getMessage("common.processFail"));
        }

        String url = "BD_crud.list.do?" + OpHelper.getSearchQueryString(request);
        return alertAndRedirect(model, MessageUtil.getMessage("common.deleteOk"), url);
    }

    /**
     * 엑셀 불러오기 기능 샘플 페이지
     * 
     * @return
     */
    @RequestMapping(value = "PD_excelRegistSample.do")
    public void sendForExcelSample(HttpServletRequest request, Model model) {

    }

}
