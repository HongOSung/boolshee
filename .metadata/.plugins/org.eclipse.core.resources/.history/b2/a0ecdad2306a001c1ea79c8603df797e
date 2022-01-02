<%
 /**
  * @Class Name : EgovUserManage.jsp
  * @Description : 사용자관리(조회,삭제) JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.02    조재영          최초 생성
  *   2016.06.13    장동한          표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스 개발팀 조재영
  *  @since 2009.03.02
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
<c:set var="pageTitle"><spring:message code="comUssUmt.userManage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<script type="text/javaScript" language="javascript" defer="defer">
function fnCheckAll() {
    var checkField = document.listForm.checkField;
    if(document.listForm.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}
function fnDeleteUser() {
    var checkField = document.listForm.checkField;
    var id = document.listForm.checkId;
    var checkedIds = "";
    var checkedCount = 0;
    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkedIds += ((checkedCount==0? "" : ",") + id[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
                checkedIds = id.value;
            }
        }
    }
    if(checkedIds.length > 0) {
    	//alert(checkedIds);
        if(confirm("<spring:message code="common.delete.msg" />")){
        	document.listForm.checkedIdForDel.value=checkedIds;
            document.listForm.action = "<c:url value='/uss/umt/EgovMberDelete.do'/>";
            document.listForm.submit();
        }
    }
}
function fnSelectUser(id) {
	document.listForm.selectedId.value = id;
	array = id.split(":");
	if(array[0] == "") {
	} else {
	    userTy = array[0];
	    userId = array[1];    
	}
	document.listForm.selectedId.value = userId;
    document.listForm.action = "<c:url value='/uss/umt/EgovMberSelectUpdtView.do'/>";
    document.listForm.submit();
}
function fnAddUserView() {
    document.listForm.action = "<c:url value='/uss/umt/EgovMberInsertView.do'/>";
    document.listForm.submit();
}
function fnLinkPage(pageNo){
	//alert(pageNo);
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/uss/umt/EgovMberManage.do?no=${resultInfo.no}'/>";
    document.listForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
/*function fn_egov_search_bbssj(){
	document.listForm.pageIndex.value = 1;
	document.listForm.submit();
}*/
/*function fnSearch(){
	document.listForm.pageIndex.value = 1;
	document.listForm.action = "<c:url value='/uss/umt/EgovMberManage.do'/>";
    document.listForm.submit();
}*/
<c:if test="${!empty resultMsg}">alert("<spring:message code="${resultMsg}" />");</c:if>
</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<form name="listForm" action="<c:url value='/uss/umt/EgovMberManage.do'/>" method="post" onSubmit="fnLinkPage(pageNo); return false;"> 
<div id="board">
	<h1>${pageTitle} <spring:message code="title.list" /></h1>
	
	<!-- 검색영역 -->
	<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<%--<li><!-- 상태-->
                <select name="sbscrbSttus" id="sbscrbSttus" title="<spring:message code="comUssUmt.userManageSsearch.sbscrbSttusTitle" />">
                    <option value="0" <c:if test="${empty mberVO.sbscrbSttus || mberVO.sbscrbSttus == '0'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.sbscrbSttusAll" /></option><!-- 상태(전체) -->
                    <option value="A" <c:if test="${mberVO.sbscrbSttus == 'A'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.sbscrbSttusA" /></option><!-- 가입신청 -->
                    <option value="D" <c:if test="${mberVO.sbscrbSttus == 'D'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.sbscrbSttusD" /></option><!-- 삭제 -->
                    <option value="P" <c:if test="${mberVO.sbscrbSttus == 'P'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.sbscrbSttusP" /></option><!-- 승인 -->
                </select>
			</li> --%>
			<li><!-- 조건 -->
                <select name="searchCondition" id="searchCondition" title="<spring:message code="comUssUmt.userManageSsearch.searchConditioTitle" />"><!--  -->
                    <option value="0" <c:if test="${mberVO.searchCondition == '0'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.searchRecommderId" /></option><!-- ID  --> 
                    <option value="1" <c:if test="${empty mberVO.searchCondition || mberVO.searchCondition == '1'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.searchMemberNm" /></option><!-- Name -->
                </select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${mberVO.searchKeyword}"/>'  maxlength="255" >
				<input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
					<%--<input type="button" class="s_btn" onClick="fnDeleteUser(); return false;" value="<spring:message code="title.delete" />" title="<spring:message code="title.delete" /> <spring:message code="input.button" />" />							
					<span class="btn_b"><a href="<c:url value='/uss/umt/EgovMberInsertView.do'/>" onClick="fnAddUserView(); return false;"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span>
				 --%>			</li>
		</ul>
	</div>


