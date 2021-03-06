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
<c:set var="pageTitle"><spring:message code="comCopBbs.boardMasterVO.gepyosotitle"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title><!-- 게시판 목록 -->
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
	//document.BBSMasterForm.action = "<c:url value='/cop/bbs/selectBBSMasterInfs.do'/>";
	document.BBSMasterForm.action = "<c:url value='/cop/bbs/selectGepyoso.do'/>";
   	document.BBSMasterForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_bbssj(){
	document.BBSMasterForm.pageIndex.value = 1;
	document.BBSMasterForm.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_bbsdetail(no) {
	// 사이트 키값(siteId) 셋팅.
	document.BBSMasterForm.members.value = no;
  	document.BBSMasterForm.action = "<c:url value='/cop/bbs/updateGepyosoView.do?no=${resultInfo.no}'/>";///cop/bbs/updateGepyosoView.do?no=${resultInfo.no}
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

<form name="BBSMasterForm" action="<c:url value='/cop/bbs/selectGepyoso.do'/>" method="post" onSubmit="fn_egov_search_bbssj(); return false;"> 
<div id="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1><!-- 투표소 목록 -->
	<!-- 하단 버튼 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>서울특별시=<font color="red"><b>서울</b></font>, 경기도=<font color="red"><b>경기</font>,인천광역시=<font color="red"><b>인천</font>,
			부산광역시=<font color="red"><b>부산</font>,제주특별자치도=<font color="red"><b>제주</font>, 충청남도=<font color="red"><b>충청남</font>,
			경상북도=<font color="red"><b>경상북</font> 등으로 <font color="red">빨간 글씨</font>대로
			<br><font color="blue"> <b>각 지역 앞 </font><font color="red">두 세자리</b></font>로 조회 합니다.</li>
			<br>
			<li>
				<select name="searchCnd" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option value="0"  <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopBbs.boardMasterVO.list.city" /></option><!-- 투표소 -->
					<option value="1"  <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopBbs.boardMasterVO.list.gepyoso" /></option><!-- 시도 -->
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
		
		<%--<col style="width: 10%;"> --%>
		<col style="width: 30%;">
		<col style="width: 30%;">
<%--		<col style="width: 20%;">	 --%>		
		<col style="width: 10%;">			
	</colgroup>
	<thead>
	<tr>
		
		<%--<th><spring:message code="table.prm" /></th><!-- key 프라이머리 --> --%>
		<th><spring:message code="table.city" /></th><!--시 도  -->
		<th><spring:message code="table.gepyoso" /></th><!-- 개표소 -->			
	<%--	<th><spring:message code="table.members" /></th><!-- 회원수 -->	 --%>	
	<th><spring:message code="table.num" /></th><!-- 번호 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="5"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
		
		<%--<td class="left"><a href="<c:url value='/cop/bbs/updateGepyosoView.do?no=${resultInfo.no}'/>" 
		onClick="fn_egov_inquire_bbsdetail('<c:out value="${resultInfo.no}"/>');return false;">
		<center><c:out value='${resultInfo.no}'/></a></td> --%>
		<td class="left"><a href="<c:url value='/cop/bbs/updateGepyosoView.do?no=${resultInfo.no}'/>" 
		onClick="fn_egov_inquire_bbsdetail('<c:out value="${resultInfo.no}"/>');return false;">
		<center><c:out value='${resultInfo.city}'/></a></td>
		<td class="left"><a href="<c:url value='/cop/bbs/updateGepyosoView.do?no=${resultInfo.no}'/>" 
		onClick="fn_egov_inquire_bbsdetail('<c:out value="${resultInfo.no}"/>');return false;">
		<center><c:out value='${resultInfo.gepyoso}'/></a></td>
		<%--<td class="left"><a href="<c:url value='/cop/bbs/updateGepyosoView.do?no=${resultInfo.no}'/>" 
		onClick="fn_egov_inquire_bbsdetail('<c:out value="${resultInfo.no}"/>');return false;">
		<center><c:out value='${resultInfo.members}'/></a></td>	 --%>	
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