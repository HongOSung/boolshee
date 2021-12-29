package egovframework.com.cmm.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : CmmUseDAO.java
 * @Description : 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 데이터 접근 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Repository("cmmUseDAO")
public class CmmUseDAO extends EgovComAbstractDAO {

    private static final java.lang.String String = null;

	/**
     * 주어진 조건에 따른 공통코드를 불러온다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO vo) throws Exception {
	return (List<CmmnDetailCode>) list("CmmUseDAO.selectCmmCodeDetail", vo);
    }

    /**
     * 공통코드로 사용할 조직정보를 를 불러온다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<CmmnDetailCode> selectOgrnztIdDetail(ComDefaultCodeVO vo) throws Exception {
	return (List<CmmnDetailCode>) list("CmmUseDAO.selectOgrnztIdDetail", vo);
    }

    /**
     * 공통코드로 사용할그룹정보를 를 불러온다.
     * @param vo
     * @return
     * @throws Exception
     */
    public List<CmmnDetailCode> selectGroupIdDetail(ComDefaultCodeVO vo) {
	return (List<CmmnDetailCode>) list("CmmUseDAO.selectGroupIdDetail", vo);
	}
    
    @SuppressWarnings("unchecked")
    public List<CmmnDetailCode> selectbbsNmDetail(ComDefaultCodeVO vo) throws Exception {
	return (List<CmmnDetailCode>) list("CmmUseDAO.selectbbsNmDetail", vo);
    }
    
    @SuppressWarnings("unchecked")
	public List<CmmnDetailCode> selectbbsNmDetailHp(ComDefaultCodeVO vo) {
    	return (List<CmmnDetailCode>) list("CmmUseDAO.selectbbsNmDetailHp", vo);
	}
    
    @SuppressWarnings("unchecked")
   	public List<CmmnDetailCode> selectbbsNmDetailNt(ComDefaultCodeVO vo) {
       	return (List<CmmnDetailCode>) list("CmmUseDAO.selectbbsNmDetailNt", vo);
   	}
    
    @SuppressWarnings("unchecked")
   	public List<CmmnDetailCode> selectRequestDetail(ComDefaultCodeVO vo) {
       	return (List<CmmnDetailCode>) list("CmmUseDAO.selectRequestDetail", vo);
   	}
    
    @SuppressWarnings("unchecked")
    public List<CmmnDetailCode> selectDistwatchDetail(CmmnDetailCode vo) throws Exception {
	return (List<CmmnDetailCode>) list("CmmUseDAO.selectDistwatchDetail", vo);
    }
    
    @SuppressWarnings("unchecked")
    public List<CmmnDetailCode> selectGepyosoDetail(CmmnDetailCode vo) throws Exception {
	return (List<CmmnDetailCode>) list("CmmUseDAO.selectGepyosoDetail", vo);
    }

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<CmmnDetailCode> selectSajeonTuDetail(CmmnDetailCode vo) {
		return (List<CmmnDetailCode>) list("CmmUseDAO.selectSajeonTuDetail", vo);
	}

	public List<CmmnDetailCode> selectPolPlaceDetail(CmmnDetailCode vo) {
		return (List<CmmnDetailCode>) list("CmmUseDAO.selectPolPlaceDetail", vo);
	}
    
    public List<CmmnDetailCode> selectPasswordCnsrCk(ComDefaultCodeVO vo) throws Exception {
    	return (List<CmmnDetailCode>) list("CmmUseDAO.selectPasswordCnsrCk", vo);
    }
    
    public String  selectHelpRequest(String mberId) throws Exception {
    	return String ("CmmUseDAO.selectTel", mberId) ;
    }

	private java.lang.String String(java.lang.String string2, java.lang.String mberId) {
		// TODO Auto-generated method stub
		return null;
	}	

    
}
