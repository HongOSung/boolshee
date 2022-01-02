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
<c:set var="pageTitle"><spring:message code="title.listWatchdog"/></c:set>
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
	document.BBSMasterForm.action = "<c:url value='/cop/bbs/selectDistwachInfs.do'/>";
   	document.BBSMasterForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_bbssj(){
	document.BBSMasterForm.pageIndex.value = 1;
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

<form name="BBSMasterForm" action="<c:url value='/cop/bbs/selectDistwachInfs.do'/>" method="post" onSubmit="fn_egov_search_bbssj(); return false;"> 
<div id="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1><!-- 게시판 목록 -->
	<!-- 하단 버튼 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCnd" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option value="0"  <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopBbs.boardMasterVO.list.watchDog" /></option><!-- 투표소 지킴이 -->
					<option value="1"  <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopBbs.boardMasterVO.list.district" /></option><!-- 선거구 -->
					<option value="2"  <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> ><spring:message code="comCopBbs.boardMasterVO.list.mnaNm" /></option><!-- 국회의원 이름 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchWrd" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
				<%--<span class="btn_b"><a href="<c:url value='/cop/bbs/insertBBSMasterView.do?cmmntyId=${searchVO.cmmntyId}' />"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span><!-- 등록 -->
				 --%>			
			</li>
		</ul>
	</div>
	
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle}<spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 5%;">
		<col style="width: 15%;">
		<col style="width: 30%;">
		<col style="width: 15%;">
		<col style="width: 15%;">
		<col style="width: 15%;">					
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 순번 -->
		<th><spring:message code="table.city" /></th><!--시 도  -->
		<th><spring:message code="table.districts" /></th><!-- 선거구 -->	
		<th><spring:message code="table.watchdog" /></th><!-- 지킴이 이름-->
		<th><spring:message code="table.mnaNm" /></th><!-- 국개의원 -->
		<th><spring:message code="table.party" /></th><!-- 정당 -->
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
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td class="left"><a href="<c:url value='/cop/bbs/districtView.do?no=${resultInfo.no}&mberId=${resultInfo.mberId}'/>" >
		<c:out value='${resultInfo.city}'/></a></td>
		<td class="left"><a href="<c:url value='/cop/bbs/districtView.do?no=${resultInfo.no}&mberId=${resultInfo.mberId}'/>" >
		<c:out value='${resultInfo.district}'/></a></td>
		<td class="left"><a href="<c:url value='/cop/bbs/districtView.do?no=${resultInfo.no}&mberId=${resultInfo.mberId}'/>" >	
		<c:out value='${resultInfo.mberNm}'/></a></td>
		<td class="left"><a href="<c:url value='/cop/bbs/districtView.do?no=${resultInfo.no}&mberId=${resultInfo.mberId}'/>" >
		<c:out value='${resultInfo.mnaNm}'/></a></td>
		<td class="left"><a href="<c:url value='/cop/bbs/districtView.do?no=${resultInfo.no}&mberId=${resultInfo.mberId}'/>" >
		<c:out value='${resultInfo.party}'/></a></td>		
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