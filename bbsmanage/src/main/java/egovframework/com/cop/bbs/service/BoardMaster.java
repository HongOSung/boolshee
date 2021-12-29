package egovframework.com.cop.bbs.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import egovframework.com.cmm.LoginVO;

/**
 *  게시판 속성정보를 담기위한 엔티티 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.12  이삼섭          최초 생성
 *   2009.06.26  한성곤		2단계 기능 추가 (댓글관리, 만족도조사)
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class BoardMaster implements Serializable {
	
    /** 전화번호 */
    private String tel = "";
    
    /** 단체총명 */
    private String fullnm = "";
    
    /** 회원수 */
    private Integer members = 0;
    
    /** 개표소 */
    private String gepyoso = "";
    
    /** 사전투표소 */
    private String tupyoso = "";
    
    /** 오전/오후 */
    private String ampm = "";
    
    /** 취지와 실천과제 */
    private String action = "";
    
    /** 도움요청 응답내용 */
    private String contents = "";
    
    /** 소시민단체 아이디 */
    private String bbsId = "";
    
    /** 소시민단체  명 */
    private String bbsNm;
    
    /** 소시민단체  명 */
    private String BBS_NM;
    
    /** 지킴이  회원 아이디 */
    private String MBER_ID;
    
    /** 지킴이  회원 명 */
    private String MBER_NM;
    
    /** 지킴이  추천자 아이디 */
    private String RECOMMENDER_ID;
    
    /** 지킴이  추천자 아이디 */
    private String RECOMMENDER_NAME;
    
    /** 지킴이  선거구 및 회원 아이디 유일키 */
    private String no_mberid;
    
    /** 소시민단체 가입 회원 아이디 */
    private String mberId = "";//
    
    /** 소시민단체 가입회원 명 */
    private String mberNm = "";
    
    /** 소시민단체 가입회원 유일키 명 */
    private String mbId_bsNm = "";
    
    /** 선거구 프라이머리 키 */
    private String no = "";    
    /** 선거구 국회의원 차수 제 몇대 */
    private String st = "";  
    
    /** 선거구 시도 */
    private String city = "";
    /** 선거구  */
    private String district = "";
    /** 정당*/
    private String party = "";
    /** 선거구 국회의원 이름*/
    private String mnaNm = "";
    /** 선거구 목록의 지킴이  사용 안함?*/
    private String Watchdog = "";
    /** 국회의원 사무실 호수*/
    private String hosu = "";
    /** 국회의원 사무실 국회 전화 */
    private String telMna = "";
    /** 국회의원 사무실 국회 팩스 */
    private String faxMna = "";
    /** 지역구 주소 */
    private String addLocal = "";
    /** 지역구 전화*/
    private String telLocal = "";
    /** 지역구 팩스 */
    private String faxLocal = "";
    /** 국회의원 이메일 */
    private String email = "";
    /** 홈페이지 */
    private String homepage = "";
    /** 페이스북 */
    private String facebook = "";
    /** 트위트 */
    private String twit = "";
    /** 유튜브 */
    private String utube = "";
    /** 국회의원 한자 이름 */
    private String mnaNmHan = "";
    /** 선거구 마지막 업데이트 사람 아이디*/
    private String LAST_UPDUSR_ID = "";
    /** 선거구 마지막 업데이트 일시 */
    private String LAST_UPDT_PNTTM = "";
    
    /** 투표 구분  */
    private String gubun = "";
    /** 투표 군 구*/
    private String gungu = "";
    /** 투표 동  */
    private String dong = "";
    /** 투표소이름*/
    private String pllngplce = "";
    /** 투표소건물  */
    private String bldngNm = "";
    /** 투표소주소*/
    private String voteAddr = "";
    
    /** 게시판 소개 */
    private String bbsIntrcn = "";
    
    /** 게시판 유형코드 */
    private String bbsTyCode = "";
    
    /** 파일첨부가능여부 */
    private String fileAtchPosblAt = "";
    
    /** 최초등록자 아이디 */
    private String frstRegisterId = "";
    
    /** 최초등록시점 */
    private String frstRegisterPnttm = "";
    
    /** 최종수정자 아이디 */
    public String lastUpdusrId = "";
    
    /** 최종수정시점 */
    private String lastUpdusrPnttm = "";
    
    /** 첨부가능파일숫자 */
    private int atchPosblFileNumber = 0;
    
    /** 첨부가능파일사이즈 */
    private String atchPosblFileSize = "";
    
    /** 답장가능여부 */
    private String replyPosblAt = "";
    
    /** 템플릿 아이디 */
    private String tmplatId = "";
    
    /** 사용여부 */
    private String useAt = "";
    
    /** 사용플래그 */
    private String bbsUseFlag = "";
    
    /** 대상 아이디 */
    private String trgetId = "";
    
    /** 등록구분코드 */
    private String registSeCode = "";
    
    /** 유일 아이디 */
    private String uniqId = "";
    
    /** 템플릿 명 */
    private String tmplatNm = "";
    
    /** 커뮤니티 ID */
    private String cmmntyId;
    
    /** 블로그 ID */
    private String blogId;
    
    /** 블로그 사용 유무 */
    private String blogAt;
    
    //---------------------------------
    // 2009.06.26 : 2단계 기능 추가
    //---------------------------------
    /** 추가 option (댓글-comment, 만족도조사-stsfdg) */
    private String option = "";
    
    /** 댓글 여부 */
    private String commentAt = "";
    
    /** 만족도조사 */
    private String stsfdgAt = "";
    ////-------------------------------

    /**
     * bbsId attribute를 리턴한다.
     * 
     * @return the bbsId
     */
    public String getBbsId() {
	return bbsId;
    }

    /**
     * bbsId attribute 값을 설정한다.
     * 
     * @param bbsId
     *            the bbsId to set
     */
    public void setBbsId(String bbsId) {
	this.bbsId = bbsId;
    }

    /**
     * bbsIntrcn attribute를 리턴한다.
     * 
     * @return the bbsIntrcn
     */
    public String getBbsIntrcn() {
	return bbsIntrcn;
    }

    /**
     * bbsIntrcn attribute 값을 설정한다.
     * 
     * @param bbsIntrcn
     *            the bbsIntrcn to set
     */
    public void setBbsIntrcn(String bbsIntrcn) {
	this.bbsIntrcn = bbsIntrcn;
    }

    /**
     * bbsNm attribute를 리턴한다.
     * 
     * @return the bbsNm
     */
    public String getBbsNm() {
	return bbsNm;
    }

    /**
     * bbsNm attribute 값을 설정한다.
     * 
     * @param bbsNm
     *            the bbsNm to set
     */
    public void setBbsNm(String bbsNm) {
	this.bbsNm = bbsNm;
    }

    /**
     * bbsTyCode attribute를 리턴한다.
     * 
     * @return the bbsTyCode
     */
    public String getBbsTyCode() {
	return bbsTyCode;
    }

    /**
     * bbsTyCode attribute 값을 설정한다.
     * 
     * @param bbsTyCode
     *            the bbsTyCode to set
     */
    public void setBbsTyCode(String bbsTyCode) {
	this.bbsTyCode = bbsTyCode;
    }

    /**
     * fileAtchPosblAt attribute를 리턴한다.
     * 
     * @return the fileAtchPosblAt
     */
    public String getFileAtchPosblAt() {
	return fileAtchPosblAt;
    }

    /**
     * fileAtchPosblAt attribute 값을 설정한다.
     * 
     * @param fileAtchPosblAt
     *            the fileAtchPosblAt to set
     */
    public void setFileAtchPosblAt(String fileAtchPosblAt) {
	this.fileAtchPosblAt = fileAtchPosblAt;
    }

    /**
     * frstRegisterId attribute를 리턴한다.
     * 
     * @return the frstRegisterId
     */
    public String getFrstRegisterId() {
	return frstRegisterId;
    }

    /**
     * frstRegisterId attribute 값을 설정한다.
     * 
     * @param frstRegisterId
     *            the frstRegisterId to set
     */
    public void setFrstRegisterId(String frstRegisterId) {
	this.frstRegisterId = frstRegisterId;
    }

    /**
     * frstRegisterPnttm attribute를 리턴한다.
     * 
     * @return the frstRegisterPnttm
     */
    public String getFrstRegisterPnttm() {
	return frstRegisterPnttm;
    }

    /**
     * frstRegisterPnttm attribute 값을 설정한다.
     * 
     * @param frstRegisterPnttm
     *            the frstRegisterPnttm to set
     */
    public void setFrstRegisterPnttm(String frstRegisterPnttm) {
	this.frstRegisterPnttm = frstRegisterPnttm;
    }

    /**
     * lastUpdusrId attribute를 리턴한다.
     * 
     * @return the lastUpdusrId
     */
    public String getLastUpdusrId() {
	return lastUpdusrId;
    }

    /**
     * lastUpdusrId attribute 값을 설정한다.
     * 
     * @param lastUpdusrId
     *            the lastUpdusrId to set
     */
    public void setLastUpdusrId(String lastUpdusrId) {
	this.lastUpdusrId = lastUpdusrId;
    }

    /**
     * lastUpdusrPnttm attribute를 리턴한다.
     * 
     * @return the lastUpdusrPnttm
     */
    public String getLastUpdusrPnttm() {
	return lastUpdusrPnttm;
    }

    /**
     * lastUpdusrPnttm attribute 값을 설정한다.
     * 
     * @param lastUpdusrPnttm
     *            the lastUpdusrPnttm to set
     */
    public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
	this.lastUpdusrPnttm = lastUpdusrPnttm;
    }

    /**
     * atchPosblFileNumber attribute를 리턴한다.
     * 
     * @return the atchPosblFileNumber
     */
    public int getAtchPosblFileNumber() {
	return atchPosblFileNumber;
    }

    /**
     * atchPosblFileNumber attribute 값을 설정한다.
     * 
     * @param atchPosblFileNumber
     *            the atchPosblFileNumber to set
     */
    public void setAtchPosblFileNumber(int atchPosblFileNumber) {
	this.atchPosblFileNumber = atchPosblFileNumber;
    }

    /**
     * atchPosblFileSize attribute를 리턴한다.
     * 
     * @return the atchPosblFileSize
     */
    public String getAtchPosblFileSize() {
	return atchPosblFileSize;
    }

    /**
     * atchPosblFileSize attribute 값을 설정한다.
     * 
     * @param atchPosblFileSize
     *            the atchPosblFileSize to set
     */
    public void setAtchPosblFileSize(String atchPosblFileSize) {
	this.atchPosblFileSize = atchPosblFileSize;
    }

    /**
     * replyPosblAt attribute를 리턴한다.
     * 
     * @return the replyPosblAt
     */
    public String getReplyPosblAt() {
	return replyPosblAt;
    }

    /**
     * replyPosblAt attribute 값을 설정한다.
     * 
     * @param replyPosblAt
     *            the replyPosblAt to set
     */
    public void setReplyPosblAt(String replyPosblAt) {
	this.replyPosblAt = replyPosblAt;
    }

    /**
     * tmplatId attribute를 리턴한다.
     * 
     * @return the tmplatId
     */
    public String getTmplatId() {
	return tmplatId;
    }

    /**
     * tmplatId attribute 값을 설정한다.
     * 
     * @param tmplatId
     *            the tmplatId to set
     */
    public void setTmplatId(String tmplatId) {
	this.tmplatId = tmplatId;
    }

    /**
     * useAt attribute를 리턴한다.
     * 
     * @return the useAt
     */
    public String getUseAt() {
	return useAt;
    }

    /**
     * useAt attribute 값을 설정한다.
     * 
     * @param useAt
     *            the useAt to set
     */
    public void setUseAt(String useAt) {
	this.useAt = useAt;
    }

    /**
     * bbsUseFlag attribute를 리턴한다.
     * 
     * @return the bbsUseFlag
     */
    public String getBbsUseFlag() {
	return bbsUseFlag;
    }

    /**
     * bbsUseFlag attribute 값을 설정한다.
     * 
     * @param bbsUseFlag
     *            the bbsUseFlag to set
     */
    public void setBbsUseFlag(String bbsUseFlag) {
	this.bbsUseFlag = bbsUseFlag;
    }

    /**
     * trgetId attribute를 리턴한다.
     * 
     * @return the trgetId
     */
    public String getTrgetId() {
	return trgetId;
    }

    /**
     * trgetId attribute 값을 설정한다.
     * 
     * @param trgetId
     *            the trgetId to set
     */
    public void setTrgetId(String trgetId) {
	this.trgetId = trgetId;
    }

    /**
     * registSeCode attribute를 리턴한다.
     * 
     * @return the registSeCode
     */
    public String getRegistSeCode() {
	return registSeCode;
    }

    /**
     * registSeCode attribute 값을 설정한다.
     * 
     * @param registSeCode
     *            the registSeCode to set
     */
    public void setRegistSeCode(String registSeCode) {
	this.registSeCode = registSeCode;
    }

    /**
     * uniqId attribute를 리턴한다.
     * 
     * @return the uniqId
     */
    public String getUniqId() {
	return uniqId;
    }

    /**
     * uniqId attribute 값을 설정한다.
     * 
     * @param uniqId
     *            the uniqId to set
     */
    public void setUniqId(String uniqId) {
	this.uniqId = uniqId;
    }

    /**
     * tmplatNm attribute를 리턴한다.
     * 
     * @return the tmplatNm
     */
    public String getTmplatNm() {
	return tmplatNm;
    }

    /**
     * tmplatNm attribute 값을 설정한다.
     * 
     * @param tmplatNm
     *            the tmplatNm to set
     */
    public void setTmplatNm(String tmplatNm) {
	this.tmplatNm = tmplatNm;
    }

    /**
     * option attribute를 리턴한다.
     * @return the option
     */
    public String getOption() {
        return option;
    }

    /**
     * option attribute 값을 설정한다.
     * @param option the option to set
     */
    public void setOption(String option) {
        this.option = option;
    }

    /**
     * commentAt attribute를 리턴한다.
     * @return the commentAt
     */
    public String getCommentAt() {
        return commentAt;
    }

    /**
     * commentAt attribute 값을 설정한다.
     * @param commentAt the commentAt to set
     */
    public void setCommentAt(String commentAt) {
        this.commentAt = commentAt;
    }

    /**
     * stsfdgAt attribute를 리턴한다.
     * @return the stsfdgAt
     */
    public String getStsfdgAt() {
        return stsfdgAt;
    }

    /**
     * stsfdg attribute 값을 설정한다.
     * @param stsfdgAt the stsfdgAt to set
     */
    public void setStsfdgAt(String stsfdgAt) {
        this.stsfdgAt = stsfdgAt;
    }
    
    /**
     * cmmntyId attribute를 리턴한다.
     * @return the cmmntyId
     */
    public String getCmmntyId() {
    	return cmmntyId;
    }
    
    /**
     * cmmntyId attribute 값을 설정한다.
     * @param cmmntyId the cmmntyId to set
     */
    public void setCmmntyId(String cmmntyId) {
    	this.cmmntyId = cmmntyId;
    }

    public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getBlogAt() {
		return blogAt;
	}

	public void setBlogAt(String blogAt) {
		this.blogAt = blogAt;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFullnm() {
		return fullnm;
	}

	public void setFullnm(String fullnm) {
		this.fullnm = fullnm;
	}

	public Integer getMembers() {
		return members;
	}

	public void setMembers(Integer members) {
		this.members = members;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMberId() {
		return mberId;
	}

	public void setMberId(String mberId) {
		this.mberId = mberId;
	}

	public String getMberNm() {
		return mberNm;
	}

	public void setMberNm(String mberNm) {
		this.mberNm = mberNm;
	}

	public String getMbId_bsNm() {
		return mbId_bsNm;
	}

	public void setMbId_bsNm(String mbId_bsNm) {
		this.mbId_bsNm = mbId_bsNm;
	}

	public String getBBS_NM() {
		return BBS_NM;
	}

	public void setBBS_NM(String bBS_NM) {
		BBS_NM = bBS_NM;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getMnaNm() {
		return mnaNm;
	}

	public void setMnaNm(String mnaNm) {
		this.mnaNm = mnaNm;
	}

	public String getWatchdog() {
		return Watchdog;
	}

	public void setWatchdog(String watchdog) {
		Watchdog = watchdog;
	}

	public String getHosu() {
		return hosu;
	}

	public void setHosu(String hosu) {
		this.hosu = hosu;
	}

	public String getTelMna() {
		return telMna;
	}

	public void setTelMna(String telMna) {
		this.telMna = telMna;
	}

	public String getFaxMna() {
		return faxMna;
	}

	public void setFaxMna(String faxMna) {
		this.faxMna = faxMna;
	}

	public String getAddLocal() {
		return addLocal;
	}

	public void setAddLocal(String addLocal) {
		this.addLocal = addLocal;
	}

	public String getTelLocal() {
		return telLocal;
	}

	public void setTelLocal(String telLocal) {
		this.telLocal = telLocal;
	}

	public String getFaxLocal() {
		return faxLocal;
	}

	public void setFaxLocal(String faxLocal) {
		this.faxLocal = faxLocal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwit() {
		return twit;
	}

	public void setTwit(String twit) {
		this.twit = twit;
	}

	public String getUtube() {
		return utube;
	}

	public void setUtube(String utube) {
		this.utube = utube;
	}

	public String getMnaNmHan() {
		return mnaNmHan;
	}

	public void setMnaNmHan(String mnaNmHan) {
		this.mnaNmHan = mnaNmHan;
	}

	public String getLAST_UPDUSR_ID() {
		return LAST_UPDUSR_ID;
	}

	public void setLAST_UPDUSR_ID(String lAST_UPDUSR_ID) {
		LAST_UPDUSR_ID = lAST_UPDUSR_ID;
	}

	public String getLAST_UPDT_PNTTM() {
		return LAST_UPDT_PNTTM;
	}

	public void setLAST_UPDT_PNTTM(String lAST_UPDT_PNTTM) {
		LAST_UPDT_PNTTM = lAST_UPDT_PNTTM;
	}

	public String getMBER_ID() {
		return MBER_ID;
	}

	public void setMBER_ID(String mBER_ID) {
		MBER_ID = mBER_ID;
	}

	public String getMBER_NM() {
		return MBER_NM;
	}

	public void setMBER_NM(String mBER_NM) {
		MBER_NM = mBER_NM;
	}

	public String getNo_mberid() {
		return no_mberid;
	}

	public void setNo_mberid(String no_mberid) {
		this.no_mberid = no_mberid;
	}

	public String getRECOMMENDER_ID() {
		return RECOMMENDER_ID;
	}

	public void setRECOMMENDER_ID(String rECOMMENDER_ID) {
		RECOMMENDER_ID = rECOMMENDER_ID;
	}

	public String getRECOMMENDER_NAME() {
		return RECOMMENDER_NAME;
	}

	public void setRECOMMENDER_NAME(String rECOMMENDER_NAME) {
		RECOMMENDER_NAME = rECOMMENDER_NAME;
	}

	public String getGubun() {
		return gubun;
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

	public String getGungu() {
		return gungu;
	}

	public void setGungu(String gungu) {
		this.gungu = gungu;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getPllngplce() {
		return pllngplce;
	}

	public void setPllngplce(String pllngplce) {
		this.pllngplce = pllngplce;
	}

	public String getBldngNm() {
		return bldngNm;
	}

	public void setBldngNm(String bldngNm) {
		this.bldngNm = bldngNm;
	}

	public String getVoteAddr() {
		return voteAddr;
	}

	public void setVoteAddr(String voteAddr) {
		this.voteAddr = voteAddr;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getGepyoso() {
		return gepyoso;
	}

	public void setGepyoso(String gepyoso) {
		this.gepyoso = gepyoso;
	}

	public String getTupyoso() {
		return tupyoso;
	}

	public void setTupyoso(String tupyoso) {
		this.tupyoso = tupyoso;
	}

	public String getAmpm() {
		return ampm;
	}

	public void setAmpm(String ampm) {
		this.ampm = ampm;
	}
}
