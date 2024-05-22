/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
package zesinc.user.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import zesinc.web.spring.controller.UserController;


/**
 * 사용자 관리 컨트롤러
 *
 *<pre>
 *<< 개정이력(Modification Information) >>
 *
 *    수정일       수정자   수정내용
 *--------------  --------  -------------------------------
 * 2015. 7. 19.    박수정   최초작성
 *</pre>
 * @author (주)제스아이엔씨 기술연구소
 * @see
 */
@Controller("사용자관리")
@RequestMapping(value = { "/user/manage" })
public class UserManageController extends UserController {

}
