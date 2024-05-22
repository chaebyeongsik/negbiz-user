/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.cms.preview.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.user.cms.preview.CmsPreviewMapper;
import zesinc.user.cms.preview.CmsPreviewService;
import zesinc.user.cms.preview.domain.CmsPreviewVO;
import zesinc.web.vo.cache.CmsCacheVO;
import zesinc.web.vo.cache.CmsMngrCacheVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 컨텐츠 및 레이아웃 미리보기
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016. 3. 11.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 */
@Service("opUserCmsPreviewService")
public class CmsPreviewServiceImpl extends EgovAbstractServiceImpl implements CmsPreviewService {

    @Resource(name = "opUserCmsPreviewDao")
    private CmsPreviewMapper opUserCmsPreviewDao;

    @Override
    public CmsPreviewVO selectUserMenuData(CmsPreviewVO cmsPreviewVo) {

        return opUserCmsPreviewDao.selectUserMenuData(cmsPreviewVo);
    }

    @Override
    public CmsCacheVO selectUserMenuPreview(CmsPreviewVO cmsPreviewVo) {

        CmsCacheVO dataVo = opUserCmsPreviewDao.selectUserMenuPreview(cmsPreviewVo);

        // 표시담당자 설정에 따라 담당자 정보를 설정
        if(Validate.isNotEmpty(dataVo) && dataVo.getPicIndctYn().equals("Y")) {
            CmsMngrCacheVO mngrVo = opUserCmsPreviewDao.selectUserMenuMngr(dataVo);
            if(Validate.isEmpty(mngrVo)) {
                mngrVo = new CmsMngrCacheVO();
            }
            dataVo.setIndictMngr(mngrVo);
        }

        return dataVo;
    }

}
