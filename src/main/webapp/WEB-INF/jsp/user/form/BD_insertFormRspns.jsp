<%--
/*
 * Copyright (c) 2015 ZES Inc. All rights reserved.
 * This software is the confidential and proprietary information of ZES Inc.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into
 * with ZES Inc. (http://www.zesinc.co.kr/)
 */
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.openworks.kr/jsp/jstl" prefix="op"%>
<%@ taglib uri="http://www.openworks.kr/jsp/validate" prefix="valid"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>신청화면</title>

<link rel="stylesheet" href="/resources/user/form/css/frmBD.css">
<op:jsTag type="intra" items="opform" />
<op:jsTag type="user" items="opform, opvalidate, opPassword" />
<op:adres zip="zip" adres="adres" adres2="adres2" />

<!-- 기능별 스크립트 삽입 부 -->
<script type="text/javascript" src="/resources/user/form/js/formRspns.js"></script>
<script type="text/javascript">
    
    var groupList = [];
    
 	//<![CDATA[	
	$(function(){	
		var formSn = ${selectForm.formSn};
		$("#opPrevious").click(function(){
			location.href = "/user/form/BD_selectForm.do?q_formSn="+formSn;			
		});	    
		
		// 정규식 start		
		// 문자만 입력 가능하도록 설정
		$(".textOnly").keyup(function() {
			var regexp = /^[a-zA-Zㄱ-힣][a-zA-Zㄱ-힣]*$/
			v = $(this).val();
			if (!regexp.test(v)) {
				alert("문자만 입력 가능합니다. (한글/영문)");
				$(this).val("");
			}
		});
		
		// 숫자만 입력 가능하도록 설정
		$(".numOnly").keyup(function() {
			var regexp = /^[0-9]*$/
			v = $(this).val();
			if (!regexp.test(v)) {
				alert("숫자만 입력 가능합니다.");
				$(this).val("");
			}
		});
		
		// 영어 및 ._-만 입력 가능하도록 설정 (이메일 아이디)
		$(".emlIdOnly").keyup(function() {
			var regexp = /[a-zA-Z0-9\.\_\-]$/
			v = $(this).val();
			if (!regexp.test(v)) {
				alert("영어만 입력 가능합니다.\n특수문자는 . _ - 만 입력 가능합니다.");
				$(this).val("");
			}
		});
		
		// 영어 및 .만 입력 가능하도록 설정 (이메일 사이트)
		$(".emlSiteOnly").keyup(function() {
			var regexp = /[a-zA-Z0-9\.]+$/
			v = $(this).val();
			if (!regexp.test(v)) {
				alert("영어만 입력 가능합니다.\n특수문자는 . 만 입력 가능합니다.");
				$(this).val("");
			}
		});
		// 정규식 end
		
		// 숫자 범위 체크
		$(".rsNum").keyup(function() {
			var val = parseInt($(this).val());
			var tagId = $(this).attr('name').substr(5);
			var min = parseInt($('input:hidden[rsNum=min_'+ tagId+']').val());
			var max = parseInt($('input:hidden[rsNum=max_'+ tagId+']').val());
			
			if(val < min || val > max) {
				alert("숫자 " + min + " ~ " + max + " 범위로 입력해주세요.");
				$(this).val("");
			}
		});
	    
	    // 체크박스 중복갯수 체크
	    $("input:checkbox[rsType='chck']").change(function() {
	        var value = $(this).val();              
	        var checked = $(this).prop('checked');  
	        var cnt = $(this).attr("cnt");
	        var myname = $(this).attr("myname");

	        if(checked){
	        	var myCnt = 0;
	        	$("input:checkbox[rsType='chck'][myname="+myname+"]").each(function (i){
	        		if($(this).prop('checked')) { 
	        			myCnt = myCnt+1; 
	        		}
	        	});
	        	if(cnt < myCnt) {
	        		alert("중복 체크는 " + (myCnt-1) + "개까지만 가능합니다.");
	        		$(this).prop('checked', false);
	        		return;
	        	}
	        }
	    });
	    
	    // 신청자 정보 이메일 사이트 셀렉트박스
	    $('#rspnsEmlSiteNm').hide();		
		$('#selectEmail').change(function(){
			$("#selectEmail option:selected").each(function () {
				if($(this).val()== 'selfInput'){ //직접입력일 경우
					$("#rspnsEmlSiteNm").show();
					$("#rspnsEmlSiteNm").val('');                        
					$("#rspnsEmlSiteNm").attr("disabled", false);
				}else{ //직접입력이 아닐경우
					/* $("#rspnsEmlSiteNm").hide(); */
					$("#rspnsEmlSiteNm").val($(this).text());      
					$("#rspnsEmlSiteNm").attr("disabled", true);
				}
			});
		});
		
		// 신청자 정보 전화번호 셀렉트박스
		$('#rgnTelno').hide();		
		$('#selectTel').change(function(){
			$("#selectTel option:selected").each(function () {
				if($(this).val()== 'selfInput'){ //직접입력일 경우
					$("#rgnTelno").show();
					$("#rgnTelno").val('');                 
					$("#rgnTelno").attr("disabled", false); 
				}else{ //직접입력이 아닐경우
					$("#rgnTelno").hide();
					$("#rgnTelno").val($(this).text());    
					$("#rgnTelno").attr("disabled", true); 
				}
			});
		});
		
		// 이메일 항목 이메일 사이트 셀렉트박스
		$("select[rsType='eml']").each(function (i) {
	   		var name = $(this).attr('id'); 
	   		var arr = name.split("_");    		
	   		var result = ""
	   		
  			for(var i in arr) {
  				$("#eml_"+arr[1]+"_"+arr[2]+"_2").hide();	
  				$("#selectEml_"+arr[1]+"_"+arr[2]).change(function(){
					if($(this).val()== 'selfInput'){ //직접입력일 경우
						$("#eml_"+arr[1]+"_"+arr[2]+"_2").show();
						$("#eml_"+arr[1]+"_"+arr[2]+"_2").val('');                        
						$("#eml_"+arr[1]+"_"+arr[2]+"_2").attr("disabled", false);
					}else{ //직접입력이 아닐경우
						$("#eml_"+arr[1]+"_"+arr[2]+"_2").hide(); 
						$("#eml_"+arr[1]+"_"+arr[2]+"_2").val($(this).val());      
						$("#eml_"+arr[1]+"_"+arr[2]+"_2").attr("disabled", true);
					}
  				});
  			}
	   	});
		
		// 연락처 항목 이메일 사이트 셀렉트박스
		$("select[rsType='tel']").each(function (i) {
      		var name = $(this).attr('id'); 
      		var arr = name.split("_");    		
      		var result = ""
      		
   			for(var i in arr) {
   				$("#tel_"+arr[1]+"_"+arr[2]+"_1").hide();	
   				$("#selectTel_"+arr[1]+"_"+arr[2]).change(function(){
					if($(this).val()== 'selfInput'){ //직접입력일 경우
						$("#tel_"+arr[1]+"_"+arr[2]+"_1").show();
						$("#tel_"+arr[1]+"_"+arr[2]+"_1").val('');                        
						$("#tel_"+arr[1]+"_"+arr[2]+"_1").attr("disabled", false);
					}else{ //직접입력이 아닐경우
						$("#tel_"+arr[1]+"_"+arr[2]+"_1").hide(); 
						$("#tel_"+arr[1]+"_"+arr[2]+"_1").val($(this).val());      
						$("#tel_"+arr[1]+"_"+arr[2]+"_1").attr("disabled", true);
					}
   				});
   			}
      	});
		
		// 필수체크 여부
		<c:forEach items="${selectGroupList}" var="groupList" varStatus="status">
			<c:forEach items="${groupList.itemList}" var="itemList" >
				var estlYn = "${itemList.esntlYn}";
				if(estlYn == "Y"){
					var answer = 'item_${groupList.groupSn}_${itemList.artclSn}';
					groupList.push(answer);					
					/* console.log("answer:"+answer); */
				}					
			</c:forEach>
		</c:forEach>
		
	});	
	//]]>
