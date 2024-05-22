package zesinc.user.join.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.user.join.UserJoinMapper;
import zesinc.user.join.UserJoinService;
import zesinc.user.join.domain.UserInfoIemEstbsVO;
import zesinc.user.join.domain.UserJoinVO;
import zesinc.user.support.UserStatus;
import zesinc.user.support.UserType;
import zesinc.web.utils.CryptoUtil;
import zesinc.web.utils.PasswdUtil;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 사용자 가입 Service 구현 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 7. 15.    ZES-INC   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Service("opUserJoinService")
public class UserJoinServiceImpl extends EgovAbstractServiceImpl implements UserJoinService {

    @Resource(name = "opUserJoinDao")
    private UserJoinMapper opUserJoinDao;

    @Override
    public Integer selectDupCheckUserId(UserJoinVO userJoinVo) {

        return opUserJoinDao.selectDupCheckUserId(userJoinVo);
    }

    @Override
    public Integer insertIndvdlUser(UserJoinVO userJoinVo) {
        userJoinVo.setRgtrId(userJoinVo.getUserId());

        userJoinVo.setUserTypeNm(UserType.INDVDL.getUserType());
        userJoinVo.setUserSttsSn(UserStatus.JOIN.getUserStatus());
        // 비밀번호 암호화
        if(!Validate.isEmpty(userJoinVo.getUserPswd())) {
            //userJoinVo.setUserPswd(PasswdUtil.encodePasswd(userJoinVo.getUserPswd()));
            userJoinVo.setUserPswd(PasswdUtil.encode(userJoinVo.getUserPswd()));
        }
        int key = opUserJoinDao.insertIndvdlUser(userJoinVo);
        UserJoinVO infoVo = opUserJoinDao.selectUserByUserKey(userJoinVo);
        userJoinVo.setUserId(infoVo.getUserId());
        opUserJoinDao.insertIndvdlUserEtcInfo(userJoinVo);
        return key;
    }

    @Override
    public UserJoinVO selectIndvdlUserInfo(UserJoinVO userJoinVo) {
        return opUserJoinDao.selectIndvdlUserInfo(userJoinVo);
    }

    @Override
    public Integer updateIndvdlUser(UserJoinVO userJoinVo) {
        // UserJoinVO originalVo = opUserJoinDao.selectIndvdlUserInfo(userJoinVo);
        // String changeContents = getIndvdlUserChangeContents(originalVo, userJoinVo);

        int result = opUserJoinDao.updateIndvdlUser(userJoinVo);
        opUserJoinDao.updateIndvdlUserEtcInfo(userJoinVo);

        return result;
    }

    @Override
    public Integer insertEntrprsUser(UserJoinVO userJoinVo) {
        userJoinVo.setRgtrId(userJoinVo.getUserId());

        userJoinVo.setUserTypeNm(UserType.ENTRPRS.getUserType());
        userJoinVo.setUserSttsSn(UserStatus.JOIN.getUserStatus());
        // 비밀번호 암호화
        if(!Validate.isEmpty(userJoinVo.getUserPswd())) {
            //userJoinVo.setUserPswd(PasswdUtil.encodePasswd(userJoinVo.getUserPswd()));
            userJoinVo.setUserPswd(PasswdUtil.encode(userJoinVo.getUserPswd()));
        }
        int key = opUserJoinDao.insertEntrprsUser(userJoinVo);
        UserJoinVO infoVo = opUserJoinDao.selectUserByUserKey(userJoinVo);
        userJoinVo.setUserId(infoVo.getUserId());
        opUserJoinDao.insertEntrprsUserEtcInfo(userJoinVo);
        return key;
    }

    @Override
    public UserJoinVO selectEntrprsUserInfo(UserJoinVO userJoinVo) {
        return opUserJoinDao.selectEntrprsUserInfo(userJoinVo);
    }

    @Override
    public Integer updateEntrprsUser(UserJoinVO userJoinVo) {
        // UserJoinVO originalVo = opUserJoinDao.selectEntrprsUserInfo(userJoinVo);
        // String changeContents = getEntrprsUserChangeContents(originalVo, userJoinVo);

        int key = opUserJoinDao.updateEntrprsUser(userJoinVo); // 기업사용자 일반정보 수정
        opUserJoinDao.updateEntrprsUserEtcInfo(userJoinVo); // 기업사용자 기타정보 수정

        return key;
    }

    @Override
    public UserInfoIemEstbsVO selectIndvdlUserInfoIemEstbsYn() {
        return opUserJoinDao.selectIndvdlUserInfoIemEstbsYn();
    }

    @Override
    public UserInfoIemEstbsVO selectEntrprsUserInfoIemEstbsYn() {
        return opUserJoinDao.selectEntrprsUserInfoIemEstbsYn();
    }

    @Override
    public List<UserJoinVO> selectUserGradInUseList() {
        UserJoinVO userJoinVo = new UserJoinVO();
        userJoinVo.setUseYn("Y");
        return opUserJoinDao.selectUserGradList(userJoinVo);
    }

    @Override
    public boolean processCheckPassword(UserJoinVO userJoinVo) throws Exception {
        UserJoinVO userInfo = opUserJoinDao.selectUserByUserId(userJoinVo);
        if(Validate.isNotNull(userInfo) && Validate.isNotEmpty(userInfo.getUserPswd())) {
            String passwd = userJoinVo.getUserPswd();// 화면에서 입력받은 비밀번호
            // 암화화되어 넘어온 비밀번호 복호화(웹페이지에서 평문전송 방지를 위해 PBKDF2 방식으로 암호화되어 넘어옴)
            passwd = CryptoUtil.decrypt(String.valueOf(passwd));

            //Boolean matcheAt = PasswdUtil.matche(passwd, userInfo.getUserPswd());
            Boolean matcheAt = PasswdUtil.matches(passwd, userInfo.getUserPswd());
            if(matcheAt) {
                return true;
            }
        }
        return false;
    }
}
