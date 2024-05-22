<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="zesinc.web.support.BaseConfig"%>
<%@ page import="zesinc.web.vo.cache.CmsCacheVO"%>
<%@ page import="zesinc.user.cms.support.CmsSupport"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    HttpSession hs = request.getSession();
    String uri = (String) request.getAttribute("javax.servlet.forward.request_uri");
    if(uri == null) {
        uri = request.getRequestURI();
    }

    CmsCacheVO cmsCacheVo = (CmsCacheVO) request.getAttribute(BaseConfig.USER_MENU_INFO_KEY);
    if(cmsCacheVo != null) {
        String menudecorator = cmsCacheVo.getLytCdNo();
        String gradMenudecorator = cmsCacheVo.getGradLytCdNo();

        String decoratorPath = null;
        if(gradMenudecorator.equals(CmsSupport.NO_AUTH_PAGE)) {
            decoratorPath = gradMenudecorator + ".jsp";
        } else {
            String siteSn = request.getParameter("q_siteSn");
            if(null == siteSn) {
                siteSn = cmsCacheVo.getSiteSn().toString();
            }
            decoratorPath = siteSn + "/" + menudecorator + ".jsp";
        }
%>
    <jsp:include page="<%=decoratorPath%>" flush="true"></jsp:include>
<%
    }
%>