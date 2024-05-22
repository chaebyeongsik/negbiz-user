/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.cms.evl;

import zesinc.user.cms.evl.domain.CmsEvlVO;

/**
 * 사용자메뉴평가 정보 서비스 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-06-09.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
public interface CmsEvlService {

    /**
     * 사용자메뉴평가 등록
     * 
     * @param cmsEvlVo
     * @return
     */
    Integer insertCmsEvl(CmsEvlVO cmsEvlVo);

}
