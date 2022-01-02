<%
 /**
  * @Class Name : EgovBBSMasterRegist.jsp
  * @Description : EgovBBSMasterRegist 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  * @ 2016.06.13   김연호              표준프레임워크 v3.6 개선
  * @ 2018.10.15   최두영             표준프레임워크 V3.8 개선
  *
  *  @author 공통서비스팀 
  *  @since 2009.02.01
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
<c:set var="pageTitle"><spring:message code="comCopBbs.boardMasterVO.noticeReqtitle"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle}<spring:message code="title.create" /></title><!-- 게시판 마스터 등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="boardMasterVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){

	// 첫 입력란에 포커스
	document.getElementById("boardMasterVO").bbsNm.focus();

}

function fn_egov_regist_bbs(form){
	if(form.bbsNm.value == null || form.bbsNm.value ==''){
		alert('알림,행사,질문 명을 입력해 주세요!');form.bbsNm.focus();return false;
	}
	if(form.action.value == null || form.action.value ==''){
		alert('알림,행사,질문 내용을 입력해 주세요!');form.action.focus();return false;
	}
	//alert(1);
	//fnInsert(form);
	// 이하 명령은 듣지 않음??????????? 현재는 별 상관 없음 홍오성 2021.11.29
	alert('validateBoardMasterVO(form)='+validateBoardMasterVO(form));
	if (!validateBoardMasterVO(form)) {	
		return false;
	} else {
		
		var validateForm = document.getElementById("boardMasterVO");

		//방명록 게시판의 경우 답장 불가, 파일첨부 불가
		if(validateForm.bbsTyCode.value == 'BBST03') {
			if(validateForm.replyPosblAt.value == 'Y') {
				alert("<spring:message code="comCopBbs.boardMasterVO.guestReply" />");
				return;
			}
			if(validateForm.fileAtchPosblAt.value == 'Y') {
				alert("<spring:message code="comCopBbs.boardMasterVO.guestFile" />");
				return;
			}
		} else {
			if(validateForm.fileAtchPosblAt.value == 'Y' && validateForm.atchPosblFileNumber.value == '0') {
				alert('첨부가능파일숫자를 선택하세요.');
				return;
			}
		}
		
		if(confirm("<spring:message code="common.regist.msg" />")){	
			form.submit();	
		}
	} 
	//alert(2);
	fnInsert(form);
}

function fnInsert(form){	
	if(confirm("<spring:message code="common.regist.msg" />")){
		alert($("#bbsNm").val());return;// false;
		$.ajax({
    		type:"POST",
    		url:"<c:url value='/uss/umt/EgovPollWatchMemberDpKeyCnfirmAjaxNt.do' />",
    		data:{
    			//"no": $("#no").val(),
    			"bbsNm": $("#bbsNm").val()			
    		},
    		dataType:'json',
    		timeout:(1000*30),
    		success:function(returnData, status){
    			alert(1);return;
    			if(status == "success") {
    				if(returnData.usedCnt > 0 ){
    					alert("<spring:message code="fail.common.NtdogDpKey" />"); // 등록 명이 중복되오니 다른 명으로 등록하시기 바랍니다.
    					return false;
    				}else{
    					//form.submit();
    					return true;    					
    				}
    			}else{ alert("ERROR!");return;} 
    		}
    	});
	}
	
	
}
</script>

</head>
<body onLoad="fn_egov_init();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form:form commandName="boardMasterVO" action="${pageContext.request.contextPath}/cop/bbs/insertBBSMasterNt.do" method="post" onSubmit="fn_egov_regist_bbs(document.forms[0]); return false;"> 
<div id="wTableFrm">
	<!-- 타이틀 -->
	<h2>${pageTitle}<spring:message code="title.create" /></h2><!-- 공지,행사,질문 등록 -->

	<!-- 등록폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle } <spring:message code="title.create" /></caption>
	<colgroup>
		<col style="width: 20%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 게시판명 -->
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.regist.reNtNm"/> </c:set>
		<tr>
			<th><label for="bbsNm">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="bbsNm" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="bbsNm" cssClass="error" /></div>     
			</td>
		</tr>
		<!-- 게시판 소개내용 -->
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.regist.reNtaction"/> </c:set>
		<tr>
			<th><label for="action">${title } <span class="pilsu">*</span></label></th>
			<td class="nopd">
				<form:textarea path="action" title="${title} ${inputTxt}" cols="100" rows="10" />   
				<div><form:errors path="action" cssClass="error" /></div>  
			</td>
		</tr>
		<%
		Object mberNm = request.getAttribute("mberNm"); 
		Object mberId = request.getAttribute("mberId");
		//Object tel = request.getAttribute("tel");
		%>
		
		<%--<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.regist.tel"/> </c:set>
		<tr>
			<th><label for="tel">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="tel" title="${title} ${inputTxt}" size="70" maxlength="70" value="${boardMasterVO.tel }" readonly="true"/>
   				<div><form:errors path="tel" cssClass="error" /></div>     
			</td>
		</tr> --%>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.regist.reNtfullnm"/> </c:set>
		<tr>
			<th><label for="fullnm">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="fullnm" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="fullnm" cssClass="error" /></div>     
			</td>
		</tr>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.regist.mberNm"/> </c:set>
		<tr>
			<th><label for="mberNm">${title}<span class="pilsu">*</span></label></th>
			<td class="left">
			    <input name="mberNm" id="mberNm" type="text" value="${mberNm }" readonly="true" />    
			</td>
		</tr> 
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.regist.reNtmembers"/> </c:set>
		<tr>
			<th><label for="members">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="members" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="members" cssClass="error" /></div>     
			</td>
		</tr>
		
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
		<input type="submit" class="s_submit" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" /><!-- 등록 -->
		<span class="btn_s"><a href="<c:url value='/cop/bbs/selecthelpRequestInfsNt.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
	</div><div style="clear:both;"></div>
	
</div>

<input name="cmmntyId" type="hidden" value="<c:out value='${searchVO.cmmntyId}'/>">
<input name="blogId" type="hidden" value="<c:out value='${searchVO.blogId}'/>">
<input name="blogAt" type="hidden" value="<c:out value='${searchVO.blogAt}'/>">
<input name="cmd" type="hidden" value="<c:out value='save'/>">
<input name="mberId" id="mberId" type="hidden" value="${mberId }"/> 
</form:form>

</body>
</html>

