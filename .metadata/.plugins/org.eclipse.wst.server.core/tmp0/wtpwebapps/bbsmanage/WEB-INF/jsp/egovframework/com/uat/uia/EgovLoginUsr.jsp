<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovLoginUsr.jsp
  * @Description : Login 인증 화면
  * @Modification Information
  * @
  * @ 수정일               수정자            수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.03.03   박지욱            최초 생성
  * @ 2011.09.25   서준식            사용자 관리 패키지가 미포함 되었을때에 회원가입 오류 메시지 표시
  * @ 2011.10.27   서준식            사용자 입력 탭 순서 변경
  * @ 2017.07.21   장동한            로그인인증제한 작업
  * @ 2019.12.11   신용호            KISA 보안약점 조치 (크로스사이트 스크립트)
  * @ 2021.07.01   홍오성            불씨 시스템 개발
  *
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.03
  *  @version 1.0
  *  @see
  *
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<!DOCTYPE html>
<html>
<head>
<title><spring:message code="comUatUia.title" /></title><!-- 로그인 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/uat/uia/login.css' />">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>" ></script>
<script type="text/javaScript" language="javascript">
function checkLogin(userSe) {
    // 일반회원
    if (userSe == "GNR") {
        document.loginForm.rdoSlctUsr[0].checked = true;
        document.loginForm.rdoSlctUsr[1].checked = false;
        document.loginForm.rdoSlctUsr[2].checked = false;
        document.loginForm.userSe.value = "GNR";
    // 기업회원
    } else if (userSe == "ENT") {
        document.loginForm.rdoSlctUsr[0].checked = false;
        document.loginForm.rdoSlctUsr[1].checked = true;
        document.loginForm.rdoSlctUsr[2].checked = false;
        document.loginForm.userSe.value = "ENT";
    // 업무사용자
    } else if (userSe == "USR") {
        document.loginForm.rdoSlctUsr[0].checked = false;
        document.loginForm.rdoSlctUsr[1].checked = false;
        document.loginForm.rdoSlctUsr[2].checked = true;
        document.loginForm.userSe.value = "USR";
    }
}

function onEnterLogin(){
	var keyCode = window.event.keyCode;
	if (keyCode == 13) {
		actionLogin();
	}
}

function actionLogin() {
	if (document.loginForm.id.value =="") {
        alert("<spring:message code="comUatUia.validate.idCheck" />"); <%-- 아이디를 입력하세요 --%>
    } else if (document.loginForm.password.value =="") {
        alert("<spring:message code="comUatUia.validate.passCheck" />"); <%-- 비밀번호를 입력하세요 --%>
    } else {
    	$.ajax({
		type:"POST",
		url:"<c:url value='/uss/umt/EgovIdDplctCnfirmAjax.do' />",
		data:{
			"checkId": $("#id").val()			
		},
		dataType:'json',
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				//alert('returnData.usedCnt='+returnData.usedCnt);
				if(returnData.usedCnt > 0 ){
				}else{
					alert("<spring:message code="fail.common.idsearch" />"); <%-- 아이디를 찾을수 없습니다. 회원가입을 하셨는지요? --%>
				}
			}else{ alert("ERROR!");return;} 
		}
		});
    	$.ajax({
		type:"POST",
		url:"<c:url value='/uss/umt/EgovPwdDplctCnfirmAjax.do' />",
		data:{
			"checkId": $("#id").val(),
			"checkPwd": $("#password").val()			
		},
		dataType:'json',
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				//alert('returnData.usedCnt='+returnData.usedCnt);
				if(returnData.usedCnt > 0 ){
				}else{
					alert("<spring:message code="fail.common.login.password2" />"); <%-- 비밀번호가 맞지 않습니다. --%>
				}
			}else{ alert("ERROR!");return;} 
		}
		
	  }); 
    	document.loginForm.action="<c:url value='/uat/uia/actionLogin.do'/>";  
    	document.loginForm.submit();
   }
	
}

function actionCrtfctLogin() {
    document.defaultForm.action="<c:url value='/uat/uia/actionCrtfctLogin.do'/>";
    document.defaultForm.submit();
}

function goFindId() {
    document.defaultForm.action="<c:url value='/uat/uia/egovIdPasswordSearch.do'/>";
    document.defaultForm.submit();
}

