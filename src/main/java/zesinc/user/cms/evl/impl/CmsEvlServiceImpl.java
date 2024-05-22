/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.cms.evl.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.user.cms.evl.CmsEvlMapper;
import zesinc.user.cms.evl.CmsEvlService;
import zesinc.user.cms.evl.domain.CmsEvlVO;
import zesinc.web.utils.DateFormatUtil;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 사용자메뉴평가 정보 서비스 구현 클레스
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

@Service("opUserCmsEvlService")
public class CmsEvlServiceImpl extends EgovAbstractServiceImpl implements CmsEvlService {

    @Resource(name = "opUserCmsEvlDao")
    private CmsEvlMapper opUserCmsEvlDao;

    @Override
    public Integer insertCmsEvl(CmsEvlVO cmsEvlVo) {
        Integer insertCnt = 0;

        Integer detlEvlCnt = opUserCmsEvlDao.selectCmsEvlDetl(cmsEvlVo);
        if(detlEvlCnt < 1) {
            String toDay = DateFormatUtil.getTodayShort();
            cmsEvlVo.setRegYmd(toDay);

            CmsEvlVO dataVo = opUserCmsEvlDao.selectCmsEvl(cmsEvlVo);
            if(Validate.isEmpty(dataVo)) {
                opUserCmsEvlDao.insertCmsEvl(cmsEvlVo);
            } else {
                opUserCmsEvlDao.updateCmsEvl(cmsEvlVo);
            }
            insertCnt += opUserCmsEvlDao.insertCmsEvlDetl(cmsEvlVo);
        }

        return insertCnt;
    }

}
