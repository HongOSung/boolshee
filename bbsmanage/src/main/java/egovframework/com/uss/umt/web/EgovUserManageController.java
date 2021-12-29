package egovframework.com.uss.umt.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.umt.service.EgovUserManageService;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.com.uss.umt.service.UserManageVO;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * 업무사용자관련 요청을  비지니스 클래스로 전달하고 처리된결과를  해당   웹 화면으로 전달하는  Controller를 정의한다
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
public class EgovUserManageController {

	/** userManageService */
	@Resource(name = "userManageService")
	private EgovUserManageService userManageService;

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
	 * 사용자목록을 조회한다. (pageing)
	 * @param userSearchVO 검색조건정보
	 * @param model 화면모델
	 * @return cmm/uss/umt/EgovUserManage
	 * @throws Exception
	 */
	@IncludedInfo(name = "업무사용자관리", order = 460, gid = 50)
	@RequestMapping(value = "/uss/umt/EgovUserManage.do")
	public String selectUserList(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		/** EgovPropertyService */
		userSearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		userSearchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userSearchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userSearchVO.getPageUnit());
		paginationInfo.setPageSize(userSearchVO.getPageSize());

		userSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> userList = userManageService.selectUserList(userSearchVO);
		model.addAttribute("resultList", userList);

		int totCnt = userManageService.selectUserListTotCnt(userSearchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		//사용자상태코드를 코드정보로부터 조회
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM013");
		List<?> emplyrSttusCode_result = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("emplyrSttusCode_result", emplyrSttusCode_result);//사용자상태코드목록

		return "egovframework/com/uss/umt/EgovUserManage";
	}