function goRegiUsr() {
	
	var useMemberManage = '${useMemberManage}';

	if(useMemberManage != 'true'){
		<%-- 사용자 관리 컴포넌트가 설치되어 있지 않습니다. \n관리자에게 문의하세요. --%>
		alert("<spring:message code="comUatUia.validate.userManagmentCheck" />");
		return false;
	}

    var userSe = document.loginForm.userSe.value;
 //alert(userSe);
    // 일반회원
    if (userSe == "GNR") {
        document.loginForm.action="<c:url value='/uss/umt/EgovStplatCnfirmMber.do'/>";
        //document.loginForm.action="<c:url value='/sec/rnc/EgovRlnmCnfirm.do'/>";///약관 동의 없이 바로 회원가입화면으로 이동해도 안됨 이유는 나중에 홍오성 210828
        document.loginForm.submit();
    // 기업회원
    } else if (userSe == "ENT") {
        document.loginForm.action="<c:url value='/uss/umt/EgovStplatCnfirmEntrprs.do'/>";
        document.loginForm.submit();
    // 업무사용자
    } else if (userSe == "USR") {
    	<%-- 업무사용자는 별도의 회원가입이 필요하지 않습니다. --%>
        alert("<spring:message code="comUatUia.validate.membershipCheck" />");
    }
}

function goGpkiIssu() {
    document.defaultForm.action="<c:url value='/uat/uia/egovGpkiIssu.do'/>";
    document.defaultForm.submit();
}

function setCookie (name, value, expires) {
    document.cookie = name + "=" + escape (value) + "; path=/; expires=" + expires.toGMTString();
}

function getCookie(Name) {
    var search = Name + "=";
    if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
        offset = document.cookie.indexOf(search);
        if (offset != -1) { // 쿠키가 존재하면
            offset += search.length;
            // set index of beginning of value
            end = document.cookie.indexOf(";", offset);
            // 쿠키 값의 마지막 위치 인덱스 번호 설정
            if (end == -1)
                end = document.cookie.length;
            return unescape(document.cookie.substring(offset, end));
        }
    }
    return "";
}

function saveid(form) {
    var expdate = new Date();
    // 기본적으로 30일동안 기억하게 함. 일수를 조절하려면 * 30에서 숫자를 조절하면 됨
    if (form.checkId.checked)
        expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 30일
    else
        expdate.setTime(expdate.getTime() - 1); // 쿠키 삭제조건
    setCookie("saveid", form.id.value, expdate);
}

function getid(form) {
    form.checkId.checked = ((form.id.value = getCookie("saveid")) != "");
}

function fnInit() {
	/* if (document.getElementById('loginForm').message.value != null) {
	    var message = document.getElementById('loginForm').message.value;
	} */
    /* if ("<c:out value='${message}'/>" != "") {
        alert("<c:out value='${message}'/>");
    } */

    /* *************************
    document.loginForm.rdoSlctUsr[0].checked = false;
    document.loginForm.rdoSlctUsr[1].checked = false;
    document.loginForm.rdoSlctUsr[2].checked = true;
    document.loginForm.userSe.value = "USR";
    document.loginForm.id.value="TEST1";
    document.loginForm.password.value="rhdxhd12";
    **************************** */

    //getid(document.loginForm);
    // 포커스
    //document.loginForm.rdoSlctUsr.focus();
    
    getid(document.loginForm);
    
    fnLoginTypeSelect("typeGnr");
    
    <c:if test="${not empty fn:trim(message) &&  message ne ''}">
    alert("<c:out value='${message}'/>");
    </c:if>
    
}

function fnLoginTypeSelect(objName){

		document.getElementById("typeGnr").className = "";
		document.getElementById("typeEnt").className = "";
		document.getElementById("typeUsr").className = "";
		
		document.getElementById(objName).className = "on";
		
		if(objName == "typeGnr"){ //일반회원
			document.loginForm.userSe.value = "GNR";
		}else if(objName == "typeEnt"){	//기업회원
			 document.loginForm.userSe.value = "ENT";
		}else if(objName == "typeUsr"){	//업무사용자
			 document.loginForm.userSe.value = "USR";
		}
	
}

