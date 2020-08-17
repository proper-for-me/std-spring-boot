package std.java.enumcase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class EnumObjectTest {

	
	
	@Test
	void test() {
	
		log.debug(Status.COMPLETE.getTitle());
		log.debug(Status.COMPLETE.getCode());
		
		log.debug(ApiInfoMang.getMemberInfo.getApiNm());
		log.debug(ApiInfoMang.getMemberInfo.getUrl());
		log.debug(ApiInfoMang.getMemberInfo.getDescription());
		log.debug(ApiInfoMang.getPointList.getDescription());
	}

}
