package egovframework.com.cop.bbs.service;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import egovframework.com.cmm.LoginVO;

/**
 * 게시판 속성 정보를 관리하기 위한 VO  클래스
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
 *   2009.3.12  이삼섭          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class BoardMasterVO extends BoardMaster implements Serializable {
    
    /** 검색시작일 */
    private String searchBgnDe = "";
    
    /** 검색조건 */
    private String searchCnd = "";
    
    /** 검색종료일 */
    private String searchEndDe = "";
    
    /** 검색단어 */
    private String searchWrd = "";
    
    /** 국회의원 이름 */
   	private String mnaNm ;
   	
    /** 국회의원 한자 이름 */
   	private String mnaNmHan ;
   	
   	/** 제 몇대 */
   	private String st ;
   	
   	/** 순번 primary key */
   	private String no ;
   	
   	/** 시 도 */
   	private String city ; 
   	
   	/** 시 도 */
   	private String tupyoso ;
   	
   	/** 오전/오후 */
   	private String ampm ;
   	
   	/** 선거 구 */
   	private String district ;
   	
   	/** 선거 구2 */
   	private String district2 ;
   	
   	/** 정당 */
   	private String party ;
   	
   	/** 지킴이 */
   	private String Watchdog ;
   	
   	/** 투표 구분  */
   	private String gubun ;
   	
   	/** 투표소 군 구 */
   	private String gungu ;
   	
   	/** 투표소 동 */
   	private String dong ;
   	
   	/** 투표소이름 */
   	private String pllngplce ;
   	
   	/** 투표소건물 */
   	private String bldngNm ;
   	
   	/** 투표소건물 */
   	private String voteAddr ;
   	
   	/** 개표소 */
   	private String gepyoso ;
   	
   	/** 회원수 */
   	private Integer members ;
   	
   	/** 지킴이 목록 지킴이 */
   	private String MBER_NM ;
   	
   	/** 소시민단체 가입회원 명 */
   	private String member ;
   	
   	/** 국회사무실 호수 */
   	private String hosu ;
   	
   	/** 국회사무실 전화 */
   	private String telMna ;
   	
   	/** 국회사무실 팩스 */
   	private String faxMna ;
   	
   	/** 지역주소 */
   	private String addLocal ;
   	
   	/** 지역전화 */
   	private String telLocal ;
   	
   	/** 지역팩스 */
   	private String faxLocal ;
   	
   	/** 이메일 */
   	private String email ;
   	
   	/** 홈페이지 */
   	private String homepage ;
   	
   	/** 페이스북 */
   	private String facebook ;
   	
   	/** 트위트 */
   	private String twit ;
   	
   	/** 유튜브 */
   	private String utube ;
    
    /** 소시민 단체명 */
	private String bbsNm ;
	
	/** 소시민 단체명 */
	private String BBS_NM ;
	
	 /** 소시민 단체 가입날짜 */
	private String sbsCrbDe ;
	
	/** 소시민 단체총명 */
	//private String fullNm ;
	
	/** 확인 전화번호 */
	private String telNo ;
	
	/** 도움 요청에서 소속 단체명 */
	private String BBS_NMST ;
	
	/** 검색단체명 */
	//private String members ;
	
	/** 검색단체명 */
	//private String action ;
    
    /** 정렬순서(DESC,ASC) */
    private String sortOrdr = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지갯수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

    /** rowNo */
    private int rowNo = 0;

    /** 최초 등록자명 */
    private String frstRegisterNm = "";

    /** 게시판유형 코드명 */
    private String bbsTyCodeNm = "";

    /** 템플릿 명 */
    private String tmplatNm = "";

    /** 최종 수정자명 */
    private String lastUpdusrNm = "";

    /** 권한지정 여부 */
    private String authFlag = "";

    /** 템플릿경로 */
    private String tmplatCours = "";

    /**
     * searchBgnDe attribute를 리턴한다.
     * 
     * @return the searchBgnDe
     */
    public String getSearchBgnDe() {
	return searchBgnDe;
    }

    /**
     * searchBgnDe attribute 값을 설정한다.
     * 
     * @param searchBgnDe
     *            the searchBgnDe to set
     */
    public void setSearchBgnDe(String searchBgnDe) {
	this.searchBgnDe = searchBgnDe;
    }

    /**
     * searchCnd attribute를 리턴한다.
     * 
     * @return the searchCnd
     */
    public String getSearchCnd() {
	return searchCnd;
    }

    /**
     * searchCnd attribute 값을 설정한다.
     * 
     * @param searchCnd
     *            the searchCnd to set
     */
    public void setSearchCnd(String searchCnd) {
	this.searchCnd = searchCnd;
    }

    /**
     * searchEndDe attribute를 리턴한다.
     * 
     * @return the searchEndDe
     */
    public String getSearchEndDe() {
	return searchEndDe;
    }

    /**
     * searchEndDe attribute 값을 설정한다.
     * 
     * @param searchEndDe
     *            the searchEndDe to set
     */
    public void setSearchEndDe(String searchEndDe) {
	this.searchEndDe = searchEndDe;
    }

    /**
     * searchWrd attribute를 리턴한다.
     * 
     * @return the searchWrd
     */
    public String getSearchWrd() {
	return searchWrd;
    }

    /**
     * searchWrd attribute 값을 설정한다.
     * 
     * @param searchWrd
     *            the searchWrd to set
     */
    public void setSearchWrd(String searchWrd) {
	this.searchWrd = searchWrd;
    }
    

    /**
     * sortOrdr attribute를 리턴한다.
     * 
     * @return the sortOrdr
     */
    public String getSortOrdr() {
	return sortOrdr;
    }

    /**
     * sortOrdr attribute 값을 설정한다.
     * 
     * @param sortOrdr
     *            the sortOrdr to set
     */
    public void setSortOrdr(String sortOrdr) {
	this.sortOrdr = sortOrdr;
    }

    /**
     * searchUseYn attribute를 리턴한다.
     * 
     * @return the searchUseYn
     */
    public String getSearchUseYn() {
	return searchUseYn;
    }

    /**
     * searchUseYn attribute 값을 설정한다.
     * 
     * @param searchUseYn
     *            the searchUseYn to set
     */
    public void setSearchUseYn(String searchUseYn) {
	this.searchUseYn = searchUseYn;
    }

    /**
     * pageIndex attribute를 리턴한다.
     * 
     * @return the pageIndex
     */
    public int getPageIndex() {
	return pageIndex;
    }

    /**
     * pageIndex attribute 값을 설정한다.
     * 
     * @param pageIndex
     *            the pageIndex to set
     */
    public void setPageIndex(int pageIndex) {
	this.pageIndex = pageIndex;
    }

    /**
     * pageUnit attribute를 리턴한다.
     * 
     * @return the pageUnit
     */
    public int getPageUnit() {
	return pageUnit;
    }

    /**
     * pageUnit attribute 값을 설정한다.
     * 
     * @param pageUnit
     *            the pageUnit to set
     */
    public void setPageUnit(int pageUnit) {
	this.pageUnit = pageUnit;
    }

    /**
     * pageSize attribute를 리턴한다.
     * 
     * @return the pageSize
     */
    public int getPageSize() {
	return pageSize;
    }

    /**
     * pageSize attribute 값을 설정한다.
     * 
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
    }

    /**
     * firstIndex attribute를 리턴한다.
     * 
     * @return the firstIndex
     */
    public int getFirstIndex() {
	return firstIndex;
    }

    /**
     * firstIndex attribute 값을 설정한다.
     * 
     * @param firstIndex
     *            the firstIndex to set
     */
    public void setFirstIndex(int firstIndex) {
	this.firstIndex = firstIndex;
    }

    /**
     * lastIndex attribute를 리턴한다.
     * 
     * @return the lastIndex
     */
    public int getLastIndex() {
	return lastIndex;
    }

    /**
     * lastIndex attribute 값을 설정한다.
     * 
     * @param lastIndex
     *            the lastIndex to set
     */
    public void setLastIndex(int lastIndex) {
	this.lastIndex = lastIndex;
    }

    /**
     * recordCountPerPage attribute를 리턴한다.
     * 
     * @return the recordCountPerPage
     */
    public int getRecordCountPerPage() {
	return recordCountPerPage;
    }

    /**
     * recordCountPerPage attribute 값을 설정한다.
     * 
     * @param recordCountPerPage
     *            the recordCountPerPage to set
     */
    public void setRecordCountPerPage(int recordCountPerPage) {
	this.recordCountPerPage = recordCountPerPage;
    }

    /**
     * rowNo attribute를 리턴한다.
     * 
     * @return the rowNo
     */
    public int getRowNo() {
	return rowNo;
    }

    /**
     * rowNo attribute 값을 설정한다.
     * 
     * @param rowNo
     *            the rowNo to set
     */
    public void setRowNo(int rowNo) {
	this.rowNo = rowNo;
    }

    /**
     * frstRegisterNm attribute를 리턴한다.
     * 
     * @return the frstRegisterNm
     */
    public String getFrstRegisterNm() {
	return frstRegisterNm;
    }

    /**
     * frstRegisterNm attribute 값을 설정한다.
     * 
     * @param frstRegisterNm
     *            the frstRegisterNm to set
     */
    public void setFrstRegisterNm(String frstRegisterNm) {
	this.frstRegisterNm = frstRegisterNm;
    }

    /**
     * bbsTyCodeNm attribute를 리턴한다.
     * 
     * @return the bbsTyCodeNm
     */
    public String getBbsTyCodeNm() {
	return bbsTyCodeNm;
    }

    /**
     * bbsTyCodeNm attribute 값을 설정한다.
     * 
     * @param bbsTyCodeNm
     *            the bbsTyCodeNm to set
     */
    public void setBbsTyCodeNm(String bbsTyCodeNm) {
	this.bbsTyCodeNm = bbsTyCodeNm;
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
     * lastUpdusrNm attribute를 리턴한다.
     * 
     * @return the lastUpdusrNm
     */
    public String getLastUpdusrNm() {
	return lastUpdusrNm;
    }

    /**
     * lastUpdusrNm attribute 값을 설정한다.
     * 
     * @param lastUpdusrNm
     *            the lastUpdusrNm to set
     */
    public void setLastUpdusrNm(String lastUpdusrNm) {
	this.lastUpdusrNm = lastUpdusrNm;
    }

    /**
     * authFlag attribute를 리턴한다.
     * 
     * @return the authFlag
     */
    public String getAuthFlag() {
	return authFlag;
    }

    /**
     * authFlag attribute 값을 설정한다.
     * 
     * @param authFlag
     *            the authFlag to set
     */
    public void setAuthFlag(String authFlag) {
	this.authFlag = authFlag;
    }

    /**
     * tmplatCours attribute를 리턴한다.
     * 
     * @return the tmplatCours
     */
    public String getTmplatCours() {
	return tmplatCours;
    }

    /**
     * tmplatCours attribute 값을 설정한다.
     * 
     * @param tmplatCours
     *            the tmplatCours to set
     */
    public void setTmplatCours(String tmplatCours) {
	this.tmplatCours = tmplatCours;
    }

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
/*
	public String getFullNm() {
		return fullNm;
	}

	public void setFullNm(String fullNm) {
		this.fullNm = fullNm;
	}*/

	public String getSbsCrbDe() {
		return sbsCrbDe;
	}

	public void setSbsCrbDe(String sbsCrbDe) {
		this.sbsCrbDe = sbsCrbDe;
	}

	public String getBbsNm() {
		return bbsNm;
	}

	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}

	public String getBBS_NM() {
		return BBS_NM;
	}

	public void setBBS_NM(String bBS_NM) {
		BBS_NM = bBS_NM;
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

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getDistrict2() {
		return district2;
	}

	public void setDistrict2(String district2) {
		this.district2 = district2;
	}

	public String getMnaNmHan() {
		return mnaNmHan;
	}

	public void setMnaNmHan(String mnaNmHan) {
		this.mnaNmHan = mnaNmHan;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
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
	

	public String getMBER_NM() {
		return MBER_NM;
	}

	public void setMBER_NM(String mBER_NM) {
		MBER_NM = mBER_NM;
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

	public String getGubun() {
		return gubun;
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getBBS_NMST() {
		return BBS_NMST;
	}

	public void setBBS_NMST(String bBS_NMST) {
		BBS_NMST = bBS_NMST;
	}

	public String getGepyoso() {
		return gepyoso;
	}

	public void setGepyoso(String gepyoso) {
		this.gepyoso = gepyoso;
	}

	public Integer getMembers() {
		return members;
	}

	public void setMembers(Integer members) {
		this.members = members;
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