function fnShowLogin(stat) {
	if (stat < 1) {	//일반로그인
		$(".login_input").eq(0).show();
		$(".login_input").eq(1).hide();
	} else {		//공인인증서 로그인
		$(".login_input").eq(0).hide();
		$(".login_type").hide();
		$(".login_input").eq(1).show();
	}
}
function fn_egov_inquire_bbsdetail73() {
  	document.BBSMasterForm7.action = "<c:url value='/images/egovframework/com/cmm/main/boolshee_mission3.PNG'/>"; 
  	document.BBSMasterForm7.submit();
}
function fn_egov_inquire_bbsdetail72() {
  	document.BBSMasterForm7.action = "<c:url value='/images/egovframework/com/cmm/main/boolshee_mission2.PNG'/>"; 
  	document.BBSMasterForm7.submit();
}
function fn_egov_inquire_bbsdetail7() {
  	document.BBSMasterForm7.action = "<c:url value='/images/egovframework/com/cmm/main/boolshee_mission.PNG'/>"; 
  	document.BBSMasterForm7.submit();
}
function fn_egov_inquire_bbsdetail74() {
  	document.BBSMasterForm7.action = "<c:url value='/images/egovframework/com/cmm/main/errRecov.PNG'/>"; 
  	document.BBSMasterForm7.submit();
}
</script>
</head>
<body onLoad="fnInit();" onkeydown="javascript:onEnterLogin();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<!-- 일반로그인 -->
<%--<div class="login_form"> --%>
<div>
	<form name="loginForm" id="loginForm" action="<c:url value='/uat/uia/actionLogin.do'/>" method="post">
	<input type="hidden" id="message" name="message" value="<c:out value='${message}'/>">
		
	<br>
	<h2><%--<font size="3em" color="green">회원가입비는</font><font size="3em" color="red">없으며</font></br> --%>
	<b><font color="blue">회원 가입</font>을 위해서</br>
	<font size="3em" color="red">추천</font>하신 분으로부터<font size="3em" color="red">추전자 아이디</font>가 필요합니다.<br></font><br>
					<ul>
						<li><a href="#" onclick="goRegiUsr(); return false;"><font size="4em" color="red">[<spring:message code="comUatUia.loginForm.regist"/>]</font></a>
						</b></li></h2><!-- 회원가입  -->
					</ul>
					<br><br>
