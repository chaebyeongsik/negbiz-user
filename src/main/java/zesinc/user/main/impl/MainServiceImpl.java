/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.main.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.component.file.FileService;
import zesinc.core.cache.CacheService;
import zesinc.core.utils.StringUtil;
import zesinc.user.form.domain.FormInfoVO;
import zesinc.user.main.MainMapper;
import zesinc.user.main.MainService;
import zesinc.user.main.domain.MainBbsVO;
import zesinc.web.support.BaseConfig;
import zesinc.web.utils.PrhibtUtil;
import zesinc.web.vo.BaseVO;
import zesinc.web.vo.cache.BbsConfigCacheVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 메인 홈페이지용 서비스 구현 객체
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2016. 4. 7.    yesno   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Service("opUserMainService")
public class MainServiceImpl extends EgovAbstractServiceImpl implements MainService {

    @Resource(name = "opUserMainDao")
    private MainMapper opUserMainDao;
    // 첨부파일
    @Resource(name = "opFileService")
    private FileService opFileService;

    @Override
    public List<MainBbsVO> selectMainBbsList(BaseVO baseVo) {

        BbsConfigCacheVO bbsConfigVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + baseVo.getParam("q_bbsSn"));

        List<MainBbsVO> dataList = opUserMainDao.selectMainBbsList(baseVo);

        for(MainBbsVO dataVo : dataList) {
            // 제목 길이 조정
            dataVo.setTtl(StringUtil.fixUnicodeLength(dataVo.getTtl(), 18, "..."));
            // 첨부파일
            if(dataVo.getFileSn() > -1) {
                dataVo.setFileList(opFileService.selectFileList(dataVo.getFileSn()));
            }

            // 금지단어 마스킹
            if(bbsConfigVo.getPhbwdUseYn().equals("Y")) {
                String ttl = PrhibtUtil.maskPrhibtWrd(bbsConfigVo.getPhbwdCdId(), dataVo.getTtl());
                dataVo.setTtl(ttl);
            }
        }

        return dataList;
    }

    @Override
    public List<FormInfoVO> selectMainFormList(BaseVO baseVo) {


        List<FormInfoVO> dataList = opUserMainDao.selectMainFormList(baseVo);

        for(FormInfoVO dataVo : dataList) {
            // 제목 길이 조정
            dataVo.setFormTtl(StringUtil.fixUnicodeLength(dataVo.getFormTtl(), 18, "..."));
            // 첨부파일
            if(dataVo.getFileSn() != null) {
                dataVo.setFileList(opFileService.selectFileList(dataVo.getFileSn()));
            }

        }

        return dataList;
    }
}
