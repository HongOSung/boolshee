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
<c:set var="pageTitle"><spring:message code="comCopBbs.boardMasterVO.mnatitle"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle } <spring:message code="title.detailListWatchdog" /></title>
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
 * 초기화

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
	<h2>${pageTitle} <spring:message code="title.detailListWatchdog" />
	<br>국회전화  앞 번호  <span style="color:red;">02-784</span>, 구내팩스 앞 번호 <span style="color:red;">02-6788</span></h2><!-- 선거구 수정 및 선거구 지킴이 등록 -->

	<!-- 수정폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.update" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width: 10%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.mnaNm"/> </c:set>
		<tr>
			<th><label for="mnaNm">${title}</label></th>
			<td class="left">
			    <form:input path="mnaNm" title="${title} ${inputTxt }" size="70" maxlength="70" readonly="true" />
   				<div><form:errors path="mnaNm" cssClass="error" /></div>     
			</td>
			<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.hosu"/> </c:set>
			<th><label for="hosu">${title} </label></th>
			<td class="left">
			    <form:input path="hosu" title="${title} ${inputTxt }" size="70" maxlength="70"/>
   				<div><form:errors path="hosu" cssClass="error" /></div>     
			</td>
			<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.telMna"/> </c:set>
			<th><label for="telMna">${title}</label></th>
			<td class="left">
			    <form:input path="telMna" title="${title}${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="telMna" cssClass="error" /></div>     
			</td>
			<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.faxMna"/> </c:set>
			<th><label for="faxMna">${title} </label></th>
			<td class="left">
			    <form:input path="faxMna" title="${title} ${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="faxMna" cssClass="error" /></div>     
			</td>
		</tr>
		<tr><td></td></tr>
		<tr>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.addLocal"/> </c:set>
			<th><label for="addLocal">${title} </label></th>
			<td class="left">
			    <form:input path="addLocal" title="${title} ${inputTxt }" size="70" maxlength="70"/>
   				<div><form:errors path="addLocal" cssClass="error" /></div>     
			</td>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.telLocal"/> </c:set>
			<th><label for="telLocal">${title} </label></th>
			<td class="left">
			    <form:input path="telLocal" title="${title} ${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="telLocal" cssClass="error" /></div>     
			</td>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.faxLocal"/> </c:set>
			<th><label for="faxLocal">${title} </label></th>
			<td class="left">
			    <form:input path="faxLocal" title="${title} ${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="faxLocal" cssClass="error" /></div>     
			</td>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.email"/> </c:set>
			<th><label for="email">${title} </label></th>
			<td class="left">
			    <form:input path="email" title="${title} ${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="email" cssClass="error" /></div>     
			</td>			
		</tr>
		<tr><td></td></tr>	
		<tr>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.homepage"/> </c:set>
			<th><label for="homepage">${title} </label></th>
			<td class="left">
			    <form:input path="homepage" title="${title} ${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="homepage" cssClass="error" /></div>     
			</td>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.facebook"/> </c:set>
			<th><label for="facebook">${title} </label></th>
			<td class="left">
			    <form:input path="facebook" title="${title} ${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="facebook" cssClass="error" /></div>     
			</td>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.twit"/> </c:set>
			<th><label for="twit">${title} </label></th>
			<td class="left">
			    <form:input path="twit" title="${title} ${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="twit" cssClass="error" /></div>     
			</td>	
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.utube"/> </c:set>
			<th><label for="utube">${title} </label></th>
			<td class="left">
			    <form:input path="utube" title="${title} ${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="utube" cssClass="error" /></div>     
			</td>
		</tr>
		<tr><td></td></tr>
		<tr>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.city"/> </c:set>
			<th><label for="city">${title} </label></th>
			<td class="left">
			    <form:input path="city" title="${title} ${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="city" cssClass="error" /></div>     
			</td>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.district"/> </c:set>
			<th><label for="district">${title} </label></th>
			<td class="left">
			    <form:input path="district" title="${title} ${inputTxt }" size="70" maxlength="70"/>
   				<div><form:errors path="district" cssClass="error" /></div>     
			</td>
		    <td></td><td></td>
		   <c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.watchdog"/> </c:set>
			<th><label for="mberNm">${title} </label></th>
			<td class="left">
			    <form:input path="mberNm" title="${title} ${inputTxt }" size="70" maxlength="70"/>
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
		<span class="btn_s"><a href="<c:url value='/cop/bbs/deleteMnaWatchDog.do?no=${boardMasterVO.no}&mberId=${boardMasterVO.mberId }'/>"  title="<spring:message code="button.delete" />  <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span><!-- 삭제 -->
		<span class="btn_s"><a href="<c:url value='/cop/bbs/selectMnaListInfs.do' /><c:if test='${boardMasterVO.no != null}'>?cmmntyId=${boardMasterVO.no}</c:if>">
		<spring:message code="button.districtList" /></a></span><!-- 의원목록 -->	
	</div>
	<div style="clear:both;"></div></c:if>
	<c:if test="${mberId_L != mberId}">
	<div class="btn">
		<span class="btn_style1 blue"><a href="javascript:fncGoAfterErrorPage();">이전 페이지</a></span>
		<span class="btn_s"><a href="<c:url value='/cop/bbs/selectMnaListInfs.do' /><c:if test='${boardMasterVO.no != null}'>?cmmntyId=${boardMasterVO.no}</c:if>">
		<spring:message code="button.districtList" /></a></span><!-- 의원목록 -->
	</div>
	<div style="clear:both;"></div></c:if>
	
	<!-- 하단 버튼 -->
	<%--<div class="btn">
		<span class="btn_style1 blue"><a href="javascript:fncGoAfterErrorPage();">이전 페이지</a></span>
		<span class="btn_s"><a href="<c:url value='/cop/bbs/insertDistwachView.do?no=${boardMasterVO.no}'/>" ><spring:message code="button.Watchdog" /></a></span><!-- 지킴이 등록 -->
		<span class="btn_s"><a href="<c:url value='/cop/bbs/selectMnaListInfs.do' /><c:if test='${boardMasterVO.no != null}'>?cmmntyId=${boardMasterVO.no}</c:if>">
		<spring:message code="button.districtList" /></a></span><!-- 목록 -->
		</div><div style="clear:both;"></div> --%> 
	 
</div>

<!-- validator 값 체크용 -->
<input name="replyPosblAt" type="hidden" value="<c:out value='${boardMasterVO.replyPosblAt}'/>">
<input name="cmmntyId" type="hidden" value="<c:out value='${boardMasterVO.cmmntyId}'/>">
<input name="no" type="hidden" value="<c:out value='${boardMasterVO.no}'/>">
<input name="st" type="hidden" value="<c:out value='${boardMasterVO.st}'/>">
<input name="party" type="hidden" value="<c:out value='${boardMasterVO.party}'/>">
<input name="mberId" type="hidden" value="<c:out value='${boardMasterVO.mberId}'/>"> 
</form:form>

</body>
</html>