<%--	<font size="3em" color="green">회원가입 > </font>
	<font size="3em" color="red">약관확인 동의 > </font>
	<font size="3em" color="green"> 일반회원관리 등록, </font></br></br><br> --%>
	<%--<font size="3em">아이디</font>와 <font size="3em" color="green">비밀번호</font>는 꼭 메모하여 기억합니다.  --%>
	<ul>
			<li>	
				<a href="#"
				onClick="fn_egov_inquire_bbsdetail74();return false;">
				<font size="2em" color="blue">
				<B> 사용자 관리 에러가 났을 경우 </B></font> </font>
				</a>
			</li>			
		</ul>
	<fieldset>
		<img src="<c:url value='/images/egovframework/com/uat/uia/boolshee2.png'/>" style="margin:30px 0 0px 60px" alt="login title image"  
		title="login title image">
		<%--<div class="login_type">
			<ul id="ulLoginType">
				<li><a href="javascript:fnLoginTypeSelect('typeGnr');" id="typeGnr" title=""><spring:message code="comUatUia.loginForm.GNR"/></a></li> <!-- 일반 -->
				<li><a href="javascript:fnLoginTypeSelect('typeEnt');" id="typeEnt" title=""><spring:message code="comUatUia.loginForm.ENT"/></a></li> <!-- 기업 -->
				<li><a href="javascript:fnLoginTypeSelect('typeUsr');" id="typeUsr" title=""><spring:message code="comUatUia.loginForm.USR"/></a></li> <!-- 업무 -->
			</ul>
		</div> --%>
	
		<div class="login_input">
			<ul>
				<!-- 아이디 -->
				<c:set var="title"><spring:message code="comUatUia.loginForm.id"/></c:set>
				<li>
					<label for="id">${title}</label>
					<input type="text" name="id" id="id" maxlength="10" title="${title} ${inputTxt}" placeholder="${title} ${inputTxt}">
				</li>
				<!-- 비밀번호 -->
				<c:set var="title"><spring:message code="comUatUia.loginForm.pw"/></c:set>
				<li>
					<label for="password">${title}</label>
					<input type="password" name="password" id="password" maxlength="12" title="${title} ${inputTxt}" placeholder="${title} ${inputTxt}">
				</li>
				<!-- 아이디 저장 -->
				<c:set var="title"><spring:message code="comUatUia.loginForm.idSave"/></c:set>
				<li class="chk">
					<input type="checkbox" name="checkId" class="check2" onclick="javascript:saveid(document.loginForm);" id="checkId">${title}
				</li>
				<li>				
					<input type="button" class="btn_login" value="<spring:message code="comUatUia.loginForm.login"/>" onclick="actionLogin()"> <!-- 로그인  -->				
				</li>
				<li>
					<ul class="btn_idpw" >
						<%--<li><a href="#" onclick="goRegiUsr(); return false;"><font size="3em" color="red"><spring:message code="comUatUia.loginForm.regist"/></font></a></li> <!-- 회원가입  -->
						 --%>						<li><a href="#" onclick="goFindId(); return false;"><spring:message code="comUatUia.loginForm.idPwSearch"/></a></li> <!-- 아이디/비밀번호 찾기 -->
					</ul>
				</li>
				<%--<li>
					<ul class="btn_idpw" >
						<li><a href="#" onclick="fnShowLogin(1);"><spring:message code="comUatUia.loginForm.login.gpki"/></a></li><!-- 인증서로그인 -->
						<li><a href="<c:url value='/uat/uia/egovGpkiIssu.do'/>"><spring:message code="comUatUia.loginForm.gpki.info"/></a></li><!-- 인증서안내 -->
					</ul>
				</li> --%>
				
			</ul>
		</div>
		
		<div class="login_input" style="display: none">
			<ul>
				<%--<li>
					<label for="password"><spring:message code="comUatUia.loginForm.pw"/></label><!-- 비밀번호 -->
					<input type="password" name="pwd" id="" maxlength="20" title="${title} ${inputTxt}" placeholder="<spring:message code="comUatUia.loginForm.pw"/>"><!-- 비밀번호 -->
				</li> --%>
				<%--<li>
					<input type="button" class="btn_login" value="<spring:message code="comUatUia.loginForm.login.gpki"/>" onclick="actionLogin()"><!-- 인증서로그인 -->
				</li> --%>
				<%--<li>
					<ul class="btn_idpw" >
						<li><a href="#" onclick="fnShowLogin(0);"><spring:message code="comUatUia.loginForm.login.normal"/></a></li><!-- 일반로그인 -->
					</ul>
					<ul class="btn_idpw" >
						<li>※ <spring:message code="comUatUia.loginForm.gpki.descrption"/></li>
					</ul>
				</li> --%>
			</ul>
			
		</div>
		<%--<input name="adres" id="adres" type="hidden" value="<c:out value='${adres}'/>" />  --%>
		<input name="DB_USER_ID" id="DB_USER_ID" type="hidden" value="<c:out value='${DB_USER_ID}'/>" /> 
		<input name="DB_USER_PW" id="DB_USER_PW" type="hidden" value="<c:out value='${DB_USER_PW}'/>" /> 
		<input name="loginIncorrect" id="loginIncorrect" type="hidden" value="<c:out value='${loginIncorrect}'/>" /> 
		<input name="fail_common_login" id="fail_common_login" type="hidden" value="<c:out value='${fail_common_login}'/>" />
	</fieldset>
	
	<input name="userSe" type="hidden" value="GNR"/> 
	<input name="j_username" type="hidden"/>
	</form>

	<form name="BBSMasterForm7" method="post">
		<div>
				<%--<ul>
					<li>	
						<a href="#"
						onClick="fn_egov_inquire_bbsdetail73();return false;">
						<font size="4em" color="red">
						<B> BoolShee </B></font> <font size="4em" color="green"><B> &nbsp;&nbsp;Mission  &nbsp;</B></font> & 
						<font size="4em" color="green"><B>  &nbsp; Plan Now ! 격 문</B></font>
						</a>
					</li>			
				</ul>
				<ul>
					<li>	
						<a href="#"
						onClick="fn_egov_inquire_bbsdetail72();return false;">
						<font size="4em" color="red">
						<B> BoolShee </B></font> <font size="4em" color="green"><B> &nbsp;&nbsp;Mission  &nbsp;</B></font> & 
						<font size="4em" color="green"><B>  &nbsp; Plan Now ! 동지 추천 앙망</B></font>
						</a>
					</li>			
				</ul> --%>
				<br><br>
				<ul>
					<li>	
						<a href="#"
						onClick="fn_egov_inquire_bbsdetail7();return false;">
						<font size="4em" color="red">
						<B> BoolShee </B></font> <font size="4em" color="green"><B> &nbsp;&nbsp;Mission  &nbsp;</B></font> & 
						<%--<font size="4em" color="green"><B>  &nbsp; Plan Now ! 사명</B></font> --%>
						<font size="4em" color="green"><B>  &nbsp; Plan Now ! </B></font>
						</a>
					</li>			
				</ul>
		</div>
	</form>
</div></h2>

<!-- 팝업 폼 -->
<form name="defaultForm" action ="" method="post" target="_blank">
<div style="visibility:hidden;display:none;">
<input name="iptSubmit3" type="submit" value="<spring:message code="comUatUia.loginForm.submit"/>" title="<spring:message code="comUatUia.loginForm.submit"/>">
</div>
</form>
<!-- login영역 //-->



</body>
</html>