	/**
	 * 사용자등록화면으로 이동한다.
	 * @param userSearchVO 검색조건정보
	 * @param userManageVO 사용자초기화정보
	 * @param model 화면모델
	 * @return cmm/uss/umt/EgovUserInsert
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovUserInsertView.do")
	public String insertUserView(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, @ModelAttribute("userManageVO") UserManageVO userManageVO, Model model)
			throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		//패스워드힌트목록을 코드정보로부터 조회
		vo.setCodeId("COM022");
		List<?> passwordHint_result = cmmUseService.selectCmmCodeDetail(vo);
		//성별구분코드를 코드정보로부터 조회
		vo.setCodeId("COM014");
		List<?> sexdstnCode_result = cmmUseService.selectCmmCodeDetail(vo);
		//사용자상태코드를 코드정보로부터 조회
		vo.setCodeId("COM013");
		List<?> emplyrSttusCode_result = cmmUseService.selectCmmCodeDetail(vo);
		//소속기관코드를 코드정보로부터 조회 - COM025
		vo.setCodeId("COM025");
		List<?> insttCode_result = cmmUseService.selectCmmCodeDetail(vo);
		//조직정보를 조회 - ORGNZT_ID정보
		vo.setTableNm("COMTNORGNZTINFO");
		List<?> orgnztId_result = cmmUseService.selectOgrnztIdDetail(vo);
		//그룹정보를 조회 - GROUP_ID정보
		vo.setTableNm("COMTNORGNZTINFO");
		List<?> groupId_result = cmmUseService.selectGroupIdDetail(vo);

		model.addAttribute("passwordHint_result", passwordHint_result); //패스워트힌트목록
		model.addAttribute("sexdstnCode_result", sexdstnCode_result); //성별구분코드목록
		model.addAttribute("emplyrSttusCode_result", emplyrSttusCode_result);//사용자상태코드목록
		model.addAttribute("insttCode_result", insttCode_result); //소속기관코드목록
		model.addAttribute("orgnztId_result", orgnztId_result); //조직정보 목록
		model.addAttribute("groupId_result", groupId_result); //그룹정보 목록

		return "egovframework/com/uss/umt/EgovUserInsert";
	}

	/**
	 * 사용자등록처리후 목록화면으로 이동한다.
	 * @param userManageVO 사용자등록정보
	 * @param bindingResult 입력값검증용 bindingResult
	 * @param model 화면모델
	 * @return forward:/uss/umt/EgovUserManage.do
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovUserInsert.do")
	public String insertUser(@ModelAttribute("userManageVO") UserManageVO userManageVO, BindingResult bindingResult, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		beanValidator.validate(userManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/umt/EgovUserInsert";
		} else {
			if ("".equals(userManageVO.getOrgnztId())) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
				userManageVO.setOrgnztId(null);
			}
			if ("".equals(userManageVO.getGroupId())) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
				userManageVO.setGroupId(null);
			}
			userManageService.insertUser(userManageVO);
			//Exception 없이 진행시 등록성공메시지
			model.addAttribute("resultMsg", "success.common.insert");
		}
		return "forward:/uss/umt/EgovUserManage.do";
	}

	/**
	 * 사용자정보 수정을 위해 사용자정보를 상세조회한다.
	 * @param uniqId 상세조회대상 사용자아이디
	 * @param userSearchVO 검색조건
	 * @param model 화면모델
	 * @return uss/umt/EgovUserSelectUpdt
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovUserSelectUpdtView.do")
	public String updateUserView(@RequestParam("selectedId") String uniqId, @ModelAttribute("searchVO") UserDefaultVO userSearchVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		//패스워드힌트목록을 코드정보로부터 조회
		vo.setCodeId("COM022");
		List<?> passwordHint_result = cmmUseService.selectCmmCodeDetail(vo);
		//성별구분코드를 코드정보로부터 조회
		vo.setCodeId("COM014");
		List<?> sexdstnCode_result = cmmUseService.selectCmmCodeDetail(vo);
		//사용자상태코드를 코드정보로부터 조회
		vo.setCodeId("COM013");
		List<?> emplyrSttusCode_result = cmmUseService.selectCmmCodeDetail(vo);
		//소속기관코드를 코드정보로부터 조회 - COM025
		vo.setCodeId("COM025");
		List<?> insttCode_result = cmmUseService.selectCmmCodeDetail(vo);
		//조직정보를 조회 - ORGNZT_ID정보
		vo.setTableNm("COMTNORGNZTINFO");
		List<?> orgnztId_result = cmmUseService.selectOgrnztIdDetail(vo);
		//그룹정보를 조회 - GROUP_ID정보
		vo.setTableNm("COMTNORGNZTINFO");
		List<?> groupId_result = cmmUseService.selectGroupIdDetail(vo);

		model.addAttribute("passwordHint_result", passwordHint_result); //패스워트힌트목록
		model.addAttribute("sexdstnCode_result", sexdstnCode_result); //성별구분코드목록
		model.addAttribute("emplyrSttusCode_result", emplyrSttusCode_result);//사용자상태코드목록
		model.addAttribute("insttCode_result", insttCode_result); //소속기관코드목록
		model.addAttribute("orgnztId_result", orgnztId_result); //조직정보 목록
		model.addAttribute("groupId_result", groupId_result); //그룹정보 목록

		UserManageVO userManageVO = new UserManageVO();
		userManageVO = userManageService.selectUser(uniqId);
		model.addAttribute("userSearchVO", userSearchVO);
		model.addAttribute("userManageVO", userManageVO);

		return "egovframework/com/uss/umt/EgovUserSelectUpdt";
	}
	
	/**
	 * 로그인인증제한 해제 
	 * @param userManageVO 사용자정보
	 * @param model 화면모델
	 * @return uss/umt/EgovUserSelectUpdtView.do
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovUserLockIncorrect.do")
	public String updateLockIncorrect(UserManageVO userManageVO, Model model)
			throws Exception {
		
		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}
		
		userManageService.updateLockIncorrect(userManageVO);
		
		return "forward:/uss/umt/EgovUserSelectUpdtView.do";
	}

	/**
	 * 사용자정보 수정후 목록조회 화면으로 이동한다.
	 * @param userManageVO 사용자수정정보
	 * @param bindingResult 입력값검증용 bindingResult
	 * @param model 화면모델
	 * @return forward:/uss/umt/EgovUserManage.do
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovUserSelectUpdt.do")
	public String updateUser(@ModelAttribute("userManageVO") UserManageVO userManageVO, BindingResult bindingResult, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		beanValidator.validate(userManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("resultMsg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "forward:/uss/umt/EgovUserManage.do";
		} else {
			//업무사용자 수정시 히스토리 정보를 등록한다.
			userManageService.insertUserHistory(userManageVO);
			if ("".equals(userManageVO.getOrgnztId())) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
				userManageVO.setOrgnztId(null);
			}
			if ("".equals(userManageVO.getGroupId())) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
				userManageVO.setGroupId(null);
			}
			userManageService.updateUser(userManageVO);
			//Exception 없이 진행시 수정성공메시지
			model.addAttribute("resultMsg", "success.common.update");
			return "forward:/uss/umt/EgovUserManage.do";
		}
	}

	/**
	 * 사용자정보삭제후 목록조회 화면으로 이동한다.
	 * @param checkedIdForDel 삭제대상아이디 정보
	 * @param userSearchVO 검색조건
	 * @param model 화면모델
	 * @return forward:/uss/umt/EgovUserManage.do
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovUserDelete.do")
	public String deleteUser(@RequestParam("checkedIdForDel") String checkedIdForDel, @ModelAttribute("searchVO") UserDefaultVO userSearchVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		userManageService.deleteUser(checkedIdForDel);
		//Exception 없이 진행시 등록성공메시지
		model.addAttribute("resultMsg", "success.common.delete");
		return "forward:/uss/umt/EgovUserManage.do";
	}

	/**
	 * 입력한 사용자아이디의 중복확인화면 이동
	 * @param model 화면모델
	 * @return uss/umt/EgovIdDplctCnfirm
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovIdDplctCnfirmView.do")
	public String checkIdDplct(ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		model.addAttribute("checkId", "");
		model.addAttribute("usedCnt", "-1");
		return "egovframework/com/uss/umt/EgovIdDplctCnfirm";
	}

	/**
	 * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
	 * @param commandMap 파라메터전달용 commandMap
	 * @param model 화면모델
	 * @return uss/umt/EgovIdDplctCnfirm
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovIdDplctCnfirm.do")
	public String checkIdDplct(@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		String checkId = (String) commandMap.get("checkId");
		checkId = new String(checkId.getBytes("ISO-8859-1"), "UTF-8");

		if (checkId == null || checkId.equals(""))
			return "forward:/uss/umt/EgovIdDplctCnfirmView.do";

		int usedCnt = userManageService.checkIdDplct(checkId);
		model.addAttribute("usedCnt", usedCnt);
		model.addAttribute("checkId", checkId);

		return "egovframework/com/uss/umt/EgovIdDplctCnfirm";
	}
	
	/**
	 * 입력한 추천자아이디의 존재여부를 확인
	 * @param commandMap 파라메터전달용 commandMap
	 * @param model 화면모델
	 * @return uss/umt/EgovIdDplctCnfirm
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovRecommenderIdCnfirmAjax.do")
	public ModelAndView checkRecommenderIdCnfirmAjax(@RequestParam Map<String, Object> commandMap) throws Exception {

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");

		String checkId = (String) commandMap.get("checkId");
		//checkId = new String(checkId.getBytes("ISO-8859-1"), "UTF-8");

		int usedCnt = userManageService.checkRecommenderIdCnfirm(checkId);
		modelAndView.addObject("usedCnt", usedCnt);
		modelAndView.addObject("checkId", checkId);

		return modelAndView;
	}
	
	/**
	 * 회원 아이디와 소시민단체 명의 조합에 의한 primary key의 중복여부를 확인
	 * @param commandMap 파라메터전달용 commandMap
	 * @param model 화면모델
	 * @return uss/umt/EgovIdDplctCnfirm
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovBbsMemberDpKeyCnfirmAjaxHp.do")
	public ModelAndView EgovBbsMemberDpKeyCnfirmAjaxHp(@RequestParam Map<String, Object> commandMap) throws Exception {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
		String mberId = (String) commandMap.get("mberId");
		String bbsNm_S = (String) commandMap.get("bbsNm_S");
		String mbId_bsNm = mberId+bbsNm_S;
		int usedCnt = userManageService.checkIdDplct_BbsMberHp(mbId_bsNm);
		modelAndView.addObject("usedCnt", usedCnt);
		modelAndView.addObject("mberId", mberId);
		modelAndView.addObject("bbsNm_S", bbsNm_S);
		return modelAndView;
	}
	
	@RequestMapping(value = "/uss/umt/EgovBbsMemberDpKeyCnfirmAjaxNt.do")
	public ModelAndView EgovBbsMemberDpKeyCnfirmAjaxNt(@RequestParam Map<String, Object> commandMap) throws Exception {

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");

		String mberId = (String) commandMap.get("mberId");
		String bbsNm_S = (String) commandMap.get("bbsNm_S");
		//System.err.println("=========================mberId="+mberId);
		//System.err.println("=========================bbsNm_S="+bbsNm_S);
		String mbId_bsNm = mberId+bbsNm_S;
		//System.err.println("=========================mbId_bsNm="+mbId_bsNm);
		int usedCnt = userManageService.checkIdDplct_BbsMberNt(mbId_bsNm);
		//System.err.println("=========================usedCnt="+usedCnt);
		modelAndView.addObject("usedCnt", usedCnt);
		modelAndView.addObject("mberId", mberId);
		modelAndView.addObject("bbsNm_S", bbsNm_S);
		//System.err.println("=========================mberId="+mberId);
		//System.err.println("=========================bbsNm_S="+bbsNm_S);
		return modelAndView;
	}
	
	/**
	 * 회원 아이디와 소시민단체 명의 조합에 의한 primary key의 중복여부를 확인
	 * @param commandMap 파라메터전달용 commandMap
	 * @param model 화면모델
	 * @return uss/umt/EgovIdDplctCnfirm
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovBbsMemberDpKeyCnfirmAjax.do")
	public ModelAndView EgovBbsMemberDpKeyCnfirmAjax(@RequestParam Map<String, Object> commandMap) throws Exception {

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");

		String mberId = (String) commandMap.get("mberId");
		String bbsNm_S = (String) commandMap.get("bbsNm_S");
		//checkId = new String(checkId.getBytes("ISO-8859-1"), "UTF-8");
		//System.err.println("=========================mberId="+mberId);
		//System.err.println("=========================bbsNm_S="+bbsNm_S);
		String mbId_bsNm = mberId+bbsNm_S;
		//System.err.println("=========================mbId_bsNm="+mbId_bsNm);
		int usedCnt = userManageService.checkIdDplct_BbsMber(mbId_bsNm);
		//System.err.println("=========================usedCnt="+usedCnt);
		modelAndView.addObject("usedCnt", usedCnt);
		modelAndView.addObject("mberId", mberId);
		modelAndView.addObject("bbsNm_S", bbsNm_S);
		//System.err.println("=========================mberId="+mberId);
		//System.err.println("=========================bbsNm_S="+bbsNm_S);
		return modelAndView;
	}
	
	/**
	 * 회원 아이디와 선거구 또는 투표소 primary key(no) 조합에 의한 중복여부를 확인
	 * @param commandMap 파라메터전달용 commandMap
	 * @param model 화면모델
	 * @return uss/umt/EgovIdDplctCnfirm
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovDistricMemberDpKeyCnfirmAjax.do")
	public ModelAndView EgovDistricMemberDpKeyCnfirmAjax(@RequestParam Map<String, Object> commandMap) throws Exception {

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
    	String no = (String) commandMap.get("no");
		String mberId = (String) commandMap.get("mberId");
		String no_mbId = no+mberId;
		int usedCnt = userManageService.checkIdDplct_noMbid(no_mbId);
		modelAndView.addObject("usedCnt", usedCnt);
		modelAndView.addObject("mberId", mberId);// 
		modelAndView.addObject("no", no);

		return modelAndView;
	}
	
	@RequestMapping(value = "/uss/umt/EgovGepyosoReponseDpKeyCnfirmAjax.do")
	public ModelAndView EgovGepyosoReponseDpKeyCnfirmAjax(@RequestParam Map<String, Object> commandMap) throws Exception {

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
    	String no = (String) commandMap.get("no");
		String mberId = (String) commandMap.get("mberId");
		String no_mbId = no+mberId;
		int usedCnt = userManageService.checkIdGepyosoResponse_noMbid(no_mbId);
		modelAndView.addObject("usedCnt", usedCnt);
		modelAndView.addObject("mberId", mberId);// 
		modelAndView.addObject("no", no);

		return modelAndView;
	}
	
	@RequestMapping(value = "/uss/umt/EgovSajeonTupyosoReponseDpKeyCnfirmAjax.do")
	public ModelAndView EgovSajeonTupyosoReponseDpKeyCnfirmAjax(@RequestParam Map<String, Object> commandMap) throws Exception {

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
    	String no = (String) commandMap.get("no");
		String mberId = (String) commandMap.get("mberId");
		String no_mbId = no+mberId;
		int usedCnt = userManageService.checkIdSajeonTupyosoResponse_noMbid(no_mbId);
		modelAndView.addObject("usedCnt", usedCnt);
		modelAndView.addObject("mberId", mberId);// 
		modelAndView.addObject("no", no);

		return modelAndView;
	}
	
	/**
	 * 회원 아이디와 선거구 또는 투표소 primary key(no) 조합에 의한 중복여부를 확인
	 * @param commandMap 파라메터전달용 commandMap
	 * @param model 화면모델
	 * @return uss/umt/EgovIdDplctCnfirm
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovPollWatchMemberDpKeyCnfirmAjax.do")
	public ModelAndView EgovPollWatchMemberDpKeyCnfirmAjax(@RequestParam Map<String, Object> commandMap) throws Exception {

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
    	String no = (String) commandMap.get("no");
		String mberId = (String) commandMap.get("mberId");
		
		String no_mbId = no+mberId;
		int usedCnt = userManageService.checkIdDplct_pollWatch(no_mbId);
		modelAndView.addObject("usedCnt", usedCnt);
		modelAndView.addObject("mberId", mberId);// 
		modelAndView.addObject("no", no);

		return modelAndView;
	}
	
	/**
	 * 회원 아이디와 소시민단체명  조합에 의한 primary key 로 중복여부를 확인
	 * @param commandMap 파라메터전달용 commandMap
	 * @param model 화면모델
	 * @return uss/umt/EgovIdDplctCnfirm
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovBBSMasterDpKeyCnfirmAjax.do")
	public ModelAndView EgovBBSMasterDpKeyCnfirmAjax(@RequestParam Map<String, Object> commandMap) throws Exception {
		System.err.println("==============");
		System.exit(0);
    	ModelAndView modelAndView = new ModelAndView();
    	System.err.println("=======111=======");
    	modelAndView.setViewName("jsonView");
    	System.err.println("========222======");
    	//System.err.println("===============(String) commandMap.get(\"bbsNm\")="+(String) commandMap.get("bbsNm"));
    	String bbsNm = (String) commandMap.get("bbsNm");
		//String mberId = (String) commandMap.get("mberId");
		
		//System.err.println("=========================no="+no);
		//System.err.println("=========================mberId="+mberId);
		
		//String no_mbId = bbsNm+mberId;
		System.err.println("=========================bbsNm="+bbsNm);
		int usedCnt = userManageService.EgovBBSMasterDpKeyCnfirmAjax(bbsNm);
		System.err.println("============EgovBBSMasterDpKeyCnfirmAjax==========usedCnt="+usedCnt);
		modelAndView.addObject("usedCnt", usedCnt);
		//modelAndView.addObject("mberId", mberId);// 
		modelAndView.addObject("bbsNm", bbsNm);

		return modelAndView;
	}
	
	/**
	 * 도움요청 명과 도움등록자 조합에 의한 중복여부를 확인
	 * @param commandMap 파라메터전달용 commandMap
	 * @param model 화면모델
	 * @return uss/umt/EgovIdDplctCnfirm
	 * @throws Exception
	 */
	//@RequestBody
	// 이쪽으로 ajax가 오지 않음??????????? 2021.11.30 홍오성
	@RequestMapping(value = "/uss/umt/EgovPollWatchMemberDpKeyCnfirmAjaxHp.do")
	@ResponseBody
	public ModelAndView EgovPollWatchMemberDpKeyCnfirmAjaxHp(@RequestParam Map<String, Object> commandMap) throws Exception {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
		String bbsNm = (String) commandMap.get("bbsNm");
		String mberId = (String) commandMap.get("mberId");
		System.err.println("=========================bbsNm="+bbsNm);
		System.err.println("=========================mberId="+mberId);
		int usedCnt = userManageService.checkIdDplct_pollWatchHp(bbsNm);
		System.err.println("========================usedCnt="+usedCnt);
		modelAndView.addObject("usedCnt", usedCnt);
		modelAndView.addObject("bbsNm", bbsNm);
		modelAndView.addObject("mberId", mberId);
		return modelAndView;
	}
	
