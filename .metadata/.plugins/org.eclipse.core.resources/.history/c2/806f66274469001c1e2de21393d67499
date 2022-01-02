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
  *  @since 2021.07.01
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
<c:set var="pageTitle"><spring:message code="comCopBbs.boardMasterVO.sajeontitle"/></c:set>
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
function fncGoAfterErrorPage(){
    history.back(-2);
}
</script>
</head>
<body onLoad="fn_egov_init();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<!-- 상단타이틀  /cop/bbs/updateBBSMaster.do -->
 <form:form commandName="boardMasterVO" action="${pageContext.request.contextPath}/cop/bbs/updateMna.do" method="post" onSubmit="fn_egov_updt_bbs(document.forms[0]); return false;">  

 <div id="wTableFrm">
	<h2>${pageTitle} <spring:message code="title.updateSajeonTu" />
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
			<th><label for="city">${title} </label></th>
			<td class="left">
			    <form:input path="city" title="${title} ${inputTxt }" size="70" maxlength="70" readonly="true" />
   				<div><form:errors path="city" cssClass="error" /></div>     
			</td>				
		</tr>
		<tr>
		
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.list.gungu"/> </c:set>
			<th><label for="gungu">${title} </label></th>
			<td class="left">
			    <form:input path="gungu" title="${title} ${inputTxt }" size="70" maxlength="70"/>
   				<div><form:errors path="gungu" cssClass="error" /></div>     
			</td>
						
		</tr>	
		<tr>
		
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.list.tupyoso"/> </c:set>
			<th><label for="tupyoso">${title} </label></th>
			<td class="left">
			    <form:input path="tupyoso" title="${title} ${inputTxt }" size="70" maxlength="70"/>
   				<div><form:errors path="tupyoso" cssClass="error" /></div>     
			</td>
						
		</tr>	
		<tr>
		
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.detail.ampm"/> </c:set>
			<th><label for="ampm">${title} </label></th>
			<td class="left">
			    <form:input path="ampm" title="${title} ${inputTxt }" size="70" maxlength="70"/>
   				<div><form:errors path="ampm" cssClass="error" /></div>     
			</td> 
			<%--<input type="text" name="RECOMMENDER_ID" value="<c:out value="${boardMasterVO.RECOMMENDER_ID}"/>" id="RECOMMENDER_ID" />
	        <input type="text" name="RECOMMENDER_NAME" value="<c:out value="${boardMasterVO.RECOMMENDER_NAME}"/>" id="RECOMMENDER_NAME" />
			 --%>				
		</tr>		              
	</tbody>
	</table>

	<!-- 하단 버튼 -->
	<div class="btn">
	<span class="btn_style1 blue"><a href="javascript:fncGoAfterErrorPage();">이전 페이지</a></span>
	    &nbsp;&nbsp;&nbsp;
	
		<span class="btn_style1 blue"><a href="<c:url value='/cop/bbs/insertSajeonTuView.do?no=${boardMasterVO.no}'/>" > 
		사전투표 참관인 신청 </a></span><!-- 참관인 신청 -->		 		
		&nbsp;&nbsp;&nbsp;
		
		<span class="btn_style1 blue"><a href="<c:url value='/cop/bbs/selectSajeonSeon.do' /><c:if test='${boardMasterVO.no != null}'>?cmmntyId=${boardMasterVO.no}</c:if>">
		사전 투표소 목록 </a></span><!-- 사전투표소  -->
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
	</div>
	<div style="clear:both;"></div>

<!-- validator 값 체크용 -->
<input name="replyPosblAt" type="hidden" value="<c:out value='${boardMasterVO.replyPosblAt}'/>">
<input name="cmmntyId" type="hidden" value="<c:out value='${boardMasterVO.cmmntyId}'/>">
<input name="no" type="hidden" value="<c:out value='${boardMasterVO.no}'/>">
</form:form>

</body>
</html>
