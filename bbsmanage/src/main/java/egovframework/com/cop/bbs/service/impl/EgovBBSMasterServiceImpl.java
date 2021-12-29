package egovframework.com.cop.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.com.cmm.EgovComponentChecker;
//import egovframework.com.cop.bbs.service.Blog;
//import egovframework.com.cop.bbs.service.BlogUser;
//import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("EgovBBSMasterService")
public class EgovBBSMasterServiceImpl extends EgovAbstractServiceImpl implements EgovBBSMasterService {

	@Resource(name = "EgovBBSMasterDAO")
    private EgovBBSMasterDAO egovBBSMasterDao;

    @Resource(name = "egovBBSMstrIdGnrService")
    private EgovIdGnrService idgenService;
	
    //---------------------------------
    // 2009.06.26 : 2단계 기능 추가
    //---------------------------------
    @Resource(name = "BBSAddedOptionsDAO")
    private BBSAddedOptionsDAO addedOptionsDAO;
    ////-------------------------------
    
	@Override
	public Map<String, Object> selectNotUsedBdMstrList(BoardMasterVO boardMasterVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBBSMasterInf(BoardMaster boardMaster) {
		egovBBSMasterDao.deleteBBSMaster(boardMaster);
	}
	
	@Override
	public void deleteBBSMasterInfHp(BoardMaster boardMaster) {
		egovBBSMasterDao.deleteBBSMasterHp(boardMaster);
	}
	
	@Override
	public void deleteBBSMasterInfNt(BoardMaster boardMaster) {
		egovBBSMasterDao.deleteBBSMasterNt(boardMaster);
	}

	@Override
	public void deleteBBSMberInf(BoardMaster boardMaster) {
		egovBBSMasterDao.deleteBBSMberInf(boardMaster);
	}
	
	@Override
	public void deleteBBSMberInfHp(BoardMaster boardMaster) {
		egovBBSMasterDao.deleteBBSMberInfHp(boardMaster);
	}
	
	@Override
	public void deleteBBSMberInfNt(BoardMaster boardMaster) {
		egovBBSMasterDao.deleteBBSMberInfNt(boardMaster);
	}
	
	@Override
	public void deleteMnaResponse(BoardMaster boardMaster) {
		egovBBSMasterDao.deleteMnaResponse(boardMaster);
	}
	
	@Override
	public void deletePolWatchDog(BoardMaster boardMaster) {
		egovBBSMasterDao.deletePolWatchDog(boardMaster);	
	}
	
	@Override
	public void deleteGepyosoResponse(BoardMaster boardMaster) {
		egovBBSMasterDao.deleteGepyosoResponse(boardMaster);	
	}
	
	@Override
	public void deleteSajeonResponse(BoardMaster boardMaster) {
		egovBBSMasterDao.deleteSajeonResponse(boardMaster);	
	}
	
	@Override
	public void deleteMnaWatchDog(BoardMaster boardMaster) {
		egovBBSMasterDao.deleteMnaWatchDog(boardMaster);	
	}
	
	@Override
	public void updateMnaInf(BoardMaster boardMaster) throws Exception {
		egovBBSMasterDao.updateMnaInf(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}
		
	}
	
	@Override
	public void updatePolPlaceInf(BoardMaster boardMaster) throws Exception {
		egovBBSMasterDao.updatePolPlaceInf(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}
	}
	
	@Override
	public void updateBBSMasterInf(BoardMaster boardMaster) throws Exception {
		egovBBSMasterDao.updateBBSMaster(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}
		
	}
	
	@Override
	public void updateBBSMasterInfNt(BoardMaster boardMaster) throws Exception {
		egovBBSMasterDao.updateBBSMasterNt(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}
		
	}
	@Override
	public void updateBBSMasterInfHp(BoardMaster boardMaster) throws Exception {
		egovBBSMasterDao.updateBBSMasterHp(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}
		
	}
	@Override
	public void updateResponseHp(BoardMaster boardMaster) throws Exception {
		egovBBSMasterDao.updateResponseHp(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}
		
	}
	
	@Override
	public void updateResponseNt(BoardMaster boardMaster) throws Exception {
		egovBBSMasterDao.updateResponseNt(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}
		
	}

	@Override
	public BoardMasterVO selectBBSMasterInf(BoardMasterVO boardMasterVO) throws Exception {
		BoardMasterVO resultVO = egovBBSMasterDao.selectBBSMasterDetail(boardMasterVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        
    	if(EgovComponentChecker.hasComponent("EgovBBSCommentService") || EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){//2011.09.15
    	    BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(boardMasterVO);
    	    
    	    if (options != null) {
	    		if (options.getCommentAt().equals("Y")) {
	    			resultVO.setOption("comment");
	    		}
	
	    		if (options.getStsfdgAt().equals("Y")) {
	    			resultVO.setOption("stsfdg");
	    		}
    	    } else {
    	    	resultVO.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
    	    }
    	}
        
        return resultVO;
	}
	
	@Override
	public BoardMasterVO selectBBSMasterInfHp(BoardMasterVO boardMasterVO) {
		BoardMasterVO resultVO = egovBBSMasterDao.selectBBSMasterDetailHp(boardMasterVO);
        if (resultVO == null)
			try {
				throw processException("info.nodata.msg");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
        
    	if(EgovComponentChecker.hasComponent("EgovBBSCommentService") || EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){//2011.09.15
    	    BoardMasterVO options = null;
			try {
				options = addedOptionsDAO.selectAddedOptionsInf(boardMasterVO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    
    	    if (options != null) {
	    		if (options.getCommentAt().equals("Y")) {
	    			resultVO.setOption("comment");
	    		}
	
	    		if (options.getStsfdgAt().equals("Y")) {
	    			resultVO.setOption("stsfdg");
	    		}
    	    } else {
    	    	resultVO.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
    	    }
    	}
        
        return resultVO;
	}
	@Override
	public BoardMasterVO selectBBSMasterInfNt(BoardMasterVO boardMasterVO) {
		BoardMasterVO resultVO = egovBBSMasterDao.selectBBSMasterDetailNt(boardMasterVO);
        if (resultVO == null)
			try {
				throw processException("info.nodata.msg");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
        
    	if(EgovComponentChecker.hasComponent("EgovBBSCommentService") || EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){//2011.09.15
    	    BoardMasterVO options = null;
			try {
				options = addedOptionsDAO.selectAddedOptionsInf(boardMasterVO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    
    	    if (options != null) {
	    		if (options.getCommentAt().equals("Y")) {
	    			resultVO.setOption("comment");
	    		}
	
	    		if (options.getStsfdgAt().equals("Y")) {
	    			resultVO.setOption("stsfdg");
	    		}
    	    } else {
    	    	resultVO.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
    	    }
    	}
        
        return resultVO;
	}
	@Override
	public BoardMasterVO updateBBSMasterView(BoardMasterVO boardMasterVO) throws Exception {
		BoardMasterVO resultVO = egovBBSMasterDao.updateBBSMasterView(boardMasterVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        
    	if(EgovComponentChecker.hasComponent("EgovBBSCommentService") || EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){//2011.09.15
    	    BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(boardMasterVO);
    	    
    	    if (options != null) {
	    		if (options.getCommentAt().equals("Y")) {
	    			resultVO.setOption("comment");
	    		}
	
	    		if (options.getStsfdgAt().equals("Y")) {
	    			resultVO.setOption("stsfdg");
	    		}
    	    } else {
    	    	resultVO.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
    	    }
    	}
        
        return resultVO;
	}
	
	@Override
	public BoardMasterVO updateBBSMasterView2Hp(BoardMasterVO boardMasterVO) throws Exception {
		BoardMasterVO resultVO = egovBBSMasterDao.updateBBSMasterView2Hp(boardMasterVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        
    	if(EgovComponentChecker.hasComponent("EgovBBSCommentService") || EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){//2011.09.15
    	    BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(boardMasterVO);
    	    
    	    if (options != null) {
	    		if (options.getCommentAt().equals("Y")) {
	    			resultVO.setOption("comment");
	    		}
	
	    		if (options.getStsfdgAt().equals("Y")) {
	    			resultVO.setOption("stsfdg");
	    		}
    	    } else {
    	    	resultVO.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
    	    }
    	}
        
        return resultVO;
	}

	@Override
	public BoardMasterVO updateBBSMasterView2Nt(BoardMasterVO boardMasterVO) throws Exception {
		BoardMasterVO resultVO = egovBBSMasterDao.updateBBSMasterView2Nt(boardMasterVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        
    	if(EgovComponentChecker.hasComponent("EgovBBSCommentService") || EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){//2011.09.15
    	    BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(boardMasterVO);
    	    
    	    if (options != null) {
	    		if (options.getCommentAt().equals("Y")) {
	    			resultVO.setOption("comment");
	    		}
	
	    		if (options.getStsfdgAt().equals("Y")) {
	    			resultVO.setOption("stsfdg");
	    		}
    	    } else {
    	    	resultVO.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
    	    }
    	}
        
        return resultVO;
	}
	
	@Override
	public BoardMasterVO updateMnaResponse(BoardMasterVO boardMasterVO) throws Exception {
		BoardMasterVO resultVO = egovBBSMasterDao.updateMnaResponse(boardMasterVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        
    	if(EgovComponentChecker.hasComponent("EgovBBSCommentService") || EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){//2011.09.15
    	    BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(boardMasterVO);
    	    
    	    if (options != null) {
	    		if (options.getCommentAt().equals("Y")) {
	    			resultVO.setOption("comment");
	    		}
	
	    		if (options.getStsfdgAt().equals("Y")) {
	    			resultVO.setOption("stsfdg");
	    		}
    	    } else {
    	    	resultVO.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
    	    }
    	}
        
        return resultVO;
	}
	
	@Override
	public BoardMasterVO selectMnaInf(BoardMasterVO boardMasterVO) throws Exception {
		BoardMasterVO resultVO = egovBBSMasterDao.selectMnaDetail(boardMasterVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        
    	if(EgovComponentChecker.hasComponent("EgovBBSCommentService") || EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){//2011.09.15
    	    BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(boardMasterVO);
    	    
    	    if (options != null) {
	    		if (options.getCommentAt().equals("Y")) {
	    			resultVO.setOption("comment");
	    		}
	
	    		if (options.getStsfdgAt().equals("Y")) {
	    			resultVO.setOption("stsfdg");
	    		}
    	    } else {
    	    	resultVO.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
    	    }
    	}
        return resultVO;
	}
	
	@Override
	public BoardMasterVO selectGepyosoInf(BoardMasterVO boardMasterVO) throws Exception {
		BoardMasterVO resultVO = egovBBSMasterDao.selectGepyosoDetail(boardMasterVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}
	
	@Override
	public BoardMasterVO selectSajeonTuInf(BoardMasterVO boardMasterVO) throws Exception {
		BoardMasterVO resultVO = egovBBSMasterDao.selectSajeonTuDetail(boardMasterVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}
	
	@Override
	public BoardMasterVO selectPolPlaceInf(BoardMasterVO boardMasterVO) throws Exception {
		BoardMasterVO resultVO = egovBBSMasterDao.selectPolPlaceDetail(boardMasterVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        
    	if(EgovComponentChecker.hasComponent("EgovBBSCommentService") || EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){//2011.09.15
    	    BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(boardMasterVO);
    	    
    	    if (options != null) {
	    		if (options.getCommentAt().equals("Y")) {
	    			resultVO.setOption("comment");
	    		}
	
	    		if (options.getStsfdgAt().equals("Y")) {
	    			resultVO.setOption("stsfdg");
	    		}
    	    } else {
    	    	resultVO.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
    	    }
    	}
        return resultVO;
	}
	
	@Override
	public BoardMasterVO updatePolPlaceWatchView(BoardMasterVO boardMasterVO) throws Exception {
		// TODO Auto-generated method stub
		//return null;
		BoardMasterVO resultVO = egovBBSMasterDao.updatePolPlaceWatchView(boardMasterVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        
    	if(EgovComponentChecker.hasComponent("EgovBBSCommentService") || EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){//2011.09.15
    	    BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(boardMasterVO);
    	    
    	    if (options != null) {
	    		if (options.getCommentAt().equals("Y")) {
	    			resultVO.setOption("comment");
	    		}
	
	    		if (options.getStsfdgAt().equals("Y")) {
	    			resultVO.setOption("stsfdg");
	    		}
    	    } else {
    	    	resultVO.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
    	    }
    	}
    	////System.err.println("======Impl===resultVO.toString()="+resultVO.toString());
        return resultVO;
	}
	
	@Override
	public BoardMasterVO updateGepyosoResponseView(BoardMasterVO boardMasterVO) throws Exception {
		// TODO Auto-generated method stub
		//return null;
		BoardMasterVO resultVO = egovBBSMasterDao.updateGepyosoResponseView(boardMasterVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        
    	if(EgovComponentChecker.hasComponent("EgovBBSCommentService") || EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){//2011.09.15
    	    BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(boardMasterVO);
    	    
    	    if (options != null) {
	    		if (options.getCommentAt().equals("Y")) {
	    			resultVO.setOption("comment");
	    		}
	
	    		if (options.getStsfdgAt().equals("Y")) {
	    			resultVO.setOption("stsfdg");
	    		}
    	    } else {
    	    	resultVO.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
    	    }
    	}
    	////System.err.println("======Impl===resultVO.toString()="+resultVO.toString());
        return resultVO;
	}
	
	@Override
	public BoardMasterVO updateSajeonResponseView(BoardMasterVO boardMasterVO) throws Exception {
		// TODO Auto-generated method stub
		//return null;
		BoardMasterVO resultVO = egovBBSMasterDao.updateSajeonResponseView(boardMasterVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        
    	if(EgovComponentChecker.hasComponent("EgovBBSCommentService") || EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){//2011.09.15
    	    BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(boardMasterVO);
    	    
    	    if (options != null) {
	    		if (options.getCommentAt().equals("Y")) {
	    			resultVO.setOption("comment");
	    		}
	
	    		if (options.getStsfdgAt().equals("Y")) {
	    			resultVO.setOption("stsfdg");
	    		}
    	    } else {
    	    	resultVO.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
    	    }
    	}
    	////System.err.println("======Impl===resultVO.toString()="+resultVO.toString());
        return resultVO;
	}

	@Override
	public Map<String, Object> selectBBSMasterInfs(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectBBSMasterInfs(boardMasterVO);
		int cnt = egovBBSMasterDao.selectBBSMasterInfsCnt(boardMasterVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	@Override
	public Map<String, Object> selectBBSMasterInfsHp(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectBBSMasterInfsHp(boardMasterVO);
		int cnt = egovBBSMasterDao.selectBBSMasterInfsCntHp(boardMasterVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	@Override
	public Map<String, Object> selectBBSMasterInfsNt(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectBBSMasterInfsNt(boardMasterVO);
		int cnt = egovBBSMasterDao.selectBBSMasterInfsCntNt(boardMasterVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	@Override
	public Map<String, Object> selectMnaListInfs(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectMnaListInfs(boardMasterVO);
		int cnt = egovBBSMasterDao.selectMnaListInfsCnt(boardMasterVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	@Override
	public Map<String, Object> selectGepyoso(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectGepyoso(boardMasterVO);
		int cnt = egovBBSMasterDao.selectGepyosoCnt(boardMasterVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}	
	
	@Override
	public Map<String, Object> selectSajeonSeon(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectSajeonSeon(boardMasterVO);
		int cnt = egovBBSMasterDao.selectSajeonSeonCnt(boardMasterVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}	

	@Override
	public Map<String, Object> selectPolPlaceListInfs(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectPolPlaceListInfs(boardMasterVO);
		int cnt = egovBBSMasterDao.selectPolPlaceListInfsCnt(boardMasterVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	@Override
	public Map<String, Object> selecthelpRequestListInfs(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selecthelpRequestListInfs(boardMasterVO);
		int cnt = egovBBSMasterDao.selecthelpRequestListInfsCnt(boardMasterVO);   
		   
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	@Override
	public Map<String, Object> selecthelpRequestListInfsNt(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selecthelpRequestListInfsNt(boardMasterVO);
		int cnt = egovBBSMasterDao.selecthelpRequestListInfsCntNt(boardMasterVO);   
		   
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	@Override
	public Map<String, Object> selectMnaRequest(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectMnaRequest(boardMasterVO);
		int cnt = egovBBSMasterDao.selectMnaRequestCnt(boardMasterVO);   
		   
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	@Override
	public Map<String, Object> selectPollWatchListInfs(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectPollWatchListInfs(boardMasterVO);
		int cnt = egovBBSMasterDao.selectPollWatchListInfsCnt(boardMasterVO);   
		   
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	public Map<String, Object> selectDistwachListInfs(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectDistwachListInfs(boardMasterVO);
		int cnt = egovBBSMasterDao.selectDistwachListInfsCnt(boardMasterVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	public Map<String, Object> selectGepyosoResponseInfs(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectGepyosoResponseInfs(boardMasterVO);
		int cnt = egovBBSMasterDao.selectGepyosoResponseInfsCnt(boardMasterVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	public Map<String, Object> selectSajeonTuResponseInfs(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectSajeonTuResponseInfs(boardMasterVO);
		int cnt = egovBBSMasterDao.selectSajeonTuResponseInfsCnt(boardMasterVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	@Override
	public Map<String, Object> selectBbsMemberInfs(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectBbsMemberInfs(boardMasterVO);
		int cnt = egovBBSMasterDao.selectBbsMemberInfsCnt(boardMasterVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	@Override
	public Map<String, Object> selectBbsMemberInfsHp(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectBbsMemberInfsHp(boardMasterVO);
		int cnt = egovBBSMasterDao.selectBbsMemberInfsCntHp(boardMasterVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	@Override
	public Map<String, Object> selectBbsMemberInfsNt(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectBbsMemberInfsNt(boardMasterVO);
		int cnt = egovBBSMasterDao.selectBbsMemberInfsCntNt(boardMasterVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	@Override
	public Map<String, Object> selectMnaResponse(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectMnaResponse(boardMasterVO);
		int cnt = egovBBSMasterDao.selectMnaResponseCnt(boardMasterVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
		
	@Override
	public void insertDistwach(BoardMaster boardMaster) throws Exception {
		
		//게시판 ID 채번
		String bbsId = idgenService.getNextStringId();
		boardMaster.setBbsId(bbsId);
		
		egovBBSMasterDao.insertDistwach(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}

	}
	
	@Override
	public void insertGepyosoResponse(BoardMaster boardMaster) throws Exception {
		
		//게시판 ID 채번
		String bbsId = idgenService.getNextStringId();
		boardMaster.setBbsId(bbsId);
		
		egovBBSMasterDao.insertGepyosoResponse(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}

	}
	
	@Override
	public void insertSajeonTuResponse(BoardMaster boardMaster) throws Exception {
		
		//게시판 ID 채번
		String bbsId = idgenService.getNextStringId();
		boardMaster.setBbsId(bbsId);
		
		egovBBSMasterDao.insertSajeonTuResponse(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		/*if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}*/

	}
	
	@Override
	public void insertPollWatch(BoardMaster boardMaster) throws Exception {
		
		//게시판 ID 채번
		String bbsId = idgenService.getNextStringId();
		boardMaster.setBbsId(bbsId);
		
		egovBBSMasterDao.insertPollWatch(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}

	}
	
	@Override
	public void insertBBSMasterInf(BoardMaster boardMaster) throws Exception {
		
		//게시판 ID 채번
		String bbsId = idgenService.getNextStringId();
		boardMaster.setBbsId(bbsId);
		
		egovBBSMasterDao.insertBBSMasterInf(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}
	}
	
	@Override
	public void insertBBSMasterInfNt(BoardMaster boardMaster) throws Exception {
		
		//게시판 ID 채번
		String bbsId = idgenService.getNextStringId();
		boardMaster.setBbsId(bbsId);
		
		egovBBSMasterDao.insertBBSMasterInfNt(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}
	}
	@Override
	public void insertBBSMasterInfHp(BoardMaster boardMaster) throws Exception {
		
		//게시판 ID 채번
		String bbsId = idgenService.getNextStringId();
		boardMaster.setBbsId(bbsId);
		
		egovBBSMasterDao.insertBBSMasterInfHp(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}

	}
	
	@Override
	public void insertBbsMemberInf(BoardMaster boardMaster) throws Exception {
		
		//게시판 ID 채번
		String bbsId = idgenService.getNextStringId();
		boardMaster.setBbsId(bbsId);
		
		egovBBSMasterDao.insertBbsMemberInf(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}
	}
	
	@Override
	public void insertBbsMemberInfHp(BoardMaster boardMaster) throws Exception {
		
		//게시판 ID 채번
		String bbsId = idgenService.getNextStringId();
		boardMaster.setBbsId(bbsId);
		
		egovBBSMasterDao.insertBbsMemberInfHp(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}
	}
	
	@Override
	public void insertBbsMemberInfNt(BoardMaster boardMaster) throws Exception {
		
		//게시판 ID 채번
		String bbsId = idgenService.getNextStringId();
		boardMaster.setBbsId(bbsId);
		
		egovBBSMasterDao.insertBbsMemberInfNt(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}
	}
	/*
	@Override
	public String checkBlogUser(BlogVO blogVO) {

		int userCnt = egovBBSMasterDao.checkExistUser(blogVO);
		
		if (userCnt == 0) {
		    return "";
		} else {
		    return "EXIST";
		}
	}
	
	@Override
	public BlogVO checkBlogUser2(BlogVO blogVO) {
		BlogVO userBlog = egovBBSMasterDao.checkExistUser2(blogVO);
		return userBlog;
	}
	
	@Override
	public void insertBoardBlogUserRqst(BlogUser blogUser) {
		egovBBSMasterDao.insertBoardBlogUserRqst(blogUser);
	}
	
	@Override
	public void insertBlogMaster(Blog blog) throws FdlException {
		egovBBSMasterDao.insertBlogMaster(blog);
	}
	
	@Override
	public BlogVO selectBlogDetail(BlogVO blogVO) throws Exception {
		BlogVO resultVO = egovBBSMasterDao.selectBlogDetail(blogVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

	@Override
	public List<BlogVO> selectBlogListPortlet(BlogVO blogVO) throws Exception{
		return egovBBSMasterDao.selectBlogListPortlet(blogVO);
	}
*/
	@Override
	public List<BoardMasterVO> selectBBSListPortlet(BoardMasterVO boardMasterVO) throws Exception {
		return egovBBSMasterDao.selectBBSListPortlet(boardMasterVO);
	}


}
