package std.java.enumcase;

public enum EnumObject {
	
  //  geMemberInfo(apiNm, url);
	
	geMemberInfo("getMemberInfoUrl", "http://getmemberInfoUrl");
	
	private String apiNm;
	private String getApiNm() {
		return apiNm;
	}

	public void setApiNm(String apiNm) {
		this.apiNm = apiNm;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String url;
	
	private EnumObject(String url, String apiNm) {
		this.apiNm = apiNm;
		this.url = url;
	}

	
	
}
