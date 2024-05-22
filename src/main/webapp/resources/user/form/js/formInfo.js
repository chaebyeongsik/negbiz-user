/**
 * BD_selectForm.jsp
 * 폼 신청 관련 기간 및 선착순 체크
 */
function save() {
	// 시작날짜/종료날짜 형식 포맷
	var startDt = $("#startDt").val().replace(/\//g,'').replace(' ', '').replace(':', '');
 	var endDt = $("#endDt").val().replace(/\//g,'').replace(' ', '').replace(':', '');
 	
 	// 현재날짜 구하기
 	var date = new Date();
 	var year = date.getFullYear().toString();
 	var month = (date.getMonth()+1).toString();
 	var day = date.getDate().toString();
 	var hour = date.getHours().toString();
 	var minute = date.getMinutes().toString();
 	
 	if(month.length == 1) { month = "0" + month; }
 	if(day.length == 1) { day = "0" + day; }
 	if(hour.length == 1) { hour = "0" + hour; }
 	
 	var today = year + month + day + hour + minute;
 	/*console.log("startDt : " + startDt + " / endDt : " + endDt + " / today : " + today);*/
 	
 	// 신청인원/제한인원 구하기
 	var rspnsNope = parseInt($("#rspnsNope").val());	// 응답인원
 	var lmtNope = parseInt($("#lmtNope").val());		// 제한인원
 	/*console.log("rspnsNope : " + rspnsNope + " / lmtNope : " + lmtNope);*/
 	
 	// 신청기간에 따른 신청 막기
 	if(startDt > today){
 		alert("신청기간이 아닙니다.");
 		return;
	} else if(endDt < today){
		alert("신청기간이 종료되었습니다.");
 		return;
	}
 	
 	// 선착순 제한 시 인원에 따라 신청 막기
 	if(rspnsNope >= lmtNope){
 		alert("선착순 마감되었습니다.")
 		return;
 	}
 	
 	$("#dataForm").submit();
}


