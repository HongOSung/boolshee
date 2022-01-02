package egovframework.com.cop.bbs.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovComponentChecker;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.BbsMemberVO;
//import egovframework.com.cop.bbs.service.Blog;
//import egovframework.com.cop.bbs.service.BlogUserVO;
//import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.com.uss.umt.service.MberManageVO;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**
 * 게시판 속성관리를 위한 컨트롤러  클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------       --------    ---------------------------
 *   2009.3.12  이삼섭          최초 생성
 *   2009.06.26	한성곤		    2단계 기능 추가 (댓글관리, 만족도조사)
 *	 2011.07.21 안민정          커뮤니티 관련 메소드 분리 (->EgovBBSAttributeManageController)
 *	 2011.8.26	정진오			IncludedInfo annotation 추가
 *   2011.09.15 서준식           2단계 기능 추가 (댓글관리, 만족도조사) 적용방법 변경
 *   2016.06.13 김연호          표준프레임워크 v3.6 개선
 * </pre>
 */

@Controller
public class EgovBBSMasterController {

    @Resource(name = "EgovBBSMasterService")
    private EgovBBSMasterService egovBBSMasterService;

    @Resource(name = "EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    
    @Resource(name = "egovBBSMstrIdGnrService")
    private EgovIdGnrService idgenServiceBbs;
    
    @Resource(name = "egovBlogIdGnrService")
    private EgovIdGnrService idgenServiceBlog;
    
    /** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
    


    @Autowired
    private DefaultBeanValidator beanValidator;

    //Logger log = Logger.getLogger(this.getClass());
    
    /**
     * 신규 게시판 마스터 등록을 위한 등록페이지로 이동한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/insertBBSMasterViewHp.do")
    public String insertBBSMasterViewHp(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, 
    		HttpSession session, HttpServletRequest request, HttpServletResponse response,
    		ModelMap model) throws Exception {
		BoardMasterVO boardMaster = new BoardMasterVO();
		//공통코드(게시판유형)
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM101");
		List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("bbsTyCode", codeResult);
		model.addAttribute("boardMasterVO", boardMaster);
		// 세션에 저장된 값 가져오기 
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");
		String mberId=(String) session.getAttribute("accessUser");
		mberId=mberId.substring(3);
		
		request.setAttribute("mberId", mberId);
		String mberNm = loginVO.getName();
		request.setAttribute("mberNm", mberNm);
		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------
		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}	
		return "egovframework/help/bBSMasterRegistHp";
    }
    
    @RequestMapping("/cop/bbs/insertBBSMasterViewNt.do")
    public String insertBBSMasterViewNt(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, 
    		HttpSession session, HttpServletRequest request, HttpServletResponse response,
    		ModelMap model) throws Exception {
		BoardMasterVO boardMaster = new BoardMasterVO();
		//공통코드(게시판유형)
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM101");
		List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("bbsTyCode", codeResult);
		model.addAttribute("boardMasterVO", boardMaster);
		// 세션에 저장된 값 가져오기 
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");
		String mberId=(String) session.getAttribute("accessUser");
		mberId=mberId.substring(3);
		
		request.setAttribute("mberId", mberId);
		String mberNm = loginVO.getName();
		request.setAttribute("mberNm", mberNm);
		//System.err.println("=====insertBBSMasterViewNt======boardMaster.getTel()="+boardMaster.getTel());
		System.err.println("=====insertBBSMasterViewNt======boardMasterVO.getTel()="+boardMasterVO.getTel());
		//비밀번호 정답 비교하는 루틴 이용하여 가입할 때 전화와 일치하는 지 확인해서 처리		
		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------
		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}	
		return "egovframework/notice/bBSMasterRegistNt";
    }

    /**
     * 신규 게시판 마스터 정보를 등록한다.
     * 
     * @param boardMasterVO
     * @param boardMaster
     * @param status
     * @return
     * @throws Exception
     */

    @RequestMapping("/cop/bbs/insertBBSMasterHp.do")
    //@ResponseBody
    public String insertBBSMasterHp(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    ComDefaultCodeVO vo = new ComDefaultCodeVO();		    
		    //게시판유형코드
		    vo.setCodeId("COM101");
		    List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
		    model.addAttribute("bbsTyCode", codeResult);
		    return "egovframework/help/bBSMasterRegistHp.";
		}
		
		if (isAuthenticated) {
		    boardMaster.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    if((boardMasterVO == null ? "" : EgovStringUtil.isNullToString(boardMasterVO.getBlogAt())).equals("Y")){
		    	boardMaster.setBlogAt("Y");
		    }else{
		    	boardMaster.setBlogAt("N");
		    }
		    egovBBSMasterService.insertBBSMasterInfHp(boardMaster);
		}
		if(boardMaster.getBlogAt().equals("Y")){
			return "forward:/cop/bbs/selectArticleBlogList.do";
		}else{
			return "forward:/cop/bbs/selecthelpRequestInfs.do";
		}
		
    }
    
    /**
     * 소시민 단체 정보를 수정한다.
     * 
     * @param boardMasterVO
     * @param boardMaster
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/updateBBSMaster1.do")
    public String updateBBSMaster1(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    BoardMasterVO vo = egovBBSMasterService.selectBBSMasterInf(boardMasterVO);
	
		    model.addAttribute("result", vo);
	
		    ComDefaultCodeVO comVo = new ComDefaultCodeVO();
	        comVo.setCodeId("COM101");
	        List<?> codeResult = cmmUseService.selectCmmCodeDetail(comVo);
	        model.addAttribute("bbsTyCode", codeResult);
		    return "egovframework/com/cop/bbs/EgovBBSMasterUpdt";
		}
	
		if (isAuthenticated) {
		    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    egovBBSMasterService.updateBBSMasterInf(boardMaster);
		}
		return "forward:/cop/bbs/selectBBSMasterInfs.do";
    }
    
    /**
     * 도움요청 정보를 수정한다.
     * 
     * @param boardMasterVO
     * @param boardMaster
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/updateBBSMasterHp.do")
    public String updateBBSMasterHp(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    BoardMasterVO vo = egovBBSMasterService.selectBBSMasterInf(boardMasterVO);
	
		    model.addAttribute("result", vo);
	
		    ComDefaultCodeVO comVo = new ComDefaultCodeVO();
	        comVo.setCodeId("COM101");
	        List<?> codeResult = cmmUseService.selectCmmCodeDetail(comVo);
	        model.addAttribute("bbsTyCode", codeResult);
		    return "egovframework/help/bBSMasterUpdt";
		}
	
		if (isAuthenticated) {
		    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    egovBBSMasterService.updateBBSMasterInfHp(boardMaster);
		}
		return "forward:/cop/bbs/selectBBSMasterInfsHp.do";
    }
    
    @RequestMapping("/cop/bbs/updateBBSMasterNt.do")
    public String updateBBSMasterNt(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    BoardMasterVO vo = egovBBSMasterService.selectBBSMasterInf(boardMasterVO);
	
		    model.addAttribute("result", vo);
	
		    ComDefaultCodeVO comVo = new ComDefaultCodeVO();
	        comVo.setCodeId("COM101");
	        List<?> codeResult = cmmUseService.selectCmmCodeDetail(comVo);
	        model.addAttribute("bbsTyCode", codeResult);
		    return "egovframework/help/bBSMasterUpdt";
		}
	
		if (isAuthenticated) {
		    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    egovBBSMasterService.updateBBSMasterInfNt(boardMaster);
		}
		return "forward:/cop/bbs/selectBBSMasterInfsNt.do";
    }
    
    @RequestMapping("/cop/bbs/updateResponseHp.do")
    public String updateResponseHp(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    BoardMasterVO vo = egovBBSMasterService.selectBBSMasterInf(boardMasterVO);
	
		    model.addAttribute("result", vo);
	
		    ComDefaultCodeVO comVo = new ComDefaultCodeVO();
	        comVo.setCodeId("COM101");
	        List<?> codeResult = cmmUseService.selectCmmCodeDetail(comVo);
	        model.addAttribute("bbsTyCode", codeResult);
		    return "egovframework/help/bBSMasterUpdt";
		}
	
		if (isAuthenticated) {
		    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    egovBBSMasterService.updateResponseHp(boardMaster);
		}
		return "forward:/cop/bbs/selectBbsMemberInfsHp.do";
    }
    
    @RequestMapping("/cop/bbs/updateResponseNt.do")
    public String updateResponseNt(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    BoardMasterVO vo = egovBBSMasterService.selectBBSMasterInf(boardMasterVO);
	
		    model.addAttribute("result", vo);
	
		    ComDefaultCodeVO comVo = new ComDefaultCodeVO();
	        comVo.setCodeId("COM101");
	        List<?> codeResult = cmmUseService.selectCmmCodeDetail(comVo);
	        model.addAttribute("bbsTyCode", codeResult);
		    return "egovframework/notice/bBSMasterUpdtNt";
		}
	
		if (isAuthenticated) {
		    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    egovBBSMasterService.updateResponseNt(boardMaster);
		}
		return "forward:/cop/bbs/selectBbsMemberInfsNt.do";
    }
    
    
    /**
     * 게시판 마스터 상세내용을 조회한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/selectBBSMasterDetail_1.do")
    public String selectBBSMasterDetail_1(@ModelAttribute("searchVO") BoardMasterVO searchVO, ModelMap model) throws Exception {
		BoardMasterVO vo = egovBBSMasterService.selectBBSMasterInf(searchVO);
		model.addAttribute("result", vo);
	
		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------
		
		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}
		
		return "egovframework/com/cop/bbs/EgovBBSMasterDetail";
    }
    
    /**
     * @param bbsId
     * @param searchVO
     * @param model
     * @throws Exception
     * 소시민단체 정보를 수정하기 위한 전 처리
     */
    @RequestMapping("/cop/bbs/updateBBSMasterView.do")
    public String updateBBSMasterView(@RequestParam("bbsId") String bbsId,
    		HttpServletRequest request, HttpSession session,
            @ModelAttribute("searchVO") BoardMaster searchVO, ModelMap model)
            throws Exception {
        BoardMasterVO boardMasterVO = new BoardMasterVO();       
        //게시판유형코드
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM101");
        List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("bbsTyCode", codeResult);        
        // Primary Key 값 세팅
        boardMasterVO.setBbsId(bbsId);
        model.addAttribute("boardMasterVO", egovBBSMasterService.selectBBSMasterInf(boardMasterVO));

		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------
		
		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}
		
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId_S = loginVO.getId();
		//System.err.println("=================mberId_S="+mberId_S);
		request.setAttribute("mberId_SS", mberId_S);
        
