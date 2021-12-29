package egovframework.com.cop.bbs.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.cmy.service.CommunityVO;
/*
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.BlogUser;
*/
@Repository("EgovBBSMasterDAO")
public class EgovBBSMasterDAO extends EgovComAbstractDAO {

	public List<?> selectBBSMasterInfs(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectBBSMasterList", boardMasterVO);
	}

	public int selectBBSMasterInfsCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectBBSMasterListTotCnt", boardMasterVO);
	}

	public int selectGepyosoCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectGepyosoCnt", boardMasterVO);
	}

	public int selectSajeonSeonCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectSajeonSeonCnt", boardMasterVO);
	}
	
	public List<?> selectBBSMasterInfsHp(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectBBSMasterListHp", boardMasterVO);
	}

	public List<?> selectBBSMasterInfsNt(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectBBSMasterListNt", boardMasterVO);
	}

	public int selectBBSMasterInfsCntHp(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectBBSMasterListTotCntHp", boardMasterVO);
	}

	public int selectBBSMasterInfsCntNt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectBBSMasterListTotCntNt", boardMasterVO);
	}
	
	public BoardMasterVO selectBBSMasterDetail(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.selectBBSMasterDetail", boardMasterVO);
	}

	public BoardMasterVO selectBBSMasterDetailHp(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.selectBBSMasterDetailHp", boardMasterVO);
	}

	public BoardMasterVO selectBBSMasterDetailNt(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.selectBBSMasterDetailNt", boardMasterVO);
	}
	
	public BoardMasterVO selectMnaDetail(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.selectMnaDetail", boardMasterVO);
	}	

	public BoardMasterVO selectGepyosoDetail(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.selectGepyosoDetail", boardMasterVO);
	}
	
	public BoardMasterVO selectSajeonTuDetail(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.selectSajeonTuDetail", boardMasterVO);
	}

	public BoardMasterVO selectPollWatchDetail(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.selectPollWatchDetail", boardMasterVO);
	}
	
	public BoardMasterVO selectPolPlaceDetail(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.selectPolPlaceDetail", boardMasterVO);
	}
	
	public BoardMasterVO updatePolPlaceWatchView(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.updatePolPlaceWatchView", boardMasterVO);
	}

	public BoardMasterVO updateGepyosoResponseView(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.updateGepyosoResponseView", boardMasterVO);
	}

	public BoardMasterVO updateSajeonResponseView(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.updateSajeonResponseView", boardMasterVO);
	}
	
	public BoardMasterVO updateBBSMasterView(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.updateBBSMasterView", boardMasterVO);
	}
	
	public BoardMasterVO updateBBSMasterView2Hp(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.updateBBSMasterView2Hp", boardMasterVO);
	}

	public BoardMasterVO updateBBSMasterView2Nt(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.updateBBSMasterView2Nt", boardMasterVO);
	}

	public BoardMasterVO updateMnaResponse(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.updateMnaResponse", boardMasterVO);
	}
	
	public void insertDistwach(BoardMaster boardMaster) {
		insert("BBSMaster.insertDistwach", boardMaster);
	}

	public void insertGepyosoResponse(BoardMaster boardMaster) {
		insert("BBSMaster.insertGepyosoResponse", boardMaster);
	}

	public void insertSajeonTuResponse(BoardMaster boardMaster) {
		insert("BBSMaster.insertSajeonTuResponse", boardMaster);
	}
	
	
	public void insertPollWatch(BoardMaster boardMaster) {
		insert("BBSMaster.insertPollWatch", boardMaster);
	}

	public void insertBBSMasterInf(BoardMaster boardMaster) {
		insert("BBSMaster.insertBBSMaster", boardMaster);
	}

	public void insertBBSMasterInfNt(BoardMaster boardMaster) {
		insert("BBSMaster.insertBBSMasterNt", boardMaster);
	}
	
	public void insertBBSMasterInfHp(BoardMaster boardMaster) {
		insert("BBSMaster.insertBBSMasterHp", boardMaster);
	}
	
	public void updateBBSMaster(BoardMaster boardMaster) {
		update("BBSMaster.updateBBSMaster", boardMaster);
	}
	
	public void updateBBSMasterHp(BoardMaster boardMaster) {
		update("BBSMaster.updateBBSMasterHp", boardMaster);
	}

	public void updateBBSMasterNt(BoardMaster boardMaster) {
		update("BBSMaster.updateBBSMasterNt", boardMaster);
	}

	public void updateResponseHp(BoardMaster boardMaster) {
		update("BBSMaster.updateResponseHp", boardMaster);
	}
	
	public void updateResponseNt(BoardMaster boardMaster) {
		update("BBSMaster.updateResponseNt", boardMaster);
	}
	
	public void deleteBBSMaster(BoardMaster boardMaster) {
		update("BBSMaster.deleteBBSMaster", boardMaster);
	}
	
	public void deleteBBSMasterHp(BoardMaster boardMaster) {
		update("BBSMaster.deleteBBSMasterHp", boardMaster);
	}

	public void deleteBBSMasterNt(BoardMaster boardMaster) {
		update("BBSMaster.deleteBBSMasterNt", boardMaster);
	}
	
	public void deleteBBSMberInf(BoardMaster boardMaster) {
		update("BBSMaster.deleteBBSMberInf", boardMaster);
	}
	
	public void deleteBBSMberInfHp(BoardMaster boardMaster) {
		update("BBSMaster.deleteBBSMberInfHp", boardMaster);
	}

	public void deleteBBSMberInfNt(BoardMaster boardMaster) {
		update("BBSMaster."
				+ "", boardMaster);
	}
	
	public void deleteMnaResponse(BoardMaster boardMaster) {
		update("BBSMaster.deleteMnaResponse", boardMaster);
	}
	
	public void deletePolWatchDog(BoardMaster boardMaster) {
		update("BBSMaster.deletePolWatchDog", boardMaster);
	}

	public void deleteGepyosoResponse(BoardMaster boardMaster) {
		update("BBSMaster.deleteGepyosoResponse", boardMaster);
	}

	public void deleteSajeonResponse(BoardMaster boardMaster) {
		update("BBSMaster.deleteSajeonResponse", boardMaster);
	}
	
	public void deleteMnaWatchDog(BoardMaster boardMaster) {
		update("BBSMaster.deleteMnaWatchDog", boardMaster);
	}
	/*
	 * 소시민단체 가입 관련
	 */
	public List<?> selectBbsMemberInfs(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectBbsMemberList", boardMasterVO);
	}

	public List<?> selectBbsMemberInfsHp(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectBbsMemberListHp", boardMasterVO);
	}

	public List<?> selectBbsMemberInfsNt(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectBbsMemberListNt", boardMasterVO);
	}	

	public List<?> selectMnaResponse(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectMnaResponse", boardMasterVO);
	}

	public int selectMnaResponseCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectMnaResponseCnt", boardMasterVO);
	}

	public int selectBbsMemberInfsCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectBbsMemberListTotCnt", boardMasterVO);
	}

	public int selectBbsMemberInfsCntHp(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectBbsMemberListTotCntHp", boardMasterVO);
	}

	public int selectBbsMemberInfsCntNt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectBbsMemberListTotCntNt", boardMasterVO);
	}
	
	public BoardMasterVO selectBbsMemberDetail(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.selectBbsMemberDetail", boardMasterVO);
	}
	
	public void insertBbsMemberInf(BoardMaster boardMaster) {
		insert("BBSMaster.insertBbsMember", boardMaster);
	}

	public void insertBbsMemberInfHp(BoardMaster boardMaster) {
		insert("BBSMaster.insertBbsMemberInfHp", boardMaster);		
	}

	public void insertBbsMemberInfNt(BoardMaster boardMaster) {
		insert("BBSMaster.insertBbsMemberInfNt", boardMaster);
	}

	public void updateBbsMember(BoardMaster boardMaster) {
		update("BBSMaster.updateBbsMember", boardMaster);
	}
	
	public void updateMnaInf(BoardMaster boardMaster) {
		update("BBSMaster.updateMnaInf", boardMaster);
	}

	public void updatePolPlaceInf(BoardMaster boardMaster) {
		update("BBSMaster.updatePolPlaceInf", boardMaster);
	}

	public void deleteBbsMember(BoardMaster boardMaster) {
		update("BBSMaster.deleteBbsMember", boardMaster);
	}
	
	/*
	 * 선거구 관련
	 */
	public List<?> selectMnaListInfs(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectMnaListInfsList", boardMasterVO);
	}

	public List<?> selectGepyoso(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectGepyoso", boardMasterVO);
	}

	public List<?> selectSajeonSeon(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectSajeonSeon", boardMasterVO);
	}
	
	public int selectMnaListInfsCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectMnaListTotCnt", boardMasterVO);
	}
	
	public List<?> selectPolPlaceListInfs(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectPolPlaceListInfsList", boardMasterVO);
	}
	
	public int selectPolPlaceListInfsCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectPolPlaceListTotCnt", boardMasterVO);
	}	

	public List<?> selecthelpRequestListInfs(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selecthelpRequestListInfs", boardMasterVO);
	}

	public List<?> selecthelpRequestListInfsNt(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selecthelpRequestListInfsNt", boardMasterVO);
	}
	
	public int selecthelpRequestListInfsCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selecthelpRequestListTotCnt", boardMasterVO);
	}
	
	public List<?> selectMnaRequest(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectMnaRequest", boardMasterVO);
	}
	
	public int selectMnaRequestCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectMnaRequestCnt", boardMasterVO);
	}

	public int selecthelpRequestListInfsCntNt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selecthelpRequestListTotCntNt", boardMasterVO);
	}	
	
	public List<?> selectPollWatchListInfs(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectPollWatchListInfs", boardMasterVO);
	}
	
	public int selectPollWatchListInfsCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectPollWatchListTotCnt", boardMasterVO);
	}
	
	public List<?> selectDistwachListInfs(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectDistwachListInfs", boardMasterVO);
	}

	public int selectDistwachListInfsCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectDistwachListTotCnt", boardMasterVO);
	}
	
	public List<?> selectGepyosoResponseInfs(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectGepyosoResponseInfs", boardMasterVO);
	}

	public List<?> selectSajeonTuResponseInfs(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectSajeonTuResponseInfs", boardMasterVO);
	}
	
	public int selectGepyosoResponseInfsCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectGepyosoResponseListTotCnt", boardMasterVO);
	}
	
	public int selectSajeonTuResponseInfsCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectSajeonTuResponseListTotCnt", boardMasterVO);
	}
	
	
	/*
	 * 블로그 관련
	 */
	public List<?> selectBlogMasterInfs(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectBlogMasterList", boardMasterVO);
	}
	
	public int selectBlogMasterInfsCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectBlogMasterListTotCnt", boardMasterVO);
	}
	
	/*
	public int checkExistUser(BlogVO blogVO) {
		return (Integer)selectOne("BBSMaster.checkExistUser", blogVO);
	}
	
	public BlogVO checkExistUser2(BlogVO blogVO) {
		return (BlogVO) selectOne("BBSMaster.checkExistUser2", blogVO);
	}
	
	public void insertBoardBlogUserRqst(BlogUser blogUser) {
		insert("BBSMaster.insertBoardBlogUserRqst", blogUser);
	}
	
	public void insertBlogMaster(Blog blog) {
		insert("BBSMaster.insertBlogMaster", blog);
	}
	
	public BlogVO selectBlogDetail(BlogVO blogVO) {
		return (BlogVO) selectOne("BBSMaster.selectBlogDetail", blogVO);
	}

	public List<BlogVO> selectBlogListPortlet(BlogVO blogVO) throws Exception{
		return (List<BlogVO>) list("BBSMaster.selectBlogListPortlet", blogVO);
	}
*/
	public List<BoardMasterVO> selectBBSListPortlet(BoardMasterVO boardMasterVO) {
		return (List<BoardMasterVO>) list("BBSMaster.selectBBSListPortlet", boardMasterVO);
	}


}
