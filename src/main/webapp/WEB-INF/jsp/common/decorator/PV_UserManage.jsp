<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="zesinc.web.support.BaseConfig"%>
<%@ page import="zesinc.web.vo.cache.CmsCacheVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String uri = (String) request.getAttribute("javax.servlet.forward.request_uri");
    if(uri == null) {
        uri = request.getRequestURI();
    }

    CmsCacheVO cmsCacheVo = (CmsCacheVO) request.getAttribute(BaseConfig.USER_MENU_INFO_KEY);
    String menudecorator = cmsCacheVo.getLytCdNo();

    String siteSn = request.getParameter("q_siteSn");
    if(null == siteSn) {
        siteSn = cmsCacheVo.getSiteSn().toString();
    }
    String decoratorPath = siteSn + "/" + menudecorator + ".jsp";

    String lytContsCn = request.getParameter("lytContsCn");
    if(lytContsCn != null) {
        out.println(lytContsCn);
    } else {
%>
<jsp:include page="<%=decoratorPath%>" flush="true"></jsp:include>
<%
    }
%>