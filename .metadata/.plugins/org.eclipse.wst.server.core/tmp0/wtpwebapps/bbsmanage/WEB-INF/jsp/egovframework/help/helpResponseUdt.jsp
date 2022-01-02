<%
 /**
  * @Class Name : EgovBBSMasterUpdt.jsp
  * @Description : EgovBBSMasterUpdt 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  * @ 2016.06.13   김연호              표준프레임워크 v3.6 개선
  * @ 2018.10.15    최두영             표준프레임워크 V3.8 개선
  *  @author 공통서비스팀 
  *  @since 2009.02.01
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
<c:set var="pageTitle"><spring:message code="comCopBbs.boardMasterVO.helpReqJointitle"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle } <spring:message code="title.update" /></title><!-- 게시판 마스터 수정 -->
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
 ******************************************************** */
function fn_egov_init(){
	//var bbsNmvar=document.getElementById("boardMasterVO").bbsNm;
	//alert(bbsNmvar.value);
	//alert(document.getElementById("boardMasterVO").bbsNm.value);
	// 첫 입력란에 포커스..
	document.getElementById("boardMasterVO").bbsNm.focus();
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_updt_bbs(form, bbsId){
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
		
		if(confirm("<spring:message code="common.update.msg" />")){	
			form.submit();	
		}					
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
				
<form:form commandName="boardMasterVO" action="${pageContext.request.contextPath}/cop/bbs/updateResponseHp.do" method="post" onSubmit="fn_egov_updt_bbs(document.forms[0]); return false;">  
<div id="wTableFrm">
	<h2>${pageTitle} <spring:message code="title.responseconfirm" /></h2><!-- 게시판 마스터 수정 -->

	<!-- 수정폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.update" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.responseconfirm" /></caption>
	<colgroup>
		<col style="width: 20%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 게시판명 -->
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.reHpbbsNm"/> </c:set>
		<tr>
			<th><label for="bbsNm">${title}<span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="bbsNm" title="${title} ${inputTxt }" size="70" maxlength="70" readonly="true" />
   				<div><form:errors path="bbsNm" cssClass="error" /></div>     
			</td>
		</tr>
		<!-- 게시판 등록자 -->
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.requestNm"/> </c:set>
		<tr>
			<th><label for="mberNm">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="mberNm" title="${title} ${inputTxt }" size="70" maxlength="70"  readonly="true" />
   				<div><form:errors path="mberNm" cssClass="error" /></div>     
			</td>
		</tr>
		<%--<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.tel"/> </c:set>
		<tr>
			<th><label for="tel">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="tel" title="${title} ${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="tel" cssClass="error" /></div>     
			</td>
		</tr> --%>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.reHpfullnm"/> </c:set>
		<tr>
			<th><label for="fullnm">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="fullnm" title="${title} ${inputTxt }" size="70" maxlength="70" readonly="true" />
   				<div><form:errors path="fullnm" cssClass="error" /></div>     
			</td>
		</tr>
		<%--<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.reHpmembers"/> </c:set>
		<tr>
			<th><label for="members">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="members" title="${title} ${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="members" cssClass="error" /></div>     
			</td>
		</tr> --%>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.responsemember"/> </c:set>
		<tr>
			<th><label for="member">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="member" title="${title} ${inputTxt }" size="70" maxlength="70" readonly="true"/>
   				<div><form:errors path="member" cssClass="error" /></div>     
			</td>
		</tr>
		<!-- 도움요청내용 -->
		<c:set var="bbsNmtt"><c:out value='${boardMasterVO.bbsNm}'/></c:set>
		<b><p style="color:red; margin-bottom: 8px; ">${bbsNmtt}</p></b>
				<form:textarea path="action" title="${title} ${inputTxt}"  cols="70" rows="15" readonly="true" data-component="textarea" required="" /> 
		              <div class="form-textarea-limit-indicator">
		                <span data-limit="1000" type="Words" data-minimum="-1" data-typeminimum="None" id="input_18-limit">
		                  0/1000
		                </span>
		              </div>
		              <div><form:errors path="action" cssClass="error" /></div>
		<!-- 도움응답내용 --> 
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.responsecontents"/> </c:set>
		<div class="jb-700">
      		<p><span class="pilsu">[</span><label for="contents">${title} <span class="pilsu">]</span></label> </p>
    	</div>
				<form:textarea path="contents" title="${title} ${inputTxt}"  cols="70" rows="15" data-component="textarea" required="" /> 
		              <div class="form-textarea-limit-indicator">
		                <span data-limit="1000" type="Words" data-minimum="-1" data-typeminimum="None" id="input_18-limit">
		                  0/1000
		                </span>
		              </div>
		              <div><form:errors path="contents" cssClass="error" /></div>		              
	</tbody>
	</table>
	<c:set var="mberId"><c:out value='${boardMasterVO.mberId}'/></c:set>
	<div>
		<span class="btn_style1 blue"><a href="javascript:fncGoAfterErrorPage();">이전 페이지</a></span>
		<c:if test="${mberId_S == mberId}">
		<div class="btn">
			<span class="btn_s"><a href="<c:url value='/cop/bbs/deleteBBSMberHp.do?bbsId=${boardMasterVO.bbsId}'/>"  title="<spring:message code="button.delete" />  <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span><!-- 삭제 -->
			<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="button.update" /> <spring:message code="input.button" />" /><!-- 수정 -->
			<span class="btn_s"><a href="<c:url value='/cop/bbs/selectBBSMasterInfsHp.do' /><c:if test='${boardMasterVO.cmmntyId != null}'>?cmmntyId=${boardMasterVO.cmmntyId}</c:if>"  title="<spring:message code="button.requestlist" /> <spring:message code="input.button" />"><spring:message code="button.requestlist" /></a></span><!-- 요청목록 -->
		</div><div style="clear:both;"></div></c:if>
	
	    <c:if test="${mberId_S != mberId}">
	    <div class="btn">
	    		<span class="btn_s"><a href="<c:url value='/cop/bbs/selectBBSMasterInfsHp.do' /><c:if test='${boardMasterVO.cmmntyId != null}'>?cmmntyId=${boardMasterVO.cmmntyId}</c:if>"  title="<spring:message code="button.requestlist" /> <spring:message code="input.button" />"><spring:message code="button.requestlist" /></a></span><!-- 요청목록 -->
		</div><div style="clear:both;"></div></c:if>
	</div>
	
</div>

<!-- validator 값 체크용 -->
<input name="replyPosblAt" type="hidden" value="<c:out value='${boardMasterVO.replyPosblAt}'/>">
<input name="cmmntyId" type="hidden" value="<c:out value='${boardMasterVO.cmmntyId}'/>">
<input name="bbsId" type="hidden" value="<c:out value='${boardMasterVO.bbsId}'/>">
</form:form>

</body>
</html>
