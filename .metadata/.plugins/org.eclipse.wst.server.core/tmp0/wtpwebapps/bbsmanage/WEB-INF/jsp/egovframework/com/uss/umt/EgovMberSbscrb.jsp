<%
 /**
  * @Class Name : EgovMberSbscrb.jsp
  * @Description : 일반회원등록 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.02    조재영          최초 생성
  *   2016.06.13    장동한          표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스 개발팀 조재영
  *  @since 2009.03.02
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssUmt.userManage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mberManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/ccm/zip/EgovZipPopup.js' />" ></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script type="text/javaScript" language="javascript" defer="defer">
/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){

	//모달 셋팅
	fn_modal_setting();

}
/*********************************************************
 * 모달셋팅
 ******************************************************** */
function fn_modal_setting(){
	//버튼에 모달 연결
	$("#btnMbrId").egovModal( "egovModal" );
	
	//타이틀 설졍
	$("#egovModal").setEgovModalTitle("<spring:message code="comUssUmt.userManageRegistModal.title" />"); //아이디 중복 확인
	var content = "";
	content = content + "<div class='modal-alignL' style='margin:5px 0 0 0'>"+"<spring:message code="comUssUmt.userManageRegistModal.userIsId" /> :"+"</div>"; //사용할아이디
	content = content + "<div class='modal-alignL'>"+"<input type='text' id='checkIdModal' name='checkIdModal' value='' size='20' maxlength='20' />"+"</div>";	
	content += "<div style='clear:both;'></div>";
	content += "<div id='divModalResult' style='margin:10px 0 0 0'><spring:message code="comUssUmt.userManageRegistModal.initStatus" /></div>"; //결과 : 중복확인을 실행하십시오.
	//모달 body 설정
	$("#egovModal").setEgovModalBody(content);

	var footer = "";
	//footer += "<div class='modal-btn'><button class='btn_s2' id='btnModalOk' onclick='fn_id_checkOk()'>확인</button></div>";
	//footer += "<div class='modal-btn'><button class='btn_s2' id='btnModalSelect' onclick='fn_id_check()'>조회</button></div>";
	footer += "<span class='btn_style1 blue' id='btnModalOk' onclick='fn_id_checkOk()'><a href='#'>확인</a></span>&nbsp;";
	footer += "<span class='btn_style1 blue' id='btnModalSelect' onclick='fn_id_check()'><a href='#'>조회</a></span>&nbsp;";
	//모달 footer 설정
	$("#egovModal").setEgovModalfooter(footer);
	
	//엔터이벤트처리
	$("input[name=checkIdModal]").keydown(function (key) {
		if(key.keyCode == 13){
			fn_id_check();	
		}
	});
	
	footer = null;
	content = null;
}
/*********************************************************
 * 아이디 체크 AJAX
 ******************************************************** */
function fn_id_check(){	
	$.ajax({
		type:"POST",
		url:"<c:url value='/uss/umt/EgovIdDplctCnfirmAjax.do' />",
		data:{
			"checkId": $("#checkIdModal").val()			
		},
		dataType:'json',
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				
				if(returnData.usedCnt > 0 ){
					//는 이미 사용하고 있는 아이디입니다.다른 아이디를 입력해 주시겠습니까?
					$("#divModalResult").html("<font color='red'><spring:message code="comUssUmt.userManageRegistModal.result" /> : ["+returnData.checkId+"]<spring:message code="comUssUmt.userManageRegistModal.useMsg" /></font>");
				}else{
					//사용가능한 아이디입니다.
					$("#divModalResult").html("<font color='blue'><spring:message code="comUssUmt.userManageRegistModal.result" /> : ["+returnData.checkId+"]<spring:message code="comUssUmt.userManageRegistModal.notUseMsg" /></font>");
				}
			}else{ alert("ERROR!");return;} 
		}
		});
}

/*********************************************************
 * 아이디 체크 확인
 ******************************************************** */
