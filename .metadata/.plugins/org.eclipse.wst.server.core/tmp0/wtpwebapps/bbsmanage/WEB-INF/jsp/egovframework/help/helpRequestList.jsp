<%
 /**
  * @Class Name : EgovBBSMasterList.jsp
  * @Description : EgovBBSMasterList 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2021.10.22   홍오성              최초 생성
  *  @author 불씨 개발팀
  *  @since 2021.10.22
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comCopBbs.boardMasterVO.helpReqtitle"/></c:set>
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
	document.BBSMasterForm.action = "<c:url value='/cop/bbs/selecthelpRequestInfs.do'/>";
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

<form name="BBSMasterForm" action="<c:url value='/cop/bbs/selecthelpRequestInfs.do'/>" method="post" onSubmit="fn_egov_search_bbssj(); return false;"> 
<div id="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1><!-- 게시판 목록 -->
	<!-- 하단 버튼 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<select name="searchCnd" title="<spring:message code="title.searchCondition" /> <spring:message code="input.cSelect" />">
					<option value="0"  <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> ><spring:message code="comCopBbs.boardMasterVO.list.bbsNm3" /></option><!-- 게시판명 -->
					<option value="1"  <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> ><spring:message code="comCopBbs.boardMasterVO.list.bbsIntrcn3" /></option><!-- 게시판 등록자 명 -->
				</select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchWrd" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="155" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
				<span class="btn_b"><a href="<c:url value='/cop/bbs/insertBBSMasterViewHp.do?cmmntyId=${searchVO.cmmntyId}' />"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span><!-- 등록 -->
			</li>
		</ul>
	</div>
	
	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle}<spring:message code="title.list" /></caption>
	<colgroup>
		<col style="width: 7%;">
		<col style="width: 15%;">
		<col style="width: 10%;">
		<%--<col style="width: 10%;"> --%>
		<col style="width: 9%;">
		<%--<col style="width: 9%;"> --%>
		<col style="width: 23%;">
		<col style="width: 20%;">
		<!-- <col style="width: 9%;"> -->
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th class="board_th_link"><spring:message code="comCopBbs.boardMasterVO.list.bbsNm3" /></th><!-- 소시민 단체명 -->
		<th><spring:message code="table.reger" /></th><!-- 등록자 -->
		<th><spring:message code="table.bssnm4" /></th><!-- 소속단체 -->
		<%--<th><spring:message code="table.tel" /></th><!-- 전화번호 --> --%>
		<%--<th><spring:message code="table.respondents" /></th><!-- 응답자수 --> --%>
		<th><spring:message code="table.helpRefullNm" /></th><!-- 도움요청 총명 -->
		<th><spring:message code="table.request&plan" /></th><!-- 요청내용과 당면과제-->
		<!--<th><spring:message code="comCopBbs.boardMasterVO.list.useAt" /></th><!-- 사용여부 -->
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
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td class="left"><a href="<c:url value='/cop/bbs/updateBBSMasterViewHp.do?bbsId=${resultInfo.bbsId}'/>" >
		<center><c:out value='${fn:substring(resultInfo.BBS_NM, 0, 40)}'/></center></a></td>
		<td class="left"><a href="<c:url value='/cop/bbs/updateBBSMasterViewHp.do?bbsId=${resultInfo.bbsId}'/>" >
		<center><c:out value='${fn:substring(resultInfo.mberNm, 0, 40)}'/></center></a></td>
		<td class="left"><a href="<c:url value='/cop/bbs/updateBBSMasterViewHp.do?bbsId=${resultInfo.bbsId}'/>" >
		<center><c:out value='${fn:substring(resultInfo.BBS_NMST, 0, 40)}'/></center></a></td>
		<%--<td class="left"><a href="<c:url value='/cop/bbs/updateBBSMasterViewHp.do?bbsId=${resultInfo.bbsId}'/>" >
		<center><c:out value='${fn:substring(resultInfo.telNo, 0, 40)}'/></center></a></td> --%>
		<%--<td class="left"><a href="<c:url value='/cop/bbs/updateBBSMasterViewHp.do?bbsId=${resultInfo.bbsId}'/>" >
		<c:out value='${fn:substring(resultInfo.members, 0, 40)}'/></a></td> --%>
		<td class="left"><a href="<c:url value='/cop/bbs/updateBBSMasterViewHp.do?bbsId=${resultInfo.bbsId}'/>" >
		<center><c:out value='${fn:substring(resultInfo.fullnm, 0, 40)}'/></center></a></td>	
		<td class="left"><a href="<c:url value='/cop/bbs/updateBBSMasterViewHp.do?bbsId=${resultInfo.bbsId}'/>" >
		<center><c:out value='${fn:substring(resultInfo.action, 0, 40)}'/></center></a></td>
		<!-- <td><c:out value='${resultInfo.useAt}'/></td> -->
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
	
	<!-- 등록버튼 -->
	<!-- 
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/cop/bbs/insertBBSMasterView.do' />"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span>
	</div>
	-->
	
</div>
<input name="cmmntyId" type="hidden" value="<c:out value='${searchVO.cmmntyId}'/>">
<input name="bbsId" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

</body>
</html>