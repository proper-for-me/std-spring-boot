package std.java.enumcase;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum Status implements EnumMapperType
{

    PROCEEDING("진행중"),
    COMPLETE("진행완료");
	
	@Getter
	private String title;
	

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return name();
	}


  
}
