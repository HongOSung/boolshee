<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%--<title>eGovFrame <spring:message code="comCmm.unitContent.1"/></title> --%>
</head>
<script type="text/javascript">
function fn_egov_inquire_bbsdetail_82() {
  	document.BBSMasterForm_82.action = "<c:url value='/cop/bbs/selectGepyoso.do'/>";
  	document.BBSMasterForm_82.submit();
}
function fn_egov_inquire_bbsdetail_822() {
  	document.BBSMasterForm_822.action = "<c:url value='/cop/bbs/selectSajeonSeon.do'/>";
  	document.BBSMasterForm_822.submit();
}
function fn_egov_inquire_bbsdetail_823() {
  	document.BBSMasterForm_823.action = "<c:url value='/cop/bbs/selectSajeonTuResponseInfs.do'/>";
  	document.BBSMasterForm_823.submit();
}
function fn_egov_inquire_bbsdetail_83() {
  	document.BBSMasterForm_83.action = "<c:url value='/cop/bbs/selectGepyosoResponseInfs.do'/>";
  	document.BBSMasterForm_83.submit();
}
function fn_egov_inquire_bbsdetail21() {
  	document.BBSMasterForm81.action = "<c:url value='/cop/bbs/selecthelpRequestInfsNt.do'/>";
  	document.BBSMasterForm81.submit();
}
function fn_egov_inquire_bbsdetail_211() {
  	document.BBSMasterForm_811.action = "<c:url value='/cop/bbs/selectMnaRequest.do'/>";
  	document.BBSMasterForm_811.submit();
}
function fn_egov_inquire_bbsdetail22() {
  	document.BBSMasterForm2.action = "<c:url value='/cop/bbs/selectBbsMemberInfs.do'/>"; 
  	document.BBSMasterForm2.submit();
}
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
  	document.BBSMasterForm5.action = "<c:url value='/cop/bbs/selectPolPlaceListInfs.do'/>"; 
  	document.BBSMasterForm5.submit();
}
function fn_egov_inquire_bbsdetail6() {
  	document.BBSMasterForm6.action = "<c:url value='/cop/bbs/selectPollWatchListInfs.do'/>"; 
  	document.BBSMasterForm6.submit();
}
function fn_egov_inquire_bbsdetail_7() {
  	document.BBSMasterForm_7.action = "<c:url value='/images/egovframework/com/cmm/main/boolshee_mission.PNG'/>"; 
  	document.BBSMasterForm_7.submit();
}
function fn_egov_inquire_bbsdetail72() {
	document.BBSMasterForm72.action = "<c:url value='/images/egovframework/com/cmm/main/boolshee_mission2.PNG'/>";
  	document.BBSMasterForm72.submit();
}
function fn_egov_inquire_bbsdetail73() {
	document.BBSMasterForm73.action = "<c:url value='/images/egovframework/com/cmm/main/boolshee_mission3.PNG'/>";
  	document.BBSMasterForm73.submit();
}
function fn_egov_inquire_bbsdetail8() {
  	document.BBSMasterForm8.action = "<c:url value='/cop/bbs/selecthelpRequestInfs.do'/>"; 
  	document.BBSMasterForm8.submit();
}
function fn_egov_inquire_bbsdetail9() {
  	document.BBSMasterForm9.action = "<c:url value='/cop/bbs/selectBbsMemberInfsHp.do'/>"; 
  	document.BBSMasterForm9.submit();
}
function fn_egov_inquire_bbsdetail91() {
  	document.BBSMasterForm91.action = "<c:url value='/cop/bbs/selectBbsMemberInfsNt.do'/>"; 
  	document.BBSMasterForm91.submit();
}
function fn_egov_inquire_bbsdetail_911() {
  	document.BBSMasterForm_911.action = "<c:url value='/cop/bbs/selectMnaResponse.do'/>"; 
  	document.BBSMasterForm_911.submit();
}
function fn_egov_inquire_bbsdetail10() {
  	document.BBSMasterForm10.action = "<c:url value='/uss/umt/EgovMberManage.do'/>"; 
  	document.BBSMasterForm10.submit();
}
function fn_egov_inquire_bbsdetail_101() {
  	document.BBSMasterForm_101.action = "<c:url value='/uss/umt/EgovMyMberManage.do'/>"; 
  	document.BBSMasterForm_101.submit();
}
</script>
<body>
<p/><p/><p/>
	<c:if test="${loginVO != null}">
		<b><font size="5em"><span style="color: blue">${loginVO.name }</span></font></b>
		<spring:message code="comCmm.unitContent.2"/> <a href="${pageContext.request.contextPath }/uat/uia/actionLogout.do"><b><span style="color: red"><spring:message code="comCmm.unitContent.3"/></span></b></a>
	</c:if>
	<c:if test="${loginVO == null }">
		<jsp:forward page="/uat/uia/egovLoginUsr.do"/>
	</c:if>
	<br/>
	<br>
