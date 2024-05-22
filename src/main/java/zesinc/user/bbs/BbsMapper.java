/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.bbs;

import java.util.List;

import zesinc.user.bbs.domain.BbsTagVO;
import zesinc.user.bbs.domain.BbsVO;
import zesinc.web.vo.FeedVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 게시판 정보 DAO 클레스
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
@Mapper("opBbsDao")
public interface BbsMapper {

    /**
     * 게시판 목록 조회
     * 
     * @param bbsVo
     * @return
     */
    List<BbsVO> selectBbsList(BbsVO bbsVo);

    /**
     * 게시판 공지목록 조회
     * 
     * @param bbsVo
     * @return
     */
    List<BbsVO> selectBbsNoticeList(BbsVO bbsVo);

    /**
     * 게시판 목록 건수
     * 
     * @param bbsVo
     * @return
     */
    Integer selectBbsListCount(BbsVO bbsVo);

    /**
     * 게시판 상세 조회
     * 
     * @param bbsVo
     * @return
     */
    BbsVO selectBbs(BbsVO bbsVo);

    /**
     * 참조일련번호로 게시물 조회
     * 
     * @param bbsVo
     * @return
     */
    List<BbsVO> selectRefrnSnBbsList(BbsVO bbsVo);

    /**
     * 게시판 등록
     * 
     * @param bbsVo
     * @return
     */
    Integer insertBbs(BbsVO bbsVo);

    /**
     * 게시판 수정
     * 
     * @param bbsVo
     * @return
     */
    Integer updateBbs(BbsVO bbsVo);

    /**
     * 답글 등록시 이전 게시물 정렬순을 증가시킨다.
     * 
     * @param bbsVo
     * @return
     */
    Integer updateBbsSortSn(BbsVO bbsVo);

    /**
     * 게시판 삭제
     * 
     * @param bbsVo
     * @return
     */
    Integer deleteBbs(BbsVO bbsVo);

    /**
     * 게시판 삭제 : 답글형이며 자식글이 있는 경우에는 내용만 삭제시킨다.
     * 
     * @param bbsVo
     * @return
     */
    Integer deleteRefrnSnBbs(BbsVO bbsVo);

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
     * 이전 게시글 조회
     * 
     * @param bbsVo
     * @return
     */
    BbsVO selectBbsPrevView(BbsVO bbsVo);

    /**
     * 다음 게시글 조회
     * 
     * @param bbsVo
     * @return
     */
    BbsVO selectBbsNextView(BbsVO bbsVo);

    /**
     * 게시글태그 목록조회
     * 
     * @param bbsVo
     * @return
     */
    List<String> selectBbsTagList(BbsVO bbsVo);

    /**
     * 게시글태그 중복조회
     * 
     * @param bbsTagVo
     * @return
     */
    Integer selectBbsTagCo(BbsTagVO bbsTagVo);

    /**
     * 게시글태그 등록
     * 
     * @param bbsTagVo
     * @return
     */
    Integer insertBbsTag(BbsTagVO bbsTagVo);

    /**
     * 게시글태그 삭제
     * 
     * @param bbsVo
     * @return
     */
    Integer deleteBbsTag(BbsVO bbsVo);

    /**
     * RSS/ATOM 서비스 제공을 위한 게시물 목록
     * 
     * @param bbsVo
     * @return
     */
    List<FeedVO> selectFeedList(BbsVO bbsVo);
}
