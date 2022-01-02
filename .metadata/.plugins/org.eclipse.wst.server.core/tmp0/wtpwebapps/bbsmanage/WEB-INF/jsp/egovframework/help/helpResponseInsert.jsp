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

<c:set var="pageTitle"><spring:message code="comUssUmt.boolsheeManage.reHptitle"/></c:set>
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
	alert('00 form.bbsNm_S.value='+form.bbsNm_S.value);
	alert('10 form.bbsId.value='+form.bbsId.value);
	alert('11 form.telNo.value='+form.telNo.value);
	alert('22 form.mberNm_S.value='+form.mberNm_S.value);
	alert('33 form.mberId_S.value='+form.mberId_S.value);
	alert('44 form.mberId.value='+form.mberId.value);
	*/
	if(form.telNo.value != form.telNoCk.value){
        alert("<spring:message code="fail.user.telNoCk" />");
        return false;
    }	
	if(confirm("<spring:message code="common.regist.msg" />")){
		$.ajax({
    		type:"POST",
    		url:"<c:url value='/uss/umt/EgovBbsMemberDpKeyCnfirmAjaxHp.do' />",
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
<form:form commandName="mberManageVO" action="${pageContext.request.contextPath}/cop/bbs/insertBbsMemberHp.do" name="mberManageVO"  method="post" onSubmit="fnInsert(document.forms[0]); return false;"> 

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
		<!-- 도움요청 응답 회원 아이디 -->
		<c:set var="title"><spring:message code="comUssUmt.boolsheeManageRegist.reHpid"/></c:set>
		<tr>
			<th><label for="mberId">${title}</label></th>
			<td class="left">
				<input value="<c:out value='${mberId}'/>" id="mberId" title="${title} ${inputTxt}" size="20" readonly="true" maxlength="20" style="width:80%;" />				
			</td>
		</tr>
		<!-- 도움요청 응답 회원이름 -->
		<c:set var="title"><spring:message code="comUssUmt.boolsheeManageRegist.reHpname"/></c:set>
		<tr>
			<th><label for="mberNm">${title}</label></th>
			<td class="left">
				<input value="<c:out value='${mberNm}'/>" title="${title} ${inputTxt}" size="50" maxlength="50" readonly="true" /> 
			</td>
		</tr>		
		<c:set var="title"><spring:message code="comUssUmt.userManageRegist.passOk"/></c:set>
		<tr>
			<th><label for="telNo">응답자 전화번호</label> <span class="pilsu">*</span></th>
			<td class="left"><font color="red">회원 가입 시 전화번호</font>로 본인 확인합니다<b>(숫자만 입력)</b>.
				<form:input path="telNo" id="telNo" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="15" maxlength="100" />				
				<div><form:errors path="telNo" cssClass="error"/></div>
			</td>
		</tr>
	<%
	String bbsId = request.getParameter("bbsId");
	%>
		<c:set var="title"><spring:message code="comUssUmt.boolsheeManageRegist.reHpbbsNm"/></c:set>
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
		
		<!-- 도움요청 요청 내용 -->
		<c:set var="title"><spring:message code="comUssUmt.boolsheeManageRegist.reHpaction"/></c:set>
		<c:forEach var="bbsNm_result" items="${bbsNm_result}" varStatus="status" begin="0" end="5" step="1">
		<tr>
			<th><label for="action">${title}</label></th>
			<%--<td class="nopd">
				<form:textarea path="action" <c:out value="${bbsNm_result.action}"/>
				 title="${title} ${inputTxt}" cols="100" rows="10" /> 				  
				<input type="text" name="action_S" id="action_S" value="${bbsNm_result.action}" />
				<div><form:errors path="action" cssClass="error" /></div>  
			</td> --%>
			<td class="left">
				<c:out value="${bbsNm_result.action}"/>
				<input type="hidden" name="action_S" id="action_S" value="${bbsNm_result.action}" />
			</td>
		</tr>
		</c:forEach>
		
		<!-- 도움요청 응답 내용 -->
		<c:set var="title"><spring:message code="comUssUmt.boolsheeManageRegist.reHpcontents"/></c:set>
		<tr>
			<th><label for="contents">${title}</label></th>
			<td class="nopd">
				<form:textarea path="contents" title="${title} ${inputTxt}" cols="100" rows="10" />   
				<div><form:errors path="contents" cssClass="error" /></div>  
			</td>
		</tr>
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
