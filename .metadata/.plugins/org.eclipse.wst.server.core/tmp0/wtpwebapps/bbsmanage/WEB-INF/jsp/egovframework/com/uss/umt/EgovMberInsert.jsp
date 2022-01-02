<%
 /**
  * @Class Name : EgovMberInsert.jsp
  * @Description : 소시민단체 가입 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2021.07.25    홍오성          최초 생성
  *
  *  @author 불씨 개발팀 홍오성
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
<%@ page import="java.util.Enumeration" %>

<c:set var="pageTitle"><spring:message code="comUssUmt.boolsheeManage.title"/></c:set>
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
					//사용할수 없는 아이디입니다.
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
					alert("<spring:message code="comUssUmt.userManageRegistModal.noIdMsg" />"); //중복되는 아이디가 있습니다.
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
	/*
	alert('00 form.bbsNm_S.value='+form.bbsNm_S.value);
	alert('10 form.bbsId.value='+form.bbsId.value);
	alert('11 form.telNo.value='+form.telNo.value);
	alert('22 form.mberNm_S.value='+form.mberNm_S.value);
	alert('33 form.mberId_S.value='+form.mberId_S.value);
	alert('44 $("#bbsNm_S").val()='+$("#bbsNm_S").val());
	*/
	if(form.telNo.value != form.telNoCk.value){
        alert("<spring:message code="fail.user.telNoCk" />");
        return false;
    }	
	if(confirm("<spring:message code="common.regist.msg" />")){
		$.ajax({
    		type:"POST",
    		url:"<c:url value='/uss/umt/EgovBbsMemberDpKeyCnfirmAjax.do' />",
    		data:{  	
    			"mberId": $("#mberId").val(),
    			"bbsNm_S": $("#bbsNm_S").val()
    		},
    		dataType:'json',
    		timeout:(1000*30),
    		success:function(returnData, status){
    			if(status == "success") {
    				//alert('returnData.usedCnt='+returnData.usedCnt);
    				if(returnData.usedCnt > 0 ){
    					alert("<spring:message code="fail.common.recommenderDpKey" />"); // 이미 이 단체에 가입되어 있습니다.
    					return false;
    				}else{
    					form.submit();
    					return true;    				
    				}
    			}else{ alert("ERROR!");return;} 
    		}
    	});
	}
}

function fncGoAfterErrorPage(){
    history.back(-2);
}

</script>
<style>
.modal-content {width: 400px;}
</style>
</head>
<body onload="fn_egov_init()">
<form:form commandName="mberManageVO" action="${pageContext.request.contextPath}/cop/bbs/insertBbsMember.do" name="mberManageVO"  method="post" onSubmit="fnInsert(document.forms[0]); return false;"> 

<div id="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle} <spring:message code="title.create" /></h2>

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 22%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력/선택 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="inputSelect"><spring:message code="input.cSelect" /></c:set>
		<!-- 소시민단체회원아이디 -->
		<c:set var="title"><spring:message code="comUssUmt.boolsheeManageRegist.id"/></c:set>
		<tr>
			<th><label for="mberId">${title}</label></th>
			<td class="left">
				<input value="<c:out value='${mberId}'/>" id="mberId" title="${title} ${inputTxt}" size="20" readonly="true" maxlength="20" style="width:80%;" />				
			</td>
		</tr>
		<!-- 소시민단체회원이름 -->
		<c:set var="title"><spring:message code="comUssUmt.boolsheeManageRegist.name"/></c:set>
		<tr>
			<th><label for="mberNm">${title}</label></th>
			<td class="left">
				<input value="<c:out value='${mberNm}'/>" title="${title} ${inputTxt}" size="50" maxlength="50" readonly="true" /> 
			</td>
		</tr>
		<c:set var="title"><spring:message code="comUssUmt.userManageRegist.telNoCk"/></c:set>
		<tr>
			<th><label for="telNo">${title}</label></span></th>
			<td class="left">
				<form:input path="telNo" id="telNo" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="50" maxlength="100" />
				<div><form:errors path="telNo" cssClass="error"/></div>
			</td>
		</tr> 
	<%
	String bbsId = request.getParameter("bbsId");
	%>
		<c:set var="title"><spring:message code="comUssUmt.boolsheeManageRegist.bbsNm"/></c:set>
		<c:forEach var="bbsNm_result" items="${bbsNm_result}" varStatus="status" begin="0" end="5" step="1">
			<tr>				
				<th><label for="bbsNm">${title}</label></th>
				<td class="left">
						<c:out value="${bbsNm_result.bbsNm}"/>
						<input type="hidden" name="bbsNm_S" id="bbsNm_S" value="${bbsNm_result.bbsNm}" />	                    				                    	
	                    <input type="hidden" name="telNoCk" value="<c:out value="${bbsNm_result.telNoCk}"/>" id="telNoCk" />
	                   	                    			                    			
	            </td>
	        </tr>
		</c:forEach>
	</tbody>
	</table>

	<!-- 하단 버튼 --> 
	<div class="btn">
	<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
	<span class="btn_style1 blue"><a href="javascript:fncGoAfterErrorPage();">이전 페이지</a></span>	
	</div><div style="clear:both;"></div>

</div><!-- div end(wTableFrm)  -->

<input name="checkedIdForDel" type="hidden" />
<input name="sosimin" type="hidden" value="소시민단체가입"/>
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
<input type="hidden" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>

<input type="hidden" name="bbsId" value="<%= bbsId %>" id="bbsId" /> 
<input type="hidden" name="mberId_S" value="<c:out value='${mberId}'/>" id="mberId_S" />
<input type="hidden" name="mberNm_S" value="<c:out value='${mberNm}'/>" id="mberNm_S" />
<!-- 비밀번호 힌트 정답 확인 -->

<!-- <input type="hidden" name="telNoCk" value="<c:out value='${telNoCk}'/>" id="telNoCk" /> -->
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
