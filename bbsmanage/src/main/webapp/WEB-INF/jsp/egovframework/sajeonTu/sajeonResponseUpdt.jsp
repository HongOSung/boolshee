<%
 /**
  * @Class Name : mNaUpdt.jsp
  * @Description :  화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2021.0920   홍오성              최초 생성
  *  @author 불씨서비스팀 
  *  @since 2021.10.13
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comCopBbs.boardMasterVO.SajeonResponsetitle"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle } <spring:message code="title.update" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
 <title>CSS</title>
    <style>
      body {
        font-family: "Noto Sans CJK KR";
      }
      .jb-700 {
        font-weight: 700;
      }
    </style>
<link href="https://cdn01.jotfor.ms/static/formCss.css?3.3.27099" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="boardMasterVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_updt_bbs(form, no){
	var validateForm = document.getElementById("boardMasterVO");
			if(validateForm.replyPosblAt.value == 'Y') {
				alert("<spring:message code="comCopBbs.boardMasterVO.guestReply" />");
				return;
			}
		if(confirm("<spring:message code="common.update.msg" />")){	
			form.submit();	
		}					
}

function resize(obj) {
	  obj.style.height = "1px";
	  obj.style.height = (12+obj.scrollHeight)+"px";
	}
function fncGoAfterErrorPage(){
    history.back(-2);
}
	
</script>
</head>
<body onLoad="fn_egov_init();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<!-- 상단타이틀  /cop/bbs/updateBBSMaster.do -->
 <form:form commandName="boardMasterVO" action="${pageContext.request.contextPath}/cop/bbs/updateSajeonResponseView.do" method="post" onSubmit="fn_egov_updt_bbs(document.forms[0]); return false;">  

 <div class="wTableFrm">
	<h2>${pageTitle} <spring:message code="title.updateGepyoReWatch" />
	<!-- 수정폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.update" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width: 10%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>		
		<tr>		
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.city"/> </c:set>
			<th><label for="city">${title}</label></th>
			<td class="left">
			    <form:input path="city" title="${title} ${inputTxt }" size="70" maxlength="70" readonly="true" />
   				<div><form:errors path="city" cssClass="error" /></div>     
			</td>
					
		</tr>
		<tr>		
		
			<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.gungu"/> </c:set>
			<th><label for="gungu">${title} </label></th>
			<td class="left">
			    <form:input path="gungu" title="${title} ${inputTxt }" readonly="true" size="70" maxlength="70" />
   				<div><form:errors path="gungu" cssClass="error" /></div>     
			</td>		
		</tr>
		<tr><td></td></tr>
		<tr>
			<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.tupyoso"/> </c:set>
			<th><label for="tupyoso">${title} </label></th>
			<td class="left">
			    <form:input path="tupyoso" title="${title} ${inputTxt }" readonly="true" size="70" maxlength="70" />
   				<div><form:errors path="tupyoso" cssClass="error" /></div>     
			</td>
		</tr>
		<tr>
			<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.ampm"/> </c:set>
			<th><label for="ampm">${title} </label></th> 
			<td class="left">
			    <form:input path="ampm" title="${title} ${inputTxt }" readonly="true" size="70" maxlength="70" />
   				<div><form:errors path="ampm" cssClass="error" /></div>     
			</td>		
		</tr>		  
		<tr>
		
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.sajeonMen"/> </c:set>
			<th><label for="mberNm">${title} </label></th>
			<td class="left">
			    <form:input path="mberNm" title="${title} ${inputTxt }" readonly="true" size="70" maxlength="70"/>
   				<div><form:errors path="mberNm" cssClass="error" /></div>     
			</td> 
		</tr>           
	</tbody>
	</table>
	<c:set var="mberId"><c:out value='${boardMasterVO.mberId}'/></c:set>
	<!-- 하단 버튼 -->
	<c:if test="${mberId_L == mberId}">
	<div class="btn">
		<span class="btn_style1 blue"><a href="javascript:fncGoAfterErrorPage();">이전 페이지</a></span>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; 
		<span class="btn_s"><a href="<c:url value='/cop/bbs/deleteSajeonResponse.do?no=${boardMasterVO.no}&mberId=${boardMasterVO.mberId }'/>"  title="<spring:message code="button.delete" />  <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span><!-- 삭제 -->
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; 
		<span class="btn_style1 blue"><a href="<c:url value='/cop/bbs/selectSajeonSeon.do' />
		<c:if test='${boardMasterVO.no != null}'>?cmmntyId=${boardMasterVO.no}</c:if>">
		사전투표소 목록</a></span><!-- 목록 -->
	
	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	 	  
	</div>
	<div style="clear:both;"></div></c:if>
	<c:if test="${mberId_L != mberId}">
	<div class="btn">
		<span class="btn_style1 blue"><a href="javascript:fncGoAfterErrorPage();">이전 페이지</a></span>
		<span class="btn_style1 blue"><a href="<c:url value='/cop/bbs/selectSajeonSeon.do' /><c:if test='${boardMasterVO.no != null}'>?cmmntyId=${boardMasterVO.no}</c:if>">
		사전투표소 목록</a></span><!-- 사전투표소목록 -->
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	<div style="clear:both;"></div></c:if>
</div>

<!-- validator 값 체크용 -->
<input name="replyPosblAt" type="hidden" value="<c:out value='${boardMasterVO.replyPosblAt}'/>">
<input name="cmmntyId" type="hidden" value="<c:out value='${boardMasterVO.cmmntyId}'/>">
<input name="no" type="hidden" value="<c:out value='${boardMasterVO.no}'/>">
<input name="st" type="hidden" value="<c:out value='${boardMasterVO.st}'/>">
<input name="gubun" type="hidden" value="<c:out value='${boardMasterVO.gubun}'/>">
<input name="city" type="hidden" value="<c:out value='${boardMasterVO.city}'/>">
<input name="mberId" type="hidden" value="<c:out value='${boardMasterVO.mberId}'/>">

</form:form>

</body>
</html>
