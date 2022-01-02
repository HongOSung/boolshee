<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>eGovFrame <spring:message code="comCmm.unitContent.1"/></title>
</head>
<script type="text/javascript">
function fn_egov_inquire_bbsdetail() {
  	document.BBSMasterForm.action = "<c:url value='/cop/bbs/selectBBSMasterInfs.do'/>";
  	document.BBSMasterForm.submit();
}
function fn_egov_inquire_bbsdetail2() {
  	document.BBSMasterForm2.action = "<c:url value='/cop/bbs/selectBbsMemberInfs.do'/>"; 
  	document.BBSMasterForm2.submit();
}
function fn_egov_inquire_bbsdetail3() {
  	document.BBSMasterForm3.action = "<c:url value='/cop/bbs/selectMnaListInfs.do'/>"; 
  	document.BBSMasterForm3.submit();
}
function fn_egov_inquire_bbsdetail4() {
  	document.BBSMasterForm4.action = "<c:url value='/cop/bbs/selectDistwachInfs.do'/>"; 
  	document.BBSMasterForm4.submit();
}
function fn_egov_inquire_bbsdetail5() {
  	document.BBSMasterForm5.action = "<c:url value='/cop/bbs/selectMnaListInfs.do'/>"; 
  	document.BBSMasterForm5.submit();
}
function fn_egov_inquire_bbsdetail6() {
  	document.BBSMasterForm6.action = "<c:url value='/cop/bbs/selectMnaListInfs.do'/>"; 
  	document.BBSMasterForm6.submit();
}
</script>
<body>
<p/><p/><p/>
	<p/><p/><p/>
	<br/><br/><br/>
	<c:if test="${loginVO != null}">
		<b><font size="5em"><span style="color: blue">${loginVO.name }</span></font></b>
		<spring:message code="comCmm.unitContent.2"/> <a href="${pageContext.request.contextPath }/uat/uia/actionLogout.do"><b><span style="color: red"><spring:message code="comCmm.unitContent.3"/></span></b></a>
	</c:if>
	<c:if test="${loginVO == null }">
		<jsp:forward page="/uat/uia/egovLoginUsr.do"/>
	</c:if>
	<br/><br/><br/>
<form name="BBSMasterForm" action="<c:url value='/cop/bbs/selectBBSMasterInfs.do'/>" 
method="post">
<div>
		<ul>
			<li>
				<a href="<c:url value='/cop/bbs/selectBBSMasterInfs.do'/>"
					onClick="fn_egov_inquire_bbsdetail();return false;"><font size="3em" color="green"><B>소시민단체  &nbsp;</B></font>
				</a>
			</li>
		</ul>
	</div>
</form>
<form name="BBSMasterForm2" action="<c:url value='/cop/bbs/selectBbsMemberInfs.do'/>" 
method="post">
<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>				
				<a href="<c:url value='/cop/bbs/selectBbsMemberInfs.do'/>"
				onClick="fn_egov_inquire_bbsdetail2();return false;"><font size="3em" color="red"><B>소시민단체 가입 목록  &nbsp;</B></font>
				</a>
			</li>			
		</ul>
	</div>
</form>
<form name="BBSMasterForm3" action="<c:url value='/cop/bbs/selectMnaListInfs.do'/>" 
method="post" onSubmit="fn_egov_search_bbssj(); return false;">
<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				<a href="<c:url value='/cop/bbs/selectMnaListInfs.do'/>"
				onClick="fn_egov_inquire_bbsdetail3();return false;"><font size="3em" color="green"><B> 국회의원 전화 주소</B></font>
				</a>
			</li>
		</ul>
	</div>
</form> 
<form name="BBSMasterForm4" action="<c:url value='/cop/bbs/selectDistwachInfs.do'/>" 
method="post" onSubmit="fn_egov_search_bbssj(); return false;">
<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
			<a href="<c:url value='/cop/bbs/selectDistwachInfs.do'/>"
				onClick="fn_egov_inquire_bbsdetail4();return false;"><font size="3em" color="red"><B> 의원 활동 지킴이 목록</B></font>
				</a>
				선거구 지킴이 목록 &nbsp; <input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
			</li>
		</ul>
	</div>
</form>
<form name="BBSMasterForm5" action="<c:url value='/cop/bbs/selectPolPlaceListInfs.do'/>" 
method="post" onSubmit="fn_egov_search_bbssj(); return false;">
<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				투표소 목록 &nbsp; <input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
			</li>
		</ul>
	</div>
</form>
<form name="BBSMasterForm6" action="<c:url value='/cop/bbs/selectPollWatchListInfs.do'/>" 
method="post" onSubmit="fn_egov_search_bbssj(); return false;">
<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				투표소 지킴이 목록 &nbsp; <input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
			</li>
		</ul>
	</div>
</form>
<%--<form name="BBSMasterForm7" action="<c:url value='/cop/bbs/select************.do'/>" 
method="post" onSubmit="fn_egov_search_bbssj(); return false;">
<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				불씨 실행계획 &nbsp; <input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
			</li>
		</ul>
	</div>
</form> --%>
<%--<form name="BBSMasterForm4" action="<c:url value='/cop/cmy/selectCommuMasterList.do'/>" 
method="post" onSubmit="fn_egov_search_bbssj(); return false;">
<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				커뮤니티 &nbsp; <input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
			</li>
		</ul>
	</div>
</form> --%>

<%--<p/><p/><p/>
	<p/><p/><p/>
	<b><spring:message code="comCmm.unitContent.44"/><br /><br/><!-- 실행 시 오류 사항이 있으시면 010-5259-5927로 연락주시기 바랍니다. -->
<%--<p/><p/><p/>
<spring:message code="comCmm.unitContent.67"/><p/> --%>

<%--
	<spring:message code="comCmm.unitContent.7"/><p/><!-- 각 컴포넌트를 쉽게 찾아볼 수 있는 바로 가기 링크페이지입니다. -->

	<br /><b><img src="${pageContext.request.contextPath }/images/egovframework/com/cmm/icon/tit_icon.png"> egovframework.com.cmm.web.EgovComIndexController.java</b><p/>

	<spring:message code="comCmm.unitContent.8"/><p/><!-- 컴포넌트 설치 후 설치된 컴포넌트들을 IncludedInfo annotation을 통해 찾아낸 후 -->
	<spring:message code="comCmm.unitContent.9"/><p/><br /><!-- 화면에 표시할 정보를 처리하는 Controller 클래스입니다. -->
	<spring:message code="comCmm.unitContent.10"/><p/><!-- 개발 시 메뉴 구조가 잡히기 전에 배포 파일들에 포함된 공통 컴포넌트들의 목록성 화면에 URL을 제공하여 -->
	<spring:message code="comCmm.unitContent.11"/><p/><!-- 개발자가 편리하게 활용할 수 있도록 작성되었습니다. -->
	<spring:message code="comCmm.unitContent.12"/> <p/><!-- 운영 시에 본 컨트롤을 사용하여 메뉴를 구성하는 경우, -->
	<spring:message code="comCmm.unitContent.13"/><p/><!-- 성능 문제를 일으키거나 사용자별 메뉴 구성에 오류를 발생할 수 있기 때문에 -->
	<spring:message code="comCmm.unitContent.14"/><p /><!-- 실 운영 시에는 삭제해서 배포하는 것을 권장해 드립니다. --> --%>
</body>
</html>