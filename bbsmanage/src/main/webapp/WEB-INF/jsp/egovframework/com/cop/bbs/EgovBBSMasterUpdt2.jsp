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
<c:set var="pageTitle"><spring:message code="comCopBbs.boardMasterVO.title"/></c:set>
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
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_bbslist() {
	boardMasterVO.action = "<c:url value='/cop/bbs/selectBBSMasterInfs.do'/>";
	boardMasterVO.submit();	
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
				
<form:form commandName="boardMasterVO" action="${pageContext.request.contextPath}/cop/bbs/updateBBSMaster.do" method="post" onSubmit="fn_egov_updt_bbs(document.forms[0]); return false;">  
<div id="wTableFrm">
	<h2>${pageTitle} <spring:message code="title.update" /></h2><!-- 게시판 마스터 수정 -->

	<!-- 수정폼 -->
	<table class="wTable" summary="<spring:message code="common.summary.update" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.update" /></caption>
	<colgroup>
		<col style="width: 20%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 게시판명 -->
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.bbsNm"/> </c:set>
		<tr>
			<th><label for="BBS_NM">${title}<span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="BBS_NM" title="${title} ${inputTxt }" size="70" maxlength="70" readonly="true" />
   				<div><form:errors path="BBS_NM" cssClass="error" /></div>     
			</td>
		</tr>
		<!-- 게시판 등록자 -->
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.frstRegisterNm"/> </c:set>
		<tr>
			<th><label for="mberNm">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="mberNm" title="${title} ${inputTxt }" size="70" maxlength="70"  readonly="true" />
   				<div><form:errors path="mberNm" cssClass="error" /></div>     
			</td>
		</tr>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.tel"/> </c:set>
		<tr>
			<th><label for="tel">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="tel" title="${title} ${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="tel" cssClass="error" /></div>     
			</td>
		</tr>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.fullnm"/> </c:set>
		<tr>
			<th><label for="fullnm">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="fullnm" title="${title} ${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="fullnm" cssClass="error" /></div>     
			</td>
		</tr>
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.members"/> </c:set>
		<tr>
			<th><label for="members">${title} <span class="pilsu">*</span></label></th>
			<td class="left">
			    <form:input path="members" title="${title} ${inputTxt }" size="70" maxlength="70" />
   				<div><form:errors path="members" cssClass="error" /></div> 
<%--   				<input name="mberId" id="mberId" type="text" value="<c:out value='${boardMasterVO.mberId}'/>">
   				<c:set var="mberId" />    <!-- value="<c:out value='${boardMasterVO.mberId}'/>"  --> --%>
			</td>
		</tr>
		<!-- 게시판 취지와 실천 과제 -->
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.action"/> </c:set>
		<div class="jb-700">
      		<p><span class="pilsu">[</span><label for="action">${title} <span class="pilsu">]</span></label> </p>
    	</div>
				<form:textarea path="action" title="${title} ${inputTxt}"  cols="70" rows="15" data-component="textarea" required="" /> 
		              <div class="form-textarea-limit-indicator">
		                <span data-limit="1000" type="Words" data-minimum="-1" data-typeminimum="None" id="input_18-limit">
		                  0/1000
		                </span>
		              </div>
		              <div><form:errors path="action" cssClass="error" /></div>		              
		<!-- <tr>
			<th><label for="action">${title} <span class="pilsu">*</span></label></th>
			<td class="nopd">
				<form:textarea path="action" title="${title} ${inputTxt}" class="autosize" onkeydown="resize(this)" onkeyup="resize(this)" />
				<div><form:errors path="action" cssClass="error" /></div>  
			</td>
		</tr> -->
		
		<!-- <tr>
		<th><label for="action">${title } <span class="pilsu">*</span></label></th>
		<td class="nopd">
		<form:textarea path="action" title="${title} ${inputTxt}" cols="100" rows="10" />
		<div><form:errors path="action" cssClass="error" /></div>
		</td>
		</tr> -->
		
		<!-- <div id="cid_18" class="form-input jf-required"> -->
          <!-- <div class="form-textarea-limit"> -->
           <!--  <span> -->
           <!-- <form:textarea path="action" title="${title} ${inputTxt}" cols="100" rows="10" /> -->
              
            <!-- </span> -->
          <!-- <div class="form-textarea-limit"> -->
        <!-- </div> -->
		
		
		<%--<!-- 파일첨부가능여부 -->
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.fileAtchPosblAt"/> </c:set>
		<tr>
			<th><label for="fileAtchPosblAt">${title}<span class="pilsu">*</span></label></th>
			<td class="left">
				<form:select path="fileAtchPosblAt" title="${title} ${inputTxt}" cssClass="txt">
					<form:option value='' label="--선택하세요--" />
					<form:option value="Y"  label="예" />
	  		   		<form:option value='N'>아니오</form:option>
				</form:select>
				<div><form:errors path="fileAtchPosblAt" cssClass="error" /></div>       
			</td>
		</tr>
		
		<!-- 첨부가능파일숫자 -->
		<c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.atchPosblFileNumber"/> </c:set>
		<tr>
			<th><label for="atchPosblFileNumber">${title } <span class="pilsu">*</span></label></th>
			<td class="left">
				<form:select path="atchPosblFileNumber" title="${title} ${inputTxt }" cssClass="txt">
					<form:option value="0" selected="selected">없음</form:option>
					<form:option value='1'>1</form:option>
	  		   		<form:option value='2'>2</form:option>
	  		   		<form:option value='3'>3</form:option>
				</form:select>
				<div><form:errors path="atchPosblFileNumber" cssClass="error" /></div>       
			</td>
		</tr>
		
		<!-- 추가 선택사항 -->
		<tr>
			<th><spring:message code="comCopBbs.boardMasterVO.detail.option" /></th><!-- 추가 선택사항 -->
			<td class="left">
				<select name="option" class="select" <c:if test="${boardMasterVO.option != 'na'}">disabled="disabled"</c:if> title="추가선택사항">
					<option value='na' <c:if test="${boardMasterVO.option == 'na'}">selected="selected"</c:if>>---<spring:message code="input.select" />--</option>
					<option value='' <c:if test="${boardMasterVO.option == ''}">selected="selected"</c:if>><spring:message code="comCopBbs.boardMasterVO.detail.option1" /></option>
					<c:if test="${useComment == 'true' }">
						<option value='comment' <c:if test="${boardMasterVO.option == 'comment'}">selected="selected"</c:if>><spring:message code="comCopBbs.boardMasterVO.detail.option2" /></option>
					</c:if>
					<c:if test="${useSatisfaction == 'true' }">
						<option value='stsfdg' <c:if test="${boardMasterVO.option == 'stsfdg'}">selected="selected"</c:if>><spring:message code="comCopBbs.boardMasterVO.detail.option3" /></option>
					</c:if>
				</select>
				<spring:message code="comCopBbs.boardMasterVO.detail.option.unabledToModify" /><!-- ※ 추가 선택사항은 수정 불가 (미설정된 기존 게시판의 경우 처음 설정은 가능함) -->
			</td>
		</tr> --%>

		<!-- 사용여부 -->
		<!-- <c:set var="title"><spring:message code="comCopBbs.boardMasterVO.updt.useAt"/> </c:set>
		<tr>
			<th><label for="useAt">${title } <span class="pilsu">*</span></label></th>
			<td class="left">
				<form:select path="useAt" title="${title} ${inputTxt }" cssClass="txt">
					<form:option value="" label="--선택하세요--" />
					<form:option value="Y"  label="예" />
	  		   		<form:option value='N'>아니오</form:option>
				</form:select>
				<div><form:errors path="useAt" cssClass="error" /></div>       
			</td>
		</tr> -->
		
	</tbody>
	</table>
	
		<span class="btn_style1 blue"><a href="javascript:fncGoAfterErrorPage();">이전 페이지</a></span>
	
	<!-- 하단 버튼 --> 
	<c:choose>
	<c:when test="${mberId_SS == boardMasterVO.mberId }">
		<div class="btn">
			<span class="btn_s"><a href="<c:url value='/uss/umt/EgovMberInsertView.do?bbsId=${boardMasterVO.bbsId}'/>"  title="<spring:message code="button.boolsheeCreate" />  <spring:message code="input.button" />"><spring:message code="button.boolsheeCreate" /></a></span><!-- 소시민단체가입 -->
			<span class="btn_s"><a href="<c:url value='/cop/bbs/selectBBSMasterInfs.do' /><c:if test='${boardMasterVO.cmmntyId != null}'>?cmmntyId=${boardMasterVO.cmmntyId}</c:if>"  title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
			<input type="submit" class="s_submit" value="<spring:message code="button.update" />" title="<spring:message code="button.update" /> <spring:message code="input.button" />" /><!-- 수정 -->
			<span class="btn_s"><a href="<c:url value='/cop/bbs/deleteBBSMaster.do?bbsId=${boardMasterVO.bbsId}'/>"  title="<spring:message code="button.delete" />  <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span><!-- 삭제 -->
		</div><div style="clear:both;"></div>
	</c:when> 
    <c:otherwise>
		<div class="btn">
			<span class="btn_s"><a href="<c:url value='/uss/umt/EgovMberInsertView.do?bbsId=${boardMasterVO.bbsId}'/>"  title="<spring:message code="button.boolsheeCreate" />  <spring:message code="input.button" />"><spring:message code="button.boolsheeCreate" /></a></span><!-- 소시민단체가입 -->
			<span class="btn_s"><a href="<c:url value='/cop/bbs/selectBBSMasterInfs.do' /><c:if test='${boardMasterVO.cmmntyId != null}'>?cmmntyId=${boardMasterVO.cmmntyId}</c:if>"  title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
		</div><div style="clear:both;"></div>
		</c:otherwise> 
</c:choose>

</div>

<!-- validator 값 체크용 -->
<input name="replyPosblAt" type="hidden" value="<c:out value='${boardMasterVO.replyPosblAt}'/>">
<input name="cmmntyId" type="hidden" value="<c:out value='${boardMasterVO.cmmntyId}'/>">
<input name="bbsId" type="hidden" value="<c:out value='${boardMasterVO.bbsId}'/>">
<input name="mberId" type="hidden" value="<c:out value='${boardMasterVO.mberId}'/>">
</form:form>

</body>
</html>