<form name="BBSMasterForm_822" method="post">
<div class="search_box">
		<ul>
			<li>
				<a href="#"
					onClick="fn_egov_inquire_bbsdetail_822();return false;"><font size="3em" color="green">
					<B> ???????????? ????????? ??????</B></font>
				</a>
			</li>
		</ul>
	</div>
</form>
<form name="BBSMasterForm_823" method="post">
<div class="search_box">
		<ul>
			<li>
				<a href="#"
					onClick="fn_egov_inquire_bbsdetail_823();return false;"><font size="3em" color="red">
					<B> ???????????? ????????? ??????</B></font>
				</a>
			</li>
		</ul>
	</div>
</form>
<br>
<form name="BBSMasterForm_82" method="post">
<div class="search_box">
		<ul>
			<li>
				<a href="#"
					onClick="fn_egov_inquire_bbsdetail_82();return false;"><font size="3em" color="green">
					<B> ?????? ????????? ??????</B></font>
				</a>
			</li>
		</ul>
	</div>
</form>

<form name="BBSMasterForm_83" method="post">
<div class="search_box">
		<ul>
			<li>
				<a href="#"
					onClick="fn_egov_inquire_bbsdetail_83();return false;"><font size="3em" color="red">
					<B> ?????? ????????? ??????</B></font>
				</a>
			</li>
		</ul>
	</div>
</form>
<br>
<form name="BBSMasterForm5" method="post">
<div class="search_box">
		<ul>
			<li>
				<a href="#"
				onClick="fn_egov_inquire_bbsdetail5();return false;"><font size="3em" color="green">
				<B> ?????? ????????? ?????? </B></font>
				</a>
			</li>
		</ul>
	</div>
</form>
<form name="BBSMasterForm6" action="<c:url value='/cop/bbs/selectPollWatchListInfs.do'/>" 
method="post">
<div class="search_box">
		<ul>
			<li>
				<a href="#"
				onClick="fn_egov_inquire_bbsdetail6();return false;"><font size="3em" color="red">
				<B> ?????? ????????? ?????? </B></font>
				</a>
			</li>
		</ul>
	</div>
</form>
<br>
<form name="BBSMasterForm_811" method="post">
<div class="search_box">
		<ul>
			<li>
				<a href="#"
					onClick="fn_egov_inquire_bbsdetail_211();return false;"><font size="5em" color="blue">
					<B> ??????</B></font>
				</a>
			</li>
		</ul>
	</div>
</form>
<form name="BBSMasterForm_911" method="post">
<div class="search_box">
		<ul>
			<li>				
				<a href="#"
				onClick="fn_egov_inquire_bbsdetail_911();return false;"><font size="3em" color="green">
				<B> ?????? ?????? ?????? </B></font>
				</a>
			</li>			
		</ul>
	</div>
</form>
<br>
<form name="BBSMasterForm3" method="post">
<div class="search_box">
		<ul>
			<li>
				<a href="#"
				onClick="fn_egov_inquire_bbsdetail3();return false;"><font size="3em" color="green">
				<B> ???????????? ?????? ????????? ??????</B></font>
				</a>
			</li>
		</ul>
	</div>
</form> 
<form name="BBSMasterForm4" method="post">
<div class="search_box">
		<ul>
			<li>
				<a href="#"
				onClick="fn_egov_inquire_bbsdetail4();return false;"><font size="3em" color="red">
				<B> ???????????? ?????? ????????? ?????? </B></font>
				</a>
			</li>
		</ul>
	</div>
</form>
<br> 

<form name="BBSMasterForm8" method="post">
<div class="search_box">
		<ul>
			<li>
				<a href="#"
					onClick="fn_egov_inquire_bbsdetail8();return false;"><font size="3em" color="green">
					<B> ?????? ?????? </B></font>
				</a>
			</li>
		</ul>
	</div>
</form>
<form name="BBSMasterForm9" method="post">
<div class="search_box">
		<ul>
			<li>				
				<a href="#"
				onClick="fn_egov_inquire_bbsdetail9();return false;"><font size="3em" color="red">
				<B> ?????? ?????? </B></font>
				</a>
			</li>			
		</ul>
	</div>
</form>
<br>
<form name="BBSMasterForm" method="post">
<div class="search_box">
		<ul>
			<li>
				<a href="#"
					onClick="fn_egov_inquire_bbsdetail();return false;"><font size="3em" color="green">
					<B> ??????????????? ?????? </B></font>
				</a>
			</li>
		</ul>
	</div>
</form>
<form name="BBSMasterForm2" method="post">
<div class="search_box">
		<ul>
			<li>				
				<a href="#"
				onClick="fn_egov_inquire_bbsdetail2();return false;"><font size="3em" color="red">
				<B> ??????????????? ?????? ?????? </B></font>
				</a>
			</li>			
		</ul>
	</div>
</form> 
<%--<form name="BBSMasterForm7" method="post">
<div class="search_box">
		<ul>
			<li>	
				<a href="#"
				onClick="fn_egov_inquire_bbsdetail73();return false;">
				<font size="4em" color="red">
				<B> BoolShee </B></font> <font size="4em" color="green"><B> &nbsp;&nbsp;Mission  &nbsp;</B></font> & 
				<font size="4em" color="green"><B>  &nbsp; Plan Now ! ???  ???</B></font>
				</a>
			</li>			
		</ul>
	</div>
