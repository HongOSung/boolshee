package egovframework.com.uss.umt.web;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.config.EgovLoginConfig;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.uat.uia.service.EgovLoginService;
import egovframework.com.uss.umt.service.EgovMberManageService;
import egovframework.com.uss.umt.service.MberManageVO;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 일반회원관련 요청을  비지니스 클래스로 전달하고 처리된결과를  해당   웹 화면으로 전달하는  Controller를 정의한다
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2011.08.26	 정진오			IncludedInfo annotation 추가
 *   2014.12.08	 이기하			암호화방식 변경(EgovFileScrty.encryptPassword)
 *   2015.06.16	 조정국			수정시 유효성체크 후 에러발생 시 목록으로 이동하여 에러메시지 표시
 *   2015.06.19	 조정국			미인증 사용자에 대한 보안처리 기준 수정 (!isAuthenticated)
 *   2017.07.21  장동한 			로그인인증제한 작업
 *
 * </pre>
 */

@Controller
public class EgovMberManageController {
	
	/** EgovLoginService */
	@Resource(name = "loginService")
	private EgovLoginService loginService;
	
	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name = "egovLoginConfig")
	EgovLoginConfig egovLoginConfig;

	/** mberManageService */
	@Resource(name = "mberManageService")
	private EgovMberManageService mberManageService;

	/** cmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** DefaultBeanValidator beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 일반회원목록을 조회한다. (pageing)
	 * @param userSearchVO 검색조건정보
	 * @param model 화면모델
	 * @return uss/umt/EgovMberManage
	 * @throws Exception
	 */
	@IncludedInfo(name = "일반회원관리", order = 470, gid = 50)
	@RequestMapping(value = "/uss/umt/EgovMberManage.do")
	public String selectMberList(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, 
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO, //@RequestParam("checkedIdForDel") String checkedIdForDel,
			HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		//Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		//if (!isAuthenticated) {
		//	return "index";
		//}
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId = loginVO.getId();
		request.setAttribute("mberId", mberId); 
		if(mberId.equals("joseph0510")|| mberId.equals("oshong34") ){// 
			/** EgovPropertyService */
			userSearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
			userSearchVO.setPageSize(propertiesService.getInt("pageSize"));
			
			/** pageing */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(userSearchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(userSearchVO.getPageUnit());
			paginationInfo.setPageSize(userSearchVO.getPageSize());
			
			mberManageService.deleteGnrMber(userSearchVO);
			mberManageService.insertCOMTNGNRLMBER(mberManageVO);
			mberManageService.insertPollWatch(mberManageVO);			
			mberManageService.insertGepyosoResponse(mberManageVO);			
			mberManageService.insertMnaResponse(mberManageVO);			
			mberManageService.insertSajeonResponse(mberManageVO);	
			mberManageService.updateGnrMber(mberManageVO);
			
			userSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			userSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			userSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			List<?> mberList = mberManageService.selectGnrMber(userSearchVO);
			int totCnt = mberManageService.selectGnrMberTotCnt(userSearchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("resultList", mberList);
			model.addAttribute("resultCnt", totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			return "egovframework/com/uss/umt/EgovMberManage";
			
		}else {
			 mberManageVO = mberManageService.selectMberHp(mberId);
			 request.setAttribute("selectedId", mberId);
			 return "forward:/uss/umt/EgovMberSelectUpdtViewHp.do";	
			 
		}
	}
	
	@IncludedInfo(name = "내 추천자 보기", order = 470, gid = 50)
	@RequestMapping(value = "/uss/umt/EgovMyMberManage.do")
	public String selectMyMberList(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, 
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO,
			HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId = loginVO.getId();
		request.setAttribute("mberId", mberId); 
			/** EgovPropertyService */
			userSearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
			userSearchVO.setPageSize(propertiesService.getInt("pageSize"));

			/** pageing */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(userSearchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(userSearchVO.getPageUnit());
			paginationInfo.setPageSize(userSearchVO.getPageSize());
			
			mberManageService.deleteGnrMber(userSearchVO);
			mberManageService.insertCOMTNGNRLMBER(mberManageVO);
			mberManageService.insertPollWatch(mberManageVO);			
			mberManageService.insertGepyosoResponse(mberManageVO);			
			mberManageService.insertMnaResponse(mberManageVO);			
			mberManageService.insertSajeonResponse(mberManageVO);		
			mberManageService.updateGnrMber(mberManageVO);
			
			userSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			userSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			userSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			userSearchVO.setMberId(mberId);
			List<?> mberList = mberManageService.selectMyGnrMber(userSearchVO);
			int totCnt = mberManageService.selectMyGnrMberTotCnt(userSearchVO);
			model.addAttribute("resultList", mberList);
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("resultCnt", totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			return "egovframework/com/uss/umt/EgovMyMberManage";
		
	}

	/**
	 * 소시민단체 가입 화면으로 이동한다.
	 * @param userSearchVO 검색조건정보
	 * @param mberManageVO 일반회원초기화정보
	 * @param model 화면모델
	 * @return uss/umt/EgovMberInsert
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovMberInsertView.do")
	public String insertMberView(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO, Model model,
			@ModelAttribute("boardMaster") BoardMaster boardMaster,
			HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
		    @RequestParam("bbsId") String bbsId //, @RequestParam("bbsNm") String BBS_NM
		    )
			throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}
		/*//패스워드힌트목록을 코드정보로부터 조회
		vo.setCodeId("COM022");
		List<?> passwordHint_result = cmmUseService.selectCmmCodeDetail(vo);
		//성별구분코드를 코드정보로부터 조회
		vo.setCodeId("COM014");
		List<?> sexdstnCode_result = cmmUseService.selectCmmCodeDetail(vo);*/
		//사용자상태코드를 코드정보로부터 조회
		//vo.setCodeId("COM013");
		//List<?> mberSttus_result = cmmUseService.selectCmmCodeDetail(vo);
		//그룹정보를 조회 - GROUP_ID정보
		//vo.setTableNm("COMTNORGNZTINFO");
		//vo.setTableNm("COMTNBBSMASTER");
		//List<?> groupId_result = cmmUseService.selectGroupIdDetail(vo);
		//List bbsNm_result = cmmUseService.selectbbsNmDetail(vo);
		/*model.addAttribute("passwordHint_result", passwordHint_result); //패스워트힌트목록
		model.addAttribute("sexdstnCode_result", sexdstnCode_result); //성별구분코드목록*/
		//model.addAttribute("mberSttus_result", mberSttus_result); //사용자상태코드목록
		//.addAttribute("bbsNm_result", bbsNm_result); //그룹정보 목록
		//((ServletRequest) model).setAttribute("bbsNm_result", bbsNm_result); //그룹정보 목록
		//model.addAttribute("deptManageList", egovDeptManageService.selectDeptManageList(deptManageVO));
		
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setBbsId(bbsId);
		//vo.setBBS_NM(BBS_NM);
		
		// 세션에 저장된 값 가져오기 
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");
		String mberId=(String) session.getAttribute("accessUser");
		mberId=mberId.substring(3);
		
		request.setAttribute("mberId", mberId);
		String mberNm = loginVO.getName();
		request.setAttribute("mberNm", mberNm);		
		vo.setMberId(mberId);
		model.addAttribute("bbsNm_result", cmmUseService.selectbbsNmDetail(vo));
		return "egovframework/com/uss/umt/EgovMberInsert";
	}
	
	/**
	 * 도움요청 등록 화면으로 이동한다.
	 * @param userSearchVO 검색조건정보
	 * @param mberManageVO 일반회원초기화정보
	 * @param model 화면모델
	 * @return uss/umt/EgovMberInsert
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovMberInsertViewHp.do")
	public String insertMberViewHp(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO, Model model,
			@ModelAttribute("boardMaster") BoardMaster boardMaster,
			HttpSession session, HttpServletRequest request, HttpServletResponse response,
		    @RequestParam("bbsId") String bbsId)
			throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}
		
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setBbsId(bbsId);
		
		// 세션에 저장된 값 가져오기 
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");
		String mberId=(String) session.getAttribute("accessUser");
		mberId=mberId.substring(3);
		
		request.setAttribute("mberId", mberId);
		String mberNm = loginVO.getName();
		request.setAttribute("mberNm", mberNm);		
		vo.setMberId(mberId);
		model.addAttribute("bbsNm_result", cmmUseService.selectbbsNmDetailHp(vo));
		//System.err.println("================model.addAttribute(\"bbsNm_result\", cmmUseService.selectbbsNmDetailHp(vo))="
		//+model.addAttribute("bbsNm_result", cmmUseService.selectbbsNmDetailHp(vo)).toString());
		return "egovframework/help/helpResponseInsert";
	}
	
	@RequestMapping("/uss/umt/EgovMberInsertViewNt.do")
	public String insertMberViewNt(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO, Model model,
			@ModelAttribute("boardMaster") BoardMaster boardMaster,
			HttpSession session, HttpServletRequest request, HttpServletResponse response,
		    @RequestParam("bbsId") String bbsId)
			throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}
		
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setBbsId(bbsId);
		
		// 세션에 저장된 값 가져오기 
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");
		String mberId=(String) session.getAttribute("accessUser");
		mberId=mberId.substring(3);
		
		request.setAttribute("mberId", mberId);
		String mberNm = loginVO.getName();
		request.setAttribute("mberNm", mberNm);		
		vo.setMberId(mberId);
		model.addAttribute("bbsNm_result", cmmUseService.selectbbsNmDetailNt(vo));
		//System.err.println("================model.addAttribute(\"bbsNm_result\", cmmUseService.selectbbsNmDetailHp(vo))="
		//+model.addAttribute("bbsNm_result", cmmUseService.selectbbsNmDetailNt(vo)).toString());
		return "egovframework/notice/noticeResponseInsert";
	}
	
	
	@RequestMapping("/uss/umt/mnaRequestInsert.do")
	public String mnaRequestInsert(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO, Model model,
			@ModelAttribute("boardMaster") BoardMaster boardMaster,
			HttpSession session, HttpServletRequest request, HttpServletResponse response,
		    @RequestParam("bbsId") String bbsId)
			throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}
		
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setBbsId(bbsId);
		
		// 세션에 저장된 값 가져오기 
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");
		String mberId=(String) session.getAttribute("accessUser");
		mberId=mberId.substring(3);
		
		request.setAttribute("mberId", mberId);
		String mberNm = loginVO.getName();
		request.setAttribute("mberNm", mberNm);		
		vo.setMberId(mberId);
		model.addAttribute("bbsNm_result", cmmUseService.selectRequestDetail(vo));
		//System.err.println("================model.addAttribute(\"bbsNm_result\", cmmUseService.selectbbsNmDetailHp(vo))="
		//+model.addAttribute("bbsNm_result", cmmUseService.selectbbsNmDetailNt(vo)).toString());
		return "egovframework/mna/mnaResponseInsert";
	}


	/**
	 * 일반회원등록처리후 목록화면으로 이동한다.
	 * @param mberManageVO 일반회원등록정보
	 * @param bindingResult 입력값검증용 bindingResult
	 * @param model 화면모델
	 * @return forward:/uss/umt/EgovMberManage.do
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovMberInsert.do")
	public String insertMber(@ModelAttribute("mberManageVO") MberManageVO mberManageVO, BindingResult bindingResult, Model model
			,@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request
			) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}
/*********************202108 홍오성 임시***************
		beanValidator.validate(mberManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			//System.err.println("=============bindingResult.hasErrors()==="+bindingResult.hasErrors());
			ComDefaultCodeVO vo = new ComDefaultCodeVO();

			//패스워드힌트목록을 코드정보로부터 조회
			vo.setCodeId("COM022");
			List<?> passwordHint_result = cmmUseService.selectCmmCodeDetail(vo);
			//성별구분코드를 코드정보로부터 조회
			vo.setCodeId("COM014");
			List<?> sexdstnCode_result = cmmUseService.selectCmmCodeDetail(vo);
			//사용자상태코드를 코드정보로부터 조회
			vo.setCodeId("COM013");
			List<?> mberSttus_result = cmmUseService.selectCmmCodeDetail(vo);
			//그룹정보를 조회 - GROUP_ID정보
			vo.setTableNm("COMTNORGNZTINFO");
			List<?> groupId_result = cmmUseService.selectGroupIdDetail(vo);
			
			model.addAttribute("passwordHint_result", passwordHint_result); //패스워트힌트목록
			model.addAttribute("sexdstnCode_result", sexdstnCode_result); //성별구분코드목록
			model.addAttribute("mberSttus_result", mberSttus_result); //사용자상태코드목록
			model.addAttribute("groupId_result", groupId_result); //그룹정보 목록
			
			return "egovframework/com/uss/umt/EgovMberInsert";
		} else {
			if ("".equals(mberManageVO.getGroupId())) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
				mberManageVO.setGroupId(null);
			}
			mberManageService.insertMber(mberManageVO);
			//Exception 없이 진행시 등록 성공메시지
			model.addAttribute("resultMsg", "success.common.insert");
		}************************/

		// 2. 로그인 처리 ***비밀번호 입력한 내용을 암호화 하는 과정 202108 홍오성
				LoginVO resultVO = loginService.actionLogin(loginVO);
				
				// 3. 일반 로그인 처리
				if (resultVO != null && resultVO.getId() != null && !resultVO.getId().equals("")) {

					// 3-1. 로그인 정보를 세션에 저장
					request.getSession().setAttribute("loginVO", resultVO);
					// 2019.10.01 로그인 인증세션 추가
					request.getSession().setAttribute("accessUser", resultVO.getUserSe().concat(resultVO.getId()));
					//System.err.println("!isAuthenticated=============Statement 1");
				    System.exit(0);
					return "redirect:/uat/uia/actionMain.do";

				} else {
					model.addAttribute("message", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
					//System.err.println("!isAuthenticated=============Statement 2");
				    System.exit(0);
					return "egovframework/com/uat/uia/EgovLoginUsr";
				}
				
		//mberManageService.insertMber(mberManageVO);
		//Exception 없이 진행시 등록 성공메시지
		//model.addAttribute("resultMsg", "success.common.insert");
		//return "forward:/uss/umt/EgovMberManage.do";			
	}
	
	
//*******************************끝***********************
	/**
	 * 일반회원정보 수정을 위해 일반회원정보를 상세조회한다.
	 * @param mberId 상세조회대상 일반회원아이디
	 * @param userSearchVO 검색조건
	 * @param model 화면모델
	 * @return uss/umt/EgovMberSelectUpdt
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovMberSelectUpdtView.do")
	public String updateMberView(@RequestParam("selectedId") String mberId,
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO,
			HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("searchVO") UserDefaultVO userSearchVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}
/*
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		System.err.println("===================selectedId======mberId="+mberId);
		//패스워드힌트목록을 코드정보로부터 조회
		vo.setCodeId("COM022");
		List<?> passwordHint_result = cmmUseService.selectCmmCodeDetail(vo);

		//성별구분코드를 코드정보로부터 조회
		vo.setCodeId("COM014");
		List<?> sexdstnCode_result = cmmUseService.selectCmmCodeDetail(vo);

		//사용자상태코드를 코드정보로부터 조회
		vo.setCodeId("COM013");
		List<?> mberSttus_result = cmmUseService.selectCmmCodeDetail(vo);

		//그룹정보를 조회 - GROUP_ID정보
		vo.setTableNm("COMTNORGNZTINFO");
		List<?> groupId_result = cmmUseService.selectGroupIdDetail(vo);

		model.addAttribute("passwordHint_result", passwordHint_result); //패스워트힌트목록
		model.addAttribute("sexdstnCode_result", sexdstnCode_result); //성별구분코드목록
		model.addAttribute("mberSttus_result", mberSttus_result); //사용자상태코드목록
		model.addAttribute("groupId_result", groupId_result); //그룹정보 목록
	*/
		//session = request.getSession();
		//LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		//String mberId = loginVO.getId();
		//request.setAttribute("mberId", mberId); 
		//String mberId = request.getParameter("mberId");
		//System.err.println("===================String mberId = request.getParameter="+mberId);
		//MberManageVO mberManageVO = mberManageService.selectMber(mberId);
		//session = request.getSession();
		//LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		//String mberId = loginVO.getId();
		//request.setAttribute("mberId", mberId); 
		
		String areaNo="",middleTelno="",endTelno="";
		areaNo =request.getParameter(areaNo);
	    middleTelno =request.getParameter(middleTelno);
		endTelno =request.getParameter(endTelno);
		String telNoCk=areaNo + middleTelno+endTelno;
		
		request.setAttribute("telNoCk", telNoCk); 
		 mberManageVO = mberManageService.selectMber(mberId);		
		model.addAttribute("mberManageVO", mberManageVO);
		model.addAttribute("userSearchVO", userSearchVO);
		return "egovframework/com/uss/umt/EgovMberSelectUpdt";
	}

	@RequestMapping("/uss/umt/EgovMberSelectUpdtViewHp.do")
	public String updateMberViewHp(
			HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("searchVO") UserDefaultVO userSearchVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId = loginVO.getId();
		request.setAttribute("mberId", mberId); 
		
		String areaNo="",middleTelno="",endTelno="";
		areaNo =request.getParameter(areaNo);
	    middleTelno =request.getParameter(middleTelno);
		endTelno =request.getParameter(endTelno);
		String telNoCk=areaNo + middleTelno+endTelno;
		
		request.setAttribute("telNoCk", telNoCk); 
		MberManageVO mberManageVO = mberManageService.selectMberHp(mberId);
		model.addAttribute("mberManageVO", mberManageVO);
		model.addAttribute("userSearchVO", userSearchVO);
		return "egovframework/com/uss/umt/EgovMberSelectUpdtHp";
	}
	
	/**
	 * 로그인인증제한 해제 
	 * @param mberManageVO 일반회원등록정보
	 * @param model 화면모델
	 * @return uss/umt/EgovMberSelectUpdtView.do
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovMberLockIncorrect.do")
	public String updateLockIncorrect(MberManageVO mberManageVO, Model model) throws Exception {

	    
	    // 미인증 사용자에 대한 보안처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        return "index";
	    }
	    
	    mberManageService.updateLockIncorrect(mberManageVO);
	    
	    return "forward:/uss/umt/EgovMberSelectUpdtView.do";
	}

	/**
	 * 일반회원정보 수정후 목록조회 화면으로 이동한다.
	 * @param mberManageVO 일반회원수정정보
	 * @param bindingResult 입력값검증용 bindingResult
	 * @param model 화면모델
	 * @return forward:/uss/umt/EgovMberManage.do
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovMberSelectUpdt.do")
	public String updateMber(@ModelAttribute("mberManageVO") MberManageVO mberManageVO, BindingResult bindingResult, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		beanValidator.validate(mberManageVO, bindingResult);
		/*if (bindingResult.hasErrors()) {
			System.err.println("======================bindingResult.getAllErrors().get(0).getDefaultMessage()="+bindingResult.getAllErrors().get(0).getDefaultMessage());
			model.addAttribute("resultMsg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "forward:/uss/umt/EgovMberManage.do";
		} else {*////2021.11.11 홍오성 수정 간편 회원가입과 수정을 위해서
			if ("".equals(mberManageVO.getGroupId())) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
				mberManageVO.setGroupId(null);
			}
			mberManageService.updateMber(mberManageVO);
			//Exception 없이 진행시 수정성공메시지
			model.addAttribute("resultMsg", "success.common.update");
			return "forward:/uss/umt/EgovMberManage.do";
		//}
	}
	
	@RequestMapping("/uss/umt/EgovMberSelectUpdtHp.do")
	public String updateMberHp(@ModelAttribute("mberManageVO") MberManageVO mberManageVO, 
			HttpServletRequest request, HttpSession session, 
			BindingResult bindingResult, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		beanValidator.validate(mberManageVO, bindingResult);
		/*if (bindingResult.hasErrors()) {
			System.err.println("======================bindingResult.getAllErrors().get(0).getDefaultMessage()="+bindingResult.getAllErrors().get(0).getDefaultMessage());
			model.addAttribute("resultMsg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "forward:/uss/umt/EgovMberManage.do";
		} else {*////2021.11.11 홍오성 수정 간편 회원가입과 수정을 위해서
			if ("".equals(mberManageVO.getGroupId())) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
				mberManageVO.setGroupId(null);
			}
			session = request.getSession();
			LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

			String mberId = loginVO.getId();
			System.err.println("==============uss/umt/EgovMberSelectUpdtHp.do====mberId="+mberId);
			mberManageVO.setMberId(mberId);
			mberManageService.updateMberHp(mberManageVO);
			//Exception 없이 진행시 수정성공메시지
			model.addAttribute("resultMsg", "success.common.update");
			//System.err.println("==================수정성공메시지="+model.addAttribute("resultMsg", "success.common.update"));
			//return "forward:/uss/umt/EgovMberManage.do";
			return "forward:/uss/umt/EgovMberSelectUpdtViewHp.do";
		//}
	}

	@RequestMapping("/uss/umt/EgovMberDelete.do")
	public String deleteMber(@RequestParam("checkedIdForDel") String checkedIdForDel, @ModelAttribute("searchVO") UserDefaultVO userSearchVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		mberManageService.deleteMber(checkedIdForDel);
		//Exception 없이 진행시 삭제성공메시지
		model.addAttribute("resultMsg", "success.common.delete");
		return "forward:/uss/umt/EgovMberManage.do";
	}
	
	@RequestMapping("/uss/umt/EgovMberDeleteHp.do")
	public String deleteMberHp(@RequestParam("checkedIdForDel") String checkedIdForDel, @ModelAttribute("searchVO") UserDefaultVO userSearchVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}
		mberManageService.deleteMberHp(checkedIdForDel);
		//Exception 없이 진행시 삭제성공메시지
		model.addAttribute("resultMsg", "success.common.delete");
		return "forward:/uat/uia/actionLogout.do";
	}

	// 탈퇴 처리 기능에 대한 예시
	@RequestMapping("/uss/umt/EgovMberWithdraw.do")
	public String withdrawMber(Model model) throws Exception {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		String returnPage = "/"; // 탈퇴 처리 후 화면 지정

		if (!isAuthenticated) {
			model.addAttribute("resultMsg", "fail.common.delete");

			return "redirect:" + returnPage;
		}

		mberManageService.deleteMber(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		//Exception 없이 진행시 삭제성공메시지
		model.addAttribute("resultMsg", "success.common.delete");

		return "redirect:" + returnPage;
	}

	/**
	 * 일반회원가입신청 등록화면으로 이동한다.
	 * @param userSearchVO 검색조건
	 * @param mberManageVO 일반회원가입신청정보
	 * @param commandMap 파라메터전달용 commandMap
	 * @param model 화면모델
	 * @return uss/umt/EgovMberSbscrb
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovMberSbscrbView.do")
	public String sbscrbMberView(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, @ModelAttribute("mberManageVO") MberManageVO mberManageVO,
			@RequestParam Map<String, Object> commandMap, Model model) throws Exception {

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		//패스워드힌트목록을 코드정보로부터 조회
		vo.setCodeId("COM022");
		List<?> passwordHint_result = cmmUseService.selectCmmCodeDetail(vo);
		//성별구분코드를 코드정보로부터 조회
		vo.setCodeId("COM014");
		List<?> sexdstnCode_result = cmmUseService.selectCmmCodeDetail(vo);

		model.addAttribute("passwordHint_result", passwordHint_result); //패스워트힌트목록
		model.addAttribute("sexdstnCode_result", sexdstnCode_result); //성별구분코드목록
		if (!"".equals(commandMap.get("realname"))) {
			model.addAttribute("mberNm", commandMap.get("realname")); //실명인증된 이름 - 주민번호 인증
			model.addAttribute("ihidnum", commandMap.get("ihidnum")); //실명인증된 주민등록번호 - 주민번호 인증
		}
		if (!"".equals(commandMap.get("realName"))) {
			model.addAttribute("mberNm", commandMap.get("realName")); //실명인증된 이름 - ipin인증
		}

		mberManageVO.setMberSttus("DEFAULT");

		return "egovframework/com/uss/umt/EgovMberSbscrb";
	}

	/**
	 * 일반회원가입신청등록처리후로그인화면으로 이동한다.
	 * @param mberManageVO 일반회원가입신청정보
	 * @return forward:/uat/uia/egovLoginUsr.do
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovMberSbscrb.do")
	public String sbscrbMber(@ModelAttribute("mberManageVO") MberManageVO mberManageVO) throws Exception {

		//가입상태 초기화
		//mberManageVO.setMberSttus("A");
		mberManageVO.setMberSttus("P");//편의상 P로 초기화 함 당분간 가입상태를 신청상태에서 승인상태로 20210818 홍오성
		String telNo=mberManageVO.getAreaNo() + mberManageVO.getMiddleTelno() + mberManageVO.getEndTelno();
		mberManageVO.setTelNo(telNo);
		String birthDay=mberManageVO.getYear() + mberManageVO.getMonth() + mberManageVO.getDay();
		mberManageVO.setBirthDay(birthDay);
		String mberId=mberManageVO.getMberId();
		mberManageVO.setMberId(mberId);
		//그룹정보 초기화
		//mberManageVO.setGroupId("1");
		//일반회원가입신청 등록시 일반회원등록기능을 사용하여 등록한다.
		mberManageService.insertMber(mberManageVO);
		return "forward:/uat/uia/egovLoginUsr.do?mberId="+ mberId;
	}

	/**
	 * 일반회원 약관확인
	 * @param model 화면모델
	 * @return uss/umt/EgovStplatCnfirm
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovStplatCnfirmMber.do")
	public String sbscrbEntrprsMber(Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		//Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		//if (!isAuthenticated) {
		//	return "index";
		//}

		//일반회원용 약관 아이디 설정
		String stplatId = "STPLAT_0000000000001";
		//회원가입유형 설정-일반회원
		String sbscrbTy = "USR01";
		//약관정보 조회
		List<?> stplatList = mberManageService.selectStplat(stplatId);
		model.addAttribute("stplatList", stplatList); //약관정보 포함
		model.addAttribute("sbscrbTy", sbscrbTy); //회원가입유형 포함

		return "egovframework/com/uss/umt/EgovStplatCnfirm";
	}

	/**
	 * @param model 화면모델
	 * @param commandMap 파라메터전달용 commandMap
	 * @param userSearchVO 검색조건
	 * @param mberManageVO 일반회원수정정보(비밀번호)
	 * @return uss/umt/EgovMberPasswordUpdt
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovMberPasswordUpdtHp.do")
	public String updatePasswordHp(ModelMap model, @RequestParam Map<String, Object> commandMap, 
			@ModelAttribute("searchVO") UserDefaultVO userSearchVO, HttpServletRequest request, HttpSession session,
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		String oldPassword = (String) commandMap.get("oldPassword");
		String newPassword = (String) commandMap.get("newPassword");
		String newPassword2 = (String) commandMap.get("newPassword2");
		//String uniqId = (String) commandMap.get("uniqId");
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId = loginVO.getId();
		request.setAttribute("mberId", mberId); 

		boolean isCorrectPassword = false;
		MberManageVO resultVO = new MberManageVO();
		mberManageVO.setPassword(newPassword);
		mberManageVO.setOldPassword(oldPassword);
		//mberManageVO.setUniqId(uniqId);
		mberManageVO.setUniqId(mberId);

		String resultMsg = "";
		resultVO = mberManageService.selectPasswordHp(mberManageVO);
		//패스워드 암호화
		String encryptPass = EgovFileScrty.encryptPassword(oldPassword, mberManageVO.getMberId());
		if (encryptPass.equals(resultVO.getPassword())) {
			if (newPassword.equals(newPassword2)) {
				isCorrectPassword = true;
			} else {
				isCorrectPassword = false;
				resultMsg = "fail.user.passwordUpdate2";
			}
		} else {
			isCorrectPassword = false;
			resultMsg = "fail.user.passwordUpdate1";
		}

		if (isCorrectPassword) {
			mberManageVO.setPassword(EgovFileScrty.encryptPassword(newPassword, mberManageVO.getMberId()));
			mberManageService.updatePasswordHp(mberManageVO);
			model.addAttribute("mberManageVO", mberManageVO);
			resultMsg = "success.common.update";
		} else {
			model.addAttribute("mberManageVO", mberManageVO);
		}
		model.addAttribute("userSearchVO", userSearchVO);
		model.addAttribute("resultMsg", resultMsg);

		return "forward:/uat/uia/actionLogout.do";
	}
	

	/**
	 * 일반회원 암호 수정 화면 이동
	 * @param model 화면모델
	 * @param commandMap 파라메터전달용 commandMap
	 * @param userSearchVO 검색조건
	 * @param mberManageVO 일반회원수정정보(비밀번호)
	 * @return uss/umt/EgovMberPasswordUpdt
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovMberPasswordUpdtView.do")
	public String updatePasswordView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO) throws Exception {
//System.err.println("=================================");
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}
		//System.err.println("==============2222222222===================");
		String userTyForPassword = (String) commandMap.get("userTyForPassword");
		mberManageVO.setUserTy(userTyForPassword);

		model.addAttribute("userSearchVO", userSearchVO);
		model.addAttribute("mberManageVO", mberManageVO);
		//System.err.println("==============333333333333333===================");
		return "egovframework/com/uss/umt/EgovMberPasswordUpdt";
	}

	@RequestMapping(value = "/uss/umt/EgovMberPasswordUpdtViewHp.do")
	public String updatePasswordViewHp(ModelMap model, @RequestParam Map<String, Object> commandMap, 
			@ModelAttribute("searchVO") UserDefaultVO userSearchVO, HttpSession session, HttpServletRequest request,
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO) throws Exception {
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		String userTyForPassword = (String) commandMap.get("userTyForPassword");
		mberManageVO.setUserTy(userTyForPassword);
		model.addAttribute("userSearchVO", userSearchVO);
		model.addAttribute("mberManageVO", mberManageVO);
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId = loginVO.getId();
		request.setAttribute("mberId", mberId); 
		return "egovframework/com/uss/umt/EgovMberPasswordUpdtHp";
	}
}