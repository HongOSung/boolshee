<%
 /**
  * @Class Name : distwachInsert.jsp
  * @Description : 선거구 지킴이 등록 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2021.10.14    홍오성          최초 생성
  *
  *  @author 불씨 개발팀 홍오성
  *  @since 2021.07.01
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
<%@ page import="java.util.Enumeration" %>

<c:set var="pageTitle"><spring:message code="comUssUmt.boolsheePolWatch.title"/></c:set>
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

function fnInsert(form){
	/*
	alert('00 form.pllngplce_S.value='+form.pllngplce_S.value);
	alert('10 form.no.value='+form.no.value);
	alert('11 form.telNo.value='+form.telNo.value);
	alert('22 form.mberNm_S.value='+form.mberNm_S.value);
	alert('33 form.mberId_S.value='+form.mberId_S.value);
	*/
	if(form.telNo.value != form.telNoCk.value){
        alert("<spring:message code="fail.user.telNoCk" />");
        return false;
    }	
	if(confirm("<spring:message code="common.regist.msg" />")){
		$.ajax({
    		type:"POST",
    		url:"<c:url value='/uss/umt/EgovPollWatchMemberDpKeyCnfirmAjax.do' />",
    		data:{
    			"no": $("#no").val(),
    			"mberId": $("#mberId").val()			
    		},
    		dataType:'json',
    		timeout:(1000*30),
    		success:function(returnData, status){
    			if(status == "success") {
    				if(returnData.usedCnt > 0 ){
    					alert("<spring:message code="fail.common.watchdogDpKey" />"); // 이미 이 선거구 지킴이로 등록되어 있습니다.
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
<form:form commandName="mberManageVO" action="${pageContext.request.contextPath}/cop/bbs/insertPollWatch.do" name="mberManageVO"  method="post" onSubmit="fnInsert(document.forms[0]); return false;"> 
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
		<!-- 소시민단체 회원이름 -->
		<c:set var="title"><spring:message code="comUssUmt.boolsheeManageRegist.name"/></c:set>
		<tr>
			<th><label for="mberNm">${title}</label></th>
			<td class="left">
				<input value="<c:out value='${mberNm}'/>" title="${title} ${inputTxt}" size="50" maxlength="50" readonly="true" /> 
			</td>
		</tr>
		<!-- 비밀번호힌트정답 => 본인확인 전화번호-->
		<c:set var="title"><spring:message code="comUssUmt.userManageRegist.telNoCk"/></c:set>
		<tr>
			<th><label for="telNo">${title}</label></th>
			<td class="left">
				<form:input path="telNo" id="telNo" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="50" maxlength="100" />
				<div><form:errors path="telNo" cssClass="error"/></div>
			</td>
		</tr> 	
		<!-- 그룹아이디 -->
		<c:set var="title"><spring:message code="comUssUmt.boolsheeManageRegist.pllngplce"/></c:set>
		<c:forEach var="bbsNm_result" items="${bbsNm_result}" varStatus="status" begin="0" end="15" step="1">
			<tr>				
				<th><label for="pllngplce">${title}</label></th>
				<td class="left">
	                    <c:out value="${bbsNm_result.pllngplce}"/>
	                     <input type="hidden" name="pllngplce_S" value="<c:out value="${bbsNm_result.pllngplce}"/>" id="pllngplce_S" />			                    				                    	
	                    <input type="hidden" name="telNoCk" value="<c:out value="${bbsNm_result.telNoCk}"/>" id="telNoCk" />
	                    <input type="hidden" name="bldngNm" value="<c:out value="${bbsNm_result.bldngNm}"/>" id="bldngNm" />
	                    <input type="hidden" name="MBER_ID" value="<c:out value="${bbsNm_result.MBER_ID}"/>" id="MBER_ID" />
	                    <input type="hidden" name="MBER_NM" value="<c:out value="${bbsNm_result.MBER_NM}"/>" id="MBER_NM" /> 
	                    <input type="hidden" name="RECOMMENDER_ID" value="<c:out value="${bbsNm_result.RECOMMENDER_ID}"/>" id="RECOMMENDER_ID" />
	                    <input type="hidden" name="RECOMMENDER_NAME" value="<c:out value="${bbsNm_result.RECOMMENDER_NAME}"/>" id="RECOMMENDER_NAME" />
	                   	                    			                    			
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

</div>
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
<input type="hidden" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
	<%
	String no = request.getParameter("no");
	%>
<input type="hidden" name="no" value="<%= no %>" id="no" /> 
<input type="hidden" name="mberId_S" value="<c:out value='${mberId}'/>" id="mberId_S" />
<input type="hidden" name="mberNm_S" value="<c:out value='${mberNm}'/>" id="mberNm_S" />
</form:form>
</body>
</html>
