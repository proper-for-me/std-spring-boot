package std.java.enumcase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum ApiInfoMang {
	//${api.getMemberInfo.url}
	getMemberInfo("requestMemberInfo", "http:///requestMemberInfo 이건 constrant 값 ", "회원정보가져오기 "),
	getPointInfo("requestPointInfo", "http:///getPointInfo", "회원 포인트 정보  "),
	getPointList("requestPointInfo", "http:///getPointList", "회원 포인트 목록  ");

	
	@Getter
	private String apiNm;
	
	@Getter @Setter
	private String url;

	@Getter
	private String description;

	
	
}