function fn_id_checkOk(){
	$.ajax({
		type:"POST",
		url:"<c:url value='/uss/umt/EgovIdDplctCnfirmAjax.do' />",
		data:{
			"checkId": $("#checkIdModal").val()			
		},
		dataType:'json',
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				if(returnData.usedCnt > 0 ){
					alert("<spring:message code="comUssUmt.userManageRegistModal.useMsg" />"); //이미 사용하고 있는 아이디입니다.다른 아이디를 입력해 주시겠습니까?
					return;
				}else{
					
					$("input[name=mberId]").val(returnData.checkId);
					$("#egovModal").setEgovModalClose();
				}
			}else{ alert("ERROR!");return;} 
		}
		});
}


function fnIdCheck1(){
    var retVal;
    var url = "<c:url value='/uss/umt/EgovIdDplctCnfirmView.do'/>";
    var varParam = new Object();
    varParam.checkId = document.mberManageVO.mberId.value;
    var openParam = "dialogWidth:303px;dialogHeight:250px;scroll:no;status:no;center:yes;resizable:yes;";
    return false;
    retVal = window.showModalDialog(url, varParam, openParam);
    if(retVal) {
    	document.mberManageVO.mberId.value = retVal;
    }
}

function showModalDialogCallback(retVal) {
	if(retVal) {
	    document.mberManageVO.mberId.value = retVal;
	}
}

function fnListPage(){
    document.mberManageVO.action = "<c:url value='/uss/umt/EgovMberManage.do'/>";
    document.mberManageVO.submit();
}

function fnInsert(form){
	if (form.recommenderId.value !="" && form.recommenderId.value != null) {
		kkk(form);
    }else{
    	alert("추천자 아이디를 입력하세요!"); return false;
	}
}

function kkk(form) {
		$.ajax({
			type:"POST",
			url:"<c:url value='/uss/umt/EgovRecommenderIdCnfirmAjax.do' />",
			data:{
				"checkId": $("#recommenderId").val()			
			},
			dataType:'json',
			timeout:(1000*30),
			success:function(returnData, status){
				if(status == "success") {
					if(returnData.usedCnt > 0 ){
		    			if (form.mberId.value =="") {
		    			            alert("<spring:message code="comUatUia.validate.idCheck" />"); return false; <%-- 아이디를 입력하세요 --%>
		    			 } else if (form.mberNm.value =="") {
		    			        	alert("회원 이름을 입력하세요"); return false;    			        
	    			    } else if (form.password.value =="") {
	    			        alert("<spring:message code="comUatUia.validate.passCheck" />"); return false; // 비밀번호를 입력하세요 
	    			    } else if(form.password.value != form.password2.value){
	    			    	alert("<spring:message code="fail.user.passwordUpdate2" />"); return false;// 비밀번호 확인이 맞지 않습니다
	    			    } else if (form.areaNo.value =="") {
	    			    	alert("전화 국번호를 입력하세요"); return false;
	    			    } else if (form.middleTelno.value =="") {
	    			    	alert("전화 중간번호를 입력하세요"); return false;
	    			    } else if (form.endTelno.value =="") {
	    			    	 alert("전화 끝번호를 입력하세요"); return false; 
	    			    } else if (form.year.value =="") {
	    			    	alert("년도를 입력하세요"); return false;
	    			    } else if (form.month.value =="") {
	    			    	alert("월을 입력하세요"); return false;
	    			    } else if (form.day.value =="") {
	    			    	 alert("날짜를 입력하세요"); return false; 
	    			    } else if (form.sex.value =="") {
	    			    	 alert("성별을 입력하세요"); return false; 
	    			    } else if (form.adres.value =="") {
	    			    	 alert("주소를 입력하세요"); return false; 
	    			    ////////////////////////////////
	    				}
		    			
				}else{ 
					alert("<spring:message code="fail.common.recommenderId" />"); // 추천자 아이디를 찾을수 없습니다.
					return false;
			}
					if(confirm("<spring:message code="common.regist.msg" />")){
						form.submit();
						return true;
					}
			}else{ alert("ERROR!");return;} 
		}
	 });	    		
}