<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<caption>${pageTitle} <spring:message code="title.list" /></caption>
	<colgroup>		
		
		<col style="width: 10%;">
		<col style="width: 15%;">
		<col style="width: 5%;">
		<col style="width: 5%;">
		<col style="width: 5%;">
		<col style="width: 8%;">
		<col style="width: 5%;">
		<col style="width: 10%;">
		<col style="width: 5%;"> 
		<col style="width: 5%;"> 
		<col style="width: 5%;"> 
		<col style="width: 5%;"> 
		<col style="width: 5%;"> 
		<col style="width: 12%;"> 
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="comUssUmt.userManageList.memberNm" /></th><!-- 회원이름 -->
		<th><spring:message code="comUssUmt.userManageList.gubun" /></th><!-- 구분 -->
		<th><spring:message code="comUssUmt.userManageList.tupyoso" /><!-- 투표소 -->
		<th><spring:message code="comUssUmt.userManageList.day" /><!-- 날짜 -->
		<th><spring:message code="comUssUmt.userManageList.ampm" /><!-- 오전/오후 -->
		<th><spring:message code="comUssUmt.userManageList.phone" /></th><!-- 전화번호 -->
		<th><spring:message code="table.regdate" /></th><!-- 등록일 -->
		<th class="board_th_link"><spring:message code="comUssUmt.userManageList.id" /></th><!--아이디 -->
		<th><spring:message code="comUssUmt.userManageList.recommender" /></th><!-- 추천자 아이디 -->
		<th><spring:message code="comUssUmt.userManageList.num" /></th><!-- 순번 --> 
		<th><spring:message code="comUssUmt.userManageList.num11" /></th><!--11 시도 --> 
		<th><spring:message code="comUssUmt.userManageList.num12" /></th><!--12 개표소 -->
		<th><spring:message code="comUssUmt.userManageList.num13" /></th><!--13 투표 참관 투표소 -->
		<th><spring:message code="comUssUmt.userManageList.num14" /></th> <!--14 청원동의 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
	    <td><c:out value="${result.mberNm}"/></td><!-- 1 회원 이름 -->
	    <td><c:out value="${result.gubun}"/></td><!-- 2 구분(1사전투표,2개표참관,3투표참관,4청원동의,5회원) -->
	    <td><c:out value="${result.tupyoso}"/></td><!-- 3 사전투표소 -->
	    <td><c:out value="${result.day}"/></td><!-- 4 사전투표 참관날짜 -->
	    <td><c:out value="${result.ampm}"/></td><!-- 5 오전/오후 -->
	    <td><c:out value="${result.telNo}"/></td><!-- 6 전화번호 -->	    
	    <td><c:out value="${fn:substring(result.sbscrbDe,0,10)}"/></td><!-- 7 등록일 -->
	    <td><a href="<c:url value='/uss/umt/EgovMberSelectUpdtView.do'/>?selectedId=<c:out value="${result.uniqId}"/>"  
	    onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>'); return false;">
	    <c:out value="${result.mberId}"/></a></td><!-- 8 회원아이디 -->
	    <td><c:out value="${result.recommenderId}"/></td><!-- 9 추천자 아이디 -->
	 	<td><c:out value="${(userSearchVO.pageIndex-1) * userSearchVO.pageSize + status.count}"/></td><!--10 순번 -->
	        
	    <td><c:out value="${result.city}"/></td><!--11 시도 -->
	    <td><c:out value="${result.gepyoso}"/></td><!--12 개표소 -->
	    <td><c:out value="${result.pllngplce}"/></td> <!--13 투표 참관 투표소 -->
	    <td><c:out value="${result.contents}"/></td> <!--14 청원동의 -->
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul><ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnLinkPage"/></ul>
	</div>

<input name="selectedId" type="hidden" />
<input name="checkedIdForDel" type="hidden" />
<input name="pageIndex" type="hidden" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
</div>
</form>

</body>
</html>
