package egovframework.com.cop.bbs.service;

import java.util.List;
import java.util.Map;
/*
import egovframework.com.cop.bbs.service.BlogUser;
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.cop.bbs.service.Blog;
*/
import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface EgovBBSMasterService {

	Map<String, Object> selectNotUsedBdMstrList(BoardMasterVO boardMasterVO);

	void deleteBBSMasterInf(BoardMaster boardMaster);

	void deleteBBSMasterInfHp(BoardMaster boardMaster);

	void deleteBBSMasterInfNt(BoardMaster boardMaster);

	void deleteBBSMberInf(BoardMaster boardMaster);

	void deleteBBSMberInfHp(BoardMaster boardMaster);

	void deleteBBSMberInfNt(BoardMaster boardMaster);

	void deleteMnaResponse(BoardMaster boardMaster);
	
	void deletePolWatchDog(BoardMaster boardMaster);

	void deleteGepyosoResponse(BoardMaster boardMaster);

	void deleteSajeonResponse(BoardMaster boardMaster);
	
	void deleteMnaWatchDog(BoardMaster boardMaster);

	void updateBBSMasterInf(BoardMaster boardMaster) throws Exception;

	void updateBBSMasterInfHp(BoardMaster boardMaster) throws Exception;

	void updateBBSMasterInfNt(BoardMaster boardMaster) throws Exception;

	void updateResponseHp(BoardMaster boardMaster) throws Exception;

	void updateResponseNt(BoardMaster boardMaster) throws Exception;
	
	void updateMnaInf(BoardMaster boardMaster) throws Exception;
	
	void updatePolPlaceInf(BoardMaster boardMaster) throws Exception;
	
	BoardMasterVO updatePolPlaceWatchView(BoardMasterVO boardMasterVO) throws Exception;

	BoardMasterVO updateGepyosoResponseView(BoardMasterVO boardMasterVO) throws Exception;

	BoardMasterVO updateSajeonResponseView(BoardMasterVO boardMasterVO) throws Exception;

	BoardMasterVO selectBBSMasterInf(BoardMasterVO boardMasterVO) throws Exception;
	
	BoardMasterVO  selectBBSMasterInfHp(BoardMasterVO boardMasterVO) throws Exception;

	BoardMasterVO selectBBSMasterInfNt(BoardMasterVO boardMasterVO) throws Exception;

	BoardMasterVO selectMnaInf(BoardMasterVO boardMasterVO) throws Exception;

	BoardMasterVO selectGepyosoInf(BoardMasterVO boardMasterVO) throws Exception;

	BoardMasterVO selectSajeonTuInf(BoardMasterVO boardMasterVO) throws Exception;
	
	BoardMasterVO selectPolPlaceInf(BoardMasterVO boardMasterVO) throws Exception;
	
	BoardMasterVO updateBBSMasterView(BoardMasterVO boardMasterVO) throws Exception;

	BoardMasterVO updateBBSMasterView2Hp(BoardMasterVO boardMasterVO) throws Exception;

	BoardMasterVO updateBBSMasterView2Nt(BoardMasterVO boardMasterVO) throws Exception;

	BoardMasterVO updateMnaResponse(BoardMasterVO boardMasterVO) throws Exception;

	Map<String, Object> selectBBSMasterInfs(BoardMasterVO boardMasterVO);

	Map<String, Object> selectBBSMasterInfsHp(BoardMasterVO boardMasterVO);

	Map<String, Object> selectBBSMasterInfsNt(BoardMasterVO boardMasterVO);
	
	Map<String, Object> selectBbsMemberInfs(BoardMasterVO boardMasterVO);

	Map<String, Object> selectBbsMemberInfsHp(BoardMasterVO boardMasterVO);

	Map<String, Object> selectBbsMemberInfsNt(BoardMasterVO boardMasterVO);

	Map<String, Object> selectMnaResponse(BoardMasterVO boardMasterVO);
	
	Map<String, Object> selectMnaListInfs(BoardMasterVO boardMasterVO);

	Map<String, Object> selectGepyoso(BoardMasterVO boardMasterVO);

	Map<String, Object> selectSajeonSeon(BoardMasterVO boardMasterVO);
	
	Map<String, Object> selectDistwachListInfs(BoardMasterVO boardMasterVO);

	Map<String, Object> selectGepyosoResponseInfs(BoardMasterVO boardMasterVO);

	Map<String, Object> selectSajeonTuResponseInfs(BoardMasterVO boardMasterVO);
	
	Map<String, Object> selectPolPlaceListInfs(BoardMasterVO boardMasterVO);

	Map<String, Object> selectPollWatchListInfs(BoardMasterVO boardMasterVO);
	
	Map<String, Object> selecthelpRequestListInfs(BoardMasterVO boardMasterVO);

	Map<String, Object> selecthelpRequestListInfsNt(BoardMasterVO boardMasterVO);

	Map<String, Object> selectMnaRequest(BoardMasterVO boardMasterVO);

	void insertBBSMasterInf(BoardMaster boardMaster) throws Exception;

	void insertBBSMasterInfNt(BoardMaster boardMaster) throws Exception;
	
	void insertBBSMasterInfHp(BoardMaster boardMaster) throws Exception;
	
	void insertBbsMemberInf(BoardMaster boardMaster) throws Exception;

	void insertBbsMemberInfHp(BoardMaster boardMaster) throws Exception;

	void insertBbsMemberInfNt(BoardMaster boardMaster) throws Exception;
	
	void insertDistwach(BoardMaster boardMaster) throws Exception;

	void insertGepyosoResponse(BoardMaster boardMaster) throws Exception;

	void insertSajeonTuResponse(BoardMaster boardMaster) throws Exception;

	void insertPollWatch(BoardMaster boardMaster) throws Exception;
	

	/*
	 * 블로그 관련
	 *
	Map<String, Object> selectBlogMasterInfs(BoardMasterVO boardMasterVO);
	
	String checkBlogUser(BlogVO blogVO);
	
	BlogVO checkBlogUser2(BlogVO blogVO);

	void insertBoardBlogUserRqst(BlogUser blogUser);
	
	void insertBlogMaster(Blog blog) throws FdlException;
	
	BlogVO selectBlogDetail(BlogVO blogVO) throws Exception;

	List<BlogVO> selectBlogListPortlet(BlogVO blogVO) throws Exception;
*/
	List<BoardMasterVO> selectBBSListPortlet(BoardMasterVO boardMasterVO) throws Exception;

}
