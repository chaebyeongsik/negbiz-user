<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>

                            <ul>
                                <c:choose>
                                    <c:when test="${not empty opFileList}">
                                        <c:forEach var="fileVo" items="${opFileList}">
                                            <c:if test="${empty opFileInputName or opFileInputName eq fileVo.inptDataNm}">
                                                <li>
                                                    <span class="glyphicon glyphicon-file"></span>
                                                    <a href="/component/file/ND_fileDownload.do?q_fileSn=${fileVo.fileSn}&amp;q_fileId=${fileVo.fileId}" title="${fileVo.fileExpln}">${fileVo.orgnlFileNm}</a>
                                                    <span class="text-success">(download ${fileVo.dwnldCnt}, ${fileVo.fileSzNm}, ${fileVo.fileTypeNm})</span>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <li>첨부파일이 없습니다.</li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>