	@RequestMapping(value = "/uss/umt/EgovPollWatchMemberDpKeyCnfirmAjaxNt.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView EgovPollWatchMemberDpKeyCnfirmAjaxNt(@RequestParam Map<String, Object> commandMap) throws Exception {
		System.err.println("================1111111111=========bbsNm");
		//System.exit(0);
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
    	
    	//String no = (String) commandMap.get("no");
		String bbsNm = (String) commandMap.get("bbsNm");
		System.err.println("=============00000000000============bbsNm="+bbsNm);
		//System.exit(0);
		//System.err.println("=========================no="+no);
		//System.err.println("=========================mberId="+mberId);
		
		//String no_mbId = no+mberId;
		//System.err.println("=========================bbsNm="+bbsNm);
		int usedCnt = userManageService.checkIdDplct_pollWatchNt(bbsNm);
		System.err.println("================22222222222222=======usedCnt="+usedCnt);
		modelAndView.addObject("usedCnt", usedCnt);
		//System.exit(0);
		modelAndView.addObject("bbsNm", bbsNm);// 
		//modelAndView.addObject("no", no);

		return modelAndView;
	}
	
	/**
	 * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
	 * @param commandMap 파라메터전달용 commandMap
	 * @param model 화면모델
	 * @return uss/umt/EgovIdDplctCnfirm
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovIdDplctCnfirmAjax.do")
	public ModelAndView checkIdDplctAjax(@RequestParam Map<String, Object> commandMap) throws Exception {

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");

		String checkId = (String) commandMap.get("checkId");
		//checkId = new String(checkId.getBytes("ISO-8859-1"), "UTF-8");

		int usedCnt = userManageService.checkIdDplct(checkId);
		modelAndView.addObject("usedCnt", usedCnt);
		modelAndView.addObject("checkId", checkId);

		return modelAndView;
	}
	
	/**
	 * 입력한 사용자패스워드의 존재여부를 확인
	 * @param commandMap 파라메터전달용 commandMap
	 * @param model 화면모델
	 * @return uss/umt/EgovPwdDplctCnfirm
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovPwdDplctCnfirmAjax.do")
	public ModelAndView checkPwdDplctAjax(@RequestParam Map<String, Object> commandMap) throws Exception {

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("jsonView");
    	String checkId = (String) commandMap.get("checkId");
    	String checkPwd = (String) commandMap.get("checkPwd");
    	////System.err.println("========uss/umt======암호화 하기전 비번과 아이디, checkPwd="+checkPwd+"===checkId="+checkId);
		String enpassword = EgovFileScrty.encryptPassword(checkPwd, checkId);
    	////System.err.println("========uss/umt======암호화 한 후 비번 enpassword="+enpassword);
    	 checkPwd = enpassword;

		int usedCnt = userManageService.checkPwdDplct(checkPwd);
		modelAndView.addObject("usedCnt", usedCnt);
		modelAndView.addObject("checkPwd", checkPwd);

		return modelAndView;
	}

	/**
	 * 업무사용자 암호 수정처리 후 화면 이동
	 * @param model 화면모델
	 * @param commandMap 파라메터전달용 commandMap
	 * @param userSearchVO 검색조 건
	 * @param userManageVO 사용자수정정보(비밀번호)
	 * @return uss/umt/EgovUserPasswordUpdt
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovUserPasswordUpdt.do")
	public String updatePassword(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("userManageVO") UserManageVO userManageVO) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		String oldPassword = (String) commandMap.get("oldPassword");
		String newPassword = (String) commandMap.get("newPassword");
		String newPassword2 = (String) commandMap.get("newPassword2");
		String uniqId = (String) commandMap.get("uniqId");

		boolean isCorrectPassword = false;
		UserManageVO resultVO = new UserManageVO();
		userManageVO.setPassword(newPassword);
		userManageVO.setOldPassword(oldPassword);
		userManageVO.setUniqId(uniqId);

		String resultMsg = "";
		resultVO = userManageService.selectPassword(userManageVO);
		//패스워드 암호화
		String encryptPass = EgovFileScrty.encryptPassword(oldPassword, userManageVO.getEmplyrId());
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
			userManageVO.setPassword(EgovFileScrty.encryptPassword(newPassword, userManageVO.getEmplyrId()));
			userManageService.updatePassword(userManageVO);
			model.addAttribute("userManageVO", userManageVO);
			resultMsg = "success.common.update";
		} else {
			model.addAttribute("userManageVO", userManageVO);
		}
		model.addAttribute("userSearchVO", userSearchVO);
		model.addAttribute("resultMsg", resultMsg);

		return "egovframework/com/uss/umt/EgovUserPasswordUpdt";
	}

	/**
	 * 업무사용자 암호 수정  화면 이동
	 * @param model 화면모델
	 * @param commandMap 파라메터전달용 commandMap
	 * @param userSearchVO 검색조건
	 * @param userManageVO 사용자수정정보(비밀번호)
	 * @return uss/umt/EgovUserPasswordUpdt
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovUserPasswordUpdtView.do")
	public String updatePasswordView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("userManageVO") UserManageVO userManageVO) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		String userTyForPassword = (String) commandMap.get("userTyForPassword");
		userManageVO.setUserTy(userTyForPassword);

		model.addAttribute("userManageVO", userManageVO);
		model.addAttribute("userSearchVO", userSearchVO);
		return "egovframework/com/uss/umt/EgovUserPasswordUpdt";
	}

}
