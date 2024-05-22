/**
 * BD_insertFormRspns.jsp
 * 폼 신청 버튼
 */
function save(){
   // 그룹번호 넘기기
   var index = 0;
   var strGrpIds = "";
   $("input[name='inputGroupList']").each(function (i) {
      if(index === 0){
         strGrpIds = $("input[name='inputGroupList']").eq(i).attr("value");
      }else{
         strGrpIds = strGrpIds + "," + $("input[name='inputGroupList']").eq(i).attr("value");
      }
      index ++;
   });            
   $('#grpIds').val(strGrpIds);
   
   // 그룹_항목번호 넘기기
   var index = 0;
   var strItemIds = "";
   $("input[name='inputGroupItemList']").each(function (i) {
      if(index === 0){
         strItemIds = $("input[name='inputGroupItemList']").eq(i).attr("value");
      }else{
         strItemIds = strItemIds + "," + $("input[name='inputGroupItemList']").eq(i).attr("value");
      }
       index ++;
   });      
   $('#itemIds').val(strItemIds);
   
   // 파일 이름 넘기기
   var filenm = "";
   var realFileNm = "";
   $("input[id^='file_']").each(function (i){
      if($(this).val() != "" && $(this).val() != null){
         realFileNm += $(this).attr('id') + ",";
      }
      filenm += $(this).attr('id') + ",";
      /*console.log("realFileNm : " + realFileNm);
      console.log("filenm : " + filenm);*/
   });
   
   
   if(filenm != null){
      var cnt = filenm.length - 1;
      filenm = filenm.substring(0, cnt);
      $("#fileNms").val(filenm);
   }
   if(realFileNm != null){
      var rcnt = realFileNm.length - 1; 
      realFileNm = realFileNm.substring(0, rcnt);
      $("#realFileNms").val(realFileNm);
   }
   
   // 이메일 입력값 합쳐서 보내기
   $("input[rsType='eml']").each(function (i){
      var name = $(this).attr('id'); 
      var arr = name.split("_");          
      var result = ""
      
      for(var i in arr) {
         if($("#eml_"+arr[1]+"_"+arr[2]+"_2").val() == ""){
            result = "";
            $("input[name='item_"+arr[1]+"_"+arr[2]+"']").val(result);
         } else if(arr[3] == 1){
            result = $("#eml_"+arr[1]+"_"+arr[2]+"_1").val() + "@" + $("#eml_"+arr[1]+"_"+arr[2]+"_2").val();
            $("input[name='item_"+arr[1]+"_"+arr[2]+"']").val(result);
         }else{
            continue;
         }
      }          
   });
   
   // 연락처 입력값 합쳐서 보내기
   $("input[rsType='tel']").each(function (i){
      var name = $(this).attr('id');
      var arr = name.split("_");
      var result = ""
      
      for(var i in arr) {
         if($("#tel_"+arr[1]+"_"+arr[2]+"_1").val() == ""){
            result = "";
            $("input[name='item_"+arr[1]+"_"+arr[2]+"']").val(result);
         } else if(arr[3] == 1){
            result = $("#tel_"+arr[1]+"_"+arr[2]+"_1").val() + "-" 
                  + $("#tel_"+arr[1]+"_"+arr[2]+"_2").val() + "-"
                  + $("#tel_"+arr[1]+"_"+arr[2]+"_3").val();
            $("input[name='item_"+arr[1]+"_"+arr[2]+"']").val(result);
         }else{
            continue;
         }
      }          
   });
   
   // 날짜/시간 입력값 합쳐서 보내기
   $("input[rsType='dt']").each(function (i){
      var name = $(this).attr('id');
      var arr = name.split("_");
      var result = ""
      
      for(var i in arr) {
         if($("#dt_"+arr[1]+"_"+arr[2]+"_1").val() == ""){
            result = "";
            $("input[name='item_"+arr[1]+"_"+arr[2]+"']").val(result);
         } else if(arr[3] == 1){
            result = $("#dt_"+arr[1]+"_"+arr[2]+"_1").val() + "[##]" + $("#dt_"+arr[1]+"_"+arr[2]+"_2").val();
            $("input[name='item_"+arr[1]+"_"+arr[2]+"']").val(result);
         }else{
            continue;
         }
      }          
   });
   
   // 주소 입력값 합쳐서 보내기
   $("input[rsType='addr']").each(function (i){
      var name = $(this).attr('id');
      var arr = name.split("_");
      var result = "";
       
      for(var i in arr) {
         if($("#addr_"+arr[1]+"_"+arr[2]+"_1").val() == ""){
            result = "";
            $("input[name='item_"+arr[1]+"_"+arr[2]+"']").val(result);
         } else if(arr[3] == 1){
            result = result = $("#addr_"+arr[1]+"_"+arr[2]+"_1").val() + "[##]"  
                  + $("#addr_"+arr[1]+"_"+arr[2]+"_2").val() + "[##]" 
                  + $("#addr_"+arr[1]+"_"+arr[2]+"_3").val();
            $("input[name='item_"+arr[1]+"_"+arr[2]+"']").val(result);
         }else{
            continue;
         }
      }          
   });
   
   // 체크박스 다중선택 값 보내기
   $("input:hidden[rsType='chck']").each(function (i){
      var name = $(this).attr('name');
      var result = "";
      
      $("input:checkbox[rsType='chck'][name='"+name+"']:checked").each(function (i){
         if(i==0){
            result += $(this).val();
         }else{
            result += "[##]" + $(this).val();
         }
      });
      $("input:hidden[name="+name+"]").val(result);
   });
   
   // 신청자 정보 입력 여부
   var wrtrInfoNo = $("#wrtrInfoNo").val();
   var name = $("#rspnsNm").val();
   var emlId = $("#rspnsEmlId").val();
   var emlSite = $("#rspnsEmlSiteNm").val();
   var rgn = $("#rgnTelno").val();             
   var telofc = $("#telofcTelno").val();
   var indiv = $("#indivTelno").val();
   
   if(wrtrInfoNo == '1') {
      if(!name) {
         alert("신청자정보의 이름을 입력해주세요.");
         return;
      } else if(!emlId || !emlSite) {
         alert("신청자정보의 이메일을 입력해주세요.");
         return;
      }
   } else if(wrtrInfoNo == '2') {
      if(!name) {
         alert("신청자정보의 이름을 입력해주세요.");
         return;
      } else if(!emlId || !emlSite) {
         alert("신청자정보의 이메일을 입력해주세요.");
         return;
      } else if(!rgn || !telofc || !indiv) {
         alert("신청자정보의 연락처를 입력해주세요.");
         return;
      }
   }
   
   // 필수체크
   for(var i=0; i<groupList.length; i++) {            
      // input
      var answer = $("input[name='"+groupList[i]+"']").val();
      var type = $("input[name='"+groupList[i]+"']").attr('rsType');
      
      // file
      var temp = groupList[i];
      temp = temp.replace("item", "file");
      var fileId = $("input[type='file'][id='"+temp+"']").attr('id');
      
      // select & textarea
      if(type == '' || type === undefined){
         var typeFile = $("select[name='"+groupList[i]+"']").attr('rsType');
         if(typeFile == 'select'){
            answer = $("select[name='"+groupList[i]+"'] option:selected").val();
         }
         var typeText = $("textarea[name='"+groupList[i]+"']").attr('rsType');
         if(typeText == 'textarea'){
            answer = $("textarea[name='"+groupList[i]+"']").val();
         }
      }
   
      if(type == 'rdo') {                                    // 유형이 라디오버튼일 경우
         answer = $("input[name='"+groupList[i]+"']:checked").val();
      } else if(fileId != "" && fileId != undefined){               // 유형이 파일일 경우 
         var fileVal = $("input[id='"+fileId+"']").val();
         answer = fileVal;         
         /*console.log("file answer : "+answer);*/
      }
      
      if(answer == "" || answer === undefined){
         alert("필수항목은 전부 작성해주세요.");
         console.log(i + "번째 : " + groupList[i] + ", answer : " + answer);
         return;
      }
   }
   
   // 개인정보 제공 동의 여부
   if($("input:radio[id='disAgree']").is(":checked")) {
      alert("개인정보 제공 동의를 체크해주세요.")
      $("input:radio[id='agree']").focus();
      return false;
   }
   
   $("#dataForm").submit();
}    