package zesinc.login.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import zesinc.core.lang.Validate;
import zesinc.login.UserLoginMapper;
import zesinc.login.UserLoginService;
import zesinc.login.domain.UserLoginVO;
import zesinc.user.support.UserType;
import zesinc.web.utils.CryptoUtil;
import zesinc.web.utils.PasswdUtil;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 사용자 로그인 Service 구현 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일       수정자   수정내용
 * --------------  --------  -------------------------------
 *  2015. 7. 19.    박수정   최초작성
 * </pre>
 *
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Service("opUserLoginService")
public class UserLoginServiceImpl extends EgovAbstractServiceImpl implements UserLoginService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "opUserLoginDao")
    private UserLoginMapper opUserLoginDao;

    @Override
    public UserLoginVO processUserIdAndPwdCheck(UserLoginVO userLoginVo) throws Exception {
        UserLoginVO dataVo = opUserLoginDao.selectUserInfoByUserKey(userLoginVo);

        if(dataVo != null) {
            userLoginVo.setUserId(dataVo.getUserId());
            dataVo.setValidId(true);

            // 암화화되어 넘어온 비밀번호 복호화(웹페이지에서 평문전송 방지를 위해 PBKDF2 방식으로 암호화되어 넘어옴)
            if (Validate.isNotEmpty(userLoginVo.getUserPswd())) {
                userLoginVo.setUserPswd(CryptoUtil.decrypt(String.valueOf(userLoginVo.getUserPswd())));
            }

for (int i=0; i<10; i++) {
    System.out.println("[" + i + "]" + "zesinc123!     " + PasswdUtil.encode("zesinc123!"));
}
for (int i=0; i<10; i++) {
    System.out.println("[" + i + "]" + "a123456789     " + PasswdUtil.encode("a123456789"));
}

            // 비밀번호 암호화
            String encryptPwd = PasswdUtil.encodePasswd(userLoginVo.getUserPswd());
            // 암호화된 DB 비밀번호
            String dbPwd = dataVo.getUserPswd();

            logger.debug("사용자가 입력한 비밀번호 = [ " + userLoginVo.getUserPswd() + " ]");
//            logger.debug("사용자가 입력한 비밀번호 암호화 = [ " + encryptPwd + " ]");
            logger.debug("DB에 저장된 비밀번호 = [ " + dbPwd + " ]");
            logger.debug("processUserIdAndPwdCheck [{}]", PasswdUtil.matches(userLoginVo.getUserPswd(), dbPwd));

//            if(encryptPwd.equals(dbPwd)) {
            if(PasswdUtil.matches(userLoginVo.getUserPswd(), dbPwd)) {

                // 비밀번호는 평문으로 Login 세션에 저장
                //dataVo.setUserPswd(userLoginVo.getUserPswd());
                dataVo.setValidPwd(true);

                UserLoginVO etcInfo;
                if(dataVo.getUserTypeNm().equals(UserType.ENTRPRS.getUserType())){
                    etcInfo = opUserLoginDao.selectEntrprsEtcInfo(userLoginVo);
                }else {
                    etcInfo = opUserLoginDao.selectIndvdlEtcInfo(userLoginVo);
                }
                dataVo.setMblRgnTelno(etcInfo.getMblRgnTelno());
                dataVo.setMblTelofcTelno(etcInfo.getMblTelofcTelno());
                dataVo.setMblIndivTelno(etcInfo.getMblIndivTelno());
            }
        } else {
            return userLoginVo;
        }

        return dataVo;
    }

    @Override
    public void insertLoginTryLog(UserLoginVO userLoginVo) {

        opUserLoginDao.insertLoginTryLog(userLoginVo);
    }

    @Override
    public void processLogOfUser(UserLoginVO userLoginVo) {
        // 사용자정보 중 로그인건수 및 최근접속일자 갱신
        opUserLoginDao.updateUserLoginInfo(userLoginVo);

        // 해당 사용자 오늘 접속 이력 건수 조회
        int loginYn = opUserLoginDao.selectTodayLoginCo(userLoginVo);

        if(loginYn == 0) {
            // 사용자 정보 조회
            UserLoginVO userInfo = opUserLoginDao.selectUserInfo(userLoginVo);
            userLoginVo.setAge(userInfo.getAge());
            userLoginVo.setGndrClsfSn(userInfo.getGndrClsfSn());
            userLoginVo.setJoinTypeSn(userInfo.getJoinTypeSn());

            // 사용자 전체 카운트 조회
            int userAllCount = opUserLoginDao.selectUserAllCount(userLoginVo);
            userLoginVo.setWholMbrCnt(userAllCount);

            if(userLoginVo.getAge() >= 10 && userLoginVo.getAge() < 20) {
                userLoginVo.setAgeType("A");
            } else if(userLoginVo.getAge() >= 20 && userLoginVo.getAge() < 30) {
                userLoginVo.setAgeType("B");
            } else if(userLoginVo.getAge() >= 30 && userLoginVo.getAge() < 40) {
                userLoginVo.setAgeType("C");
            } else if(userLoginVo.getAge() >= 40 && userLoginVo.getAge() < 50) {
                userLoginVo.setAgeType("D");
            } else if(userLoginVo.getAge() >= 50 && userLoginVo.getAge() < 60) {
                userLoginVo.setAgeType("E");
            } else if(userLoginVo.getAge() >= 60 && userLoginVo.getAge() < 70) {
                userLoginVo.setAgeType("F");
            } else if(userLoginVo.getAge() >= 70) {
                userLoginVo.setAgeType("G");
            } else {
                userLoginVo.setAgeType("H");
            }

            opUserLoginDao.insertLogStatsCount(userLoginVo);
        }

    }

}
