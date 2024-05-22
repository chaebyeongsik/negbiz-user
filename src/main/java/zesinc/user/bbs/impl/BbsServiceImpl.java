/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.bbs.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import zesinc.component.file.FileService;
import zesinc.component.file.domain.FileVO;
import zesinc.core.cache.CacheService;
import zesinc.core.crypto.Crypto;
import zesinc.core.crypto.CryptoFactory;
import zesinc.core.crypto.CryptoFactory.EncryptType;
import zesinc.core.lang.Validate;
import zesinc.core.utils.StringUtil;
import zesinc.user.bbs.BbsCmntMapper;
import zesinc.user.bbs.BbsMapper;
import zesinc.user.bbs.BbsService;
import zesinc.user.bbs.domain.BbsCmntVO;
import zesinc.user.bbs.domain.BbsTagVO;
import zesinc.user.bbs.domain.BbsVO;
import zesinc.user.bbs.support.BbsUtil;
import zesinc.web.support.BaseConfig;
import zesinc.web.support.pager.Pager;
import zesinc.web.utils.Converter;
import zesinc.web.utils.DateFormatUtil;
import zesinc.web.utils.FileUtil;
import zesinc.web.utils.PrhibtUtil;
import zesinc.web.vo.FeedVO;
import zesinc.web.vo.cache.BbsConfigCacheVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 게시판 정보 서비스 구현 클레스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015-07-16.    황신욱   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */

@Service("opBbsService")
public class BbsServiceImpl extends EgovAbstractServiceImpl implements BbsService {

    @Resource(name = "opBbsDao")
    private BbsMapper opBbsDao;

    // 댓글
    @Resource(name = "opBbsCmntDao")
    private BbsCmntMapper opBbsCmntDao;

    // 첨부파일
    @Resource(name = "opFileService")
    private FileService opFileService;

