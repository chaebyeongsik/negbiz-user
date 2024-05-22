/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.bbs;

import java.util.List;

import zesinc.component.file.domain.FileVO;
import zesinc.user.bbs.domain.BbsVO;
import zesinc.web.support.pager.Pager;
import zesinc.web.vo.FeedVO;

/**
 * 게시판 정보 서비스 클레스
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
public interface BbsService {

    /**
     * 게시물 목록 조회
     * 
     * @param bbsVo
     * @return
     */
    Pager<BbsVO> selectBbsPageList(BbsVO bbsVo);

    /**
     * 게시물 공지 목록 조회
     * 
     * @param bbsVo
     * @return
     */
    List<BbsVO> selectBbsNoticeList(BbsVO bbsVo);

    /**
     * 게시물 상세 조회
     * 
     * @param bbsVo
     * @return
     */
    BbsVO selectBbs(BbsVO bbsVo);

    /**
     * 게시물 등록
     * 
     * @param bbsVo
     * @return
     */
    Integer insertBbs(BbsVO bbsVo);

    /**
     * 게시물 수정
     * 
     * @param bbsVo
     * @return
     */
    Integer updateBbs(BbsVO bbsVo);

    /**
     * 게시물 삭제
     * 
     * @param bbsVo
     * @return
     */
    Integer deleteBbs(BbsVO bbsVo);

    /**
     * 게시물 상세 조회 카운트를 증가시킨다.
     * 
     * @param bbsVo
     * @return
     */
    Integer updateBbsInqCnt(BbsVO bbsVo);

    /**
     * 게시물 만족도평가
     * 
     * @param bbsVo
     * @return
     */
    Integer updateStsfdgEvl(BbsVO bbsVo);

    /**
     * 게시물 추천
     * 
     * @param bbsVo
     * @return
     */
    Integer updateRecoment(BbsVO bbsVo);

    /**
     * 게시물 신고
     * 
     * @param bbsVo
     * @return
     */
    Integer updateSttemnt(BbsVO bbsVo);

    /**
     * 용량이 오버된 첨부파일을 삭제한다.
     * 
     * @param fileList
     * @return
     */
    boolean deleteFile(List<FileVO> fileList);

    /**
     * RSS/ATOM 서비스 제공을 위한 게시물 목록
     * 
     * @param bbsVo
     * @return
     */
    List<FeedVO> selectFeedList(BbsVO bbsVo);
}
