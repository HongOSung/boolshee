package egovframework.com.cmm.service;

import java.io.Serializable;

/**
 * 공통상세코드 모델 클래스
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *   2017.09.07	이정은		표준프레임워크 v3.7 개선(clCode 추가)
 *
 * </pre>
 */
public class CmmnDetailCode implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * 분류코드
	 */
	private String clCode = "";

	/*
	 * 코드ID
	 */
    private String codeId = "";

    /*
     * 코드ID명
     */
    private String codeIdNm = "";

    /*
     * 상세코드
     */
	private String code = "";

	/*
	 * 상세코드명
	 */
    private String codeNm = "";
    
    /*
	 * 소시민단체명
	 */
    private String bbsNm = "";
    
    /*
	 * 선거구 시도
	 */
    private String city = "";
    
    /*
 	 * 선거구 군 구
 	 */
     private String gungu = "";
     
     /*
  	 * 오전/오후
  	 */
      private String ampm = "";
      
      /*
    	 * 오전/오후
    	 */
        private String day = "";
    
    /*
	 * 선거구
	 */
    private String district = "";
    
    /*
	 * 개표소
	 */
    private String gepyoso = "";
    
    /*
	 * 개표소
	 */
    private String tupyoso = "";
    
    /*
	 * 회원수
	 */
    private Integer members = 0;
    
    /*
	 * 투표소 명
	 */
    private String pllngplce = "";
    
    /*
	 * 투표소건물명
	 */
    private String bldngNm = "";
    
    /*
   	 * 국회의원 프라이머리 키
   	 */
       private String no = "";
       
   /*
  	 * 선거구 지킴이 등록 회원 아이디
  	 */
      private String mberId = "";
    
    /*
	 * 국회의원 차수
	 */
    private String st = "";
    
    /*
	 * 국회의원 이름
	 */
    private String mnaNm = "";
    
    /*
	 * 정당 명
	 */
    private String party = "";
    
    /*
	 * 지킴이  아이디
	 */
    private String MBER_ID = "";
    
    /*
	 * 지킴이 이름
	 */
    private String MBER_NM = "";
    
    /*
	 * 지킴이 추천자 아이디
	 */
    private String RECOMMENDER_ID = "";
    
    /*
	 * 지킴이 추천자 이름
	 */
    private String RECOMMENDER_NAME = "";
    
    /*
	 * 비밀번호 정답
	 */
    private String passwordCnsrCk = "";
    
    /*
	 * 가입시 전화번호 정답
	 */
    private String telNoCk = "";
    /*
	 * 도움 요청내용
	 */
    private String action = "";

    /*
     * 상세코드설명
     */
    private String codeDc = "";

    /*
     * 사용여부
     */
    private String useAt = "";

    /*
     * 최초등록자ID
     */
    private String frstRegisterId = "";

    /*
     * 최종수정자ID
     */
    private String lastUpdusrId   = "";


    /**
     * clCode attribute 를 리턴한다.
     * @return String
     */
    public String getClCode() {
    	return clCode;
    }
    
    /**
     * clCode attribute 값을 설정한다.
     * @param clCode String
     */
    public void setClCode(String clCode) {
    	this.clCode = clCode;
    }

    /**
	 * codeId attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeId() {
		return codeId;
	}

	/**
	 * codeId attribute 값을 설정한다.
	 * @param codeId String
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	/**
	 * codeIdNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeIdNm() {
		return codeIdNm;
	}

	/**
	 * codeIdNm attribute 값을 설정한다.
	 * @param codeIdNm String
	 */
	public void setCodeIdNm(String codeIdNm) {
		this.codeIdNm = codeIdNm;
	}

	/**
	 * code attribute 를 리턴한다.
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * code attribute 값을 설정한다.
	 * @param code String
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * codeNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeNm() {
		return codeNm;
	}

	/**
	 * codeNm attribute 값을 설정한다.
	 * @param codeNm String
	 */
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	/**
	 * codeDc attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeDc() {
		return codeDc;
	}

	/**
	 * codeDc attribute 값을 설정한다.
	 * @param codeDc String
	 */
	public void setCodeDc(String codeDc) {
		this.codeDc = codeDc;
	}

	/**
	 * useAt attribute 를 리턴한다.
	 * @return String
	 */
	public String getUseAt() {
		return useAt;
	}

	/**
	 * useAt attribute 값을 설정한다.
	 * @param useAt String
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * @return String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * @param frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * lastUpdusrId attribute 를 리턴한다.
	 * @return String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * @param lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public String getBbsNm() {
		return bbsNm;
	}

	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}

	public String getPasswordCnsrCk() {
		return passwordCnsrCk;
	}

	public void setPasswordCnsrCk(String passwordCnsrCk) {
		this.passwordCnsrCk = passwordCnsrCk;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
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

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMnaNm() {
		return mnaNm;
	}

	public void setMnaNm(String mnaNm) {
		this.mnaNm = mnaNm;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getMberId() {
		return mberId;
	}

	public void setMberId(String mberId) {
		this.mberId = mberId;
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

	public String getTelNoCk() {
		return telNoCk;
	}

	public void setTelNoCk(String telNoCk) {
		this.telNoCk = telNoCk;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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

	public String getGungu() {
		return gungu;
	}

	public void setGungu(String gungu) {
		this.gungu = gungu;
	}

	public String getAmpm() {
		return ampm;
	}

	public void setAmpm(String ampm) {
		this.ampm = ampm;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}


}
