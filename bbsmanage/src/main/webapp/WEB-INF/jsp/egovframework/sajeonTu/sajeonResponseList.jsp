<%
 /**
  * @Class Name : mNaList.jsp
  * @Description : mNaList 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2021.09.14  홍오성              최초 생성
  *  @author 불씨 서비스팀
  *  @since 2021.07.01
  *  @version 1.0
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="title.listWatchSajeonTu"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title><!-- 국회의원 활동 지킴이 목록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javascript">
/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.BBSMasterForm.searchCnd.focus();
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	document.BBSMasterForm.pageIndex.value = pageNo;
	document.BBSMasterForm.action = "<c:url value='/cop/bbs/selectSajeonTuResponseInfs.do'/>";
   	document.BBSMasterForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_bbssj(){
	//alert(1);
	document.BBSMasterForm.pageIndex.value = 1;
	document.BBSMasterForm.action = "<c:url value='/cop/bbs/selectSajeonTuResponseInfs.do'/>";
	document.BBSMasterForm.submit();
}
function fncGoAfterErrorPage(){
    history.back(-2);
}
</script>
</head>
<body onload="fn_egov_init()">
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="BBSMasterForm" action="<c:url value='/cop/bbs/selectSajeonTuResponseInfs.do'/>" method="post" onSubmit="fn_egov_search_bbssj(); return false;"> 
<div id="aa"><br>
	<h1>${pageTitle} <spring:message code="title.list" /></h1><!-- 사전투표 참관인 목록 -->
	<!-- 조회 버튼 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCnd" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option value="0"  <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopBbs.boardMasterVO.list.city" /></option><!-- 시 도-->
					<option value="1"  <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopBbs.boardMasterVO.list.gungu" /></option><!-- 군 구 -->					
					<option value="2"  <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> ><spring:message code="comCopBbs.boardMasterVO.list.tupyoso" /></option><!--사전투표소  -->					
					<option value="3"  <c:if test="${searchVO.searchCnd == '3'}">selected="selected"</c:if> ><spring:message code="comCopBbs.boardMasterVO.list.tupyosoWatch" /></option><!-- 투표 참관인 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li></li>
			<li>
				<input class="s_input" name="searchWrd" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->			
			</li>
		</ul>
	</div>
	
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle}<spring:message code="title.list" /></caption>
	<colgroup>
		
		<col style="width: 20%;">
		<col style="width: 30%;">
		<col style="width: 10%;">
		<col style="width: 20%;">	
		
		<col style="width: 15%;">
		<col style="width: 5%;">				
	</colgroup>
	<thead>
	<tr>
		
		<th><spring:message code="table.sajeonRedog" /></th><!-- 사전투표 참관인-->
		<th><spring:message code="table.sajeonTupyoso" /></th><!-- 사전투표소 -->	
		<th><spring:message code="table.ampm" /></th><!-- 오전/오후 -->
		
		<th><spring:message code="table.city" /></th><!--시 도  -->
		<th><spring:message code="table.sajeonGungu" /></th><!--군 구  -->
		<th><spring:message code="table.num" /></th><!-- 순번 -->
		<h1>사전투표  <font color="blue">참관인</font> <font color="red"><spring:message code="title.list" /></font></h1>
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
		
		<td class="left"><a href="<c:url value='/cop/bbs/updateSajeonResponseView.do?no=${resultInfo.no}&mberId=${resultInfo.mberId}'/>" >	
		<center><c:out value='${resultInfo.mberNm}'/></a></td>
		<td class="left"><a href="<c:url value='/cop/bbs/updateSajeonResponseView.do?no=${resultInfo.no}&mberId=${resultInfo.mberId}'/>" >
		<center><c:out value='${resultInfo.tupyoso}'/></a></td>
		<td class="left"><a href="<c:url value='/cop/bbs/updateSajeonResponseView.do?no=${resultInfo.no}&mberId=${resultInfo.mberId}'/>" >
		<center><c:out value='${resultInfo.ampm}'/></a></td>
		
		<td class="left"><a href="<c:url value='/cop/bbs/updateSajeonResponseView.do?no=${resultInfo.no}&mberId=${resultInfo.mberId}'/>" >
		<center><c:out value='${resultInfo.city}'/></a></td>
		<td class="left"><a href="<c:url value='/cop/bbs/updateSajeonResponseView.do?no=${resultInfo.no}&mberId=${resultInfo.mberId}'/>" >
		<center><c:out value='${resultInfo.gungu}'/></a></td>
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>		
	</tr>
	</c:forEach>
	</tbody>
	</table>
	<div>
		<span class="btn_style1 blue"><a href="javascript:fncGoAfterErrorPage();">이전 페이지</a></span>
	</div>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul>
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage"/>
		</ul>
	</div>
	
</div>
<input name="cmmntyId" type="hidden" value="<c:out value='${searchVO.cmmntyId}'/>">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

</body>
</html>