</form>
<form name="BBSMasterForm72" method="post">
<div class="search_box">
		<ul>
			<li>	
				<a href="#"
				onClick="fn_egov_inquire_bbsdetail72();return false;">
				<font size="4em" color="red">
				<B> BoolShee </B></font> <font size="4em" color="green"><B> &nbsp;&nbsp;Mission  &nbsp;</B></font> & 
				<font size="4em" color="green"><B>  &nbsp; Plan Now ! ?????? ?????? ??????</B></font>
				</a>
			</li>			
		</ul>
	</div>
</form> --%>

<form name="BBSMasterForm_101" method="post">
<div class="search_box">
		<ul>
			<li>	
				<a href="#"
				onClick="fn_egov_inquire_bbsdetail_101();return false;">
				<font size="3em" color="green">
				<B> ??? ?????????</B></font>
				</a>
			</li>			
		</ul>
	</div>
</form>
<form name="BBSMasterForm10" method="post">
<div class="search_box">
		<ul>
			<li>	
				<a href="#"
				onClick="fn_egov_inquire_bbsdetail10();return false;">
				<font size="3em" color="green">
				<B> ?????? ?????? ?????? </B></font>
				</a>
			</li>			
		</ul>
	</div>
</form>
<form name="BBSMasterForm_7" method="post">
<div class="search_box">
		<ul>
			<li>	
				<a href="#"
				onClick="fn_egov_inquire_bbsdetail_7();return false;">
				<font size="4em" color="red">
				<B> BoolShee </B></font> <font size="4em" color="green"><B> &nbsp;&nbsp;Mission  &nbsp;</B></font> & 
				<font size="4em" color="green"><B>  &nbsp; Plan Now ! </B></font>
				</a>
			</li>			
		</ul>
	</div>
</form>
</br>
<%--<c:set var="mberId" value="${mberId}" /> --%>

<%--<c:if test="${mberId eq 'oshong34'}"> --%>



<%--</c:if> --%>

<%--<form name="BBSMasterForm7" action="<c:url value='/cop/bbs/select************.do'/>" 
method="post" onSubmit="fn_egov_search_bbssj(); return false;">
<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				?????? ???????????? &nbsp; <input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- ?????? -->
			</li>
		</ul>
	</div>
</form> --%>
<%--<form name="BBSMasterForm4" action="<c:url value='/cop/cmy/selectCommuMasterList.do'/>" 
method="post" onSubmit="fn_egov_search_bbssj(); return false;">
<div class="search_box" title="<spring:message code="common.searchCondition.msg" />">
		<ul>
			<li>
				???????????? &nbsp; <input type="submit" class="s_btn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- ?????? -->
			</li>
		</ul>
	</div>
</form> --%>

<%--<p/><p/><p/>
	<p/><p/><p/>
	<b><spring:message code="comCmm.unitContent.44"/><br /><br/><!-- ?????? ??? ?????? ????????? ???????????? 010-5259-5927??? ??????????????? ????????????. -->
<%--<p/><p/><p/>
<spring:message code="comCmm.unitContent.67"/><p/> --%>

<%--
	<spring:message code="comCmm.unitContent.7"/><p/><!-- ??? ??????????????? ?????? ????????? ??? ?????? ?????? ?????? ????????????????????????. -->

	<br /><b><img src="${pageContext.request.contextPath }/images/egovframework/com/cmm/icon/tit_icon.png"> egovframework.com.cmm.web.EgovComIndexController.java</b><p/>

	<spring:message code="comCmm.unitContent.8"/><p/><!-- ???????????? ?????? ??? ????????? ?????????????????? IncludedInfo annotation??? ?????? ????????? ??? -->
	<spring:message code="comCmm.unitContent.9"/><p/><br /><!-- ????????? ????????? ????????? ???????????? Controller ??????????????????. -->
	<spring:message code="comCmm.unitContent.10"/><p/><!-- ?????? ??? ?????? ????????? ????????? ?????? ?????? ???????????? ????????? ?????? ?????????????????? ????????? ????????? URL??? ???????????? -->
	<spring:message code="comCmm.unitContent.11"/><p/><!-- ???????????? ???????????? ????????? ??? ????????? ?????????????????????. -->
	<spring:message code="comCmm.unitContent.12"/> <p/><!-- ?????? ?????? ??? ???????????? ???????????? ????????? ???????????? ??????, -->
	<spring:message code="comCmm.unitContent.13"/><p/><!-- ?????? ????????? ??????????????? ???????????? ?????? ????????? ????????? ????????? ??? ?????? ????????? -->
	<spring:message code="comCmm.unitContent.14"/><p /><!-- ??? ?????? ????????? ???????????? ???????????? ?????? ????????? ????????????. --> --%>
</body>
</html>