//글자 입력후 포커스가 다음 input 박스로 자동으로 이동되는 소스
function siche_next() {
	if (document.mberManageVO.areaNo.value.length==3) {
	document.mberManageVO.middleTelno.focus();
	return;
	}
}

function siche_next2() {
	if (document.mberManageVO.middleTelno.value.length==4) {
		document.mberManageVO.endTelno.focus();
		return;
		}
}
function siche_next22() {
	if (document.mberManageVO.year.value.length==4) {
		document.mberManageVO.month.focus();
		return;
		}
}
function siche_next3() {
	if (document.mberManageVO.month.value.length==2) {
	document.mberManageVO.day.focus();
	return;
	}
}
function siche_next33() {
	if (document.mberManageVO.day.value.length==2) {
	document.mberManageVO.sex.focus();
	return;
	}
}

function enterkey() {
    if (window.event.keyCode == 13) {
         login();
    }
}

</script>
<style>
.modal-content {width: 400px;}
</style>
</head>
<body onload="fn_egov_init()">
<form:form commandName="mberManageVO" action="${pageContext.request.contextPath}/uss/umt/EgovMberSbscrb.do" name="mberManageVO"  method="post" onSubmit="fnInsert(document.forms[0]); return false;"> 
<div id="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 10%;"><col style="width: ;">
	</colgroup>
	<tbody>
	<!-- 추천자아이디 -->
		<c:set var="title"><spring:message code="comUssUmt.userManageRegist.rcmdId"/></c:set>
		<tr>
			<th><label for="recommenderId">${title}</label>  </th>
			<td class="left">
                    <form:input path="recommenderId" id="recommenderId" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="20" maxlength="15" />
                    <div><form:errors path="recommenderId" cssClass="error" /></div>
			</td>
		</tr> 
		<!-- 입력/선택 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="inputSelect"><spring:message code="input.cSelect" /></c:set>
		<!-- 일반회원아이디 -->
		<c:set var="title"><spring:message code="comUssUmt.userManageRegist.id"/></c:set>
		<tr>
			<th><label for="mberId">${title}</label><font color='blue'> </th>
			
			<td class="left">
			<font color='red'>아이디 중복 방지를 위해 </font>
			<button id="btnMbrId" class="btn_s2" onClick="return false;"
				<spring:message code="input.button" />"><spring:message code="comUssUmt.userManageRegistBtn.idSearch" /></button>
				<font color='red'>을 클릭합니다. </font>
				<div><form:errors path="mberId" cssClass="error" /></div>
				<form:input path="mberId" id="mberId" title="${title} ${inputTxt}" size="20" readonly="true" maxlength="20" style="width:80%;" />				
			</td>
		</tr>
		<!-- 일반회원이름 -->
		<c:set var="title"><spring:message code="comUssUmt.userManageRegist.name"/></c:set>
		<tr>
			<th><label for="mberNm">${title}</label>  </th>
			<td class="left">
				<form:input path="mberNm" title="${title} ${inputTxt}" size="50" maxlength="50" />
				<div><form:errors path="mberNm" cssClass="error" /></div> 
			</td>
		</tr><tr><td></td></tr>
		
		<!-- 비밀번호 -->
		<c:set var="title"><spring:message code="comUssUmt.userManageRegist.pass"/></c:set>
		<tr>
			<th><label for="password">${title}</label> </th>
			<td class="left">
				<form:password path="password" title="${title} ${inputTxt}" size="50" maxlength="20" />
				<div><form:errors path="password" cssClass="error" /></div> 
			</td>
		</tr><tr><td></td></tr>
		<!-- 비밀번호확인 -->
		<c:set var="title"><spring:message code="comUssUmt.userManageRegist.passConfirm"/></c:set>
		<tr>
			<th><label for="password2">${title}</label></th>
			<td class="left">
			<input name="password2" id="password2" title="${title} ${inputTxt}" type="password" size="50" maxlength="20" />
			</td>
		</tr>
		<tr>
			<th></th>
			<td class="left">
			<b><font size="3em" color="red">아이디</font>와 <font size="3em" color="blue">비밀번호</font>는 꼭 메모하여 기억합니다.</b>
			</td>
		</tr>
		<!-- 전화번호 -->
		<c:set var="title"><spring:message code="comUssUmt.userManageRegist.tel"/></c:set>
		<tr>
			<th><label for="areaNo">${title}</label>  </th>
			<td class="left">
                    <form:input path="areaNo" id="areaNo" title="전화번호" cssClass="txaIpUmt" size="5" maxlength="3" style="width:22px;" onkeyup="siche_next()" />
                    - <form:input path="middleTelno" id="middleTelno" cssClass="txaIpUmt" size="5" maxlength="4" style="width:31px;" onkeyup="siche_next2()" />
                    - <form:input path="endTelno" id="endTelno" cssClass="txaIpUmt" size="5" maxlength="4" style="width:31px;" /> 가능한 <font color='red'><b>연락</b></font>이 쉽게 <font color='red'><b>핸드폰 번호</b></font>를 입력합니다.
                    <div><form:errors path="areaNo" cssClass="error" /></div>
                    <div><form:errors path="middleTelno" cssClass="error" /></div>
                    <div><form:errors path="endTelno" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 생년월일 -->
		<c:set var="title"><spring:message code="comUssUmt.userManageRegist.yyyymmddsex"/></c:set>
		<tr>
			<th><label for="birthDay">${title}</label>  </th>
			<td class="left">
                    <form:input path="year" id="year" cssClass="txaIpUmt" size="5" maxlength="4" style="width:27px;" onkeyup="siche_next22()" />
                    . <form:input path="month" id="month" cssClass="txaIpUmt" size="5" maxlength="2" style="width:16px;" onkeyup="siche_next3()" />
                    . <form:input path="day" id="day" cssClass="txaIpUmt" size="5" maxlength="2" style="width:16px;" onkeyup="siche_next33()" />
                    . <form:input path="sex" id="sex" cssClass="txaIpUmt" size="5" maxlength="1" style="width:10px;" />
                    <font color='blue'> <b>년도</font> 4자리</b>&nbsp;&nbsp;<font color='red'><b>월</font> 두자리,</b>&nbsp;&nbsp;<font color='blue'><b>날짜 </font>두자리,</b>
                    <font color='red'> <b>성별</b></font><b><font color='blue'>(남자=1,</font><font color='blue'>여자=2)</font> 숫자, 한자리</b>를 입력합니다.
        
                    <div><form:errors path="year" cssClass="error" /></div>
                    <div><form:errors path="month" cssClass="error" /></div>
                    <div><form:errors path="day" cssClass="error" /></div>
                    <div><form:errors path="sex" cssClass="error" /></div>
			</td>
		</tr>
		<c:set var="title"><spring:message code="comUssUmt.userManageRegist.addr"/></c:set>
		<tr>
			<th><label for="adres">${title}</label>  </th>
			<td class="left"><b>도로명 또는 지번을</b>입력합니다.
                    <form:input path="adres" id="adres" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="70" maxlength="100" />
                    <div><form:errors path="adres" cssClass="error" /></div>
			</td>
		</tr>
		<input type="hidden" name="mberSttus" value="DEFAULT" />
	</tbody>
	</table>
	<!-- 하단 버튼 --> 
	<div class="btn">
	<input onkeyup="enterkey();" type="hidden"  value="<spring:message code="button.create" />"  title="<spring:message code="button.create" />" />
	<div style='float: left;'>
	<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
	</div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 	  
	</div><div style="clear:both;"></div>

</div><!-- div end(wTableFrm)  -->

 <!-- 우편번호검색 -->
 <input type="hidden" name="zip_url" value="<c:url value='/sym/ccm/zip/EgovCcmZipSearchPopup.do'/>" />
</form:form>

<!-- Egov Modal include  -->
<c:import url="/EgovModal.do" charEncoding="utf-8">
	<c:param name="scriptYn" value="Y" />
	<c:param name="modalName" value="egovModal" />
</c:import>

</body>
</html>
