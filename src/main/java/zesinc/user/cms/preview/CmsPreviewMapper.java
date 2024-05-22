/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.cms.preview;

import zesinc.user.cms.preview.domain.CmsPreviewVO;
import zesinc.web.vo.cache.CmsCacheVO;
import zesinc.web.vo.cache.CmsMngrCacheVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

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
 * @see
 */
@Mapper("opUserCmsPreviewDao")
public interface CmsPreviewMapper {

    /**
     * 사용자메뉴컨텐츠 상세조회
     * 
     * @param cmsPreviewVo
     * @return
     */
    CmsPreviewVO selectUserMenuData(CmsPreviewVO cmsPreviewVo);

    /**
     * 사용자메뉴 레이아웃 정보
     * 
     * @param cmsPreviewVo
     * @return
     */
    CmsCacheVO selectUserMenuPreview(CmsPreviewVO cmsPreviewVo);

    /**
     * 사용자메뉴 담당자 정보
     * 
     * @param cmsPreviewVo
     * @return
     */
    CmsMngrCacheVO selectUserMenuMngr(CmsCacheVO cmsCacheVo);
}