</script>
</head>
<body>
<!-- 폼빌더 신청화면 -->
<div class="frmBD">
    <h3 class="cStyle">${selectForm.formTtl}</h3>

    <form name="dataForm" id="dataForm" method="post" action="ND_insertFormRspns.do" enctype="multipart/form-data" class="form-inline">
        <input type="hidden" name="q_formSn" id="q_formSn" value="${selectForm.formSn}" />
        <input type="hidden" name="wrtrInfoNo" id="wrtrInfoNo" value="${selectForm.wrtrInfoClctSttsNo}" />
        <input type="hidden" name="grpIds" id="grpIds" value=""/>		
        <input type="hidden" name="itemIds" id="itemIds" value=""/>
        <input type="hidden" name="fileNms" id="fileNms" value=""/>
        <input type="hidden" name="realFileNms" id="realFileNms" value=""/>

        <!-- 신청자정보 -->
        <c:if test="${selectForm.wrtrInfoClctSttsNo eq '0'}"> </c:if>
        <c:if test="${selectForm.wrtrInfoClctSttsNo eq '1'}">
	        <h4 class="cStyle">신청자정보</h4>
	        
	        <div class="desc required">
	            <p>항목은 필수 입력항목입니다.</p>
	        </div>
	
	        <table class="cStyle">
	            <colgroup>
					<col style="width: 20%;">
					<col style="width: 30%;">
					<col style="width: 20%;">
					<col style="width: 30%;">
				</colgroup>
	            <tbody>
					<tr>
						<th class="text-left"><span class="mandatory">*</span>이름</th>
						<td colspan="3"><input type="text" name="rspnsNm" id="rspnsNm" value="" class="textOnly" placeholder="이름을 입력하세요." required/> </td>
					</tr>
					<tr>
	                    <th class="text-left"><span class="mandatory">*</span>이메일</th>
						<td colspan="3">
	                        <div class="frm_email">
	                            <input type="text" name="rspnsEmlId" id="rspnsEmlId" value="" class="emlIdOnly" required/>
	                            <span>@</span>
	                            <input type="text" name="rspnsEmlSiteNm" id="rspnsEmlSiteNm" value="" class="emlSiteOnly" required/>
	                            <select name="rspnsEmlSiteNm" id="selectEmail" class="select selectEmail">
	                                <option value="" selected>선택</option>
	                                <option value="naver.com">naver.com</option>
	                                <option value="daum.net">daum.net</option>
	                                <option value="nate.com">nate.com</option>
	                                <option value="yahoo.co.kr">yahoo.co.kr</option>
	                                <option value="gmail.com">gmail.com</option>
	                                <option value="selfInput">직접입력</option>
	                            </select>
	                        </div>
						</td>
					</tr>
				</tbody>
	        </table>
        </c:if>
        
        <c:if test="${selectForm.wrtrInfoClctSttsNo eq '2'}">
	        <h4 class="cStyle">신청자정보</h4>
	
	        <div class="desc required">
	            <p>항목은 필수 입력항목입니다.</p>
	        </div>
	
	        <table class="cStyle">
	            <colgroup>
					<col style="width: 20%;">
					<col>
				</colgroup>
	            <tbody>
					<tr>
						<th class="text-left"><span class="mandatory">*</span>이름</th>
						<td><input type="text" name="rspnsNm" id="rspnsNm" value="" class="textOnly" placeholder="이름을 입력하세요." required/> </td>
					</tr>
					<tr>
	                    <th class="text-left"><span class="mandatory">*</span>이메일</th>
						<td>
	                        <div class="frm_email">
	                            <input type="text" name="rspnsEmlId" id="rspnsEmlId" value="" class="emlIdOnly" required/>
	                            <span>@</span>
	                            <input type="text" name="rspnsEmlSiteNm" id="rspnsEmlSiteNm" value="" class="emlSiteOnly" required/>
	                            <select name="rspnsEmlSiteNm" id="selectEmail" class="select selectEmail">
	                                <option value="" selected>선택</option>
	                                <option value="naver.com">naver.com</option>
	                                <option value="daum.net">daum.net</option>
	                                <option value="nate.com">nate.com</option>
	                                <option value="yahoo.co.kr">yahoo.co.kr</option>
	                                <option value="gmail.com">gmail.com</option>
	                                <option value="selfInput">직접입력</option>
	                            </select>
	                        </div>
						</td>
					</tr>
					<tr>
						<th class="text-left"><span class="mandatory">*</span>연락처</th>
						<td>
	                        <div class="frm_contact">
	                            <select name="rgnTelno" id="selectTel" class="select selectTel">
	                                <option value="" selected>선택</option>
	                                <option value="010">010</option>
	                                <option value="011">011</option>
	                                <option value="016">016</option>
	                                <option value="017">017</option>
	                                <option value="018">018</option>
	                                <option value="019">019</option>
	                                <option value="selfInput">직접입력</option>
	                            </select>
	                            <input type="text" name="rgnTelno" id="rgnTelno" maxlength="4" value="" class="numOnly" required/>
	                            <span>-</span>
	                            <input type="text" name="telofcTelno" id="telofcTelno" maxlength="4" value="" class="numOnly" required/>
	                            <span>-</span>
	                            <input type="text" name="indivTelno" id="indivTelno" maxlength="4" value="" class="numOnly" required/>
	                        </div>
						</td>
					</tr>
				</tbody>
	        </table>
        </c:if>
        <!-- // 신청자정보 -->


        <!-- 그룹 -->
        <c:forEach items="${selectGroupList}" var="groupList" varStatus="status">
        	<c:if test="${groupList.useYn eq 'Y'}">
		        <div class="frm_group">
		            <input type="hidden" name="inputGroupList" value="${groupList.groupSn}"/>
		
		            <h4 class="cStyle">${groupList.groupNm}</h4>
		            <c:if test="${not empty groupList.groupExpln}">
			            <div class="desc group">
			                <p>${groupList.groupExpln}</p>
			            </div>
		            </c:if>
		
		
		            <!-- 항목 -->
		            <c:forEach items="${groupList.itemList}" var="itemList">
		            	<c:if test="${itemList.useYn  eq 'Y'}">
				            <div class="frm_cate">
				                <input type="hidden" name="inputGroupItemList" value="${groupList.groupSn}_${itemList.artclSn}"/>
							
				                <!-- 단답형 -->
				                <c:if test="${itemList.artclTypeCd eq 'shortTxIem'}">
					                <div class="cate_wrap">
					                    <h5 class="cStyle"><c:if test="${itemList.esntlYn eq 'Y'}"><span class="mandatory">*</span></c:if>${itemList.artclNm}</h5>
					                    <c:if test="${not empty itemList.artclExpln}">
						                    <div class="desc cate">
						                        <p>${itemList.artclExpln}</p>
						                    </div>
					                    </c:if>
					                    <input type="text" name="item_${groupList.groupSn}_${itemList.artclSn}" id="" value="" esntlYn="${itemList.esntlYn}">
					                </div>
				                </c:if>
				                <!-- // 단답형 -->
				
								<!-- 장문형 -->
								<c:if test="${itemList.artclTypeCd eq 'longTxIem'}">
					                <div class="cate_wrap">
					                    <h5 class="cStyle"><c:if test="${itemList.esntlYn eq 'Y'}"><span class="mandatory">*</span></c:if>${itemList.artclNm}</h5>
					                    <c:if test="${not empty itemList.artclExpln}">
						                    <div class="desc cate">
						                        <p>${itemList.artclExpln}</p>
						                    </div>
					                    </c:if>
					                    <div class="frm_textarea">
					                        <textarea name="item_${groupList.groupSn}_${itemList.artclSn}" id="" rows="5" maxlength="" value="" esntlYn="${itemList.esntlYn}" rsType="textarea"></textarea>
					                    </div>
					                </div>
				                </c:if>
				                <!-- // 장문형 -->
				
								<!-- 숫자 -->
								<c:if test="${itemList.artclTypeCd eq 'numIem'}">
					                <div class="cate_wrap">
					                    <h5 class="cStyle"><c:if test="${itemList.esntlYn eq 'Y'}"><span class="mandatory">*</span></c:if>${itemList.artclNm}</h5>
					                    <c:if test="${not empty itemList.artclExpln}">
						                    <div class="desc cate">
						                        <p>${itemList.artclExpln}</p>
						                    </div>
					                    </c:if>
					                    <div class="frm_num">
					                        <input type="hidden" rsNum="min_${groupList.groupSn}_${itemList.artclSn}" value="${itemList.initVl}" />
					                        <input type="hidden" rsNum="max_${groupList.groupSn}_${itemList.artclSn}" value="${itemList.lmtVl}" />
					                        <input type="number" name="item_${groupList.groupSn}_${itemList.artclSn}" id="" min="${itemList.initVl}" max="${itemList.lmtVl}"  esntlYn="${itemList.esntlYn}" class="numOnly rsNum">
					                        <i>숫자는 ${itemList.initVl} ~ ${itemList.lmtVl} 범위 내에서 입력 바랍니다.</i>
					                    </div>
					                </div>
				                </c:if>
				                <!-- // 숫자 -->
				                
				                <!-- 날짜 & 시간 -->
								<c:if test="${itemList.artclTypeCd eq 'ymdIem'}">
					                <div class="cate_wrap">
					                    <h5 class="cStyle"><c:if test="${itemList.esntlYn eq 'Y'}"><span class="mandatory">*</span></c:if>${itemList.artclNm}</h5>
					                    <c:if test="${not empty itemList.artclExpln}">
						                    <div class="desc cate">
						                        <p>${itemList.artclExpln}</p>
						                    </div>
						                </c:if>
					                    <div class="frm_date">
					                        <input type="date" name="" id="dt_${groupList.groupSn}_${itemList.artclSn}_1" rsType="dt" maxlength="" value="" esntlYn="${itemList.esntlYn}" placeholder="">
					                        <input type="time" name="" id="dt_${groupList.groupSn}_${itemList.artclSn}_2" rsType="dt" maxlength="" value="" esntlYn="${itemList.esntlYn}" placeholder="">
					                        <input type="hidden" name="item_${groupList.groupSn}_${itemList.artclSn}" />
					                    </div>
					                </div>
				                </c:if>
				                <!-- // 날짜 & 시간 -->
				
				                <!-- 이메일 -->
				                <c:if test="${itemList.artclTypeCd eq 'emlIem'}">
					                <div class="cate_wrap">
					                    <h5 class="cStyle"><c:if test="${itemList.esntlYn eq 'Y'}"><span class="mandatory">*</span></c:if>${itemList.artclNm}</h5>
					                    <c:if test="${not empty itemList.artclExpln}">
						                    <div class="desc cate">
						                        <p>${itemList.artclExpln}</p>
						                    </div>
					                    </c:if>
					                    <div class="frm_email">
					                        <input type="text" name="" id="eml_${groupList.groupSn}_${itemList.artclSn}_1" rsType="eml" maxlength="" value="" esntlYn="${itemList.esntlYn}" class="emlIdOnly">
					                        <span class="form-control-static">@</span>
					                        <input type="text" name="" id="eml_${groupList.groupSn}_${itemList.artclSn}_2" rsType="eml" maxlength="" value="" esntlYn="${itemList.esntlYn}" class="emlSiteOnly">
					                        <select name="" rsType="eml" id="selectEml_${groupList.groupSn}_${itemList.artclSn}" class="select selectEmail">
				                                <option value="" selected>선택</option>
				                                <option value="naver.com">naver.com</option>
				                                <option value="daum.net">daum.net</option>
				                                <option value="nate.com">nate.com</option>
				                                <option value="yahoo.co.kr">yahoo.co.kr</option>
				                                <option value="gmail.com">gmail.com</option>
				                                <option value="selfInput">직접입력</option>
				                            </select>
					                        <input type="hidden" name="item_${groupList.groupSn}_${itemList.artclSn}"/>
					                    </div>
					                </div>
				                </c:if>				                
				                <!-- // 이메일 -->
				
				                <!-- 연락처 -->
				                <c:if test="${itemList.artclTypeCd eq 'telnoIem'}">
					                <div class="cate_wrap">
					                    <h5 class="cStyle"><c:if test="${itemList.esntlYn eq 'Y'}"><span class="mandatory">*</span></c:if>${itemList.artclNm}</h5>
						                    <c:if test="${not empty itemList.artclExpln}">
						                    <div class="desc cate">
						                        <p>${itemList.artclExpln}</p>
						                    </div>
						                </c:if>
					                    <div class="frm_contact">
					                    	<select name="" rsType="tel" id="selectTel_${groupList.groupSn}_${itemList.artclSn}" class="select selectTel">
				                                <option value="" selected>선택</option>
				                                <option value="010">010</option>
				                                <option value="011">011</option>
				                                <option value="016">016</option>
				                                <option value="017">017</option>
				                                <option value="018">018</option>
				                                <option value="019">019</option>
				                                <option value="selfInput">직접입력</option>
				                            </select>
					                        <input type="text" name="" id="tel_${groupList.groupSn}_${itemList.artclSn}_1" rsType="tel" maxlength="4" esntlYn="${itemList.esntlYn}" class="numOnly">
					                        <span>-</span>
					                        <input type="text" name="" id="tel_${groupList.groupSn}_${itemList.artclSn}_2" rsType="tel" maxlength="4" esntlYn="${itemList.esntlYn}" class="numOnly">
					                        <span>-</span>
					                        <input type="text" name="" id="tel_${groupList.groupSn}_${itemList.artclSn}_3" rsType="tel" maxlength="4" esntlYn="${itemList.esntlYn}" class="numOnly">
					                        <input type="hidden" name="item_${groupList.groupSn}_${itemList.artclSn}" />
					                    </div>
					                </div>
				                </c:if>
				                <!-- // 연락처 -->
				                				                
				                <!-- 주소 -->
				                <c:if test="${itemList.artclTypeCd eq 'addrIem'}">
					                <div class="cate_wrap">
					                    <h5 class="cStyle"><c:if test="${itemList.esntlYn eq 'Y'}"><span class="mandatory">*</span></c:if>${itemList.artclNm}</h5>
					                    <c:if test="${not empty itemList.artclExpln}">
						                    <div class="desc cate">
						                        <p>${itemList.artclExpln}</p>
						                    </div>
						                </c:if>
					                    <div class="frm_addr">
					                        <div>
					                            <label for="zip" class="sr-only">우편번호 앞자리</label>
					                            <input type="text" name="" id="addr_${groupList.groupSn}_${itemList.artclSn}_1" rsType="addr" maxlength="5" value="${dataVo.zip}" esntlYn="${itemList.esntlYn}" class="zip numOnly" placeholder="우편번호">
					                            <button type="button" class="btn btn-default adresPopBtn">우편번호찾기</button>
					                        </div>
					                        <div>
					                            <label for="userAddr" class="sr-only">기본주소</label>
					                            <input type="text" name="" id="addr_${groupList.groupSn}_${itemList.artclSn}_2" rsTtype="addr" maxlength="" value="${dataVo.zuserAddrip}" esntlYn="${itemList.esntlYn}" class="adres" placeholder="기본주소">
					                        </div>
					                        <div>
					                            <label for="daddr" class="sr-only">상세주소</label>
					                            <input type="text" name="" id="addr_${groupList.groupSn}_${itemList.artclSn}_3" rsType="addr" maxlength="" value="${dataVo.daddr}" esntlYn="${itemList.esntlYn}" class="adres2" placeholder="상세주소">
					                            <input type="hidden" name="item_${groupList.groupSn}_${itemList.artclSn}"/>
					                        </div>
					                    </div> 
					                </div>
				                </c:if>
				                <!-- // 주소 -->
				                
				                <!-- Y & N -->
								<c:if test="${itemList.artclTypeCd eq 'ynIem'}">
					                <div class="cate_wrap">
					                    <h5 class="cStyle"><c:if test="${itemList.esntlYn eq 'Y'}"><span class="mandatory">*</span></c:if>${itemList.artclNm}</h5>
					                    <c:if test="${not empty itemList.artclExpln}">
						                    <div class="desc cate">
						                        <p>${itemList.artclExpln}</p>
						                    </div>
						                </c:if>
					                    <div class="frm_radioChk">
					                        <%-- <input type="hidden" name="item_${groupList.groupSn}_${itemList.artclSn}" rsType="rdo"/> --%>
					                        <ul>
					                            <li>
					                                <input type="radio" value="Y" name="item_${groupList.groupSn}_${itemList.artclSn}" id="Y_${groupList.groupSn}_${itemList.artclSn}" esntlYn="${itemList.esntlYn}" class="radio-inline radio-primary" rsType="rdo"/>
					                                <label for="Y_${groupList.groupSn}_${itemList.artclSn}">Y</label>
					                            </li>
					                            <li>
					                                <input type="radio" value="N" name="item_${groupList.groupSn}_${itemList.artclSn}" id="N_${groupList.groupSn}_${itemList.artclSn}" esntlYn="${itemList.esntlYn}" class="radio-inline radio-primary" rsType="rdo"/>
					                                <label for="N_${groupList.groupSn}_${itemList.artclSn}">N</label>
					                            </li>
					                        </ul>
					                    </div>
					                </div>
				                </c:if>
				                <!-- // Y & N -->
				
				                <!-- 라디오버튼 -->
				                <c:if test="${itemList.artclTypeCd eq 'radioIem'}">
					                <div class="cate_wrap">
					                    <h5 class="cStyle"><c:if test="${itemList.esntlYn eq 'Y'}"><span class="mandatory">*</span></c:if>${itemList.artclNm}</h5>
					                    <c:if test="${not empty itemList.artclExpln}">
						                    <div class="desc cate">
						                        <p>${itemList.artclExpln}</p>
						                    </div>
						                </c:if>
					                    <div class="frm_radioChk">
					                        <%-- <input type="hidden" name="item_${groupList.groupSn}_${itemList.artclSn}" rsType="rdo"/> --%>
					                        <ul>
					                            <c:forEach items="${itemList.optList}" var="optList" >
					                                <li>
					                                    <input type="radio" name="item_${groupList.groupSn}_${itemList.artclSn}" id="${optList.optCn}" value="${optList.optCn}" esntlYn="${itemList.esntlYn}" class="radio-inline radio-primary" rsType="rdo"/>
					                                    <label for="${optList.optCn}">${optList.optCn}</label>
					                                </li>
					                            </c:forEach>
					                        </ul>
					                    </div>
					                </div>
				                </c:if>
				                <!-- // 라디오버튼 -->
				                
				                <!-- 체크박스 -->
				                <c:if test="${itemList.artclTypeCd eq 'checkIem'}">
					                <div class="cate_wrap">
					                    <h5 class="cStyle"><c:if test="${itemList.esntlYn eq 'Y'}"><span class="mandatory">*</span></c:if>${itemList.artclNm}</h5>
					                    <c:if test="${not empty itemList.artclExpln}">
						                    <div class="desc cate">
						                        <p>${itemList.artclExpln}</p>
						                    </div>
						                </c:if>
					                    <div class="frm_radioChk">
					                        <input type="hidden" name="item_${groupList.groupSn}_${itemList.artclSn}" rsType="chck"/>
					
					                        <ul>
					                            <c:forEach items="${itemList.optList}" var="optList" varStatus="i">
					                                <li>
					                                    <input type="checkbox" name="item_${groupList.groupSn}_${itemList.artclSn}" id="${optList.optCn}" rsType="chck" myname="item_${groupList.groupSn}_${itemList.artclSn}" value="${optList.optCn}" esntlYn="${itemList.esntlYn}" cnt="${itemList.plChcCnt}" class="checkbox-inline">
					                                    <label for="${optList.optCn}">${optList.optCn}</label>
					                                </li>
					                            </c:forEach>
					                        </ul>
					                        <i>최대 ${itemList.plChcCnt}개까지 중복하여 선택할 수 있습니다.</i>
					                    </div>
					                </div>
								</c:if>
								<!-- // 체크박스 -->
				                
				                <!-- 셀렉트박스 -->
				                <c:if test="${itemList.artclTypeCd eq 'selectIem'}">
					                <div class="cate_wrap">
					                    <h5 class="cStyle"><c:if test="${itemList.esntlYn eq 'Y'}"><span class="mandatory">*</span></c:if>${itemList.artclNm}</h5>
					                    <c:if test="${not empty itemList.artclExpln}">
						                    <div class="desc cate">
						                        <p>${itemList.artclExpln}</p>
						                    </div>
						                </c:if>
					                    <div class="frm_select">
					                        <select name="item_${groupList.groupSn}_${itemList.artclSn}" id="" class="select" rsType="select">
					                            <option value="" selected="selected">-- 항목선택 --</option>
					                            <c:forEach items="${itemList.optList}" var="optList" >
					                                <option value="${optList.optCn}" name="item_${groupList.groupSn}_${itemList.artclSn}">${optList.optCn}</option>
					                            </c:forEach>
					                        </select>
					                    </div>
					                </div>
								</c:if>
				                <!-- // 셀렉트박스 -->
				
				                <!-- 파일 -->
								<c:if test="${itemList.artclTypeCd eq 'fileIem'}">
					                <div class="cate_wrap">
					                    <h5 class="cStyle"><c:if test="${itemList.esntlYn eq 'Y'}"><span class="mandatory">*</span></c:if>${itemList.artclNm}</h5>
						                    <div class="desc cate">
						                        <p>${itemList.artclExpln}</p>
						                    </div>
					                    <div class="frm_file">
											<op:fileUpload view="basic" name="rspnsFile" count="1" size="${itemList.lmtFileSz}M" exts="${itemList.prmsnFileExtnMttr}" fileIdNm="file_${groupList.groupSn}_${itemList.artclSn}" />
					                        <i>${itemList.prmsnFileExtnMttr} 형식으로 된 파일만 등록할 수 있습니다.</i>
					                    </div>
					                </div>
				                </c:if>
				                <!-- // 파일 -->
				            </div>
				            
			            </c:if>
		            </c:forEach>
		            <!-- // 항목 -->
		        </div>
			</c:if>
        </c:forEach>
        <!-- // 그룹 -->


        <!-- 개인정보제공동의 -->
        <c:choose>
        	<c:when test="${selectForm.wrtrInfoClctSttsNo eq '0'}"> </c:when>
        	<c:otherwise>
        		<h4 class="cStyle">개인정보제공동의</h4>
				<div class="frm_agree">
					<div class="cont">
						${selectForm.prvcClctAgreCn}
					</div>
					<div class="frm_radioChk">
						<ul>
							<li>
								<input type="radio" value="Y" id="agree" name="prvcClctAgreCn" class="radio-inline radio-primary">
								<label for="agree">동의</label>
							</li>
							<li>
								<input type="radio" value="N" id="disAgree" name="prvcClctAgreCn" class="radio-inline radio-primary" checked>
								<label for="disAgree">미동의</label>
							</li>
						</ul>
					</div>
				</div>
        	</c:otherwise>        	
        </c:choose>
        <!-- / 개인정보제공동의 -->


        <!-- 버튼 -->
        <div class="frm_btn">
            <button type="button" class="" id="opPrevious">이전으로</button>
            <button type="button" class="submit" onclick="save()">제출하기</button>
        </div>
        <!-- // 버튼 -->
    </form>
</div> 
<!-- // 폼빌더 신청화면 -->
</body>
</html>