        return "egovframework/com/cop/bbs/EgovBBSMasterUpdt";
    }
    
    
    @RequestMapping("/cop/bbs/updateBBSMasterView3.do")
    public String updateBBSMasterView3(@RequestParam("bbsId") String bbsId , @RequestParam("mberId") String mberId,  
    		HttpServletRequest request, HttpSession session,
            @ModelAttribute("searchVO") BoardMaster searchVO, ModelMap model)
            throws Exception {
        BoardMasterVO boardMasterVO = new BoardMasterVO();       
        //게시판유형코드
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM101");
        List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("bbsTyCode", codeResult);        
        // Primary Key 값 세팅
        boardMasterVO.setBbsId(bbsId);
        boardMasterVO.setMberId(mberId);
        model.addAttribute("boardMasterVO", egovBBSMasterService.selectBBSMasterInf(boardMasterVO));

		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------
		
		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId_S = loginVO.getId();
		//System.err.println("=================mberId_S="+mberId_S);
		request.setAttribute("mberId_SS", mberId_S);
        
        return "egovframework/com/cop/bbs/EgovBBSMasterUpdt";
    }
    
    /**
     * @param bbsId
     * @param searchVO
     * @param model
     * @param MBER_NM 
     * @param mberNm 
     * @throws Exception
     * 시민단체 가입정보를 확인 또는 수정하기 위한 전 처리
     */
    @RequestMapping("/cop/bbs/updateBBSMasterView2.do")
    public String updateBBSMasterView2(@RequestParam("bbsId") String bbsId, @RequestParam("mberId") String mberId,  
			HttpServletRequest request, HttpSession session,
            @ModelAttribute("searchVO") BoardMaster searchVO, ModelMap model)
            throws Exception {
        BoardMasterVO boardMasterVO = new BoardMasterVO();       
        //게시판유형코드
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM101");
        List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("bbsTyCode", codeResult);               
        boardMasterVO.setBbsId(bbsId);
        boardMasterVO.setMberId(mberId);
        model.addAttribute("boardMasterVO", egovBBSMasterService.updateBBSMasterView(boardMasterVO));        
        //System.err.println("=========model.addAttribute(\"boardMasterVO\", egovBBSMasterService.updateBBSMasterView(boardMasterVO)).toString()"+model.addAttribute("boardMasterVO", egovBBSMasterService.updateBBSMasterView(boardMasterVO)).toString());

		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------		
		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId_S = loginVO.getId();
		//System.err.println("================updateBBSMasterView2=mberId_S="+mberId_S);
		request.setAttribute("mberId_S", mberId_S);
        return "egovframework/mna/bbsMmberUdt"; 
    }
    
    /**
     * 신규 게시판 마스터 등록을 위한 등록페이지로 이동한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/insertBBSMasterView.do")
    public String insertBBSMasterView(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, 
    		HttpSession session, HttpServletRequest request, HttpServletResponse response,
    		ModelMap model) throws Exception {
		BoardMasterVO boardMaster = new BoardMasterVO();
		//공통코드(게시판유형)
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM101");
		List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("bbsTyCode", codeResult);
		model.addAttribute("boardMasterVO", boardMaster);
	
		// 세션에 저장된 값 가져오기 
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");
		String mberId=(String) session.getAttribute("accessUser");
		mberId=mberId.substring(3);
		
		request.setAttribute("mberId", mberId);
		String mberNm = loginVO.getName();
		request.setAttribute("mberNm", mberNm);
		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------

		
		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}
		
		return "egovframework/com/cop/bbs/EgovBBSMasterRegist";
    }

    /**
     * 신규 게시판 마스터 정보를 등록한다.
     * 
     * @param boardMasterVO
     * @param boardMaster
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/insertBBSMaster.do")
    public String insertBBSMaster(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model) throws Exception {
    	
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    ComDefaultCodeVO vo = new ComDefaultCodeVO();
		    
		    //게시판유형코드
		    vo.setCodeId("COM101");
		    List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
		    model.addAttribute("bbsTyCode", codeResult);
	
		    return "egovframework/com/cop/bbs/EgovBBSMasterRegist";
		}
		
		if (isAuthenticated) {
		    boardMaster.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    if((boardMasterVO == null ? "" : EgovStringUtil.isNullToString(boardMasterVO.getBlogAt())).equals("Y")){
		    	boardMaster.setBlogAt("Y");
		    }else{
		    	boardMaster.setBlogAt("N");
		    }
		    egovBBSMasterService.insertBBSMasterInf(boardMaster);
		   // System.err.println("=======================user.getId()="+user.getId());//boardMaster.getTel();
		}
		if(boardMaster.getBlogAt().equals("Y")){
			return "forward:/cop/bbs/selectArticleBlogList.do";
		}else{
			return "forward:/cop/bbs/selectBBSMasterInfs.do";
		}		
    }
    @RequestMapping("/cop/bbs/insertBBSMasterNt.do")
    public String insertBBSMasterNt(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model) throws Exception {
    	
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    ComDefaultCodeVO vo = new ComDefaultCodeVO();
		    
		    //게시판유형코드
		    vo.setCodeId("COM101");
		    List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
		    model.addAttribute("bbsTyCode", codeResult);
	
		    return "egovframework/com/cop/bbs/EgovBBSMasterRegist";
		}
		
		if (isAuthenticated) {
		    boardMaster.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    if((boardMasterVO == null ? "" : EgovStringUtil.isNullToString(boardMasterVO.getBlogAt())).equals("Y")){
		    	boardMaster.setBlogAt("Y");
		    }else{
		    	boardMaster.setBlogAt("N");
		    }
		    egovBBSMasterService.insertBBSMasterInfNt(boardMaster);
		   // System.err.println("=======================user.getId()="+user.getId());//boardMaster.getTel();
		}
		if(boardMaster.getBlogAt().equals("Y")){
			return "forward:/cop/bbs/selectArticleBlogList.do";
		}else{
			//return "forward:/cop/bbs/selectBBSMasterInfs.do";// /cop/bbs/selectBBSMasterInfsNt.do
			return "forward:/cop/bbs/selectBBSMasterInfsNt.do";
		}		
    }
    
    /**
     * 게시판 마스터 정보를 수정한다.
     * 
     * @param boardMasterVO
     * @param boardMaster
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/updateBBSMaster.do")
    public String updateBBSMaster(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    BoardMasterVO vo = egovBBSMasterService.selectBBSMasterInf(boardMasterVO);
	
		    model.addAttribute("result", vo);
	
		    ComDefaultCodeVO comVo = new ComDefaultCodeVO();
	        comVo.setCodeId("COM101");
	        List<?> codeResult = cmmUseService.selectCmmCodeDetail(comVo);
	        model.addAttribute("bbsTyCode", codeResult);
		    return "egovframework/com/cop/bbs/EgovBBSMasterUpdt";
		}
	
		if (isAuthenticated) {
		    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    egovBBSMasterService.updateBBSMasterInf(boardMaster);
		}
		return "forward:/cop/bbs/selectBBSMasterInfs.do";
    }
    /**
     * 게시판 마스터 상세내용을 조회한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/selectBBSMasterDetail.do")
    public String selectBBSMasterDetail(@ModelAttribute("searchVO") BoardMasterVO searchVO, ModelMap model) throws Exception {
		BoardMasterVO vo = egovBBSMasterService.selectBBSMasterInf(searchVO);
		model.addAttribute("result", vo);
	
		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------
		
		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}
		
		return "egovframework/com/cop/bbs/EgovBBSMasterDetail";
    }
    
    /**
     * @param bbsId
     * @param searchVO
     * @param model
     * @throws Exception
     * 소시민단체 정보를 수정하기 위한 전 처리
     */
    @RequestMapping("/cop/bbs/updateBBSMasterViewHp.do")
    public String updateBBSMasterViewHp(@RequestParam("bbsId") String bbsId,
    		HttpServletRequest request, HttpSession session,
            //@ModelAttribute("searchVO") BoardMaster searchVO, 
            @ModelAttribute("searchVO") BoardMasterVO boardMasterVO, 
            ModelMap model)
            throws Exception {
        //BoardMasterVO boardMasterVO = new BoardMasterVO();       
        //게시판유형코드
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM101");
        List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("bbsTyCode", codeResult);     
        // Primary Key 값 세팅
        boardMasterVO.setBbsId(bbsId);
        model.addAttribute("boardMasterVO", egovBBSMasterService.selectBBSMasterInfHp(boardMasterVO));
        //System.err.println("========boardMasterVO.getMberId()="+boardMasterVO.getMberId());
        
		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------
		
		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}
		
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId_S = loginVO.getId();
		//System.err.println("=================mberId_S="+mberId_S);
		request.setAttribute("mberId_SS", mberId_S);
        return "egovframework/help/bBSMasterUpdtHp";
    }
    
    @RequestMapping("/cop/bbs/updateBBSMasterViewNt.do")
    public String updateBBSMasterViewNt(@RequestParam("bbsId") String bbsId,
    		HttpServletRequest request, HttpSession session,
            @ModelAttribute("searchVO") BoardMaster searchVO, ModelMap model)
            throws Exception {
        BoardMasterVO boardMasterVO = new BoardMasterVO();       
        //게시판유형코드
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM101");
        List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("bbsTyCode", codeResult);     
        // Primary Key 값 세팅
        boardMasterVO.setBbsId(bbsId);
        //System.err.println("=================");
        model.addAttribute("boardMasterVO", egovBBSMasterService.selectBBSMasterInfNt(boardMasterVO));
		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------
		
		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}
		
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId_S = loginVO.getId();
		//System.err.println("=================mberId_S="+mberId_S);
		request.setAttribute("mberId_SS", mberId_S);
        return "egovframework/notice/bBSMasterUpdtNt";
    } 
    
    
    @RequestMapping("/cop/bbs/updateBBSMasterView32.do")
    public String updateBBSMasterView32(@RequestParam("bbsId") String bbsId , @RequestParam("mberId") String mberId,  
    		HttpServletRequest request, HttpSession session,
            @ModelAttribute("searchVO") BoardMaster searchVO, ModelMap model)
            throws Exception {
        BoardMasterVO boardMasterVO = new BoardMasterVO();       
        //게시판유형코드
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM101");
        List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("bbsTyCode", codeResult);        
        // Primary Key 값 세팅
        boardMasterVO.setBbsId(bbsId);
        boardMasterVO.setMberId(mberId);
        model.addAttribute("boardMasterVO", egovBBSMasterService.selectBBSMasterInf(boardMasterVO));

		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------
		
		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId_S = loginVO.getId();
		//System.err.println("=================mberId_S="+mberId_S);
		request.setAttribute("mberId_SS", mberId_S);
        
        return "egovframework/com/cop/bbs/EgovBBSMasterUpdt";
    }
    
    /**
     * @param bbsId
     * @param searchVO
     * @param model
     * @param MBER_NM 
     * @param mberNm 
     * @throws Exception
     * 시민단체 가입정보를 확인 또는 수정하기 위한 전 처리
     */
    @RequestMapping("/cop/bbs/updateBBSMasterView2Hp.do")
    public String updateBBSMasterView2Hp(@RequestParam("bbsId") String bbsId, @RequestParam("mberId") String mberId,  
			HttpServletRequest request, HttpSession session,
            @ModelAttribute("searchVO") BoardMaster searchVO, ModelMap model)
            throws Exception {
        BoardMasterVO boardMasterVO = new BoardMasterVO();       
        //게시판유형코드
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM101");
        List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("bbsTyCode", codeResult);               
        boardMasterVO.setBbsId(bbsId);
        boardMasterVO.setMberId(mberId);
        model.addAttribute("boardMasterVO", egovBBSMasterService.updateBBSMasterView2Hp(boardMasterVO));        
        //System.err.println("========egovBBSMasterService.updateBBSMasterView2Hp(boardMasterVO))"
        //+model.addAttribute("boardMasterVO", egovBBSMasterService.updateBBSMasterView2Hp(boardMasterVO)).toString());

		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------		
		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId_S = loginVO.getId();
		 //System.err.println("============mberId="+mberId);
		//System.err.println("================updateBBSMasterView2=mberId_S="+mberId_S);
		request.setAttribute("mberId_S", mberId_S);
        return "egovframework/help/helpResponseUdt";
    }
    
    @RequestMapping("/cop/bbs/updateBBSMasterView2Nt.do")
    public String updateBBSMasterView2Nt(@RequestParam("bbsId") String bbsId, @RequestParam("mberId") String mberId,  
			HttpServletRequest request, HttpSession session,
            @ModelAttribute("searchVO") BoardMaster searchVO, ModelMap model)
            throws Exception {
        BoardMasterVO boardMasterVO = new BoardMasterVO();       
        //게시판유형코드
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM101");
        List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("bbsTyCode", codeResult);               
        boardMasterVO.setBbsId(bbsId);
        boardMasterVO.setMberId(mberId);
        model.addAttribute("boardMasterVO", egovBBSMasterService.updateBBSMasterView2Nt(boardMasterVO));        
        //System.err.println("========egovBBSMasterService.updateBBSMasterView2Hp(boardMasterVO))"
        //+model.addAttribute("boardMasterVO", egovBBSMasterService.updateBBSMasterView2Hp(boardMasterVO)).toString());

		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------		
		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId_S = loginVO.getId();
		// System.err.println("============mberId="+mberId);
		//System.err.println("================updateBBSMasterView2=mberId_S="+mberId_S);
		request.setAttribute("mberId_S", mberId_S);
        return "egovframework/notice/noticeResponseUdt";
    }

    @IncludedInfo(name="청원 동의 내용확인")
    @RequestMapping("/cop/bbs/updateMnaResponse.do")
    public String updateMnaResponse(@RequestParam("bbsId") String bbsId, @RequestParam("mberId") String mberId,  
			HttpServletRequest request, HttpSession session,
            @ModelAttribute("searchVO") BoardMaster searchVO, ModelMap model)
            throws Exception {
        BoardMasterVO boardMasterVO = new BoardMasterVO();       
        //게시판유형코드
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM101");
        List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("bbsTyCode", codeResult);               
        boardMasterVO.setBbsId(bbsId);
        boardMasterVO.setMberId(mberId);
        model.addAttribute("boardMasterVO", egovBBSMasterService.updateMnaResponse(boardMasterVO));        

		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId_S = loginVO.getId();
		// System.err.println("============mberId="+mberId);
		//System.err.println("================updateBBSMasterView2=mberId_S="+mberId_S);
		request.setAttribute("mberId_S", mberId_S);
        return "egovframework/mna/mnaResponseUdt";
    }
    /**
     * 도움 요청 목록을 조회한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="도움 요청",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selecthelpRequestInfs.do")
    public String selecthelpRequestInfs(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		Map<String, Object> map = egovBBSMasterService.selecthelpRequestListInfs(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		//System.err.println("map.get(\"resultList\")==selectBBSMasterInfs="+map.get("resultList").toString());
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/help/helpRequestList";
    }
    
    @IncludedInfo(name="알림,행사,질문",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selecthelpRequestInfsNt.do")
    public String selecthelpRequestInfsNt(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		Map<String, Object> map = egovBBSMasterService.selecthelpRequestListInfsNt(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		//System.err.println("map.get(\"resultList\")==selectBBSMasterInfs="+map.get("resultList").toString());
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/notice/noticeRequestList";
    }
    
    @IncludedInfo(name="청원",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectMnaRequest.do")
    public String selectMnaRequest(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		Map<String, Object> map = egovBBSMasterService.selectMnaRequest(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		//System.err.println("map.get(\"resultList\")==selectBBSMasterInfs="+map.get("resultList").toString());
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/mna/mnaRequestList";
    }
    
    /**
     * 소시민단체 목록을 조회한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="소시민단체",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectBBSMasterInfs.do")
    public String selectBBSMasterInfs(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		Map<String, Object> map = egovBBSMasterService.selectBBSMasterInfs(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		//System.err.println("map.get(\"resultList\")==selectBBSMasterInfs="+map.get("resultList"));
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/bbs/EgovBBSMasterList";
    }
    
    /**
     * 도움요청 응답 목록을 조회한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="도움요청 응답",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectBBSMasterInfsHp.do")
    public String selectBBSMasterInfsHp(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		Map<String, Object> map = egovBBSMasterService.selectBBSMasterInfsHp(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		//System.err.println("map.get(\"resultList\")==selectBBSMasterInfs="+map.get("resultList"));
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/help/helpRequestList";
    }
    @IncludedInfo(name="알림,행사,질문 응답",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectBBSMasterInfsNt.do")
    public String selectBBSMasterInfsNt(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		Map<String, Object> map = egovBBSMasterService.selectBBSMasterInfsNt(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		//System.err.println("map.get(\"resultList\")==selectBBSMasterInfs="+map.get("resultList"));
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/notice/noticeRequestList";
    }
    
    /**
     * 선거구 지킴이 등록을 위한 등록페이지로 이동한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/insertDistwachView.do")
    public String insertDistwachView(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO, Model model,
			@ModelAttribute("boardMaster") BoardMaster boardMaster,
			HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
		    @RequestParam("no") String no)
			throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			System.err.println("!isAuthenticated=============Statement 1"+!isAuthenticated);
	        //System.exit(0);
			return "index";
		}
		CmmnDetailCode vo = new CmmnDetailCode();
		vo.setNo(no);
		// 세션에 저장된 값 가져오기 
				session = request.getSession();
				LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");
				String mberId=(String) session.getAttribute("accessUser");
				mberId=mberId.substring(3);
				
				request.setAttribute("mberId", mberId);
				String mberNm = loginVO.getName();
				request.setAttribute("mberNm", mberNm);
				
				vo.setMberId(mberId);

		List<CmmnDetailCode> result= cmmUseService.selectDistwatchDetail(vo);
		model.addAttribute("bbsNm_result", result);
		return "egovframework/mna/distwachInsert";
	}
    
    @RequestMapping("/cop/bbs/insertGepyosoView.do")
    public String insertGepyosoView(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO, Model model,
			@ModelAttribute("boardMaster") BoardMaster boardMaster,
			HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
		    @RequestParam("no") String no)
			throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			System.err.println("!isAuthenticated=============Statement 1"+!isAuthenticated);
			return "index";
		}
		CmmnDetailCode vo = new CmmnDetailCode();
		vo.setNo(no);
		// 세션에 저장된 값 가져오기 
				session = request.getSession();
				LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");
				String mberId=(String) session.getAttribute("accessUser");
				mberId=mberId.substring(3);
				
				request.setAttribute("mberId", mberId);
				String mberNm = loginVO.getName();
				request.setAttribute("mberNm", mberNm);
				
				vo.setMberId(mberId);

		List<CmmnDetailCode> result= cmmUseService.selectGepyosoDetail(vo);
		model.addAttribute("bbsNm_result", result);
		return "egovframework/gepyoso/gepyosoInsert";
	}
    @RequestMapping("/cop/bbs/insertSajeonTuView.do")
    public String insertSajeonTuView(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO, Model model,
			@ModelAttribute("boardMaster") BoardMaster boardMaster,
			HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
		    @RequestParam("no") String no)
			throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			System.err.println("!isAuthenticated=============Statement 1"+!isAuthenticated);
			return "index";
		}
		CmmnDetailCode vo = new CmmnDetailCode();
		vo.setNo(no);
		// 세션에 저장된 값 가져오기 
				session = request.getSession();
				LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");
				String mberId=(String) session.getAttribute("accessUser");
				mberId=mberId.substring(3);
				
				request.setAttribute("mberId", mberId);
				String mberNm = loginVO.getName();
				request.setAttribute("mberNm", mberNm);
				
				vo.setMberId(mberId);
		List<CmmnDetailCode> result= cmmUseService.selectSajeonTuDetail(vo);
		model.addAttribute("bbsNm_result", result);
		return "egovframework/sajeonTu/sajeonTuInsert";
	}
    
    /**
     * 투표소 지킴이 등록을 위한 등록페이지로 이동한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/insertPolPlaceView.do")
    public String insertPolPlaceView(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO, Model model,
			@ModelAttribute("boardMaster") BoardMaster boardMaster,
			HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
		    @RequestParam("no") String no)
			throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			System.err.println("!isAuthenticated=============Statement 1"+!isAuthenticated);
			return "index";
		}
		CmmnDetailCode vo = new CmmnDetailCode();
		vo.setNo(no);
		
		// 세션에 저장된 값 가져오기 
				session = request.getSession();
				LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");
				String mberId=(String) session.getAttribute("accessUser");
				mberId=mberId.substring(3);
				
				request.setAttribute("mberId", mberId);
				String mberNm = loginVO.getName();
				request.setAttribute("mberNm", mberNm);
				
				vo.setMberId(mberId);
				//System.err.println("====/cop/bbs/insertPolPlaceView.do====vo.getMberId()==="+vo.getMberId());
		List<CmmnDetailCode> result= cmmUseService.selectPolPlaceDetail(vo);
		model.addAttribute("bbsNm_result", result);
		return "egovframework/mna/pollWatchInsert";
	}
    
    /**
     * 회원이 원하는 선거구에 지킴이로 등록한다.
     * 
     * @param bbsMemberVO
     * @param boardMaster
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/insertPollWatch.do")
    public String insertPolPlace(@ModelAttribute("searchVO") BbsMemberVO bbsMemberVO,
    		BindingResult bindingResult, 
    		@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO, Model model,
			@ModelAttribute("boardMaster") BoardMaster boardMaster, @RequestParam("no") String no,
		    @RequestParam("pllngplce_S") String pllngplce, 
		   @RequestParam("ampm") String ampm,
		    @RequestParam("mberId_S") String mberId, @RequestParam("mberNm_S") String mberNm, 
		    @RequestParam("bldngNm") String bldngNm,
		    @RequestParam("MBER_ID") String MBER_ID,
		    @RequestParam("MBER_NM") String MBER_NM,
		    @RequestParam("RECOMMENDER_ID") String RECOMMENDER_ID, 
		    @RequestParam("RECOMMENDER_NAME") String RECOMMENDER_NAME 
			) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		//System.err.println("@RequestMapping(\"/cop/bbs/insertPollWatch.do\") boardMaster.getMberId()====000========");
		//System.exit(0);
		boardMaster.setNo(no);
		boardMaster.setMberId(mberId);
		boardMaster.setMberNm(mberNm);
		boardMaster.setPllngplce(pllngplce);
		boardMaster.setAmpm(ampm);
		boardMaster.setMnaNm(bldngNm);
		boardMaster.setMBER_ID(mberId); 
		boardMaster.setMBER_NM(mberNm); 
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    ComDefaultCodeVO vo = new ComDefaultCodeVO();	    
		    //게시판유형코드
		    vo.setCodeId("COM101");
		    List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
		    model.addAttribute("bbsTyCode", codeResult);

			//System.err.println("@RequestMapping(\"/cop/bbs/insertPollWatch.do\") boardMaster.getMberNm()============"+boardMaster.getMberNm());
			System.exit(0);
		}
		
		if (isAuthenticated) {
		    boardMaster.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    if((bbsMemberVO == null ? "" : EgovStringUtil.isNullToString(bbsMemberVO.getBlogAt())).equals("Y")){
		    	boardMaster.setBlogAt("Y");
		    }else{
		    	boardMaster.setBlogAt("N");
		    }
			String no_mberid=boardMaster.getNo()+boardMaster.getMberId();
			boardMaster.setNo_mberid(no_mberid);
		    egovBBSMasterService.insertPollWatch(boardMaster);
		}
		if(boardMaster.getBlogAt().equals("Y")){
			return "forward:/cop/bbs/selectArticleBlogList.do";
		}else{
			return "forward:/cop/bbs/selectPollWatchInfs.do";
		}
    }
    
    /**
     * 회원이 원하는 선거구에 지킴이로 등록한다.
     * 
     * @param bbsMemberVO
     * @param boardMaster
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/insertDistwach.do")
    public String insertDistwach(@ModelAttribute("searchVO") BbsMemberVO bbsMemberVO,
    		BindingResult bindingResult, 
    		@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO, Model model,
			@ModelAttribute("boardMaster") BoardMaster boardMaster, @RequestParam("no") String no,
			@RequestParam("st") String st, @RequestParam("city") String city, 
		    @RequestParam("district_S") String district, 
		    @RequestParam("mberId_S") String mberId, @RequestParam("mberNm_S") String mberNm, 
		    @RequestParam("party") String party,  @RequestParam("mnaNm") String mnaNm,
		    @RequestParam("MBER_ID") String MBER_ID,
		    @RequestParam("MBER_NM") String MBER_NM,
		    @RequestParam("RECOMMENDER_ID") String RECOMMENDER_ID, 
		    @RequestParam("RECOMMENDER_NAME") String RECOMMENDER_NAME 
			) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		boardMaster.setNo(no);
		boardMaster.setMberId(mberId);
		boardMaster.setMberNm(mberNm);
		boardMaster.setDistrict(district);
		boardMaster.setSt(st);
		boardMaster.setCity(city);
		boardMaster.setParty(party);
		boardMaster.setMnaNm(mnaNm);
		boardMaster.setMBER_ID(mberId); 
		boardMaster.setMBER_NM(mberNm); 
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    ComDefaultCodeVO vo = new ComDefaultCodeVO();	    
		    //게시판유형코드
		    vo.setCodeId("COM101");
		    List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
		    model.addAttribute("bbsTyCode", codeResult);
			System.err.println("@RequestMapping(\"/cop/bbs/insertDistwach.do\") boardMaster.getMberNm()============"+boardMaster.getMberNm());
			System.exit(0);
		}
		
		if (isAuthenticated) {
		    boardMaster.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    if((bbsMemberVO == null ? "" : EgovStringUtil.isNullToString(bbsMemberVO.getBlogAt())).equals("Y")){
		    	boardMaster.setBlogAt("Y");
		    }else{
		    	boardMaster.setBlogAt("N");
		    }
			String no_mberid=boardMaster.getNo()+boardMaster.getMberId();
			boardMaster.setNo_mberid(no_mberid);
		    egovBBSMasterService.insertDistwach(boardMaster);
		}
		if(boardMaster.getBlogAt().equals("Y")){
			return "forward:/cop/bbs/selectArticleBlogList.do";
		}else{
			return "forward:/cop/bbs/selectDistwachInfs.do";
		}
    }
    
    @RequestMapping("/cop/bbs/insertGepyosoResponse.do")
    public String insertGepyosoResponse(@ModelAttribute("searchVO") BbsMemberVO bbsMemberVO,
    		BindingResult bindingResult, 
    		@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO, Model model,
			@ModelAttribute("boardMaster") BoardMaster boardMaster, @RequestParam("no") String no,
			@RequestParam("city") String city, 
		    @RequestParam("gepyoso") String gepyoso, 
		    @RequestParam("mberId_S") String mberId, @RequestParam("mberNm_S") String mberNm, 
		    @RequestParam("members") Integer members,
		    @RequestParam("MBER_ID") String MBER_ID,
		    @RequestParam("MBER_NM") String MBER_NM,
		    @RequestParam("RECOMMENDER_ID") String RECOMMENDER_ID, 
		    @RequestParam("RECOMMENDER_NAME") String RECOMMENDER_NAME 
			) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		boardMaster.setNo(no);
		boardMaster.setMberId(mberId);
		boardMaster.setMberNm(mberNm);
		boardMaster.setGepyoso(gepyoso);
		boardMaster.setCity(city);
		boardMaster.setMembers(members);
		boardMaster.setMBER_ID(mberId); 
		boardMaster.setMBER_NM(mberNm); 
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    ComDefaultCodeVO vo = new ComDefaultCodeVO();	    
		    //게시판유형코드
		    vo.setCodeId("COM101");
		    List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
		    model.addAttribute("bbsTyCode", codeResult);
			System.err.println("@RequestMapping(\"/cop/bbs/insertGepyosoResponse.do\") boardMaster.getMberNm()============"+boardMaster.getMberNm());
			System.exit(0);
		}
		
		if (isAuthenticated) {
		    boardMaster.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    if((bbsMemberVO == null ? "" : EgovStringUtil.isNullToString(bbsMemberVO.getBlogAt())).equals("Y")){
		    	boardMaster.setBlogAt("Y");
		    }else{
		    	boardMaster.setBlogAt("N");
		    }
			String no_mberid=boardMaster.getNo()+boardMaster.getMberId();
			boardMaster.setNo_mberid(no_mberid);
		    egovBBSMasterService.insertGepyosoResponse(boardMaster);
		}
		if(boardMaster.getBlogAt().equals("Y")){
			return "forward:/cop/bbs/selectArticleBlogList.do";
		}else{
			return "forward:/cop/bbs/selectGepyosoResponseInfs.do";
		}
    }
    
    @RequestMapping("/cop/bbs/insertSajeonTuResponse.do")
    public String insertSajeonTuResponse(@ModelAttribute("searchVO") BbsMemberVO bbsMemberVO,
    		BindingResult bindingResult, 
    		@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("mberManageVO") MberManageVO mberManageVO, Model model,
			@ModelAttribute("boardMaster") BoardMaster boardMaster, @RequestParam("no") String no,
			@RequestParam("city") String city, 
		    @RequestParam("tupyoso_S") String tupyoso, 
		    @RequestParam("mberId_S") String mberId, @RequestParam("mberNm_S") String mberNm, 
		    @RequestParam("members") Integer members,
		    @RequestParam("day") String day,
		    @RequestParam("ampm") String ampm,
		    @RequestParam("MBER_ID") String MBER_ID,
		    @RequestParam("MBER_NM") String MBER_NM,
		    @RequestParam("RECOMMENDER_ID") String RECOMMENDER_ID, 
		    @RequestParam("RECOMMENDER_NAME") String RECOMMENDER_NAME 
			) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		boardMaster.setNo(no);
		boardMaster.setMberId(mberId);
		boardMaster.setMberNm(mberNm);
		boardMaster.setGepyoso(tupyoso);
		boardMaster.setCity(city);
		boardMaster.setMembers(members);
		boardMaster.setDay(day);
		boardMaster.setAmpm(ampm);
		boardMaster.setMBER_ID(mberId); 
		boardMaster.setMBER_NM(mberNm); 
		boardMaster.setRECOMMENDER_ID(RECOMMENDER_ID);
		boardMaster.setRECOMMENDER_NAME(RECOMMENDER_NAME);
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {/*
		    ComDefaultCodeVO vo = new ComDefaultCodeVO();	    
		    //게시판유형코드
		    vo.setCodeId("COM101");
		    List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
		    model.addAttribute("bbsTyCode", codeResult);*/
			System.err.println("@RequestMapping(\"/cop/bbs/insertSajeonTuResponse.do\") boardMaster.getMberNm()============"+boardMaster.getMberNm());
			System.exit(0);
		}
		
		if (isAuthenticated) {
		    boardMaster.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    /*if((bbsMemberVO == null ? "" : EgovStringUtil.isNullToString(bbsMemberVO.getBlogAt())).equals("Y")){
		    	boardMaster.setBlogAt("Y");
		    }else{
		    	boardMaster.setBlogAt("N");
		    }*/
			String no_mberid=boardMaster.getNo()+boardMaster.getMberId();
			boardMaster.setNo_mberid(no_mberid);
		    egovBBSMasterService.insertSajeonTuResponse(boardMaster);
		    return "forward:/cop/bbs/selectSajeonTuResponseInfs.do";
		}
		if(boardMaster.getBlogAt().equals("Y")){
			return "forward:/cop/bbs/selectArticleBlogList.do";
		}else{
			return "forward:/cop/bbs/selectSajeonTuResponseInfs.do";
		}
    }
    
    /**
     * 소시민단체 가입  정보를 등록한다.
     * 
     * @param bbsMemberVO
     * @param boardMaster
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/insertBbsMember.do")
    public String insertBbsMember(@ModelAttribute("searchVO") BbsMemberVO bbsMemberVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model, @RequestParam("bbsId") String bbsId, @RequestParam("bbsNm_S") String bbsNm,   
	    @RequestParam("mberId_S") String mberId, @RequestParam("mberNm_S") String mberNm) throws Exception {
    	
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		boardMaster.setBbsId(bbsId);	
		boardMaster.setBbsNm(bbsNm);
		boardMaster.setMberId(mberId);
		boardMaster.setMberNm(mberNm);
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    ComDefaultCodeVO vo = new ComDefaultCodeVO();
		    //게시판유형코드
		    vo.setCodeId("COM101");
		    List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
			System.err.println("request.getParameter(bbsNm)============"+boardMaster.getMberNm());
			System.exit(0);
		    return "egovframework/com/cop/bbs/EgovBbsMemberRegist";
		    //return "egovframework/com/uss/umt/EgovMberInsert.jsp";/////임시로 테스트 동안 20210901 홍오성
		}
		if (isAuthenticated) {
		    boardMaster.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    if((bbsMemberVO == null ? "" : EgovStringUtil.isNullToString(bbsMemberVO.getBlogAt())).equals("Y")){
		    	boardMaster.setBlogAt("Y");
		    }else{
		    	boardMaster.setBlogAt("N");
		    }		   
			String mbId_bsNm=boardMaster.getMberId()+boardMaster.getBbsNm();
			//System.err.println("===========================mbId_bsNm="+mbId_bsNm);
			boardMaster.setMbId_bsNm(mbId_bsNm);
		    egovBBSMasterService.insertBbsMemberInf(boardMaster);
		}
		if(boardMaster.getBlogAt().equals("Y")){
			return "forward:/cop/bbs/selectArticleBlogList.do";
		}else{
			return "forward:/cop/bbs/selectBbsMemberInfs.do";
		}
    }
    
    /**
     * 도움요청 응답  정보를 등록한다.
     * 
     * @param bbsMemberVO
     * @param boardMaster
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/insertBbsMemberHp.do")
    public String insertBbsMemberHp(@ModelAttribute("searchVO") BbsMemberVO bbsMemberVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model, @RequestParam("bbsId") String bbsId, @RequestParam("bbsNm_S") String bbsNm,   
	    @RequestParam("mberId_S") String mberId, @RequestParam("mberNm_S") String mberNm) throws Exception {
    	
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		boardMaster.setBbsId(bbsId);	
		boardMaster.setBbsNm(bbsNm);
		boardMaster.setMberId(mberId);
		boardMaster.setMberNm(mberNm);
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    ComDefaultCodeVO vo = new ComDefaultCodeVO();
		    //게시판유형코드
		    vo.setCodeId("COM101");
		    List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
			System.err.println("request.getParameter(bbsNm)============"+boardMaster.getMberNm());
			System.exit(0);
		    return "egovframework/help/bBSMasterRegistHp";
		}
		if (isAuthenticated) {
		    boardMaster.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    if((bbsMemberVO == null ? "" : EgovStringUtil.isNullToString(bbsMemberVO.getBlogAt())).equals("Y")){
		    	boardMaster.setBlogAt("Y");
		    }else{
		    	boardMaster.setBlogAt("N");
		    }		   
			String mbId_bsNm=boardMaster.getMberId()+boardMaster.getBbsNm();
			boardMaster.setMbId_bsNm(mbId_bsNm);
		    egovBBSMasterService.insertBbsMemberInfHp(boardMaster);
		}
		if(boardMaster.getBlogAt().equals("Y")){
			return "forward:/cop/bbs/selectArticleBlogList.do";
		}else{
			return "forward:/cop/bbs/selectBbsMemberInfsHp.do";
		}
    }
    
    @RequestMapping("/cop/bbs/insertBbsMemberNt.do")
    public String insertBbsMemberNt(@ModelAttribute("searchVO") BbsMemberVO bbsMemberVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model, @RequestParam("bbsId") String bbsId, @RequestParam("bbsNm_S") String bbsNm,   
	    @RequestParam("mberId_S") String mberId, @RequestParam("mberNm_S") String mberNm) throws Exception {
    	
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		boardMaster.setBbsId(bbsId);	
		boardMaster.setBbsNm(bbsNm);
		boardMaster.setMberId(mberId);
		boardMaster.setMberNm(mberNm);
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    ComDefaultCodeVO vo = new ComDefaultCodeVO();
		    //게시판유형코드
		    vo.setCodeId("COM101");
		    List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
			System.err.println("request.getParameter(bbsNm)============"+boardMaster.getMberNm());
			System.exit(0);
		    return "egovframework/notice/bBSMasterRegistNt";
		}
		if (isAuthenticated) {
		    boardMaster.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    if((bbsMemberVO == null ? "" : EgovStringUtil.isNullToString(bbsMemberVO.getBlogAt())).equals("Y")){
		    	boardMaster.setBlogAt("Y");
		    }else{
		    	boardMaster.setBlogAt("N");
		    }		   
			String mbId_bsNm=boardMaster.getMberId()+boardMaster.getBbsNm();
			//System.err.println("===========================mbId_bsNm="+mbId_bsNm);
			boardMaster.setMbId_bsNm(mbId_bsNm);
		    egovBBSMasterService.insertBbsMemberInfNt(boardMaster);
		}
		if(boardMaster.getBlogAt().equals("Y")){
			return "forward:/cop/bbs/selectArticleBlogList.do";
		}else{
			return "forward:/cop/bbs/selectBbsMemberInfsNt.do";
		}
    }
    
    /**
     * 소시민단체 가입 목록을 조회한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="소시민단체 가입 목록",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectBbsMemberInfs.do")
    public String selectBbsMemberInfs(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovBBSMasterService.selectBbsMemberInfs(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/bbs/EgovBbsMemberList";
    }
    
    /**
     * 도움요청 응답 목록을 조회한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="도움요청 응답 목록",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectBbsMemberInfsHp.do")
    public String selectBbsMemberInfsHp(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovBBSMasterService.selectBbsMemberInfsHp(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/help/helpResponseList";
    }
    
    @IncludedInfo(name="알림,행사,질문 응답 목록",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectBbsMemberInfsNt.do")
    public String selectBbsMemberInfsNt(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovBBSMasterService.selectBbsMemberInfsNt(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/notice/noticeResponseList";
    }
    
    @IncludedInfo(name="청원 동의 목록",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectMnaResponse.do")
    public String selectMnaResponse(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovBBSMasterService.selectMnaResponse(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/mna/mnaResponseList";
    }
    
    /**
     * 선거구 지킴이 등록 목록을 조회한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="국회의원 활동 지킴이 목록",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectDistwachInfs.do")
    public String selectMnaInfs(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovBBSMasterService.selectDistwachListInfs(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/mna/distwachList";
    }
    
    @IncludedInfo(name="개표 참관인 목록",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectGepyosoResponseInfs.do")
    public String selectGepyosoResponseInfs(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovBBSMasterService.selectGepyosoResponseInfs(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/gepyoso/gepyosoResponseList";
    }
    
    @IncludedInfo(name="사전투표 참관인 목록",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectSajeonTuResponseInfs.do")
    public String selectSajeonTuResponseInfs(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		Map<String, Object> map = egovBBSMasterService.selectSajeonTuResponseInfs(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);
		return "egovframework/sajeonTu/sajeonResponseList";
    }
    
    /**
     * 선거구 지킴이 등록 목록을 조회한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="선거구 지킴이 목록",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectPollWatchInfs.do")
    public String selectPollWatchInfs(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovBBSMasterService.selectPollWatchListInfs(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
	//System.err.println("map.get(\"resultList\")==selectBbsMemberInfs="+map.get("resultList"));
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/mna/pollWatchList";
    }

   
    /**
     * 선거구 목록을 조회한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="선거구",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectMnaListInfs.do")
    public String selectMnaListInfs(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovBBSMasterService.selectMnaListInfs(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		//System.err.println("map.get(\"resultList\")==selectBBSMasterInfs="+map.get("resultList"));
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/mna/mNaList";
    }
    
    @IncludedInfo(name="개표소",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectGepyoso.do")
    public String selectGepyoso(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovBBSMasterService.selectGepyoso(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		//System.err.println("map.get(\"resultList\")==selectBBSMasterInfs="+map.get("resultList"));
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		
		return "egovframework/gepyoso/gepyosoList";
    }
    
    @IncludedInfo(name="사전투표",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectSajeonSeon.do")
    public String selectSajeonSeon(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovBBSMasterService.selectSajeonSeon(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);
		return "egovframework/sajeonTu/sajeonTuList";
    }
    
    /**
     * 투표소 목록을 조회한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="투표소",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectPolPlaceListInfs.do")
    public String selectPollPlaceListInfs(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovBBSMasterService.selectPolPlaceListInfs(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/mna/polPlaceList";
    }
    
    /**
     * 투표소 목록을 조회한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="투표소 지킴이",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/selectPollWatchListInfs.do")
    public String selectPollWatchListInfs(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = egovBBSMasterService.selectPollWatchListInfs(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/mna/pollWatchList";
    }
    /**
     * 블로그에 대한 목록을 조회한다.
     * 
     * @param blogVO
     * @param model
     * @return
     * @throws Exception
     *
    @IncludedInfo(name="블로그관리", order = 170 ,gid = 40)
    @RequestMapping("/cop/bbs/selectBlogList.do")
    public String selectBlogMasterList(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	 //KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
    	
		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardMasterVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		boardMasterVO.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		
		Map<String, Object> map = egovBBSMasterService.selectBlogMasterInfs(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/cop/bbs/EgovBlogList";
    }
    */
    /**
     * 블로그 등록을 위한 등록페이지로 이동한다.
     * 
     * @param blogVO
     * @param model
     * @return
     * @throws Exception
     *
    @RequestMapping("/cop/bbs/insertBlogMasterView.do")
    public String insertBlogMasterView(@ModelAttribute("searchVO") BlogVO blogVO, ModelMap model) throws Exception {
    	model.addAttribute("blogMasterVO", new BlogVO());
	return "egovframework/com/cop/bbs/EgovBlogRegist";
    }
    */
    /**
     * 블로그 생성 유무를 판단한다.
     * 
     * @param blogVO
     * @param model
     * @return
     * @throws Exception
     *
    @RequestMapping("/cop/bbs/selectChkBloguser.do")
    public ModelAndView chkBlogUser(@ModelAttribute("searchVO") BlogVO blogVO, ModelMap model) throws Exception {
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
   	 	// KISA 보안취약점 조치 (2018-12-10, 신용호)
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if(!isAuthenticated) {
        	throw new IllegalAccessException("Login Required!");
        }
    	
    	model.addAttribute("blogMasterVO", new BlogVO());
    	
    	String userVal="";
    	blogVO.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
    	userVal = egovBBSMasterService.checkBlogUser(blogVO);
    	
    	ModelAndView mav = new ModelAndView("jsonView");
    	mav.addObject("userChk", userVal);
    	return mav;
    }
*/
    /**
     * 블로그 정보를 등록한다.
     * 
     * @param blogVO
     * @param blog
     * @param status
     * @param model
     * @return
     * @throws Exception
     *
    @RequestMapping("/cop/bbs/insertBlogMaster.do")
    public String insertBlogMaster(@ModelAttribute("searchVO") BlogVO blogVO, @ModelAttribute("blogMaster") Blog blog,
	    BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
        if(!isAuthenticated) { //KISA 보안약점 조치 (2018-12-10, 신용호)
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
		
		blogVO.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		BlogVO vo = egovBBSMasterService.checkBlogUser2(blogVO);
		
		if(vo != null) {
			model.addAttribute("blogMasterVO", new BlogVO());
			model.addAttribute("message", egovMessageSource.getMessage("comCopBlog.validate.blogUserCheck"));
			return "egovframework/com/cop/bbs/EgovBlogRegist";
		}
		
		beanValidator.validate(blog, bindingResult);
		
		if (bindingResult.hasErrors()) {
		    return "egovframework/com/cop/bbs/EgovBlogRegist";
		}
		
		String blogId = idgenServiceBlog.getNextStringId(); //블로그 아이디 채번
		String bbsId = idgenServiceBbs.getNextStringId(); //게시판 아이디 채번
		
		blog.setRegistSeCode("REGC02");
		blog.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		blog.setBbsId(bbsId);
		blog.setBlogId(blogId);
		blog.setBlogAt("Y");
		egovBBSMasterService.insertBlogMaster(blog);
		
		if (isAuthenticated) {
		    //블로그 개설자의 정보를 등록한다.
		    BlogUserVO blogUserVO = new BlogUserVO();
		    blogUserVO.setBlogId(blogId);
		    blogUserVO.setEmplyrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    blogUserVO.setMngrAt("Y");
		    blogUserVO.setMberSttus("P");
		    blogUserVO.setUseAt("Y");
		    blogUserVO.setFrstRegisterId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    
		    egovBBSMasterService.insertBoardBlogUserRqst(blogUserVO);
		}
		return "forward:/cop/bbs/selectBlogList.do";
    }
*/
    
    /**
     * 선거구 세부정보 보기
     * @param bbsId
     * @param searchVO
     * @param model
     * @throws Exception
     */
    
    @RequestMapping("/cop/bbs/districtView.do")
    public String districtView(@RequestParam("no") String no , @RequestParam("mberId") String mberId,
            @ModelAttribute("searchVO") BoardMaster searchVO, HttpSession session, HttpServletRequest request,  
            ModelMap model)
            throws Exception {
    	BoardMasterVO boardMasterVO = new BoardMasterVO();        
        //게시판유형코드
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM101");
        List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("bbsTyCode", codeResult);        
        // Primary Key 값 세팅
        boardMasterVO.setNo(no);
        boardMasterVO.setMberId(mberId);
        session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");
		
		String mberId_L = loginVO.getId();
		request.setAttribute("mberId_L", mberId_L);
        model.addAttribute("boardMasterVO", egovBBSMasterService.selectMnaInf(boardMasterVO));
        return "egovframework/mna/distwatchView";
    }
    
    /**
     * 선거구 정보를 수정하기 위한 전 처리
     * @param bbsId
     * @param searchVO
     * @param model
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/updateMnaView.do")
    public String updateMnaView(@RequestParam("no") String no ,
            @ModelAttribute("searchVO") BoardMaster searchVO, ModelMap model)
            throws Exception {
        BoardMasterVO boardMasterVO = new BoardMasterVO();        
        //게시판유형코드
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM101");
        List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("bbsTyCode", codeResult);        
        // Primary Key 값 세팅
        boardMasterVO.setNo(no);
        model.addAttribute("boardMasterVO", egovBBSMasterService.selectMnaInf(boardMasterVO));
		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------		
		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}
        return "egovframework/mna/mNaUpdt";
    }
    
    @RequestMapping("/cop/bbs/updateGepyosoView.do")
    public String updateGepyosoView(
    		@RequestParam("no") String no,
            @ModelAttribute("searchVO") BoardMaster searchVO,
            ModelMap model)
            throws Exception {
    	BoardMasterVO boardMasterVO = new BoardMasterVO(); 
        boardMasterVO.setNo(no);
        model.addAttribute("boardMasterVO", egovBBSMasterService.selectGepyosoInf(boardMasterVO));
        return "egovframework/gepyoso/gepyosoUpdt";
    }
    
    @RequestMapping("/cop/bbs/updateSajeonTuView.do")
    public String updateSajeonTuView(
    		@RequestParam("no") String no,
            @ModelAttribute("searchVO") BoardMaster searchVO,
            ModelMap model)
            throws Exception {
    	BoardMasterVO boardMasterVO = new BoardMasterVO(); 
        boardMasterVO.setNo(no);
        model.addAttribute("boardMasterVO", egovBBSMasterService.selectSajeonTuInf(boardMasterVO));
        return "egovframework/sajeonTu/sajeonTuUpdt";
    }
    
    /**
     * 투표소 지킴이 정보를 수정하기 위한 전 처리
     * @param no
     * @param searchVO
     * @param model
     * @throws Exception
     */
    
    @RequestMapping("/cop/bbs/updatePolPlaceWatchView.do")
    public String updatePolPlaceWatchView(@RequestParam("no") String no , @RequestParam("mberId") String mberId ,
            @ModelAttribute("searchVO") BoardMaster searchVO, HttpServletRequest request, HttpSession session, ModelMap model)
            throws Exception {
        BoardMasterVO boardMasterVO = new BoardMasterVO();        
        //게시판유형코드
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM101");
        List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("bbsTyCode", codeResult);        
        // Primary Key 값 세팅
        boardMasterVO.setNo(no);
        boardMasterVO.setMberId(mberId);
        model.addAttribute("boardMasterVO", egovBBSMasterService.updatePolPlaceWatchView(boardMasterVO));
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId_L = loginVO.getId();
		request.setAttribute("mberId_L", mberId_L);
		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------		
		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}
        return "egovframework/mna/polPlaceWatchUpdt";
    }
    
    @RequestMapping("/cop/bbs/updateGepyosoResponseView.do")
    public String updateGepyosoResponseView(@RequestParam("no") String no , @RequestParam("mberId") String mberId ,
            @ModelAttribute("searchVO") BoardMaster searchVO, HttpServletRequest request, HttpSession session, ModelMap model)
            throws Exception {
        BoardMasterVO boardMasterVO = new BoardMasterVO();        
        //게시판유형코드
        /*
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM101");
        List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("bbsTyCode", codeResult); */       
        // Primary Key 값 세팅
        boardMasterVO.setNo(no);
        boardMasterVO.setMberId(mberId);
        model.addAttribute("boardMasterVO", egovBBSMasterService.updateGepyosoResponseView(boardMasterVO));
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId_L = loginVO.getId();
		request.setAttribute("mberId_L", mberId_L);
		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------		
		/*if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}*/
        return "egovframework/gepyoso/gepyosoResponseUpdt";
    }
    
    @RequestMapping("/cop/bbs/updateSajeonResponseView.do")
    public String updateSajeonTuResponseView(@RequestParam("no") String no , @RequestParam("mberId") String mberId ,
            @ModelAttribute("searchVO") BoardMaster searchVO, HttpServletRequest request, HttpSession session, ModelMap model)
            throws Exception {
        BoardMasterVO boardMasterVO = new BoardMasterVO();        
        //게시판유형코드
        /*
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM101");
        List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("bbsTyCode", codeResult); */       
        // Primary Key 값 세팅
        boardMasterVO.setNo(no);
        boardMasterVO.setMberId(mberId);
        model.addAttribute("boardMasterVO", egovBBSMasterService.updateSajeonResponseView(boardMasterVO));
		session = request.getSession();
		LoginVO loginVO =(LoginVO) session.getAttribute("loginVO");

		String mberId_L = loginVO.getId();
		request.setAttribute("mberId_L", mberId_L);
		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------		
		/*if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}*/
        return "egovframework/sajeonTu/sajeonResponseUpdt";
    }
    /**
     * 투표소 정보를 수정하기 위한 전 처리
     * @param no
     * @param searchVO
     * @param model
     * @throws Exception
     */
    
    @RequestMapping("/cop/bbs/updatePolPlaceView.do")
    public String updatePolPlaceView(@RequestParam("no") String no , 
            @ModelAttribute("searchVO") BoardMaster searchVO, ModelMap model)
            throws Exception {
        BoardMasterVO boardMasterVO = new BoardMasterVO();        
        //게시판유형코드
        ComDefaultCodeVO vo = new ComDefaultCodeVO();
        vo.setCodeId("COM101");
        List<?> codeResult = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("bbsTyCode", codeResult);        
        // Primary Key 값 세팅
        boardMasterVO.setNo(no);

        model.addAttribute("boardMasterVO", egovBBSMasterService.selectPolPlaceInf(boardMasterVO));

		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------		
		if(EgovComponentChecker.hasComponent("EgovArticleCommentService")){
			model.addAttribute("useComment", "true");
		}
		if(EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		}
        return "egovframework/mna/polPlaceUpdt";
    }
    
    @RequestMapping("/cop/bbs/updatePolPlace.do")
    public String updatePolPlace(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    BoardMasterVO vo = egovBBSMasterService.selectBBSMasterInf(boardMasterVO);
	
		    model.addAttribute("result", vo);
	
		    ComDefaultCodeVO comVo = new ComDefaultCodeVO();
	        comVo.setCodeId("COM101");
	        List<?> codeResult = cmmUseService.selectCmmCodeDetail(comVo);
	        model.addAttribute("bbsTyCode", codeResult);
		    return "egovframework/mna/polPlaceUpdt";
		}
	
		if (isAuthenticated) {
		    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    egovBBSMasterService.updatePolPlaceInf(boardMaster);
		}
		return "forward:/cop/bbs/selectPolPlaceListInfs.do";
    }
 
    @RequestMapping("/cop/bbs/updateMna.do")
    public String updateMna(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    BoardMasterVO vo = egovBBSMasterService.selectBBSMasterInf(boardMasterVO);
	
		    model.addAttribute("result", vo);
	
		    ComDefaultCodeVO comVo = new ComDefaultCodeVO();
	        comVo.setCodeId("COM101");
	        List<?> codeResult = cmmUseService.selectCmmCodeDetail(comVo);
	        model.addAttribute("bbsTyCode", codeResult);
		    return "egovframework/mna/mNaUpdt";
		}
	
		if (isAuthenticated) {
		    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    egovBBSMasterService.updateMnaInf(boardMaster);
		}
		return "forward:/cop/bbs/selectMnaListInfs.do";
    }

    /**
     * 투표소 지킴이를 삭제한다.
     * 
     * @param boardMasterVO
     * @param boardMaster
     * @param bbsNm 
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/deletePolWatchDog.do")
    public String deletePolWatchDog(@ModelAttribute("boardMaster") BoardMaster boardMaster, 
    		@RequestParam("no") String no , @RequestParam("mberId") String mberId 
	    ) throws Exception {
	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

	if (isAuthenticated) {
	    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	    boardMaster.setNo(no);
	    boardMaster.setMberId(mberId);
	    egovBBSMasterService.deletePolWatchDog(boardMaster);
	}
	return "forward:/cop/bbs/selectPollWatchListInfs.do";
    }
    
    @RequestMapping("/cop/bbs/deleteGepyosoResponse.do")
    public String deleteGepyosoResponse(@ModelAttribute("boardMaster") BoardMaster boardMaster, 
    		@RequestParam("no") String no , @RequestParam("mberId") String mberId 
	    ) throws Exception {
	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

	if (isAuthenticated) {
	    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	    boardMaster.setNo(no);
	    boardMaster.setMberId(mberId);
	    egovBBSMasterService.deleteGepyosoResponse(boardMaster);
	}
	return "forward:/cop/bbs/selectGepyosoResponseInfs.do";
    }
    
    @RequestMapping("/cop/bbs/deleteSajeonResponse.do")
    public String deleteSajeonResponse(@ModelAttribute("boardMaster") BoardMaster boardMaster, 
    		@RequestParam("no") String no , @RequestParam("mberId") String mberId 
	    ) throws Exception {
	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

	if (isAuthenticated) {
	    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	    boardMaster.setNo(no);
	    boardMaster.setMberId(mberId);
	    egovBBSMasterService.deleteSajeonResponse(boardMaster);
	}
	return "forward:/cop/bbs/selectSajeonTuResponseInfs.do";
    }
    
    /**
     * 선거구(국회의원) 지킴이를 삭제한다.
     * 
     * @param boardMasterVO
     * @param boardMaster
     * @param bbsNm 
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/deleteMnaWatchDog.do")
    public String deleteMnaWatchDog(@ModelAttribute("boardMaster") BoardMaster boardMaster, 
    		@RequestParam("no") String no , @RequestParam("mberId") String mberId 
	    ) throws Exception {
	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

	if (isAuthenticated) {
	    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	    boardMaster.setNo(no);
	    boardMaster.setMberId(mberId);
	    egovBBSMasterService.deleteMnaWatchDog(boardMaster);
	}
	return "forward:/cop/bbs/selectDistwachInfs.do";
    }

    /**
     * 소시민단체를 삭제한다.
     * 
     * @param boardMasterVO
     * @param boardMaster
     * @param bbsNm 
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/deleteBBSMaster.do")
    public String deleteBBSMaster(@ModelAttribute("boardMaster") BoardMaster boardMaster, 
    @RequestParam("bbsId") String bbsId 
	    ) throws Exception {
	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	if (isAuthenticated) {
	    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	    boardMaster.setBbsId(bbsId);
	    egovBBSMasterService.deleteBBSMasterInf(boardMaster);
	}
	return "forward:/cop/bbs/selectBBSMasterInfs.do";
    }
    
    /**
     * 도움 요청을 삭제한다.
     * 
     * @param boardMasterVO
     * @param boardMaster
     * @param bbsNm 
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/deleteBBSMasterHp.do")
    public String deleteBBSMasterHp(@ModelAttribute("boardMaster") BoardMaster boardMaster, 
    @RequestParam("bbsId") String bbsId 
	    ) throws Exception {
	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	if (isAuthenticated) {
	    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	    boardMaster.setBbsId(bbsId);
	    egovBBSMasterService.deleteBBSMasterInfHp(boardMaster);
	}
	return "forward:/cop/bbs/selecthelpRequestInfs.do";
    }
    
    @RequestMapping("/cop/bbs/deleteBBSMasterNt.do")
    public String deleteBBSMasterNt(@ModelAttribute("boardMaster") BoardMaster boardMaster, 
    @RequestParam("bbsId") String bbsId 
	    ) throws Exception {
	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	if (isAuthenticated) {
	    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	    boardMaster.setBbsId(bbsId);
	    egovBBSMasterService.deleteBBSMasterInfNt(boardMaster);
	}
	return "forward:/cop/bbs/selecthelpRequestInfsNt.do";
    }
    
    /**
     * 소시민단체 가입자를 삭제한다.
     * 
     * @param boardMasterVO
     * @param boardMaster
     * @param bbsNm 
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/deleteBBSMber.do")
    public String deleteBBSMber(@ModelAttribute("boardMaster") BoardMaster boardMaster, 
    @RequestParam("bbsId") String bbsId 
	    ) throws Exception {
	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	if (isAuthenticated) {
	    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	    boardMaster.setBbsId(bbsId);
	    egovBBSMasterService.deleteBBSMberInf(boardMaster);
	}
	return "forward:/cop/bbs/selectBbsMemberInfs.do";
    }
    
    /**
     * 소시민단체 가입자를 삭제한다.
     * 
     * @param boardMasterVO
     * @param boardMaster
     * @param bbsNm 
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/deleteBBSMberHp.do")
    public String deleteBBSMberHp(@ModelAttribute("boardMaster") BoardMaster boardMaster, 
    @RequestParam("bbsId") String bbsId 
	    ) throws Exception {
	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	if (isAuthenticated) {
	    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	    boardMaster.setBbsId(bbsId);
	    egovBBSMasterService.deleteBBSMberInfHp(boardMaster);
	}
	return "forward:/cop/bbs/selectBbsMemberInfsHp.do";//  /cop/bbs/selectBbsMemberInfs.do
    }
    
    @RequestMapping("/cop/bbs/deleteBBSMberNt.do")
    public String deleteBBSMberNt(@ModelAttribute("boardMaster") BoardMaster boardMaster, 
    @RequestParam("bbsId") String bbsId 
	    ) throws Exception {
	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	if (isAuthenticated) {
	    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
	    boardMaster.setBbsId(bbsId);
	    egovBBSMasterService.deleteBBSMberInfNt(boardMaster);
	}
	return "forward:/cop/bbs/selectBbsMemberInfsNt.do";
    }
    
    @IncludedInfo(name="청원 동의 삭제")
    @RequestMapping("/cop/bbs/deleteMnaResponse.do")
    public String deleteMnaResponse(@ModelAttribute("boardMaster") BoardMaster boardMaster, 
    @RequestParam("bbsId") String bbsId 
	    ) throws Exception {
	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (isAuthenticated) {
		    boardMaster.setLastUpdusrId(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
		    boardMaster.setBbsId(bbsId);
		    egovBBSMasterService.deleteMnaResponse(boardMaster);
		}
	//return "forward:/cop/bbs/selectBbsMemberInfsNt.do";
	return "egovframework/mna/mnaResponseList";
    }
    /**
     * 포트릿을 위한 블로그 목록 정보를 조회한다.
     * 
     * @param blogVO
     * @param model
     * @return
     * @throws Exception
     *
    @RequestMapping("/cop/bbs/selectBlogListPortlet.do")
    public String selectBlogListPortlet(@ModelAttribute("searchVO") BlogVO blogVO, ModelMap model) throws Exception {
	List<BlogVO> result = egovBBSMasterService.selectBlogListPortlet(blogVO);
	
	model.addAttribute("resultList", result);

	return "egovframework/com/cop/bbs/EgovBlogListPortlet";
    }
    */
    /**
     * 포트릿을 위한 게시판 목록 정보를 조회한다.
     * 
     * @param blogVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/selectBBSListPortlet.do")
    public String selectBBSListPortlet(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
    	List<BoardMasterVO> result = egovBBSMasterService.selectBBSListPortlet(boardMasterVO);
    	
    	model.addAttribute("resultList", result);
    	
    	return "egovframework/com/cop/bbs/EgovBBSListPortlet";
    }

    
}