    @Override
    public Pager<BbsVO> selectBbsPageList(BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));

        bbsVo.addParam("ntcPstYn", "N");
        List<BbsVO> dataList = opBbsDao.selectBbsList(bbsVo);
        Integer totalNum = opBbsDao.selectBbsListCount(bbsVo);

        for(BbsVO dataVo : dataList) {
            // 평점
            int dgstfnSumScr = dataVo.getDgstfnSumScr();
            int dgstfnEvlCnt = dataVo.getDgstfnEvlCnt();
            if(dgstfnEvlCnt == 0) {
                dgstfnEvlCnt = 1;
            }
            float stsfdg_evl_avrg = dgstfnSumScr / dgstfnEvlCnt;
            dataVo.setStsfdgEvlAvrg(stsfdg_evl_avrg);

            // 첨부파일
            if(dataVo.getFileSn() > -1) {
                dataVo.setFileList(opFileService.selectFileList(dataVo.getFileSn()));
            }

            // 금지단어 마스킹
            if(bbsConfigVo.getPhbwdUseYn().equals("Y")) {
                String docContsCn = PrhibtUtil.maskPrhibtWrd(bbsConfigVo.getPhbwdCdId(), dataVo.getDocContsCn());
                String ttl = PrhibtUtil.maskPrhibtWrd(bbsConfigVo.getPhbwdCdId(), dataVo.getTtl());
                dataVo.setDocContsCn(docContsCn);
                dataVo.setTtl(ttl);
            }

            // 태그를 가져온다.
            List<String> tagList = new ArrayList<String>();
            if(Validate.isNotEmpty(dataVo)) {
                tagList = opBbsDao.selectBbsTagList(dataVo);
            }
            if(Validate.isNotEmpty(tagList)) {
                dataVo.setTagNms(StringUtils.join(tagList, ","));
            }
        }

        return new Pager<BbsVO>(dataList, bbsVo, totalNum);
    }

    @Override
    public List<BbsVO> selectBbsNoticeList(BbsVO bbsVo) {

        BbsConfigCacheVO bbsConfigVo = (BbsConfigCacheVO) CacheService.get(BaseConfig.BBS_CONFIG_CACHE_KEY + bbsVo.getParam("q_bbsSn"));

        bbsVo.addParam("ntcPstYn", "Y");
        List<BbsVO> dataList = opBbsDao.selectBbsNoticeList(bbsVo);

        for(BbsVO dataVo : dataList) {
            // 평점
            int dgstfnSumScr = dataVo.getDgstfnSumScr();
            int dgstfnEvlCnt = dataVo.getDgstfnEvlCnt();
            if(dgstfnEvlCnt == 0) {
                dgstfnEvlCnt = 1;
            }
            float stsfdg_evl_avrg = dgstfnSumScr / dgstfnEvlCnt;
            dataVo.setStsfdgEvlAvrg(stsfdg_evl_avrg);

            // 첨부파일
            if(dataVo.getFileSn() > -1) {
                dataVo.setFileList(opFileService.selectFileList(dataVo.getFileSn()));
            }

            // 금지단어 마스킹
            if(bbsConfigVo.getPhbwdUseYn().equals("Y")) {
                String docContsCn = PrhibtUtil.maskPrhibtWrd(bbsConfigVo.getPhbwdCdId(), dataVo.getDocContsCn());
                String ttl = PrhibtUtil.maskPrhibtWrd(bbsConfigVo.getPhbwdCdId(), dataVo.getTtl());
                dataVo.setDocContsCn(docContsCn);
                dataVo.setTtl(ttl);
            }

            // 태그를 가져온다.
            List<String> tagList = new ArrayList<String>();
            if(Validate.isNotEmpty(dataVo)) {
                tagList = opBbsDao.selectBbsTagList(dataVo);
            }
            if(Validate.isNotEmpty(tagList)) {
                dataVo.setTagNms(StringUtils.join(tagList, ","));
            }
        }

        return dataList;
    }

    @Override
    public BbsVO selectBbs(BbsVO bbsVo) {

        BbsVO dataVo = opBbsDao.selectBbs(bbsVo);

        if(Validate.isNotEmpty(dataVo)) {
            // 삭제 처리된 게시물은 그냥 리턴
            if(dataVo.getDelYn().equals("Y") || dataVo.getMngrDelYn().equals("Y")) {
                return dataVo;
            }

            // 공지가 아닌경우에만 이전 다음글 표시
            if(!dataVo.getNtcPstYn().equals("Y")) {
                dataVo.setPrevVo(opBbsDao.selectBbsPrevView(bbsVo));
                dataVo.setNextVo(opBbsDao.selectBbsNextView(bbsVo));
            }

            // 첨부파일
            if(dataVo.getFileSn() > -1) {
                dataVo.setFileList(opFileService.selectFileList(dataVo.getFileSn()));
            }

            // 평점
            int dgstfnSumScr = dataVo.getDgstfnSumScr();
            int dgstfnEvlCnt = dataVo.getDgstfnEvlCnt();
            if(dgstfnEvlCnt == 0) {
                dgstfnEvlCnt = 1;
            }
            float stsfdg_evl_avrg = dgstfnSumScr / dgstfnEvlCnt;
            dataVo.setStsfdgEvlAvrg(stsfdg_evl_avrg);

            // 태그를 가져온다.
            List<String> tagList = new ArrayList<String>();
            if(Validate.isNotEmpty(dataVo)) {
                tagList = opBbsDao.selectBbsTagList(dataVo);
            }
            if(Validate.isNotEmpty(tagList)) {
                dataVo.setTagNms(StringUtils.join(tagList, ","));
            }
        }

        return dataVo;
    }

    @Override
    public Integer insertBbs(BbsVO bbsVo) {

        Integer result = 0;

        if(Validate.isNotEmpty(bbsVo.getFileList())) {
            // 첨부파일
            Integer fileSn = opFileService.insertFileList(bbsVo.getFileList());
            bbsVo.setFileSn(fileSn);
        }

        bbsVo.setTtl(Converter.cleanHtml(bbsVo.getTtl()));
        bbsVo.setMainCn(StringUtil.fixUnicodeLength(Converter.cleanHtml(bbsVo.getDocContsCn()), 60, "..."));
        if(Validate.isNotEmpty(bbsVo.getPswd())) {
            // 암호화
            Crypto cry = CryptoFactory.getInstance(EncryptType.SHA256);
            bbsVo.setPswd(cry.encrypt(bbsVo.getPswd()));
        }

        String date = DateFormatUtil.getTodayMsec();
        if(Validate.isNotEmpty(bbsVo.getBbsDocNo())) {
            // 답글 작성시
            BbsVO dataVo = selectBbs(bbsVo);
            bbsVo.setBbsDocNo(date);
            bbsVo.setRfrncDocNo(dataVo.getRfrncDocNo());
            bbsVo.setSortSn(dataVo.getSortSn() + 1);
            bbsVo.setOpnnGrdSn(dataVo.getOpnnGrdSn() + 1);

            // 정렬순 증가
            opBbsDao.updateBbsSortSn(bbsVo);
        } else {
            // 신규 등록시
            bbsVo.setBbsDocNo(date);
            bbsVo.setSortSn(0);
            bbsVo.setOpnnGrdSn(0);
            bbsVo.setRfrncDocNo(date);
        }

        // 저작권 내용 생성
        if(bbsVo.getCprgtUseYn().equals("Y")) {
            bbsVo.setAutTypeNm("USER");
            bbsVo.setCprgtTypeNm(BbsUtil.makeCprgtTypeNm(bbsVo));
            bbsVo.setCprgtCn(BbsUtil.makeCprgtCn(bbsVo));
        }

        result += opBbsDao.insertBbs(bbsVo);

        if(result > 0) {
            if(Validate.isNotEmpty(bbsVo.getTagNms())) {
                String[] tagNms = bbsVo.getTagNms().split(",");
                opBbsDao.deleteBbsTag(bbsVo);
                for(String tagNm : tagNms) {
                    BbsTagVO bbsTagVo = new BbsTagVO(bbsVo.getBbsSn(), bbsVo.getBbsDocNo(), StringUtils.trim(tagNm));
                    opBbsDao.insertBbsTag(bbsTagVo);
                }
            }
        }

        return result;
    }

    @Override
    public Integer updateBbs(BbsVO bbsVo) {

        Integer result = 0;

        BbsVO dataVo = selectBbs(bbsVo);
        // 삭제처리 파일
        if(Validate.isNotEmpty(bbsVo.getFileIds())) {
            opFileService.deleteFiles(dataVo.getFileSn(), bbsVo.getFileIds());
        }
        if(Validate.isNotEmpty(bbsVo.getFileList())) {
            // 첨부파일
            Integer dataFileSn = dataVo.getFileSn() == -1 ? null : dataVo.getFileSn();
            Integer fileSn = opFileService.insertFileList(dataFileSn, bbsVo.getFileList());
            bbsVo.setFileSn(fileSn);
        } else {
            bbsVo.setFileSn(dataVo.getFileSn());
        }

        bbsVo.setTtl(Converter.cleanHtml(bbsVo.getTtl()));
        bbsVo.setMainCn(StringUtil.fixUnicodeLength(Converter.cleanHtml(bbsVo.getDocContsCn()), 60, "..."));

        // 저작권 내용 생성
        if(bbsVo.getCprgtUseYn().equals("Y")) {
            bbsVo.setAutTypeNm("USER");
            bbsVo.setCprgtTypeNm(BbsUtil.makeCprgtTypeNm(bbsVo));
            bbsVo.setCprgtCn(BbsUtil.makeCprgtCn(bbsVo));
        }

        result += opBbsDao.updateBbs(bbsVo);

        if(result > 0) {
            if(Validate.isNotEmpty(bbsVo.getTagNms())) {
                bbsVo.setBbsSn(Integer.parseInt((String) bbsVo.getParam("q_bbsSn")));
                bbsVo.setBbsDocNo((String) bbsVo.getParam("q_bbsDocNo"));
                opBbsDao.deleteBbsTag(bbsVo);
                String[] tagNms = bbsVo.getTagNms().split(",");
                for(String tagNm : tagNms) {
                    BbsTagVO bbsTagVo = new BbsTagVO(bbsVo.getBbsSn(), bbsVo.getBbsDocNo(), StringUtils.trim(tagNm));
                    opBbsDao.insertBbsTag(bbsTagVo);
                }
            }
        }

        return result;
    }

    @Override
    public Integer deleteBbs(BbsVO bbsVo) {

        int delCnt = 0;

        // 원본 삭제
        BbsVO dataVo = selectBbs(bbsVo);

        if(Validate.isNotEmpty(dataVo)) {
            dataVo.setParamMap(bbsVo.getParamMap());

            // 삭제 대상글을 참조하는 자식글 존재여부 확인
            List<BbsVO> dataList = opBbsDao.selectRefrnSnBbsList(bbsVo);

            // 댓글 삭제
            BbsCmntVO bbsCmntVo = new BbsCmntVO();
            bbsCmntVo.setBbsSn(dataVo.getBbsSn());
            bbsCmntVo.setBbsDocNo(dataVo.getBbsDocNo());
            opBbsCmntDao.deleteBbsCmntAll(bbsCmntVo);
            // 태그 삭제
            opBbsDao.deleteBbsTag(dataVo);

            /*
             * 자식글 또는 댓글이 존재한다면 내용만 삭제하고, 없는 경우 완전 삭제한다.
             */
            if(Validate.isNotEmpty(dataList)) {
                delCnt += opBbsDao.deleteRefrnSnBbs(bbsVo);
            } else {
                delCnt += opBbsDao.deleteBbs(dataVo);
            }

            // 첨부파일 삭제
            if(Validate.isNotEmpty(dataVo.getFileSn())) {
                opFileService.deleteFile(dataVo.getFileSn());
            }
        }
        return delCnt;
    }

    @Override
    public Integer updateBbsInqCnt(BbsVO bbsVo) {

        return opBbsDao.updateBbsInqCnt(bbsVo);
    }

    @Override
    public Integer updateStsfdgEvl(BbsVO bbsVo) {

        return opBbsDao.updateStsfdgEvl(bbsVo);
    }

    @Override
    public Integer updateRecoment(BbsVO bbsVo) {

        return opBbsDao.updateRecoment(bbsVo);
    }

    @Override
    public Integer updateSttemnt(BbsVO bbsVo) {

        return opBbsDao.updateSttemnt(bbsVo);
    }

    @Override
    public List<FeedVO> selectFeedList(BbsVO bbsVo) {

        return opBbsDao.selectFeedList(bbsVo);
    }

    @Override
    public boolean deleteFile(List<FileVO> fileList) {

        int deleteCnt = 0;
        if(Validate.isNotEmpty(fileList)) {
            // 물리적인 첨부파일 삭제
            for(FileVO fileVo : fileList) {
                if(FileUtil.delete(BaseConfig.UPLOAD_ROOT + fileVo.getFileUrlAddr())) {
                    deleteCnt++;
                }
            }
        }

        return (deleteCnt == fileList.size()) ? true : false;
    }

}
