/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.banner.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.user.banner.BannerMapper;
import zesinc.user.banner.BannerService;
import zesinc.user.banner.domain.BannerVO;
import zesinc.web.vo.BaseVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 배너 정보 서비스 구현 클레스
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *    
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-07-19.    방기배   최초작성
 * </pre>
 * 
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opUserBannerService")
public class BannerServiceImpl extends EgovAbstractServiceImpl implements BannerService {

    @Resource(name = "opUserBannerDao")
    private BannerMapper opUserBannerDao;

    @Override
    public List<BannerVO> selectBannerList(BaseVO paramVo) {

        List<BannerVO> dataList = opUserBannerDao.selectBannerList(paramVo);

        return dataList;
    }
}
